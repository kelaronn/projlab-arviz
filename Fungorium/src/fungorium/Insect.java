package fungorium;

public class Insect {
    private int speed = 1; // itt annyi hogy ez az  hogy hányszor tud egy körben mozogni az lenne a legszebb a speedre és ha -1 akkor meg nem csak mozogni nem tudsz hanem enni sem meg vágni sem és akkor az lenne a stun spore
    private boolean cutAbility = true;
    private int effectTimeLeft = 0;
    private InsectColony hostColony;
    private Tecton tecton;

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
     * Spóra elfogyasztása
     * @param s spóra
     */
    public void EatSpore(Spore s){
        if(s.GetTecton() != tecton){
            //throw new IllegalArgumentException("Spore is not on the right tecton");
            System.out.println("Spore not on the right Tecton");
            return;
        }        

        s.GiveEffect(this);
        hostColony.addNutrition(s.GetNutritionValue());
        tecton.RemoveSpore(s);
        System.out.println("Spore eaten.");

    }

    /**
     * Mozgás
     * @param t técton
     * @return sikeres-e a mozgás
     */
    public boolean Move(Tecton t){
        if(t.GetHypha(t, tecton) != null || tecton.GetHypha(t, tecton) != null){
            t.AddInsect(this);
            tecton.RemoveInsect(this);
            this.SetTecton(t);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Vágás
     * @param t técton
     * @return sikeres-e a vágás
     */
    public boolean Cut(Tecton t){
        if(!cutAbility){return false;}

        Hypha h;
        Tecton correctTecton;

        if(t.GetHypha(t, tecton) != null){
            h = t.GetHypha(t, tecton);
            correctTecton = t;
        }
        else if(tecton.GetHypha(t, tecton) != null){
            h = tecton.GetHypha(t, tecton);
            correctTecton = tecton;
        }
        else{
            return false;
        }

        if(t.GetHypha(t, tecton) != null || tecton.GetHypha(t, tecton) != null){
            h.GetHostFungus().RemoveHypha(h);
            correctTecton.RemoveHypha(h);
            for(Hypha n : h.GetNeighbours()){
                n.RemoveNeighbours(h);
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
     * Técton lekérdezése
     * @return técton
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
     * Técton beállítása
     * @param t técton
     */
    public void SetTecton(Tecton t){
        tecton = t;
    }

}
