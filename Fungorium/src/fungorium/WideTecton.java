package fungorium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WideTecton extends Tecton {

    /**
     * ősosztályban definiált fv. felüldefiniálása
     * @param fungus a gombafonalhoz tartozó gombafaj
     * @param t0 a gombafonalhoz tartozó előző tekton 
     * @return true, ha sikeresen hozzáadható a hifa, egyébként false
     */
    @Override
    public boolean AddHypha(Fungus fungus, Tecton t0) {
        // lehet rajta más fajú hifa, de ugyanolyan fajú nem.
        for(Hypha h : hyphas){
            if(h.GetHostFungus().equals(fungus))
                return false;
        }

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
