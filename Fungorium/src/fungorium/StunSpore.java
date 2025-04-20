package fungorium;

public class StunSpore extends Spore {
    /**
     * Konstruktor
     * @param nv tápérték
     * @param ed hatásidő
     * @param hf host gombatest
     */
    public StunSpore(int nv, int ed, Fungus hf){
        super(nv, ed, hf);
    }

    /**
     * Hatás kifejtése (a rovar sebességének nullára csökkentése, vágási képességének megszüntetése)
     * @param i rovar
     * @return sikeres-e a hatás kifejtése
     */
    @Override public boolean GiveEffect(Insect i){
        i.SetSpeed(0);
        System.out.println(">[Insect].SetSpeed(0)");
        i.SetCutAbility(false);
        System.out.println(">[Insect].SetCutAbility(false)");
        i.SetEffectTimeLeft(this.GetEffectDurr());
        System.out.println(">[Insect].SetEffectTimeLeft(2)");
        return true;
    }

    @Override
    public String ToString(String data){
        return "StunSpore,"+data;
    }
}
