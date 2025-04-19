package fungorium;

public interface ITectonController {
    boolean GrowFungusBodyFromInsect(Fungus fungus);

    boolean GrowFungusBody(Fungus fungus);

    boolean AbsorbHyphas();

    /**
     * virtuális metódus, a
     * leszármazottaknak meg kell valósítania, létrehoz egy új hifát (Hypha) a tektonon,
     * mégegyet a tekton és a paraméterül kapott tekton között, és hozzáadja a hyphas nevű
     * Hypha lista attribútumhoz.
     * Override a leszármazottakban!
     *
     * @param fungus host Fungus
     * @param t0,    szomszédos Tecton
     * @return true, ha sikeres, false ha nem.
     */
    boolean AddHypha(Fungus fungus, Tecton t0);
    boolean SupplyHyphas();
}
