package fungorium;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLightLaf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class View implements IView {
    private LinkedHashMap<String, Object> planet = new LinkedHashMap<>();
    private GameController controller;
    private GameGUI gui;
    private int icCtr = 0;
    private int fCtr = 0;
    private int fbCtr = 0;
    private int hCtr = 0;
    private int tCtr = 0;
    private int iCtr = 0;
    private int sCtr = 0;

    public View() {
        if (controller == null && gui == null) {
            controller = new GameController(this);
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            SwingUtilities.invokeLater(() -> {
                gui = new GameGUI(this);
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setVisible(true);
            });
        }
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        View view = new View();
        System.out.println("Fungorium game (prototype):");
        System.out.println("(Segitseg: /help)");
        Scanner scanner = new Scanner(System.in);
        System.out.print(">");
        while (true) {
            String line = scanner.nextLine();
            view.menuExec(line);
        }
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
    public void help() {
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
                "-f [Name]: Gombafaj neve.\n" +
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
        System.out.println("\n/exit: Leírás: Játékból kilépés.");
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
     * @param fileName
     */
    public boolean load(String fileName) {
        // Ellenőrizzük, hogy a fájl .txt kiterjesztésű-e
        if (!fileName.endsWith(".txt")) {
            System.err.println("#A megadott fajl nem .txt kiterjesztesu: " + fileName);
            return false;
        }

        // A fungorium mappa elérési útja
        File folder = new File("fungorium/out");

        // Ellenőrizzük, hogy a mappa létezik-e és könyvtár-e
        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("#A fungorium/out mappa nem találhato vagy nem mappa: " + folder.getPath());
            return false;
        }

        // A megadott fájl elérési útja
        File file = new File(folder, fileName);

        // Ellenőrizzük, hogy a fájl létezik-e
        if (!file.exists()) {
            System.err.println("#A fajl nem talalható: " + file.getPath());
            return false;
        }

        Map<String, Tecton> tectonMap = new LinkedHashMap<>();
        Map<String, Fungus> fungusMap = new LinkedHashMap<>();
        Map<String, FungusBody> fungusBodyMap = new LinkedHashMap<>();
        Map<String, InsectColony> insectColonyMap = new LinkedHashMap<>();
        Map<String, Insect> insectMap = new LinkedHashMap<>();
        Map<String, Hypha> hyphaMap = new LinkedHashMap<>();
        Map<String, Spore> sporeMap = new LinkedHashMap<>();
        Map<String, String[]> tectonNeighbors = new LinkedHashMap<>();
        Map<String, String[]> hyphaNeighbors = new LinkedHashMap<>();

        // Fájl tartalmának beolvasása BufferedReader és FileReader segítségével
        try (Scanner scanner = new Scanner(file)) {
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
                    case "VitalTecton" -> {
                        tectonMap.put(name, new VitalTecton());
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
                    case "SpeedSpore" -> sporeMap.put(name, new SpeedSpore(
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3]),
                            fungusMap.get(parts[4]),
                            tectonMap.get(parts[5])));
                    case "SplitSpore" -> sporeMap.put(name, new SplitSpore(
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3]),
                            fungusMap.get(parts[4]),
                            tectonMap.get(parts[5])));
                    case "SlowSpore" -> sporeMap.put(name, new SlowSpore(
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3]),
                            fungusMap.get(parts[4]),
                            tectonMap.get(parts[5])));
                    case "DisarmSpore" -> sporeMap.put(name, new DisarmSpore(
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3]),
                            fungusMap.get(parts[4]),
                            tectonMap.get(parts[5])));
                    case "StunSpore" -> sporeMap.put(name, new StunSpore(
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
            return false;
        }
        return true;
    }

    public boolean save(String fileName) {
        // Ellenőrizzük, hogy a fájlnév .txt kiterjesztésű-e
        if (!fileName.endsWith(".txt")) {
            System.err.println("#A megadott fajl nem .txt kiterjesztesu: " + fileName);
            return false;
        }

        // A fungorium/array mappa elérési útja
        File folder = new File("fungorium/out");

        // Ellenőrizzük, hogy a mappa létezik-e, ha nem, létrehozzuk
        if (!folder.exists()) {
            folder.mkdirs(); // Létrehozza a fungorium/array mappát, ha nem létezik
        } else if (!folder.isDirectory()) {
            System.err.println("#A fungorium/out nem mappa: " + folder.getPath());
            return false;
        }

        // A kimeneti fájl elérési útja
        File file = new File(folder, fileName);

        // Fájl tartalmának kiírása BufferedWriter és FileWriter segítségével
        // Writing
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
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
                    for (ITectonView oneNeighbourView : tectonView.GetNeighbours()) {
                        for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                            Object obj2 = entry2.getValue();
                            boolean isNeighbour = false;
                            try {
                                ITectonView neighbourView = (ITectonView) obj2;
                                if (oneNeighbourView.equals(neighbourView)) {
                                    isNeighbour = true;
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
                    /*for (ITectonView oneNeighbourView : tectonView.GetNeighbours()) {
                        for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                            Object obj2 = entry2.getValue();
                            boolean isNeighbour = false;
                            try {
                                ITectonView neighbourView = (ITectonView) obj2;
                                if (oneNeighbourView.equals(neighbourView)) {
                                    isNeighbour = true;
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
                    }*/
                    for (ITectonView oneTectonView : hyphaView.GetTectons()) {
                        for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                            Object obj2 = entry2.getValue();
                            try {
                                ITectonView tectonView = (ITectonView) obj2;
                                if (oneTectonView.equals(tectonView)) {
                                    if (tectonNames.equals("")) {
                                        tectonNames = entry2.getKey();
                                        break;
                                    } else {
                                        tectonNames += "," + entry2.getKey();
                                        break;
                                    }
                                }
                            } catch (ClassCastException ccex) {
                                // Wrong element, we do nothing and move on.
                            }
                        }
                    }
                    writer.write(hyphaView.ToString(hyphaName + ","
                            + ((neighbourNames.equals("")) ? neighbourNames : "[" + neighbourNames + "]") + ","
                            + fungusName + "," + ((tectonNames.equals("")) ? tectonNames : "[" + tectonNames + "]")
                            + "\n"));
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
                                fungusName = entry2.getKey();
                                break;
                            }
                        } catch (ClassCastException ccex) {
                            // Wrong element, we do nothing and move on.
                        }
                    }
                    writer.write(insectView.ToString(insectName + "," + insectView.GetSpeed() + ","
                            + insectView.GetCutAbility() + "," + insectView.GetEffectTimeLeft() + "," + insectColonyName
                            + "," + tectonName + "," + ((fungusName.equals("")) ? "null" : fungusName) + "\n"));
                } catch (ClassCastException ccex) {
                    // Wrong element, we do nothing and move on.
                }
            }
        } catch (IOException ioex) {
            System.out.println(ioex);
            return false;
        }
        System.out.println("#Sikeres fajlba iras.");
        return true;
    }

    public boolean addt(String name, String tectonType){
        for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
            String Name = entry1.getKey();
            if (Name.equals(name)) {
                System.out.println("#Ez a nev mar foglalt: "+name);
                return false;
            }
        }
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
                System.out.println("#Hibas tipus: "+tectonType);
                return false;
        }
        tCtr++;
        System.out.println("#Sikeres tekton letrehozas "+name+" neven!");
        return true;
    }

    public boolean addf(String name){
        for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
            String Name = entry1.getKey();
            if (Name.equals(name)) {
                System.out.println("#Ez a nev mar foglalt: "+name);
                return false;
            }
        }
        Fungus actFungus = new Fungus();
        planet.put(name, actFungus);
        controller.playersInit();
        fCtr++;
        System.out.println("#Sikeres gombafaj letrehozas "+name+" neven!");
        return true;
    }

    public boolean addic(String name, int nutritionValue){
        for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
            String Name = entry1.getKey();
            if (Name.equals(name)) {
                System.out.println("#Ez a nev mar foglalt: "+name);
                return false;
            }
        }
        if (nutritionValue<0) {
            System.out.println("#Osszegyujtott tapanyag mennyiseg erteke hibas: "+nutritionValue);
            return false;
        }
        InsectColony actInsectColony = new InsectColony();
        actInsectColony.addNutrition(nutritionValue);
        planet.put(name, actInsectColony);
        controller.playersInit();
        icCtr++;
        System.out.println("#Sikeres rovar kolonia letrehozas "+name+" neven!");
        return true;
    }

    public boolean addfb(String name, Tecton tecton, Fungus actFungus, boolean fullyDeveloped, int age, boolean isDead, int sporeCount, int shotLimit){
        for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
            String Name = entry1.getKey();
            if (Name.equals(name)) {
                System.out.println("#Ez a nev mar foglalt: "+name);
                return false;
            }
        }
        if (age < 0 || sporeCount < 0 || shotLimit < 0) {
            System.out.println("#Hibas szam ertek!");
            return false;
        }
        if (tecton.GetFungusBody()!=null) {
            System.out.println("#Mar van gombatest a tektonon!");
            return false;
        }
        FungusBody actFungusBody = new FungusBody(tecton, actFungus, fullyDeveloped, age, isDead, sporeCount, shotLimit);
        actFungus.AddBody(actFungusBody);
        tecton.SetFungusBody(actFungusBody);
        planet.put(name, actFungusBody);
        fbCtr++;
        System.out.println("#Sikeres gombatest letrehozas "+name+" neven!");
        return true;
    }

    public boolean addh(String name, Fungus actFungus, Tecton tectonTS, Tecton tectonTN){
        for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
            String Name = entry1.getKey();
            if (Name.equals(name)) {
                System.out.println("#Ez a nev mar foglalt: "+name);
                return false;
            }
        }
        if (tectonTN!=null) {
            if (!tectonTS.GetNeighbours().contains(tectonTN)) {
                System.out.println("#A tektonok nem szomszedosak!");
                return false;
            }
            for (Hypha hypha : tectonTN.GetHyphas()) {
                if (hypha.GetHostFungus().equals(actFungus) && hypha.GetTectons().size()==2) {
                    System.out.println("#Ennek a gombafajnak mar van gombafonala a tektonon!");
                    return false;
                }
            }
            
            boolean  success = tectonTN.simpleAddHypha(actFungus, tectonTS);
            if(!success){
                System.err.println("#Hifa lerakas sikertelen!");
                return false;
            }
            Hypha actHypha = tectonTN.GetHyphas().get(tectonTN.GetHyphas().size()-1);
            hCtr++;
            planet.put(name, actHypha);
            System.out.println("#Sikeres gombafonal letrehozas "+name+" neven!");
            return true;
        }
        for (Hypha hypha : tectonTS.GetHyphas()) {
            if (hypha.GetHostFungus().equals(actFungus) && hypha.GetTectons().size()==1) {
                System.out.println("#Ennek a gombafajnak mar van gombafonala a tektonon!");
                return false;
            }
        }
        boolean  success = tectonTS.simpleAddHypha(actFungus, null);
        if(!success){
            System.err.println("#Hifa lerakas sikertelen!");
            return false;
        }
        Hypha actHypha = tectonTS.GetHyphas().get(tectonTS.GetHyphas().size()-1);
        hCtr++;
        planet.put(name, actHypha);
        System.out.println("#Sikeres gombafonal letrehozas "+name+" neven!");
        return true;
    }

    public boolean adds(String name, String sporeType, int nutritionValue, int effectDurr, Fungus actFungus, Tecton tectonTN){
        for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
            String Name = entry1.getKey();
            if (Name.equals(name)) {
                System.out.println("#Ez a nev mar foglalt: "+name);
                return false;
            }
        }
        if (nutritionValue < 0 || effectDurr < 0) {
            System.out.println("#Hibas szam ertek!");
            return false;
        }
        switch (sporeType) {
            case "s":
                Spore actSporeS = new Spore(nutritionValue, effectDurr, actFungus, tectonTN);
                tectonTN.GetSpores().add(actSporeS);
                planet.put(name, actSporeS);
                break;
            case "sd":
                SpeedSpore actSporeSD = new SpeedSpore(nutritionValue, effectDurr, actFungus, tectonTN);
                tectonTN.GetSpores().add(actSporeSD);
                planet.put(name, actSporeSD);
                break;
            case "st":
                SplitSpore actSporeST = new SplitSpore(nutritionValue, effectDurr, actFungus, tectonTN);
                tectonTN.GetSpores().add(actSporeST);
                planet.put(name, actSporeST);
                break;
            case "sw":
                SlowSpore actSporeSW = new SlowSpore(nutritionValue, effectDurr, actFungus, tectonTN);
                tectonTN.GetSpores().add(actSporeSW);
                planet.put(name, actSporeSW);
                break;
            case "dm":
                DisarmSpore actSporeDM = new DisarmSpore(nutritionValue, effectDurr, actFungus, tectonTN);
                tectonTN.GetSpores().add(actSporeDM);
                planet.put(name, actSporeDM);
                break;
            case "sn":
                StunSpore actSporeSN = new StunSpore(nutritionValue, effectDurr, actFungus, tectonTN);
                tectonTN.GetSpores().add(actSporeSN);
                planet.put(name, actSporeSN);
                break;
            default:
                System.out.println("#Hibas tipus!");
                return false;
        }
        sCtr++;
        System.out.println("#Sikeres spora letrehozas "+name+" neven!");
        return true;
    }

    public boolean addi(String name, int speed, boolean cutAbility, int effectTimeLeft, InsectColony actInsectColony, Tecton tecton, Fungus eatenBy){
        for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
            String Name = entry1.getKey();
            if (Name.equals(name)) {
                System.out.println("#Ez a nev mar foglalt: "+name);
                return false;
            }
        }
        if (speed < 0 || effectTimeLeft < 0) {
            System.out.println("#Hibas szam ertek!");
            return false;
        }
        Insect actInsect;
        actInsect = new Insect(speed, cutAbility, effectTimeLeft, actInsectColony, tecton, eatenBy);
        /*if (eatenBy!=null) {
            actInsect = new Insect(0, false, 0, actInsectColony, tecton, eatenBy);
        }
        else {
            actInsect = new Insect(speed, cutAbility, effectTimeLeft, actInsectColony, tecton, eatenBy);
        }*/
        actInsectColony.AddInsect(actInsect);
        tecton.AddInsect(actInsect);
        planet.put(name, actInsect);
        iCtr++;
        controller.insectInit();
        System.out.println("#Sikeres rovar letrehozas "+name+" neven!");
        return true;
    }

    public boolean altt(Tecton tecton, Tecton tectonNH){
        for (Tecton actTecton : tecton.GetNeighbours()) {
            if (actTecton.equals(tectonNH)) {
                System.out.println("#A tektonok mar szomszedosak!");
                return false;
            }
        }
        if (!tecton.equals(tectonNH)) {
            tecton.AddNeighbour(tectonNH);
            tectonNH.AddNeighbour(tecton);
            System.out.println("#Sikeresen kialakitva a szomszedsag a ket tekton kozott.");
            return true;
        }
        else{
            System.out.println("#A ket tektonok ugyanaz!");
            return false;
        }
    }

    public boolean alth(Hypha hypha, Hypha hyphaNH){
        if (hypha.GetTectons().size() == hyphaNH.GetTectons().size()) {
            System.out.println("#Hiba: Mind a ketto res gombafonal vagy tektonon levo gombafonal!");
            return false;
        }
        if (hypha.GetHostFungus().equals(hyphaNH.GetHostFungus())) {
            if (!hypha.equals(hyphaNH)) {
                for (Hypha actHypha : hypha.GetNeighbours()) {
                    if (actHypha.equals(hyphaNH)) {
                        System.out.println("#A gombafonalak mar szomszedosak!");
                        return false;
                    }
                }
                for (Tecton actTecton : hypha.GetTectons()) {
                    if (hyphaNH.GetTectons().contains(actTecton)) {
                        hypha.AddNeighbour(hyphaNH);
                        hyphaNH.AddNeighbour(hypha);
                        System.out.println("#Sikeresen kialakitva a szomszedsag a ket gombafonal kozott.");
                        return true;
                    }
                }
                System.out.println("#Nem talalhato kozos Tecton a szomszedsag letrehozasahoz!");
                return false;
            }
            else{
                System.out.println("#A ket gombafonal ugyanaz!");
                return false;
            }
        }
        else{
            System.out.println("#A gombafonalak nem ugyanabba a gombafajba tartoznak!");
            return false;
        }
    }

    public void lstt(){
        System.out.println("#Tektonok listaja:");
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
                for (ITectonView oneNeighbourView : tectonView.GetNeighbours()) {
                    for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                        Object obj2 = entry2.getValue();
                        boolean isNeighbour = false;
                        try {
                            ITectonView neighbourView = (ITectonView) obj2;
                            if (oneNeighbourView.equals(neighbourView)) {
                                isNeighbour = true;
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
                System.out.println(tectonView
                .ToString(tectonName + "," + ((insectNames.equals("")) ? "" : "[" + insectNames + "]") + ","
                        + ((hyphaNames.equals("")) ? "" : "[" + hyphaNames + "]") + ","
                        + ((sporeNames.equals("")) ? "" : "[" + sporeNames + "]") + ","
                        + ((neighbourNames.equals("")) ? "" : "[" + neighbourNames + "]") + ","
                        + fungusBodyName));
            } catch (ClassCastException ccex) {
                // Wrong element, we do nothing and move on.
            }
        }
    }

    public void lstf(){
        System.out.println("#Gombafajok listaja:");
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
                System.out.println(fungusView.ToString(fungusName + ","
                        + ((fungusBodyNames.equals("")) ? fungusBodyNames : "[" + fungusBodyNames + "]") + ","
                        + ((hyphaNames.equals("")) ? hyphaNames : "[" + hyphaNames + "]")));
            } catch (ClassCastException ccex) {
                // Wrong element, we do nothing and move on.
            }
        }
    }

    public void lstic(){
        System.out.println("#Rovar koloniak listaja:");
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
                System.out.println(insectColonyView.ToString(
                        insectColonyName + "," + ((insectNames.equals("")) ? insectNames : "[" + insectNames + "]")
                                + "," + insectColonyView.getNutrition()));
            } catch (ClassCastException ccex) {
                // Wrong element, we do nothing and move on.
            }
        }
    }

    public void lstfb(){
        System.out.println("#Gombatestek listaja:");
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
                System.out.println(fungusBodyView.ToString(
                        fungusBodyName + "," + fungusBodyView.GetIsDeveloped() + "," + fungusBodyView.GetAge() + ","
                                + fungusBodyView.GetIsDead() + "," + fungusBodyView.GetSporeCount() + ","
                                + fungusBodyView.GetShotsLeft() + "," + tectonName + "," + fungusName));
            } catch (ClassCastException ccex) {
                // Wrong element, we do nothing and move on.
            }
        }
    }

    public void lsth(){
        System.out.println("#Gombafonalak listaja:");
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
                for (ITectonView oneTectonView : hyphaView.GetTectons()) {
                    for (Map.Entry<String, Object> entry2 : planet.entrySet()) {
                        Object obj2 = entry2.getValue();
                        try {
                            ITectonView tectonView = (ITectonView) obj2;
                            if (oneTectonView.equals(tectonView)) {
                                if (tectonNames.equals("")) {
                                    tectonNames = entry2.getKey();
                                    break;
                                } else {
                                    tectonNames += "," + entry2.getKey();
                                    break;
                                }
                            }
                        } catch (ClassCastException ccex) {
                            // Wrong element, we do nothing and move on.
                        }
                    }
                }
                System.out.println(hyphaView.ToString(hyphaName + ","
                + ((neighbourNames.equals("")) ? neighbourNames : "[" + neighbourNames + "]") + ","
                + fungusName + "," + ((tectonNames.equals("")) ? tectonNames : "[" + tectonNames + "]")));
            } catch (ClassCastException ccex) {
                // Wrong element, we do nothing and move on.
            }
        }
    }

    public void lsts(){
        System.out.println("#Sporak listaja:");
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
                System.out.println(sporeView.ToString(sporeName + "," + sporeView.GetNutritionValue() + ","
                        + sporeView.GetEffectDurr() + "," + fungusName + "," + tectonName));
            } catch (ClassCastException ccex) {
                // Wrong element, we do nothing and move on.
            }
        }
    }

    public void lsti(){
        System.out.println("#Rovarok listaja:");
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
                            fungusName = entry2.getKey();
                            break;
                        }
                    } catch (ClassCastException ccex) {
                        // Wrong element, we do nothing and move on.
                    }
                }
                System.out.println(insectView.ToString(insectName + "," + insectView.GetSpeed() + ","
                        + insectView.GetCutAbility() + "," + insectView.GetEffectTimeLeft() + "," + insectColonyName
                        + "," + tectonName + "," + ((fungusName.equals("")) ? "null" : fungusName)));
            } catch (ClassCastException ccex) {
                // Wrong element, we do nothing and move on.
            }
        }
    }

    public boolean exec(String fileName){
        // Ellenőrizzük, hogy a fájl .txt kiterjesztésű-e
        if (!fileName.endsWith(".txt")) {
            System.err.println("#A megadott fajl nem .txt kiterjesztesu: " + fileName);
            return false;
        }

        File folder = new File("fungorium/array_act");
            
        // Ellenőrizzük, hogy a mappa létezik-e és könyvtár-e
        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("#A fungorium/array_act mappa nem találhato vagy nem mappa: " + folder.getPath());
            return false;
        }

        // A megadott fájl elérési útja
        File file = new File(folder, fileName);

        // Ellenőrizzük, hogy a fájl létezik-e
        if (!file.exists()) {
            System.err.println("#A fajl nem talalható: " + file.getPath());
            return false;
        }

        // Fájl tartalmának beolvasása BufferedReader és FileReader segítségével
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                menuExec(line);
            }
        } catch (IOException e) {
            System.out.println("#Hiba a fajl olvasása kozben: " + e.getMessage());
        }
        System.out.println("#Sikeres script vegrehajtas.");
        return true;
    }

    public void rst(){
        planet = new LinkedHashMap<>();
        icCtr = 0;
        fCtr = 0;
        fbCtr = 0;
        hCtr = 0;
        tCtr = 0;
        iCtr = 0;
        sCtr = 0;
        controller.SetToDefault();
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows CMD esetén
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Linux/Unix esetén
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Fungorium game (prototype):");
        System.out.println("(Segitseg: /help)");
    }

    public boolean menuExec(String command){
        boolean everythingGood = true;
        if (command.equals("/exit")) {
            System.out.println("#Viszontlatasra és tovabbi szep napot!");
            System.exit(0);
        }
        String select = (command.trim().split("\\s+"))[0];
        if (select.startsWith("/")) {
            switch (select) {
                case "/help":
                    help();
                    break;
                case "/exec":
                    String[] parts = command.trim().split("\\s+");
                    if (!parts[0].equals("/exec")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    if (parts.length != 2) {
                        System.out.println("#Hiba a parameterezessel!");
                        return false;
                    }
                    // A fungorium mappa elérési útja
                    String fileName = parts[1];
                    everythingGood = exec(fileName);
                    break;
                case "/rand":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("/rand")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    if (parts.length > 2) {
                        System.out.println("#Hiba a paraméterezéssel!");
                        return false;
                    }
                    String cmd = "";
                    if (parts.length == 2) {
                        cmd = parts[1];
                    }
                    everythingGood = controller.Rand(cmd);
                    break;
                case "/trigg":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("/trigg")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    if (parts.length != 2) {
                        System.out.println("#Hiba a paraméterezéssel!");
                        return false;
                    }
                    String event = parts[1];
                    everythingGood = controller.Trigg(event);
                    break;
                case "/turns":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("/turns")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    if (parts.length > 2) {
                        System.out.println("#Hiba a paraméterezéssel!");
                        return false;
                    }
                    cmd = "";
                    if (parts.length == 2) {
                        cmd = parts[1];
                    }
                    everythingGood = controller.Turns(cmd);
                    break;
                case "/addt":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("/addt")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    LinkedHashMap<String, String> args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("n")) {
                        System.out.println("#Hianyzik a -n [Name] argumentum.");
                        return false;
                    }
                    if (args.get("n").equals("") || args.get("n")==null) {
                        System.out.println("#Nincs nev megadva (-n): "+args.get("n"));
                        return false;
                    }
                    for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
                        String Name = entry1.getKey();
                        if (Name.equals(args.get("n"))) {
                            System.out.println("#Ez a nev mar foglalt (-n): "+args.get("n"));
                            return false;
                        }
                    }
                    String name = args.get("n");
                    LinkedList<String> typeList = new LinkedList<>(List.of("n","wi","v","we","b"));
                    if (args.containsKey("t") && (args.get("t").equals("") || args.get("t")==null || !typeList.contains(args.get("t")))) {
                        System.out.println("#Nincs tekton tipus megadva vagy hibas (-t): "+args.get("t"));
                        return false;
                    }
                    String tectonType = args.containsKey("t") ? args.get("t") : "n";
                    everythingGood = addt(name, tectonType);
                    break;
                case "/addf":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("/addf")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("n")) {
                        System.out.println("#Hianyzik a -n [Name] argumentum.");
                        return false;
                    }
                    if (args.get("n").equals("") || args.get("n")==null) {
                        System.out.println("#Nincs nev megadva (-n): "+args.get("n"));
                        return false;
                    }
                    for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
                        String Name = entry1.getKey();
                        if (Name.equals(args.get("n"))) {
                            System.out.println("#Ez a nev mar foglalt (-n): "+args.get("n"));
                            return false;
                        }
                    }
                    name = args.get("n");
                    everythingGood = addf(name);
                    break;
                case "/addic":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("/addic")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("n")) {
                        System.out.println("#Hianyzik a -n [Name] argumentum.");
                        return false;
                    }
                    if (args.get("n").equals("") || args.get("n")==null) {
                        System.out.println("#Nincs nev megadva (-n): "+args.get("n"));
                        return false;
                    }
                    for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
                        String Name = entry1.getKey();
                        if (Name.equals(args.get("n"))) {
                            System.out.println("#Ez a nev mar foglalt (-n): "+args.get("n"));
                            return false;
                        }
                    }
                    name = args.get("n");
                    if (args.containsKey("nv") && (args.get("nv").equals("") || args.get("nv")==null || parseIntNumberMinZero(args.get("nv")) ==-1)) {
                        System.out.println("#Nincs osszegyujtott tapanyag mennyiseg megadva vagy hibas (-nv): "+args.get("nv"));
                        return false;
                    }
                    int nutritionValue = args.containsKey("nv") ? parseIntNumberMinZero(args.get("nv")) : 0;
                    everythingGood = addic(name, nutritionValue);
                    break;
                case "/addfb":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("/addfb")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("n")) {
                        System.out.println("#Hianyzik a -n [Name] argumentum.");
                        return false;
                    }
                    if (!args.containsKey("f")) {
                        System.out.println("#Hianyzik a -f [Name] argumentum.");
                        return false;
                    }
                    if (!args.containsKey("t")) {
                        System.out.println("#Hianyzik a -t [Name] argumentum.");
                        return false;
                    }
                    if (args.get("n").equals("") || args.get("n")==null) {
                        System.out.println("#Nincs nev megadva (-n): "+args.get("n"));
                        return false;
                    }
                    for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
                        String Name = entry1.getKey();
                        if (Name.equals(args.get("n"))) {
                            System.out.println("#Ez a nev mar foglalt (-n): "+args.get("n"));
                            return false;
                        }
                    }
                    name = args.get("n");
                    if ((planet.get(args.get("f")))==null) {
                        System.out.println("#Nincs ilyen gombafaj (-f): "+args.get("f"));
                        return false;
                    }
                    Fungus actFungus = null;
                    try {
                        actFungus = (Fungus)planet.get(args.get("f"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen gombafaj (-f): "+args.get("f"));
                        return false;
                    }
                    actFungus = (Fungus)planet.get(args.get("f"));
                    if (planet.get(args.get("t"))==null) {
                        System.out.println("#Nincs ilyen tekton (-t): "+args.get("t"));
                        return false;
                    }
                    Tecton tecton = null;
                    try {
                        tecton = (Tecton)planet.get(args.get("t"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen tekton (-t): "+args.get("t"));
                        return false;
                    }
                    if (tecton.GetFungusBody()!=null) {
                        System.out.println("#Mar van egy masik gombatest a tektonon!");
                        return false;
                    }
                    if (args.containsKey("d") && (args.get("d").equals("") || args.get("d")==null)) {
                        System.out.println("#Nincs allapot megadva (-d): "+args.get("d"));
                        return false;
                    }
                    boolean isDead = args.containsKey("d") && args.get("d").equalsIgnoreCase("y");
                    if (args.containsKey("a") && (args.get("a").equals("") || args.get("a")==null || parseIntNumberMinZero(args.get("a")) ==-1)) {
                        System.out.println("#Nincs eletkor megadva vagy hibas (-a): "+args.get("a"));
                        return false;
                    }
                    int age = args.containsKey("a") ? parseIntNumberMinZero(args.get("a")) : 0;
                    if (args.containsKey("dv") && (args.get("dv").equals("") || args.get("dv")==null)) {
                        System.out.println("#Nincs allapot megadva (-dv): "+args.get("dv"));
                        return false;
                    }
                    boolean fullyDeveloped = args.containsKey("dv") && args.get("dv").equalsIgnoreCase("y");
                    if (args.containsKey("sc") && (args.get("sc").equals("") || args.get("sc")==null || parseIntNumberMinZero(args.get("sc")) ==-1)) {
                        System.out.println("#Nincs spora szam megadva vagy hibas (-sc): "+args.get("sc"));
                        return false;
                    }
                    int sporeCount = args.containsKey("sc") ? parseIntNumberMinZero(args.get("sc")) : 0;
                    if (args.containsKey("sl") && (args.get("sl").equals("") || args.get("sl")==null || parseIntNumberMinZero(args.get("sl")) ==-1)) {
                        System.out.println("#Nincs maximalis loves szam megadva vagy hibas (-sl): "+args.get("sl"));
                        return false;
                    }
                    int shotLimit = args.containsKey("sl") ? parseIntNumberMinZero(args.get("sl")) : 4;
                    everythingGood = addfb(name, tecton, actFungus, fullyDeveloped, age, isDead, sporeCount, shotLimit);
                    break;
                case "/addh":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("/addh")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("n")) {
                        System.out.println("#Hianyzik a -n [Name] argumentum.");
                        return false;
                    }
                    if (!args.containsKey("f")) {
                        System.out.println("#Hianyzik a -f [Name] argumentum.");
                        return false;
                    }
                    if (!args.containsKey("ts")) {
                        System.out.println("#Hianyzik a -ts [Name] argumentum.");
                        return false;
                    }
                    if (args.get("n").equals("") || args.get("n")==null) {
                        System.out.println("#Nincs nev megadva (-n): "+args.get("n"));
                        return false;
                    }
                    for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
                        String Name = entry1.getKey();
                        if (Name.equals(args.get("n"))) {
                            System.out.println("#Ez a nev mar foglalt (-n): "+args.get("n"));
                            return false;
                        }
                    }
                    name = args.get("n");
                    if (((Fungus)planet.get(args.get("f")))==null) {
                        System.out.println("#Nincs ilyen gombafaj (-f): "+args.get("f"));
                        return false;
                    }
                    actFungus = null;
                    try {
                        actFungus = (Fungus)planet.get(args.get("f"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen gombafaj (-f): "+args.get("f"));
                        return false;
                    }
                    actFungus = (Fungus)planet.get(args.get("f"));
                    if (planet.get(args.get("ts"))==null) {
                        System.out.println("#Nincs ilyen tekton (-ts): "+args.get("ts"));
                        return false;
                    }
                    Tecton tectonTS = null;
                    try {
                        tectonTS = (Tecton)planet.get(args.get("ts"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen tekton (-ts): "+args.get("ts"));
                        return false;
                    }
                    if (args.containsKey("tn")) {
                        if ((args.get("tn").equals("") || args.get("tn")==null)) {
                            System.out.println("#Nincs tekton megadva (-tn): "+args.get("tn"));
                            return false;
                        }
                        if (planet.get(args.get("tn"))==null) {
                            System.out.println("#Nincs ilyen tekton (-tn): "+args.get("tn"));
                            return false;
                        }
                        Tecton tectonTN = null;
                        try {
                            tectonTN = (Tecton)planet.get(args.get("tn"));
                        } catch (ClassCastException ccex) {
                            System.out.println("#Nincs ilyen tekton (-tn): "+args.get("tn"));
                            return false;
                        }
                        everythingGood = addh(name, actFungus, tectonTS, tectonTN);
                    }
                    else {
                        everythingGood = addh(name, actFungus, tectonTS, null);
                    }
                    break;
                case "/adds":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("/adds")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("n")) {
                        System.out.println("#Hianyzik a -n [Name] argumentum.");
                        return false;
                    }
                    if (!args.containsKey("f")) {
                        System.out.println("#Hianyzik a -f [Name] argumentum.");
                        return false;
                    }
                    if (!args.containsKey("tn")) {
                        System.out.println("#Hianyzik a -tn [Name] argumentum.");
                        return false;
                    }
                    if (args.get("n").equals("") || args.get("n")==null) {
                        System.out.println("#Nincs nev megadva (-n): "+args.get("n"));
                        return false;
                    }
                    for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
                        String Name = entry1.getKey();
                        if (Name.equals(args.get("n"))) {
                            System.out.println("#Ez a nev mar foglalt (-n): "+args.get("n"));
                            return false;
                        }
                    }
                    name = args.get("n");
                    if (((Fungus)planet.get(args.get("f")))==null) {
                        System.out.println("#Nincs ilyen gombafaj (-f): "+args.get("f"));
                        return false;
                    }
                    actFungus = null;
                    try {
                        actFungus = (Fungus)planet.get(args.get("f"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen gombafaj (-f): "+args.get("f"));
                        return false;
                    }
                    actFungus = (Fungus)planet.get(args.get("f"));
                    if ((args.get("tn").equals("") || args.get("tn")==null)) {
                        System.out.println("#Nincs tekton megadva (-tn): "+args.get("tn"));
                        return false;
                    }
                    if (planet.get(args.get("tn"))==null) {
                        System.out.println("#Nincs ilyen tekton (-tn): "+args.get("tn"));
                        return false;
                    }
                    Tecton tectonTN = null;
                    try {
                        tectonTN = (Tecton)planet.get(args.get("tn"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen tekton (-tn): "+args.get("tn"));
                        return false;
                    }
                    typeList = new LinkedList<>(List.of("s","sd","st","sw","dm","sn"));
                    if (args.containsKey("t") && (args.get("t").equals("") || args.get("t")==null || !typeList.contains(args.get("t")))) {
                        System.out.println("#Nincs spora tipus megadva vagy hibas (-t): "+args.get("t"));
                        return false;
                    }
                    String sporeType = args.containsKey("t") ? args.get("t") : "s";
                    if (args.containsKey("nv") && (args.get("nv").equals("") || args.get("nv")==null || parseIntNumberMinZero(args.get("nv")) ==-1)) {
                        System.out.println("#Nincs tapanyag ertek megadva vagy hibas (-nv): "+args.get("nv"));
                        return false;
                    }
                    nutritionValue = args.containsKey("nv") ? parseIntNumberMinZero(args.get("nv")) : 1;
                    if (args.containsKey("ed") && (args.get("ed").equals("") || args.get("ed")==null || parseIntNumberMinZero(args.get("ed")) ==-1)) {
                        System.out.println("#Nincs effekt hatas ideje megadva vagy hibas (-ed): "+args.get("ed"));
                        return false;
                    }
                    int effectDurr = args.containsKey("ed") ? parseIntNumberMinZero(args.get("ed")) : 0;
                    everythingGood = adds(name, sporeType, nutritionValue, effectDurr, actFungus, tectonTN);
                    break;
                case "/addi":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("/addi")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("n")) {
                        System.out.println("#Hianyzik a -n [Name] argumentum.");
                        return false;
                    }
                    if (!args.containsKey("ic")) {
                        System.out.println("#Hianyzik a -ic [Name] argumentum.");
                        return false;
                    }
                    if (!args.containsKey("t")) {
                        System.out.println("#Hianyzik a -t [Name] argumentum.");
                        return false;
                    }
                    if (args.get("n").equals("") || args.get("n")==null) {
                        System.out.println("#Nincs nev megadva (-n): "+args.get("n"));
                        return false;
                    }
                    for (Map.Entry<String, Object> entry1 : planet.entrySet()) {
                        String Name = entry1.getKey();
                        if (Name.equals(args.get("n"))) {
                            System.out.println("#Ez a nev mar foglalt (-n): "+args.get("n"));
                            return false;
                        }
                    }
                    name = args.get("n");
                    if (((InsectColony)planet.get(args.get("ic")))==null) {
                        System.out.println("#Nincs ilyen rovar kolonia (-ic): "+args.get("ic"));
                        return false;
                    }
                    InsectColony actInsectColony = null;
                    try {
                        actInsectColony = (InsectColony)planet.get(args.get("ic"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen rovar kolonia (-ic): "+args.get("ic"));
                        return false;
                    }
                    actInsectColony = (InsectColony)planet.get(args.get("ic"));
                    if (planet.get(args.get("t"))==null) {
                        System.out.println("#Nincs ilyen tekton (-t): "+args.get("t"));
                        return false;
                    }
                    tecton = null;
                    try {
                        tecton = (Tecton)planet.get(args.get("t"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen tekton (-t): "+args.get("t"));
                        return false;
                    }
                    if (args.containsKey("sd") && (args.get("sd").equals("") || args.get("sd")==null || parseIntNumberMinZero(args.get("sd")) ==-1)) {
                        System.out.println("#Nincs sebesseg/lepesszam ertek megadva vagy hibas (-sd): "+args.get("sd"));
                        return false;
                    }
                    int speed = args.containsKey("sd") ? parseIntNumberMinZero(args.get("sd")) : 2;
                    if (args.containsKey("ca") && (args.get("ca").equals("") || args.get("ca")==null)) {
                        System.out.println("#Nincs allapot megadva (-ca): "+args.get("ca"));
                        return false;
                    }
                    boolean cutAbility = !args.containsKey("ca") || (args.containsKey("ca") && args.get("ca").equalsIgnoreCase("y"));
                    if (args.containsKey("et") && (args.get("et").equals("") || args.get("et")==null || parseIntNumberMinZero(args.get("et")) ==-1)) {
                        System.out.println("#Nincs effekt hatas ido megadva vagy hibas (-et): "+args.get("et"));
                        return false;
                    }
                    int effectTimeLeft = args.containsKey("et") ? parseIntNumberMinZero(args.get("et")) : 0;
                    Fungus eatenBy;
                    if (args.containsKey("eb")) {
                        if (((Fungus)planet.get(args.get("eb")))==null) {
                            System.out.println("#Nincs ilyen gombafaj (-eb): "+args.get("eb"));
                            return false;
                        }
                        eatenBy = null;
                        try {
                            eatenBy = (Fungus)planet.get(args.get("eb"));
                        } catch (ClassCastException ccex) {
                            System.out.println("#Nincs ilyen gombafaj (-eb): "+args.get("eb"));
                            return false;
                        }
                        eatenBy = (Fungus)planet.get(args.get("eb"));
                    }
                    else{
                        eatenBy = null;
                    }
                    everythingGood = addi(name, speed, cutAbility, effectTimeLeft, actInsectColony, tecton, eatenBy);
                    break;
                case "/altt":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("/altt")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("n")) {
                        System.out.println("#Hianyzik a -n [Name] argumentum.");
                        return false;
                    }
                    /*if (!args.containsKey("nh")) {
                        System.out.println("#Hianyzik a -nh [Name] argumentum.");
                        return false;
                    }*/
                    if (args.get("n").equals("") || args.get("n")==null) {
                        System.out.println("#Nincs nev megadva (-n): "+args.get("n"));
                        return false;
                    }
                    if (planet.get(args.get("n"))==null) {
                        System.out.println("#Nincs ilyen tekton (-n): "+args.get("n"));
                        return false;
                    }
                    tecton = null;
                    try {
                        tecton = (Tecton)planet.get(args.get("n"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen tekton (-n): "+args.get("n"));
                        return false;
                    }
                    if (args.containsKey("nh")) {
                        if (args.get("nh").equals("") || args.get("nh")==null) {
                            System.out.println("#Nincs nev megadva (-nh): "+args.get("nh"));
                            return false;
                        }
                        if (planet.get(args.get("nh"))==null) {
                            System.out.println("#Nincs ilyen tekton (-nh): "+args.get("nh"));
                            return false;
                        }
                        Tecton tectonNH = null;
                        try {
                            tectonNH = (Tecton)planet.get(args.get("nh"));
                        } catch (ClassCastException ccex) {
                            System.out.println("#Nincs ilyen tekton (-nh): "+args.get("nh"));
                            return false;
                        }
                        everythingGood = altt(tecton, tectonNH);
                    }
                    else{
                        System.out.println("#A(z) -nh kapcsolo hianya miatt a muvelet meghiusult!");
                        return false;
                    }
                    
                    break;
                case "/alth":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("/alth")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("n")) {
                        System.out.println("#Hianyzik a -n [Name] argumentum.");
                        return false;
                    }
                    /*if (!args.containsKey("nh")) {
                        System.out.println("#Hianyzik a -nh [Name] argumentum.");
                        return false;
                    }*/
                    if (args.get("n").equals("") || args.get("n")==null) {
                        System.out.println("#Nincs nev megadva (-n): "+args.get("n"));
                        return false;
                    }
                    if (planet.get(args.get("n"))==null) {
                        System.out.println("#Nincs ilyen gombafonal (-n): "+args.get("n"));
                        return false;
                    }
                    Hypha hypha = null;
                    try {
                        hypha = (Hypha)planet.get(args.get("n"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen gombafonal (-n): "+args.get("n"));
                        return false;
                    }
                    hypha = (Hypha)planet.get(args.get("n"));
                    if (args.containsKey("nh")) {
                        if (args.get("nh").equals("") || args.get("nh")==null) {
                            System.out.println("#Nincs nev megadva (-nh): "+args.get("nh"));
                            return false;
                        }
                        if (planet.get(args.get("nh"))==null) {
                            System.out.println("#Nincs ilyen gombafonal (-nh): "+args.get("nh"));
                            return false;
                        }
                        Hypha hyphaNH = null;
                        try {
                            hyphaNH = (Hypha)planet.get(args.get("nh"));
                        } catch (ClassCastException ccex) {
                            System.out.println("#Nincs ilyen gombafonal (-nh): "+args.get("nh"));
                            return false;
                        }
                        hyphaNH = (Hypha)planet.get(args.get("nh"));
                        everythingGood = alth(hypha, hyphaNH);
                    }
                    else{
                        System.out.println("#A(z) -nh kapcsolo hianya miatt a muvelet meghiusult!");
                        return false;
                    }
                    break;
                case "/lstt":
                    lstt();
                    break;
                case "/lstf":
                    lstf();
                    break;
                case "/lstic":
                    lstic();
                    break;
                case "/lstfb":
                    lstfb();
                    break;
                case "/lsth":
                    lsth();
                    break;
                case "/lsts":
                    lsts();
                    break;
                case "/lsti":
                    lsti();
                    break;
                case "/save":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("/save")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    if (parts.length != 2) {
                        System.out.println("#Hiba a parameterezessel!");
                        return false;
                    }
                    fileName = parts[1];
                    everythingGood = save(fileName);
                    break;
                case "/load":
                    String[] cmdParts = command.trim().split("\\s+");
                    if (!cmdParts[0].equals("/load")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    if (cmdParts.length != 2) {
                        System.out.println("#Hiba a parameterezessel!");
                        return false;
                    }
                    fileName = cmdParts[1];
                    everythingGood = load(fileName);
                    break;
                case "/rst":
                    rst();
                    break;
                default:
                    System.out.println("#Nem letezo parancs: "+select);
                    everythingGood = false;
                    break;
            }
        }
        else{
            switch (select) {
                case "breaktecton":
                    String[] parts = command.trim().split("\\s+");
                    if (!parts[0].equals("breaktecton")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    LinkedHashMap<String, String> args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("t")) {
                        System.out.println("#Hianyzik a -t [Name] argumentum.");
                        return false;
                    }
                    if (planet.get(args.get("t"))==null) {
                        System.out.println("#Nincs ilyen tekton (-t): "+args.get("t"));
                        return false;
                    }
                    Tecton tecton = null;
                    try {
                        tecton = (Tecton)planet.get(args.get("t"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen tekton (-t): "+args.get("t"));
                        return false;
                    }
                    everythingGood = controller.BreakTecton(tecton);
                    break;
                case "growfungusbody":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("growfungusbody")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("f")) {
                        System.out.println("#Hianyzik a -f [Name] argumentum.");
                        return false;
                    }
                    if (!args.containsKey("t")) {
                        System.out.println("#Hianyzik a -t [Name] argumentum.");
                        return false;
                    }
                    if ((planet.get(args.get("f")))==null) {
                        System.out.println("#Nincs ilyen gombafaj (-f): "+args.get("f"));
                        return false;
                    }
                    Fungus actFungus = null;
                    try {
                        actFungus = (Fungus)planet.get(args.get("f"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen gombafaj (-f): "+args.get("f"));
                        return false;
                    }
                    actFungus = (Fungus)planet.get(args.get("f"));
                    if (planet.get(args.get("t"))==null) {
                        System.out.println("#Nincs ilyen tekton (-t): "+args.get("t"));
                        return false;
                    }
                    Tecton actTecton = null;
                    try {
                        actTecton = (Tecton)planet.get(args.get("t"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen tekton (-t): "+args.get("t"));
                        return false;
                    }
                    if (actTecton.GetFungusBody()!=null) {
                        System.out.println("#Mar van egy masik gombatest a tektonon!");
                        return false;
                    }
                    everythingGood = controller.GrowFungusBody(actTecton, actFungus);
                    break;
                case "growfungusbodyfrominsect":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("growfungusbodyfrominsect")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("f")) {
                        System.out.println("#Hianyzik a -f [Name] argumentum.");
                        return false;
                    }
                    if (!args.containsKey("t")) {
                        System.out.println("#Hianyzik a -t [Name] argumentum.");
                        return false;
                    }
                    if ((planet.get(args.get("f")))==null) {
                        System.out.println("#Nincs ilyen gombafaj (-f): "+args.get("f"));
                        return false;
                    }
                    actFungus = null;
                    try {
                        actFungus = (Fungus)planet.get(args.get("f"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen gombafaj (-f): "+args.get("f"));
                        return false;
                    }
                    actFungus = (Fungus)planet.get(args.get("f"));
                    if (planet.get(args.get("t"))==null) {
                        System.out.println("#Nincs ilyen tekton (-t): "+args.get("t"));
                        return false;
                    }
                    actTecton = null;
                    try {
                        actTecton = (Tecton)planet.get(args.get("t"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen tekton (-t): "+args.get("t"));
                        return false;
                    }
                    if (actTecton.GetFungusBody()!=null) {
                        System.out.println("#Mar van egy masik gombatest a tektonon!");
                        return false;
                    }
                    everythingGood = controller.GrowFungusBodyFromInsect(actTecton, actFungus);
                    break;
                case "absorbhypha":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("absorbhypha")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("t")) {
                        System.out.println("#Hianyzik a -t [Name] argumentum.");
                        return false;
                    }
                    actTecton = null;
                    try {
                        actTecton = (Tecton)planet.get(args.get("t"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen tekton (-t): "+args.get("t"));
                        return false;
                    }
                    everythingGood = controller.AbsorbHypha(actTecton);
                    break;
                case "producespore":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("producespore")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("fb")) {
                        System.out.println("#Hianyzik a -fb [Name] argumentum.");
                        return false;
                    }
                    if ((planet.get(args.get("fb")))==null) {
                        System.out.println("#Nincs ilyen gombatest (-fb): "+args.get("fb"));
                        return false;
                    }
                    FungusBody actFungusBody = null;
                    try {
                        actFungusBody = (FungusBody)planet.get(args.get("fb"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen gombatest (-fb): "+args.get("fb"));
                        return false;
                    }
                    actFungusBody = (FungusBody)planet.get(args.get("fb"));
                    everythingGood = controller.ProduceSpore(actFungusBody);
                    break;
                case "shootspores":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("shootspores")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("fb")) {
                        System.out.println("#Hianyzik a -fb [Name] argumentum.");
                        return false;
                    }
                    if ((planet.get(args.get("fb")))==null) {
                        System.out.println("#Nincs ilyen gombatest (-fb): "+args.get("fb"));
                        return false;
                    }
                    actFungusBody = null;
                    try {
                        actFungusBody = (FungusBody)planet.get(args.get("fb"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen gombatest (-fb): "+args.get("fb"));
                        return false;
                    }
                    actFungusBody = (FungusBody)planet.get(args.get("fb"));
                    everythingGood = controller.ShootSpores(actFungusBody);
                    break;
                case "diefungusbody":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("diefungusbody")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("fb")) {
                        System.out.println("#Hianyzik a -fb [Name] argumentum.");
                        return false;
                    }
                    if ((planet.get(args.get("fb")))==null) {
                        System.out.println("#Nincs ilyen gombatest (-fb): "+args.get("fb"));
                        return false;
                    }
                    actFungusBody = null;
                    try {
                        actFungusBody = (FungusBody)planet.get(args.get("fb"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen gombatest (-fb): "+args.get("fb"));
                        return false;
                    }
                    actFungusBody = (FungusBody)planet.get(args.get("fb"));
                    everythingGood = controller.DieFungusBody(actFungusBody);
                    break;
                case "growhypha":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("growhypha")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("f")) {
                        System.out.println("#Hianyzik a -f [Name] argumentum.");
                        return false;
                    }
                    if (!args.containsKey("ts")) {
                        System.out.println("#Hianyzik a -ts [Name] argumentum.");
                        return false;
                    }
                    if (!args.containsKey("tn")) {
                        System.out.println("#Hianyzik a -tn [Name] argumentum.");
                        return false;
                    }
                    actFungus = null;
                    try {
                        actFungus = (Fungus)planet.get(args.get("f"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen gombafaj (-f): "+args.get("f"));
                        return false;
                    }
                    actFungus = (Fungus)planet.get(args.get("f"));
                    if (planet.get(args.get("ts"))==null) {
                        System.out.println("#Nincs ilyen tekton (-ts): "+args.get("ts"));
                        return false;
                    }
                    Tecton tectonTS = null;
                    try {
                        tectonTS = (Tecton)planet.get(args.get("ts"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen tekton (-ts): "+args.get("ts"));
                        return false;
                    }
                    if ((args.get("tn").equals("") || args.get("tn")==null)) {
                        System.out.println("#Nincs tekton megadva (-tn): "+args.get("tn"));
                        return false;
                    }
                    if (planet.get(args.get("tn"))==null) {
                        System.out.println("#Nincs ilyen tekton (-tn): "+args.get("tn"));
                        return false;
                    }
                    Tecton tectonTN = null;
                    try {
                        tectonTN = (Tecton)planet.get(args.get("tn"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen tekton (-tn): "+args.get("tn"));
                        return false;
                    }
                    if (!tectonTS.GetNeighbours().contains(tectonTN)) {
                        System.out.println("#A(z) "+args.get("ts")+" es a(z) "+args.get("tn")+" tektonok nem szomszedosak!");
                        return false;
                    }
                    for (Hypha hypha : tectonTN.GetHyphas()) {
                        if (hypha.GetHostFungus().equals(actFungus) && hypha.GetTectons().size()==2) {
                            System.out.println("#Ennek a gombafajnak mar van gombafonala a tektonon (-tn): "+args.get("tn"));
                            return false;
                        }
                    }
                    everythingGood = controller.GrowHypha(actFungus, tectonTS, tectonTN);
                    break;
                case "atrophyofhypha":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("atrophyofhypha")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("h")) {
                        System.out.println("#Hianyzik a -h [Name] argumentum.");
                        return false;
                    }
                    if ((planet.get(args.get("h")))==null) {
                        System.out.println("#Nincs ilyen gombafonal (-h): "+args.get("h"));
                        return false;
                    }
                    Hypha actHypha = null;
                    try {
                        actHypha = (Hypha)planet.get(args.get("h"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen gombafonal (-h): "+args.get("h"));
                        return false;
                    }
                    everythingGood = controller.AtrophyOfHypha(actHypha);
                    break;
                case "eatstunnedinsect":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("eatstunnedinsect")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("h")) {
                        System.out.println("#Hianyzik a -h [Name] argumentum.");
                        return false;
                    }
                    if (!args.containsKey("i")) {
                        System.out.println("#Hianyzik a -i [Name] argumentum.");
                        return false;
                    }
                    if ((planet.get(args.get("h")))==null) {
                        System.out.println("#Nincs ilyen gombafonal (-h): "+args.get("h"));
                        return false;
                    }
                    actHypha = null;
                    try {
                        actHypha = (Hypha)planet.get(args.get("h"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen gombafonal (-h): "+args.get("h"));
                        return false;
                    }
                    actHypha = (Hypha)planet.get(args.get("h"));
                    if ((planet.get(args.get("i")))==null) {
                        System.out.println("#Nincs ilyen rovar (-i): "+args.get("i"));
                        return false;
                    }
                    Insect actInsect = null;
                    try {
                        actInsect = (Insect)planet.get(args.get("i"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen rovar (-i): "+args.get("i"));
                        return false;
                    }
                    actInsect = (Insect)planet.get(args.get("i"));
                    everythingGood = controller.EatStunnedInsect(actHypha, actInsect);
                    break;
                case "eatspore":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("eatspore")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("s")) {
                        System.out.println("#Hianyzik a -s [Name] argumentum.");
                        return false;
                    }
                    if (!args.containsKey("i")) {
                        System.out.println("#Hianyzik a -i [Name] argumentum.");
                        return false;
                    }
                    if ((planet.get(args.get("s")))==null) {
                        System.out.println("#Nincs ilyen gombafonal (-s): "+args.get("s"));
                        return false;
                    }
                    Spore actSpore = null;
                    try {
                        actSpore = (Spore)planet.get(args.get("s"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen spora (-s): "+args.get("s"));
                        return false;
                    }
                    actSpore = (Spore)planet.get(args.get("s"));
                    if ((planet.get(args.get("i")))==null) {
                        System.out.println("#Nincs ilyen rovar (-i): "+args.get("i"));
                        return false;
                    }
                    actInsect = null;
                    try {
                        actInsect = (Insect)planet.get(args.get("i"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen rovar (-i): "+args.get("i"));
                        return false;
                    }
                    actInsect = (Insect)planet.get(args.get("i"));
                    everythingGood = controller.EatSpore(actInsect, actSpore);
                    break;
                case "moveinsect":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("moveinsect")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("t")) {
                        System.out.println("#Hianyzik a -s [Name] argumentum.");
                        return false;
                    }
                    if (!args.containsKey("i")) {
                        System.out.println("#Hianyzik a -i [Name] argumentum.");
                        return false;
                    }
                    if ((planet.get(args.get("i")))==null) {
                        System.out.println("#Nincs ilyen rovar (-i): "+args.get("i"));
                        return false;
                    }
                    actInsect = null;
                    try {
                        actInsect = (Insect)planet.get(args.get("i"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen rovar (-i): "+args.get("i"));
                        return false;
                    }
                    actInsect = (Insect)planet.get(args.get("i"));
                    if (planet.get(args.get("t"))==null) {
                        System.out.println("#Nincs ilyen tekton (-t): "+args.get("t"));
                        return false;
                    }
                    actTecton = null;
                    try {
                        actTecton = (Tecton)planet.get(args.get("t"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen tekton (-t): "+args.get("t"));
                        return false;
                    }
                    everythingGood = controller.MoveInsect(actInsect, actTecton);
                    break;
                case "cuthypha":
                    parts = command.trim().split("\\s+");
                    if (!parts[0].equals("cuthypha")) {
                        System.out.println("#Rossz fuggvenyhivas!");
                        return false;
                    }
                    args = new LinkedHashMap<>();
                    for (int i = 1; i < parts.length; i+=2) {
                        if (i+1<parts.length && parts[i].startsWith("-")) {
                            args.put(parts[i].substring(1), parts[i+1]);
                        }
                        else{
                            System.out.println("#Hibas argumentum formatum: "+parts[i]);
                            return false;
                        }
                    }
                    if (!args.containsKey("h")) {
                        System.out.println("#Hianyzik a -h [Name] argumentum.");
                        return false;
                    }
                    if (!args.containsKey("i")) {
                        System.out.println("#Hianyzik a -i [Name] argumentum.");
                        return false;
                    }
                    if ((planet.get(args.get("h")))==null) {
                        System.out.println("#Nincs ilyen gombafonal (-h): "+args.get("h"));
                        return false;
                    }
                    actHypha = null;
                    try {
                        actHypha = (Hypha)planet.get(args.get("h"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen gombafonal (-h): "+args.get("h"));
                        return false;
                    }
                    actHypha = (Hypha)planet.get(args.get("h"));
                    if ((planet.get(args.get("i")))==null) {
                        System.out.println("#Nincs ilyen rovar (-i): "+args.get("i"));
                        return false;
                    }
                    actInsect = null;
                    try {
                        actInsect = (Insect)planet.get(args.get("i"));
                    } catch (ClassCastException ccex) {
                        System.out.println("#Nincs ilyen rovar (-i): "+args.get("i"));
                        return false;
                    }
                    actInsect = (Insect)planet.get(args.get("i"));
                    everythingGood = controller.CutHypha(actInsect, actHypha);
                    break;
                default:
                    System.out.println("#Nem letezo parancs: "+select);
                    everythingGood = false;
                    break;
            }
        }
        System.out.print(">");
        return everythingGood;
    }

    private int parseIntNumberMinZero(String value){
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
