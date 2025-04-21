package fungorium;

import java.io.StringBufferInputStream;
import java.util.*;

public class GameController {
    private Random rand = new Random();
    private IView view;

    enum Turn { Fungus1,Fungus2,Colony1,Colony2 };
    Turn currentTurn = Turn.Fungus1;  // default

    private boolean isRandom = true;
    private boolean turns = true;

    public GameController(IView view){
        this.view = view;
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
            }
        }


        // tektonokon vegigmegy
        for(String tKey : tKeys){
            ITectonController tc = (ITectonController) planet.get(tKey);
            tc.AbsorbHyphas();                                              // felszivja a hifakat

            if(tc != null && isRandom){                                     // ha be van kapcsolva a random
                int chance = rand.nextInt(100);
                if(chance < 5){                                             // dobunk a kockaval, 5% esellyel torik el a tecton
                    tc.Break();
                }
            }
        }

        CleanUpHyphas(planet);
        CleanUpSpores(planet);
    }

    /**
     * A parameterben atadott utasitasnak megfeleloen lepteti a jatek koret.
     *
     * @param event - String, ha = "nr", kovetkezo kor, jatekost nem valt
     *              -         ha = "np", kovetkezo jatekos. Utolso jatekos utan uj kor jon es visszakerul az elso jatekos.
     */
    public void Trigg(String event){
        if(!turns)
            return;

        // kovetkezo kor
        if(event  == "nr"){
            EndOfRound();
        }   // kovetkezo jatekos
        else if(event  == "np"){

            switch (currentTurn){
            case Fungus1:
                currentTurn = Turn.Fungus2;
                break;
            case Fungus2:
                currentTurn = Turn.Colony1;
                break;
            case Colony1:
                currentTurn = Turn.Colony2;
                break;
            case Colony2:
                EndOfRound();
                currentTurn = Turn.Fungus1;
                break;
            default:
                currentTurn = Turn.Fungus1;
                break;
            }
        }
    }
    /**
     * A random paraméterek ki- és bekapcsolását vezérli. Alapertelmezetten be van kapcsolva.
     * @param cmd --String: "enable", ha beállítjuk, "disable", ha kikapcsoljuk, ha üres akkor ellentettjére állítja.
     */
    public void Rand(String cmd){
        if(cmd.length() == 0) {
            isRandom = isRandom ? false : true;
        }
        else if (cmd == "enable") {
            isRandom = true;
        }
        else if (cmd == "disable") {
            isRandom = false;
        }
        else{
            System.err.println("Error: '" + cmd + "' is not a valid command in method: rand");
        }
    }

    /**
     * A koroket ki- és bekapcsolo parancs. Alapértelmezetten vannak korok.
     * @param cmd -- String: "enable" bekapccsolja, "disable" kikapcsolja a koroket. Ha ures ellentetjere allitja.
     */
    public void Turns(String cmd){
        if(cmd.length() == 0){
            turns = !turns;
        }
        else if(cmd == "enable"){
            turns = true;
        }
        else if(cmd == "disable"){
            turns = false;
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

        if (turns) {
            if(currentTurn == Turn.Colony1 || currentTurn != Turn.Colony2){
                System.err.println("Insect colony kor van");
                return false;
            }
        }

        boolean successful = tecton.GrowFungusBody(fungus);
        if(!successful)
            return false;

        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        FungusBody fb = fungus.GetBodies().getLast();
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
        if (turns) {
            if(currentTurn == Turn.Colony1 || currentTurn != Turn.Colony2){
                System.err.println("Insect colony kor van");
                return false;
            }
        }

        boolean successful = tecton.GrowFungusBodyFromInsect(fungus);
        if(!successful)
            return false;

        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        FungusBody fb = fungus.GetBodies().getLast();
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
        if (turns) {
            if(currentTurn == Turn.Colony1 || currentTurn != Turn.Colony2){
                System.err.println("Insect colony kor van");
                return false;
            }
        }

        boolean successful = tecton.AbsorbHyphas();
        if(!successful)
            return false;

        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        CleanUpHyphas(planet);
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

                for(Tecton t : tectons){
                   if( !t.hyphas.contains(hypha) &&                // a tecton szerint nem taroljuk el
                           hypha.GetTectons().contains(t)){        // de a hifa szerint igen
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
     * A megadott Gombatesten elinditja a sporatermelest.
     *
     * @param fb IFungusBodyController peldany, amin meghivja a ProduceSpore metodust.
     * @return sikeres-e a muvelet.
     */
    public boolean ProduceSpore(IFungusBodyController fb){
        if (turns) {
            if(currentTurn == Turn.Colony1 || currentTurn != Turn.Colony2){
                System.err.println("Insect colony kor van");
                return false;
            }
        }

        boolean success = fb.ProduceSpore();
        if(!success){
            System.err.println("Nem tudott sporat termelni");
            return false;
        }
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
        if (turns) {
            if(currentTurn == Turn.Colony1 || currentTurn != Turn.Colony2){
                System.err.println("Insect colony kor van");
                return false;
            }
        }

        boolean success = fb.ShootSpores(isRandom, rand);
        if(!success){
            System.err.println("Nem tudott sporat loni");
            return false;
        }
        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        AddCreatedSpores(planet);
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
        if (turns) {
            if(currentTurn == Turn.Colony1 || currentTurn != Turn.Colony2){
                System.err.println("Insect Colony kor van");
                return false;
            }
        }

        boolean success = fb.Die();
        if(!success){
            System.err.println("Nem tudott a gombatest meghalni");
            return false;
        }

        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        CleanUpHyphas(planet);
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
    public boolean GrowHypha(Fungus fungus,Tecton tectonFrom, Tecton tectonTo){
        if (turns) {
            if(currentTurn == Turn.Colony1 || currentTurn != Turn.Colony2){
                System.err.println("Insect colony kor van");
                return false;
            }
        }

        boolean  success = tectonFrom.AddHypha(fungus,tectonTo);
        if(!success){
            System.err.println("Nem tudott a hifa atnoni");
            return false;
        }

        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
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
                    view.IncfCtr();
                    String name = "F"+view.getsCtr();
                    planet.put(name,h);
                }
            }
        }
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
        if (turns) {
            if(currentTurn == Turn.Colony1 || currentTurn != Turn.Colony2){
                System.err.println("Insect colony kor van");
                return false;
            }
        }

        boolean success = hypha.Atrophy();
        if(!success){
            System.err.println("Nem tudott elsorvadni");
        }
        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        CleanUpHyphas(planet);
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
            if(currentTurn == Turn.Fungus1 || currentTurn != Turn.Fungus2){
                System.err.println("Fungus kor van");
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
        if (turns) {
            if(currentTurn == Turn.Fungus1 || currentTurn != Turn.Fungus2){
                System.err.println("Fungus kor van");
                return false;
            }
        }

        boolean success = insect.EatSpore(spore);
        if(!success){
            System.err.println("Nem tudta a rovar megenni a sporat");
            return false;
        }
        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        CleanUpSpores(planet);
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
            if(currentTurn == Turn.Fungus1 || currentTurn != Turn.Fungus2){
                System.err.println("Fungus kor van");
                return false;
            }
        }

        boolean success = insect.Move(tecton);
        if(!success){
            System.err.println("Nem tudott a rovar atlepni a tektonra");
            return false;
        }
        return true;
    }

    /**
     *  Meghivja a megadott rovar Cut metodusat.
     *  Ha sikeres eltavolitja a megsemmisult hifakat a planetbol.
     * @param insect - IInsectController peldany, ami meghivja a Cut metodust.
     * @param tecton - Tekton amire meghivjak a Cut metodust.
     * @return
     */
    public boolean CutHypha(IInsectController insect, Tecton tecton){
        if (turns) {
            if(currentTurn == Turn.Fungus1 || currentTurn != Turn.Fungus2){
                System.err.println("Fungus kor van");
                return false;
            }
        }

        boolean success = insect.Cut(tecton);
        if(!success){
            System.err.println("Nem tudta a rovar elvagni a hifat.");
            return false;
        }
        LinkedHashMap<String,Object> planet = (LinkedHashMap<String,Object>) view.getPlanet();
        CleanUpHyphas(planet);
        return true;
    }



}
