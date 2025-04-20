package fungorium;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class View implements IView {
    private static LinkedHashMap<String, Object> planet = new LinkedHashMap<>();
    GameController controller = new GameController();
    int icCtr = 0;
    int fCtr = 0;
    int hCtr = 0;
    int tCtr = 0;
    int iCtr = 0;
    int sCtr = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            help();
            save("test0_out.txt");
            System.out.println("\n#### Main Menu #####");
            System.out.println("1 \t- Grow Hypha");
            System.out.println("2 \t- Atrophy of Hypha");
            System.out.println("3 \t- Grow FungusBody");
            System.out.println("4 \t- Full Tecton Breaks");
            System.out.println("5 \t- Hypha Absorb");
            System.out.println("6 \t- Insect Move");
            System.out.println("7 \t- Insect Cut");
            System.out.println("8 \t- Insect EatSpore");
            System.out.println("9 \t- ShootSpores");
            System.out.println("10 \t- FungusBody death");
            System.out.println("11 \t- ProduceSpore");
            System.out.println("0 \t- Exit");
            System.out.print("Select an option: ");

            int inp = getInput(scanner);

            if (inp == 0) {
                System.out.println("Exiting...");
                break;
            }

            switch (inp) {
                case 1:
                    Tester tester = new Tester();

                    System.out.println("1 - Grow Hypha Successful");
                    System.out.println("2 - Grow Hypha Unsuccessful");
                    System.out.println("3 - Grow Two Differend Hypha On Wide Tecton Successful");
                    System.out.println("4 - Grow Same Type Hypha On Wide Tecton Unsuccessful");
                    System.out.println("0 - Back to Main Menu");
                    System.out.println("Which test would you like to run? ");
                    int inp2 = getInput(scanner);
                    switch (inp2) {
                        case 1:
                            tester.Test_GrowHyphaSuccessful();
                            waitForEnter();
                            break;
                        case 2:
                            tester.Test_GrowHyphaUnsuccessful();
                            waitForEnter();
                            break;
                        case 3:
                            tester.Test_GrowTwoDifferentHyphaOnWideTectonSuccessful();
                            waitForEnter();
                            break;
                        case 4:
                            tester.Test_GrowSameTypeHyphaOnWideTectonUnsuccessful();
                            waitForEnter();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("There is no such option, please try again.");
                    }
                    break;
                case 2:
                    Tester tester2 = new Tester();
                    tester2.Test_AtrophyOfHypha();
                    waitForEnter();
                    break;
                case 3:
                    Tester tester3 = new Tester();
                    System.out.println("1 - Grow FungusBody On Weak Tecton");
                    System.out.println("2 - Grow FungusBody On FungusBody");
                    System.out.println("3 - Grow FungusBody Not Enough Spores");
                    System.out.println("4 - Grow FungusBody Success");
                    System.out.println("0 - Back to Main Menu");
                    System.out.println("Which test would you like to run? ");
                    int inp3 = getInput(scanner);
                    switch (inp3) {
                        case 1:
                            tester3.Test_GrowFungusBodyOnWeakTecton();
                            waitForEnter();
                            break;
                        case 2:
                            tester3.Test_GrowFungusBodyOnBody();
                            waitForEnter();
                            break;
                        case 3:
                            tester3.Test_GrowFungusBodyNotEnoughSpores();
                            waitForEnter();
                            break;
                        case 4:
                            tester3.Test_GrowFungusBodySuccess();
                            waitForEnter();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("There is no such option, please try again.");
                    }
                    break;
                case 4:
                    Tester tester4 = new Tester();
                    tester4.Test_FullTectonBreaks();
                    waitForEnter();
                    break;
                case 5:
                    Tester tester5 = new Tester();
                    System.out.println("1 - Hypha Absorb Successful");
                    System.out.println("2 - Hypha Absorb Unsuccessful");
                    System.out.println("0 - Back to Main Menu");
                    System.out.println("Which test would you like to run? ");
                    int inp5 = getInput(scanner);
                    switch (inp5) {
                        case 1:
                            tester5.Test_HyphaAbsorbSuccessful();
                            waitForEnter();
                            break;
                        case 2:
                            tester5.Test_HyphaAbsorbUnsuccessful();
                            waitForEnter();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("There is no such option, please try again.");
                    }
                    break;
                case 6:
                    Tester tester6 = new Tester();
                    System.out.println("1 - Move Successful");
                    System.out.println("2 - Move Unsuccessful");
                    System.out.println("0 - Back to Main Menu");
                    System.out.println("Which test would you like to run? ");
                    int inp6 = getInput(scanner);
                    switch (inp6) {
                        case 1:
                            tester6.Test_MoveSuccessful();
                            waitForEnter();
                            break;
                        case 2:
                            tester6.Test_MoveUnsuccessful();
                            waitForEnter();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("There is no such option, please try again.");
                    }
                    break;
                case 7:
                    Tester tester7 = new Tester();
                    System.out.println("1 - Insect Cut Not Able");
                    System.out.println("2 - Insect Cut No Bridge");
                    System.out.println("3 - Insect Cut Successful");
                    System.out.println("0 - Back to Main Menu");
                    System.out.println("Which test would you like to run? ");
                    int inp7 = getInput(scanner);
                    switch (inp7) {
                        case 1:
                            tester7.Test_CutNotAble();
                            waitForEnter();
                            break;
                        case 2:
                            tester7.Test_CutNoBridge();
                            waitForEnter();
                            break;
                        case 3:
                            tester7.Test_CutSuccessful();
                            waitForEnter();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("There is no such option, please try again.");
                    }
                    break;
                case 8:
                    Tester tester8 = new Tester();
                    System.out.println("1 - InsectEat Spore Successful");
                    System.out.println("2 - Insect Eat Spore Unsuccessful");
                    System.out.println("0 - Back to Main Menu");
                    System.out.println("Which test would you like to run? ");
                    int inp8 = getInput(scanner);
                    switch (inp8) {
                        case 1:
                            tester8.Test_EatSporeSuccessful();
                            waitForEnter();
                            break;
                        case 2:
                            tester8.Test_EatSporeUnsuccessful();
                            waitForEnter();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("There is no such option, please try again.");
                    }
                    break;
                case 9:
                    Tester tester9 = new Tester();
                    System.out.println("1 - Basic Shoot Spores Successful");
                    System.out.println("2 - Basic Shoot Spores Unsuccessful");
                    System.out.println("3 - Advanced Shoot Spores Successful");
                    System.out.println("4 - Advanced Shoot Spores Unsuccessful");
                    System.out.println("0 - Back to Main Menu");
                    System.out.println("Which test would you like to run? ");
                    int inp9 = getInput(scanner);
                    switch (inp9) {
                        case 1:
                            tester9.Test_BasicShootSporesSuccessful();
                            waitForEnter();
                            break;
                        case 2:
                            tester9.Test_BasicShootSporesUnsuccessful();
                            waitForEnter();
                            break;
                        case 3:
                            tester9.Test_AdvancedShootSporesSuccessful();
                            waitForEnter();
                            break;
                        case 4:
                            tester9.Test_AdvancedShootSporesUnsuccessful();
                            waitForEnter();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("There is no such option, please try again.");
                    }
                    break;
                case 10:
                    Tester tester10 = new Tester();
                    System.out.println("1 - FungusBody Die Successful");
                    System.out.println("2 - FungusBody Die Unsuccessful");
                    System.out.println("0 - Back to Main Menu");
                    System.out.println("Which test would you like to run? ");
                    int inp10 = getInput(scanner);
                    switch (inp10) {
                        case 1:
                            tester10.Test_FungusBodyDieSuccessful();
                            waitForEnter();
                            break;
                        case 2:
                            tester10.Test_FungusBodyDieUnsuccessful();
                            waitForEnter();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("There is no such option, please try again.");
                    }
                    break;
                case 11:
                    Tester tester11 = new Tester();
                    tester11.Test_ProduceSporeSuccessful();
                    waitForEnter();
                    break;
                default:
                    System.out.println("There is no such option, please try again.");
            }
        }
        scanner.close();
    }

    /**
     * Beolvassa és ellenőrzi a felhasználó által küldött bemenetet.
     * 
     * @param scanner Scanner objektum a felhasználói bemenet olvasásához
     * @return a kiválasztott menüopció sorszáma egész számként (parseInt
     *         segítségével az összes létező karaktert egész számmá castoljuk)
     *         Ha a bemenet nem érvényes szám, akkor a függvény -1-el tér vissza.
     */
    private static int getInput(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Várakozás a felhasználó enter lenyomására
     * (annak érdekében, hogy a kimenet látható maradjon a konzolon)
     */
    private static void waitForEnter() {
        System.console().readLine("Press Enter to continue...");
    }

    /**
     * Getter metódus a planet attribútumhoz.
     * 
     * @return planet
     */
    public Map<String, Object> getPlanet() {
        return planet;
    }

    /**
     * Getter metódus az icCtr attribútumhoz.
     * 
     * @return icCtr
     */
    public int getIcCtr() {
        return icCtr;
    }

    /**
     * Getter metódus az fCtr attribútumhoz.
     * 
     * @return fCtr
     */
    public int getfCtr() {
        return fCtr;
    }

    /**
     * Getter metódus a hCtr attribútumhoz.
     * 
     * @return hCtr
     */
    public int gethCtr() {
        return hCtr;
    }

    /**
     * Getter metódus a tCtr attribútumhoz.
     * 
     * @return tCtr
     */
    public int gettCtr() {
        return tCtr;
    }

    /**
     * Getter metódus az iCtr attribútumhoz.
     * 
     * @return iCtr
     */
    public int getiCtr() {
        return iCtr;
    }

    /**
     * Getter metódus az sCtr attribútumhoz.
     * 
     * @return sCtr
     */
    public int getsCtr() {
        return sCtr;
    }

    public static void help() {
        System.out.println("Rendszer parancsok:");

        // Listázás a parancsok és leírásaik szerint
        System.out.println("/help: Leírás: Rendszer parancsok listázása.");
        System.out.println("/exec: Leírás: Szkript futtatása\nOpciók: [Path]: Szkript elérési útja (relatív).");
        System.out.println(
                "/rand: Leírás: Random generálás engedélyezése / letiltása (paraméter megadása nélkül az aktuális állapot negáltja lesz érvényes).\n"
                        +
                        "Opciók: e, enable: Random generálás engedélyezése (opcionális).\n" +
                        "d, disable: Random generálás tiltása (opcionális).");
        System.out.println(
                "/turns: Leírás: Kör rendszer engedélyezése / letiltása (paraméter megadása nélkül az aktuális állapot negáltja lesz érvényes).\n"
                        +
                        "Opciók: e, enable: Kör rendszer engedélyezése (opcionális).\n" +
                        "d, disable: Kör rendszer letiltása (opcionális).");
        System.out.println(
                "/trigg: Leírás: Esemény manuális kiváltása.\nOpciók: nr: Következő kör\nnp: Következő játékos");
        System.out.println(
                "/addf: Leírás: Gombafaj (Fungus) hozzáadása a játékhoz.\nOpciók: -n [Name]: A gombafaj neve.");
        System.out.println("/addt: Leírás: Tekton (Tecton) hozzáadása a játékhoz.\nOpciók: -n [Name]: Tekton neve.\n" +
                "-t [Type]: Tekton típusa.\n" +
                "n: Szűk tekton (NarrowTecton).\n" +
                "wi: Széles tekton (WideTecton).\n" +
                "v: Életadó tekton (VitalTecton).\n" +
                "we: Gyenge tekton (WeakTecton).\n" +
                "b: Sivár tekton (BarrenTecton).");
        System.out.println("/addfb: Leírás: Gombatest (FungusBody) hozzáadása a játékhoz.\nOpciók: " +
                "-n [Name]: Gombatest neve.\n" +
                "-f [Name]: Gombafaj neve.\n" +
                "-t [Name]: Tekton neve.\n" +
                "-d [y/n]: Gombatest halott-e.\n" +
                "-a [Value]: Gombatest életkora (min. 0).\n" +
                "-dv [y/n]: A gombatest teljes kifejlettség beállítása.\n" +
                "-sc [Value]: Tárolt gombaspóra mennyisége (min. 0).\n" +
                "-sl [Value]: Lövési lehetőség száma (min. 0).");
        System.out.println("/addh: Leírás: Gombafonal (Hypha) hozzáadása a játékhoz.\nOpciók: " +
                "-n [Name]: Gombafonal neve.\n" +
                "-f [Name]: Gombafaj neve.\n" +
                "-ts [Name]: Tekton neve, ahol van vagy ahol kezdődik.\n" +
                "-tn [Name]: Tekton neve, ahol végződik.");
        System.out.println("/adds: Leírás: Gomba spóra (Spore) hozzáadása a játékhoz.\nOpciók: " +
                "-n [Name]: Gomba spóra neve.\n" +
                "-tn [Name]: Tekton neve.\n" +
                "-t [Type]: Gomba spóra típusa.\nsd: Gyorsító spóra (SpeedSpore).\n" +
                "st: Hasító spóra (SplitSpore).\nsw: Lassító spóra (SlowSpore).\n" +
                "dm: Lefegyverző spóra (DisarmSpore).\nsn: Kábító spóra (StunSpore).\n" +
                "-nv [Value]: Tápanyag értéke (min. 0).\n-ed [Value]: Effekt hatásának ideje, ha van (min. 0).");
        System.out.println("/addic: Leírás: Rovar kolónia (InsectColony) hozzáadása a játékhoz.\nOpciók: " +
                "-n [Name]: Rovar kolónia neve.\n-nv [Value]: Eddig összegyűjtött tápanyag mennyisége (min. 0).");
        System.out.println("/addi: Leírás: Rovar (Insect) hozzáadása a játékhoz.\nOpciók: " +
                "-n [Name]: Rovar neve.\n-ic [Name]: Rovar kolónia neve.\n-t [Name]: Tekton neve.\n" +
                "-sd [Value]: Rovar sebessége/lépésszáma (min. 0).\n-ca [y/n]: Vágási képesség megléte.\n" +
                "-et [Value]: Effekt hatásának hátralévő ideje (min. 0).\n-eb [Name]: Gombafaj neve, amelyiknek gombafonala megette.");
        System.out.println("/altt: Leírás: Tekton (Tecton) tulajdonságainak módosítása.\nOpciók: " +
                "-n [Name]: Tekton neve.\n-nh [Name]: Tekton neve, akivel a szomszédságot kialakítjuk.");
        System.out.println("/alth: Leírás: Gombafonal (Hypha) tulajdonságainak módosítása.\nOpciók: " +
                "-n [Name]: Gombafonal neve.\n-nh [Name]: Azonos fajú szomszédos gombafonal neve, akivel létrejön a szomszédság.");
        System.out.println("/lstf: Leírás: Gombafajok és paramétereik listázása.");
        System.out.println("/lstt: Leírás: Tektonok és paramétereik listázása.");
        System.out.println("/lstfb: Leírás: Gombatestek és paramétereik listázása.");
        System.out.println("/lsth: Leírás: Hifák és paramétereik listázása.");
        System.out.println("/lstic: Leírás: Rovar kolóniák és paramétereik listázása.");
        System.out.println("/lsti: Leírás: Rovarok és paramétereik listázása.");
        System.out.println("/save: Leírás: Játék aktuális állapotának mentése.\nOpciók: [Name]: Kimeneti fájl neve.");
        System.out.println(
                "/load: Leírás: Játék visszatöltése mentett állapotból.\nOpciók: [Path]: Mentett fájl elérési útja (relatív).");
        System.out.println("/rst: Leírás: Játék kezdeti beolvasás és végrehajtás nélküli módba állítása.");
        System.out.println("breaktecton: Leírás: Tekton kettétörése.\nOpciók: -t [Name]: Tekton neve.");
        System.out.println(
                "growfungusbody: Leírás: Gombatest növesztése a tektonon.\nOpciók: -f [Name]: Gombafaj neve.\n-t [Name]: Tekton neve.");
        System.out.println("absorbhypha: Leírás: Gombafonál felszívódása a tektonon.\nOpciók: -t [Name]: Tekton neve.");
        System.out.println("producespore: Leírás: Gombatest spóra termelése.\nOpciók: -fb [Name]: Gombatest neve.");
        System.out.println("shootspores: Leírás: Gombatest spóra lövése.\nOpciók: -fb [Name]: Gombatest neve.");
        System.out.println("diefungusbody: Leírás: Gombatest megölése.\nOpciók: -fb [Name]: Gombatest neve.");
        System.out.println(
                "growhypha: Leírás: Gombafonal növesztése.\nOpciók: -f [Name]: Gombafaj neve.\n-ts [Name]: Tekton neve, amelyről növesztjük.\n-tn [Name]: Tekton neve, amelyre növesztünk.");
        System.out.println(
                "atrophyofhypha: Leírás: Gombafonalak elsorvasztása.\nOpciók: -h [Name]: Gombafonal neve (a hálózat egy tagja).");
        System.out.println(
                "eatstunnedinsect: Leírás: Kábult rovar megevése.\nOpciók: -h [Name]: Gombafonal neve.\n-i [Name]: Rovar neve.");
        System.out
                .println("eatspore: Leírás: Gombaspóra evése.\nOpciók: -i [Name]: Rovar neve.\n-s [Name]: Spóra neve.");
        System.out.println(
                "moveinsect: Leírás: Rovar mozgatása.\nOpciók: -i [Name]: Rovar neve.\n-t [Name]: Tekton neve.");
        System.out.println(
                "cuthypha: Leírás: Gombafonal elvágása.\nOpciók: -i [Name]: Rovar neve.\n-h [Name]: Gombafonal neve.");
    }

    public static void save(String filePath){
        // Initialization:
        NarrowTecton T1 = new NarrowTecton();
        NarrowTecton T2 = new NarrowTecton();
        NarrowTecton T3 = new NarrowTecton();
        WideTecton T4 = new WideTecton();
        Fungus F1 = new Fungus();
        Fungus F2 = new Fungus();
        InsectColony IC1 = new InsectColony();
        T1.AddNeighbour(T2);
        T1.AddNeighbour(T3);
        T1.AddNeighbour(T4);
        T2.AddNeighbour(T1);
        T2.AddNeighbour(T4);
        T3.AddNeighbour(T1);
        T3.AddNeighbour(T4);
        T4.AddNeighbour(T1);
        T4.AddNeighbour(T2);
        T4.AddNeighbour(T3);
        FungusBody FB1 = new FungusBody(T1, F1);
        F1.AddBody(FB1);
        T1.SetFungusBody(FB1);
        FungusBody FB2 = new FungusBody(T2, F2);
        F2.AddBody(FB2);
        T2.SetFungusBody(FB2);
        Hypha H1 = new Hypha(new ArrayList<>(), F1, new ArrayList<>(List.of(T1)));
        F1.AddHypha(H1);
        T1.GetHyphas().add(H1);
        Hypha H2 = new Hypha(new ArrayList<>(), F2, new ArrayList<>(List.of(T2)));
        F2.AddHypha(H2);
        T2.GetHyphas().add(H2);
        Hypha H3 = new Hypha(new ArrayList<>(), F2, new ArrayList<>(List.of(T2,T4)));
        F2.AddHypha(H3);
        T4.GetHyphas().add(H3);
        Hypha H4 = new Hypha(new ArrayList<>(), F2, new ArrayList<>(List.of(T4)));
        F2.AddHypha(H4);
        T4.GetHyphas().add(H4);
        H2.AddNeighbour(H3);
        H3.AddNeighbour(H4);
        Spore S1 = new Spore(5, 0, F1, T1);
        Spore S2 = new Spore(5, 0, F1, T1);
        Insect I1 = new Insect(2,true,0,T1);
        I1.SetHostColony(IC1);
        I1.SetEatenBy(F1);
        Insect I2 = new Insect(2,true,0,T1);
        I2.SetHostColony(IC1);
        I2.SetEatenBy(F1);
        planet.put("T1", T1);
        planet.put("T2", T2);
        planet.put("T3", T3);
        planet.put("T4", T4);
        planet.put("F1", F1);
        planet.put("F2", F2);
        planet.put("IC1", IC1);
        planet.put("FB1", FB1);
        planet.put("FB2", FB2);
        planet.put("H1", H1);
        planet.put("H2", H2);
        planet.put("H3", H3);
        planet.put("H4", H4);
        planet.put("S1", S1);
        planet.put("S2", S2);
        planet.put("I1", I1);
        planet.put("I2", I2);
        // Writing
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
        // Tectons:
        for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
            String tectonName = entry1.getKey();
            Object obj1 = entry1.getValue();
            try {
                ITectonView tectonView = (ITectonView) obj1;
                String insectNames = "";
                for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                    Object obj2 = entry2.getValue();
                    try {
                        IInsectView insectView = (IInsectView) obj2;
                        if (((ITectonView)insectView.GetTecton()).equals(tectonView)) {
                            if (insectNames.equals("")) {
                                insectNames=entry2.getKey();
                            }
                            else{
                                insectNames+=","+entry2.getKey();
                            }
                        }
                    } catch (ClassCastException ccex) {
                        // Wrong element, we do nothing and move on.
                    }
                }
                String hyphaNames = "";
                int num = 0;
                for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                    Object obj2 = entry2.getValue();
                    try {
                        IHyphaView hyphaView = (IHyphaView) obj2;
                        if (num==tectonView.GetHyphas().size()) {
                            break;
                        }
                        for (IHyphaView ihView : tectonView.GetHyphas()) {
                            if (hyphaView.equals(ihView)) {
                                if (hyphaNames.equals("")) {
                                    hyphaNames=entry2.getKey();
                                    num++;
                                    break;
                                }
                                else{
                                    hyphaNames+=","+entry2.getKey();
                                    num++;
                                    break;
                                }
                            }
                        }
                    } catch (ClassCastException ccex) {
                        // Wrong element, we do nothing and move on.
                    }
                }
                String sporeNames = "";
                for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                    Object obj2 = entry2.getValue();
                    try {
                        ISporeView sporeView = (ISporeView) obj2;
                        if (((ITectonView)sporeView.GetTecton()).equals(tectonView)) {
                            if (sporeNames.equals("")) {
                                sporeNames=entry2.getKey();
                            }
                            else{
                                sporeNames+=","+entry2.getKey();
                            }
                        }
                    } catch (ClassCastException ccex) {
                        // Wrong element, we do nothing and move on.
                    }
                }
                String neighbourNames = "";
                for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                    Object obj2 = entry2.getValue();
                    boolean isNeighbour = false;
                    try {
                        ITectonView neighbourView = (ITectonView) obj2;
                        for (ITectonView oneNeighbourView : ((ITectonView)neighbourView).GetNeighbours()) {
                            if (oneNeighbourView.equals(tectonView)) {
                                isNeighbour=true;
                                break;
                            }
                        }
                        if (isNeighbour) {
                            if (neighbourNames.equals("")) {
                                neighbourNames=entry2.getKey();
                            }
                            else{
                                neighbourNames+=","+entry2.getKey();
                            }
                        }
                    } catch (ClassCastException ccex) {
                        // Wrong element, we do nothing and move on.
                    }
                }
                String fungusBodyName = "";
                for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                    Object obj2 = entry2.getValue();
                    try {
                        IFungusBodyView fungusBodyView = (IFungusBodyView) obj2;
                        if (((ITectonView)fungusBodyView.GetTecton()).equals(tectonView)) {
                            fungusBodyName=entry2.getKey();
                            break;
                        }
                    } catch (ClassCastException ccex) {
                        // Wrong element, we do nothing and move on.
                    }
                }
                writer.write(tectonView.ToString(tectonName+","+((insectNames.equals(""))?"":"["+insectNames+"]")+","+((hyphaNames.equals(""))?"":"["+hyphaNames+"]")+","+((sporeNames.equals(""))?"":"["+sporeNames+"]")+","+((neighbourNames.equals(""))?"":"["+neighbourNames+"]")+","+fungusBodyName+"\n"));
                System.out.println(tectonView.ToString(tectonName+","+((insectNames.equals(""))?"":"["+insectNames+"]")+","+((hyphaNames.equals(""))?"":"["+hyphaNames+"]")+","+((sporeNames.equals(""))?"":"["+sporeNames+"]")+","+((neighbourNames.equals(""))?"":"["+neighbourNames+"]")+","+fungusBodyName+"\n"));
            } catch (ClassCastException ccex) {
                // Wrong element, we do nothing and move on.
            }
        }
        // Fungus:
        for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
            String fungusName = entry1.getKey();
            Object obj1 = entry1.getValue();
            try {
                IFungusView fungusView = (IFungusView) obj1;
                String fungusBodyNames = "";
                for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                    Object obj2 = entry2.getValue();
                    try {
                        IFungusBodyView fungusBodyView = (IFungusBodyView) obj2;
                        int num = 0;
                        int max = fungusView.GetBodies().size();
                        if (((IFungusView)fungusBodyView.GetHostFungus()).equals(fungusView)) {
                            if (fungusBodyNames.equals("")) {
                                fungusBodyNames=entry2.getKey();
                                num++;
                            }
                            else{
                                fungusBodyNames+=","+entry2.getKey();
                                num++;
                            }
                        }
                        if (num==max) {
                            break;
                        }
                    } catch (ClassCastException ccex) {
                        // Wrong element, we do nothing and move on.
                    }
                }
                String hyphaNames = "";
                for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                    Object obj2 = entry2.getValue();
                    try {
                        IHyphaView hyphaView = (IHyphaView) obj2;
                        int num = 0;
                        int max = fungusView.GetMycelium().size();
                        if (((IFungusView)hyphaView.GetHostFungus()).equals(fungusView)) {
                            if (hyphaNames.equals("")) {
                                hyphaNames=entry2.getKey();
                                num++;
                            }
                            else{
                                hyphaNames+=","+entry2.getKey();
                                num++;
                            }
                        }
                        if (num==max) {
                            break;
                        }
                    } catch (ClassCastException ccex) {
                        // Wrong element, we do nothing and move on.
                    }
                }
                writer.write(fungusView.ToString(fungusName+","+((fungusBodyNames.equals(""))?fungusBodyNames:"["+fungusBodyNames+"]")+","+((hyphaNames.equals(""))?hyphaNames:"["+hyphaNames+"]")+"\n"));
                System.out.println(fungusView.ToString(fungusName+","+((fungusBodyNames.equals(""))?fungusBodyNames:"["+fungusBodyNames+"]")+","+((hyphaNames.equals(""))?hyphaNames:"["+hyphaNames+"]")+"\n"));
            } catch (ClassCastException ccex) {
                // Wrong element, we do nothing and move on.
            }
        }
        // InsectColony:
        for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
            String insectColonyName = entry1.getKey();
            Object obj1 = entry1.getValue();
            try {
                IInsectColonyView insectColonyView = (IInsectColonyView) obj1;
                String insectNames = "";
                for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                    Object obj2 = entry2.getValue();
                    try {
                        IInsectView insectView = (IInsectView) obj2;
                        if (((IInsectColonyView)insectView.GetHostColony()).equals(insectColonyView)) {
                            if (insectNames.equals("")) {
                                insectNames=entry2.getKey();
                            }
                            else{
                                insectNames+=","+entry2.getKey();
                            }
                        }
                    } catch (ClassCastException ccex) {
                        // Wrong element, we do nothing and move on.
                    }
                }
                writer.write(insectColonyView.ToString(insectColonyName+","+((insectNames.equals(""))?insectNames:"["+insectNames+"]")+","+insectColonyView.getNutrition()+"\n"));
                System.out.println(insectColonyView.ToString(insectColonyName+","+((insectNames.equals(""))?insectNames:"["+insectNames+"]")+","+insectColonyView.getNutrition()+"\n"));
            } catch (ClassCastException ccex) {
                // Wrong element, we do nothing and move on.
            }
        }
        // FungusBody:
        for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
            String fungusBodyName = entry1.getKey();
            Object obj1 = entry1.getValue();
            try {
                IFungusBodyView fungusBodyView = (IFungusBodyView)obj1;
                String tectonName = "";
                for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                    Object obj2 = entry2.getValue();
                    try {
                        ITectonView tectonView = (ITectonView) obj2;
                        if (((IFungusBodyView)tectonView.GetFungusBody()).equals(fungusBodyView)) {
                            tectonName=entry2.getKey();
                            break;
                        }
                    } catch (ClassCastException ccex) {
                        // Wrong element, we do nothing and move on.
                    }
                }
                String fungusName = "";
                for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                    Object obj2 = entry2.getValue();
                    try {
                        IFungusView fungusView = (IFungusView) obj2;
                        for (IFungusBodyView fBView : fungusView.GetBodies()) {
                            if (fBView.equals(fungusBodyView)) {
                                fungusName=entry2.getKey();
                                break;
                            }
                        }
                        if (!fungusName.equals("")) {
                            break;
                        }
                    } catch (ClassCastException ccex) {
                        // Wrong element, we do nothing and move on.
                    }
                }
                writer.write(fungusBodyView.ToString(fungusBodyName+","+fungusBodyView.GetIsDeveloped()+","+fungusBodyView.GetAge()+","+fungusBodyView.GetIsDead()+","+fungusBodyView.GetSporeCount()+","+fungusBodyView.GetShotsLeft()+","+tectonName+","+fungusName+"\n"));
                System.out.println(fungusBodyView.ToString(fungusBodyName+","+fungusBodyView.GetIsDeveloped()+","+fungusBodyView.GetAge()+","+fungusBodyView.GetIsDead()+","+fungusBodyView.GetSporeCount()+","+fungusBodyView.GetShotsLeft()+","+tectonName+","+fungusName+"\n"));
            } catch (ClassCastException ccex) {
                // Wrong element, we do nothing and move on.
            }
        }
        // Hypha:
        for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
            String hyphaName = entry1.getKey();
            Object obj1 = entry1.getValue();
            try {
                IHyphaView hyphaView = (IHyphaView) obj1;
                String neighbourNames = "";
                for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                    Object obj2 = entry2.getValue();
                    boolean isNeighbour = false;
                    try {
                        IHyphaView neighbourView = (IHyphaView) obj2;
                        for (IHyphaView oneNeighbourView : ((IHyphaView)neighbourView).GetNeighbours()) {
                            if (oneNeighbourView.equals(hyphaView)) {
                                isNeighbour=true;
                                break;
                            }
                        }
                        if (isNeighbour) {
                            if (neighbourNames.equals("")) {
                                neighbourNames=entry2.getKey();
                            }
                            else{
                                neighbourNames+=","+entry2.getKey();
                            }
                        }
                    } catch (ClassCastException ccex) {
                        // Wrong element, we do nothing and move on.
                    }
                }
                String fungusName = "";
                for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                    Object obj2 = entry2.getValue();
                    try {
                        IFungusView fungusView = (IFungusView) obj2;
                        for (IHyphaView hView : fungusView.GetMycelium()) {
                            if (hView.equals(hyphaView)) {
                                fungusName=entry2.getKey();
                                break;
                            }
                        }
                        if (!fungusName.equals("")) {
                            break;
                        }
                    } catch (ClassCastException ccex) {
                        // Wrong element, we do nothing and move on.
                    }
                }
                String tectonNames = "";
                int num = 0;
                for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                    Object obj2 = entry2.getValue();
                    try {
                        ITectonView tectonView = (ITectonView) obj2;
                        for (ITectonView itView : hyphaView.GetTectons()) {
                            if (tectonView.equals(itView)) {
                                if (tectonNames.equals("")) {
                                    tectonNames=entry2.getKey();
                                    num++;
                                }
                                else{
                                    tectonNames+=","+entry2.getKey();
                                    num++;
                                }
                            }
                        }
                        if (num==hyphaView.GetTectons().size()) {
                            break;
                        }
                    } catch (ClassCastException ccex) {
                        // Wrong element, we do nothing and move on.
                    }
                }
                writer.write(hyphaView.ToString(hyphaName+","+((neighbourNames.equals(""))?neighbourNames:"["+neighbourNames+"]")+","+fungusName+","+((tectonNames.equals(""))?tectonNames:"["+tectonNames+"]")+"\n"));
                System.out.println(hyphaView.ToString(hyphaName+","+((neighbourNames.equals(""))?neighbourNames:"["+neighbourNames+"]")+","+fungusName+","+((tectonNames.equals(""))?tectonNames:"["+tectonNames+"]")+"\n"));
            } catch (ClassCastException ccex) {
                // Wrong element, we do nothing and move on.
            }
        }
        // Spore:
        for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
            String sporeName = entry1.getKey();
            Object obj1 = entry1.getValue();
            try {
                ISporeView sporeView = (ISporeView) obj1;
                String fungusName = "";
                for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                    Object obj2 = entry2.getValue();
                    try {
                        IFungusView fungusView = (IFungusView) obj2;
                        if (((IFungusView)sporeView.GetHostFungus()).equals(fungusView)) {
                            fungusName=entry2.getKey();
                            break;
                        }
                    } catch (ClassCastException ccex) {
                        // Wrong element, we do nothing and move on.
                    }
                }
                String tectonName = "";
                for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                    Object obj2 = entry2.getValue();
                    try {
                        ITectonView tectonView = (ITectonView) obj2;
                        if (((ITectonView)sporeView.GetTecton()).equals(tectonView)) {
                            tectonName=entry2.getKey();
                            break;
                        }
                    } catch (ClassCastException ccex) {
                        // Wrong element, we do nothing and move on.
                    }
                }
                writer.write(sporeView.ToString(sporeName+","+sporeView.GetNutritionValue()+","+sporeView.GetEffectDurr()+","+fungusName+","+tectonName+"\n"));
                System.out.println(sporeView.ToString(sporeName+","+sporeView.GetNutritionValue()+","+sporeView.GetEffectDurr()+","+fungusName+","+tectonName+"\n"));
            } catch (ClassCastException ccex) {
                // Wrong element, we do nothing and move on.
            }
        }
        // Insect:
        for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
            String insectName = entry1.getKey();
            Object obj1 = entry1.getValue();
            try {
                IInsectView insectView = (IInsectView) obj1;
                String insectColonyName = "";
                for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                    Object obj2 = entry2.getValue();
                    try {
                        IInsectColonyView insectColonyView = (IInsectColonyView) obj2;
                        if (((IInsectColonyView)insectView.GetHostColony()).equals(insectColonyView)) {
                            insectColonyName=entry2.getKey();
                            break;
                        }
                    } catch (ClassCastException ccex) {
                        // Wrong element, we do nothing and move on.
                    }
                }
                String tectonName = "";
                for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                    Object obj2 = entry2.getValue();
                    try {
                        ITectonView tectonView = (ITectonView) obj2;
                        if (((ITectonView)insectView.GetTecton()).equals(tectonView)) {
                            tectonName=entry2.getKey();
                            break;
                        }
                    } catch (ClassCastException ccex) {
                        // Wrong element, we do nothing and move on.
                    }
                }
                String fungusName = "";
                for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                    Object obj2 = entry2.getValue();
                    try {
                        IFungusView fungusView = (IFungusView) obj2;
                        if (((IFungusView)insectView.GetEatenBy()).equals(fungusView)) {
                            tectonName=entry2.getKey();
                            break;
                        }
                    } catch (ClassCastException ccex) {
                        // Wrong element, we do nothing and move on.
                    }
                }
                writer.write(insectView.ToString(insectName+","+insectView.GetSpeed()+","+insectView.GetCutAbility()+","+insectView.GetEffectTimeLeft()+","+insectColonyName+","+tectonName+","+((fungusName.equals(""))?"null":fungusName)+"\n"));
                System.out.println(insectView.ToString(insectName+","+insectView.GetSpeed()+","+insectView.GetCutAbility()+","+insectView.GetEffectTimeLeft()+","+insectColonyName+","+tectonName+","+((fungusName.equals(""))?"null":fungusName)+"\n"));
            } catch (ClassCastException ccex) {
                // Wrong element, we do nothing and move on.
            }
        }
        } catch (IOException ioex){
            System.err.println(ioex);
        }
    }
}
