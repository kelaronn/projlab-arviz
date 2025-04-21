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

    public DisarmSpore(int nv, int ed, Fungus fungus, Tecton tecton) {
        super(nv,ed,fungus,tecton);
    }

    /**
     * Hatás kifejtése (a rovar vágási képességének megszüntetése)
     * @param i rovar
     * @return sikeres-e a hatás kifejtése
     */
    @Override public boolean GiveEffect(Insect i){
        i.SetCutAbility(false);

        i.SetEffectTimeLeft(this.GetEffectDurr());

        return true;
    }

    @Override
    public String ToString(String data){
        return "DisarmSpore,"+data;
    }
}
