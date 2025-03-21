package fungorium;

import java.util.LinkedList;

public class WideTecton extends Tecton {


    @Override
    public boolean AddHypha(Fungus fungus, Tecton t0) {
        // lehet rajta más fajú hifa, de ugyanolyan fajú nem.
        for(Hypha h : hyphas){
            if(h.GetHostFungus().equals(fungus))
                return false;
        }

        Hypha hyphaBetweenTectons = new Hypha(new LinkedList<Hypha>(), fungus, new Tecton[]{t0, this});
        Hypha hyphaOnTecton = new Hypha(new LinkedList<Hypha>(), fungus, new Tecton[]{this});

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
