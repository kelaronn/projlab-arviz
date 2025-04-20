package fungorium;

public class BarrenTecton extends NarrowTecton{
    protected int absorptionTime = 3;

    public BarrenTecton() {
        super();
    }
    /**
     * Létrehoz egy új Barren tektont, amibe átmásolja az eredeti tekton szomszédjait, minden más default.
     * @param t másolni kívánt Barren tekton
     */
    public BarrenTecton(BarrenTecton t) {
        super(t);
        absorptionTime = t.absorptionTime;
    }

    /**
     * Meghívja a saját másoló konstruktorját.
     * @return új Barren Tekton
     */
    @Override
    public Tecton CreateCopy() {
        return new BarrenTecton(this);
    }
    /**
     * Getter a tekton abszorpciós idejének lekérdezésére
     * @return a tekton abszorpciós ideje
     */
    public int GetAbsorbtionTime() {
        return absorptionTime;
    }
    /**
     * Setter a tekton abszorpciós idejének beállítására
     * @param absorbtionTime a tekton abszorpciós ideje
     */
    public void SetAbsorbtionTime(int absorbtionTime) {
        this.absorptionTime = absorbtionTime;
    }
    /**
     * A tekton abszorpcióját implementálásaa
     * @return true, mert történt felszívódás
     */
    @Override
    public boolean AbsorbHyphas(){
        for (int i=0; i<hyphas.size(); i++){
            System.out.println(">[BarrenTecton].RemoveHypha(h)");
            RemoveHypha(hyphas.get(i));
        }
        return true;
    }

    @Override
    public String ToString(String data){
        return "BarrenTecton,"+data;
    }
}
