package fungorium;

public class SplitSpore extends Spore {
    /**
     * Konstruktor
     * @param nv tápérték
     * @param ed hatásidő
     * @param hf host gombatest
     */
    public SplitSpore(int nv, int ed, Fungus hf){
        super(nv, ed, hf);
    }

    public SplitSpore(int nv, int ed, Fungus fungus, Tecton tecton) {
        super(nv,ed,fungus,tecton);
    }

    /**
     * Hatás kifejtése (a rovar "megkettőzése")
     * @param i rovar
     * @return sikeres-e a hatás kifejtése
     */
    @Override public boolean GiveEffect(Insect i){
        Insect newInsect = new Insect(i);

        i.GetHostColony().AddInsect(newInsect);
        i.GetTecton().AddInsect(newInsect);

        return true;
    }

    @Override
    public String ToString(String data){
        return "SplitSpore,"+data;
    }
}