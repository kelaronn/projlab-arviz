package fungorium;

public class Tester {
    /**
     * ShootSpores függvény a spórák kilövéséhez. Fejlett állapotban a szomszédok szomszédjára lő
     * Normál esetben csak a szomszédos tektonokra lő spórát
     * @param sporeCnt rendelkezésre álló spórák száma (egy lövéshez min 4 spórája kell legyen a testnek)
     * @param shotsLft megmaradt lövés lehetőségek száma. Ha eléri a nullát nem lőhet többet a gombatest
     * @param isDev logikai érték ami megadja, hogy fejlett-e a test.
     * @param isDead logikai érték ami megadja, hogy életben van-e a gombatest.
     * @return
     */
    public boolean Test_ShootSpores(int sporeCnt, int shotsLft, boolean isDev, boolean isDead){
        Fungus f = new Fungus();
        System.out.println("Fungus konstruktorhívás");

        NarrowTecton t = new NarrowTecton();
        System.out.println("NarrowTecton konstruktohívás");

        FungusBody fb=new FungusBody(t,f);
        System.out.println("FungusBody konstruktorhívás");
        
        fb.SetSporeCount(sporeCnt);
        System.out.println("SetSporeCount függvényhívás");

        fb.SetShotsLeft(shotsLft);
        System.out.println("SetShotsLeft függvényhívás");

        fb.SetIsDeveloped(isDev);
        System.out.println("SetIsFeveloped függvényhívás");
        System.out.println("SetIsDead függvényhívás");
        fb.SetIsDead(isDead);


        fb.ShootSpores();
        System.out.println("ShootSpores függvényhívás"); 
        return true;
    }

    /**
     * Gombatest halála
     * @param sporeCnt rendelkezésre álló spórák száma (egy lövéshez min 4 spórája kell legyen a testnek)
     * @param shotsLft megmaradt lövés lehetőségek száma. Ha eléri a nullát nem lőhet többet a gombatest
     * @param isDev logikai érték ami megadja, hogy fejlett-e a test.
     * @param isDead logikai érték ami megadja, hogy életben van-e a gombatest.
     * @return
     */
    public void Test_FungusBodyDie(int sporeCnt, int shotsLft, boolean isDev, boolean isDead){
        Fungus f = new Fungus();
        System.out.println("Fungus konstruktorhívás");

        NarrowTecton t = new NarrowTecton();
        System.out.println("NarrowTecton konstruktohívás");

        FungusBody fb=new FungusBody(t,f);
        System.out.println("FungusBody konstruktorhívás");
        
        fb.SetSporeCount(sporeCnt);
        System.out.println("SetSporeCount függvényhívás");

        fb.SetShotsLeft(shotsLft);
        System.out.println("SetShotsLeft függvényhívás");

        fb.SetIsDeveloped(isDev);
        System.out.println("SetIsFeveloped függvényhívás");
        System.out.println("SetIsDead függvényhívás");
        fb.SetIsDead(isDead);

        fb.Die();
    }

    /**
     * Gombatest spóratermelése
     * @param sporeCnt rendelkezésre álló spórák száma (egy lövéshez min 4 spórája kell legyen a testnek)
     * @param shotsLft megmaradt lövés lehetőségek száma. Ha eléri a nullát nem lőhet többet a gombatest
     * @param isDev logikai érték ami megadja, hogy fejlett-e a test.
     * @param isDead logikai érték ami megadja, hogy életben van-e a gombatest.
     * @return
     */
    public void Test_ProduceSpore(int sporeCnt, int shotsLft, boolean isDev, boolean isDead){
        Fungus f = new Fungus();
        System.out.println("Fungus konstruktorhívás");

        NarrowTecton t = new NarrowTecton();
        System.out.println("NarrowTecton konstruktohívás");

        FungusBody fb=new FungusBody(t,f);
        System.out.println("FungusBody konstruktorhívás");
        
        fb.SetSporeCount(sporeCnt);
        System.out.println("SetSporeCount függvényhívás");

        fb.SetShotsLeft(shotsLft);
        System.out.println("SetShotsLeft függvényhívás");

        fb.SetIsDeveloped(isDev);
        System.out.println("SetIsFeveloped függvényhívás");
        System.out.println("SetIsDead függvényhívás");
        fb.SetIsDead(isDead);

        fb.ProduceSpore();
    }
}
