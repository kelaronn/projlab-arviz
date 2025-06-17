package fungorium;

public class SpeedSpore extends Spore {
    /**
     * Konstruktor
     * @param nv tápérték
     * @param ed hatásidő
     * @param hf host gombatest
     */
    public SpeedSpore(int nv, int ed, Fungus hf){
        super(nv, ed, hf);
    }

    public SpeedSpore(int nv, int ed, Fungus fungus, Tecton tecton) {
        super(nv,ed,fungus,tecton);
    }

    /**
     * Hatás kifejtése (a rovar sebességének növelése)
     * @param i rovar
     * @return sikeres-e a hatás kifejtése
     */
    @Override public boolean GiveEffect(Insect i){
        i.SetSpeed(3);

        i.SetEffectTimeLeft(this.GetEffectDurr());

        return true;
    }

    @Override
    public String ToString(String data){
        return "SpeedSpore,"+data;
    }
}
