package fungorium;

public class StunSpore extends Spore {

    public StunSpore(int nv, int ed, Fungus hf){
        super(nv, ed, hf);
    }

    @Override public boolean giveEffect(Insect i){
        i.setSpeed(-1);
        return true;
    }
}
