package fungorium;

public class BarrenTecton extends NarrowTecton{
    protected int absorbtionTime;
    /**
     * Getter a tekton abszorpciós idejének lekérdezésére
     * @return a tekton abszorpciós ideje
     */
    public int GetAbsorbtionTime() {
        return absorbtionTime;
    }
    /**
     * Setter a tekton abszorpciós idejének beállítására
     * @param absorbtionTime a tekton abszorpciós ideje
     */
    public void SetAbsorbtionTime(int absorbtionTime) {
        this.absorbtionTime = absorbtionTime;
    }
    /**
     * A tekton abszorpcióját implementálásaa
     * @return true, mert történt felszívódás
     */
    @Override
    public boolean AbsorbHyphas(){
        for(Hypha h : hyphas){
            RemoveHyphaFromTecton(h);
        }
        return true;
    }
}
