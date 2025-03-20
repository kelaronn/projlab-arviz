package fungorium;

public class Tester {
    /**
     * ShootSpporesSuccesful teszteli a fejlett és még nem fejlett gombatestek spóralövését. 
     * @param fb Gombatest objektum, amelyen a ShootSpores tesztet elvégezzük.
     */
    public void Test_ShootSpores(FungusBody fb){
        fb.ShootSpores();
    }

    /**
     * Gombatest halála
     * @param fb gombatest példány, amelen teszteljük a halálozás funkciót.
     */
    public void Test_FungusBodyDie(FungusBody fb){fb.Die();}

    /**
     * Spóratermelés funkció
     * @param fb gombatest példány, amelyen teszteljük a spóratermelés funkciót.
     */
    public void Test_ProduceSpore(FungusBody fb){fb.ProduceSpore();}
}
