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
    public void Test_FungusBodyDie(int sporeCnt, int shotsLft, boolean isDev, boolean isDead, int age){
        Fungus f = new Fungus();
        System.out.println("[Fungus].Fungus()");

        NarrowTecton t = new NarrowTecton();
        System.out.println("[NarrowTecton].NarrowTecton()");

        FungusBody fb=new FungusBody(t,f,isDev, age, isDead, sporeCnt, shotsLft);
        System.out.println("[FungusBody].FungusBody(t,f,isDev, age, isDead, sporeCnt, shotsLft)");

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
    public void Test_ProduceSpore(int sporeCnt, int shotsLft, boolean isDev, boolean isDead, int age){
        Fungus f = new Fungus();
        System.out.println("[Fungus].Fungus()");

        NarrowTecton t = new NarrowTecton();
        System.out.println("[NarrowTecton].NarrowTecton()");

        FungusBody fb=new FungusBody(t,f,isDev, age, isDead, sporeCnt, shotsLft);
        System.out.println("[FungusBody].FungusBody(t,f,isDev, age, isDead, sporeCnt, shotsLft)");

        System.out.println("[FungusBody].ProduceSpore()");
        fb.ProduceSpore();
    }
}
