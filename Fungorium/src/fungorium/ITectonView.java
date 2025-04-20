package fungorium;

import java.util.ArrayList;
import java.util.List;

public interface ITectonView {
    Tecton Break();

    ArrayList<Tecton> GetNeighbours();

    void AddSpore(Fungus fungus);

    List<Spore> GetSpores();

    void AddInsect(Insect insect);

    void SetFungusBody(FungusBody fb);

    FungusBody GetFungusBody();

    void AddNeighbour(Tecton neighbour);

    List<Hypha> GetHyphas();

    String ToString(String data);
}
