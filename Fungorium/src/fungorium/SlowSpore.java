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
    @Override public boolean GiveEffect(Insect i){
        i.SetSpeed(0);
        System.out.println(">[Insect].SetSpeed(0)");
        i.SetEffectTimeLeft(this.GetEffectDurr());
        System.out.println(">[Insect].SetEffectTimeLeft(2)");
        return true;
    }
}