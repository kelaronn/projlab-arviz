package fungorium;

import java.util.ArrayList;
import java.util.Random;

public class FungusBody implements IFungusBodyView, IFungusBodyController {
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
    @Override
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
            else{
                this.SetIsDeveloped(false);
            }
        }
    }

    /**Lekéri az életkor értéket
     * @return életkor
    */
    @Override
    public int GetAge(){return age;}

    /**
     * Beállítja az isDead értéket
     * @param b logikai érték: halott/élő
     */
    public void SetIsDead(boolean b){isDead=b;}

    /**Lekéri az isDead érttéket
     * @return halott-e a gombatest
    */
    @Override
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
    @Override
    public int GetSporeCount(){return sporeCount;}

    /**
     * Beállítja a shotsLeft értéket
     * @param i lövési lehetőségek száma
     */
    public void SetShotsLeft(int i){
        if(i >= 0){
            shotsLeft=i;
            if (i == 0) {
                this.Die();
            }
        }
    }

    /**Lekéri a shotsLeft értéket
     * @return lövési lehetőségek száma
    */
    @Override
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
    @Override
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
    @Override
    public Fungus GetHostFungus(){return hostFungus;}


    /**ShootSpores függvény implementálja a gombatest spóralövésének logikáját*/
    @Override
    public boolean ShootSpores(boolean isRandom, Random rand){
        if(isDead){
            System.err.println("Error: The fungus body is dead and cannot shoot spores.");
            return false;
        }
        if(sporeCount<4){
            System.err.println("Error: The fungus body has less than 4 spores.");
            return false;
        }
        if (tecton == null) {
            System.err.println("Error: The fungus body is not located on any tecton.");
            return false;
        }

        ArrayList<Tecton> neighbours = new ArrayList<>(); 
        neighbours=tecton.GetNeighbours();
        System.out.println(">[Tecton].GetNeighbours()");
        if (neighbours == null) {
            System.err.println("Error: The tecton of the fungus body has no neighbours.");
            return false;
        } 

        for(int i=0; i<neighbours.size(); ++i) {
            neighbours.get(i).AddSpore(hostFungus,isRandom,rand);
            sporeCount--;
        }
        if(isDeveloped){
            System.out.println("Developed shooting!");
            ArrayList<Tecton> nns = new ArrayList<>();
            for(int i=0; i<neighbours.size();++i){
                nns=neighbours.get(i).GetNeighbours();
                if (!nns.equals(GetTecton())) {
                    for(int j=0; j < nns.size(); ++j) {
                        if(!this.GetTecton().equals(nns.get(j)) && !GetTecton().GetNeighbours().contains(nns.get(j))){
                            if(sporeCount == 0)
                                break;
                            nns.get(j).AddSpore(hostFungus,isRandom,rand);
                            sporeCount--;
                        }
                    }
                }
            }
        }
        //sporeCount-=4;
        shotsLeft--;
        System.out.println("ShootSpores success");
        return true;
    }

    /**
     * Gombatest meghalását implementálja
     *
     * @return
     */
    @Override
    public boolean Die(){
        /*tecton.AbsorbHyphas();
        System.out.println("[Tecton].AbsorbHyphas()");
        isDead=true;*/
        if(!this.GetIsDead()){
            this.SetIsDead(true);
            Hypha sameTypeHypha = null;
            //System.out.println(">[FungusBody].GetTecton().GetHyphas()");
            //System.out.println(">[Hypha].GetHostFungus() loop");
            //System.out.println(">[Hypha].GetHostFungus() loop");
            for (Hypha hypha : this.GetTecton().GetHyphas()) {
                if(hypha.GetHostFungus().equals(this.GetHostFungus())){
                    sameTypeHypha = hypha;
                    break;
                }
            }
            if(sameTypeHypha != null){
                sameTypeHypha.Atrophy();
                //System.out.println(">[Hypha].Atrophy()");
            }
            return true;
            //System.out.println(">[FungusBody].SetIsDead(true)");
            //System.out.println("Die() success.");
        }
        
        else{
            //System.out.println("Die() unsuccess, because FungusBody is already dead.");
            return false;
        }
       
    }

    /**
     * Spórát termel a gombatestnek
     */
    @Override
    public boolean ProduceSpore(){
        System.out.println(">[FungusBody].GetIsDead()");
        if(!this.GetIsDead()){
            sporeCount++;
            //System.out.println("ProduceSpore success.");
            return true;
        }
        else{
            return false;
            //System.out.println("ProduceSpore unsuccess, because FungusBody is dead.");
        }   
    }

    public String ToString(String data){
        return "FungusBody,"+data;
    }
}
