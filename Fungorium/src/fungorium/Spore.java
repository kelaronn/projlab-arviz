package fungorium;

public class Spore {
    private int nutritionValue;
    private int effectDurr;
    private Fungus hostFungus;
    private Tecton tecton;
    
    public Spore(int nv, int ed, Fungus hf){
        nutritionValue = nv;
        effectDurr = ed;
        hostFungus = hf;
    }

    public boolean giveEffect(Insect i){
        return false;
    }

    public int getNutritionValue(){
        return nutritionValue;
    }

    public int getEffectDurr(){
        return effectDurr;
    }

    public Fungus getHostFungus(){
        return hostFungus;
    }

    public Tecton getTecton(){
        return tecton;
    }

    public void setNutritionValue(int nv){
        nutritionValue = nv;
    }

    public void setEffectDurr(int ed){
        effectDurr = ed;
    }

    public void setHostFungus(Fungus f){
        hostFungus = f;
    }

    public void setTecton(Tecton t){
        tecton = t;
    }

}