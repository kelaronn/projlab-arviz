package fungorium;

import java.util.List;

/**
 * Interfész a Hypha osztály vezérlési műveleteihez, amelyek módosítják az objektum állapotát.
 */
public interface IHyphaController {
    /**
     * Hozzáad egy szomszédos gombafonalat a szomszédok listájához, ha még nem szerepel benne,
     * ugyanahhoz a gombához tartozik, és van közös tektonjuk. A hozzáadás kölcsönös.
     * 
     * @param h A hozzáadandó szomszédos gombafonal.
     * @return Igaz, ha a szomszéd hozzáadása sikeres, különben hamis.
     */
    boolean AddNeighbour(Hypha h);

    /**
     * Eltávolít egy szomszédos gombafonalat a szomszédok listájából, ha ugyanahhoz a gombához tartozik
     * és szerepel a listában. Az eltávolítás kölcsönös.
     * 
     * @param h Az eltávolítandó szomszédos gombafonal.
     * @return Igaz, ha a szomszéd eltávolítása sikeres, különben hamis.
     */
    boolean RemoveNeighbours(Hypha h);

    /**
     * Beállítja a gombafonalat birtokló gomba referenciát, ha az új gomba nem null és különbözik
     * a jelenlegitől.
     * 
     * @param f A beállítandó gomba referencia.
     * @return Igaz, ha a beállítás sikeres, különben hamis.
     */
    boolean SetHostFungus(Fungus f);

    /**
     * Megvizsgálja, hogy van-e élő gombatest az azonos fajhoz tartozó szomszédos gombafonalak hálózatában.
     * Ha nincs, akkor az összes bejárt gombafonal elhal, és eltávolításra kerül a tektonokról.
     * 
     * @return Igaz, ha a hálózat elhalt (nincs élő gombatest vagy étető tekton), különben hamis.
     */
    boolean Atrophy();

    /**
     * Elkábult rovar megevése, ha a rovar a gombafonal egyetlen tektonján van, él, el van kábulva,
     * és még nem ette meg más gomba. A rovar sebessége és hatása nullázódik, és a gomba megeszi.
     * 
     * @param insect A megevendő rovar.
     * @return Igaz, ha a rovar megevése sikeres, különben hamis.
     */
    boolean EatStunnedInsect(Insect insect);
}