package fungorium;

public class Insect implements IInsectController, IInsectView {
    private int speed = 2; // itt annyi hogy ez az hogy hányszor tud egy körben mozogni az lenne a legszebb
                           // a speedre és ha -1 akkor meg nem csak mozogni nem tudsz hanem enni sem meg
                           // vágni sem és akkor az lenne a stun spore
    private boolean cutAbility = true; // vágási képesség
    private int effectTimeLeft = 0; // hatásidő
    private InsectColony hostColony; // a rovarhoz tartozó kolónia
    private Tecton tecton; // a rovarhoz tartozó tekton
    private Fungus eatenBy = null;

    /**
     * Default konstruktor
     */
    public Insect() {
    }

    /**
     * Konstruktor
     * 
     * @param s   sebesség
     * @param ca  vágási képesség
     * @param etl hatásidő
     */
    public Insect(int s, boolean ca, int etl) {
        speed = s;
        cutAbility = ca;
        effectTimeLeft = etl;
    }

    /**
     * Konstruktor
     * 
     * @param s   sebesség
     * @param ca  vágási képesség
     * @param etl hatásidő
     * @param t   tekton amelyen elhelyeszkedik a rovar
     */
    public Insect(int s, boolean ca, int etl, Tecton t) {
        speed = s;
        cutAbility = ca;
        effectTimeLeft = etl;
        tecton = t;
    }

    /**
     * Konstruktor
     * 
     * @param s
     * @param ca
     * @param etl
     * @param t
     * @param ic
     */
    public Insect(int s, boolean ca, int etl, Tecton t, InsectColony ic) {
        speed = s;
        cutAbility = ca;
        effectTimeLeft = etl;
        tecton = t;
        hostColony = ic;
        eatenBy = null;
    }

    /**
     * Konstruktor
     * 
     * @param s   sebesség
     * @param ca  vágási képesség
     * @param etl hatásidő
     * @param t   tekton amelyen elhelyeszkedik a rovar
     * @param ic  kolónia amelyhez tartozik a rovar
     * @param eb  gombafaj amelyik megette a rovart
     */
    public Insect(int s, boolean ca, int etl, InsectColony ic, Tecton t, Fungus eb) {
        speed = s;
        cutAbility = ca;
        effectTimeLeft = etl;
        tecton = t;
        hostColony = ic;
        eatenBy = eb;
    }

    /**
     * Konstruktor (másoló konstruktor)
     *
     * @param i Insect
     */
    public Insect(Insect i){
        this(i.speed, i.cutAbility, i.effectTimeLeft, i.hostColony, i.tecton, i.eatenBy);
    }

    /**
     * Spóra elfogyasztása
     *
     * @param s spóra
     * @return
     */
    @Override
    public boolean EatSpore(Spore s) {
        if (s.GetTecton() != tecton) {
            // throw new IllegalArgumentException("Spore is not on the right tecton");
            System.err.println("Insect and Spore are not on the same Tecton");
            return false;
        }

        s.GiveEffect(this);
        hostColony.addNutrition(s.GetNutritionValue());
        tecton.RemoveSpore(s);
        return true;
    }

    /**
     * Mozgás
     * 
     * @param t tekton
     * @return sikeres-e a mozgás
     */
    @Override
    public boolean Move(Tecton t) {
        if (t.GetHypha(t, tecton) != null || tecton.GetHypha(t, tecton) != null) {
            t.AddInsect(this);

            tecton.RemoveInsect(this);

            this.SetTecton(t);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Vágás
     * 
     * @param t tekton
     * @return sikeres-e a vágás
     */
    @Override
    public boolean Cut(Tecton t) {
        if (!cutAbility) {
            return false;
        }

        Hypha h;
        Tecton correctTecton;

        if (t.GetHypha(t, tecton) != null) {
            h = t.GetHypha(t, tecton);
            //System.out.println(">[Tecton].GetHypha(t, tecton)");
            correctTecton = t;
        } else if (tecton.GetHypha(t, tecton) != null) {
            h = tecton.GetHypha(t, tecton);
            //System.out.println(">[Tecton].GetHypha(t, tecton)");
            correctTecton = tecton;
        } else {
            return false;
        }

        if (t.GetHypha(t, tecton) != null || tecton.GetHypha(t, tecton) != null) {
            h.GetHostFungus().RemoveHypha(h);
            //System.out.println(">[Fungus].RemoveHypha(h)");
            correctTecton.RemoveHypha(h);
            //System.out.println(">[Tecton].RemoveHypha(h)");
            for (Hypha n : h.GetNeighbours()) {
                n.RemoveNeighbours(h);
                //System.out.println(">[Hypha].RemoveNeighbours(h)");
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sebesség lekérdezése
     * 
     * @return sebesség
     */
    @Override
    public int GetSpeed() {
        return speed;
    }

    /**
     * Vágási képesség lekérdezése
     * 
     * @return vágási képesség
     */
    @Override
    public boolean GetCutAbility() {
        return cutAbility;
    }

    /**
     * Hatásidő lekérdezése
     * 
     * @return hatásidő
     */
    @Override
    public int GetEffectTimeLeft() {
        return effectTimeLeft;
    }

    /**
     * Host kolónia lekérdezése
     * 
     * @return host kolónia
     */
    @Override
    public InsectColony GetHostColony() {
        return hostColony;
    }

    /**
     * Tekton lekérdezése
     * 
     * @return tekton
     */
    @Override
    public Tecton GetTecton() {
        return tecton;
    }

    /**
     * Sebesség beállítása
     * 
     * @param s sebesség
     */
    public void SetSpeed(int s) {
        speed = s;
    }

    /**
     * Vágási képesség beállítása
     * 
     * @param ca vágási képesség
     */
    public void SetCutAbility(boolean ca) {
        cutAbility = ca;
    }

    /**
     * Hatásidő beállítása
     * 
     * @param etf hatásidő
     */
    public void SetEffectTimeLeft(int etf) {
        effectTimeLeft = etf;
        if(etf < 0)
            effectTimeLeft = 0;
    }

    /**
     * Host kolónia beállítása
     * 
     * @param ic host kolónia
     */
    public void SetHostColony(InsectColony ic) {
        hostColony = ic;
    }

    /**
     * Tekton beállítása
     * 
     * @param t tekton
     */
    public void SetTecton(Tecton t) {
        tecton = t;
    }

    /**
     * Lekéri melyik Fungus ette meg a rovart.
     * Ha null akkor nem ette meg egyik Fungus Sem.
     * 
     * @return Melyik Fungus ette meg a rovart. Fungus.
     */
    @Override
    public Fungus GetEatenBy() {
        return eatenBy;
    }

    /**
     * Gombafaj beállítása, amelyik megette a rovar.
     * 
     * @param fungus gombafaj
     */
    public void SetEatenBy(Fungus fungus) {
        eatenBy = fungus;
    }

    public String ToString(String data) {
        return "Insect," + data;
    }
}
