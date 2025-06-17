package fungorium;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface ITectonView {

    ArrayList<Tecton> GetNeighbours();

    void AddSpore(Fungus fungus, boolean isRandom, Random rand);

    List<Spore> GetSpores();

    void AddInsect(Insect insect);

    void SetFungusBody(FungusBody fb);

    FungusBody GetFungusBody();

    void AddNeighbour(Tecton neighbour);

    List<Hypha> GetHyphas();

    List<Insect> GetInsects();

    String ToString(String data);

    void accept(TectonVisitor visitor);
}
