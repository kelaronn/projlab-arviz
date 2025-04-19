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

    /**
     * Getter metódus az fCtr attribútumhoz.
     * 
     * @return fCtr
     */
    public int getfCtr();

    /**
     * Getter metódus az hCtr attribútumhoz.
     * 
     * @return hCtr
     */
    public int gethCtr();

    /**
     * Getter metódus az tCtr attribútumhoz.
     * 
     * @return tCtr
     */
    public int gettCtr();

    /**
     * Getter metódus az iCtr attribútumhoz.
     * 
     * @return iCtr
     */
    public int getiCtr();

    /**
     * Getter metódus az sCtr attribútumhoz.
     * 
     * @return sCtr
     */
    public int getsCtr();
}
