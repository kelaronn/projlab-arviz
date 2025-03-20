package fungorium;

public class Insect {
    private int speed = 1; // itt annyi hogy ez az  hogy hányszor tud egy körben mozogni az lenne a legszebb a speedre és ha -1 akkor meg nem csak mozogni nem tudsz hanem enni sem meg vágni sem és akkor az lenne a stun spore
    private boolean cutAbility = true;
    private int effectTimeLeft = 0;
    private InsectColony hostColony;
    private Tecton tecton;

    public Insect(){}

    public Insect(int s, boolean ca, int etl){
        speed = s;
        cutAbility = ca;
        effectTimeLeft = etl;
    }

    public void eatSpore(Spore s){
        /*if(s.getTecton != tecton)
            throw new IllegalArgumentException("Spore is not on the right tecton")
        */

        s.giveEffect(this);
        hostColony.addNutrition(s.getNutritionValue());
        //tecton.removeSpore(s);

    }

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

    public int getSpeed(){
        return speed;
    }

    public boolean getCutAbility(){
        return cutAbility;
    }

    public int getEffectTimeLeft(){
        return effectTimeLeft;
    }

    public InsectColony getHostColony(){
        return hostColony;
    }

    public Tecton getTecton(){
        return tecton;
    }

    public void setSpeed(int s){
        speed = s;
    }

    public void setCutAbility(boolean ca){
        cutAbility = ca;
    }

    public void setEffectTimeLeft(int etf){
        effectTimeLeft = etf;
    }

    public void setHostColony(InsectColony ic){
        hostColony = ic;
    }

    public void setTecton(Tecton t){
        tecton = t;
    }

}
