package fungorium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NarrowTecton extends Tecton {

    public NarrowTecton() {
        super();
    }
    /**
     * Létrehoz egy új Narrow tektont, amibe átmásolja az eredeti tekton szomszédjait, minden más default.
     * @param t másolni kívánt Narrow tekton
     */
    public NarrowTecton(NarrowTecton t) {
        super(t);
    }

    /**
     * Meghívja a saját másoló konstruktorját.
     * @return új Narrow Tekton
     */
    @Override
    public Tecton CreateCopy() {
        return new NarrowTecton(this);
    }

    /**
     * A hifák hozzáadását implementáló függvény (felülírja a Tecton osztályban lévőt).
     * @param fungus a hozzáadandó hifa hostja
     * @param t0 a hozzáadandó hifa szomszédja
     * @return true, ha a hifa hozzá lett adva, false egyébként
     */
    @Override
    public boolean AddHypha(Fungus fungus, Tecton t0) {

        if (!hyphas.isEmpty()) // csak NarrowTecton esetén, ha már van rajta akármi, nem tud nőni. @override a többiben.
            return false;

        // ha nincs megadva szomszédos tekton, csak lerak egy hifát
        if(t0 == null){
            Hypha hypha = new Hypha(new LinkedList<Hypha>(), fungus, new ArrayList<>(List.of(this)));
            fungus.AddHypha(hypha);
            hyphas.add(hypha);
            return true;
        }
        if(!neighbours.contains(t0)) // nincs a két tekton egymás mellett
            return false;

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
        System.out.println(">[NarrowTecton].hyphas.add(hyphaBetweenTectons)");
        hyphas.add(hyphaOnTecton);
        System.out.println(">[NarrowTecton].hyphas.add(hyphaOnTecton)");
        fungus.AddHypha(hyphaOnTecton);
        System.out.println(">[Fungus].AddHypha(hyphaOnTecton)");
        fungus.AddHypha(hyphaBetweenTectons);
        System.out.println(">[Fungus].AddHypha(hyphaBetweenTectons)");
        return true;
    }
    
    @Override
    public String ToString(String data){
        return "NarrowTecton,"+data;
    }
}
