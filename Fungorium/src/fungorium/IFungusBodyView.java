package fungorium;

public interface IFungusBodyView {
    boolean GetIsDeveloped();

    int GetAge();

    boolean GetIsDead();

    int GetSporeCount();

    int GetShotsLeft();

    Tecton GetTecton();

    Fungus GetHostFungus();

    String ToString(String data);
}
