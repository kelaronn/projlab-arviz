package fungorium;

public class VitalTecton extends NarrowTecton {
    public VitalTecton() {
        super();
    }

    /**
     * Létrehoz egy új Vital tektont, amibe átmásolja az eredeti tekton
     * szomszédjait, minden más default.
     * 
     * @param t másolni kívánt Vital tekton
     */
    public VitalTecton(VitalTecton t) {
        super(t);
    }

    /**
     * Meghívja a saját másoló konstruktorját.
     * 
     * @return új Vital Tekton
     */
    @Override
    public Tecton CreateCopy() {
        return new VitalTecton(this);
    }

    @Override
    public boolean SupplyHyphas() {
        return true;
    }

    @Override
    public String ToString(String data) {
        return "VitalTecton," + data;
    }

    /*
     * A TectonVisitor osztály acceptálásához szükséges metódus.
     * 
     * @param visitor a látogató, aki végrehajtja a műveletet
     */
    @Override
    public void accept(TectonVisitor visitor) {
        visitor.visit(this);
    }
}
