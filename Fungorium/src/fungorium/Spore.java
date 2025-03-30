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
     * Konstruktor
     * @param nv tápérték
     * @param ed hatásidő
     * @param hf host gombatest
     * @param t tekton amelyen a spóra elhelyeszkedik
     */
    public Spore(int nv, int ed, Fungus hf, Tecton t){
        nutritionValue = nv;
        effectDurr = ed;
        hostFungus = hf;
        tecton = t;
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
    * Tekton lekérdezése
     * @return tekton
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
     * Tekton beállítása
     * @param t tekton
     */
    public void SetTecton(Tecton t){
        tecton = t;
    }

}