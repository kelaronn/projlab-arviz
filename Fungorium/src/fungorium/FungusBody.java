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
     * Konstruktor, amely inicializálja a gombatestet (csak gomba és tekton paraméter)
     * @param t a tekton amelyen van a gombatest
     * @param f a gomba amelyhez tartozik a gombatest
     */
    public FungusBody(Tecton t, Fungus f){
        isDeveloped=false;
        age=0;
        isDead=false;
        sporeCount=0;
        shotsLeft=4;
        if(t==null){
            System.err.println("Error: The given tecton is not initialized.");
            return;
        }
        tecton=t;
        if(f==null){
            System.err.println("Error: The given fungus is not initialized.");
            return;
        }
        hostFungus=f;
    }

    /**
     * Konstruktor, amely inicializálja a gombatestet (összes paraméter)
     * @param t a tekton amelyen van a gombatest
     * @param f a gomba amelyhez tartozik a gombatest
     * @param isDev a gombatest fejlett-e
     * @param age a gombatest életkora
     * @param isDead a gombatest halott-e
     * @param sporeCnt a gombatest spóráinak száma
     * @param shotsLft a gombatest lövési lehetőségeinek száma
     **/
    public FungusBody(Tecton t, Fungus f, boolean isDev, int age, boolean isDead, int sporeCnt, int shotsLft){
        isDeveloped=isDev;
        this.age=age;
        this.isDead=isDead;
        sporeCount=sporeCnt;
        shotsLeft=shotsLft;
        if(t==null){
            System.err.println("Error: The given tecton is not initialized.");
            return;
        }
        tecton=t;
        if(f==null){
            System.err.println("Error: The given fungus is not initialized.");
            return;
        }
        hostFungus=f;
    }

     /**
     * Konstruktor, amely inicializálja a gombatestet (összes paraméter, kivéve a fejlett-e)
     * @param t a tekton amelyen van a gombatest
     * @param f a gomba amelyhez tartozik a gombatest
     * @param isDev a gombatest fejlett-e
     * @param age a gombatest életkora
     * @param isDead a gombatest halott-e
     * @param sporeCnt a gombatest spóráinak száma
     * @param shotsLft a gombatest lövési lehetőségeinek száma
     **/
    public FungusBody(Tecton t, Fungus f, int age, boolean isDead, int sporeCnt, int shotsLft){
        if(age<5)isDeveloped=false;
        else isDeveloped=true;
        this.age=age;
        this.isDead=isDead;
        if(shotsLft<=0) this.isDead=true;
        sporeCount=sporeCnt;
        shotsLeft=shotsLft;
        if(t==null){
            System.err.println("Error: The given tecton is not initialized.");
            return;
        }
        tecton=t;
        if(f==null){
            System.err.println("Error: The given fungus is not initialized.");
            return;
        }
        hostFungus=f;
    }

    /**
     * Beálllítja az isSeveloped értékét
     * @param b a kívánt logikai érték: fejlett/nem fejlett
     */
    public void SetIsDeveloped(boolean b){isDeveloped=b;}

    /**Lekéri az isDeveloped értékét
     * @return fejlett-e a gombatest
    */
    public boolean GetIsDeveloped(){return isDeveloped;}

    /**
     * Beállítja az életkor értéket
     * @param a életkor érték paraméterként
     */
    public void SetAge(int a){
        if(a >= 0){
            age=a;
            if (a >= 5) {
                this.SetIsDeveloped(true);
            }
        }
    }

    /**Lekéri az életkor értéket
     * @return életkor
    */
    public int GetAge(){return age;}

    /**
     * Beállítja az isDead értéket
     * @param b logikai érték: halott/élő
     */
    public void SetIsDead(boolean b){isDead=b;}

    /**Lekéri az isDead érttéket
     * @return halott-e a gombatest
    */
    public boolean GetIsDead(){return isDead;}

    /**
     * Beállítja a sporeCount értéket
     * @param i spórák száma
     */
    public void SetSporeCount(int i){
        if(i >= 0){
            sporeCount=i;
        }
    }

    /**Lekéri a spórák számát
     * @return spórák száma
    */
    public int GetSporeCount(){return sporeCount;}

    /**
     * Beállítja a shotsLeft értéket
     * @param i lövési lehetőségek száma
     */
    public void SetShotsLeft(int i){
        if(i >= 0){
            shotsLeft=i;
            if (i == 0) {
                this.SetIsDead(true);
            }
        }
    }

    /**Lekéri a shotsLeft értéket
     * @return lövési lehetőségek száma
    */
    public int GetShotsLeft(){return shotsLeft;}

    /**
     * Beállítja a tektont, amelyen tartózkodik a gombatest
     * @param t tekton példány
     */
    public void SetTecton(Tecton t){
        if(t != null && !tecton.equals(t)){
            tecton=t;
        }
    }

    /**Lekéri a tektont amelyen tartózkodik a gombatest
     * @return tekton példány
    */
    public Tecton GetTecton(){return tecton;}

    /**
     * Beállítja a gombát, amelyhez tartozik a gombatest
     * @param f gomba emelyhez tartozik a gombatest
     */
    public void SetHostFungus(Fungus f){
        if(f != null && !hostFungus.equals(f)){
            hostFungus=f;
        }
    }

    /**
     * Lekéri a gombát, amelyhez tartozik a gombatest
     * @return gomba példány
    */
    public Fungus GetHostFungus(){return hostFungus;}


    /**ShootSpores függvény implementálja a gombatest spóralövésének logikáját*/
    public void ShootSpores(){
        if(isDead){
            System.err.println("Error: The fungus body is dead and cannot shoot spores.");
            return;
        }
        if(sporeCount<4){
            System.err.println("Error: The fungus body has less than 4 spores.");
            return;
        }
        if (tecton == null) {
            System.err.println("Error: The fungus body is not located on any tecton.");
            return;
        }

        ArrayList<Tecton> neighbours = new ArrayList<>(); 
        neighbours=tecton.GetNeighbours();
        System.out.println("[Tecton].GetNeighbours()");
        /*if (neighbours == null) {
            System.err.println("Error: The tecton of the fungus body has no neighbours.");
            return;
        }*/ //TODO: Tesztelés után uncommentelni

        for(int i=0; i<neighbours.size(); ++i) {
            neighbours.get(i).AddSpore(hostFungus);
            System.out.println("[Tecton].AddSpore().");
        }
        if(isDeveloped){
            System.out.println("Developed shooting!");
            ArrayList<Tecton> nns = new ArrayList<>();
            for(int i=0; i<neighbours.size();++i){
                nns=neighbours.get(i).GetNeighbours();
                System.out.println("[Tecton].GetNeighbours()");
                for(int j=0; j < nns.size(); ++j) {
                    nns.get(j).AddSpore(hostFungus);
                    System.out.println("[Tecton].AddSpore()");
            }
        }
        }
        sporeCount-=4;
        shotsLeft--;
        System.out.println("ShootSpores success");
    }

    /**
     * Gombatest meghalását implementálja
     */
    public void Die(){
        /*tecton.AbsorbHyphas();
        System.out.println("[Tecton].AbsorbHyphas()");
        isDead=true;*/
        if(!this.GetIsDead()){
            Hypha sameTypeHypha = null;
            for (Hypha hypha : this.GetTecton().GetHyphas()) {
                if(hypha.GetHostFungus().equals(this.GetHostFungus())){
                    sameTypeHypha = hypha;
                    break;
                }
            }
            if(sameTypeHypha != null){
                sameTypeHypha.Atrophy();
            }
            this.SetIsDead(true);
            System.out.println("Die() success.");
        }
        
        else{
            System.out.println("Die() unsuccess, because FungusBody is already dead.");
        }
    }

    /**
     * Spórát termel a gombatestnek
     */
    public void ProduceSpore(){
        if(!this.GetIsDead()){
            sporeCount++;
            System.out.println("ProduceSpore success.");
        }
        else{
            System.out.println("ProduceSpore unsuccess, because FungusBody is dead.");
        }   
    }
}
