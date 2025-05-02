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
        {
            return false;
        }
        boolean hasSameFungus = false;
        Hypha actHypha = null;
        for (Hypha hypha : t0.hyphas) {
            if (hypha.GetHostFungus().equals(fungus)) {
                actHypha = hypha;
                hasSameFungus = true;
                break;
            }
        }
        if (!hasSameFungus) {
            return false;
        }

        Hypha hyphaBetweenTectons = new Hypha(new LinkedList<Hypha>(), fungus, new ArrayList<>(List.of(t0, this)));
        Hypha hyphaOnTecton = new Hypha(new LinkedList<Hypha>(), fungus, new ArrayList<>(List.of(this)));
        hyphaOnTecton.AddNeighbour(hyphaBetweenTectons);
        actHypha.AddNeighbour(hyphaBetweenTectons);

        // az első fungushoz tartozó hypha szomszédjaihoz hozzáadja a tektonok közötti hifát. Leginkább WideTecton miatt kell.
        /*for (Hypha h : hyphas) {
            if (h.GetHostFungus().equals(fungus)) {
                //hyphaBetweenTectons.AddNeighbour(h);
               h.AddNeighbour(hyphaBetweenTectons);
                break;
            } else {
                return false; // nincs hifa aminek szomszédjaihoz hozzá lehetne adni
            }
        }*/

        hyphas.add(hyphaBetweenTectons);
        hyphas.add(hyphaOnTecton);
        fungus.AddHypha(hyphaBetweenTectons);
        fungus.AddHypha(hyphaOnTecton);
        return true;
    }

    @Override
    public boolean simpleAddHypha(Fungus fungus, Tecton t0){
        if (!hyphas.isEmpty()) // csak NarrowTecton esetén, ha már van rajta akármi, nem tud nőni. @override a többiben.
        {
            for (Hypha hypha : hyphas) {
                if (!hypha.GetHostFungus().equals(fungus)) {
                    return false;
                }
            }
            
        }
        // ha nincs megadva szomszédos tekton, csak lerak egy hifát
        if(t0 == null){
            for(Hypha h : GetHyphas()){
                if(h.GetHostFungus().equals(fungus) && h.GetTectons().size()==1)
                {
                    return false;
                }
            }
            Hypha hypha = new Hypha(new LinkedList<Hypha>(), fungus, new ArrayList<>(List.of(this)));
            fungus.AddHypha(hypha);
            hyphas.add(hypha);
            return true;
        }
        if(!neighbours.contains(t0)) // nincs a két tekton egymás mellett
        {
            return false;
        }
        for(Hypha h : GetHyphas()){
            if(h.GetHostFungus().equals(fungus) && h.GetTectons().get(h.GetTectons().size()-1).equals(this))
            {
                return false;
            }
        }
        Hypha hyphaBetweenTectons = new Hypha(new LinkedList<Hypha>(), fungus, new ArrayList<>(List.of(t0, this)));
        hyphas.add(hyphaBetweenTectons);
        fungus.AddHypha(hyphaBetweenTectons);
        return true;
    }
    
    @Override
    public String ToString(String data){
        return "NarrowTecton,"+data;
    }
}
