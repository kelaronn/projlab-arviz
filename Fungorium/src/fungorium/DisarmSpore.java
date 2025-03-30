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
    @Override public boolean GiveEffect(Insect i){
        i.SetCutAbility(false);
        System.out.println(">[Insect].SetCutAbility(false)");
        i.SetEffectTimeLeft(this.GetEffectDurr());
        System.out.println(">[Insect].SetEffectTimeLeft(2)");
        return true;
    }
}
