package fungorium;

import java.util.ArrayList;
import java.util.List;

/**
 * A Hypha osztály egy gombafonalat (hifát) reprezentál, amely a gombatestből nő ki, és terjed a tektonokon.
 * Felelőssége a gombák terjedésének és a rovarok mozgásának elősegítése, valamint támpont nyújtása.
 */
public class Hypha {
    /** A szomszédos gombafonalak listája. */
    private List<Hypha> neighbours = new ArrayList<>();

    /** A gombafonalat birtokló gomba (Fungus) referencia. */
    private Fungus hostFungus;

    /** A gombafonalat meghatározó egy vagy két tekton (Tecton) tömbje. */
    private Tecton[] tectons = new Tecton[2];

    /**
     * Konstruktor, amely inicializálja a gombafonal attribútumait.
     * @param n A szomszédos gombafonalak kezdeti listája.
     * @param h A gombafonalat birtokló gomba (Fungus).
     * @param t A gombafonalat meghatározó egy vagy két tekton tömbje.
     */
    public Hypha(List<Hypha> n, Fungus h, Tecton[] t) {
        neighbours = n;
        hostFungus = h;
        tectons = t;
    }

    /**
     * Hozzáad egy szomszédos gombafonalat a neighbours listához, ha még nem szerepel benne.
     * @param h A hozzáadandó szomszédos gombafonal.
     */
    public void AddNeighbour(Hypha h) {
        // Ellenőrizzük, hogy nem szerepel-e már a szomszédok között és azonos-e a hostFungus
        if (!neighbours.contains(h) && h.GetHostFungus() == this.GetHostFungus()) {
            Tecton[] otherTectons = h.GetTectons();
            Tecton[] thisTectons = this.GetTectons();
            
            // Ellenőrizzük a közös tektonokat
            boolean hasCommonTecton = false;
            for (Tecton t1 : thisTectons) {
                if (t1 != null) {  // Null check az 1 tektonos eset miatt
                    for (Tecton t2 : otherTectons) {
                        if (t2 != null && t1 == t2) {  // Null check és egyenlőség ellenőrzés
                            hasCommonTecton = true;
                            break;
                        }
                    }
                }
                if (hasCommonTecton) break;
            }
        
            // Csak akkor adjuk hozzá, ha van közös tekton
            if (hasCommonTecton) {
                neighbours.add(h);
            }
        }
    }

    /**
     * Eltávolít egy szomszédos gombafonalat a neighbours listából.
     * @param h Az eltávolítandó szomszédos gombafonal.
     */
    public void RemoveNeighbour(Hypha h) { neighbours.remove(h); }

    /**
     * Beállítja a gombafonalat birtokló gomba (Fungus) referenciát.
     * @param f A beállítandó gomba referencia.
     */
    public void SetHostFungus(Fungus f) { hostFungus = f; }

    /**
     * Visszaadja a gombafonalat birtokló gomba (Fungus) referenciát.
     * @return A hostFungus attribútum értéke.
     */
    public Fungus GetHostFungus() { return hostFungus; }

    /**
     * Visszaadja a gombafonalat meghatározó egy vagy két tekton tömbjét.
     * @return A tectons tömb referenciája.
     */
    public Tecton[] GetTectons() { return tectons; }
    /**
     * Visszaadja a hifa szomszédjainak listáját.
     * @return A tectons tömb referenciája.
     */
    public List<Hypha> GetNeighbours() { return neighbours; }
}