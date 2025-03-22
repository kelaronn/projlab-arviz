package fungorium;

public class Spore {
    private int nutritionValue;
    private int effectDurr = 2;
    private Fungus hostFungus;
    private Tecton tecton;
    
    /**
     * Default konstruktor
     */
    public Spore(){
        nutritionValue = 0;
        effectDurr = 0;
        hostFungus = null;
        tecton = null;
    }

    /**
     * Konstruktor
     * @param nv tápérték
     * @param ed hatásidő
     * @param hf host gombatest
     */
    public Spore(int nv, int ed, Fungus hf){
        nutritionValue = nv;
        effectDurr = ed;
        hostFungus = hf;
    }

    /**
     * Hatás kifejtése (sima spóra esetén nem történik semmi)
     * @param i rovar
     * @return sikeres-e a hatás kifejtése
     */
    public boolean GiveEffect(Insect i){
        return false;
    }

    /**
     * Tápérték lekérdezése
     * @return tápérték
     */
    public int GetNutritionValue(){
        return nutritionValue;
    }

    /**
     * Hatásidő lekérdezése
     * @return hatásidő
     */
    public int GetEffectDurr(){
        return effectDurr;
    }

    /**
     * Host gombatest lekérdezése
     * @return host gombatest
     */
    public Fungus GetHostFungus(){
        return hostFungus;
    }

    /**
     * Técton lekérdezése
     * @return técton
     */
    public Tecton GetTecton(){
        return tecton;
    }

    /**
     * Tápérték beállítása
     * @param nv tápérték
     */
    public void SetNutritionValue(int nv){
        nutritionValue = nv;
    }

    /**
     * Hatásidő beállítása
     * @param ed hatásidő
     */
    public void SetEffectDurr(int ed){
        effectDurr = ed;
    }

    /**
     * Host gombatest beállítása
     * @param f host gombatest
     */
    public void SetHostFungus(Fungus f){
        hostFungus = f;
    }

    /**
     * Técton beállítása
     * @param t técton
     */
    public void SetTecton(Tecton t){
        tecton = t;
    }

}