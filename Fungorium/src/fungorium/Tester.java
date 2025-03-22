package fungorium;

public class Tester {
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
    public void Test_ShootSpores(int sporeCnt, int shotsLft, boolean isDead, int age){
        Fungus f = new Fungus();
        System.out.println("[Fungus].Fungus()");

        NarrowTecton t = new NarrowTecton();
        System.out.println("[NarrowTecton].NarrowTecton()");

        FungusBody fb=new FungusBody(t,f, age, isDead, sporeCnt, shotsLft);
        System.out.println("[FungusBody].FungusBody(t,f, age, isDead, sporeCnt, shotsLft)");
        
        System.out.println("[FugusBody].ShootSpores()"); 
        fb.ShootSpores();
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
