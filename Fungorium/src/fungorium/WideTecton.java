package fungorium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WideTecton extends Tecton {

    public WideTecton() {
        super();
    }
    /**
     * Létrehoz egy új Wide tektont, amibe átmásolja az eredeti tekton szomszédjait, minden más default.
     * @param t másolni kívánt Wide tekton
     */
    public WideTecton(WideTecton t) {
        super(t);
    }

    /**
     * Meghívja a saját másoló konstruktorját.
     * @return új Wide Tekton
     */
    @Override
    public Tecton CreateCopy() {
        return new WideTecton(this);
    }




    /**
     * ősosztályban definiált fv. felüldefiniálása
     * @param fungus a gombafonalhoz tartozó gombafaj
     * @param t0 a gombafonalhoz tartozó előző tekton 
     * @return true, ha sikeresen hozzáadható a hifa, egyébként false
     */
    @Override
    public boolean AddHypha(Fungus fungus, Tecton t0) {
        if(!neighbours.contains(t0)){ // nincs a két tecton egymás mellett
            return false;
        }
        // lehet rajta más fajú hifa, de ugyanolyan fajú nem.
        for(Hypha h : GetHyphas()){
            if(h.GetHostFungus().equals(fungus)){
                return false;}
        }
        // ha nincs megadva szomszédos tekton, csak lerak egy hifát
        if(t0 == null){
            Hypha hypha = new Hypha(new LinkedList<Hypha>(), fungus, new ArrayList<>(List.of(this)));
            fungus.AddHypha(hypha);
            hyphas.add(hypha);
            return true;
        }
        boolean HasSameFungus = false;
        Hypha actHypha = null;
        for (Hypha hypha : t0.hyphas) {
            if (hypha.GetHostFungus().equals(fungus)) {
                actHypha = hypha;
                HasSameFungus = true;
                break;
            }
        }
        if (!HasSameFungus) {
            return false;
        }
        Hypha hyphaBetweenTectons = new Hypha(new LinkedList<Hypha>(), fungus, new ArrayList<>(List.of(t0, this)));
        Hypha hyphaOnTecton = new Hypha(new LinkedList<Hypha>(), fungus, new ArrayList<>(List.of(this)));
        hyphaBetweenTectons.AddNeighbour(hyphaOnTecton);
        actHypha.AddNeighbour(hyphaBetweenTectons);

        // az első fungushoz tartozó hypha szomszédjaihoz hozzáadja a tektonok közötti hifát. Leginkább WideTecton miatt kell.
        /*for (Hypha h : hyphas) {
            if (h.GetHostFungus().equals(fungus)) {
                hyphaBetweenTectons.AddNeighbour(h);
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
    public String ToString(String data){
        return "WideTecton,"+data;
    }
}
