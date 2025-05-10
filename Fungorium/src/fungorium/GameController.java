package fungorium;

import java.util.*;

public class GameController {
    private Random rand = new Random();
    private IView view;
    private static HashSet<Object> players = new HashSet<>();
    private Object currentPlayer;
    private int playerIndex = 0;
    private HashMap<Insect, Integer> InsectMovesLeft = new HashMap<Insect, Integer>();
    private boolean isRandom = true;
    private boolean turns = true;
    private int roundsLeft = 0;
    private boolean gameOver = false;

    public GameController(IView view){
        this.view = view;
    }

    public void insectInit(){
        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        for(Map.Entry<String,Object> entry : planet.entrySet()){
            if(entry.getKey().matches("I\\d+")){           // insect max lepesek beallitasa
                InsectMovesLeft.put( (Insect)entry.getValue(), ((Insect) entry.getValue()).GetSpeed() );
            }
        }
    }

    /**
     *  Betolti a jatekosokat a kor logika kezelesehez. A planet feltoltese utan erdemes hasznalni.
     */
    public void playersInit(){
        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        for(Map.Entry<String,Object> entry : planet.entrySet()){
            if(entry.getKey().matches("F\\d+")){                // Gomba
                players.add( (Fungus)entry.getValue() );
            }
            else if(entry.getKey().matches("IC\\d+")){          // InsectColony
                players.add( (InsectColony)entry.getValue() );
            }
            else if(entry.getKey().matches("I\\d+")){           // insect max lepesek beallitasa
                InsectMovesLeft.put( (Insect)entry.getValue(), ((Insect) entry.getValue()).GetSpeed() );
            }
        }
        currentPlayer = players.toArray()[0];       // elso player
    }

    /**
     * A jatek korenek veget jelzi. Vegigmegy a jatek elemein es meghivja a megfelelo metodusokat.
     * 1. gombatesteket oregiti
     * 2. rovarokon levo effecteket kezeli
     * 3. tektonokat tor kette, ha be van kapcsolva a random. 5% esellyel.
     */
    private void EndOfRound(){
        if(!turns)
            return;

        if(roundsLeft > 0)
            roundsLeft--;
        else{
            gameOver = true;
            return;
        }


        List<String> fbKeys = new ArrayList<>();
        List<String> iKeys = new ArrayList<>();
        List<String> tKeys = new ArrayList<>();

        // kiszedjuk a szukseges kulcsokat
        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        for(Map.Entry<String,Object> entry : planet.entrySet()){
            if(entry.getKey().matches("FB\\d+")){           // gombatest
                fbKeys.add(entry.getKey());
            }
            else if(entry.getKey().matches("I\\d+")){       // rovar
                iKeys.add(entry.getKey());
            }
            else if(entry.getKey().matches("T\\d+")){       // tekton
                tKeys.add(entry.getKey());
            }
        }

        // gombatesteken vegigmegy
        for(String fbKey : fbKeys){
            FungusBody fb = (FungusBody) planet.get(fbKey);
            if(fb != null && !fb.isDead ) {                                       // ha el a gombatest
                fb.SetAge(fb.GetAge() + 1);
            }
        }

        // rovarokon vegigmegy
        for(String iKey : iKeys){
            Insect i = (Insect) planet.get(iKey);
            if( i!= null && i.GetEatenBy() == null){                            // ha meg el az insect
                i.SetEffectTimeLeft( i.GetEffectTimeLeft() - 1);
                if(i.GetEffectTimeLeft() <= 0 ){                                // ha lejart rola a spora hatasa
                    i.SetSpeed(2);
                    i.SetCutAbility(true);
                }
                InsectMovesLeft.put(i,i.GetSpeed());                         // visszaalitjuk a lepeseit a kor vegen
            }
        }

        // tektonokon vegigmegy
        for(String tKey : tKeys){
            ITectonController tc = (ITectonController) planet.get(tKey);
            tc.AbsorbHyphas();                                              // felszivja a hifakat

            if(tc != null && isRandom){                                     // ha be van kapcsolva a random
                int chance = rand.nextInt(100);
                if(chance < 5){                                             // dobunk a kockaval, 5% esellyel torik el a tecton
                   if(!BreakTecton(tc) ){
                       System.err.println("#A tekton nem tudott szettorni!");
                   }
                }
            }
        }

        CleanUpHyphas(planet);
        CleanUpSpores(planet);
        CleanUpFungusBodies(planet);
    }

