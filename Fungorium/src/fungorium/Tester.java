package fungorium;

import java.util.ArrayList;
import java.util.List;

public class Tester {

    public void TectonBreakInit(){
        InsectColony ic = new InsectColony();
        Fungus fungus = new Fungus();

        Tecton t1 = new NarrowTecton();
        Insect i = new Insect();
        i.setHostColony(ic);
        i.setTecton(t1);
        t1.AddInsect(i);
        FungusBody fb = new FungusBody(t1,fungus);
        fungus.AddBody(fb);
        Hypha h1 = new Hypha(new ArrayList<>(),fungus,new Tecton[2]);
        h1.GetTectons()[0] = t1; // jobb lenne arrayList
        t1.hyphas.add(h1);

        Tecton t2 = new WeakTecton();
        t2.neighbours.add(t1);
        t1.neighbours.add(t2);

        Tecton t3 = new BarrenTecton();
        Hypha h2 = new Hypha(new ArrayList<>(),fungus,new Tecton[2]);
        h2.GetTectons()[0] = t3; // jobb lenne arrayList
        t3.hyphas.add(h2);
        fungus.AddHypha(h2);
        for (int j = 0; j < 3; j++) {
            t3.AddSpore(fungus);
        }
        t1.AddSpore(fungus);
    }
    /**
     * ShootSpores függvény a spórák kilövéséhez. Fejlett állapotban a szomszédok szomszédjára lő
     * Normál esetben csak a szomszédos tektonokra lő spórát
     * @param sporeCnt rendelkezésre álló spórák száma (egy lövéshez min 4 spórája kell legyen a testnek)
     * @param shotsLft megmaradt lövés lehetőségek száma. Ha eléri a nullát nem lőhet többet a gombatest
     * @param isDev logikai érték ami megadja, hogy fejlett-e a test.
     * @param isDead logikai érték ami megadja, hogy életben van-e a gombatest.
     * @param age megadja a gombatest életkorát
     * @return
     */
    public boolean Test_ShootSpores(int sporeCnt, int shotsLft, boolean isDead, int age){
        Fungus f = new Fungus();
        System.out.println("[Fungus].Fungus()");

        NarrowTecton t = new NarrowTecton();
        System.out.println("[NarrowTecton].NarrowTecton()");

        FungusBody fb=new FungusBody(t,f, age, isDead, sporeCnt, shotsLft);
        System.out.println("[FungusBody].FungusBody(t,f, age, isDead, sporeCnt, shotsLft)");
        
        System.out.println("[FugusBody].ShootSpores()"); 
        fb.ShootSpores();
        return true;
    }

    /**
     * Gombatest halála
     * @param sporeCnt rendelkezésre álló spórák száma (egy lövéshez min 4 spórája kell legyen a testnek)
     * @param shotsLft megmaradt lövés lehetőségek száma. Ha eléri a nullát nem lőhet többet a gombatest
     * @param isDev logikai érték ami megadja, hogy fejlett-e a test.
     * @param isDead logikai érték ami megadja, hogy életben van-e a gombatest.
     * @param age megadja a gombatest életkorát
     * @return
     */
    public void Test_FungusBodyDie(){
        Fungus f = new Fungus();
        System.out.println("[Fungus].Fungus()");

        NarrowTecton t = new NarrowTecton();
        System.out.println("[NarrowTecton].NarrowTecton()");

        FungusBody fb=new FungusBody(t,f, true, 6, false, 0, 0);
        System.out.println("[FungusBody].FungusBody(t,f,true, 6, false, 0, 0)");

        System.out.println("[FungusBody].Die()");
        fb.Die();
    }

    /**
     * Gombatest spóratermelése
     * @param sporeCnt rendelkezésre álló spórák száma (egy lövéshez min 4 spórája kell legyen a testnek)
     * @param shotsLft megmaradt lövés lehetőségek száma. Ha eléri a nullát nem lőhet többet a gombatest
     * @param isDev logikai érték ami megadja, hogy fejlett-e a test.
     * @param isDead logikai érték ami megadja, hogy életben van-e a gombatest.
     * @param age megadja a gombatest életkorát
     * @return
     */
    public void Test_ProduceSpore(){
        Fungus f = new Fungus();
        System.out.println("[Fungus].Fungus()");

        NarrowTecton t = new NarrowTecton();
        System.out.println("[NarrowTecton].NarrowTecton()");

        FungusBody fb=new FungusBody(t,f,false, 1, false, 0, 5);
        System.out.println("[FungusBody].FungusBody(t,f,false, 1, false, 0, 5)");
        System.out.println("[FungusBody].GetSporeCnt() -> "+fb.GetSporeCount());
        System.out.println("[FungusBody].ProduceSpore()");
        fb.ProduceSpore();
        System.out.println("[FungusBody].GetSporeCnt() -> "+fb.GetSporeCount());
    }
}
