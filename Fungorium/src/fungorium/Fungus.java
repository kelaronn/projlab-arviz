package fungorium;

import java.util.ArrayList;
import java.util.List;

/**
 * A Fungus osztály egy gombafajt reprezentál, amely gombatesteket (FungusBody) és gombafonalakat (Hypha)
 * csoportosít és tart számon. Felelőssége a gomba terjeszkedésének és túlélésének elősegítése.
 */
public class Fungus {
    /** A gombához tartozó gombatestek listája. */
    private List<FungusBody> bodies = new ArrayList<>();

    /** A gombához tartozó gombafonalak (mycelium) listája. */
    private List<Hypha> mycelium = new ArrayList<>();

    /**
     * Hozzáad egy gombafonalat (Hypha) a mycelium listához.
     * @param h A hozzáadandó gombafonal.
     */
    public void addHypha(Hypha h) { mycelium.add(h); }

    /**
     * Eltávolít egy gombafonalat (Hypha) a mycelium listából.
     * @param h Az eltávolítandó gombafonal.
     */
    public void removeHypha(Hypha h) { mycelium.remove(h); }

    /**
     * Hozzáad egy gombatestet (FungusBody) a bodies listához.
     * @param b A hozzáadandó gombatest.
     */
    public void addBody(FungusBody b) { bodies.add(b); }

    /**
     * Eltávolít egy gombatestet (FungusBody) a bodies listából.
     * @param b Az eltávolítandó gombatest.
     */
    public void removeBody(FungusBody b) { bodies.remove(b); }

    /**
     * Visszaadja a gombatestek (FungusBody) listáját.
     * @return A bodies lista referenciája.
     */
    public List<FungusBody> getBodies() { return bodies; }

    /**
     * Visszaadja a gombafonalak (Hypha) listáját.
     * @return A mycelium lista referenciája.
     */
    public List<Hypha> getMycelium() { return mycelium; }
}