    public boolean BreakTecton(ITectonController tc){
        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        Tecton newT;
        newT = tc.Break();
        if(newT == null){
            System.out.println("#A tekton torese sikertelen!");
            return false;
        }

        view.InctCtr();
        String name = "T" + view.gettCtr();
        planet.put(name, newT);

        CleanUpHyphas(planet);
        CleanUpSpores(planet);
        CleanUpFungusBodies(planet);
        System.out.println("#Sikeres a tekton torese.");
        return true;
    }

    /**
     * A parameterben atadott utasitasnak megfeleloen lepteti a jatek koret.
     *
     * @param event - String, ha = "nr", kovetkezo kor, jatekost nem valt
     *              -         ha = "np", kovetkezo jatekos. Utolso jatekos utan uj kor jon es visszakerul az elso jatekos.
     */
    public boolean Trigg(String event){
        if(!turns) {
            System.out.println("#Hiba: kor logika ki van kapcsolva!");
            return false;
        }
        if(event.equals("nr")){
            EndOfRound();
            return true;
        }
        else if(event.equals("np")){
            if (!players.isEmpty()) {
                if(playerIndex < players.size())
                    playerIndex++;
                else
                    playerIndex = 0;
                currentPlayer = players.toArray()[playerIndex];
                return true;
            }
            else {
                System.out.println("#Nincsenek jatekosok (gombafajok és vagy rovar koloniak)!");
                return false;
            }
        }
        else{
            System.err.println("#Error: '" + event + "' is not a valid command in method: trigg");
            return false;
        }
    }

    /**
     * A random paraméterek ki- és bekapcsolását vezérli. Alapertelmezetten be van kapcsolva.
     * @param cmd --String: "enable", ha beállítjuk, "disable", ha kikapcsoljuk, ha üres akkor ellentettjére állítja.
     */
    public boolean Rand(String cmd){
        if(cmd.isEmpty() || cmd.equals("")) {
            isRandom = !isRandom;
            return true;
        }
        else if (cmd.equals("enable") || cmd.equals("e")) {
            isRandom = true;
            return true;
        }
        else if (cmd.equals("disable") || cmd.equals("d")) {
            isRandom = false;
            return true;
        }
        else{
            System.err.println("Error: '" + cmd + "' is not a valid command in method: rand");
            return false;
        }
    }

    /**
     * A koroket ki- és bekapcsolo parancs. Alapértelmezetten vannak korok.
     * @param cmd -- String: "enable" bekapccsolja, "disable" kikapcsolja a koroket. Ha ures ellentetjere allitja.
     */
    public boolean Turns(String cmd){
        if(cmd.isEmpty() || cmd.equals("")){
            turns = !turns;
            return true;
        }
        else if(cmd.equals("enable") || cmd.equals("e")){
            turns = true;
            return true;
        }
        else if(cmd.equals("disable") || cmd.equals("d")){
            turns = false;
            return true;
        }
        else{
            System.err.println("Error: '" + cmd + "' is not a valid command in method: turns");
            return false;
        }
    }

    /**
     * Meghívja a paramtérként kapott tektonon a paraméterként kapott Gombával a GrowFungusBodyt.
     * Sikeres metodushivas utan belerakja az uj Gombatestet a planetbe.
     * Sikeres metodushivas utan kiveszi a felhasznalt sporakat a planetbol.
     *
     * @param tecton ITectonController példány, a tekton amin hívja a GrowFungusBody függvényt
     * @param fungus Fungus példány, a tekton GrowFungusBody függvényéhez kell
     * @return sikeres-e a művelet.
     */
    public boolean GrowFungusBody(ITectonController tecton, Fungus fungus){
        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        if (turns) {
            if(!currentPlayer.equals(fungus)){
                System.err.println("#Nem az o kore van!");
                return false;
            }
        }

        boolean successful = tecton.GrowFungusBody(fungus);
        if(!successful){
            System.out.println("#Gombatest novesztes sikertelen!");
            return false;
        }

        FungusBody fb = fungus.GetBodies().get( fungus.GetBodies().size() - 1);//getLast();
        view.IncfbCtr();
        String name = "FB"+ view.getfbCtr();
        planet.put(name,fb);

        ITectonView tect = (ITectonView)tecton;

        List<String> keysToRemove = new ArrayList<>();
        for (Map.Entry<String, Object> entry : planet.entrySet()) {
            if(entry.getKey().startsWith("S")){                                          // spore legyen
                ISporeView spore = (ISporeView) entry.getValue();
                if(spore.GetTecton().equals(tect) && !tect.GetSpores().contains(spore)){ // sporat mar toroltuk de a spora meg nem tudja
                    keysToRemove.add(entry.getKey());
                }
            }
        }
        for(String key : keysToRemove){
            planet.remove(key);
        }
        System.out.println("#Sikeres gombatest novesztes "+name+" neven!");
        return true;
    }

