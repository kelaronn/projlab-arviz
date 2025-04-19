package fungorium;

import java.util.List;

/**
 * Interfész a Hypha osztály megtekintési műveleteihez, amelyek csak az adatok lekérdezését biztosítják.
 */
public interface IHyphaView {
    /**
     * Visszaadja a gombafonalat birtokló gomba (Fungus) referenciát.
     * @return A hostFungus attribútum értéke.
     */
    Fungus GetHostFungus();

    /**
     * Visszaadja a gombafonalat meghatározó egy vagy két tekton tömbjét.
     * @return A tectons lista referenciája.
     */
    List<Tecton> GetTectons();

    /**
     * Visszaadja a hifa szomszédjainak listáját.
     * @return A neighbours lista referenciája.
     */
    List<Hypha> GetNeighbours();
}