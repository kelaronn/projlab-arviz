package fungorium;

public class Insect {
    private int speed = 1; // itt annyi hogy ez az  hogy hányszor tud egy körben mozogni az lenne a legszebb a speedre és ha -1 akkor meg nem csak mozogni nem tudsz hanem enni sem meg vágni sem és akkor az lenne a stun spore
    private boolean cutAbility = true; // vágási képesség
    private int effectTimeLeft = 0; // hatásidő
    private InsectColony hostColony; // a rovarhoz tartozó kolónia
    private Tecton tecton; // a rovarhoz tartozó tekton
    private Fungus eatenBy = null;

    /**
     * Default konstruktor
     */
    public Insect(){}

    /**
     * Konstruktor
     * @param s sebesség
     * @param ca vágási képesség
     * @param etl hatásidő
     */
    public Insect(int s, boolean ca, int etl){
        speed = s;
        cutAbility = ca;
        effectTimeLeft = etl;
    }

    /**
     * Konstruktor
     * @param s sebesség
     * @param ca vágási képesség
     * @param etl hatásidő
     * @param t tekton amelyen elhelyeszkedik a rovar
     */
    public Insect(int s, boolean ca, int etl, Tecton t){
        speed = s;
        cutAbility = ca;
        effectTimeLeft = etl;
        tecton = t;
    }

    /**
     * Spóra elfogyasztása
     * @param s spóra
     */
    public void EatSpore(Spore s){
        if(s.GetTecton() != tecton){
            //throw new IllegalArgumentException("Spore is not on the right tecton");
            System.out.println("Insect and Spore are not on the same Tecton");
            return;
        }        

        s.GiveEffect(this);
        System.out.println(">[Spore].GiveEffect(this)");
        hostColony.addNutrition(s.GetNutritionValue());
        System.out.println(">[InsectColony].addNutrition(s.GetNutritionValue())");
        tecton.RemoveSpore(s);
        System.out.println(">[Tecton].RemoveSpore(s)");
        System.out.println("Spore eaten.");

    }

    /**
     * Mozgás
     * @param t tekton
     * @return sikeres-e a mozgás
     */
    public boolean Move(Tecton t){
        if(t.GetHypha(t, tecton) != null || tecton.GetHypha(t, tecton) != null){
            t.AddInsect(this);
            System.out.println(">[Tecton].AddInsect(this)");
            tecton.RemoveInsect(this);
            System.out.println(">[Tecton].AddInsect(this)");
            this.SetTecton(t);
            System.out.println(">[Insect].SetTecton(t)");
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Vágás
     * @param t tekton
     * @return sikeres-e a vágás
     */
    public boolean Cut(Tecton t){
        if(!cutAbility){return false;}

        Hypha h;
        Tecton correctTecton;

        if(t.GetHypha(t, tecton) != null){
            h = t.GetHypha(t, tecton);
            System.out.println(">[Tecton].GetHypha(t, tecton)");
            correctTecton = t;
        }
        else if(tecton.GetHypha(t, tecton) != null){
            h = tecton.GetHypha(t, tecton);
            System.out.println(">[Tecton].GetHypha(t, tecton)");
            correctTecton = tecton;
        }
        else{
            return false;
        }

        if(t.GetHypha(t, tecton) != null || tecton.GetHypha(t, tecton) != null){
            h.GetHostFungus().RemoveHypha(h);
            System.out.println(">[Fungus].RemoveHypha(h)");
            correctTecton.RemoveHypha(h);
            System.out.println(">[Tecton].RemoveHypha(h)");
            for(Hypha n : h.GetNeighbours()){
                n.RemoveNeighbours(h);
                System.out.println(">[Hypha].RemoveNeighbours(h)");
            }
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Sebesség lekérdezése
     * @return sebesség
     */ 
    public int GetSpeed(){
        return speed;
    }

    /**
     * Vágási képesség lekérdezése
     * @return vágási képesség
     */ 
    public boolean GetCutAbility(){
        return cutAbility;
    }

    /**
     * Hatásidő lekérdezése
     * @return hatásidő
     */  
    public int GetEffectTimeLeft(){
        return effectTimeLeft;
    }

    /**
     * Host kolónia lekérdezése
     * @return host kolónia
     */   
    public InsectColony GetHostColony(){
        return hostColony;
    }

    /**
     * Tekton lekérdezése
     * @return tekton
     */         
    public Tecton GetTecton(){
        return tecton;
    }

    /**
     * Sebesség beállítása
     * @param s sebesség
     */
    public void SetSpeed(int s){
        speed = s;
    }

    /**
     * Vágási képesség beállítása
     * @param ca vágási képesség
     */
    public void SetCutAbility(boolean ca){
        cutAbility = ca;
    }

    /**
     * Hatásidő beállítása
     * @param etf hatásidő
     */
    public void SetEffectTimeLeft(int etf){
        effectTimeLeft = etf;
    }

    /**
     * Host kolónia beállítása
     * @param ic host kolónia
     */
    public void SetHostColony(InsectColony ic){
        hostColony = ic;
    }

    /**
     * Tekton beállítása
    * @param t tekton
     */
    public void SetTecton(Tecton t){
        tecton = t;
    }

    /**
     * Lekéri melyik Fungus ette meg a rovart.
     * Ha null akkor nem ette meg egyik Fungus Sem.
     * @return Melyik Fungus ette meg a rovart. Fungus.
     */
    public Fungus GetEatenBy(){ return eatenBy; }

}
