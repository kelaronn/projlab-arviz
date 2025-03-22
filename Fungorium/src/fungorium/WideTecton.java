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
            System.out.println(">[Hypha].GetHostFungus()");
        }

        Hypha hyphaBetweenTectons = new Hypha(new LinkedList<Hypha>(), fungus, new ArrayList<>(List.of(t0, this)));
        Hypha hyphaOnTecton = new Hypha(new LinkedList<Hypha>(), fungus, new ArrayList<>(List.of(this)));

        hyphaBetweenTectons.AddNeighbour(hyphaOnTecton);
        System.out.println(">[Hypha].AddNeighbour(hyphaOnTecton)");
        hyphaOnTecton.AddNeighbour(hyphaBetweenTectons);
        System.out.println(">[Hypha].AddNeighbour(hyphaBetweenTectons)");
        // az első fungushoz tartozó hypha szomszédjaihoz hozzáadja a tektonok közötti hifát. Leginkább WideTecton miatt kell.
        for (Hypha h : t0.hyphas) {
            if (h.GetHostFungus().equals(fungus)) {
                hyphaBetweenTectons.AddNeighbour(h);
                System.out.println(">[Hypha].AddNeighbour(h)");
                h.AddNeighbour(hyphaBetweenTectons);
                System.out.println(">[Hypha].AddNeighbour(hyphaBetweenTectons)");
                break;
            } else {
                return false; // nincs hifa aminek szomszédjaihoz hozzá lehetne adni
            }
        }

        hyphas.add(hyphaBetweenTectons);
        System.out.println(">[WideTecton].hyphas.add(hyphaBetweenTectons)");
        hyphas.add(hyphaOnTecton);
        System.out.println(">[WideTecton].hyphas.add(hyphaOnTecton)");
        fungus.AddHypha(hyphaOnTecton);
        System.out.println(">[Fungus].AddHypha(hyphaOnTecton)");
        fungus.AddHypha(hyphaBetweenTectons);
        System.out.println(">[Fungus].AddHypha(hyphaBetweenTectons)");
        return true;
    }
}
