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

    public SlowSpore(int nv, int ed, Fungus fungus, Tecton tecton) {
        super(nv,ed,fungus,tecton);
    }

    /**
     * Hatás kifejtése (a rovar sebességének nullára csökkentése)
     * @param i rovar
     * @return sikeres-e a hatás kifejtése
     */
    @Override public boolean GiveEffect(Insect i){
        i.SetSpeed(1);

        i.SetEffectTimeLeft(this.GetEffectDurr());

        return true;
    }

    @Override
    public String ToString(String data){
        return "SlowSpore,"+data;
    }
}