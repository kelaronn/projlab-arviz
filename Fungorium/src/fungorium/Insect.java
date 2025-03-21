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
    public void eatSpore(Spore s){
        /*if(s.getTecton != tecton)
            throw new IllegalArgumentException("Spore is not on the right tecton")
        */

        s.giveEffect(this);
        hostColony.addNutrition(s.getNutritionValue());
        //tecton.removeSpore(s);

    }

    /**
     * Mozgás
     * @param t técton
     * @return sikeres-e a mozgás
     */
    public boolean move(Tecton t){
        /*if(getHypha(t, tecton) != null){
            t.addInsect(this);
            tecton.removeInsect(this);
            this.setTecton(t);
            return true;
        }
        else{
            return false
        }

        */
        return false;
    }

    /**
     * Vágás
     * @param t técton
     * @return sikeres-e a vágás
     */
    public boolean cut(Tecton t){
        if(cutAbility == false){return false;}


        /*if(getHypha(t, tecton) != null){
            Hypha h = getHypha(t, tecton);
            h.getFungus().removeHypha(h);
            h.getTecton().removeHypha(h);
            for(n in h.neighbours){
                n.removeNeighbour(h);
            }
            return true;
        }
        else{
            return false
        }

        */

        return false;
    }

    /**
     * Sebesség lekérdezése
     * @return sebesség
     */ 
    public int getSpeed(){
        return speed;
    }

    /**
     * Vágási képesség lekérdezése
     * @return vágási képesség
     */ 
    public boolean getCutAbility(){
        return cutAbility;
    }

    /**
     * Hatásidő lekérdezése
     * @return hatásidő
     */  
    public int getEffectTimeLeft(){
        return effectTimeLeft;
    }

    /**
     * Host kolónia lekérdezése
     * @return host kolónia
     */   
    public InsectColony getHostColony(){
        return hostColony;
    }

    /**
     * Técton lekérdezése
     * @return técton
     */         
    public Tecton getTecton(){
        return tecton;
    }

    /**
     * Sebesség beállítása
     * @param s sebesség
     */
    public void setSpeed(int s){
        speed = s;
    }

    /**
     * Vágási képesség beállítása
     * @param ca vágási képesség
     */
    public void setCutAbility(boolean ca){
        cutAbility = ca;
    }

    /**
     * Hatásidő beállítása
     * @param etf hatásidő
     */
    public void setEffectTimeLeft(int etf){
        effectTimeLeft = etf;
    }

    /**
     * Host kolónia beállítása
     * @param ic host kolónia
     */
    public void setHostColony(InsectColony ic){
        hostColony = ic;
    }

    /**
     * Técton beállítása
     * @param t técton
     */
    public void setTecton(Tecton t){
        tecton = t;
    }

}
