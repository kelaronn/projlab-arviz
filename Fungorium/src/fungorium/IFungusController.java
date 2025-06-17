package fungorium;

/**
 * Az IFungusController interfész a gomba módosítására szolgáló műveleteket definiálja.
 * Lehetővé teszi gombatestek és gombafonalak hozzáadását és eltávolítását.
 */
public interface IFungusController {
    /**
     * Hozzáad egy gombafonalat (Hypha) a micéliumhoz.
     * 
     * @param h A hozzáadandó gombafonal.
     * @return Igaz, ha a gombafonal hozzáadása sikeres, különben hamis.
     */
    boolean AddHypha(Hypha h);

    /**
     * Eltávolít egy gombafonalat (Hypha) a micéliumból.
     * 
     * @param h Az eltávolítandó gombafonal.
     * @return Igaz, ha a gombafonal eltávolítása sikeres, különben hamis.
     */
    boolean RemoveHypha(Hypha h);

    /**
     * Hozzáad egy gombatestet (FungusBody) a gombatestek listájához.
     * 
     * @param b A hozzáadandó gombatest.
     * @return Igaz, ha a gombatest hozzáadása sikeres, különben hamis.
     */
    boolean AddBody(FungusBody b);

    /**
     * Eltávolít egy gombatestet (FungusBody) a gombatestek listájából.
     * 
     * @param b Az eltávolítandó gombatest.
     * @return Igaz, ha a gombatest eltávolítása sikeres, különben hamis.
     */
    boolean RemoveBody(FungusBody b);
}