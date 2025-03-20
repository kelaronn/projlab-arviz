package fungorium;

import java.util.ArrayList;
import java.util.List;

public class InsectColony {
    private List<Insect> insects;
    private int nutrition;

    public InsectColony(){
        insects = new ArrayList<>();
        nutrition = 0;
    }

    public void addNutrition(int nu){
        nutrition += nu;
    }

    public Insect createInsect(Tecton t){
       Insect ni = new Insect();

       insects.add(ni);
       //t.getInsects().add(ni); //amikor kész lesz
       //t.addInsect(ni);        //pick your poison

       return ni;
    }


    public List<Insect> getInsects(){
        return insects;
    }

    public int getNutrition(){
        return nutrition;
    }

    public void setInsects(List<Insect> i){
        insects = i;
    }

    public void setNutrition(int nu){
        nutrition = nu;
    }

}