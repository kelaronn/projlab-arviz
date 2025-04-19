package fungorium;

public interface IInsectView {
    void EatSpore(Spore s);

    int GetSpeed();

    boolean GetCutAbility();

    int GetEffectTimeLeft();

    InsectColony GetHostColony();

    Tecton GetTecton();

    Fungus GetEatenBy();
}
