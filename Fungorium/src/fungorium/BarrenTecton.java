package fungorium;

public class BarrenTecton extends NarrowTecton{
    protected int absorbtionTime;

    public int GetAbsorbtionTime() {
        return absorbtionTime;
    }

    public void SetAbsorbtionTime(int absorbtionTime) {
        this.absorbtionTime = absorbtionTime;
    }
@Override
    public boolean AbsorbHyphas(){
        for(Hypha h : hyphas){
            RemoveHyphaFromTecton(h);
        }
        return true;
    }
}
