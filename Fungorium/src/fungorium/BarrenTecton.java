package fungorium;

public class BarrenTecton extends NarrowTecton{
    protected int absorbtionTime;

    public int getAbsorbtionTime() {
        return absorbtionTime;
    }

    public void setAbsorbtionTime(int absorbtionTime) {
        this.absorbtionTime = absorbtionTime;
    }
@Override
    public boolean AbsorbHyphas(){
        return true;
    }
}
