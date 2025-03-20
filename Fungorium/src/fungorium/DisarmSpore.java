package fungorium;

public class DisarmSpore extends Spore {

    public DisarmSpore(int nv, int ed, Fungus hf){
        super(nv, ed, hf);
    }

    @Override public boolean giveEffect(Insect i){
        i.setCutAbility(false);
        return true;
    }
}
