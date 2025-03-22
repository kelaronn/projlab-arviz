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

    /**
     * Hatás kifejtése (a rovar sebességének növelése)
     * @param i rovar
     * @return sikeres-e a hatás kifejtése
     */
    @Override public boolean GiveEffect(Insect i){
        i.SetSpeed(2);
        System.out.println(">[Insect].SetSpeed(2)");
        i.SetEffectTimeLeft(2);
        System.out.println(">[Insect].SetEffectTimeLeft(2)");
        return true;
    }
}
