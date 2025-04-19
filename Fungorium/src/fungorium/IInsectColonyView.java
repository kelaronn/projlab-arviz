package fungorium;

import java.util.List;

public interface IInsectColonyView {
    Insect createInsect(Tecton t);

    List<Insect> getInsects();

    int getNutrition();

    void AddInsect(Insect insectToAdd);
}
