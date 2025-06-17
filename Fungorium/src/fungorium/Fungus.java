package fungorium;

import java.util.ArrayList;
import java.util.List;

/**
 * A Fungus osztály egy gombafajt reprezentál, amely gombatesteket (FungusBody) és gombafonalakat (Hypha)
 * csoportosít és tart számon. Felelőssége a gomba terjeszkedésének és túlélésének elősegítése a tektonokon
 * keresztül.
 */
public class Fungus implements IFungusView, IFungusController {
    /** A gombához tartozó gombatestek listája. */
    private List<FungusBody> bodies = new ArrayList<>();

    /** A gombához tartozó gombafonalak (mycelium) listája. */
    private List<Hypha> mycelium = new ArrayList<>();

    /**
     * Hozzáad egy gombafonalat (Hypha) a micélium listához, ha az nem null, ehhez a gombához tartozik,
     * és még nem szerepel a listában.
     * 
     * @param h A hozzáadandó gombafonal.
     * @return Igaz, ha a gombafonal hozzáadása sikeres, különben hamis.
     */
    public boolean AddHypha(Hypha h) {
        if(h != null && h.GetHostFungus().equals(this) && !mycelium.contains(h)) {
            mycelium.add(h);
            return true;
        }
        return false;
    }

    /**
     * Eltávolít egy gombafonalat (Hypha) a micélium listából, ha az nem null, ehhez a gombához tartozik,
     * és szerepel a listában.
     * 
     * @param h Az eltávolítandó gombafonal.
     * @return Igaz, ha a gombafonal eltávolítása sikeres, különben hamis.
     */
    public boolean RemoveHypha(Hypha h) {
        if(h != null && h.GetHostFungus().equals(this) && mycelium.contains(h)) {
            mycelium.remove(h);
            return true;
        }
        return false;
    }

    /**
     * Hozzáad egy gombatestet (FungusBody) a gombatestek listájához, ha az nem null, ehhez a gombához
     * tartozik, és még nem szerepel a listában.
     * 
     * @param b A hozzáadandó gombatest.
     * @return Igaz, ha a gombatest hozzáadása sikeres, különben hamis.
     */
    public boolean AddBody(FungusBody b) {
        if(b != null && b.GetHostFungus().equals(this) && !bodies.contains(b)){
            bodies.add(b);
            return true;
        }
        return false;
    }

    /**
     * Eltávolít egy gombatestet (FungusBody) a gombatestek listájából, ha az nem null, ehhez a gombához
     * tartozik, és szerepel a listában. Az eltávolítás során a gombatestet a tektonról is törli.
     * 
     * @param b Az eltávolítandó gombatest.
     * @return Igaz, ha a gombatest eltávolítása sikeres, különben hamis.
     */
    public boolean RemoveBody(FungusBody b) {
        if(b != null && b.GetHostFungus().equals(this) && bodies.contains(b)){
            bodies.remove(b);
            b.GetTecton().SetFungusBody(null);
            return true;
        }
        return false;
    }

    /**
     * Visszaadja a gombatestek (FungusBody) listáját.
     * @return A bodies lista referenciája.
     */
    public List<FungusBody> GetBodies() { return bodies; }

    /**
     * Visszaadja a gombafonalak (Hypha) listáját.
     * @return A mycelium lista referenciája.
     */
    public List<Hypha> GetMycelium() { return mycelium; }

    public String ToString(String data){
        return "Fungus,"+data;
    }
}
