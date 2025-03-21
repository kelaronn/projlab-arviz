package fungorium;

public class DisarmSpore extends Spore {
    /**
     * Konstruktor
     * @param nv tápérték
     * @param ed hatásidő
     * @param hf host gombatest
     */
    public DisarmSpore(int nv, int ed, Fungus hf){
        super(nv, ed, hf);
    }

    /**
     * Hatás kifejtése (a rovar vágási képességének megszüntetése)
     * @param i rovar
     * @return sikeres-e a hatás kifejtése
     */
    @Override public boolean giveEffect(Insect i){
        i.setCutAbility(false);
        i.setEffectTimeLeft(2);
        return true;
    }
}