    /**
     * Meghívja a paramtérként kapott tektonon a paraméterként kapott Gombával a GrowFungusBodytFromInsectet.
     * Sikeres metodushivas utan belerakja az uj Gombatestet a planetbe.
     * Sikeres metodushivas utan kiveszi az eltavolitott Rovart a planetbol.
     *
     * @param tecton ITectonController példány, a tekton amin hívja a GrowFungusBody függvényt
     * @param fungus Fungus példány, a tekton GrowFungusBody függvényéhez kell
     * @return sikeres-e a művelet.
     */
    public boolean GrowFungusBodyFromInsect(ITectonController tecton, Fungus fungus){
        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        if (turns) {
            if(!currentPlayer.equals(fungus)){
                System.err.println("#Nem az o kore van!");
                return false;
            }
        }

        boolean successful = tecton.GrowFungusBodyFromInsect(fungus);

        if(!successful){
            System.out.println("#Gombatest novesztes sikertelen!");
            return false;
        }

        FungusBody fb = fungus.GetBodies().get( fungus.GetBodies().size() -1 );//getLast();
        view.IncfbCtr();
        String name = "FB"+ view.getfbCtr();
        planet.put(name,fb);

        ITectonView tect = (ITectonView)tecton;

        List<String> keysToRemove = new ArrayList<>();
        for (Map.Entry<String, Object> entry : planet.entrySet()) {
            if(entry.getKey().matches("I\\d+")){                                      // Insect legyen, I + valamilyen szám
                IInsectView insect = (IInsectView) entry.getValue();
                if(insect.GetTecton().equals(tect) && !tect.GetInsects().contains(insect)){ // tektonról már töröltük, de az insect ezt még nem tudja
                    keysToRemove.add(entry.getKey());
                }
            }
        }
        for(String key : keysToRemove){
            planet.remove(key);
        }
        System.out.println("#Sikeres gombatest novesztes "+name+" neven!");
        return true;
    }

    /**
     * A megadott tektonon meghivja az AbsorbHyphas metodust.
     * Sikeres metodushivas utan kiveszi a planetbol a felszivodott hifakat.
     *
     * @param tecton ITectonController peldany, aminek meghivja a AbsorbHyphas metodusat.
     * @return sikeres-e a muvelet.
     */
    public boolean AbsorbHypha(ITectonController tecton){
        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        boolean successful = false;
        if(!turns){
            for (int i = 0; i <3; i++) {
               successful = tecton.AbsorbHyphas();
            }
        }
        if(!successful){
            System.out.println("#Az absorb muvelet sikertelen!");
            return false;}

        CleanUpHyphas(planet);
        System.out.println("#Sikeres absorb muvelet.");
        return true;
    }

    /**
     * Kitorli a tektonrol mar kitorolt, de a planetben meg benne levo hifakat.
     *
     * @param planet View planet peldanya
     */
    private void CleanUpHyphas(LinkedHashMap<String, Object> planet) {
        List<String> keysToRemove = new ArrayList<>();
        for (Map.Entry<String, Object> entry : planet.entrySet()) {
            if(entry.getKey().startsWith("H")){                     // biztosan hifat talalunk

                IHyphaView hypha = (IHyphaView) entry.getValue();
                List<Tecton> tectons = hypha.GetTectons();
                if(tectons.size() == 1){                        // nem res hifa
                    if (!tectons.get(0)/*getFirst()*/.hyphas.contains(hypha) &&                // a tecton szerint nem taroljuk el
                            hypha.GetTectons().contains(tectons.get(0)/*getFirst()*/)) {        // de a hifa szerint igen
                        keysToRemove.add(entry.getKey());
                    }
                }
                else{                                          // res hifa
                    if (!tectons.get(tectons.size()-1)/*getLast()*/.hyphas.contains(hypha) &&                // a tecton szerint nem taroljuk el
                            hypha.GetTectons().contains(tectons.get(tectons.size()-1)/*getLast()*/)) {        // de a hifa szerint igen
                        keysToRemove.add(entry.getKey());
                    }
                }
            }
        }

        for(String key : keysToRemove){
            planet.remove(key);
        }
    }

