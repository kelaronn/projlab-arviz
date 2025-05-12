package fungorium;

import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GameGUI extends JFrame {
    private static final String MENU_CARD = "MenuCard";
    private static final String OPTIONS_CARD = "OptionsCard";
    private static final String PLAY_CARD = "PlayCard";
    private JPanel gamePanel;
    private JPanel playPanel;
    private JPanel optionsPanel;
    private JPanel menuPanel;
    private JLayeredPane gameMapJPanel;
    private JLabel insectImageIconLb1;
    private JLabel insectImageIconLb2;
    private JLabel insectImageIconLb3;
    private JLabel insectImageIconLb4;
    private JLabel sporeImageIconLb1;
    private JLabel sporeImageIconLb2;
    private JLabel sporeImageIconLb3;
    private JLabel sporeImageIconLb4;
    private JLabel hyphaImageIconLb1;
    private JLabel hyphaImageIconLb2;
    private JLabel hyphaImageIconLb3;
    private JLabel hyphaImageIconLb4;
    private JLabel fungusBodyImageIconLb;
    private JLabel tectonImageIconLb;
    private CardLayout cardLayout;
    private JList<String> allSelectableEntitiesJList;
    private JList<String> entitiesForOperationsJList;
    IView iview;

    public GameGUI() {
    }

    public GameGUI(View view) {
        this.iview = (IView) view;
        initComponents();
    }

    private void initComponents() {
        setResizable(false);
        setTitle("Fungorium");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // CardLayout inicializálása
        cardLayout = new CardLayout();
        gamePanel = new JPanel(cardLayout);

        // Menu panel hozzáadása
        menuPanel = createMenuPanel();
        gamePanel.add(menuPanel, MENU_CARD);

        // Options panel hozzáadása
        optionsPanel = createOptionsPanel();
        gamePanel.add(optionsPanel, OPTIONS_CARD);

        // Play panel hozzáadása
        playPanel = createPlayPanel();
        gamePanel.add(playPanel, PLAY_CARD);

        // gamePanel hozzáadása a contentPane-hez
        contentPane.add(gamePanel, BorderLayout.CENTER);

        // Ablak beállításai
        pack();
        setLocationRelativeTo(getOwner());
        // getOwner() vagy null
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setPreferredSize(new Dimension(1020, 555));

        // Komponensek inicializálása
        JLabel gameNameLb = new JLabel();
        JLabel teamNameLb = new JLabel();
        JButton playBt = new JButton();
        JButton loadBt = new JButton();
        JButton saveBt = new JButton();
        JButton optionsBt = new JButton();
        JButton quitGameBt = new JButton();

        // gameNameLb beállításai
        gameNameLb.setText("FUNGORIUM");
        gameNameLb.setFont(new Font("Segoe UI", Font.BOLD, 48));
        menuPanel.add(gameNameLb);
        gameNameLb.setBounds(365, 40, 294, 64);

        // teamNameLb beállításai
        teamNameLb.setText("Arviz");
        teamNameLb.setFont(new Font("Segoe UI", Font.BOLD, 22));
        menuPanel.add(teamNameLb);
        teamNameLb.setBounds(480, 95, 54, 30);

        // playBt beállításai
        playBt.setText("Play");
        playBt.setFont(new Font("Segoe UI", Font.BOLD, 22));
        playBt.setFocusPainted(false);
        menuPanel.add(playBt);
        playBt.setBounds(365, 160, 295, 40);

        // playBt eseménykezelő
        playBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Váltás a játék kártyára
                cardLayout.show(GameGUI.this.gamePanel, PLAY_CARD);
                RefreshSelectableEntities();
            }
        });

        // loadBt beállításai
        loadBt.setText("Load");
        loadBt.setFont(new Font("Segoe UI", Font.BOLD, 22));
        loadBt.setFocusPainted(false);
        menuPanel.add(loadBt);
        loadBt.setBounds(365, 210, 295, 40);

        // loadBt eseménykezelő
        loadBt.addActionListener(e -> {
            // JTextField a fájlnévhez
            JTextField fileNameField = new JTextField(20);
            JPanel inputPanel = new JPanel();
            inputPanel.add(new JLabel("File name (.txt):"));
            inputPanel.add(fileNameField);

            // JOptionPane megjelenítése
            int result = JOptionPane.showConfirmDialog(
                    null,
                    inputPanel,
                    "Load game",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            // Ha az OK gombra kattintottak, mentsük el a szöveget
            if (result == JOptionPane.OK_OPTION) {
                String fileName = fileNameField.getText();
                if (!fileName.trim().isEmpty()) {
                    System.out.print("/load " + fileName.trim() + "\n");
                    boolean success = iview.load(fileName.trim());
                    System.out.print(">");
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Load game successful.", "Successful execution message",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Load game failed!", "Error message",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Empty filename!", "Error message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // saveBt beállításai
        saveBt.setText("Save");
        saveBt.setFont(new Font("Segoe UI", Font.BOLD, 22));
        saveBt.setFocusPainted(false);
        menuPanel.add(saveBt);
        saveBt.setBounds(365, 260, 295, 40);

        // saveBt eseménykezelő
        saveBt.addActionListener(e -> {
            // JTextField a fájlnévhez
            JTextField fileNameField = new JTextField(20);
            JPanel inputPanel = new JPanel();
            inputPanel.add(new JLabel("File name (.txt):"));
            inputPanel.add(fileNameField);

            // JOptionPane megjelenítése
            int result = JOptionPane.showConfirmDialog(
                    null,
                    inputPanel,
                    "Save game",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            // Ha az OK gombra kattintottak, mentsük el a szöveget
            if (result == JOptionPane.OK_OPTION) {
                String fileName = fileNameField.getText();
                if (!fileName.trim().isEmpty()) {
                    System.out.print("/save " + fileName.trim() + "\n");
                    boolean success = iview.save(fileName.trim());
                    System.out.print(">");
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Save game successful.", "Successful execution message",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Save game failed!", "Error message",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Empty filename!", "Error message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // optionsBt beállításai
        optionsBt.setText("Options...");
        optionsBt.setFont(new Font("Segoe UI", Font.BOLD, 22));
        optionsBt.setFocusPainted(false);
        menuPanel.add(optionsBt);
        optionsBt.setBounds(365, 340, 140, 40);

        // optionsBt eseménykezelő
        optionsBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Váltás a beállítások kártyára
                cardLayout.show(GameGUI.this.gamePanel, OPTIONS_CARD);
            }
        });

        // quitGameBt beállításai
        quitGameBt.setText("Quit game");
        quitGameBt.setFont(new Font("Segoe UI", Font.BOLD, 22));
        quitGameBt.setFocusPainted(false);
        menuPanel.add(quitGameBt);
        quitGameBt.setBounds(515, 340, 145, 40);

        // quitGameBt eseménykezelő
        quitGameBt.addActionListener(e -> {
            System.out.print("/exit\n");
            System.out.println("#See you later and have a nice day!");
            System.exit(0);
        });

        return menuPanel;
    }

    private JPanel createOptionsPanel() {
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(null);
        optionsPanel.setPreferredSize(new Dimension(1020, 555));

        // Komponensek inicializálása
        JButton resetGameBt = new JButton();
        JLabel maxTurnsLb = new JLabel();
        JSpinner maxTurnsSpinner = new JSpinner();
        JButton randomBt = new JButton();
        JButton turnsBt = new JButton();
        JButton executeScriptBt = new JButton();
        JLabel executeScriptLb = new JLabel();
        JTextField executeScriptTF = new JTextField();
        JButton listTectonsBt = new JButton();
        JButton listFungiBt = new JButton();
        JButton listInsectColoniesBt = new JButton();
        JButton listFungusBodiesBt = new JButton();
        JButton listHyphaeBt = new JButton();
        JButton listSporesBt = new JButton();
        JButton listInsectsBt = new JButton();
        JSeparator separator1 = new JSeparator();
        JButton addTectonBt = new JButton();
        JLabel addtTectonNameLb = new JLabel();
        JTextField addtTectonNameTF = new JTextField();
        JLabel addtTectonTypeLb = new JLabel();
        JComboBox<String> addtTectonTypeCB = new JComboBox<>();
        JButton addFungusBt = new JButton();
        JLabel addfFungusNameLb = new JLabel();
        JTextField addfFungusNameTF = new JTextField();
        JButton addInsectColonyBt = new JButton();
        JLabel addicInsectColonyNameLb = new JLabel();
        JTextField addicInsectColonyNameTF = new JTextField();
        JLabel addicAmoutOfNutrientsLb = new JLabel();
        JSpinner addicAmoutOfNutrientsSpinner = new JSpinner();
        JButton addFugusBodyBt = new JButton();
        JLabel addfbFungusBodyNameLb = new JLabel();
        JTextField addfbFungusBodyNameTF = new JTextField();
        JLabel addfbFungusNameLb = new JLabel();
        JTextField addfbFungusNameTF = new JTextField();
        JLabel addfbTectonNameLb = new JLabel();
        JTextField addfbTectonNameTF = new JTextField();
        JLabel addfbIsDeadLb = new JLabel();
        JButton addfbIsDeadBt = new JButton();
        JLabel addfbAgeLb = new JLabel();
        JSpinner addfbAgeSpinner = new JSpinner();
        JLabel addfbIsDevelopedLb = new JLabel();
        JButton addfbIsDevelopedBt = new JButton();
        JLabel addfbSporeCountLb = new JLabel();
        JSpinner addfbSporeCountSpinner = new JSpinner();
        JLabel addfbMaxShootLb = new JLabel();
        JSpinner addfbMaxShootSpinner = new JSpinner();
        JButton addHyphaBt = new JButton();
        JLabel addhHyphaNameLb = new JLabel();
        JTextField addhHyphaNameTF = new JTextField();
        JLabel addhFungusNameLb = new JLabel();
        JTextField addhFungusNameTF = new JTextField();
        JLabel addhTectonNameStartLb = new JLabel();
        JTextField addhTectonNameStartTF = new JTextField();
        JLabel addhTectonNameEndLb = new JLabel();
        JTextField addhTectonNameEndTF = new JTextField();
        JButton addSporeBt = new JButton();
        JLabel addsSporeNameLb = new JLabel();
        JTextField addsSporeNameTF = new JTextField();
        JLabel addsFungusNameLb = new JLabel();
        JTextField addsFungusNameTF = new JTextField();
        JLabel addsTectonNameLb = new JLabel();
        JTextField addsTectonNameTF = new JTextField();
        JLabel addsSporeTypeLb = new JLabel();
        JComboBox<String> addsSporeTypeCB = new JComboBox<>();
        JLabel addsNutritionalValueLb = new JLabel();
        JSpinner addsNutritionalValueSpinner = new JSpinner();
        JLabel addsEffectDurationLb = new JLabel();
        JSpinner addsEffectDurationSpinner = new JSpinner();
        JButton addInsectBt = new JButton();
        JLabel addiInsectNameLb = new JLabel();
        JTextField addiInsectNameTF = new JTextField();
        JLabel addiInsectColonyNameLb = new JLabel();
        JTextField addiInsectColonyNameTF = new JTextField();
        JLabel addiTectonNameLb = new JLabel();
        JTextField addiTectonNameTF = new JTextField();
        JLabel addiInsectMaxMoveLb = new JLabel();
        JSpinner addiInsectMaxMoveSpinner = new JSpinner();
        JLabel addiCutAbilityLb = new JLabel();
        JButton addiCutAbilityBt = new JButton();
        JLabel addiEffectTimeLeftLb = new JLabel();
        JSpinner addiEffectTimeLeftSpinner = new JSpinner();
        JLabel addiEatenByLb = new JLabel();
        JTextField addiEatenByTF = new JTextField();
        JSeparator separator2 = new JSeparator();
        JButton altTectonBt = new JButton();
        JLabel alttTectonNameLb = new JLabel();
        JTextField alttTectonNameTF = new JTextField();
        JLabel alttNewNeighbourTectonNameLb = new JLabel();
        JTextField alttNewNeighbourTectonNameTF = new JTextField();
        JSeparator separator4 = new JSeparator();
        JButton altHyphaBt = new JButton();
        JLabel althHyphaNameLb = new JLabel();
        JTextField althHyphaNameTF = new JTextField();
        JLabel althNewNeighbourHyphaNameLb = new JLabel();
        JTextField althNewNeighbourHyphaNameTF = new JTextField();
        JSeparator separator3 = new JSeparator();
        JButton doneBt = new JButton();

        // resetGameBt beállításai
        resetGameBt.setText("Reset game");
        resetGameBt.setFocusPainted(false);
        optionsPanel.add(resetGameBt);
        resetGameBt.setBounds(5, 5, 92, 25);

        // resetGameBt eseménykezelő
        resetGameBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("/rst\n");
                iview.rst();
                System.out.print(">");
                maxTurnsSpinner.setValue(1);
                maxTurnsSpinner.setEnabled(true);
                randomBt.setText("Random: on");
                turnsBt.setText("Turns: on");
            }
        });

        // maxTurnsLb beállításai
        maxTurnsLb.setText("Max turns:");
        optionsPanel.add(maxTurnsLb);
        maxTurnsLb.setBounds(250, 10, 54, 16);

        // maxTurnsSpinner beállításai
        maxTurnsSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
        optionsPanel.add(maxTurnsSpinner);
        maxTurnsSpinner.setBounds(310, 5, 64, 25);

        // maxTurnsSpinner eseménykezelő
        maxTurnsSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                iview.GetGameController().SetMaxRounds((Integer) maxTurnsSpinner.getValue());
            }
        });

        // randomBt beállításai
        randomBt.setText("Random: on");
        randomBt.setFocusPainted(false);
        optionsPanel.add(randomBt);
        randomBt.setBounds(5, 50, 96, 25);

        // randomBt eseménykezelő
        randomBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (randomBt.getText().equals("Random: on")) {
                    System.out.print("/rand d\n");
                    iview.GetGameController().Rand("d");
                    System.out.print(">");
                    randomBt.setText("Random: off");
                } else if (randomBt.getText().equals("Random: off")) {
                    System.out.print("/rand e\n");
                    iview.GetGameController().Rand("e");
                    System.out.print(">");
                    randomBt.setText("Random: on");
                }
            }
        });

        // turnsBt beállításai
        turnsBt.setText("Turns: on");
        turnsBt.setFocusPainted(false);
        optionsPanel.add(turnsBt);
        turnsBt.setBounds(5, 95, 79, 25);

        // turnsBt eseménykezelő
        turnsBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turnsBt.getText().equals("Turns: on")) {
                    System.out.print("/turns d\n");
                    iview.GetGameController().Turns("d");
                    System.out.print(">");
                    turnsBt.setText("Turns: off");
                    maxTurnsSpinner.setEnabled(false);
                } else if (turnsBt.getText().equals("Turns: off")) {
                    System.out.print("/turns e\n");
                    iview.GetGameController().Turns("e");
                    System.out.print(">");
                    turnsBt.setText("Turns: on");
                    maxTurnsSpinner.setEnabled(true);
                }
            }
        });

        // executeScriptBt beállításai
        executeScriptBt.setText("Execute script");
        executeScriptBt.setFocusPainted(false);
        optionsPanel.add(executeScriptBt);
        executeScriptBt.setBounds(5, 140, 103, 25);

        // executeScriptBt eseménykezelő
        executeScriptBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!executeScriptTF.getText().trim().isEmpty()) {
                    System.out.print("/exec " + executeScriptTF.getText().toString() + "\n");
                    boolean success = iview.exec(executeScriptTF.getText().trim().toString());
                    System.out.print(">");
                    if (success) {
                        executeScriptTF.setText("");
                        JOptionPane.showMessageDialog(null, "Script execution successful.",
                                "Successful execution message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Script execution failed!", "Error message",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the name of the script file to be executed!",
                            "Error message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // executeScriptLb beállításai
        executeScriptLb.setText("File name:");
        optionsPanel.add(executeScriptLb);
        executeScriptLb.setBounds(115, 145, 54, 16);
        optionsPanel.add(executeScriptTF);
        executeScriptTF.setBounds(175, 140, 190, 25);

        // listTectonsBt beállításai
        listTectonsBt.setText("List Tectons");
        listTectonsBt.setFocusPainted(false);
        optionsPanel.add(listTectonsBt);
        listTectonsBt.setBounds(5, 185, 92, 25);

        // listTectonsBt eseménykezelő
        listTectonsBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("/lstt\n");
                iview.lstt();
                System.out.print(">");

                // Eredeti System.out mentése
                PrintStream originalOut = System.out;

                // Kimenet átirányítása egy ByteArrayOutputStream-be
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                System.setOut(ps);

                // A konzolra író függvény meghívása
                iview.lstt();

                // Kimenet visszaállítása
                System.setOut(originalOut);

                // Rögzített kimenet String-ként
                String fullOutput = baos.toString();

                // Sorok szétválasztása
                String[] lines = fullOutput.split("\\r?\\n");

                // Első sor elkülönítése és módosítása (biztosan létezik)
                String firstLine = lines[0];
                String modifiedFirstLine = firstLine.length() > 2 ? firstLine.substring(1, firstLine.length() - 1)
                        : firstLine;

                // A többi sor összefűzése (első sor nélkül)
                StringBuilder consoleOutput = new StringBuilder();
                for (int i = 1; i < lines.length; i++) {
                    consoleOutput.append(lines[i]).append("\n");
                }

                // Kimenet megjelenítése JOptionPane-ben, az első sor mint cím
                String outputToShow = consoleOutput.length() > 0 ? consoleOutput.toString() : "The list is empty.";
                JOptionPane.showMessageDialog(null, outputToShow, modifiedFirstLine, JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // listFungiBt beállításai
        listFungiBt.setText("List Fungi");
        listFungiBt.setFocusPainted(false);
        optionsPanel.add(listFungiBt);
        listFungiBt.setBounds(5, 225, 80, 25);

        // listFungiBt eseménykezelő
        listFungiBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("/lstf\n");
                iview.lstf();
                System.out.print(">");

                // Eredeti System.out mentése
                PrintStream originalOut = System.out;

                // Kimenet átirányítása egy ByteArrayOutputStream-be
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                System.setOut(ps);

                // A konzolra író függvény meghívása
                iview.lstf();

                // Kimenet visszaállítása
                System.setOut(originalOut);

                // Rögzített kimenet String-ként
                String fullOutput = baos.toString();

                // Sorok szétválasztása
                String[] lines = fullOutput.split("\\r?\\n");

                // Első sor elkülönítése és módosítása (biztosan létezik)
                String firstLine = lines[0];
                String modifiedFirstLine = firstLine.length() > 2 ? firstLine.substring(1, firstLine.length() - 1)
                        : firstLine;

                // A többi sor összefűzése (első sor nélkül)
                StringBuilder consoleOutput = new StringBuilder();
                for (int i = 1; i < lines.length; i++) {
                    consoleOutput.append(lines[i]).append("\n");
                }

                // Kimenet megjelenítése JOptionPane-ben, az első sor mint cím
                String outputToShow = consoleOutput.length() > 0 ? consoleOutput.toString() : "The list is empty.";
                JOptionPane.showMessageDialog(null, outputToShow, modifiedFirstLine, JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // listInsectColoniesBt beállításai
        listInsectColoniesBt.setText("List Insect Colonies");
        listInsectColoniesBt.setFocusPainted(false);
        optionsPanel.add(listInsectColoniesBt);
        listInsectColoniesBt.setBounds(5, 265, 130, 25);

        // listInsectColoniesBt eseménykezelő
        listInsectColoniesBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("/lstic\n");
                iview.lstic();
                System.out.print(">");

                // Eredeti System.out mentése
                PrintStream originalOut = System.out;

                // Kimenet átirányítása egy ByteArrayOutputStream-be
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                System.setOut(ps);

                // A konzolra író függvény meghívása
                iview.lstic();

                // Kimenet visszaállítása
                System.setOut(originalOut);

                // Rögzített kimenet String-ként
                String fullOutput = baos.toString();

                // Sorok szétválasztása
                String[] lines = fullOutput.split("\\r?\\n");

                // Első sor elkülönítése és módosítása (biztosan létezik)
                String firstLine = lines[0];
                String modifiedFirstLine = firstLine.length() > 2 ? firstLine.substring(1, firstLine.length() - 1)
                        : firstLine;

                // A többi sor összefűzése (első sor nélkül)
                StringBuilder consoleOutput = new StringBuilder();
                for (int i = 1; i < lines.length; i++) {
                    consoleOutput.append(lines[i]).append("\n");
                }

                // Kimenet megjelenítése JOptionPane-ben, az első sor mint cím
                String outputToShow = consoleOutput.length() > 0 ? consoleOutput.toString() : "The list is empty.";
                JOptionPane.showMessageDialog(null, outputToShow, modifiedFirstLine, JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // listFungusBodiesBt beállításai
        listFungusBodiesBt.setText("List Fungus Bodies");
        listFungusBodiesBt.setFocusPainted(false);
        optionsPanel.add(listFungusBodiesBt);
        listFungusBodiesBt.setBounds(5, 305, 127, 25);

        // listFungusBodiesBt eseménykezelő
        listFungusBodiesBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("/lstfb\n");
                iview.lstfb();
                System.out.print(">");

                // Eredeti System.out mentése
                PrintStream originalOut = System.out;

                // Kimenet átirányítása egy ByteArrayOutputStream-be
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                System.setOut(ps);

                // A konzolra író függvény meghívása
                iview.lstfb();

                // Kimenet visszaállítása
                System.setOut(originalOut);

                // Rögzített kimenet String-ként
                String fullOutput = baos.toString();

                // Sorok szétválasztása
                String[] lines = fullOutput.split("\\r?\\n");

                // Első sor elkülönítése és módosítása (biztosan létezik)
                String firstLine = lines[0];
                String modifiedFirstLine = firstLine.length() > 2 ? firstLine.substring(1, firstLine.length() - 1)
                        : firstLine;

                // A többi sor összefűzése (első sor nélkül)
                StringBuilder consoleOutput = new StringBuilder();
                for (int i = 1; i < lines.length; i++) {
                    consoleOutput.append(lines[i]).append("\n");
                }

                // Kimenet megjelenítése JOptionPane-ben, az első sor mint cím
                String outputToShow = consoleOutput.length() > 0 ? consoleOutput.toString() : "The list is empty.";
                JOptionPane.showMessageDialog(null, outputToShow, modifiedFirstLine, JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // listHyphaeBt beállításai
        listHyphaeBt.setText("List Hyphae");
        listHyphaeBt.setFocusPainted(false);
        optionsPanel.add(listHyphaeBt);
        listHyphaeBt.setBounds(5, 345, 92, 25);

        // listHyphaeBt eseménykezelő
        listHyphaeBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("/lsth\n");
                iview.lsth();
                System.out.print(">");

                // Eredeti System.out mentése
                PrintStream originalOut = System.out;

                // Kimenet átirányítása egy ByteArrayOutputStream-be
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                System.setOut(ps);

                // A konzolra író függvény meghívása
                iview.lsth();

                // Kimenet visszaállítása
                System.setOut(originalOut);

                // Rögzített kimenet String-ként
                String fullOutput = baos.toString();

                // Sorok szétválasztása
                String[] lines = fullOutput.split("\\r?\\n");

                // Első sor elkülönítése és módosítása (biztosan létezik)
                String firstLine = lines[0];
                String modifiedFirstLine = firstLine.length() > 2 ? firstLine.substring(1, firstLine.length() - 1)
                        : firstLine;

                // A többi sor összefűzése (első sor nélkül)
                StringBuilder consoleOutput = new StringBuilder();
                for (int i = 1; i < lines.length; i++) {
                    consoleOutput.append(lines[i]).append("\n");
                }

                // Kimenet megjelenítése JOptionPane-ben, az első sor mint cím
                String outputToShow = consoleOutput.length() > 0 ? consoleOutput.toString() : "The list is empty.";
                JOptionPane.showMessageDialog(null, outputToShow, modifiedFirstLine, JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // listSporesBt beállításai
        listSporesBt.setText("List Spores");
        listSporesBt.setFocusPainted(false);
        optionsPanel.add(listSporesBt);
        listSporesBt.setBounds(5, 385, 87, 25);

        // listSporesBt eseménykezelő
        listSporesBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("/lsts\n");
                iview.lsts();
                System.out.print(">");

                // Eredeti System.out mentése
                PrintStream originalOut = System.out;

                // Kimenet átirányítása egy ByteArrayOutputStream-be
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                System.setOut(ps);

                // A konzolra író függvény meghívása
                iview.lsts();

                // Kimenet visszaállítása
                System.setOut(originalOut);

                // Rögzített kimenet String-ként
                String fullOutput = baos.toString();

                // Sorok szétválasztása
                String[] lines = fullOutput.split("\\r?\\n");

                // Első sor elkülönítése és módosítása (biztosan létezik)
                String firstLine = lines[0];
                String modifiedFirstLine = firstLine.length() > 2 ? firstLine.substring(1, firstLine.length() - 1)
                        : firstLine;

                // A többi sor összefűzése (első sor nélkül)
                StringBuilder consoleOutput = new StringBuilder();
                for (int i = 1; i < lines.length; i++) {
                    consoleOutput.append(lines[i]).append("\n");
                }

                // Kimenet megjelenítése JOptionPane-ben, az első sor mint cím
                String outputToShow = consoleOutput.length() > 0 ? consoleOutput.toString() : "The list is empty.";
                JOptionPane.showMessageDialog(null, outputToShow, modifiedFirstLine, JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // listInsectsBt beállításai
        listInsectsBt.setText("List Insects");
        listInsectsBt.setFocusPainted(false);
        optionsPanel.add(listInsectsBt);
        listInsectsBt.setBounds(5, 425, 86, 25);

        // listInsectsBt eseménykezelő
        listInsectsBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("/lsti\n");
                iview.lsti();
                System.out.print(">");

                // Eredeti System.out mentése
                PrintStream originalOut = System.out;

                // Kimenet átirányítása egy ByteArrayOutputStream-be
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                System.setOut(ps);

                // A konzolra író függvény meghívása
                iview.lsti();

                // Kimenet visszaállítása
                System.setOut(originalOut);

                // Rögzített kimenet String-ként
                String fullOutput = baos.toString();

                // Sorok szétválasztása
                String[] lines = fullOutput.split("\\r?\\n");

                // Első sor elkülönítése és módosítása (biztosan létezik)
                String firstLine = lines[0];
                String modifiedFirstLine = firstLine.length() > 2 ? firstLine.substring(1, firstLine.length() - 1)
                        : firstLine;

                // A többi sor összefűzése (első sor nélkül)
                StringBuilder consoleOutput = new StringBuilder();
                for (int i = 1; i < lines.length; i++) {
                    consoleOutput.append(lines[i]).append("\n");
                }

                // Kimenet megjelenítése JOptionPane-ben, az első sor mint cím
                String outputToShow = consoleOutput.length() > 0 ? consoleOutput.toString() : "The list is empty.";
                JOptionPane.showMessageDialog(null, outputToShow, modifiedFirstLine, JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // separator1 beállításai
        separator1.setOrientation(SwingConstants.VERTICAL);
        optionsPanel.add(separator1);
        separator1.setBounds(380, 0, 25, 457);

        // addTectonBt beállításai
        addTectonBt.setText("Add Tecton");
        addTectonBt.setFocusPainted(false);
        optionsPanel.add(addTectonBt);
        addTectonBt.setBounds(410, 5, 92, 25);

        // addTectonBt eseménykezelő
        addTectonBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!addtTectonNameTF.getText().trim().isEmpty()
                        && addtTectonNameTF.getText().trim().matches("^T\\d+$")) {
                    String tectonType = addtTectonTypeCB.getSelectedItem().toString().equals("NarrowTecton") ? "n"
                            : addtTectonTypeCB.getSelectedItem().equals("WideTecton") ? "wi"
                                    : addtTectonTypeCB.getSelectedItem().equals("VitalTecton") ? "v"
                                            : addtTectonTypeCB.getSelectedItem().equals("WeakTecton") ? "we"
                                                    : addtTectonTypeCB.getSelectedItem().equals("BarrenTecton") ? "b"
                                                            : "n";
                    System.out.print("/addt -n " + addtTectonNameTF.getText().trim() + " -t " + tectonType + "\n");
                    boolean success = iview.addt(addtTectonNameTF.getText().trim(), tectonType);
                    System.out.print(">");
                    if (success) {
                        addtTectonNameTF.setText("");
                        addtTectonTypeCB.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(null, "Tecton successfully created.",
                                "Successful execution message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if ((Tecton) iview.getPlanet().get(addtTectonNameTF.getText().trim()) != null) {
                            JOptionPane.showMessageDialog(null, "This name is already taken!", "Error message",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "The operation failed!", "Error message",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the required information correctly!",
                            "Error message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // addtTectonNameLb beállításai
        addtTectonNameLb.setText("Tecton name:");
        optionsPanel.add(addtTectonNameLb);
        addtTectonNameLb.setBounds(510, 10, 71, 16);
        optionsPanel.add(addtTectonNameTF);
        addtTectonNameTF.setBounds(590, 5, 64, 25);

        // addtTectonTypeLb beállításai
        addtTectonTypeLb.setText(", tecton type:");
        optionsPanel.add(addtTectonTypeLb);
        addtTectonTypeLb.setBounds(665, 10, 68, 16);

        // addtTectonTypeCB beállításai
        addtTectonTypeCB.setModel(new DefaultComboBoxModel<>(new String[] {
                "NarrowTecton",
                "WideTecton",
                "VitalTecton",
                "WeakTecton",
                "BarrenTecton"
        }));
        addtTectonTypeCB.setMaximumRowCount(5);
        optionsPanel.add(addtTectonTypeCB);
        addtTectonTypeCB.setBounds(740, 5, 109, 25);

        // addFungusBt beállításai
        addFungusBt.setText("Add Fungus");
        addFungusBt.setFocusPainted(false);
        optionsPanel.add(addFungusBt);
        addFungusBt.setBounds(410, 40, 92, 25);

        // addFungusBt eseménykezelő
        addFungusBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!addfFungusNameTF.getText().trim().isEmpty()
                        && addfFungusNameTF.getText().trim().matches("^F\\d+$")) {
                    System.out.print("/addf -n " + addfFungusNameTF.getText().trim() + "\n");
                    boolean success = iview.addf(addfFungusNameTF.getText().trim());
                    System.out.print(">");
                    if (success) {
                        addfFungusNameTF.setText("");
                        JOptionPane.showMessageDialog(null, "Fungus successfully created.",
                                "Successful execution message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if ((Fungus) iview.getPlanet().get(addfFungusNameTF.getText().trim()) != null) {
                            JOptionPane.showMessageDialog(null, "This name is already taken!", "Error message",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "The operation failed!", "Error message",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the required information correctly!",
                            "Error message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // addfFungusNameLb beállításai
        addfFungusNameLb.setText("Fungus name:");
        optionsPanel.add(addfFungusNameLb);
        addfFungusNameLb.setBounds(510, 45, 72, 16);
        optionsPanel.add(addfFungusNameTF);
        addfFungusNameTF.setBounds(590, 40, 64, 25);

        // addInsectColonyBt beállításai
        addInsectColonyBt.setText("Add Insect Colony");
        addInsectColonyBt.setFocusPainted(false);
        optionsPanel.add(addInsectColonyBt);
        addInsectColonyBt.setBounds(410, 75, 126, 25);

        // addInsectColonyBt eseménykezelő
        addInsectColonyBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!addicInsectColonyNameTF.getText().trim().isEmpty()
                        && addicInsectColonyNameTF.getText().trim().matches("^IC\\d+$")) {
                    System.out.print("/addic -n " + addicInsectColonyNameTF.getText().trim() + " -nv "
                            + (Integer) addicAmoutOfNutrientsSpinner.getValue() + "\n");
                    boolean success = iview.addic(addicInsectColonyNameTF.getText().trim(),
                            (Integer) addicAmoutOfNutrientsSpinner.getValue());
                    System.out.print(">");
                    if (success) {
                        addicInsectColonyNameTF.setText("");
                        addicAmoutOfNutrientsSpinner.setValue(0);
                        JOptionPane.showMessageDialog(null, "Insect Colony successfully created.",
                                "Successful execution message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if ((InsectColony) iview.getPlanet().get(addicInsectColonyNameTF.getText().trim()) != null) {
                            JOptionPane.showMessageDialog(null, "This name is already taken!", "Error message",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "The operation failed!", "Error message",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the required information correctly!",
                            "Error message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // addicInsectColonyNameLb beállításai
        addicInsectColonyNameLb.setText("Insect Colony name:");
        optionsPanel.add(addicInsectColonyNameLb);
        addicInsectColonyNameLb.setBounds(545, 80, 106, 16);
        optionsPanel.add(addicInsectColonyNameTF);
        addicInsectColonyNameTF.setBounds(660, 75, 64, 25);

        // addicAmoutOfNutrientsLb beállításai
        addicAmoutOfNutrientsLb.setText(", amount of nutrients collected:");
        optionsPanel.add(addicAmoutOfNutrientsLb);
        addicAmoutOfNutrientsLb.setBounds(735, 80, 164, 16);

        // addicAmoutOfNutrientsSpinner beállításai
        addicAmoutOfNutrientsSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
        optionsPanel.add(addicAmoutOfNutrientsSpinner);
        addicAmoutOfNutrientsSpinner.setBounds(910, 75, 64, 25);

        // addFugusBodyBt beállításai
        addFugusBodyBt.setText("Add Fungus Body");
        addFugusBodyBt.setFocusPainted(false);
        optionsPanel.add(addFugusBodyBt);
        addFugusBodyBt.setBounds(410, 110, 123, 25);

        // addFugusBodyBt eseménykezelő
        addFugusBodyBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!addfbFungusBodyNameTF.getText().trim().isEmpty()
                        && addfbFungusBodyNameTF.getText().trim().matches("^FB\\d+$") &&
                        !addfbFungusNameTF.getText().trim().isEmpty()
                        && addfbFungusNameTF.getText().trim().matches("^F\\d+$")
                        && (Fungus) iview.getPlanet().get(addfbFungusNameTF.getText().trim()) != null &&
                        !addfbTectonNameTF.getText().trim().isEmpty()
                        && addfbTectonNameTF.getText().trim().matches("^T\\d+$")
                        && (Tecton) iview.getPlanet().get(addfbTectonNameTF.getText().trim()) != null) {
                    System.out.print("/addfb -n " + addfbFungusBodyNameTF.getText().trim() + " -f "
                            + addfbFungusNameTF.getText().trim() + " -t " + addfbTectonNameTF.getText().trim() + " -d "
                            + (addfbIsDeadBt.getText().equals("IsDead: no") ? "n" : "y")
                            + " -a " + (Integer) addfbAgeSpinner.getValue() + " -dv "
                            + (addfbIsDevelopedBt.getText().equals("IsDeveloped: no") ? "n" : "y") + " -sc "
                            + (Integer) addfbSporeCountSpinner.getValue() + " -sl "
                            + (Integer) addfbMaxShootSpinner.getValue() + "\n");
                    boolean success = iview.addfb(addfbFungusBodyNameTF.getText().trim(),
                            (Tecton) iview.getPlanet().get(addfbTectonNameTF.getText().trim()),
                            (Fungus) iview.getPlanet().get(addfbFungusNameTF.getText().trim()),
                            addfbIsDevelopedBt.getText().equals("IsDeveloped: no") ? false : true,
                            (Integer) addfbAgeSpinner.getValue(),
                            addfbIsDeadBt.getText().equals("IsDead: no") ? false : true,
                            (Integer) addfbSporeCountSpinner.getValue(), (Integer) addfbMaxShootSpinner.getValue());
                    System.out.print(">");
                    if (success) {
                        addfbFungusBodyNameTF.setText("");
                        addfbTectonNameTF.setText("");
                        addfbFungusNameTF.setText("");
                        addfbIsDevelopedBt.setText("IsDeveloped: no");
                        addfbAgeSpinner.setValue(0);
                        addfbIsDeadBt.setText("IsDead: no");
                        addfbSporeCountSpinner.setValue(0);
                        addfbMaxShootSpinner.setValue(4);
                        JOptionPane.showMessageDialog(null, "Fungus Body successfully created.",
                                "Successful execution message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if ((FungusBody) iview.getPlanet().get(addfbFungusBodyNameTF.getText().trim()) != null) {
                            JOptionPane.showMessageDialog(null, "This name is already taken!", "Error message",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "The operation failed!", "Error message",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the required information correctly!",
                            "Error message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // addfbFungusBodyNameLb beállításai
        addfbFungusBodyNameLb.setText("Fungus Body name:");
        optionsPanel.add(addfbFungusBodyNameLb);
        addfbFungusBodyNameLb.setBounds(540, 115, 102, 16);
        optionsPanel.add(addfbFungusBodyNameTF);
        addfbFungusBodyNameTF.setBounds(650, 110, 64, 25);

        // addfbFungusNameLb beállításai
        addfbFungusNameLb.setText(", Fungus name:");
        optionsPanel.add(addfbFungusNameLb);
        addfbFungusNameLb.setBounds(725, 115, 78, 16);
        optionsPanel.add(addfbFungusNameTF);
        addfbFungusNameTF.setBounds(810, 110, 64, 25);

        // addfbTectonNameLb beállításai
        addfbTectonNameLb.setText(", Tecton name:");
        optionsPanel.add(addfbTectonNameLb);
        addfbTectonNameLb.setBounds(540, 150, 77, 16);
        optionsPanel.add(addfbTectonNameTF);
        addfbTectonNameTF.setBounds(625, 145, 64, 25);

        // addfbIsDeadLb beállításai
        addfbIsDeadLb.setText(", ");
        optionsPanel.add(addfbIsDeadLb);
        addfbIsDeadLb.setBounds(700, 150, 20, 16);

        // addfbIsDeadBt beállításai
        addfbIsDeadBt.setText("IsDead: no");
        addfbIsDeadBt.setFocusPainted(false);
        optionsPanel.add(addfbIsDeadBt);
        addfbIsDeadBt.setBounds(715, 145, 89, 25);
        addfbIsDeadBt.setEnabled(false);

        // addfbAgeLb beállításai
        addfbAgeLb.setText(", age:");
        optionsPanel.add(addfbAgeLb);
        addfbAgeLb.setBounds(810, 150, 28, 16);

        // addfbAgeSpinner beállításai
        addfbAgeSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
        optionsPanel.add(addfbAgeSpinner);
        addfbAgeSpinner.setBounds(845, 145, 64, 25);

        // addfbAgeSpinner eseménykezelő
        addfbAgeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Integer ageValue = (Integer) addfbAgeSpinner.getValue();
                Integer maxShootValue = (Integer) addfbMaxShootSpinner.getValue();
                if (ageValue >= 5) {
                    addfbIsDevelopedBt.setText("IsDeveloped: yes");
                } else {
                    addfbIsDevelopedBt.setText("IsDeveloped: no");
                }
                if (maxShootValue == 0) {
                    addfbIsDeadBt.setText("IsDead: yes");
                } else {
                    addfbIsDeadBt.setText("IsDead: no");
                }
            }
        });

        // addfbIsDevelopedLb beállításai
        addfbIsDevelopedLb.setText(", ");
        optionsPanel.add(addfbIsDevelopedLb);
        addfbIsDevelopedLb.setBounds(920, 150, 25, 16);

        // addfbIsDevelopedBt beállításai
        addfbIsDevelopedBt.setText("IsDeveloped: no");
        addfbIsDevelopedBt.setFocusPainted(false);
        optionsPanel.add(addfbIsDevelopedBt);
        addfbIsDevelopedBt.setBounds(540, 180, 119, 25);
        addfbIsDevelopedBt.setEnabled(false);

        // addfbSporeCountLb beállításai
        addfbSporeCountLb.setText(", spore count:");
        optionsPanel.add(addfbSporeCountLb);
        addfbSporeCountLb.setBounds(665, 185, 74, 16);

        // addfbSporeCountSpinner beállításai
        addfbSporeCountSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
        optionsPanel.add(addfbSporeCountSpinner);
        addfbSporeCountSpinner.setBounds(745, 180, 64, 25);

        // addfbMaxShootLb beállításai
        addfbMaxShootLb.setText(", max shoots:");
        optionsPanel.add(addfbMaxShootLb);
        addfbMaxShootLb.setBounds(820, 185, 68, 16);

        // addfbMaxShootSpinner beállításai
        addfbMaxShootSpinner.setModel(new SpinnerNumberModel(4, 0, null, 1));
        optionsPanel.add(addfbMaxShootSpinner);
        addfbMaxShootSpinner.setBounds(895, 180, 64, 25);

        // addfbMaxShootSpinner eseménykezelő
        addfbMaxShootSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Integer maxShootValue = (Integer) addfbMaxShootSpinner.getValue();
                if (maxShootValue == 0) {
                    addfbIsDeadBt.setText("IsDead: yes");
                } else {
                    addfbIsDeadBt.setText("IsDead: no");
                }
            }
        });

        // addHyphaBt beállításai
        addHyphaBt.setText("Add Hypha");
        addHyphaBt.setFocusPainted(false);
        optionsPanel.add(addHyphaBt);
        addHyphaBt.setBounds(410, 215, 90, 25);

        // addHyphaBt eseménykezelő
        addHyphaBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!addhHyphaNameTF.getText().trim().isEmpty() && addhHyphaNameTF.getText().trim().matches("^H\\d+$")
                        &&
                        !addhFungusNameTF.getText().trim().isEmpty()
                        && addhFungusNameTF.getText().trim().matches("^F\\d+$")
                        && (Fungus) iview.getPlanet().get(addhFungusNameTF.getText().trim()) != null &&
                        !addhTectonNameStartTF.getText().trim().isEmpty()
                        && addhTectonNameStartTF.getText().trim().matches("^T\\d+$")
                        && (Tecton) iview.getPlanet().get(addhTectonNameStartTF.getText().trim()) != null &&
                        (addhTectonNameEndTF.getText().trim().isEmpty() ? true
                                : addhTectonNameEndTF.getText().trim().matches("^T\\d+$") && (Tecton) iview.getPlanet()
                                        .get(addhTectonNameEndTF.getText().trim()) != null)) {
                    System.out.print("/addh -n " + addhHyphaNameTF.getText().trim() + " -f "
                            + addhFungusNameTF.getText().trim() + " -ts " + addhTectonNameStartTF.getText().trim()
                            + (addhTectonNameEndTF.getText().trim().isEmpty() ? ""
                                    : " -tn " + addhTectonNameEndTF.getText().trim())
                            + "\n");
                    boolean success = iview.addh(addhHyphaNameTF.getText().trim(),
                            (Fungus) iview.getPlanet().get(addhFungusNameTF.getText().trim()),
                            (Tecton) iview.getPlanet().get(addhTectonNameStartTF.getText().trim()),
                            (addhTectonNameEndTF.getText().trim().isEmpty() ? null
                                    : (Tecton) iview.getPlanet().get(addhTectonNameEndTF.getText().trim())));
                    System.out.print(">");
                    if (success) {
                        addhHyphaNameTF.setText("");
                        addhFungusNameTF.setText("");
                        addhTectonNameStartTF.setText("");
                        addhTectonNameEndTF.setText("");
                        JOptionPane.showMessageDialog(null, "Hypha successfully created.",
                                "Successful execution message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if ((Hypha) iview.getPlanet().get(addhHyphaNameTF.getText().trim()) != null) {
                            JOptionPane.showMessageDialog(null, "This name is already taken!", "Error message",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "The operation failed!", "Error message",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the required information correctly!",
                            "Error message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // addhHyphaNameLb beállításai
        addhHyphaNameLb.setText("Hypha name:");
        optionsPanel.add(addhHyphaNameLb);
        addhHyphaNameLb.setBounds(510, 220, 70, 16);
        optionsPanel.add(addhHyphaNameTF);
        addhHyphaNameTF.setBounds(585, 215, 64, 25);

        // addhFungusNameLb beállításai
        addhFungusNameLb.setText(", Fungus name:");
        optionsPanel.add(addhFungusNameLb);
        addhFungusNameLb.setBounds(655, 220, 78, 16);
        optionsPanel.add(addhFungusNameTF);
        addhFungusNameTF.setBounds(740, 215, 64, 25);

        // addhTectonNameStartLb beállításai
        addhTectonNameStartLb.setText(", Tecton name (start):");
        optionsPanel.add(addhTectonNameStartLb);
        addhTectonNameStartLb.setBounds(810, 220, 111, 16);
        optionsPanel.add(addhTectonNameStartTF);
        addhTectonNameStartTF.setBounds(930, 215, 64, 25);

        // addhTectonNameEndLb beállításai
        addhTectonNameEndLb.setText(", Tecton name (end if has):");
        optionsPanel.add(addhTectonNameEndLb);
        addhTectonNameEndLb.setBounds(510, 255, 139, 16);
        optionsPanel.add(addhTectonNameEndTF);
        addhTectonNameEndTF.setBounds(655, 250, 64, 25);

        // addSporeBt beállításai
        addSporeBt.setText("Add Spore");
        addSporeBt.setFocusPainted(false);
        optionsPanel.add(addSporeBt);
        addSporeBt.setBounds(410, 285, 87, 25);

        // addSporeBt eseménykezelő
        addSporeBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!addsSporeNameTF.getText().trim().isEmpty() && addsSporeNameTF.getText().trim().matches("^S\\d+$")
                        &&
                        !addsFungusNameTF.getText().trim().isEmpty()
                        && addsFungusNameTF.getText().trim().matches("^F\\d+$")
                        && (Fungus) iview.getPlanet().get(addsFungusNameTF.getText().trim()) != null &&
                        !addsTectonNameTF.getText().trim().isEmpty()
                        && addsTectonNameTF.getText().trim().matches("^T\\d+$")
                        && (Tecton) iview.getPlanet().get(addsTectonNameTF.getText().trim()) != null) {
                    String sporeType = addsSporeTypeCB.getSelectedItem().toString().equals("Spore") ? "s"
                            : addsSporeTypeCB.getSelectedItem().equals("SpeedSpore") ? "sd"
                                    : addsSporeTypeCB.getSelectedItem().equals("SplitSpore") ? "st"
                                            : addsSporeTypeCB.getSelectedItem().equals("SlowSpore") ? "sw"
                                                    : addsSporeTypeCB.getSelectedItem().equals("DisarmSpore") ? "dm"
                                                            : addsSporeTypeCB.getSelectedItem().equals("StunSpore")
                                                                    ? "sn"
                                                                    : "s";
                    System.out.print("/adds -n " + addsSporeNameTF.getText().trim() + " -f "
                            + addsFungusNameTF.getText().trim() + " -tn " + addsTectonNameTF.getText().trim() + " -t "
                            + sporeType + " -nv " + (Integer) addsNutritionalValueSpinner.getValue() + " -ed "
                            + (Integer) addsEffectDurationSpinner.getValue() + "\n");
                    boolean success = iview.adds(addsSporeNameTF.getText().trim(), sporeType,
                            (Integer) addsNutritionalValueSpinner.getValue(),
                            (Integer) addsEffectDurationSpinner.getValue(),
                            (Fungus) iview.getPlanet().get(addsFungusNameTF.getText().trim()),
                            (Tecton) iview.getPlanet().get(addsTectonNameTF.getText().trim()));
                    System.out.print(">");
                    if (success) {
                        addsSporeNameTF.setText("");
                        addsFungusNameTF.setText("");
                        addsTectonNameTF.setText("");
                        addsSporeTypeCB.setSelectedIndex(0);
                        addsNutritionalValueSpinner.setValue(5);
                        addsEffectDurationSpinner.setValue(0);
                        JOptionPane.showMessageDialog(null, "Spore successfully created.",
                                "Successful execution message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if ((Hypha) iview.getPlanet().get(addhHyphaNameTF.getText().trim()) != null) {
                            JOptionPane.showMessageDialog(null, "This name is already taken!", "Error message",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "The operation failed!", "Error message",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the required information correctly!",
                            "Error message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // addsSporeNameLb beállításai
        addsSporeNameLb.setText("Spore name:");
        optionsPanel.add(addsSporeNameLb);
        addsSporeNameLb.setBounds(505, 290, 66, 16);
        optionsPanel.add(addsSporeNameTF);
        addsSporeNameTF.setBounds(580, 285, 64, 25);

        // addsFungusNameLb beállításai
        addsFungusNameLb.setText(", Fungus name:");
        optionsPanel.add(addsFungusNameLb);
        addsFungusNameLb.setBounds(650, 290, 78, 16);
        optionsPanel.add(addsFungusNameTF);
        addsFungusNameTF.setBounds(735, 285, 64, 25);

        // addsTectonNameLb beállításai
        addsTectonNameLb.setText(", Tecton name:");
        optionsPanel.add(addsTectonNameLb);
        addsTectonNameLb.setBounds(805, 290, 77, 16);
        optionsPanel.add(addsTectonNameTF);
        addsTectonNameTF.setBounds(890, 285, 64, 25);

        // addsSporeTypeLb beállításai
        addsSporeTypeLb.setText(", Spore type:");
        optionsPanel.add(addsSporeTypeLb);
        addsSporeTypeLb.setBounds(505, 325, 66, 16);

        // addsSporeTypeCB beállításai
        addsSporeTypeCB.setModel(new DefaultComboBoxModel<>(new String[] {
                "Spore",
                "SpeedSpore",
                "SplitSpore",
                "SlowSpore",
                "DisarmSpore",
                "StunSpore"
        }));
        addsSporeTypeCB.setMaximumRowCount(6);
        optionsPanel.add(addsSporeTypeCB);
        addsSporeTypeCB.setBounds(575, 320, 103, 25);

        // addsNutritionalValueLb beállításai
        addsNutritionalValueLb.setText(", nutritional value:");
        optionsPanel.add(addsNutritionalValueLb);
        addsNutritionalValueLb.setBounds(685, 325, 94, 16);

        // addsNutritionalValueSpinner beállításai
        addsNutritionalValueSpinner.setModel(new SpinnerNumberModel(5, 0, null, 1));
        optionsPanel.add(addsNutritionalValueSpinner);
        addsNutritionalValueSpinner.setBounds(785, 320, 64, 25);

        // addsEffectDurationLb beállításai
        addsEffectDurationLb.setText(", effect duration:");
        optionsPanel.add(addsEffectDurationLb);
        addsEffectDurationLb.setBounds(855, 325, 86, 16);

        // addsEffectDurationSpinner beállításai
        addsEffectDurationSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
        optionsPanel.add(addsEffectDurationSpinner);
        addsEffectDurationSpinner.setBounds(950, 320, 64, 25);

        // addInsectBt beállításai
        addInsectBt.setText("Add Insect");
        addInsectBt.setFocusPainted(false);
        optionsPanel.add(addInsectBt);
        addInsectBt.setBounds(410, 355, 86, 25);

        // addInsectBt eseménykezelő
        addInsectBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!addiInsectNameTF.getText().trim().isEmpty() && addiInsectNameTF.getText().trim().matches("^I\\d+$")
                        &&
                        !addiInsectColonyNameTF.getText().trim().isEmpty()
                        && addiInsectColonyNameTF.getText().trim().matches("^IC\\d+$")
                        && (InsectColony) iview.getPlanet().get(addiInsectColonyNameTF.getText().trim()) != null &&
                        !addiTectonNameTF.getText().trim().isEmpty()
                        && addiTectonNameTF.getText().trim().matches("^T\\d+$")
                        && (Tecton) iview.getPlanet().get(addiTectonNameTF.getText().trim()) != null &&
                        (addiEatenByTF.getText().trim().isEmpty() ? true
                                : addiEatenByTF.getText().trim().matches("^F\\d+$")
                                        && (Fungus) iview.getPlanet().get(addiEatenByTF.getText().trim()) != null)) {
                    System.out.print("/addi -n " + addiInsectNameTF.getText().trim() + " -ic "
                            + addiInsectColonyNameTF.getText().trim() + " -t " + addiTectonNameTF.getText().trim()
                            + " -sd " + (Integer) addiInsectMaxMoveSpinner.getValue() + " -ca "
                            + (addiCutAbilityBt.getText().equals("CutAbility: yes") ? "y" : "n") +
                            " -et " + (Integer) addiEffectTimeLeftSpinner.getValue()
                            + (addiEatenByTF.getText().trim().isEmpty() ? "" : " -eb " + addiEatenByTF.getText().trim())
                            + "\n");
                    boolean success = iview.addi(addiInsectNameTF.getText().trim(),
                            (Integer) addiInsectMaxMoveSpinner.getValue(),
                            (addiCutAbilityBt.getText().equals("CutAbility: yes") ? true : false),
                            (Integer) addiEffectTimeLeftSpinner.getValue(),
                            (InsectColony) iview.getPlanet().get(addiInsectColonyNameTF.getText().trim()),
                            (Tecton) iview.getPlanet().get(addiTectonNameTF.getText().trim()),
                            ((Fungus) iview.getPlanet().get(addiEatenByTF.getText().trim()) != null
                                    ? (Fungus) iview.getPlanet().get(addiEatenByTF.getText().trim())
                                    : null));
                    System.out.print(">");
                    if (success) {
                        addiInsectNameTF.setText("");
                        addiInsectColonyNameTF.setText("");
                        addiTectonNameTF.setText("");
                        addiEatenByTF.setText("");
                        addiInsectMaxMoveSpinner.setValue(2);
                        addiEffectTimeLeftSpinner.setValue(0);
                        addiCutAbilityBt.setText("CutAbility: yes");
                        JOptionPane.showMessageDialog(null, "Insect successfully created.",
                                "Successful execution message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if ((Hypha) iview.getPlanet().get(addhHyphaNameTF.getText().trim()) != null) {
                            JOptionPane.showMessageDialog(null, "This name is already taken!", "Error message",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "The operation failed!", "Error message",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the required information correctly!",
                            "Error message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // addiInsectNameLb beállításai
        addiInsectNameLb.setText("Insect name:");
        optionsPanel.add(addiInsectNameLb);
        addiInsectNameLb.setBounds(505, 360, 66, 16);
        optionsPanel.add(addiInsectNameTF);
        addiInsectNameTF.setBounds(580, 355, 64, 25);

        // addiInsectColonyNameLb beállításai
        addiInsectColonyNameLb.setText(", Insect Colony name:");
        optionsPanel.add(addiInsectColonyNameLb);
        addiInsectColonyNameLb.setBounds(650, 360, 111, 16);
        optionsPanel.add(addiInsectColonyNameTF);
        addiInsectColonyNameTF.setBounds(770, 355, 64, 25);

        // addiTectonNameLb beállításai
        addiTectonNameLb.setText(", Tecton name:");
        optionsPanel.add(addiTectonNameLb);
        addiTectonNameLb.setBounds(840, 360, 77, 16);
        optionsPanel.add(addiTectonNameTF);
        addiTectonNameTF.setBounds(925, 355, 64, 25);

        // addiInsectMaxMoveLb beállításai
        addiInsectMaxMoveLb.setText(", Insect max move:");
        optionsPanel.add(addiInsectMaxMoveLb);
        addiInsectMaxMoveLb.setBounds(505, 395, 97, 16);

        // addiInsectMaxMoveSpinner beállításai
        addiInsectMaxMoveSpinner.setModel(new SpinnerNumberModel(2, 0, null, 1));
        optionsPanel.add(addiInsectMaxMoveSpinner);
        addiInsectMaxMoveSpinner.setBounds(610, 390, 64, 25);

        // addiCutAbilityLb beállításai
        addiCutAbilityLb.setText(", ");
        optionsPanel.add(addiCutAbilityLb);
        addiCutAbilityLb.setBounds(680, 395, 20, 16);

        // addiCutAbilityBt beállításai
        addiCutAbilityBt.setText("CutAbility: yes");
        addiCutAbilityBt.setFocusPainted(false);
        optionsPanel.add(addiCutAbilityBt);
        addiCutAbilityBt.setBounds(690, 390, 104, 25);

        // addiCutAbilityBt eseménykezelő
        addiCutAbilityBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addiCutAbilityBt.getText().equals("CutAbility: yes")) {
                    addiCutAbilityBt.setText("CutAbility: no");
                } else {
                    addiCutAbilityBt.setText("CutAbility: yes");
                }
            }
        });

        // addiEffectTimeLeftLb beállításai
        addiEffectTimeLeftLb.setText(", effect time left:");
        optionsPanel.add(addiEffectTimeLeftLb);
        addiEffectTimeLeftLb.setBounds(800, 395, 86, 16);

        // addiEffectTimeLeftSpinner beállításai
        addiEffectTimeLeftSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
        optionsPanel.add(addiEffectTimeLeftSpinner);
        addiEffectTimeLeftSpinner.setBounds(895, 390, 64, 25);

        // addiEatenByLb beállításai
        addiEatenByLb.setText(", eaten by (Fungus):");
        optionsPanel.add(addiEatenByLb);
        addiEatenByLb.setBounds(505, 430, 102, 16);
        optionsPanel.add(addiEatenByTF);
        addiEatenByTF.setBounds(615, 425, 64, 25);

        // separator2 beállításai
        optionsPanel.add(separator2);
        separator2.setBounds(0, 455, 1020, 25);

        // altTectonBt beállításai
        altTectonBt.setText("Alter Tecton");
        altTectonBt.setFocusPainted(false);
        optionsPanel.add(altTectonBt);
        altTectonBt.setBounds(15, 475, 95, 25);

        // altTectonBt eseménykezelő
        altTectonBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!alttTectonNameTF.getText().trim().isEmpty() && alttTectonNameTF.getText().trim().matches("^T\\d+$")
                        && (Tecton) iview.getPlanet().get(alttTectonNameTF.getText().trim()) != null &&
                        !alttNewNeighbourTectonNameTF.getText().trim().isEmpty()
                        && alttNewNeighbourTectonNameTF.getText().trim().matches("^T\\d+$")
                        && (Tecton) iview.getPlanet().get(alttNewNeighbourTectonNameTF.getText().trim()) != null) {
                    System.out.print("/altt -n " + alttTectonNameTF.getText().trim() + " -nh "
                            + alttNewNeighbourTectonNameTF.getText().trim() + "\n");
                    boolean success = iview.altt((Tecton) iview.getPlanet().get(alttTectonNameTF.getText().trim()),
                            (Tecton) iview.getPlanet().get(alttNewNeighbourTectonNameTF.getText().trim()));
                    System.out.print(">");
                    if (success) {
                        alttTectonNameTF.setText("");
                        alttNewNeighbourTectonNameTF.setText("");
                        JOptionPane.showMessageDialog(null, "Tecton neighborhood successfully established.",
                                "Successful execution message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if (((Tecton) iview.getPlanet().get(alttTectonNameTF.getText().trim())).GetNeighbours()
                                .contains((Tecton) iview.getPlanet()
                                        .get(alttNewNeighbourTectonNameTF.getText().trim()))) {
                            JOptionPane.showMessageDialog(null, "The two tectons are neighbors already!",
                                    "Error message", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "The operation failed!", "Error message",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the required information correctly!",
                            "Error message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // alttTectonNameLb beállításai
        alttTectonNameLb.setText("Tecton name:");
        optionsPanel.add(alttTectonNameLb);
        alttTectonNameLb.setBounds(115, 480, 71, 16);
        optionsPanel.add(alttTectonNameTF);
        alttTectonNameTF.setBounds(195, 475, 64, 25);

        // alttNewNeighbourTectonNameLb beállításai
        alttNewNeighbourTectonNameLb.setText(", new neighbour Tecton name:");
        optionsPanel.add(alttNewNeighbourTectonNameLb);
        alttNewNeighbourTectonNameLb.setBounds(265, 480, 159, 16);
        optionsPanel.add(alttNewNeighbourTectonNameTF);
        alttNewNeighbourTectonNameTF.setBounds(430, 475, 64, 25);

        // separator4 beállításai
        separator4.setOrientation(SwingConstants.VERTICAL);
        optionsPanel.add(separator4);
        separator4.setBounds(510, 457, 25, 65);

        // altHyphaBt beállításai
        altHyphaBt.setText("Alter Hypha");
        altHyphaBt.setFocusPainted(false);
        optionsPanel.add(altHyphaBt);
        altHyphaBt.setBounds(525, 475, 93, 25);

        // altHyphaBt eseménykezelő
        altHyphaBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!althHyphaNameTF.getText().trim().isEmpty() && althHyphaNameTF.getText().trim().matches("^H\\d+$")
                        && (Hypha) iview.getPlanet().get(althHyphaNameTF.getText().trim()) != null &&
                        !althNewNeighbourHyphaNameTF.getText().trim().isEmpty()
                        && althNewNeighbourHyphaNameTF.getText().trim().matches("^H\\d+$")
                        && (Hypha) iview.getPlanet().get(althNewNeighbourHyphaNameTF.getText().trim()) != null) {
                    System.out.print("/alth -n " + althHyphaNameTF.getText().trim() + " -nh "
                            + althNewNeighbourHyphaNameTF.getText().trim() + "\n");
                    boolean success = iview.alth((Hypha) iview.getPlanet().get(althHyphaNameTF.getText().trim()),
                            (Hypha) iview.getPlanet().get(althNewNeighbourHyphaNameTF.getText().trim()));
                    System.out.print(">");
                    if (success) {
                        althHyphaNameTF.setText("");
                        althNewNeighbourHyphaNameTF.setText("");
                        JOptionPane.showMessageDialog(null, "Hypha neighborhood successfully established.",
                                "Successful execution message", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if (((Hypha) iview.getPlanet().get(althHyphaNameTF.getText().trim())).GetNeighbours().contains(
                                (Hypha) iview.getPlanet().get(althNewNeighbourHyphaNameTF.getText().trim()))) {
                            JOptionPane.showMessageDialog(null, "The two hyphae are neighbors already!",
                                    "Error message", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "The operation failed!", "Error message",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the required information correctly!",
                            "Error message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // althHyphaNameLb beállításai
        althHyphaNameLb.setText("Hypha name:");
        optionsPanel.add(althHyphaNameLb);
        althHyphaNameLb.setBounds(625, 480, 70, 16);
        optionsPanel.add(althHyphaNameTF);
        althHyphaNameTF.setBounds(700, 475, 64, 25);

        // althNewNeighbourHyphaNameLb beállításai
        althNewNeighbourHyphaNameLb.setText(", new neighbour Hypha name:");
        optionsPanel.add(althNewNeighbourHyphaNameLb);
        althNewNeighbourHyphaNameLb.setBounds(770, 480, 158, 16);
        optionsPanel.add(althNewNeighbourHyphaNameTF);
        althNewNeighbourHyphaNameTF.setBounds(935, 475, 64, 25);

        // separator3 beállításai
        optionsPanel.add(separator3);
        separator3.setBounds(0, 520, 1020, 25);

        // doneBt beállításai
        doneBt.setText("Done");
        doneBt.setFocusPainted(false);
        optionsPanel.add(doneBt);
        doneBt.setBounds(410, 525, 200, 25);

        // doneBt eseménykezelő
        doneBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Váltás a menü kártyára
                cardLayout.show(GameGUI.this.gamePanel, MENU_CARD);
            }
        });

        return optionsPanel;
    }

    private JPanel createPlayPanel() {
        JPanel playPanel = new JPanel();
        playPanel.setLayout(null);
        playPanel.setPreferredSize(new Dimension(1020, 555));

        // Komponensek inicializálása
        JButton menuBt = new JButton();
        JSeparator separator3 = new JSeparator();
        JLabel currentPlayerLb = new JLabel();
        JLabel amountOfNutrientsLb = new JLabel();
        JSeparator separator5 = new JSeparator();
        JLabel roundLb = new JLabel();
        JSeparator separator4 = new JSeparator();
        JButton nextRoundBt = new JButton();
        JButton nextPlayerBt = new JButton();
        JSeparator separator2 = new JSeparator();
        JLabel allSelectableEntitiesLb = new JLabel();
        JScrollPane scrollPane1 = new JScrollPane();
        allSelectableEntitiesJList = new JList<>();
        JLabel gameMapLb = new JLabel();
        gameMapJPanel = new JLayeredPane();
        insectImageIconLb1 = new JLabel();
        insectImageIconLb2 = new JLabel();
        insectImageIconLb3 = new JLabel();
        insectImageIconLb4 = new JLabel();
        sporeImageIconLb1 = new JLabel();
        sporeImageIconLb2 = new JLabel();
        sporeImageIconLb3 = new JLabel();
        sporeImageIconLb4 = new JLabel();
        hyphaImageIconLb1 = new JLabel();
        hyphaImageIconLb2 = new JLabel();
        hyphaImageIconLb3 = new JLabel();
        hyphaImageIconLb4 = new JLabel();
        fungusBodyImageIconLb = new JLabel();
        tectonImageIconLb = new JLabel();
        JLabel entitiesForOperationsLb = new JLabel();
        JScrollPane scrollPane2 = new JScrollPane();
        entitiesForOperationsJList = new JList<>();
        JButton growHyphaBt = new JButton();
        JButton growFungusBodyFromSporeBt = new JButton();
        JButton growFungusBodyFromInsectBt = new JButton();
        JButton produceSporeBt = new JButton();
        JButton shootSporeBt = new JButton();
        JButton eatStunnedInsectBt = new JButton();
        JButton eatSporeBt = new JButton();
        JButton moveInsectBt = new JButton();
        JButton cutHyphaBt = new JButton();

        // menuBt beállításai 0.
        menuBt.setText("Menu");
        menuBt.setFocusPainted(false);
        playPanel.add(menuBt);
        menuBt.setBounds(5, 5, 110, 32);

        // menuBt eseménykezelő
        menuBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Váltás a menü kártyára
                cardLayout.show(GameGUI.this.gamePanel, MENU_CARD);
            }
        });

        // separator3 beállításai 1.
        separator3.setOrientation(SwingConstants.VERTICAL);
        playPanel.add(separator3);
        separator3.setBounds(120, 0, 25, 46);

        // currentPlayerLb beállításai 2.
        currentPlayerLb.setText("Current player: IC1");
        currentPlayerLb.setFont(new Font("Segoe UI", Font.BOLD, 18));
        playPanel.add(currentPlayerLb);
        currentPlayerLb.setBounds(130, 10, 159, 25);

        // amountOfNutrientsLb beállításai 3.
        amountOfNutrientsLb.setText("Amount of nutrients collected: 0");
        amountOfNutrientsLb.setFont(new Font("Segoe UI", Font.BOLD, 18));
        playPanel.add(amountOfNutrientsLb);
        amountOfNutrientsLb.setBounds(325, 10, 279, 25);

        // separator5 beállításai 4.
        separator5.setOrientation(SwingConstants.VERTICAL);
        playPanel.add(separator5);
        separator5.setBounds(685, 0, 25, 46);

        // roundLb beállításai 5.
        roundLb.setText("Round: 1");
        roundLb.setBorder(null);
        roundLb.setFont(new Font("Segoe UI", Font.BOLD, 18));
        playPanel.add(roundLb);
        roundLb.setBounds(695, 10, 77, 25);

        // separator4 beállításai 6.
        separator4.setOrientation(SwingConstants.VERTICAL);
        playPanel.add(separator4);
        separator4.setBounds(800, 0, 25, 46);

        // nextRoundBt beállításai 7.
        nextRoundBt.setText("Next round");
        nextRoundBt.setFocusPainted(false);
        playPanel.add(nextRoundBt);
        nextRoundBt.setBounds(810, 5, 97, 32);
        nextRoundBt.addActionListener(new NextRoundButtonListener());

        // nextPlayerBt beállításai 8.
        nextPlayerBt.setText("Next player");
        nextPlayerBt.setFocusPainted(false);
        playPanel.add(nextPlayerBt);
        nextPlayerBt.setBounds(915, 5, 98, 32);
        nextPlayerBt.addActionListener(new NextPlayerButtonListener());

        // separator2 beállításai 9.
        playPanel.add(separator2);
        separator2.setBounds(0, 45, 1020, 20);

        // allSelectableEntitiesLb beállításai 10.
        allSelectableEntitiesLb.setText("All selectable entities:");
        playPanel.add(allSelectableEntitiesLb);
        allSelectableEntitiesLb.setBounds(5, 55, 115, 16);

        // scrollPane1 és allSelectableEntitiesJList beállításai 11.
        allSelectableEntitiesJList.setModel(new AbstractListModel<String>() {
            String[] values = GetKeysFromPlanet();

            @Override
            public int getSize() {
                return values.length;
            }

            @Override
            public String getElementAt(int i) {
                return values[i];
            }
        });
        allSelectableEntitiesJList.addListSelectionListener(new SelectableEntitiesListener());
        scrollPane1.setViewportView(allSelectableEntitiesJList);
        playPanel.add(scrollPane1);
        scrollPane1.setBounds(5, 75, 125, 470);

        // gameMapLb beállításai 12.
        gameMapLb.setText("Game map:");
        playPanel.add(gameMapLb);
        gameMapLb.setBounds(475, 55, 61, 16);

        // gameMapJPanel beállításai
        gameMapJPanel.setOpaque(true);
        gameMapJPanel.setBackground(new Color(0x99ccff));

        // insectImageIconLb beállításai
        String insectImageIconPath = "fungorium/Insect.png";

        insectImageIconLb1.setIcon(new ImageIcon(insectImageIconPath));
        gameMapJPanel.add(insectImageIconLb1, 13);
        insectImageIconLb1.setBounds(new Rectangle(new Point(175, 290), insectImageIconLb1.getPreferredSize()));
        insectImageIconLb1.setVisible(false);

        insectImageIconLb2.setIcon(new ImageIcon(insectImageIconPath));
        gameMapJPanel.add(insectImageIconLb2, 12);
        insectImageIconLb2.setBounds(new Rectangle(new Point(525, 290), insectImageIconLb2.getPreferredSize()));
        insectImageIconLb2.setVisible(false);

        insectImageIconLb3.setIcon(new ImageIcon(insectImageIconPath));
        gameMapJPanel.add(insectImageIconLb3, 11);
        insectImageIconLb3.setBounds(new Rectangle(new Point(525, 50), insectImageIconLb3.getPreferredSize()));
        insectImageIconLb3.setVisible(false);

        insectImageIconLb4.setIcon(new ImageIcon(insectImageIconPath));
        gameMapJPanel.add(insectImageIconLb4, 10);
        insectImageIconLb4.setBounds(new Rectangle(new Point(175, 50), insectImageIconLb4.getPreferredSize()));
        insectImageIconLb4.setVisible(false);

        // sporeImageIconLb beállításai
        String sporeImageIconPath = "fungorium/Spore.png";

        sporeImageIconLb1.setIcon(new ImageIcon(sporeImageIconPath));
        gameMapJPanel.add(sporeImageIconLb1, 9);
        sporeImageIconLb1.setBounds(new Rectangle(new Point(170, 225), sporeImageIconLb1.getPreferredSize()));
        sporeImageIconLb1.setVisible(false);

        sporeImageIconLb2.setIcon(new ImageIcon(sporeImageIconPath));
        gameMapJPanel.add(sporeImageIconLb2, 8);
        sporeImageIconLb2.setBounds(new Rectangle(new Point(525, 225), sporeImageIconLb2.getPreferredSize()));
        sporeImageIconLb2.setVisible(false);

        sporeImageIconLb3.setIcon(new ImageIcon(sporeImageIconPath));
        gameMapJPanel.add(sporeImageIconLb3, 7);
        sporeImageIconLb3.setBounds(new Rectangle(new Point(525, 115), sporeImageIconLb3.getPreferredSize()));
        sporeImageIconLb3.setVisible(false);

        sporeImageIconLb4.setIcon(new ImageIcon(sporeImageIconPath));
        gameMapJPanel.add(sporeImageIconLb4, 6);
        sporeImageIconLb4.setBounds(new Rectangle(new Point(170, 115), sporeImageIconLb4.getPreferredSize()));
        sporeImageIconLb4.setVisible(false);

        // hyphaImageIconLb beállításai
        String hyphaImageIconPath = "fungorium/Hypha.png";

        hyphaImageIconLb1.setIcon(new ImageIcon(hyphaImageIconPath));
        gameMapJPanel.add(hyphaImageIconLb1, 5);
        hyphaImageIconLb1.setBounds(new Rectangle(new Point(270, 25), hyphaImageIconLb1.getPreferredSize()));
        hyphaImageIconLb1.setVisible(false);

        hyphaImageIconLb2.setIcon(new ImageIcon(hyphaImageIconPath));
        gameMapJPanel.add(hyphaImageIconLb2, 4);
        hyphaImageIconLb2.setBounds(new Rectangle(new Point(280, 25), hyphaImageIconLb2.getPreferredSize()));
        hyphaImageIconLb2.setVisible(false);

        hyphaImageIconLb3.setIcon(new ImageIcon(hyphaImageIconPath));
        gameMapJPanel.add(hyphaImageIconLb3, 3);
        hyphaImageIconLb3.setBounds(new Rectangle(new Point(290, 25), hyphaImageIconLb3.getPreferredSize()));
        hyphaImageIconLb3.setVisible(false);

        hyphaImageIconLb4.setIcon(new ImageIcon(hyphaImageIconPath));
        gameMapJPanel.add(hyphaImageIconLb4, 2);
        hyphaImageIconLb4.setBounds(new Rectangle(new Point(300, 25), hyphaImageIconLb4.getPreferredSize()));
        hyphaImageIconLb4.setVisible(false);

        // fungusBodyImageIconLb beállításai
        fungusBodyImageIconLb.setIcon(new ImageIcon("fungorium/FungusBody.png"));
        gameMapJPanel.add(fungusBodyImageIconLb, 1);
        fungusBodyImageIconLb.setBounds(new Rectangle(new Point(230, 50), fungusBodyImageIconLb.getPreferredSize()));
        fungusBodyImageIconLb.setVisible(false);

        // tectonImageIconLb beállításai
        tectonImageIconLb.setIcon(null);
        gameMapJPanel.add(tectonImageIconLb);
        tectonImageIconLb.setBounds(new Rectangle(new Point(75, 5), tectonImageIconLb.getPreferredSize()));

        // gameMapJPanel méretének kiszámítása 13.
        Dimension preferredSize = new Dimension();
        for (int i = 0; i < gameMapJPanel.getComponentCount(); i++) {
            Rectangle bounds = gameMapJPanel.getComponent(i).getBounds();
            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
        }
        Insets insets = gameMapJPanel.getInsets();
        preferredSize.width += insets.right;
        preferredSize.height += insets.bottom;
        gameMapJPanel.setLayout(null);
        gameMapJPanel.setMinimumSize(preferredSize);
        gameMapJPanel.setPreferredSize(preferredSize);

        playPanel.add(gameMapJPanel);
        gameMapJPanel.setBounds(140, 75, 730, 385);

        // entitiesForOperationsLb beállításai 14.
        entitiesForOperationsLb.setText("Entities for operations:");
        playPanel.add(entitiesForOperationsLb);
        entitiesForOperationsLb.setBounds(880, 55, 119, 16);

        // scrollPane2 és entitiesForOperationsJList beállításai 15.
        entitiesForOperationsJList.setModel(new AbstractListModel<String>() {
            String[] values = {};

            @Override
            public int getSize() {
                return values.length;
            }

            @Override
            public String getElementAt(int i) {
                return values[i];
            }
        });
        entitiesForOperationsJList.setSelectedIndex(-1);
        scrollPane2.setViewportView(entitiesForOperationsJList);
        playPanel.add(scrollPane2);
        scrollPane2.setBounds(880, 75, 135, 470);

        // growHyphaBt beállításai 16.
        growHyphaBt.setText("GrowHypha");
        growHyphaBt.setFocusPainted(false);
        playPanel.add(growHyphaBt);
        growHyphaBt.setBounds(140, 470, 100, 32);
        growHyphaBt.addActionListener(new GrowHyphaListener());

        // growFungusBodyFromSporeBt beállításai 17.
        growFungusBodyFromSporeBt.setText("GrowFungusBodyFromSpore");
        growFungusBodyFromSporeBt.setFocusPainted(false);
        playPanel.add(growFungusBodyFromSporeBt);
        growFungusBodyFromSporeBt.setBounds(250, 470, 199, 32);
        growFungusBodyFromSporeBt.addActionListener(new GrowFungusBodyFromSporeListener());

        // growFungusBodyFromInsectBt beállításai 18.
        growFungusBodyFromInsectBt.setText("GrowFungusBodyFromInsect");
        growFungusBodyFromInsectBt.setFocusPainted(false);
        playPanel.add(growFungusBodyFromInsectBt);
        growFungusBodyFromInsectBt.setBounds(460, 470, 199, 32);
        growFungusBodyFromInsectBt.addActionListener(new GrowFungusBodyFromInsectListener());

        // produceSporeBt beállításai 19.
        produceSporeBt.setText("ProduceSpore");
        produceSporeBt.setFocusPainted(false);
        playPanel.add(produceSporeBt);
        produceSporeBt.setBounds(670, 470, 116, 32);
        produceSporeBt.addActionListener(new ProduceSporeListener());

        // shootSporeBt beállításai 20.
        shootSporeBt.setText("ShootSpore");
        shootSporeBt.setFocusPainted(false);
        playPanel.add(shootSporeBt);
        shootSporeBt.setBounds(140, 510, 102, 32);
        shootSporeBt.addActionListener(new ShootSporeListener());

        // eatStunnedInsectBt beállításai 21.
        eatStunnedInsectBt.setText("EatStunnedInsect");
        eatStunnedInsectBt.setFocusPainted(false);
        playPanel.add(eatStunnedInsectBt);
        eatStunnedInsectBt.setBounds(250, 510, 135, 32);
        eatStunnedInsectBt.addActionListener(new EatStunnedInsectListener());

        // eatSporeBt beállításai 22.
        eatSporeBt.setText("EatSpore");
        eatSporeBt.setFocusPainted(false);
        playPanel.add(eatSporeBt);
        eatSporeBt.setBounds(395, 510, 87, 32);
        eatSporeBt.addActionListener(new EatSporeListener());

        // moveInsectBt beállításai 23.
        moveInsectBt.setText("MoveInsect");
        moveInsectBt.setFocusPainted(false);
        playPanel.add(moveInsectBt);
        moveInsectBt.setBounds(490, 510, 100, 32);
        moveInsectBt.addActionListener(new MoveInsectListener());

        // cutHyphaBt beállításai 24.
        cutHyphaBt.setText("CutHypha");
        cutHyphaBt.setFocusPainted(false);
        playPanel.add(cutHyphaBt);
        cutHyphaBt.setBounds(600, 510, 97, 32);
        cutHyphaBt.addActionListener(new CutHyphaListener());

        return playPanel;
    }

    /**
     * Visszatér a View planet nem res-hifa kulcsainak tombjével.
     *
     * @return String array - Keys form View.planet
     */
    public String[] GetKeysFromPlanet() {
        LinkedList<String> keys = new LinkedList<String>();
        LinkedHashMap<String, Object> planet = (LinkedHashMap<String, Object>) iview.getPlanet();

        for (Map.Entry<String, Object> entry : planet.entrySet()) {
            // ha az entry hifa
            if (entry.getKey().startsWith("H")) {
                Hypha hypha = (Hypha) entry.getValue();
                // es nem res hifa
                if (hypha.GetTectons().size() < 2) {
                    keys.add(entry.getKey());
                }
            } else {
                keys.add(entry.getKey());
            }
        }

        return keys.toArray(new String[0]);
    }

    /**
     * Visszaadja egy String tombbe a kivalasztott kulcshoz tartozo elemeket, amiken
     * potencialisan valamilyen muveletet tud a felhasznalo vegrehajtani.
     * 
     * @param key kivalasztott elem kulcsa
     * @return String array - keys from planet
     */
    public String[] GetOperatableKeysFromPlanet(String key) {
        if (key == null) {
            return new String[0];
        }
        LinkedList<String> keys = new LinkedList<String>();
        LinkedHashMap<String, Object> planet = (LinkedHashMap<String, Object>) iview.getPlanet();

        if (key.startsWith("H")) {                              // hifa, nem lehet res hifa
            IHyphaView ihypha = (IHyphaView) planet.get(key);
            ITectonView itectonView = ihypha.GetTectons().get(0);

            ArrayList<Tecton> tectons = itectonView.GetNeighbours();
            ArrayList<Insect> insects = (ArrayList<Insect>) itectonView.GetInsects();

            // tektonok kulcsainak megkeresese
            for (Tecton tecton : tectons) {
                for (Map.Entry<String, Object> entry : planet.entrySet()) {
                    if (entry.getValue().equals(tecton)) {
                        keys.add(entry.getKey());
                        break;
                    }
                }
            }
            // rovarok kulcsainak megkeresese
            for (Insect insect : insects) {
                for (Map.Entry<String, Object> entry : planet.entrySet()) {
                    if (entry.getValue().equals(insect)) {
                        keys.add(entry.getKey());
                        break;
                    }
                }
            }
        } else if (key.startsWith("T")) {                                       // tekton
            ITectonView itectonView = (ITectonView) planet.get(key);

            ArrayList<Spore> spores = (ArrayList<Spore>) itectonView.GetSpores();
            ArrayList<Insect> insects = (ArrayList<Insect>) itectonView.GetInsects();

            // sporak kulcsainak megkeresese
            for (Spore spore : spores) {
                for (Map.Entry<String, Object> entry : planet.entrySet()) {
                    if (entry.getValue().equals(spore)) {
                        keys.add(entry.getKey());
                        break;
                    }
                }
            }
            // rovarok kulcsainak megkeresese
            for (Insect insect : insects) {
                for (Map.Entry<String, Object> entry : planet.entrySet()) {
                    if (entry.getValue().equals(insect)) {
                        keys.add(entry.getKey());
                        break;
                    }
                }
            }
        } else if (key.matches("I\\d+")) {                                  // rovar
            IInsectView insectView = (IInsectView) planet.get(key);
            ITectonView tectonview = insectView.GetTecton();

            ArrayList<Tecton> tectons = (ArrayList<Tecton>) tectonview.GetNeighbours();
            ArrayList<Spore> spores = (ArrayList<Spore>) tectonview.GetSpores();
            ArrayList<Hypha> hyphas = (ArrayList<Hypha>) tectonview.GetHyphas();

            // tektonok kulcsainak megkeresese
            for (Tecton tecton : tectons) {
                for (Map.Entry<String, Object> entry : planet.entrySet()) {
                    if (entry.getValue().equals(tecton)) {
                        keys.add(entry.getKey());
                        break;
                    }
                }
            }
            // sporak kulcsainak megkeresese
            for (Spore spore : spores) {
                for (Map.Entry<String, Object> entry : planet.entrySet()) {
                    if (entry.getValue().equals(spore)) {
                        keys.add(entry.getKey());
                        break;
                    }
                }
            }
            // hifak kulcsainak megkeresese
            for (Hypha hypha : hyphas) {
                for (Map.Entry<String, Object> entry : planet.entrySet()) {
                    if (entry.getValue().equals(hypha)) {
                        if (hypha.GetTectons().size() > 1) { // res hifak kellenek, csak azokat tudja elvagni
                            keys.add(entry.getKey());
                            break;
                        }
                    }
                }
            }
        } else if (key.matches("IC\\d+")) {                                         // rovar kolonia
            IInsectColonyView icView = (IInsectColonyView) planet.get(key);
            ArrayList<Insect> insects = (ArrayList<Insect>) icView.getInsects();
            for (Insect insect : insects) {
                for (Map.Entry<String, Object> entry : planet.entrySet()) {
                    if (entry.getValue().equals(insect)) {
                        keys.add(entry.getKey());
                    }
                }
            }
        } else if (key.matches("F\\d+")) {                                          // gomba
            IFungusView fungusView = (IFungusView) planet.get(key);
            ArrayList<FungusBody> fbS = (ArrayList<FungusBody>) fungusView.GetBodies();
            ArrayList<Hypha> mycelium = (ArrayList<Hypha>) fungusView.GetMycelium();
            for (FungusBody fb : fbS) {
                for (Map.Entry<String, Object> entry : planet.entrySet()) {
                    if (entry.getValue().equals(fb)) {
                        keys.add(entry.getKey());
                    }
                }
            }
            for (Hypha hypha : mycelium) {
                for (Map.Entry<String, Object> entry : planet.entrySet()) {
                    if (entry.getValue().equals(hypha)) {
                        keys.add(entry.getKey());
                    }
                }
            }
        }

        return keys.toArray(new String[keys.size()]);
    }

    /**
     * Frissiti a kivalaszthato elemek listajat (bal oldali lista).
     */
    private void RefreshSelectableEntities() {
        String[] values = GetKeysFromPlanet(); // now returns String[]

        allSelectableEntitiesJList.setModel(new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return values.length;
            }

            @Override
            public String getElementAt(int i) {
                return values[i];
            }
        });

        String selectedKey = allSelectableEntitiesJList.getSelectedValue();
        if (selectedKey != null) {
            String[] operatableKeys = GetOperatableKeysFromPlanet(selectedKey);
            entitiesForOperationsJList.setModel(new AbstractListModel<String>() {
                @Override
                public int getSize() {
                    return operatableKeys.length;
                }

                @Override
                public String getElementAt(int i) {
                    return operatableKeys[i];
                }
            });
        } else {
            entitiesForOperationsJList.setModel(new AbstractListModel<String>() {
                @Override
                public int getSize() {
                    return 0;
                }

                @Override
                public String getElementAt(int i) {
                    return null;
                }
            });
        }
    }

    private class SelectableEntitiesListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            String[] values = GetKeysFromPlanet();
            if (values.length == 0)
                return;
            String key = allSelectableEntitiesJList.getSelectedValue();
            if (key == null) {
                return;
            }
            entitiesForOperationsJList.setModel(new AbstractListModel<String>() {
                String[] values = GetOperatableKeysFromPlanet(key);

                @Override
                public int getSize() {
                    return values.length;
                }

                @Override
                public String getElementAt(int i) {
                    return values[i];
                }
            });

            // Játékos aktuális állapotának frissítése
            LinkedHashMap<String, Object> planet = (LinkedHashMap<String, Object>) iview.getPlanet();
            Object selectedEntity = planet.get(key);

            if (key.startsWith("T")) { // Tecton
                Draw((ITectonView) selectedEntity);
            }

            else if (key.startsWith("H")) { // Hypha
                IHyphaView hyphaView = (IHyphaView) selectedEntity;
                ITectonView tecton = hyphaView.GetTectons().get(0); // Get the first Tecton
                Draw(tecton);
            } else if (key.matches("I\\d+")) { // Insect
                IInsectView insectView = (IInsectView) selectedEntity;
                ITectonView tecton = insectView.GetTecton();
                Draw(tecton);
            } else if (key.startsWith("FB")) { // FungusBody
                IFungusBodyView fungusBodyView = (IFungusBodyView) selectedEntity;
                ITectonView tecton = fungusBodyView.GetTecton();
                Draw(tecton);
            } else if (key.startsWith("S")) { // Spore
                ISporeView sporeView = (ISporeView) selectedEntity;
                ITectonView tecton = sporeView.GetTecton();
                Draw(tecton);
            }


            if (key.startsWith("H")) {                  // hifa,
                SetButtonsVisible(true,false,false,false,false,true,false,false,false);

            } else if (key.startsWith("T")) {           // tekton
                SetButtonsVisible(false,true,true,false,false,false,false,false,false);

            } else if (key.matches("I\\d+")) {    // rovar
                SetButtonsVisible(false,false,false,false,false,false,true,true,true);

            } else if (key.startsWith("FB")) {          // gombatest
                SetButtonsVisible(false,false,false,true,true,false,false,false,false);

            } else {
                SetButtonsVisible(false,false,false,false,false,false,false,false,false);
            }

        }
    }

    /**
     * Segedfuggveny a gombok lathatosaganak beallitasara
     * @param growH - growHyphaBt lathatosaga
     * @param growFbS - growFungusBodyFromSporeBt lathatosaga
     * @param growFbI - growFungusBodyFromInsectBt lathatosaga
     * @param produceS - produceSporeBt lathatosaga
     * @param shootS - shootSporeBt lathatosaga
     * @param eatI - eatStunnedInsectBt lathatosaga
     * @param eatS - eatSporeBt lathatosaga
     * @param moveI - moveInectBt lathatosaga
     * @param cutH - cutHyphaBt lathatosaga
     */
    private void SetButtonsVisible(boolean growH,boolean growFbS, boolean growFbI, boolean produceS, boolean shootS, boolean eatI, boolean eatS, boolean moveI, boolean cutH ){
        // button komponensek kinyerese
        JButton growHyphaBt = (JButton) playPanel.getComponent(16);
        JButton growFungusBodyFromSporeBt = (JButton) playPanel.getComponent(17);
        JButton growFungusBodyFromInsectBt = (JButton) playPanel.getComponent(18);
        JButton produceSporeBt = (JButton) playPanel.getComponent(19);
        JButton shootSporeBt = (JButton) playPanel.getComponent(20);
        JButton eatStunnedInsectBt = (JButton) playPanel.getComponent(21);
        JButton eatSporeBt = (JButton) playPanel.getComponent(22);
        JButton moveInsectBt = (JButton) playPanel.getComponent(23);
        JButton cutHyphaBt = (JButton) playPanel.getComponent(24);
        // button visibility beallitasa
        growHyphaBt.setEnabled(growH);
        growFungusBodyFromSporeBt.setEnabled(growFbS);
        growFungusBodyFromInsectBt.setEnabled(growFbI);
        produceSporeBt.setEnabled(produceS);
        shootSporeBt.setEnabled(shootS);
        eatStunnedInsectBt.setEnabled(eatI);
        eatSporeBt.setEnabled(eatS);
        moveInsectBt.setEnabled(moveI);
        cutHyphaBt.setEnabled(cutH);
    }


    private class GrowFungusBodyFromSporeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ITectonController tectonController;
            try {
                tectonController = (ITectonController) iview.getPlanet()
                        .get(allSelectableEntitiesJList.getSelectedValue());
            } catch (NullPointerException ne) {
                System.err.println("Kulcs nem talalhato");
                return;
            } catch (ClassCastException cce) {
                System.err.println("Nem megfelelo tipus lett kivalasztva");
                return;
            }
            GameController controller = iview.GetGameController();
            controller.GrowFungusBody(tectonController, (Fungus) controller.GetCurrentPlayer());
            // ToDo: felugró ablakok error esetén
            RefreshSelectableEntities();

        }
    }

    private class GrowFungusBodyFromInsectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ITectonController tectonController;
            try {
                tectonController = (ITectonController) iview.getPlanet()
                        .get(allSelectableEntitiesJList.getSelectedValue());
            } catch (NullPointerException ne) {
                System.err.println("Kulcs nem talalhato");
                return;
            } catch (ClassCastException cce) {
                System.err.println("Nem megfelelo tipus lett kivalasztva");
                return;
            }
            GameController controller = iview.GetGameController();
            controller.GrowFungusBodyFromInsect(tectonController, (Fungus) controller.GetCurrentPlayer());
            // ToDo: felugró ablakok error esetén
            RefreshSelectableEntities();

        }
    }

    private class GrowHyphaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            IHyphaView hyphaView;
            Tecton tectonTo;
            Tecton tectonFrom;
            try {
                hyphaView = (IHyphaView) iview.getPlanet().get(allSelectableEntitiesJList.getSelectedValue());
                tectonFrom = hyphaView.GetTectons().get(0);
                tectonTo = (Tecton) iview.getPlanet().get(entitiesForOperationsJList.getSelectedValue());
            } catch (NullPointerException ne) {
                System.err.println("Kulcs nem talalhato");
                return;
            } catch (ClassCastException cce) {
                System.err.println("Nem megfelelo tipus lett kivalasztva");
                return;
            }

            if (tectonTo == null)
                return;
            GameController controller = iview.GetGameController();
            controller.GrowHypha((Fungus) controller.GetCurrentPlayer(), tectonFrom, tectonTo);
            // ToDo: felugró ablakok error esetén
            RefreshSelectableEntities();
        }
    }

    private class ProduceSporeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            IFungusBodyController fbController;
            try {
                fbController = (IFungusBodyController) iview.getPlanet()
                        .get(allSelectableEntitiesJList.getSelectedValue());
            } catch (NullPointerException ne) {
                System.err.println("Kulcs nem talalhato");
                return;
            } catch (ClassCastException cce) {
                System.err.println("Nem megfelelo tipus lett kivalasztva");
                return;
            }
            GameController controller = iview.GetGameController();
            controller.ProduceSpore(fbController);
            // ToDo: felugró ablakok error esetén
            RefreshSelectableEntities();

        }
    }

    private class ShootSporeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            IFungusBodyController fbController;
            try {
                fbController = (IFungusBodyController) iview.getPlanet()
                        .get(allSelectableEntitiesJList.getSelectedValue());
            } catch (NullPointerException ne) {
                System.err.println("Kulcs nem talalhato");
                return;
            } catch (ClassCastException cce) {
                System.err.println("Nem megfelelo tipus lett kivalasztva");
                return;
            }
            GameController controller = iview.GetGameController();
            controller.ShootSpores(fbController);
            // ToDo: felugró ablakok error esetén
            RefreshSelectableEntities();
        }
    }

    private class EatStunnedInsectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            IHyphaController hyphaController;
            Insect insect;
            try {
                hyphaController = (IHyphaController) iview.getPlanet()
                        .get(allSelectableEntitiesJList.getSelectedValue());
                insect = (Insect) iview.getPlanet().get(entitiesForOperationsJList.getSelectedValue());
            } catch (NullPointerException ne) {
                System.err.println("Kulcs nem talalhato");
                return;
            } catch (ClassCastException cce) {
                System.err.println("Nem megfelelo tipus lett kivalasztva");
                return;
            }
            GameController controller = iview.GetGameController();
            controller.EatStunnedInsect(hyphaController, insect);
            // ToDo: felugró ablakok error esetén
            RefreshSelectableEntities();
        }
    }

    private class EatSporeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            IInsectController insectController;
            Spore spore;
            try {
                insectController = (IInsectController) iview.getPlanet()
                        .get(allSelectableEntitiesJList.getSelectedValue());
                spore = (Spore) iview.getPlanet().get(entitiesForOperationsJList.getSelectedValue());
            } catch (NullPointerException ne) {
                System.err.println("Kulcs nem talalhato");
                return;
            } catch (ClassCastException cce) {
                System.err.println("Nem megfelelo tipus lett kivalasztva");
                return;
            }
            GameController controller = iview.GetGameController();
            controller.EatSpore(insectController, spore);
            // ToDo: felugró ablakok error esetén
            RefreshSelectableEntities();
        }
    }

    private class MoveInsectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            IInsectController insectController;
            Tecton tecton;
            try {
                insectController = (IInsectController) iview.getPlanet().get(allSelectableEntitiesJList.getSelectedValue());
                tecton = (Tecton) iview.getPlanet().get(entitiesForOperationsJList.getSelectedValue());
            } catch (NullPointerException ne) {
                System.err.println("Kulcs nem talalhato");
                return;
            } catch (ClassCastException cce) {
                System.err.println("Nem megfelelo tipus lett kivalasztva");
                return;
            }
            GameController controller = iview.GetGameController();
            controller.MoveInsect(insectController, tecton);
            // ToDo: felugró ablakok error esetén
            RefreshSelectableEntities();
        }

    }

    private class CutHyphaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            IInsectController insectController;
            Hypha hypha;
            try {
                insectController = (IInsectController) iview.getPlanet()
                        .get(allSelectableEntitiesJList.getSelectedValue());
                hypha = (Hypha) iview.getPlanet().get(entitiesForOperationsJList.getSelectedValue());
            } catch (NullPointerException ne) {
                System.err.println("Kulcs nem talalhato");
                return;
            } catch (ClassCastException cce) {
                System.err.println("Nem megfelelo tipus lett kivalasztva");
                return;
            }
            GameController controller = iview.GetGameController();
            controller.CutHypha(insectController, hypha);
            // ToDo: felugró ablakok error esetén
            RefreshSelectableEntities();
        }
    }

    private class NextRoundButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            iview.GetGameController().Trigg("nr");
            RoundDisplay();
        }
    }

    /**
     * A körök lefolyását megjelenítő függvény
     */
    private void RoundDisplay() {
        if (iview.GetGameController().IsGameOver()) {
            JOptionPane.showMessageDialog(null, "Game Over!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }

        JLabel roundLabel = (JLabel) playPanel.getComponent(5);
        String currentRound = "0";
        roundLabel.setText("Round: " + iview.GetGameController().GetCurrentRound());
        RefreshSelectableEntities();
    }

    private class NextPlayerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            iview.GetGameController().Trigg("np");
            JLabel playerLabel = (JLabel) playPanel.getComponent(2);
            String currentPlayer = "default";
            for (Map.Entry<String, Object> entry : iview.getPlanet().entrySet()) {
                if (entry.getValue().equals(iview.GetGameController().GetCurrentPlayer())) {
                    currentPlayer = entry.getKey();
                }
            }

            playerLabel.setText("Current player: " + currentPlayer);
            RoundDisplay();
            RefreshSelectableEntities();
        }
    }

    private void Draw(ITectonView view) {
        if (tectonImageIconLb != null)
            gameMapJPanel.remove(tectonImageIconLb);
        DrawVisitor drawVisitor = new DrawVisitor();
        view.accept(drawVisitor);
        tectonImageIconLb = drawVisitor.getLabel();
        gameMapJPanel.add(tectonImageIconLb, 14);
        int insectCount = view.GetInsects().size();
        int sporeCount = view.GetSpores().size();
        int hyphaCount = 0;
        for (Hypha h : view.GetHyphas()) {
            if (h.GetTectons().size() < 2) {
                hyphaCount++;
            }
        }
        JLabel[] ilabels = { insectImageIconLb1, insectImageIconLb2, insectImageIconLb3, insectImageIconLb4 };
        JLabel[] hlabels = { hyphaImageIconLb1, hyphaImageIconLb2, hyphaImageIconLb3, hyphaImageIconLb4 };
        JLabel[] slabels = { sporeImageIconLb1, sporeImageIconLb2, sporeImageIconLb3, sporeImageIconLb4 };
        visHelper(ilabels, insectCount);
        visHelper(hlabels, hyphaCount);
        visHelper(slabels, sporeCount);
        if (view.GetFungusBody() != null) {
            fungusBodyImageIconLb.setVisible(true);
        } else {
            fungusBodyImageIconLb.setVisible(false);
        }
        gameMapJPanel.repaint();
    }

    private void visHelper(JLabel[] label, int i) {
        switch (i) {
            case 0:
                label[0].setVisible(false);
                label[1].setVisible(false);
                label[2].setVisible(false);
                label[3].setVisible(false);
                break;
            case 1:
                label[0].setVisible(true);
                label[1].setVisible(false);
                label[2].setVisible(false);
                label[3].setVisible(false);
                break;
            case 2:
                label[0].setVisible(true);
                label[1].setVisible(true);
                label[2].setVisible(false);
                label[3].setVisible(false);
                break;
            case 3:
                label[0].setVisible(true);
                label[1].setVisible(true);
                label[2].setVisible(true);
                label[3].setVisible(false);
                break;
            case 4:
                label[0].setVisible(true);
                label[1].setVisible(true);
                label[2].setVisible(true);
                label[3].setVisible(true);
                break;
        }
    }
}