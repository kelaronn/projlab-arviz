package fungorium;

public class WeakTecton extends NarrowTecton{
   public WeakTecton() {
       super();
   }
    /**
     * Létrehoz egy új Weak tektont, amibe átmásolja az eredeti tekton szomszédjait, minden más default.
     * @param t másolni kívánt Weak tekton
     */
    public WeakTecton(WeakTecton t) {
        super(t);
    }

    /**
     * Meghívja a saját másoló konstruktorját.
     * @return új Weak Tekton
     */
    @Override
    public Tecton CreateCopy() {
        return new WeakTecton(this);
    }


    /**
     * ősosztályban definiált fv. felüldefiniálása
     * @param fungus
     * @return false
     */
    @Override
    public boolean GrowFungusBody(Fungus fungus) {
        return false; // nem tud növeszteni
    }
    @Override
    public boolean GrowFungusBodyFromInsect(Fungus fungus){ return false;} // nem tud növeszteni

    @Override
    public String ToString(String data){
        return "WeakTecton,"+data;
    }
}
