package fungorium;

import java.util.List;

public interface IInsectColonyController {
    void addNutrition(int nu);

    void setInsects(List<Insect> i);

    void setNutrition(int nu);

    void RemoveInsect(Insect insectToRemove);
}
