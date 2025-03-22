package fungorium;

public class WeakTecton extends NarrowTecton{
    /**
     * ősosztályban definiált fv. felüldefiniálása
     * @param fungus
     * @return false
     */
    @Override
    public boolean GrowFungusBody(Fungus fungus) {
        return false; // nem tud növeszteni
    }
}
