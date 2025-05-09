package fungorium;

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
    /**
     * Getter metódus a View-hoz tartozó kontroller példányhoz.
     *
     * @return GameController
     */
    public GameController GetGameController();

}
