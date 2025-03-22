package fungorium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NarrowTecton extends Tecton {

    @Override
    public boolean AddHypha(Fungus fungus, Tecton t0) {

        if (!hyphas.isEmpty()) // csak NarrowTecton esetén, ha már van rajta akármi, nem tud nőni. @override a többiben.
            return false;

        Hypha hyphaBetweenTectons = new Hypha(new LinkedList<Hypha>(), fungus, new ArrayList<>(List.of(t0, this)));
        Hypha hyphaOnTecton = new Hypha(new LinkedList<Hypha>(), fungus, new ArrayList<>(List.of(this)));

        hyphaBetweenTectons.AddNeighbour(hyphaOnTecton);
        hyphaOnTecton.AddNeighbour(hyphaBetweenTectons);
        // az első fungushoz tartozó hypha szomszédjaihoz hozzáadja a tektonok közötti hifát. Leginkább WideTecton miatt kell.
        for (Hypha h : t0.hyphas) {
            if (h.GetHostFungus().equals(fungus)) {
                hyphaBetweenTectons.AddNeighbour(h);
                h.AddNeighbour(hyphaBetweenTectons);
                break;
            } else {
                return false; // nincs hifa aminek szomszédjaihoz hozzá lehetne adni
            }
        }

        hyphas.add(hyphaBetweenTectons);
        hyphas.add(hyphaOnTecton);
        fungus.AddHypha(hyphaOnTecton);
        fungus.AddHypha(hyphaBetweenTectons);
        return true;
    }
}
