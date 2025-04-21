package fungorium;

public interface IInsectView {

    int GetSpeed();

    boolean GetCutAbility();

    int GetEffectTimeLeft();

    InsectColony GetHostColony();

    Tecton GetTecton();

    Fungus GetEatenBy();

    String ToString(String data);
}