    /**
     * Kitorli a tektonrol mar kitorolt, de a planetben meg benne levo sporakat.
     *
     * @param planet View planet peldanya
     */
    private void CleanUpSpores(LinkedHashMap<String, Object> planet) {
        List<String> keysToRemove = new ArrayList<>();
        for (Map.Entry<String, Object> entry : planet.entrySet()) {
            if(entry.getKey().startsWith("S")){                     // biztosan sporat talalunk

                ISporeView spore = (ISporeView) entry.getValue();
                Tecton tecton = spore.GetTecton();

                    if( !tecton.spores.contains(spore) &&                // a tecton szerint nem taroljuk el
                            spore.GetTecton().equals(tecton)){           // de a spora szerint igen
                        keysToRemove.add(entry.getKey());
                    }
            }
        }

        for(String key : keysToRemove){
            planet.remove(key);
        }
    }
    /**
     * Kitorli a tektonrol mar kitorolt, de a planetben meg benne levo gombatesteket.
     *
     * @param planet View planet peldanya
     */
    private void CleanUpFungusBodies(LinkedHashMap<String, Object> planet){
        List<String> keysToRemove = new ArrayList<>();
        for (Map.Entry<String, Object> entry : planet.entrySet()) {
            if(entry.getKey().startsWith("FB")){                     // biztosan gombatestet talalunk

                IFungusBodyView fb = (IFungusBodyView) entry.getValue();
                Tecton tecton = fb.GetTecton();

                if( tecton.GetFungusBody() == null &&                // a tecton szerint nem taroljuk el
                        fb.GetTecton().equals(tecton)){           // de a spora szerint igen
                    keysToRemove.add(entry.getKey());
                }
            }
        }

        for(String key : keysToRemove){
            planet.remove(key);
        }
    }

    /**
     * A megadott Gombatesten elinditja a sporatermelest.
     *
     * @param fb IFungusBodyController peldany, amin meghivja a ProduceSpore metodust.
     * @return sikeres-e a muvelet.
     */
    public boolean ProduceSpore(IFungusBodyController fb){
        if (turns) {
            IFungusBodyView fbView = (IFungusBodyView) fb;
            if( !currentPlayer.equals( fbView.GetHostFungus() ) ){
                System.err.println("#Nem az o kore van!");
                return false;
            }
        }

        boolean success = fb.ProduceSpore();
        if(!success){
            System.err.println("#Nem tudott sporat termelni!");
            return false;
        }
        System.out.println("#Sikeres spora termeles.");
        return true;
    }

    /**
     * A megadott Gombatest ShootSpores fuggvenyet meghivja.
     * Sikeres metodushivas utan letrehozott sporakat belerakja a planetbe.
     *
     * @param fb  IFungusBodyController peldany, aminek meghivja a ShootSpores(boolean,Random) fuggvenyet
     * @return sikeres-e a művelet
     */
    public boolean ShootSpores(IFungusBodyController fb){
        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        if (turns) {
            IFungusBodyView fbView = (IFungusBodyView) fb;
            if( !currentPlayer.equals( fbView.GetHostFungus() ) ){
                System.err.println("#Nem az o kore van!");
                return false;
            }
        }

        boolean success = fb.ShootSpores(isRandom, rand);
        if(!success){
            System.err.println("#Nem tudott sporat loni!");
            return false;
        }
        AddCreatedSpores(planet);
        System.out.println("#Sikeres spora loves.");
        return true;
    }

    private void AddCreatedSpores(LinkedHashMap<String, Object> planet) {
        List<Spore> sporesToAdd = new ArrayList<>();

        // végigmegyünk a tektonokon
        for(Map.Entry<String, Object> entry : planet.entrySet()){
            if(entry.getKey().startsWith("T")){                     // biztos tekton lesz
                ITectonView tect = (ITectonView)entry.getValue();
                List<Spore> spores = tect.GetSpores();

                // minden spórájára megnézzük, hogy már benne van-e a planetben
                for(Spore s : spores){
                    boolean inPlanet = planet.values().contains(s);
                    if(!inPlanet){
                        sporesToAdd.add(s);
                    }
                }
            }
        }

        // belerakjuk a spórákat amik nem szerepelnek a planetben, de a tektonokon rajta vannak
        for(Spore s : sporesToAdd){
            view.IncsCtr();
            String name = "S"+view.getsCtr();
            planet.put(name,s);
        }
    }

