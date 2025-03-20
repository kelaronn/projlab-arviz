package fungorium;

public class SpeedSpore extends Spore {

    public SpeedSpore(int nv, int ed, Fungus hf){
        super(nv, ed, hf);
    }

    @Override public boolean giveEffect(Insect i){
        i.setSpeed(2);
        return true;
    }
}
