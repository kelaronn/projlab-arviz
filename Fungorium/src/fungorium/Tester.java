package fungorium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tester {
    private HashMap<String, Object> HyphaInit = new HashMap<>();
    private HashMap<String, Object> GameObjects = new HashMap<>(); // így minden teszter példány el tudja tárolni a benne létrehozott objecteket és más fv-ek is fel tudják használni pl. Tesztek.
    private HashMap<String, Object> ShootSporesInit = new HashMap<>();
    private HashMap<String, Object> InsectInit = new HashMap<>();
    /**
     * tekton inicializálás
     */
    public void TectonBreakInit(){
        InsectColony ic = new InsectColony();
        GameObjects.put("ic", ic);
        Fungus fungus = new Fungus();
        GameObjects.put("fungus", fungus);

        
        NarrowTecton t1 = new NarrowTecton();
        GameObjects.put("t1", t1);
        Insect i = new Insect();
        GameObjects.put("i", i);
        i.SetHostColony(ic);
        i.SetTecton(t1);
        t1.AddInsect(i);
        FungusBody fb = new FungusBody(t1,fungus);
        GameObjects.put("fb", fb);
        fungus.AddBody(fb);
        Hypha h1 = new Hypha(new ArrayList<>(),fungus,new ArrayList<>());
        GameObjects.put("h1", h1);
        h1.GetTectons().add(t1);
        t1.hyphas.add(h1);

        WeakTecton t2 = new WeakTecton();
        GameObjects.put("t2", t2);
        t2.neighbours.add(t1);
        t1.neighbours.add(t2);

        BarrenTecton t3 = new BarrenTecton();
        GameObjects.put("t3", t3);
        Hypha h2 = new Hypha(new ArrayList<>(),fungus,new ArrayList<>());
        GameObjects.put("h2", h2);
        h2.GetTectons().add(t3);
        t3.hyphas.add(h2);
        fungus.AddHypha(h2);
        for (int j = 0; j < 3; j++) {
            t3.AddSpore(fungus);
            GameObjects.put("bs"+j, t3.spores.get(t3.spores.size() -1));
        }
        t1.AddSpore(fungus);
        GameObjects.put("s",t1.spores.get(t1.spores.size() -1));
    }
    /**
     * gombafonal inicializálás
     */
    public void HyphaInitFunction(){
        // HyphaInit start ->
        Fungus pf = new Fungus();
        Fungus kf = new Fungus();
        NarrowTecton nt1 = new NarrowTecton();
        NarrowTecton nt2 = new NarrowTecton();
        NarrowTecton nt3 = new NarrowTecton();
        WideTecton wt1 = new WideTecton();
        FungusBody pfb = new FungusBody(nt1, pf);
        pf.AddBody(pfb);
        nt1.SetFungusBody(pfb);
        FungusBody kfb = new FungusBody(nt2, kf);
        kf.AddBody(kfb);
        nt2.SetFungusBody(kfb);
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
        HyphaInit = new HashMap<>();
        HyphaInit.put("pf", pf);
        HyphaInit.put("kf", kf);
        HyphaInit.put("nt1", nt1);
        HyphaInit.put("nt2", nt2);
        HyphaInit.put("nt3", nt3);
        HyphaInit.put("wt1", wt1);
        HyphaInit.put("pfb", pfb);
        HyphaInit.put("kfb", kfb);
        HyphaInit.put("ph1", ph1);
        HyphaInit.put("kh1", kh1);
        // HyphaInit end <-
    }
    /**
     * spóra lövéséhez szükséges inicializálás
     */
    public void ShootSporesInitFunction(){
        // ShootSporesInit start ->
        Fungus f = new Fungus();
        NarrowTecton t1 = new NarrowTecton();
        NarrowTecton t2 = new NarrowTecton();
        NarrowTecton t3 = new NarrowTecton();
        NarrowTecton t4 = new NarrowTecton();
        FungusBody fb = new FungusBody(t1, f);
        t1.SetFungusBody(fb);
        Hypha h = new Hypha(new ArrayList<>(), f, new ArrayList<>(List.of(t1)));
        t1.GetHyphas().add(h);
        t1.AddNeighbour(t2);
        t1.AddNeighbour(t3);
        t2.AddNeighbour(t1);
        t2.AddNeighbour(t4);
        t3.AddNeighbour(t1);
        t4.AddNeighbour(t2);
        ShootSporesInit = new HashMap<>();
        ShootSporesInit.put("f", f);
        ShootSporesInit.put("t1", t1);
        ShootSporesInit.put("t2", t2);
        ShootSporesInit.put("t3", t3);
        ShootSporesInit.put("t4", t4);
        ShootSporesInit.put("fb", fb);
        ShootSporesInit.put("h", h);
        // ShootSporesInit end <-
    }
    /**
     * rovar inicializálás
     */
    public void InsectInitFunction(){
        Fungus f = new Fungus();
        InsectColony ic = new InsectColony();
        NarrowTecton n1 = new NarrowTecton();
        NarrowTecton n2 = new NarrowTecton();
        NarrowTecton n3 = new NarrowTecton();
        Spore s = new Spore(2, 0, f);
        s.SetTecton(n2);
        SpeedSpore sp = new SpeedSpore(2, 2, f);
        Insect i1 = new Insect();
        Insect i2 = new Insect();
        Hypha h = new Hypha(new ArrayList<>(), f, new ArrayList<>(List.of(n2,n3)));

        i1.SetHostColony(ic);
        i1.SetTecton(n1);
        i2.SetHostColony(ic);
        i2.SetTecton(n2);

        n1.AddNeighbour(n2);
        n1.AddInsect(i1);

        n2.AddNeighbour(n1);
        n2.AddNeighbour(n3);
        n2.AddInsect(i2);
        n2.GetHyphas().add(h);
        n2.GetSpores().add(s);
        n2.GetSpores().add(sp);

        n3.AddNeighbour(n2);

        InsectInit = new HashMap<>();
        InsectInit.put("f", f);
        InsectInit.put("n1", n1);
        InsectInit.put("n2", n2);
        InsectInit.put("n3", n3);
        InsectInit.put("i1", i1);
        InsectInit.put("i2", i2);
        InsectInit.put("h", h);
        InsectInit.put("s", s);
        InsectInit.put("sp", sp);
    }
    /**
     * Teszt: rovar sikeresen megeszi a spórát
     */
    public void Test_EatSporeSuccessful(){
        InsectInitFunction();
        Fungus f = (Fungus)InsectInit.get("f");
        InsectColony ic = (InsectColony)InsectInit.get("ic");
        NarrowTecton n2 = (NarrowTecton)InsectInit.get("n2");
        Insect i2 = (Insect)InsectInit.get("i2");
        Spore s = (Spore)InsectInit.get("s");

        System.out.println(">[Insect].EatSpore(s)");
        i2.EatSpore(s);
    }
    /**
     * Teszt: rovar nem tudja megenni a spórát
     */
    public void Test_EatSporeUnsuccessful(){
        InsectInitFunction();
        Fungus f = (Fungus)InsectInit.get("f");
        NarrowTecton n1 = (NarrowTecton)InsectInit.get("n1");
        NarrowTecton n2 = (NarrowTecton)InsectInit.get("n2");
        Insect i1 = (Insect)InsectInit.get("i1");
        Spore s = (Spore)InsectInit.get("s");

        System.out.println(">[Insect].EatSpore(s)");
        i1.EatSpore(s);
    }

    /**
     * Teszt: rovar el tudja vágni a gombafonalat
     */
    public void Test_CutSuccessful(){
        InsectInitFunction();
        Fungus f = (Fungus)InsectInit.get("f");
        NarrowTecton n2 = (NarrowTecton)InsectInit.get("n2");
        NarrowTecton n3 = (NarrowTecton)InsectInit.get("n3");
        Insect i2 = (Insect)InsectInit.get("i2");

        System.out.println(">[Insect].Cut(n3)");
        boolean response = i2.Cut(n3);
        System.out.println(response ? "<true" : "<false");
    }

    /**
     * Teszt: rovar nem tudja levágni a gombafonalat, mert nincs fonál a résen
     */
    public void Test_CutNoBridge(){
        InsectInitFunction();
        Fungus f = (Fungus)InsectInit.get("f");
        NarrowTecton n1 = (NarrowTecton)InsectInit.get("n1");
        NarrowTecton n2 = (NarrowTecton)InsectInit.get("n2");
        Insect i1 = (Insect)InsectInit.get("i1");

        System.out.println(">[Insect].Cut(n2)");
        boolean response = i1.Cut(n2);
        System.out.println(response ? "<true" : "<false");
    }

    /**
     * Teszt: rovar nem tudja elvágni a gombafonalat, mert nem képes rá
     */
    public void Test_CutNotAble(){
        InsectInitFunction();
        Fungus f = (Fungus)InsectInit.get("f");
        NarrowTecton n2 = (NarrowTecton)InsectInit.get("n2");
        NarrowTecton n3 = (NarrowTecton)InsectInit.get("n3");
        Insect i2 = (Insect)InsectInit.get("i2");
        i2.SetCutAbility(false);

        System.out.println(">[Insect].Cut(n3)");
        boolean response = i2.Cut(n3);
        System.out.println(response ? "<true" : "<false");
    }

    /**
     * Teszt: rovar mozgás sikeres
     */
    public void Test_MoveSuccessful(){
        InsectInitFunction();
        Fungus f = (Fungus)InsectInit.get("f");
        NarrowTecton n2 = (NarrowTecton)InsectInit.get("n2");
        NarrowTecton n3 = (NarrowTecton)InsectInit.get("n3");
        Insect i2 = (Insect)InsectInit.get("i2");

        System.out.println(">[Insect].Move(n3)");
        boolean response = i2.Move(n3);
        System.out.println(response ? "<true" : "<false");
    }

    /**
     * Teszt: rovar mozgás sikertelen
     */
    public void Test_MoveUnsuccessful(){
        InsectInitFunction();
        Fungus f = (Fungus)InsectInit.get("f");
        NarrowTecton n1 = (NarrowTecton)InsectInit.get("n1");
        NarrowTecton n2 = (NarrowTecton)InsectInit.get("n2");
        Insect i1 = (Insect)InsectInit.get("i1");

        System.out.println(">[Insect].Move(n2)");
        boolean response = i1.Move(n2);
        System.out.println(response ? "<true" : "<false");
    }

    /**
     * Teszt: gombafonal növesztés sikeres
     */ 
    public void Test_GrowHyphaSuccessful(){
        System.out.println("Initialization start ->");
        HyphaInitFunction();
        Fungus pf = (Fungus)HyphaInit.get("pf");
        NarrowTecton nt1 = (NarrowTecton)HyphaInit.get("nt1");
        NarrowTecton nt3 = (NarrowTecton)HyphaInit.get("nt3");
        System.out.println("<- Initialization end\n");

        System.out.println(">[NarrowTecton].AddHypha()");
        boolean response = nt3.AddHypha(pf, nt1);
        System.out.println(response ? "<true" : "<false");
        boolean successful = (response)?true:false;
        System.out.println("Test_GrowHyphaSuccessful is successful: "+((successful)?"true":"false"));
    }

    /**
     * Teszt: gombafonal növesztés sikertelen
     */ 
    public void Test_GrowHyphaUnsuccessful(){
        System.out.println("Initialization start ->");
        HyphaInitFunction();
        Fungus pf = (Fungus)HyphaInit.get("pf");
        NarrowTecton nt1 = (NarrowTecton)HyphaInit.get("nt1");
        NarrowTecton nt2 = (NarrowTecton)HyphaInit.get("nt2");
        System.out.println("<- Initialization end\n");

        System.out.println(">[NarrowTecton].AddHypha()");
        boolean response = nt2.AddHypha(pf, nt1);
        System.out.println(response ? "<true" : "<false");
        boolean successful = (!response)?true:false;
        System.out.println("Test_GrowHyphaUnsuccessful is successful: "+((successful)?"true":"false"));
    }

    /**
     * Teszt: két különböző típusú gombafonal növesztés sikeres (wide tektonon)
     */
    public void Test_GrowTwoDifferentHyphaOnWideTectonSuccessful(){
        System.out.println("Initialization start ->");
        HyphaInitFunction();
        Fungus pf = (Fungus)HyphaInit.get("pf");
        Fungus kf = (Fungus)HyphaInit.get("kf");
        NarrowTecton nt1 = (NarrowTecton)HyphaInit.get("nt1");
        NarrowTecton nt2 = (NarrowTecton)HyphaInit.get("nt2");
        WideTecton wt1 = (WideTecton)HyphaInit.get("wt1");
        System.out.println("<- Initialization end\n");

        System.out.println(">[WideTecton].AddHypha()");
        boolean response = wt1.AddHypha(pf, nt1);
        System.out.println(response ? "<true" : "<false");
        System.out.println(">[WideTecton].AddHypha()");
         response = wt1.AddHypha(kf, nt2);
        System.out.println(response ? "<true" : "<false");
        boolean successful = (response)?true:false;
        System.out.println("Test_GrowTwoDifferentHyphaOnWideTectonSuccessful is successful: "+((successful)?"true":"false"));
    }

    /**
     * Teszt: gombafonal sikeres elsorvadása 
     */
    public void Test_AtrophyOfHypha(){
        System.out.println("Initialization start ->");
        HyphaInitFunction();
        Fungus pf = (Fungus)HyphaInit.get("pf");
        NarrowTecton nt1 = (NarrowTecton)HyphaInit.get("nt1");
        NarrowTecton nt3 = (NarrowTecton)HyphaInit.get("nt3");
        System.out.println("<- Initialization end\n");

        System.out.println(">[NarrowTecton].AddHypha()");
        boolean response = nt3.AddHypha(pf, nt1);
        System.out.println(">[NarrowTecton].GetFungusBody().Die()");
        nt1.GetFungusBody().Die();
        System.out.println("Test_AtrophyOfHypha is successful: "+((nt1.GetHyphas().size()+nt3.GetHyphas().size()==0)?"true":"false"));
    }

    /**
     * Teszt: gombatest növesztés sikertelen, mert már van azonos típusú hifa a wide tektonon
     */
    public void Test_GrowSameTypeHyphaOnWideTectonUnsuccessful(){
        System.out.println("Initialization start ->");
        HyphaInitFunction();
        Fungus pf = (Fungus)HyphaInit.get("pf");
        NarrowTecton nt1 = (NarrowTecton)HyphaInit.get("nt1");
        NarrowTecton nt3 = (NarrowTecton)HyphaInit.get("nt3");
        WideTecton wt1 = (WideTecton)HyphaInit.get("wt1");
        System.out.println("<- Initialization end\n");

        System.out.println(">[NarrowTecton].AddHypha()");
        boolean response = nt3.AddHypha(pf, nt1);
        System.out.println(response ? "<true" : "<false");
        System.out.println(">[WideTecton].AddHypha()");
        response = wt1.AddHypha(pf, nt3);
        System.out.println(response ? "<true" : "<false");
        System.out.println(">[WideTecton].AddHypha()");
        response = wt1.AddHypha(pf, nt1);
        System.out.println(response ? "<true" : "<false");
        boolean successful = (!response)?true:false;
        System.out.println("Test_GrowSameTypeHyphaOnWideTectonUnsuccessful is successful: "+((successful)?"true":"false"));
    }

    /**
     * Teszt: gombatest növesztés sikeres, mert nincs gombatest a tektonon
     */
    public void Test_GrowFungusBodyOnWeakTecton(){
        TectonBreakInit();
        WeakTecton t2 = (WeakTecton)GameObjects.get("t2");
        Fungus fungus = (Fungus)GameObjects.get("fungus");

        boolean ans = t2.GrowFungusBody(fungus);
        System.out.println(">[Tecton].GrowFungusBody()");
        System.out.println(ans ? "<true":"<false");
    }

    /**
     * Teszt: gombatest növesztés sikeres
     */
    public void Test_GrowFungusBodyOnBody(){
        TectonBreakInit();
        NarrowTecton t1 = (NarrowTecton)GameObjects.get("t1");
        Fungus fungus = (Fungus)GameObjects.get("fungus");

        boolean ans = t1.GrowFungusBody(fungus);
        System.out.println(">[Tecton].GrowFungusBody()");
        System.out.println(ans ? "<true":"<false");
    }

    /**
     * Teszt: gombatest növesztés sikertelen, mert nincs elég spóra
     */
    public void Test_GrowFungusBodyNotEnoughSpores(){
        TectonBreakInit();
        BarrenTecton t3 = (BarrenTecton)GameObjects.get("t3");
        Fungus fungus = (Fungus)GameObjects.get("fungus");

        var spores = t3.GetSpores();
        System.out.println(">[Tecton].GetSpores()");
        for(int i = 0; i < t3.sporeCountToGrowFungus; i++){
            t3.RemoveSpore(t3.spores.get(spores.size() -1));
            System.out.println("    >[Tecton].RemoveSpore(t3.spores.get(t3.spores.size() -1))");
        }
        boolean ans = t3.GrowFungusBody(fungus);
        System.out.println(">[Tecton].GrowFungusBody()");
        System.out.println(ans ? "<true":"<false");
    }

    /**
     * Teszt: gombatest növesztés sikeres
     */
    public void Test_GrowFungusBodySuccess(){
        TectonBreakInit();
        BarrenTecton t3 = (BarrenTecton)GameObjects.get("t3");
        Fungus fungus = (Fungus)GameObjects.get("fungus");

        boolean ans = t3.GrowFungusBody(fungus);
        System.out.println(">[Tecton].GrowFungusBody()");
        System.out.println(ans ? "<true":"<false");
        System.out.println("    [FungusBody].FungusBody(t3,fungus)");
        
        for(int i = 0; i < t3.sporeCountToGrowFungus; i++){
            t3.RemoveSpore(t3.spores.get(t3.spores.size() -1));
            System.out.println("    >[Tecton].RemoveSpore(t3.spores.get(t3.spores.size() -1))");
        }
        fungus.AddBody(t3.GetFungusBody());
        System.out.println("    >[Fungus].AddBody(fb2)");
        System.out.println(ans ? "<true":"<false");
    }
    /**
     * Teszt: tekton törés sikeres
     */
    public void Test_FullTectonBreaks(){
        TectonBreakInit();
        NarrowTecton t1 = (NarrowTecton)GameObjects.get("t1");
        Insect i = (Insect)GameObjects.get("i");
        Fungus fungus = (Fungus)GameObjects.get("fungus");
        FungusBody fb = (FungusBody)GameObjects.get("fb");

        t1.Break();
        System.out.println(">[Tecton].Break()");
    }
    /**
     * Teszt: hifa felszódás sikeres
     */
    public void Test_HyphaAbsorbSuccessful(){
        TectonBreakInit();
        BarrenTecton t3 = (BarrenTecton)GameObjects.get("t3");
        Fungus fungus = (Fungus)GameObjects.get("fungus");
        Hypha h2 = (Hypha)GameObjects.get("h2");

        boolean ans = t3.AbsorbHyphas();
        System.out.println(">[Tecton].AbsorbHyphas()");
        System.out.println(ans ? "<true" : "<false");
    }
    /**
     * Teszt: hifa felszódás sikertelen
     */
    public void Test_HyphaAbsorbUnsuccessful(){
        TectonBreakInit();
        NarrowTecton t1 = (NarrowTecton)GameObjects.get("t1");
        boolean ans = t1.AbsorbHyphas();
        System.out.println(">[Tecton].AbsorbHyphas()");
        System.out.println(ans ? "<true" : "<false");
    }

    /**
     * Gombatest sikeres spóra szórása szomszédos tektonokra
     */
    public void Test_BasicShootSporesSuccessful(){
        System.out.println("Initialization start ->");
        ShootSporesInitFunction();
        System.out.println(">[Fungus].Fungus()");
        Fungus f = (Fungus)ShootSporesInit.get("f");
        System.out.println(">[NarrowTecton].NarrowTecton()");
        NarrowTecton t1 = (NarrowTecton)ShootSporesInit.get("t1");
        System.out.println(">[FungusBody].FungusBody()");
        FungusBody fb = (FungusBody)ShootSporesInit.get("fb");
        System.out.println("<- Initialization end\n");

        System.out.println(">[FungusBody].SetSporeCount(4)");
        fb.SetSporeCount(4);
        List<Integer> oldNeighboursSporeCount = new ArrayList<>();
        for (Tecton tecton : t1.GetNeighbours()) {
            oldNeighboursSporeCount.add(tecton.GetSpores().size());
        }
        System.out.println(">[FugusBody].ShootSpores()");
        fb.ShootSpores();
        List<Integer> newNeighboursSporeCount = new ArrayList<>();
        for (Tecton tecton : t1.GetNeighbours()) {
            newNeighboursSporeCount.add(tecton.GetSpores().size());
        }
        boolean successful = true;
        for(int i = 0; i < oldNeighboursSporeCount.size(); i++){
            if (!(newNeighboursSporeCount.get(i).intValue() == (oldNeighboursSporeCount.get(i).intValue()+1))) {
                successful = false;
                break;
            }
        }
        System.out.println("Test_BasicShootSporesSuccessful is successful: "+((successful)?"true":"false"));
    }
    
    /**
     * Gombatest sikeres spóra szórása szomszédos tektonok szomszédaira
     */
    public void Test_AdvancedShootSporesSuccessful(){
        System.out.println("Initialization start ->");
        ShootSporesInitFunction();
        System.out.println(">[Fungus].Fungus()");
        Fungus f = (Fungus)ShootSporesInit.get("f");
        System.out.println(">[NarrowTecton].NarrowTecton()");
        NarrowTecton t1 = (NarrowTecton)ShootSporesInit.get("t1");
        System.out.println(">[FungusBody].FungusBody()");
        FungusBody fb = (FungusBody)ShootSporesInit.get("fb");
        System.out.println("<- Initialization end\n");

        System.out.println(">[FungusBody].SetAge(5)");
        fb.SetAge(5);
        System.out.println(">[FungusBody].SetSporeCount(4)");
        fb.SetSporeCount(4);
        int step = 0;
        List<List<Integer>> oldNeighboursNeighboursSporeCount = new ArrayList<>();
        for (int i = 0; i < t1.GetNeighbours().size(); i++) {
            oldNeighboursNeighboursSporeCount.add(new ArrayList<>());
        }
        for (int i = 0; i < t1.GetNeighbours().size(); i++) {
            for (int j = 0; j < t1.GetNeighbours().get(i).GetNeighbours().size(); j++) {
                if(!t1.GetNeighbours().get(i).GetNeighbours().get(j).equals(t1)){
                    oldNeighboursNeighboursSporeCount.get(i).add(t1.GetNeighbours().get(i).GetNeighbours().get(j).GetSpores().size());
                }
            }
        }
        System.out.println("[FugusBody].ShootSpores()");
        fb.ShootSpores();
        List<List<Integer>> newNeighboursNeighboursSporeCount = new ArrayList<>();
        for (int i = 0; i < t1.GetNeighbours().size(); i++) {
            newNeighboursNeighboursSporeCount.add(new ArrayList<>());
        }
        for (int i = 0; i < t1.GetNeighbours().size(); i++) {
            for (int j = 0; j < t1.GetNeighbours().get(i).GetNeighbours().size(); j++) {
                if(!t1.GetNeighbours().get(i).GetNeighbours().get(j).equals(t1)){
                    newNeighboursNeighboursSporeCount.get(i).add(t1.GetNeighbours().get(i).GetNeighbours().get(j).GetSpores().size());
                }
            }
        }
        boolean successful = true;
        for(int i = 0; i < oldNeighboursNeighboursSporeCount.size(); i++){
            for(int j = 0; j < oldNeighboursNeighboursSporeCount.get(i).size(); j++){
                if (!(newNeighboursNeighboursSporeCount.get(i).get(j).intValue() == (oldNeighboursNeighboursSporeCount.get(i).get(j).intValue()+1))) {
                    successful = false;
                    break;
                }
            }
            if(!successful){
                break;
            }
        }
        for (int i = 0; i < oldNeighboursNeighboursSporeCount.size(); i++) {
            System.out.println("oldNeighboursNeighboursSporeCount[" + i + "] = " + oldNeighboursNeighboursSporeCount.get(i));
        }
        for (int i = 0; i < newNeighboursNeighboursSporeCount.size(); i++) {
            System.out.println("newNeighboursNeighboursSporeCount[" + i + "] = " + newNeighboursNeighboursSporeCount.get(i));
        }
        System.out.println("Test_AdvancedShootSporesSuccessful is successful: "+((successful)?"true":"false"));
    }

    /**
     * Gombatest sikeres halála
     */
    public void Test_FungusBodyDieSuccessful(){
        System.out.println("Initialization start ->");
        ShootSporesInitFunction();
        System.out.println(">[Fungus].Fungus()");
        Fungus f = (Fungus)ShootSporesInit.get("f");
        System.out.println(">[NarrowTecton].NarrowTecton()");
        NarrowTecton t1 = (NarrowTecton)ShootSporesInit.get("t1");
        System.out.println(">[FungusBody].FungusBody()");
        FungusBody fb = (FungusBody)ShootSporesInit.get("fb");
        System.out.println("<- Initialization end\n");

        System.out.println(">[FungusBody].SetAge(6)");
        fb.SetAge(6);
        boolean oldIsDead = fb.GetIsDead();
        System.out.println(">[FungusBody].Die()");
        fb.Die();
        boolean newIsDead = fb.GetIsDead();
        System.out.println("Test_FungusBodyDieSuccessful is successful: "+((newIsDead != oldIsDead)?"true":"false"));
    }

    /**
     * Gombatest sikeres spóratermelése
     */
    public void Test_ProduceSporeSuccessful(){
        System.out.println("Initialization start ->");
        ShootSporesInitFunction();
        System.out.println(">[Fungus].Fungus()");
        Fungus f = (Fungus)ShootSporesInit.get("f");
        System.out.println(">[NarrowTecton].NarrowTecton()");
        NarrowTecton t1 = (NarrowTecton)ShootSporesInit.get("t1");
        System.out.println(">[FungusBody].FungusBody()");
        FungusBody fb = (FungusBody)ShootSporesInit.get("fb");
        System.out.println("<- Initialization end\n");

        System.out.println(">[FungusBody].SetAge(1)");
        fb.SetAge(1);
        System.out.println(">[FungusBody].SetShotsLeft(5)");
        fb.SetShotsLeft(5);
        System.out.println(">[FungusBody].GetSporeCnt() -> "+fb.GetSporeCount());
        int oldSporeCount = fb.GetSporeCount();
        System.out.println(">[FungusBody].ProduceSpore()");
        fb.ProduceSpore();
        System.out.println(">[FungusBody].GetSporeCnt() -> "+fb.GetSporeCount());
        int newSporeCount = fb.GetSporeCount();
        System.out.println("Test_ProduceSporeSuccessful is successful: "+((newSporeCount>oldSporeCount)?"true":"false"));
    }

    /**
     * Gombatest sikertelen spóratermelése, mert már halott
     */
    public void Test_ProduceSporeUnsuccessful(){
        System.out.println("Initialization start ->");
        ShootSporesInitFunction();
        System.out.println(">[Fungus].Fungus()");
        Fungus f = (Fungus)ShootSporesInit.get("f");
        System.out.println(">[NarrowTecton].NarrowTecton()");
        NarrowTecton t1 = (NarrowTecton)ShootSporesInit.get("t1");
        System.out.println(">[FungusBody].FungusBody()");
        FungusBody fb = (FungusBody)ShootSporesInit.get("fb");
        System.out.println("<- Initialization end\n");

        System.out.println(">[FungusBody].SetAge(5)");
        fb.SetAge(5);
        System.out.println(">[FungusBody].SetShotsLeft(0)");
        fb.SetShotsLeft(0);
        System.out.println(">[FungusBody].GetSporeCnt() -> "+fb.GetSporeCount());
        int oldSporeCount = fb.GetSporeCount();
        System.out.println(">[FungusBody].ProduceSpore()");
        fb.ProduceSpore();
        System.out.println(">[FungusBody].GetSporeCnt() -> "+fb.GetSporeCount());
        int newSporeCount = fb.GetSporeCount();
        System.out.println("Test_ProduceSporeUnsuccessful is successful: "+((newSporeCount==oldSporeCount)?"<true":"<false"));
    }

    /**
     * Gombatest sikertelen halála, mert már halott
     */
    public void Test_FungusBodyDieUnsuccessful(){
        System.out.println("Initialization start ->");
        ShootSporesInitFunction();
        System.out.println(">[Fungus].Fungus()");
        Fungus f = (Fungus)ShootSporesInit.get("f");
        System.out.println(">[NarrowTecton].NarrowTecton()");
        NarrowTecton t1 = (NarrowTecton)ShootSporesInit.get("t1");
        System.out.println(">[FungusBody].FungusBody()");
        FungusBody fb = (FungusBody)ShootSporesInit.get("fb");
        System.out.println("<- Initialization end\n");

        System.out.println(">[FungusBody].SetAge(6)");
        fb.SetAge(6);
        System.out.println(">[FungusBody].SetShotsLeft(0)");
        fb.SetShotsLeft(0);
        boolean oldIsDead = fb.GetIsDead();
        System.out.println(">[FungusBody].Die()");
        fb.Die();
        boolean newIsDead = fb.GetIsDead();
        System.out.println("Test_FungusBodyDieUnsuccessful is successful: "+((newIsDead == oldIsDead)?"<true":"<false"));
    }

    /**
     * Gombatest sikertelen spóra szórása szomszédos tektonokra
     */
    public void Test_BasicShootSporesUnsuccessful(){
        System.out.println("Initialization start ->");
        ShootSporesInitFunction();
        System.out.println(">[Fungus].Fungus()");
        Fungus f = (Fungus)ShootSporesInit.get("f");
        System.out.println(">[NarrowTecton].NarrowTecton()");
        NarrowTecton t1 = (NarrowTecton)ShootSporesInit.get("t1");
        System.out.println(">[FungusBody].FungusBody()");
        FungusBody fb = (FungusBody)ShootSporesInit.get("fb");
        System.out.println("<- Initialization end\n");

        System.out.println(">[FungusBody].SetSporeCount(3)");
        fb.SetSporeCount(3);
        List<Integer> oldNeighboursSporeCount = new ArrayList<>();
        for (Tecton tecton : t1.GetNeighbours()) {
            oldNeighboursSporeCount.add(tecton.GetSpores().size());
        }
        System.out.println(">[FugusBody].ShootSpores()");
        fb.ShootSpores();
        List<Integer> newNeighboursSporeCount = new ArrayList<>();
        for (Tecton tecton : t1.GetNeighbours()) {
            newNeighboursSporeCount.add(tecton.GetSpores().size());
        }
        boolean successful = true;
        for(int i = 0; i < oldNeighboursSporeCount.size(); i++){
            if ((newNeighboursSporeCount.get(i).intValue() == (oldNeighboursSporeCount.get(i).intValue()+1))) {
                successful = false;
                break;
            }
        }
        System.out.println("Test_BasicShootSporesUnsuccessful is successful: "+((successful)?"true":"false"));
    }
    
    /**
     * Gombatest sikertelen spóra szórása szomszédos tektonok szomszédaira
     */
    public void Test_AdvancedShootSporesUnsuccessful(){
        System.out.println("Initialization start ->");
        ShootSporesInitFunction();
        System.out.println(">[Fungus].Fungus()");
        Fungus f = (Fungus)ShootSporesInit.get("f");
        System.out.println(">[NarrowTecton].NarrowTecton()");
        NarrowTecton t1 = (NarrowTecton)ShootSporesInit.get("t1");
        System.out.println(">[FungusBody].FungusBody()");
        FungusBody fb = (FungusBody)ShootSporesInit.get("fb");
        System.out.println("<- Initialization end\n");

        System.out.println("[FungusBody].SetAge(4)");
        fb.SetAge(4);
        System.out.println(">[FungusBody].SetSporeCount(4)");
        fb.SetSporeCount(4);
        List<List<Integer>> oldNeighboursNeighboursSporeCount = new ArrayList<>();
        for (int i = 0; i < t1.GetNeighbours().size(); i++) {
            oldNeighboursNeighboursSporeCount.add(new ArrayList<>());
        }
        for (int i = 0; i < t1.GetNeighbours().size(); i++) {
            for (int j = 0; j < t1.GetNeighbours().get(i).GetNeighbours().size(); j++) {
                if(!t1.GetNeighbours().get(i).GetNeighbours().get(j).equals(t1)){
                    oldNeighboursNeighboursSporeCount.get(i).add(t1.GetNeighbours().get(i).GetNeighbours().get(j).GetSpores().size());
                }
            }
        }
        System.out.println("[FugusBody].ShootSpores()");
        fb.ShootSpores();
        List<List<Integer>> newNeighboursNeighboursSporeCount = new ArrayList<>();
        for (int i = 0; i < t1.GetNeighbours().size(); i++) {
            newNeighboursNeighboursSporeCount.add(new ArrayList<>());
        }
        for (int i = 0; i < t1.GetNeighbours().size(); i++) {
            for (int j = 0; j < t1.GetNeighbours().get(i).GetNeighbours().size(); j++) {
                if(!t1.GetNeighbours().get(i).GetNeighbours().get(j).equals(t1)){
                    newNeighboursNeighboursSporeCount.get(i).add(t1.GetNeighbours().get(i).GetNeighbours().get(j).GetSpores().size());
                }
            }
        }
        boolean successful = true;
        for(int i = 0; i < oldNeighboursNeighboursSporeCount.size(); i++){
            for(int j = 0; j < oldNeighboursNeighboursSporeCount.get(i).size(); j++){
                if ((newNeighboursNeighboursSporeCount.get(i).get(j).intValue() == (oldNeighboursNeighboursSporeCount.get(i).get(j).intValue()+1))) {
                    successful = false;
                    break;
                }
            }
            if(!successful){
                break;
            }
        }
        for (int i = 0; i < oldNeighboursNeighboursSporeCount.size(); i++) {
            System.out.println("oldNeighboursNeighboursSporeCount[" + i + "] = " + oldNeighboursNeighboursSporeCount.get(i));
        }
        for (int i = 0; i < newNeighboursNeighboursSporeCount.size(); i++) {
            System.out.println("newNeighboursNeighboursSporeCount[" + i + "] = " + newNeighboursNeighboursSporeCount.get(i));
        }
        System.out.println("Test_AdvancedShootSporesUnsuccessful is successful: "+((successful)?"true":"false"));
    }
}