    /**
     * A megadott Gombatest Diue fuggvenyet meghivja.
     * Sikeres metodushivas utan az elhalt hifakat kitorli a planetbol.
     * @param fb IFungusBodyController peldany, aminek meghivja a ShootSpores(boolean,Random) fuggvenyet
     * @return
     */
    public boolean DieFungusBody(IFungusBodyController fb){
        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        if (turns) {
            IFungusBodyView fbView = (IFungusBodyView) fb;
            if( !currentPlayer.equals( fbView.GetHostFungus() ) ){
                System.err.println("#Nem az o kore van!");
                return false;
            }
        }

        boolean success = fb.Die();
        if(!success){
            System.err.println("#Nem tudott a gombatest meghalni!");
            return false;
        }

        CleanUpHyphas(planet);
        System.out.println("#Sikeresen meghalt a gombatest.");
        return true;
    }

    /**
     * Meghivja a tectonFrom-on az AddHypha metodust.
     * Ha sikeres akkor belerakja az uj hifakat a planetbe.
     *
     * @param fungus    Gomba amihez tartozik a hifa
     * @param tectonFrom    Tekton amirol no a hifa
     * @param tectonTo  Tekton amire no a hifa, lehet null is
     * @return Sikeres-e a muvelet.
     */
    public boolean GrowHypha(Fungus fungus, Tecton tectonFrom, Tecton tectonTo){
        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        if (turns) {
            if( !currentPlayer.equals( fungus ) ){
                System.err.println("#Nem az o kore van!");
                return false;
            }
        }

        //boolean  success = tectonFrom.AddHypha(fungus,tectonTo);
        boolean  success = tectonTo.AddHypha(fungus,tectonFrom);
        if(!success){
            System.err.println("#Nem tudott a hifa atnoni!");
            return false;
        }

        List<Fungus> fungi = new ArrayList<Fungus>();
        // megkeressuk a gombakat
        for(String key : planet.keySet()){
            if(key.matches("F\\d+")){           // Gombat talalunk
                fungi.add( (Fungus)planet.get(key) );
            }
        }
        for(Fungus f : fungi){
            List<Hypha> hyphas = f.GetMycelium();
            for(Hypha h : hyphas){
                if( !planet.containsValue(h) ){         // a hifa benne van a Gombaban, de nincs benne a planetben
                    view.InchCtr();
                    String name = "H"+view.gethCtr();
                    planet.put(name,h);
                }
            }
        }
        System.out.println("#Sikeres gombafonal novesztes.");
        return true;
    }

    /**
     * Meghivja az adott hifan az Atrophy metodust.
     * Ha sikeres akkor kitorli a planetbol is az elsorvadt hifakat.
     *
     * @param hypha - IHyphaController peldany, amin meghijuk az Atrophy metodust
     * @return Sikeres-e a muvelet.
     */
    public boolean AtrophyOfHypha(IHyphaController hypha){
        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        if (turns) {
            IHyphaView hyphaView = (IHyphaView) hypha;
            if( !currentPlayer.equals( hyphaView.GetHostFungus() ) ){
                System.err.println("#Nem az o kore van!");
                return false;
            }
        }

        boolean success = hypha.Atrophy();
        if(!success){
            System.err.println("#Nem tudott a gombafonal (halozat) elsorvadni!");
            return false;
        }
        CleanUpHyphas(planet);
        System.err.println("#Sikeres gombafonal (halozat) elsorvadas!");
        return true;
    }

    /**
     * Meghivja a megadott hifa EatStunnedInsect metodusat.
     *
     * @param hypha - IHyphaController peldany, amire meghivjuk az EatStunnedInsect metodust
     * @param insect - Rovar amit megeszik a hifa
     * @return Sikeres-e a muvelet
     */
    public boolean EatStunnedInsect(IHyphaController hypha, Insect insect){
        if (turns) {
            IHyphaView hyphaView = (IHyphaView) hypha;
            if( !currentPlayer.equals( hyphaView.GetHostFungus() ) ){
                System.err.println("Nem az o kore van");
                return false;
            }
        }

        boolean success = hypha.EatStunnedInsect(insect);
        if(!success){
            System.err.println("Nem tudta megenni a rovart");
            return false;
        }
        return true;

    }

