package fungorium;

import java.util.ArrayList;
import java.util.List;

public class InsectColony implements IInsectColonyView, IInsectColonyController {
    private List<Insect> insects; // rovarok listája
    private int nutrition; // kolónia által gyűjtött tápérték

    /**
     * Default konstruktor
     */
    public InsectColony() {
        insects = new ArrayList<>();
        nutrition = 0;
    }

    /**
     * Tápérték hozzáadása
     * 
     * @param nu élelem
     */
    @Override
    public void addNutrition(int nu) {
        nutrition += nu;
    }

    /**
     * Insect létrehozása
     * 
     * @param t tekton
     * @return új rovar
     */
    @Override
    public Insect createInsect(int s, boolean ca, int etl, Tecton t, Fungus eb) {
        Insect ni = new Insect(s, ca, etl, this, t, eb); // új rovar létrehozása
        insects.add(ni);
        t.AddInsect(ni);

        return ni;
    }

    /**
     * Insect lista lekérdezése
     * 
     * @return rovar lista
     */
    @Override
    public List<Insect> getInsects() {
        return insects;
    }

    /**
     * Tápérték lekérdezése
     * 
     * @return tápérték
     */
    @Override
    public int getNutrition() {
        return nutrition;
    }

    /**
     * Insect lista beállítása
     * 
     * @param i rovar lista
     */
    @Override
    public void setInsects(List<Insect> i) {
        insects = i;
    }

    /**
     * Tápérték beállítása
     * 
     * @param nu tápérték
     */
    @Override
    public void setNutrition(int nu) {
        nutrition = nu;
    }

    /**
     * Kiveszi a megadott roart az insects listájából.
     * 
     * @param insectToRemove
     */
    @Override
    public void RemoveInsect(Insect insectToRemove) {
        insects.remove(insectToRemove);
    }

    /**
     * Hozzáadja a megadott rovart az insects listájához.
     * 
     * @param insectToAdd
     */
    @Override
    public void AddInsect(Insect insectToAdd) {
        insects.add(insectToAdd);
    }

    public String ToString(String data) {
        return "InsectColony," + data;
    }
}