package fungorium;

import java.util.HashSet;
import java.util.Map;

public interface IView {
    /**
     * Getter metódus a planet attribútumhoz.
     * 
     * @return planet
     */
    public Map<String, Object> getPlanet();

    /**
     * Getter metódus az icCtr attribútumhoz.
     * 
     * @return icCtr
     */
    public int getIcCtr();
    public void IncIcCtr();

    /**
     * Getter metódus az fCtr attribútumhoz.
     * 
     * @return fCtr
     */
    public int getfCtr();
    public void IncfCtr();
    /**
     * Getter metódus az fbCtr attribútumhoz.
     *
     * @return fbCtr
     */
    public int getfbCtr();
    public void IncfbCtr();

    /**
     * Getter metódus az fbCtr attribútumhoz.
     * 
     * @return fbCtr
     */

    /**
     * Getter metódus az hCtr attribútumhoz.
     * 
     * @return hCtr
     */
    public int gethCtr();
    public void InchCtr();

    /**
     * Getter metódus az tCtr attribútumhoz.
     * 
     * @return tCtr
     */
    public int gettCtr();
    public void InctCtr();

    /**
     * Getter metódus az iCtr attribútumhoz.
     * 
     * @return iCtr
     */
    public int getiCtr();
    public void InciCtr();

    /**
     * Getter metódus az sCtr attribútumhoz.
     * 
     * @return sCtr
     */
    public int getsCtr();
    public void IncsCtr();

    public boolean menuExec(String command);
    public boolean save(String fileName);
    public boolean load(String fileName);
    public void rst();
    public boolean exec(String fileName);
    public boolean addt(String name, String tectonType);
    public boolean addf(String name);
    public boolean addic(String name, int nutritionValue);
    public boolean addfb(String name, Tecton tecton, Fungus actFungus, boolean fullyDeveloped, int age, boolean isDead, int sporeCount, int shotLimit);
    public boolean addh(String name, Fungus actFungus, Tecton tectonTS, Tecton tectonTN);
    public boolean adds(String name, String sporeType, int nutritionValue, int effectDurr, Fungus actFungus, Tecton tectonTN);
    public boolean addi(String name, int speed, boolean cutAbility, int effectTimeLeft, InsectColony actInsectColony, Tecton tecton, Fungus eatenBy);
    public boolean altt(Tecton tecton, Tecton tectonNH);
    public boolean alth(Hypha hypha, Hypha hyphaNH);
    public void lstt();
    public void lstf();
    public void lstic();
    public void lstfb();
    public void lsth();
    public void lsts();
    public void lsti();
    /**
     * Getter metódus a View-hoz tartozó kontroller példányhoz.
     *
     * @return GameController
     */
    public GameController GetGameController();
}
