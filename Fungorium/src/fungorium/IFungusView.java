package fungorium;

import java.util.List;

/**
 * Az IFungusView interfész a gomba megtekintésére szolgáló műveleteket definiálja.
 * Lehetővé teszi a gombatestek és gombafonalak listájának lekérdezését.
 */
public interface IFungusView {
    /**
     * Visszaadja a gombatestek (FungusBody) listáját.
     * @return A gombatestek listája.
     */
    List<FungusBody> GetBodies();

    /**
     * Visszaadja a gombafonalak (Hypha) listáját.
     * @return A gombafonalak listája.
     */
    List<Hypha> GetMycelium();
}