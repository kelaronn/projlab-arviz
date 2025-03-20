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
        if(t==null){
            System.err.println("Hiba: A megadott tekton nincs inicializálva.");
            return;
        }
        tecton=t;
        tecton.GrowFungusBody(this);
        if(f==null){
            System.err.println("Hiba: A megadott gomba nincs inicializálva.");
            return;
        }
        hostFungus=f;
        f.AddBody(this);
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
        if (tecton == null) {
            System.err.println("Hiba: A gombatest nem tartózkodik egyetlen tektonon sem.");
            return;
        }

        ArrayList<Tecton> neighbours = new ArrayList<>(); 
        neighbours=tecton.GetNeighbours();
        System.out.println("GetNeighbour függvény segítségével feltöltjük a neighour listát. ");
        if (neighbours == null) {
            System.out.print("Eredménye: sikertelen.");
            System.err.println("A gombatest tektonjának nincsenek szomszédjai.");
            return;
        }
        System.out.println("Eredménye: sikeres.");

        for(int i=0; i<neighbours.size(); ++i) {
            neighbours.get(i).AddSpore();
            System.out.println("AddSpore függvényhívás iteratívan a spórák kiszórásához.");
        }
        if(isDeveloped){
            ArrayList<Tecton> nns = new ArrayList<>();
            for(int i=0; i<neighbours.size();++i){
                nns=neighbours.get(i).GetNeighbours();
                System.out.println("GetNeighbours függvénnyel iteratívan feltöltjük a szomszédok szomszédjainak (nns) listáját");
                for(int j=0; j < nns.size(); ++j) {
                    nns.get(j).AddSpore();
                    System.out.println("AddSpore függvényhívás iteratívan a spórák kiszórásához.");
            }
        }
        }
        sporeCount-=4;
        shotsLeft--;
        System.out.println("sporeCountból levonunk négyet (egy lövés ára) és shotsLeftet csökkentjük egyel.");
        System.out.println("ShootSpores sikeres végrehajtva!");
    }

    /**Gombatest meghalását implementálja*/
    public void Die(){
        tecton.AbsorbHyphas();
        System.out.println("AbsorbHyphas függvényhívás a fonalak felszívodása miatt");
        isDead=true;
        System.out.println("isDead érték igazra állítva!");
    }
    /**Spórát termel a gombatestnek*/
    public void ProduceSpore(){
        sporeCount++;
        System.out.println("sporeCount megnövelve egyel.");
    }

}
