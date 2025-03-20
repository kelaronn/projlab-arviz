package fungorium;

import java.util.ArrayList;

public class FungusBody {
    boolean isDeveloped; /**A gombatest fejlettségét kifejező logikai érték. Igaz ha a gombatest fejelett*/
    int age; /**A gombatest életkora*/
    boolean isDead; /**A gomba halott, ha az isDead logikai érték igaz*/
    int sporeCount; /**Megadja a gombatest elérhető spóráinak számát*/
    int shotsLeft;  /**Megadja a gombatest lövési lehetőségeinek számát*/
    Tecton tecton; /**Megadja a tektont amelyen tartózkodik a gombatest*/
    Fungus hostFungus; /**Megadja a gombát amelyhez tartozik a gombatest*/

    /**
     * Konstruktor, amely inicializálja a gombatestet
     * @param t a tekton amelyen van a gombatest
     * @param f a gomba amelyhez tartozik a gombatest
     **/
    public FungusBody(Tecton t, Fungus f){
        isDeveloped=false;
        age=0;
        isDead=false;
        sporeCount=0;
        shotsLeft=4;
    }

    /**
     * Beálllítja az isSeveloped értékét
     * @param b a kívánt logikai érték: fejlett/nem fejlett
     */
    public void SetIsDeveloped(boolean b){isDeveloped=b;}
    /**Lekéri az isDeveloped értékét*/
    public boolean GetIsDeveloped(){return isDeveloped;}
    /**
     * Beállítja az életkor értéket
     * @param a életkor érték paraméterként
     */
    public void SetAge(int a){age=a;}
    /**Lekéri az életkor értéket*/
    public int GetAge(){return age;}
    /**
     * Beállítja az isDead értéket
     * @param b logikai érték: halott/élő
     */
    public void SetIsDead(boolean b){isDead=b;}
    /**Lekéri az isDead érttéket*/
    public boolean GetIsDead(){return isDead;}
    /**
     * Beállítja a sporeCount értéket
     * @param i spórák száma
     */
    public void SetSporeCount(int i){sporeCount=i;}
    /**Lekéri a spórák számát*/
    public int GetSporeCount(){return sporeCount;}
    /**
     * Beállítja a shotsLeft értéket
     * @param i lövési lehetőségek száma
     */
    public void SetShotsLeft(int i){shotsLeft=i;}
    /**Lekéri a shotsLeft értéket*/
    public int GetShotsLeft(){return shotsLeft;}
    /**
     * Beállítja a tektont, amelyen tartózkodik a gombatest
     * @param t tekton példány
     */
    public void SetTecton(Tecton t){tecton=t;}
    /**Lekéri a tektont amelyen tartózkodik a gombatest*/
    public Tecton GetTecton(){return tecton;}
    /**
     * Beállítja a gombát, amelyhez tartozik a gombatest
     * @param f gomba emelyhez tartozik a gombatest
     */
    public void SetHostFungus(Fungus f){hostFungus=f;}
    /**Lekéri a gombát, amelyhez tartozik a gombatest*/
    public Fungus GetHostFungus(){return hostFungus;}

    /**ShootSpores függvény implementálja a gombatest spóralövésének logikáját*/
    public void ShootSpores(){
        ArrayList<Tecton> neighbours = new ArrayList<>(); 
        neighbours=tecton.GetNeighbours();

        for(int i=0; i<neighbours.size(); ++i) neighbours.get(i).AddSpore();
        if(isDeveloped){
            ArrayList<Tecton> nns = new ArrayList<>();
            for(int i=0; i<neighbours.size();++i){
                nns=neighbours.get(i).GetNeighbours();
                for(int j=0; j < nns.size(); ++j) nns.get(j).AddSpore();
            }
        }
        sporeCount-=4;
        shotsLeft--;
    }

    /**Gombatest meghalását implementálja*/
    public void Die(){
        isDead=true;
    }
    /**Spórát termel a gombatestnek*/
    public void ProduceSpore(){
        sporeCount++;
    }

}
