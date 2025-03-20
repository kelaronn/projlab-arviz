package fungorium;

public class SlowSpore extends Spore{

    public SlowSpore(int nv, int ed, Fungus hf){
        super(nv, ed, hf);
    }

    @Override public boolean giveEffect(Insect i){
        i.setSpeed(0);
        return true;
    }
}