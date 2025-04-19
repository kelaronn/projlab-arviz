package fungorium;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Tecton implements ITectonController, ITectonView {
    protected Random rand = new Random();
    protected final int sporeCountToGrowFungus = 3;
    protected final int defaultFbShotsLeft = 4;
    protected List<Insect> insects;
    protected List<Hypha> hyphas;
    protected List<Spore> spores;
    protected ArrayList<Tecton> neighbours;
    protected FungusBody fungusBody;
    /**
     * Default konstruktor a tekton létrehozásához.
     */
    public Tecton() {
        insects = new ArrayList<>();
        hyphas = new ArrayList<>();
        spores = new ArrayList<>();
        neighbours = new ArrayList<>();
        fungusBody = null;
    }

    /**
     * Létrehoz egy új tektont, amibe átmásolja az eredeti tekton szomszédjait, minden más default.
     * @param t másolni kívánt tekton
     */
    public Tecton(Tecton t ) {
        insects = new ArrayList<>();
        hyphas = new ArrayList<>();
        spores = new ArrayList<>();
        neighbours = new ArrayList<>(t.neighbours);
        fungusBody = null;
    }
    /**
     * Konstruktor a tekton létrehozásához.
     * @param insects
     * @param hyphas
     * @param spores
     */
    public Tecton(List<Insect> insects, List<Hypha> hyphas, List<Spore> spores) {
        this.insects = insects;
        this.hyphas = hyphas;
        this.spores = spores;
        neighbours = new ArrayList<>();
        fungusBody = null;
    }
    /**
     * Konstruktor a tekton létrehozásához.
     * @param insects
     * @param hyphas
     * @param spores
     * @param neighbours
     */
    public Tecton(List<Insect> insects, List<Hypha> hyphas, List<Spore> spores, ArrayList<Tecton> neighbours) {
        this.insects = insects;
        this.hyphas = hyphas;
        this.spores = spores;
        this.neighbours = neighbours;
        fungusBody = null;
    }

    /**
     * Egy absztrakt függvény ami létrehoz és visszaad egy saját magával megegyező típusú tektont.
     * @return egy saját magával megyezeő típusú tekton
     */
    public abstract Tecton CreateCopy();

    /**
     * Véletlenszerűen létrehoz egy Tecton fajtát és visszatér vele.
     * @return véletlenszerűen generált leszármazott
     */
//    protected Tecton GetRandomChild(){
//        int r = rand.nextInt(0,3);
//        switch(r){
//            case 1: return new WideTecton();
//            case 2: return new WeakTecton();
//            case 3: return new BarrenTecton();
//            default: return new NarrowTecton();
//        }
//    }

    /**
     * Segédfüggvény a hifák törlésére.
     * A paraméterben kapott hifát és a szomszédait kitörli a fungusból.
     * Majd a szomszédos tektonokon is megkeresi és törli ezeket.
     * @param h
     */
    protected void RemoveHyphaFromTecton(Hypha h) {
        h.GetHostFungus().RemoveHypha(h);
        System.out.println(">[Fungus].RemoveHypha(h)");
        //var hNeighbours = (ArrayList<Hypha>) h.GetNeighbours();
        List<Hypha> hNeighbours = h.GetNeighbours();
        // A paraméter hifa szomszédjait is töröljük a fungusból, mert azok mind résen vannak és így már nincs mihez kötődniük.
        for(Hypha n : hNeighbours){
            n.GetHostFungus().RemoveHypha(n);
            System.out.println(">[Fungus].RemoveHypha(n)");
        }
        // nem csak a fungusból kell törölni, hanem a szomszédos tectonokon is meg kell keresni és törölni.
        for( Tecton n : neighbours){
            RemoveHypha(this,n);
            System.out.println(">[Tecton].RemoveHypha(this,n)");
        }
        // majd végül a paraméterben kapott hifát is törölni kell
        hyphas.remove(h);
    }

    /**
     * A tekton kettétörik, törli a rajta lévő gombafonalat/kat (hyphas), spórákat
     * (spores) és gombatestet (fungusBody).
     * Létrehoz egy új, eredetivel megegyező típusú tektont. Az insecteket (ha vannak) maradnak az eredeti tektonon.
     */
    @Override
    public Tecton Break(){

//        Tecton t1 = GetRandomChild();
//        System.out.println(">[Tecton].GetRandomChild()");
//        Tecton t2 = GetRandomChild();
//        System.out.println(">[Tecton].GetRandomChild()");

//        if( !insects.isEmpty() ){
//            for(Insect i : insects){
//                t2.AddInsect(i);
//                System.out.println(">[Tecton].AddInsect(i)");
//                i.SetTecton(t2);
//                System.out.println(">[Insect].SetTecton(t2)");
//            }
//        }

//        for(Tecton n : neighbours){
//            t1.AddNeighbour(n);
//            System.out.println(">[Tecton].AddNeighbour(n)");
//            t2.AddNeighbour(n);
//            System.out.println(">[Tecton].AddNeighbour(n)");
//        }
        for (int i=0; i < hyphas.size(); i++){
            RemoveHyphaFromTecton(hyphas.get(i));
            System.out.println(">[Tecton].RemoveHyphaFromTecton(h)");
        }
        if(fungusBody != null){
            fungusBody.GetHostFungus().RemoveBody(fungusBody);
            System.out.println(">[Fungus].RemoveBody(fungusBody)");
        }
        SetFungusBody(null);
        System.out.println(">[Tecton].SetFungusBody(null)");

        for(int i = 0; i < spores.size(); i++){
            spores.remove(spores.get(spores.size()-1));
            System.out.println(">[Tecton].spores.remove(spores.get(spores.size()-1))");
        }

        Tecton nt = this.CreateCopy();

        for(Tecton n : this.neighbours ){
            n.AddNeighbour(nt);
        }
        this.AddNeighbour(nt);
        nt.AddNeighbour(this);

        return nt;
    }
    /**
     * egy gombatestet növeszt egy halott insectből az adott tektonon, és a
     * SetFungusBody setter függvénnyel beállítja a rajta lévő gombatestet. Igaz értékkel tér
     * vissza.
     * Override a leszármazottakban!
     * @param fungus
     * @return
     */
    @Override
    public boolean GrowFungusBodyFromInsect(Fungus fungus) {
        if( this.GetFungusBody() != null ) // már van fungisbody rajta
            return false;
        if(this.insects.isEmpty() ) // nincs rajta insect
            return false;

        Insect insectToRemove = null;
        for(Insect i : insects){
            if(i.GetEatenBy().equals(fungus)) {
                insectToRemove = i;
                break;
            }
        }
        if(insectToRemove == null)
            return false;

        insectToRemove.GetHostColony().RemoveInsect(insectToRemove);
        RemoveInsect(insectToRemove);

        fungusBody = new FungusBody(this,fungus,false,0,false,0,defaultFbShotsLeft);
        System.out.println(">[FungusBody].FungusBody(this,fungus,false,0,false,0,defaultFbShotsLeft)");
        fungus.AddBody(fungusBody);
        System.out.println(">[Fungus].AddBody(fungusBody)");
        this.SetFungusBody(fungusBody);
        System.out.println(">[Tecton].SetFungusBody(fungusBody)");
        return true;

    }
    /**
     * egy gombatestet növeszt az adott tektonon, és a
     * SetFungusBody setter függvénnyel beállítja a rajta lévő gombatestet. Igaz értékkel tér
     * vissza.
     * Override a leszármazottakban!
     * @param fungus
     * @return
     */
    @Override
    public boolean GrowFungusBody(Fungus fungus) {
        if(spores.size() < sporeCountToGrowFungus) // nem elég spóra
            return false;
        if( this.GetFungusBody() != null ) // már van fungisbody rajta
            return false;

        fungusBody = new FungusBody(this,fungus,false,0,false,0,defaultFbShotsLeft);
        System.out.println(">[FungusBody].FungusBody(this,fungus,false,0,false,0,defaultFbShotsLeft)");
        fungus.AddBody(fungusBody);
        System.out.println(">[Fungus].AddBody(fungusBody)");
        this.SetFungusBody(fungusBody);
        System.out.println(">[Tecton].SetFungusBody(fungusBody)");

        for(int i = 0; i < spores.size(); i++){
            spores.remove(spores.get(spores.size()-1));
        }
        return true;

    }

    /**
     * Abszorpciós függvény (hifa felszívódás), hamis értékkel tér vissza alapesetben.
     * @return false
     */
    @Override
    public boolean AbsorbHyphas(){
        return false;
    }

    /**
     * visszatér a neighbours nevű Tecton lista attribútum
     * referenciájával.
     * @return Tecton.neighbours
     */
    @Override
    public ArrayList<Tecton> GetNeighbours() {
        return neighbours;
    }

    /**
     * létrehoz egy véletlenszerű spórát a paraméterként kapott gombához
     * rendelve, és hozzáadja a saját spores nevű Spore lista attribútumhoz.
     * @param fungus
     */
    @Override
    public void AddSpore(Fungus fungus) {
        Spore spore;
        int type = rand.nextInt(0, 5);

        switch (type) { // 0 is normal spore/default
        case 1:
            spore = new StunSpore(3, 1, fungus);
            break;
        case 2:
            spore = new SpeedSpore(2, 2, fungus);
            break;
        case 3:
            spore = new SlowSpore(2, 2, fungus);
            break;
        case 4:
            spore = new DisarmSpore(3, 1, fungus);
            break;
        default:
            spore = new Spore(1, 0, fungus);
            break;
        }
        spores.add(spore);
        System.out.println(">[Tecton].spores.add(spore)");
    }

    /**
     * eltávolítja a paraméterként kapott spórát a spores nevű
     * Spore lista attribútumból.
     * @param spore spóra
     */
    public void RemoveSpore(Spore spore) {
        if( !spores.remove(spore) ){
            System.out.println("Spore not found");
            return;
        }
        System.out.println(">[Tecton].spores.remove(spore)");
    }

    /**
     * Visszaadja Spore listáját a tektonnak.
     * @return spores
     */
    @Override
    public List<Spore> GetSpores() {
        return spores;
    }

    /**
     * eltávolítja a paraméterként kapott hifát a hyphas nevű
     * Hypha lista attribútumból és a Fungusból is meghívva a RemoveHyphaFromTectont().
     * Ha rés hifa, csak ezt törli, ha tektonon van, a szomszédait is.
     * @param h eltávolítandó hifa
     */
    public void RemoveHypha(Hypha h) {
        if(h != null && hyphas.contains(h)) {
            if(h.GetTectons().size() == 1) {
                System.out.println(">[Fungus].RemoveHypha(h)");
                RemoveHyphaFromTecton(h);
            }
            else{
                hyphas.remove(h);
                System.out.println(">[Tecton].RemoveHypha(h)");
            }
        }
        else
            System.out.println("Hypha not found");
    }

    /**
     * hozzáadja a paraméterként kapott rovar (Insect) referenciáját
     * az insects nevű Insect lista attribútumhoz.
     * @param insect hozzáadandó insect
     */
    @Override
    public void AddInsect(Insect insect) {
        insects.add(insect);
    }

    /**
     * eltávolítja a paraméterként kapott rovar (Insect)
     * referenciáját az insects nevű Insect lista attribútumból.
     * @param insect eltávolítandó insect
     */
    public void RemoveInsect(Insect insect) {
        if( !insects.remove(insect) ){
            System.out.println("insect not found");
            return;
        }
        System.out.println(">[Tecton].insects.remove(insect)");
    }

    /**
     * setter függvény, beállítja a fungusBody
     * attribútum értékét.
     * @param fb fungusbody paraméter
     */
    @Override
    public void SetFungusBody(FungusBody fb){
        fungusBody = fb;
    }

    /**
     * getter függvény, visszatér a fungusBody attribútum
     * referenciájával.
     * @return tecton.fungusBody
     */
    @Override
    public FungusBody GetFungusBody(){
        return fungusBody;
    }

    /**
     * hozzáadja a paraméterül kapott Tecton referenciáját a
     * neighbours nevű Tecton lista attribúmhoz.
     * @param neighbour hozzzáadnivaló szomszédos tekton
     */
    @Override
    public void AddNeighbour(Tecton neighbour) {
        neighbours.add(neighbour);
        //System.out.println(">[Tecton].neighbours.add(neighbour)");
    }

    /**
     * eltávolítja a paraméterül kapott Tecton
     * referenciáját a neighbours nevű Tecton lista attribútumból.
     * @param neighbour eltávolítandó szomszédos tekton
     */
    public void RemoveNeighbour(Tecton neighbour) {
        neighbours.remove(neighbour);
        System.out.println(">[Tecton].neighbours.remove(neighbour)");
    }

    /**
     *törli a hyphas nevű listában található hifát (Hypha) és törli a Fungusból is,
     * ami ez a tekton és a paraméterben kapott szomszédos tekton között helyezkedik el.
     * @param t1 egyik Tekton
     * @param t2 másik Tekton
     */
    public void RemoveHypha(Tecton t1, Tecton t2) {
        Hypha h = GetHypha(t1, t2);
        System.out.println(">[Tecton].RemoveHypha(t1,t2)");
        if(h == null)
            return;

        h.GetHostFungus().RemoveHypha(h);
        System.out.println(">[Fungus].RemoveHypha(h)");
        hyphas.remove(h);
        System.out.println(">[Tecton].hyphas.remove(h)");
    }
    
    /**
     * visszatér a hyphas nevű Hypha lista azon
     * hifájának referenciájával, amely hifa tectons nevű Tecton tömjében a két paraméterül
     * kapott Tecton referenciája található (tehát t1 és t2 tektonok közötti résen található hifa),
     * ha van ilyen, egyébként null-al.
     * @param t1 egyik Tecton
     * @param t2 másik Tecton
     * @return Hypha h, ha van, egyébként null
     */
    public Hypha GetHypha(Tecton t1, Tecton t2){

        for(Hypha h : t1.hyphas){
            List<Tecton> tectons = h.GetTectons();
            if( (tectons.contains(t1) && tectons.contains(t2))
                   /* || (tectons.get(0).equals(t2) && tectons.get(1).equals(t1))*/ ){
                return h;
            }
        }
        for(Hypha h : t2.hyphas){
            List<Tecton> tectons = h.GetTectons();
            System.out.println(">[Hypha].GetTectons()");
            if( (tectons.contains(t1) && tectons.contains(t2))
                /* || (tectons.get(0).equals(t2) && tectons.get(1).equals(t1))*/ ){
                return h;
            }
        }
        return null;
    }
    /**
     * Visszaadja a tektonon lévő hifák listáját.
     * @return hyphas
     */
    @Override
    public List<Hypha> GetHyphas(){
        return hyphas;
    }

    /**
     * Megadja, hogy az adott Tekton el tudja-e látni a hifákat, mint egy Gombatest.
     * @return
     */
    public boolean SupplyHyphas(){
        return false;
    }
}
