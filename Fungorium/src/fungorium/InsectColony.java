package fungorium;

import java.util.ArrayList;
import java.util.List;

public class InsectColony {
    private List<Insect> insects; // rovarok listája
    private int nutrition; // kolónia által gyűjtött tápérték

    /**
     * Default konstruktor
     */
    public InsectColony(){
        insects = new ArrayList<>();
        nutrition = 0;
    }

    /**
     * Tápérték hozzáadása
     * @param nu élelem
     */
    public void addNutrition(int nu){
        nutrition += nu;
    }

    /**
     * Insect létrehozása
     * @param t tekton
     * @return új rovar
     */
    public Insect createInsect(Tecton t){
       Insect ni = new Insect();
       System.out.println(">[Insect].Insect()");

       insects.add(ni);
       System.out.println(">[InsectColony].insects.add(ni)");
       //t.getInsects().add(ni); //amikor kész lesz
       //t.addInsect(ni);        

       return ni;
    }

    /**
     * Insect lista lekérdezése
     * @return rovar lista
     */
    public List<Insect> getInsects(){
        return insects;
    }

    /**
     * Tápérték lekérdezése
     * @return tápérték
     */
    public int getNutrition(){
        return nutrition;
    }

    /**
     * Insect lista beállítása
     * @param i rovar lista
     */
    public void setInsects(List<Insect> i){
        insects = i;
    }

    /**
     * Tápérték beállítása
     * @param nu tápérték
     */
    public void setNutrition(int nu){
        nutrition = nu;
    }

    /**
     * Kiveszi a megadott roart az insects listájából.
     * @param insectToRemove
     */
    public void RemoveInsect(Insect insectToRemove) {
        insects.remove(insectToRemove);
    }

    /**
     * Hozzáadja a megadott rovart az insects listájához.
     * @param insectToAdd
     */
    public void AddInsect(Insect insectToAdd) {
        insects.add(insectToAdd);
    }
}