    /**
     * Meghivja a megadott rovar EatSpore metodusat.
     * Ha sikeres akkor kitorli a megevett sporakat a planetbol.
     * @param insect - IInsectController peldany, ami meghivja az EatSpore metodust.
     * @param spore - Spora amit megeszik a rovar.
     * @return Sikeres-e a muvelet.
     */
    public boolean EatSpore(IInsectController insect, Spore spore){
        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        IInsectView insectView = (IInsectView) insect;
        if (turns) {
            if( !currentPlayer.equals( insectView.GetHostColony() ) ){
                System.err.println("#Nem az o kore van!");
                return false;
            }
        }

        int insectCountBefore = insectView.GetTecton().insects.size();
        boolean success = insect.EatSpore(spore);
        if(!success){
            System.err.println("#Nem tudta a rovar megenni a sporat!");
            return false;
        }
        int insectCountAfter = insectView.GetTecton().insects.size();
        if(insectCountBefore != insectCountAfter){
            view.InciCtr();
            String name = "I"+view.getiCtr();
            Insect nInsect = insectView.GetTecton().GetInsects().get( insectCountAfter - 1);
            planet.put(name, nInsect );
            InsectMovesLeft.put(nInsect, nInsect.GetSpeed());
        }
        CleanUpSpores(planet);
        System.out.println("#Sikeres spora eves.");
        return true;

    }

    /**
     * Meghivja a megadott rovar Move metodusat.
     *
     * @param insect - IInsectController peldany, ami meghiva a Move metodust.
     * @param tecton - tekton amire lep a rovar.
     * @return Sikeres-e a muvelet
     */
    public boolean MoveInsect(IInsectController insect, Tecton tecton){
        if (turns) {
            IInsectView insectView = (IInsectView) insect;
            if( !currentPlayer.equals( insectView.GetHostColony() ) ){
                System.err.println("Nem az o kore van");
                return false;
            }
        }

        if(!InsectMovesLeft.containsKey(insect) || InsectMovesLeft.get(insect) <= 0){            // nincs ilyen rovar vagy elfogyott a lepese
            System.err.println("#Nem tudott a rovar atlepni a tektonra!");
            return false;
        }

        boolean success = insect.Move(tecton);
        if(!success){
            System.err.println("#Nem tudott a rovar atlepni a tektonra!");
            return false;
        }
        int moves = InsectMovesLeft.get(insect);
        InsectMovesLeft.put( (Insect)insect,moves-1);
        System.out.println("#Sikeres rovar lepes masik tektonra!");
        return true;
    }

    /**
     *  Meghivja a megadott rovar Cut metodusat.
     *  Ha sikeres eltavolitja a megsemmisult hifakat a planetbol.
     * @param insect - IInsectController peldany, ami meghivja a Cut metodust.
     * @param tecton - Tekton amire meghivjak a Cut metodust.
     * @return
     */
    public boolean CutHypha(IInsectController insect, Hypha hypha){
        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        if (turns) {
            IInsectView insectView = (IInsectView) insect;
            if( !currentPlayer.equals( insectView.GetHostColony() ) ){
                System.err.println("#Nem az o kore van!");
                return false;
            }
        }

        boolean success = insect.Cut(hypha);
        if(!success){
            System.err.println("#Nem tudta a rovar elvagni a hifat!");
            return false;
        }
        CleanUpHyphas(planet);
        System.out.println("#Sikeres gombafonal elvagas.");
        return true;
    }

    public void SetToDefault(){
        players = new HashSet<>();
        currentPlayer = null;
        playerIndex = 0;
        isRandom = true;
        turns = true;
        roundsLeft = 5;
        gameOver = false;
    }
    public void SetRoundsLeft(int rounds){
        if(rounds <= 0)
            System.err.println("Nem lehet 0 vagy kevesebb kör!");
        else
            rounds = rounds;
    }
    public int GetRoundsLeft(){
        return roundsLeft;
    }
    public boolean IsGameOver(){
        return gameOver;
    }
    public Object GetCurrentPlayer(){
        return currentPlayer;
    }
}
