package fungorium;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class View implements IView {
    private static LinkedHashMap<String, Object> planet = new LinkedHashMap<>();
    GameController controller = new GameController(this);
    int icCtr = 0;
    int fCtr = 0;
    int fbCtr = 0;
    int hCtr = 0;
    int tCtr = 0;
    int iCtr = 0;
    int sCtr = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
         Insect I1 = new Insect(2, true, 0, T1);
         I1.SetHostColony(IC1);
         I1.SetEatenBy(F1);
         Insect I2 = new Insect(2, true, 0, T1);
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
        while (true) {
            // help();
            load("test0_in.txt");
            //----------------------------------------------
            save("test0_out.txt");
            addt("/addt -n T5 -t b");
            addf("/addf -n F3");
            addic("/addic -n IC2 -nv 10");
            addfb("/addfb -n FB3 -f F2 -t T4 -d n -a 3 -dv y -sc 10 -sl 6");
            addh("/addh -n H5 -f F1 -ts T1 -tn T4");
            addh("/addh -n H6 -f F1 -ts T4");
            save("test0_out.txt");
            //-----------------------------------------------
            int inp = getInput(scanner);

            if (inp == 0) {
                System.out.println("Exiting...");
                break;
            }

            /* switch (inp) {
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
            } */
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
     * Inkrementer metódus az icCtr attribútumhoz.
     *
     */
    public void IncIcCtr() {
        icCtr++;
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
     * Inkrementer metódus az fCtr attribútumhoz.
     *
     */
    public void IncfCtr() {
        fCtr++;
    }

    /**
     * Getter metódus az fbCtr attribútumhoz.
     *
     * @return fbCtr
     */
    public int getfbCtr() {
        return fbCtr;
    }

    /**
     * Inkrementer metódus az fbCtr attribútumhoz.
     *
     */
    public void IncfbCtr() {
        fbCtr++;
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
     * Inkrementer metódus a hCtr attribútumhoz.
     *
     */
    public void InchCtr() {
        hCtr++;
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
     * Inkrementer metódus a tCtr attribútumhoz.
     *
     */
    public void InctCtr() {
        tCtr++;
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
     * Inkrementer metódus az iCtr attribútumhoz.
     *
     */
    public void InciCtr() {
        iCtr++;
    }

    /**
     * Getter metódus az sCtr attribútumhoz.
     * 
     * @return sCtr
     */
    public int getsCtr() {
        return sCtr;
    }

    /**
     * Inkrementer metódus az sCtr attribútumhoz.
     *
     */
    public void IncsCtr() {
        sCtr++;
    }

    /**
     * /help parancs függvénye, listázza a parancsokat
     */
    public static void help() {
        System.out.println("\nRendszer parancsok:");

        System.out.println("\n/help: Leírás: Rendszer parancsok listázása.");
        System.out.println("\n/exec: Leírás: Szkript futtatása\nOpciók:\n[Path]: Szkript elérési útja (relatív).");
        System.out.println(
                "\n/rand: Leírás: Random generálás engedélyezése / letiltása (paraméter megadása nélkül az aktuális állapot negáltja lesz érvényes).\n"
                        +
                        "Opciók:\ne, enable: Random generálás engedélyezése (opcionális).\n" +
                        "d, disable: Random generálás tiltása (opcionális).");
        System.out.println(
                "\n/turns: Leírás: Kör rendszer engedélyezése / letiltása (paraméter megadása nélkül az aktuális állapot negáltja lesz érvényes).\n"
                        +
                        "Opciók:\ne, enable: Kör rendszer engedélyezése (opcionális).\n" +
                        "d, disable: Kör rendszer letiltása (opcionális).");
        System.out.println(
                "\n/trigg: Leírás: Esemény manuális kiváltása.\nOpciók:\nnr: Következő kör\nnp: Következő játékos");
        System.out.println(
                "\n/addf: Leírás: Gombafaj (Fungus) hozzáadása a játékhoz.\nOpciók:\n-n [Name]: A gombafaj neve.");
        System.out
                .println("\n/addt: Leírás: Tekton (Tecton) hozzáadása a játékhoz.\nOpciók:\n-n [Name]: Tekton neve.\n" +
                        "-t [Type]: Tekton típusa.\n" +
                        "n: Szűk tekton (NarrowTecton).\n" +
                        "wi: Széles tekton (WideTecton).\n" +
                        "v: Életadó tekton (VitalTecton).\n" +
                        "we: Gyenge tekton (WeakTecton).\n" +
                        "b: Sivár tekton (BarrenTecton).");
        System.out.println("\n/addfb: Leírás: Gombatest (FungusBody) hozzáadása a játékhoz.\nOpciók:\n" +
                "-n [Name]: Gombatest neve.\n" +
                "-f [Name]: Gombafaj neve.\n" +
                "-t [Name]: Tekton neve.\n" +
                "-d [y/n]: Gombatest halott-e.\n" +
                "-a [Value]: Gombatest életkora (min. 0).\n" +
                "-dv [y/n]: A gombatest teljes kifejlettség beállítása.\n" +
                "-sc [Value]: Tárolt gombaspóra mennyisége (min. 0).\n" +
                "-sl [Value]: Lövési lehetőség száma (min. 0).");
        System.out.println("\n/addh: Leírás: Gombafonal (Hypha) hozzáadása a játékhoz.\nOpciók:\n" +
                "-n [Name]: Gombafonal neve.\n" +
                "-f [Name]: Gombafaj neve.\n" +
                "-ts [Name]: Tekton neve, ahol van vagy ahol kezdődik.\n" +
                "-tn [Name]: Tekton neve, ahol végződik.");
        System.out.println("\n/adds: Leírás: Gomba spóra (Spore) hozzáadása a játékhoz.\nOpciók:\n" +
                "-n [Name]: Gomba spóra neve.\n" +
                "-tn [Name]: Tekton neve.\n" +
                "-t [Type]: Gomba spóra típusa.\nsd: Gyorsító spóra (SpeedSpore).\n" +
                "st: Hasító spóra (SplitSpore).\nsw: Lassító spóra (SlowSpore).\n" +
                "dm: Lefegyverző spóra (DisarmSpore).\nsn: Kábító spóra (StunSpore).\n" +
                "-nv [Value]: Tápanyag értéke (min. 0).\n-ed [Value]: Effekt hatásának ideje, ha van (min. 0).");
        System.out.println("\n/addic: Leírás: Rovar kolónia (InsectColony) hozzáadása a játékhoz.\nOpciók:\n " +
                "-n [Name]: Rovar kolónia neve.\n-nv [Value]: Eddig összegyűjtött tápanyag mennyisége (min. 0).");
        System.out.println("\n/addi: Leírás: Rovar (Insect) hozzáadása a játékhoz.\nOpciók:\n" +
                "-n [Name]: Rovar neve.\n-ic [Name]: Rovar kolónia neve.\n-t [Name]: Tekton neve.\n" +
                "-sd [Value]: Rovar sebessége/lépésszáma (min. 0).\n-ca [y/n]: Vágási képesség megléte.\n" +
                "-et [Value]: Effekt hatásának hátralévő ideje (min. 0).\n-eb [Name]: Gombafaj neve, amelyiknek gombafonala megette.");
        System.out.println("\n/altt: Leírás: Tekton (Tecton) tulajdonságainak módosítása.\nOpciók:\n" +
                "-n [Name]: Tekton neve.\n-nh [Name]: Tekton neve, akivel a szomszédságot kialakítjuk.");
        System.out.println("\n/alth: Leírás: Gombafonal (Hypha) tulajdonságainak módosítása.\nOpciók:\n" +
                "-n [Name]: Gombafonal neve.\n-nh [Name]: Azonos fajú szomszédos gombafonal neve, akivel létrejön a szomszédság.");
        System.out.println("\n/lstf: Leírás: Gombafajok és paramétereik listázása.");
        System.out.println("\n/lstt: Leírás: Tektonok és paramétereik listázása.");
        System.out.println("\n/lstfb: Leírás: Gombatestek és paramétereik listázása.");
        System.out.println("\n/lsth: Leírás: Hifák és paramétereik listázása.");
        System.out.println("\n/lstic: Leírás: Rovar kolóniák és paramétereik listázása.");
        System.out.println("\n/lsti: Leírás: Rovarok és paramétereik listázása.");
        System.out
                .println("\n/save: Leírás: Játék aktuális állapotának mentése.\nOpciók:\n [Name]: Kimeneti fájl neve.");
        System.out.println(
                "\n/load: Leírás: Játék visszatöltése mentett állapotból.\nOpciók:\n[Path]: Mentett fájl elérési útja (relatív).");
        System.out.println("\n/rst: Leírás: Játék kezdeti beolvasás és végrehajtás nélküli módba állítása.");
        System.out.println("\nbreaktecton: Leírás: Tekton kettétörése.\nOpciók:\n-t [Name]: Tekton neve.");
        System.out.println(
                "\ngrowfungusbody: Leírás: Gombatest növesztése a tektonon.\nOpciók:\n-f [Name]: Gombafaj neve.\n-t [Name]: Tekton neve.");
        System.out
                .println(
                        "\nabsorbhypha: Leírás: Gombafonál felszívódása a tektonon.\nOpciók:\n-t [Name]: Tekton neve.");
        System.out.println("\nproducespore: Leírás: Gombatest spóra termelése.\nOpciók:\n-fb [Name]: Gombatest neve.");
        System.out.println("\nshootspores: Leírás: Gombatest spóra lövése.\nOpciók:\n-fb [Name]: Gombatest neve.");
        System.out.println("\ndiefungusbody: Leírás: Gombatest megölése.\nOpciók:\n-fb [Name]: Gombatest neve.");
        System.out.println(
                "\ngrowhypha: Leírás: Gombafonal növesztése.\nOpciók:\n-f [Name]: Gombafaj neve.\n-ts [Name]: Tekton neve, amelyről növesztjük.\n-tn [Name]: Tekton neve, amelyre növesztünk.");
        System.out.println(
                "\natrophyofhypha: Leírás: Gombafonalak elsorvasztása.\nOpciók:\n-h [Name]: Gombafonal neve (a hálózat egy tagja).");
        System.out.println(
                "\neatstunnedinsect: Leírás: Kábult rovar megevése.\nOpciók:\n-h [Name]: Gombafonal neve.\n-i [Name]: Rovar neve.");
        System.out
                .println(
                        "\neatspore: Leírás: Gombaspóra evése.\nOpciók:\n-i [Name]: Rovar neve.\n-s [Name]: Spóra neve.");
        System.out.println(
                "\nmoveinsect: Leírás: Rovar mozgatása.\nOpciók:\n-i [Name]: Rovar neve.\n-t [Name]: Tekton neve.");
        System.out.println(
                "\ncuthypha: Leírás: Gombafonal elvágása.\nOpciók:\n-i [Name]: Rovar neve.\n-h [Name]: Gombafonal neve.");
    }

    /**
     * Beolvassa a fájlt és inicializálja a játék állapotát.
     *
     * @param filePath
     */
    public static void load(String filePath) {
        Map<String, Tecton> tectonMap = new LinkedHashMap<>();
        Map<String, Fungus> fungusMap = new LinkedHashMap<>();
        Map<String, FungusBody> fungusBodyMap = new LinkedHashMap<>();
        Map<String, InsectColony> insectColonyMap = new LinkedHashMap<>();
        Map<String, Insect> insectMap = new LinkedHashMap<>();
        Map<String, Hypha> hyphaMap = new LinkedHashMap<>();
        Map<String, Spore> sporeMap = new LinkedHashMap<>();
        Map<String, String[]> tectonNeighbors = new LinkedHashMap<>();
        Map<String, String[]> hyphaNeighbors = new LinkedHashMap<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            planet.clear();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                List<String> partsList = new ArrayList<>();
                StringBuilder currentPart = new StringBuilder();
                boolean insideBrackets = false;

                for (char c : line.toCharArray()) {
                    if (c == '[') {
                        insideBrackets = true;
                    } else if (c == ']') {
                        insideBrackets = false;
                    } else if (c == ',' && !insideBrackets) {
                        partsList.add(currentPart.toString());
                        currentPart.setLength(0);
                        continue;
                    }
                    currentPart.append(c);
                }
                partsList.add(currentPart.toString());
                String[] parts = partsList.toArray(new String[0]);
                String type = parts[0];
                String name = parts[1];

                switch (type) {
                    case "NarrowTecton" -> {
                        tectonMap.put(name, new NarrowTecton());
                        tectonNeighbors.put(name, parts[5].replace("[", "").replace("]", "").split(","));
                    }
                    case "WideTecton" -> {
                        tectonMap.put(name, new WideTecton());
                        tectonNeighbors.put(name, parts[5].replace("[", "").replace("]", "").split(","));
                    }
                    case "WeakTecton" -> {
                        tectonMap.put(name, new WeakTecton());
                        tectonNeighbors.put(name, parts[5].replace("[", "").replace("]", "").split(","));
                    }
                    case "BarrenTecton" -> {
                        tectonMap.put(name, new BarrenTecton());
                        tectonNeighbors.put(name, parts[5].replace("[", "").replace("]", "").split(","));
                    }
                    case "Fungus" -> fungusMap.put(name, new Fungus());
                    case "FungusBody" -> {
                        fungusBodyMap.put(name, new FungusBody(
                                tectonMap.get(parts[7]),
                                fungusMap.get(parts[8]),
                                Boolean.parseBoolean(parts[2]),
                                Integer.parseInt(parts[3]),
                                Boolean.parseBoolean(parts[4]),
                                Integer.parseInt(parts[5]),
                                Integer.parseInt(parts[6])));
                        fungusMap.get(parts[8]).AddBody(fungusBodyMap.get(name));
                        tectonMap.get(parts[7]).SetFungusBody(fungusBodyMap.get(name));
                    }
                    case "InsectColony" -> {
                        InsectColony colony = new InsectColony();
                        colony.setNutrition(Integer.parseInt(parts[3]));
                        insectColonyMap.put(name, colony);
                    }
                    case "Insect" -> {
                        insectMap.put(name, new Insect(
                                Integer.parseInt(parts[2]),
                                Boolean.parseBoolean(parts[3]),
                                Integer.parseInt(parts[4]),
                                insectColonyMap.get(parts[5]),
                                tectonMap.get(parts[6]),
                                fungusMap.get(parts[7])));
                        insectColonyMap.get(parts[5]).AddInsect(insectMap.get(name));
                    }
                    case "Hypha" -> {
                        List<Tecton> tectons = new ArrayList<>();
                        String[] tectonNames = parts[4].replace("[", "").replace("]", "").split(",");
                        for (String tectonName : tectonNames) {
                            tectons.add(tectonMap.get(tectonName));
                        }
                        hyphaMap.put(name, new Hypha(
                                new ArrayList<>(),
                                fungusMap.get(parts[3]),
                                tectons));
                        hyphaNeighbors.put(name, parts[2].replace("[", "").replace("]", "").split(","));
                        fungusMap.get(parts[3]).AddHypha(hyphaMap.get(name));
                    }
                    case "Spore" -> sporeMap.put(name, new Spore(
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3]),
                            fungusMap.get(parts[4]),
                            tectonMap.get(parts[5])));
                    default -> System.err.println("Ismeretlen tipus: " + type);
                }

            }

            for (Map.Entry<String, Hypha> entry : hyphaMap.entrySet()) {
                Hypha currentHypha = entry.getValue();
                List<Tecton> hyphaTectons = currentHypha.GetTectons();

                // Ensure the Hypha is only added to the second Tecton (if it exists)
                if (hyphaTectons.size() > 1) {
                    Tecton secondTecton = hyphaTectons.get(1);
                    secondTecton.GetHyphas().add(currentHypha);
                } else if (hyphaTectons.size() == 1) {
                    Tecton firstTecton = hyphaTectons.get(0);
                    firstTecton.GetHyphas().add(currentHypha);
                }
            }

            for (Map.Entry<String, Spore> entry : sporeMap.entrySet()) {
                for (Map.Entry<String, Tecton> tectonEntry : tectonMap.entrySet()) {
                    if (entry.getValue().GetTecton().equals(tectonEntry.getValue())) {
                        tectonEntry.getValue().GetSpores().add(entry.getValue());
                    }
                }
            }

            for (Map.Entry<String, FungusBody> entry : fungusBodyMap.entrySet()) {
                for (Map.Entry<String, Tecton> tectonEntry : tectonMap.entrySet()) {
                    if (entry.getValue().GetTecton().equals(tectonEntry.getValue())) {
                        tectonEntry.getValue().SetFungusBody(entry.getValue());
                    }
                }
            }

            for (Map.Entry<String, Insect> entry : insectMap.entrySet()) {
                for (Map.Entry<String, Tecton> tectonEntry : tectonMap.entrySet()) {
                    if (entry.getValue().GetTecton().equals(tectonEntry.getValue())) {
                        tectonEntry.getValue().AddInsect(entry.getValue());
                    }
                }
            }

            for (Map.Entry<String, Tecton> entry : tectonMap.entrySet()) {
                Tecton currentTecton = entry.getValue();
                String[] neighborNames = tectonNeighbors.get(entry.getKey());

                if (neighborNames != null) {
                    for (String neighborName : neighborNames) {
                        if (!neighborName.isEmpty()) {
                            Tecton neighbor = tectonMap.get(neighborName);

                            if (neighbor != null && !currentTecton.GetNeighbours().contains(neighbor)) {
                                currentTecton.AddNeighbour(neighbor);
                            }

                            if (neighbor != null && !neighbor.GetNeighbours().contains(currentTecton)) {
                                neighbor.AddNeighbour(currentTecton);
                            }
                        }
                    }
                }
            }

            for (Map.Entry<String, Hypha> entry : hyphaMap.entrySet()) {
                Hypha currentHypha = entry.getValue();
                String[] neighborNames = hyphaNeighbors.get(entry.getKey());

                for (String neighborName : neighborNames) {
                    if (!neighborName.isEmpty()) {
                        Hypha neighbor = hyphaMap.get(neighborName);
                        if (neighbor != null && !currentHypha.GetNeighbours().contains(neighbor)) {
                            currentHypha.AddNeighbour(neighbor);
                        }
                        if (neighbor != null && !neighbor.GetNeighbours().contains(currentHypha)) {
                            neighbor.AddNeighbour(currentHypha);
                        }
                    }
                }
            }

            planet.putAll(tectonMap);
            planet.putAll(fungusMap);
            planet.putAll(fungusBodyMap);
            planet.putAll(insectColonyMap);
            planet.putAll(insectMap);
            planet.putAll(hyphaMap);
            planet.putAll(sporeMap);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static void save(String filePath) {
        // Writing
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
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
                            if (((ITectonView) insectView.GetTecton()).equals(tectonView)) {
                                if (insectNames.equals("")) {
                                    insectNames = entry2.getKey();
                                } else {
                                    insectNames += "," + entry2.getKey();
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
                            if (num == tectonView.GetHyphas().size()) {
                                break;
                            }
                            for (IHyphaView ihView : tectonView.GetHyphas()) {
                                if (hyphaView.equals(ihView)) {
                                    if (hyphaNames.equals("")) {
                                        hyphaNames = entry2.getKey();
                                        num++;
                                        break;
                                    } else {
                                        hyphaNames += "," + entry2.getKey();
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
                            if (((ITectonView) sporeView.GetTecton()).equals(tectonView)) {
                                if (sporeNames.equals("")) {
                                    sporeNames = entry2.getKey();
                                } else {
                                    sporeNames += "," + entry2.getKey();
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
                            for (ITectonView oneNeighbourView : ((ITectonView) neighbourView).GetNeighbours()) {
                                if (oneNeighbourView.equals(tectonView)) {
                                    isNeighbour = true;
                                    break;
                                }
                            }
                            if (isNeighbour) {
                                if (neighbourNames.equals("")) {
                                    neighbourNames = entry2.getKey();
                                } else {
                                    neighbourNames += "," + entry2.getKey();
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
                            if (((ITectonView) fungusBodyView.GetTecton()).equals(tectonView)) {
                                fungusBodyName = entry2.getKey();
                                break;
                            }
                        } catch (ClassCastException ccex) {
                            // Wrong element, we do nothing and move on.
                        }
                    }
                    writer.write(tectonView
                            .ToString(tectonName + "," + ((insectNames.equals("")) ? "" : "[" + insectNames + "]") + ","
                                    + ((hyphaNames.equals("")) ? "" : "[" + hyphaNames + "]") + ","
                                    + ((sporeNames.equals("")) ? "" : "[" + sporeNames + "]") + ","
                                    + ((neighbourNames.equals("")) ? "" : "[" + neighbourNames + "]") + ","
                                    + fungusBodyName + "\n"));
                    /*System.out.println(tectonView
                            .ToString(tectonName + "," + ((insectNames.equals("")) ? "" : "[" + insectNames + "]") + ","
                                    + ((hyphaNames.equals("")) ? "" : "[" + hyphaNames + "]") + ","
                                    + ((sporeNames.equals("")) ? "" : "[" + sporeNames + "]") + ","
                                    + ((neighbourNames.equals("")) ? "" : "[" + neighbourNames + "]") + ","
                                    + fungusBodyName + "\n"));*/
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
                            if (((IFungusView) fungusBodyView.GetHostFungus()).equals(fungusView)) {
                                if (fungusBodyNames.equals("")) {
                                    fungusBodyNames = entry2.getKey();
                                    num++;
                                } else {
                                    fungusBodyNames += "," + entry2.getKey();
                                    num++;
                                }
                            }
                            if (num == max) {
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
                            if (((IFungusView) hyphaView.GetHostFungus()).equals(fungusView)) {
                                if (hyphaNames.equals("")) {
                                    hyphaNames = entry2.getKey();
                                    num++;
                                } else {
                                    hyphaNames += "," + entry2.getKey();
                                    num++;
                                }
                            }
                            if (num == max) {
                                break;
                            }
                        } catch (ClassCastException ccex) {
                            // Wrong element, we do nothing and move on.
                        }
                    }
                    writer.write(fungusView.ToString(fungusName + ","
                            + ((fungusBodyNames.equals("")) ? fungusBodyNames : "[" + fungusBodyNames + "]") + ","
                            + ((hyphaNames.equals("")) ? hyphaNames : "[" + hyphaNames + "]") + "\n"));
                    /*System.out.println(fungusView.ToString(fungusName + ","
                            + ((fungusBodyNames.equals("")) ? fungusBodyNames : "[" + fungusBodyNames + "]") + ","
                            + ((hyphaNames.equals("")) ? hyphaNames : "[" + hyphaNames + "]") + "\n"));*/
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
                            if (((IInsectColonyView) insectView.GetHostColony()).equals(insectColonyView)) {
                                if (insectNames.equals("")) {
                                    insectNames = entry2.getKey();
                                } else {
                                    insectNames += "," + entry2.getKey();
                                }
                            }
                        } catch (ClassCastException ccex) {
                            // Wrong element, we do nothing and move on.
                        }
                    }
                    writer.write(insectColonyView.ToString(
                            insectColonyName + "," + ((insectNames.equals("")) ? insectNames : "[" + insectNames + "]")
                                    + "," + insectColonyView.getNutrition() + "\n"));
                    /*System.out.println(insectColonyView.ToString(
                            insectColonyName + "," + ((insectNames.equals("")) ? insectNames : "[" + insectNames + "]")
                                    + "," + insectColonyView.getNutrition() + "\n"));*/
                } catch (ClassCastException ccex) {
                    // Wrong element, we do nothing and move on.
                }
            }
            // FungusBody:
            for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
                String fungusBodyName = entry1.getKey();
                Object obj1 = entry1.getValue();
                try {
                    IFungusBodyView fungusBodyView = (IFungusBodyView) obj1;
                    String tectonName = "";
                    for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                        Object obj2 = entry2.getValue();
                        try {
                            ITectonView tectonView = (ITectonView) obj2;
                            if (((IFungusBodyView) tectonView.GetFungusBody()) != null && ((IFungusBodyView) tectonView.GetFungusBody()).equals(fungusBodyView)) {
                                tectonName = entry2.getKey();
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
                                    fungusName = entry2.getKey();
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
                    writer.write(fungusBodyView.ToString(
                            fungusBodyName + "," + fungusBodyView.GetIsDeveloped() + "," + fungusBodyView.GetAge() + ","
                                    + fungusBodyView.GetIsDead() + "," + fungusBodyView.GetSporeCount() + ","
                                    + fungusBodyView.GetShotsLeft() + "," + tectonName + "," + fungusName + "\n"));
                    /*System.out.println(fungusBodyView.ToString(
                            fungusBodyName + "," + fungusBodyView.GetIsDeveloped() + "," + fungusBodyView.GetAge() + ","
                                    + fungusBodyView.GetIsDead() + "," + fungusBodyView.GetSporeCount() + ","
                                    + fungusBodyView.GetShotsLeft() + "," + tectonName + "," + fungusName + "\n"));*/
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
                            for (IHyphaView oneNeighbourView : ((IHyphaView) neighbourView).GetNeighbours()) {
                                if (oneNeighbourView.equals(hyphaView)) {
                                    isNeighbour = true;
                                    break;
                                }
                            }
                            if (isNeighbour) {
                                if (neighbourNames.equals("")) {
                                    neighbourNames = entry2.getKey();
                                } else {
                                    neighbourNames += "," + entry2.getKey();
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
                                    fungusName = entry2.getKey();
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
                                        tectonNames = entry2.getKey();
                                        num++;
                                    } else {
                                        tectonNames += "," + entry2.getKey();
                                        num++;
                                    }
                                }
                            }
                            if (num == hyphaView.GetTectons().size()) {
                                break;
                            }
                        } catch (ClassCastException ccex) {
                            // Wrong element, we do nothing and move on.
                        }
                    }
                    writer.write(hyphaView.ToString(hyphaName + ","
                            + ((neighbourNames.equals("")) ? neighbourNames : "[" + neighbourNames + "]") + ","
                            + fungusName + "," + ((tectonNames.equals("")) ? tectonNames : "[" + tectonNames + "]")
                            + "\n"));
                    /*System.out.println(hyphaView.ToString(hyphaName + ","
                            + ((neighbourNames.equals("")) ? neighbourNames : "[" + neighbourNames + "]") + ","
                            + fungusName + "," + ((tectonNames.equals("")) ? tectonNames : "[" + tectonNames + "]")
                            + "\n"));*/
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
                            if (((IFungusView) sporeView.GetHostFungus()).equals(fungusView)) {
                                fungusName = entry2.getKey();
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
                            if (((ITectonView) sporeView.GetTecton()).equals(tectonView)) {
                                tectonName = entry2.getKey();
                                break;
                            }
                        } catch (ClassCastException ccex) {
                            // Wrong element, we do nothing and move on.
                        }
                    }
                    writer.write(sporeView.ToString(sporeName + "," + sporeView.GetNutritionValue() + ","
                            + sporeView.GetEffectDurr() + "," + fungusName + "," + tectonName + "\n"));
                    /*System.out.println(sporeView.ToString(sporeName + "," + sporeView.GetNutritionValue() + ","
                            + sporeView.GetEffectDurr() + "," + fungusName + "," + tectonName + "\n"));*/
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
                            if (((IInsectColonyView) insectView.GetHostColony()).equals(insectColonyView)) {
                                insectColonyName = entry2.getKey();
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
                            if (((ITectonView) insectView.GetTecton()).equals(tectonView)) {
                                tectonName = entry2.getKey();
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
                            if (insectView.GetEatenBy() != null
                                    && ((IFungusView) insectView.GetEatenBy()).equals(fungusView)) {
                                tectonName = entry2.getKey();
                                break;
                            }
                        } catch (ClassCastException ccex) {
                            // Wrong element, we do nothing and move on.
                        }
                    }
                    writer.write(insectView.ToString(insectName + "," + insectView.GetSpeed() + ","
                            + insectView.GetCutAbility() + "," + insectView.GetEffectTimeLeft() + "," + insectColonyName
                            + "," + tectonName + "," + ((fungusName.equals("")) ? "null" : fungusName) + "\n"));
                    /*System.out.println(insectView.ToString(insectName + "," + insectView.GetSpeed() + ","
                            + insectView.GetCutAbility() + "," + insectView.GetEffectTimeLeft() + "," + insectColonyName
                            + "," + tectonName + "," + ((fungusName.equals("")) ? "null" : fungusName) + "\n"));*/
                } catch (ClassCastException ccex) {
                    // Wrong element, we do nothing and move on.
                }
            }
        } catch (IOException ioex) {
            System.err.println(ioex);
        }
    }

    public static void addt(String command){
        String[] parts = command.trim().split("\\s+");
        if (!parts[0].equals("/addt")) {
            System.out.println("Rossz függvényhívás!");
            return;
        }
        LinkedHashMap<String, String> args = new LinkedHashMap<>();
        for (int i = 1; i < parts.length; i+=2) {
            if (i+1<parts.length && parts[i].startsWith("-")) {
                args.put(parts[i].substring(1), parts[i+1]);
            }
            else{
                System.out.println("Hibás argumentum formátum: "+parts[i]);
                return;
            }
        }
        if (!args.containsKey("n")) {
            System.out.println("Hiányzik a -n [Name] argumentum.");
            return;
        }
        if (args.get("n").equals("") || args.get("n")==null) {
            System.out.println("Nincs név megadva (-n): "+args.get("n"));
            return;
        }
        for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
            String Name = entry1.getKey();
            if (Name.equals(args.get("n"))) {
                System.out.println("Ez a név már foglalt (-n): "+args.get("n"));
                return;
            }
        }
        String name = args.get("n");
        LinkedList<String> typeList = new LinkedList<>(List.of("n","wi","v","we","b"));
        if (args.containsKey("t") && (args.get("t").equals("") || args.get("t")==null || !typeList.contains(args.get("t")))) {
            System.out.println("Nincs tekton típus megadva vagy hibás (-t): "+args.get("t"));
            return;
        }
        String tectonType = args.containsKey("t") ? args.get("t") : "n";
        switch (tectonType) {
            case "n":
                NarrowTecton actTectonN = new NarrowTecton();
                planet.put(name, actTectonN);
                break;
            case "wi":
                WideTecton actTectonWi = new WideTecton();
                planet.put(name, actTectonWi);
                break;
            case "v":
                VitalTecton actTectonV = new VitalTecton();
                planet.put(name, actTectonV);
                break;
            case "we":
                WeakTecton actTectonWe = new WeakTecton();
                planet.put(name, actTectonWe);
                break;
            case "b":
                BarrenTecton actTectonB = new BarrenTecton();
                planet.put(name, actTectonB);
                break;
            default:
                break;
        }
        System.out.println("Sikeres tekton létrehozás "+name+" néven!");
    }

    public static void addf(String command){
        String[] parts = command.trim().split("\\s+");
        if (!parts[0].equals("/addf")) {
            System.out.println("Rossz függvényhívás!");
            return;
        }
        LinkedHashMap<String, String> args = new LinkedHashMap<>();
        for (int i = 1; i < parts.length; i+=2) {
            if (i+1<parts.length && parts[i].startsWith("-")) {
                args.put(parts[i].substring(1), parts[i+1]);
            }
            else{
                System.out.println("Hibás argumentum formátum: "+parts[i]);
                return;
            }
        }
        if (!args.containsKey("n")) {
            System.out.println("Hiányzik a -n [Name] argumentum.");
            return;
        }
        if (args.get("n").equals("") || args.get("n")==null) {
            System.out.println("Nincs név megadva (-n): "+args.get("n"));
            return;
        }
        for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
            String Name = entry1.getKey();
            if (Name.equals(args.get("n"))) {
                System.out.println("Ez a név már foglalt (-n): "+args.get("n"));
                return;
            }
        }
        String name = args.get("n");
        Fungus actFungus = new Fungus();
        planet.put(name, actFungus);
        System.out.println("Sikeres gombafaj létrehozás "+name+" néven!");
    }

    public static void addic(String command){
        String[] parts = command.trim().split("\\s+");
        if (!parts[0].equals("/addic")) {
            System.out.println("Rossz függvényhívás!");
            return;
        }
        LinkedHashMap<String, String> args = new LinkedHashMap<>();
        for (int i = 1; i < parts.length; i+=2) {
            if (i+1<parts.length && parts[i].startsWith("-")) {
                args.put(parts[i].substring(1), parts[i+1]);
            }
            else{
                System.out.println("Hibás argumentum formátum: "+parts[i]);
                return;
            }
        }
        if (!args.containsKey("n")) {
            System.out.println("Hiányzik a -n [Name] argumentum.");
            return;
        }
        if (args.get("n").equals("") || args.get("n")==null) {
            System.out.println("Nincs név megadva (-n): "+args.get("n"));
            return;
        }
        for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
            String Name = entry1.getKey();
            if (Name.equals(args.get("n"))) {
                System.out.println("Ez a név már foglalt (-n): "+args.get("n"));
                return;
            }
        }
        String name = args.get("n");
        if (args.containsKey("nv") && (args.get("nv").equals("") || args.get("nv")==null || parseIntNumberMinZero(args.get("nv")) ==-1)) {
            System.out.println("Nincs össz tápanyag mennyiség megadva vagy hibás (-nv): "+args.get("nv"));
            return;
        }
        int nv = args.containsKey("nv") ? parseIntNumberMinZero(args.get("nv")) : 0;
        InsectColony actInsectColony = new InsectColony();
        actInsectColony.addNutrition(nv);
        planet.put(name, actInsectColony);
        System.out.println("Sikeres rovar kolónia létrehozás "+name+" néven!");
    }

    public static void addfb(String command){
        String[] parts = command.trim().split("\\s+");
        if (!parts[0].equals("/addfb")) {
            System.out.println("Rossz függvényhívás!");
            return;
        }
        LinkedHashMap<String, String> args = new LinkedHashMap<>();
        for (int i = 1; i < parts.length; i+=2) {
            if (i+1<parts.length && parts[i].startsWith("-")) {
                args.put(parts[i].substring(1), parts[i+1]);
            }
            else{
                System.out.println("Hibás argumentum formátum: "+parts[i]);
                return;
            }
        }
        if (!args.containsKey("n")) {
            System.out.println("Hiányzik a -n [Name] argumentum.");
            return;
        }
        if (!args.containsKey("f")) {
            System.out.println("Hiányzik a -f [Name] argumentum.");
            return;
        }
        if (!args.containsKey("t")) {
            System.out.println("Hiányzik a -t [Name] argumentum.");
            return;
        }
        if (args.get("n").equals("") || args.get("n")==null) {
            System.out.println("Nincs név megadva (-n): "+args.get("n"));
            return;
        }
        for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
            String Name = entry1.getKey();
            if (Name.equals(args.get("n"))) {
                System.out.println("Ez a név már foglalt (-n): "+args.get("n"));
                return;
            }
        }
        String name = args.get("n");
        if (((Fungus)planet.get(args.get("f")))==null) {
            System.out.println("Nincs ilyen gombafaj (-f): "+args.get("f"));
            return;
        }
        Fungus fungusType = null;
        try {
            fungusType = (Fungus)planet.get(args.get("f"));
        } catch (ClassCastException ccex) {
            System.out.println("Nincs ilyen gombafaj (-f): "+args.get("f"));
            return;
        }
        fungusType = (Fungus)planet.get(args.get("f"));
        if (planet.get(args.get("t"))==null) {
            System.out.println("Nincs ilyen tekton (-t): "+args.get("t"));
            return;
        }
        Tecton tecton = null;
        boolean castError = false;
        try {
            try {
                tecton = (BarrenTecton)planet.get(args.get("t"));
            } catch (ClassCastException ccex) {
                castError = true;
            }
            if (castError) {
                try {
                    tecton = (NarrowTecton)planet.get(args.get("t"));
                    castError = false;
                } catch (ClassCastException ccex) {
                    castError = true;
                }
            }
            if (castError) {
                try {
                    tecton = (VitalTecton)planet.get(args.get("t"));
                    castError = false;
                } catch (ClassCastException ccex) {
                    castError = true;
                }
            }
            if (castError) {
                try {
                    tecton = (WeakTecton)planet.get(args.get("t"));
                    castError = false;
                    System.out.println("Gyenge tektonra a gombatest növesztés nem lehetséges!");
                    return;
                } catch (ClassCastException ccex) {
                    castError = true;
                }
            }
            if (castError) {
                try {
                    tecton = (WideTecton)planet.get(args.get("t"));
                    castError = false;
                } catch (ClassCastException ccex) {
                    castError = true;
                }
            }
            if (castError) tecton = (BarrenTecton)planet.get(args.get("t"));
        } catch (ClassCastException ccex) {
            System.out.println("Nincs ilyen tekton (-t): "+args.get("t"));
            return;
        }
        if (tecton.GetFungusBody()!=null) {
            System.out.println("Már van gombatest a tektonon!");
            return;
        }
        if (args.containsKey("d") && (args.get("d").equals("") || args.get("d")==null)) {
            System.out.println("Nincs állapot megadva (-d): "+args.get("d"));
            return;
        }
        boolean isDead = args.containsKey("d") && args.get("d").equalsIgnoreCase("y");
        if (args.containsKey("a") && (args.get("a").equals("") || args.get("a")==null || parseIntNumberMinZero(args.get("a")) ==-1)) {
            System.out.println("Nincs életkor megadva vagy hibás (-a): "+args.get("a"));
            return;
        }
        int age = args.containsKey("a") ? parseIntNumberMinZero(args.get("a")) : 0;
        if (args.containsKey("dv") && (args.get("dv").equals("") || args.get("dv")==null)) {
            System.out.println("Nincs állapot megadva (-dv): "+args.get("dv"));
            return;
        }
        boolean fullyDeveloped = args.containsKey("dv") && args.get("dv").equalsIgnoreCase("y");
        if (args.containsKey("sc") && (args.get("sc").equals("") || args.get("sc")==null || parseIntNumberMinZero(args.get("sc")) ==-1)) {
            System.out.println("Nincs spóra szám megadva vagy hibás (-sc): "+args.get("sc"));
            return;
        }
        int sporeCount = args.containsKey("sc") ? parseIntNumberMinZero(args.get("sc")) : 0;
        if (args.containsKey("sl") && (args.get("sl").equals("") || args.get("sl")==null || parseIntNumberMinZero(args.get("sl")) ==-1)) {
            System.out.println("Nincs maximális lövés szám megadva vagy hibás (-sl): "+args.get("sl"));
            return;
        }
        int shotLimit = args.containsKey("sl") ? parseIntNumberMinZero(args.get("sl")) : 0;
        FungusBody actFungusBody = new FungusBody(tecton, fungusType, fullyDeveloped, age, isDead, sporeCount, shotLimit);
        fungusType.AddBody(actFungusBody);
        tecton.SetFungusBody(actFungusBody);
        planet.put(name, actFungusBody);
        System.out.println("Sikeres gombatest létrehozás "+name+" néven!");
    }

    public static void addh(String command){
        String[] parts = command.trim().split("\\s+");
        if (!parts[0].equals("/addh")) {
            System.out.println("Rossz függvényhívás!");
            return;
        }
        LinkedHashMap<String, String> args = new LinkedHashMap<>();
        for (int i = 1; i < parts.length; i+=2) {
            if (i+1<parts.length && parts[i].startsWith("-")) {
                args.put(parts[i].substring(1), parts[i+1]);
            }
            else{
                System.out.println("Hibás argumentum formátum: "+parts[i]);
                return;
            }
        }
        if (!args.containsKey("n")) {
            System.out.println("Hiányzik a -n [Name] argumentum.");
            return;
        }
        if (!args.containsKey("f")) {
            System.out.println("Hiányzik a -f [Name] argumentum.");
            return;
        }
        if (!args.containsKey("ts")) {
            System.out.println("Hiányzik a -ts [Name] argumentum.");
            return;
        }
        if (args.get("n").equals("") || args.get("n")==null) {
            System.out.println("Nincs név megadva (-n): "+args.get("n"));
            return;
        }
        for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
            String Name = entry1.getKey();
            if (Name.equals(args.get("n"))) {
                System.out.println("Ez a név már foglalt (-n): "+args.get("n"));
                return;
            }
        }
        String name = args.get("n");
        if (((Fungus)planet.get(args.get("f")))==null) {
            System.out.println("Nincs ilyen gombafaj (-f): "+args.get("f"));
            return;
        }
        Fungus fungusType = null;
        try {
            fungusType = (Fungus)planet.get(args.get("f"));
        } catch (ClassCastException ccex) {
            System.out.println("Nincs ilyen gombafaj (-f): "+args.get("f"));
            return;
        }
        fungusType = (Fungus)planet.get(args.get("f"));
        if (planet.get(args.get("ts"))==null) {
            System.out.println("Nincs ilyen tekton (-ts): "+args.get("ts"));
            return;
        }
        Tecton tectonTS = null;
        boolean maxLimitTS = true;
        boolean castError = false;
        try {
            try {
                tectonTS = (BarrenTecton)planet.get(args.get("ts"));
            } catch (ClassCastException ccex) {
                castError = true;
            }
            if (castError) {
                try {
                    tectonTS = (NarrowTecton)planet.get(args.get("ts"));
                    castError = false;
                } catch (ClassCastException ccex) {
                    castError = true;
                }
            }
            if (castError) {
                try {
                    tectonTS = (VitalTecton)planet.get(args.get("ts"));
                    castError = false;
                } catch (ClassCastException ccex) {
                    castError = true;
                }
            }
            if (castError) {
                try {
                    tectonTS = (WeakTecton)planet.get(args.get("ts"));
                    castError = false;
                } catch (ClassCastException ccex) {
                    castError = true;
                }
            }
            if (castError) {
                try {
                    tectonTS = (WideTecton)planet.get(args.get("ts"));
                    maxLimitTS = false;
                    castError = false;
                } catch (ClassCastException ccex) {
                    castError = true;
                }
            }
            if (castError) tectonTS = (BarrenTecton)planet.get(args.get("ts"));
        } catch (ClassCastException ccex) {
            System.out.println("Nincs ilyen tekton (-ts): "+args.get("ts"));
            return;
        }
        if (args.containsKey("tn")) {
            if ((args.get("tn").equals("") || args.get("tn")==null)) {
                System.out.println("Nincs tekton megadva (-tn): "+args.get("tn"));
                return;
            }
            if (planet.get(args.get("ts"))==null) {
                System.out.println("Nincs ilyen tekton (-ts): "+args.get("ts"));
                return;
            }
            Tecton tectonTN = null;
            boolean maxLimitTN = true;
            castError = false;
            try {
                try {
                    tectonTN = (BarrenTecton)planet.get(args.get("tn"));
                } catch (ClassCastException ccex) {
                    castError = true;
                }
                if (castError) {
                    try {
                        tectonTN = (NarrowTecton)planet.get(args.get("tn"));
                        castError = false;
                    } catch (ClassCastException ccex) {
                        castError = true;
                    }
                }
                if (castError) {
                    try {
                        tectonTN = (VitalTecton)planet.get(args.get("tn"));
                        castError = false;
                    } catch (ClassCastException ccex) {
                        castError = true;
                    }
                }
                if (castError) {
                    try {
                        tectonTN = (WeakTecton)planet.get(args.get("tn"));
                        castError = false;
                    } catch (ClassCastException ccex) {
                        castError = true;
                    }
                }
                if (castError) {
                    try {
                        tectonTN = (WideTecton)planet.get(args.get("tn"));
                        maxLimitTN = false;
                        castError = false;
                    } catch (ClassCastException ccex) {
                        castError = true;
                    }
                }
                if (castError) tectonTN = (BarrenTecton)planet.get(args.get("tn"));
            } catch (ClassCastException ccex) {
                System.out.println("Nincs ilyen tekton (-tn): "+args.get("tn"));
                return;
            }
            if (!tectonTS.GetNeighbours().contains(tectonTN)) {
                System.out.println("A(z) "+args.get("ts")+" és "+args.get("tn")+" tektonok nem szomszédosak!");
                return;
            }
            if (maxLimitTN && tectonTN.GetHyphas().size()>=1) {
                System.out.println("Nincs hely a tektonon (-tn): "+args.get("tn"));
                return;
            }
            for (Hypha hypha : tectonTN.GetHyphas()) {
                if (hypha.GetHostFungus().equals(fungusType)) {
                    System.out.println("Ennek a gombafajnak már van fonala a "+args.get("tn")+" tektonon!");
                    return;
                }
            }
            Hypha actHypha = new Hypha(new ArrayList<>(), fungusType, new ArrayList<>(List.of(tectonTS, tectonTN)));
            fungusType.AddHypha(actHypha);
            tectonTN.GetHyphas().add(actHypha);
            planet.put(name, actHypha);
            System.out.println("Sikeres gombafonál létrehozás "+name+" néven!");
            return;
        }
        if (maxLimitTS && tectonTS.GetHyphas().size()>=1) {
            System.out.println("Nincs hely a tektonon (-ts): "+args.get("ts"));
            return;
        }
        for (Hypha hypha : tectonTS.GetHyphas()) {
            if (hypha.GetHostFungus().equals(fungusType) && hypha.GetTectons().size()==1) {
                System.out.println("Ennek a gombafajnak már van fonala a "+args.get("ts")+" tektonon!");
                return;
            }
        }
        Hypha actHypha = new Hypha(new ArrayList<>(), fungusType, new ArrayList<>(List.of(tectonTS)));
        fungusType.AddHypha(actHypha);
        tectonTS.GetHyphas().add(actHypha);
        planet.put(name, actHypha);
        System.out.println("Sikeres gombafonál létrehozás "+name+" néven!");
    }

    public static void adds(){
        //
    }

    public static void addi(){
        //
    }

    public static void altt(){
        //
    }

    public static void alth(){
        //
    }

    private static int parseIntNumberMinZero(String value){
        if (value == null || value.isEmpty() || !value.matches("-?\\d+")) {
            return -1;
        }
        else{
            int result = Integer.parseInt(value);
            if(result < 0){
                return 0;
            }
            else{
                return result;
            }
        }
    }
}
