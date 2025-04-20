package fungorium;

import java.util.List;

public interface IInsectColonyView {
    Insect createInsect(int s, boolean ca, int etl, Tecton t, Fungus eb);

    List<Insect> getInsects();

    int getNutrition();

    void AddInsect(Insect insectToAdd);

    String ToString(String data);
}
