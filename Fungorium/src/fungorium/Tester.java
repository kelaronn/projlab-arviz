package fungorium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tester {
    HashMap GameObjects = new HashMap<String, Object>(); // így minden teszter példány el tudja tárolni a benne létrehozott objecteket és más fv-ek is fel tudják használni pl. Tesztek.

    public void TectonBreakInit(){
        InsectColony ic = new InsectColony();
        GameObjects.put("ic", ic);
        Fungus fungus = new Fungus();
        GameObjects.put("fungus", fungus);

        
        Tecton t1 = new NarrowTecton();
        GameObjects.put("t1", t1);
        Insect i = new Insect();
        GameObjects.put("i", i);
        i.setHostColony(ic);
        i.setTecton(t1);
        t1.AddInsect(i);
        FungusBody fb = new FungusBody(t1,fungus);
        GameObjects.put("fb", fb);
        fungus.AddBody(fb);
        Hypha h1 = new Hypha(new ArrayList<>(),fungus,new ArrayList<>());
        GameObjects.put("h1", h1);
        h1.GetTectons().add(t1);
        t1.hyphas.add(h1);

        Tecton t2 = new WeakTecton();
        GameObjects.put("t2", t2);
        t2.neighbours.add(t1);
        t1.neighbours.add(t2);

        Tecton t3 = new BarrenTecton();
        GameObjects.put("t3", t3);
        Hypha h2 = new Hypha(new ArrayList<>(),fungus,new ArrayList<>());
        GameObjects.put("h2", h2);
        h2.GetTectons().add(t3);
        t3.hyphas.add(h2);
        fungus.AddHypha(h2);
        for (int j = 0; j < 3; j++) {
            t3.AddSpore(fungus);
            GameObjects.put("bs"+j, t3.spores.getLast());
        }
        t1.AddSpore(fungus);
        GameObjects.put("s",t1.spores.getLast());
    }

    public void GrowHyphaSuccessful(){
        // Init start ->
        Fungus pf = new Fungus();
        Fungus kf = new Fungus();
        NarrowTecton nt1 = new NarrowTecton();
        NarrowTecton nt2 = new NarrowTecton();
        NarrowTecton nt3 = new NarrowTecton();
        WideTecton wt1 = new WideTecton();
        nt1.GrowFungusBody(pf);
        pf.AddBody(nt1.GetFungusBody());
        nt2.GrowFungusBody(kf);
        kf.AddBody(nt2.GetFungusBody());
        Hypha ph1 = new Hypha(new ArrayList<>(), pf, new ArrayList<>(List.of(nt1)));
        pf.AddHypha(ph1);
        nt1.GetHyphas().add(ph1);
        Hypha kh1 = new Hypha(new ArrayList<>(), kf, new ArrayList<>(List.of(nt2)));
        kf.AddHypha(kh1);
        nt2.GetHyphas().add(kh1);
        nt1.AddNeighbour(nt2);
        nt1.AddNeighbour(nt3);
        nt1.AddNeighbour(wt1);
        nt2.AddNeighbour(nt1);
        nt2.AddNeighbour(wt1);
        nt3.AddNeighbour(nt1);
        nt3.AddNeighbour(wt1);
        wt1.AddNeighbour(nt1);
        wt1.AddNeighbour(nt2);
        wt1.AddNeighbour(nt3);
        // HyphaInit end <-
        boolean GrowHyphaSuccessful = nt3.AddHypha(pf, nt1);
        System.out.println(GrowHyphaSuccessful);
    }

    public void Test_GrowFungusBody(){}
    public void Test_GrowFungusBodyOnWeakTecton(){
        Tecton t2 = (Tecton)GameObjects.get("t2");
        Fungus fungus = (Fungus)GameObjects.get("fungus");

        boolean ans = t2.GrowFungusBody(fungus);
        System.out.println("[t2].GrowFungusBody()");
        System.out.println(ans ? "true":"false");
    }

    public void Test_GrowFungusBodyOnBody(){
        Tecton t1 = (Tecton)GameObjects.get("t1");
        Fungus fungus = (Fungus)GameObjects.get("fungus");

        boolean ans = t1.GrowFungusBody(fungus);
        System.out.println("[t1].GrowFungusBody()");
        System.out.println(ans ? "true":"false");
    }

    public void Test_GrowFungusBodyNotEnoughSpores(){
        Tecton t3 = (Tecton)GameObjects.get("t3");
        Fungus fungus = (Fungus)GameObjects.get("fungus");

        var spores = t3.GetSpores();
        System.out.println("[t3].GetSpores()");
        for (Spore s : spores) {
            t3.RemoveSpore(s);
            System.out.println("[t3].RemoveSpore(s)");
        }
        boolean ans = t3.GrowFungusBody(fungus);
        System.out.println("[t3].GrowFungusBody()");
        System.out.println(ans ? "true":"false");
    }

    public void Test_GrowFungusBodySuccess(){
        Tecton t3 = (Tecton)GameObjects.get("t3");
        Fungus fungus = (Fungus)GameObjects.get("fungus");

        boolean ans = t3.GrowFungusBody(fungus);
        System.out.println("[t3].GrowFungusBody()");
        FungusBody fb2 = new FungusBody(t3,fungus);
        System.out.println("    [fb2].FungusBody(t3,fungus)");
        for(int i = 0; i < t3.sporeCountToGrowFungus; i++){
            t3.RemoveSpore(t3.spores.getLast());
            System.out.println("    [t3].RemoveSpore(t3.spores.getLast())");
        }
        fungus.AddBody(fb2);
        System.out.println("    [fungus].AddBody(fb2)");
        System.out.println(ans ? "true":"false");
    }

    public void Test_FullTectonBreaks(){
        Tecton t1 = (Tecton)GameObjects.get("t1");
        Insect i = (Insect)GameObjects.get("i");
        Fungus fungus = (Fungus)GameObjects.get("fungus");
        FungusBody fb = (FungusBody)GameObjects.get("fb");

        t1.Break();
        System.out.println("[t1].Break()");
    }

    public void Test_HyphaAbsorbSuccessful(){
        Tecton t3 = (Tecton)GameObjects.get("t3");
        Fungus fungus = (Fungus)GameObjects.get("fungus");
        Hypha h2 = (Hypha)GameObjects.get("h2");

        t3.AbsorbHyphas();
        System.out.println("[t3].AbsorbHyphas()");
    }

    public void Test_HyphaAbsorbUnsuccessful(){
        Tecton t1 = (Tecton)GameObjects.get("t1");
        t1.AbsorbHyphas();
        System.out.println("[t1].AbsorbHyphas()");
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
