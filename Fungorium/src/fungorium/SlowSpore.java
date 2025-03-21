package fungorium;

public class SlowSpore extends Spore{
    /**
     * Konstruktor
     * @param nv tápérték
     * @param ed hatásidő
     * @param hf host gombatest
     */
    public SlowSpore(int nv, int ed, Fungus hf){
        super(nv, ed, hf);
    }

    /**
     * Hatás kifejtése (a rovar sebességének nullára csökkentése)
     * @param i rovar
     * @return sikeres-e a hatás kifejtése
     */
    @Override public boolean giveEffect(Insect i){
        i.setSpeed(0);
        i.setEffectTimeLeft(2);
        return true;
    }
}