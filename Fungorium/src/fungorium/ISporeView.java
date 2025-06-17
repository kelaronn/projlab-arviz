package fungorium;

public interface ISporeView {
    int GetNutritionValue();

    int GetEffectDurr();

    Fungus GetHostFungus();

    Tecton GetTecton();

    String ToString(String data);
}
