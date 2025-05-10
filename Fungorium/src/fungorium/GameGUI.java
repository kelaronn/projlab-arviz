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

public class GameGUI extends JFrame {
    private static final String MENU_CARD = "MenuCard";
    private static final String OPTIONS_CARD = "OptionsCard";
    private static final String PLAY_CARD = "PlayCard";
    private JList<String> allSelectableEntitiesJList;
    private JList<String> entitiesForOperationsJList;
    private JPanel gamePanel;
    private CardLayout cardLayout;
    IView iview;

    public GameGUI() {}

    public GameGUI(View view) {
        this.iview = (IView)view;
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
        JPanel menuPanel = createMenuPanel();
        gamePanel.add(menuPanel, MENU_CARD);

        // Options panel hozzáadása
        JPanel optionsPanel = createOptionsPanel();
        gamePanel.add(optionsPanel, OPTIONS_CARD);

        // Play panel hozzáadása
        JPanel playPanel = createPlayPanel();
        gamePanel.add(playPanel, PLAY_CARD);

        // gamePanel hozzáadása a contentPane-hez
        contentPane.add(gamePanel, BorderLayout.CENTER);

        // Ablak beállításai
        pack();
        setLocationRelativeTo(getOwner());
        //getOwner() vagy null
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
            }
        });

        // loadBt beállításai
        loadBt.setText("Load");
        loadBt.setFont(new Font("Segoe UI", Font.BOLD, 22));
        loadBt.setFocusPainted(false);
        menuPanel.add(loadBt);
        loadBt.setBounds(365, 210, 295, 40);

        // saveBt beállításai
        saveBt.setText("Save");
        saveBt.setFont(new Font("Segoe UI", Font.BOLD, 22));
        saveBt.setFocusPainted(false);
        menuPanel.add(saveBt);
        saveBt.setBounds(365, 260, 295, 40);

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
        quitGameBt.addActionListener(e -> System.exit(0));

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
                iview.GetGameController().SetRoundsLeft((Integer)maxTurnsSpinner.getValue());
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
                }
                else if (randomBt.getText().equals("Random: off")) {
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
                }
                else if (turnsBt.getText().equals("Turns: off")) {
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
                    System.out.print("/exec "+executeScriptTF.getText().toString()+"\n");
                    boolean success = iview.exec(executeScriptTF.getText().trim().toString());
                    System.out.print(">");
                    if (success) {
                        executeScriptTF.setText("");
                        JOptionPane.showMessageDialog(null, "Script execution successful.", "Successful execution message", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Script execution failed!", "Error message", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please enter the name of the script file to be executed!", "Error message", JOptionPane.ERROR_MESSAGE);
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
                String modifiedFirstLine = firstLine.length() > 2 ? firstLine.substring(1, firstLine.length() - 1) : firstLine;

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
                String modifiedFirstLine = firstLine.length() > 2 ? firstLine.substring(1, firstLine.length() - 1) : firstLine;

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
                String modifiedFirstLine = firstLine.length() > 2 ? firstLine.substring(1, firstLine.length() - 1) : firstLine;

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
                String modifiedFirstLine = firstLine.length() > 2 ? firstLine.substring(1, firstLine.length() - 1) : firstLine;

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
                String modifiedFirstLine = firstLine.length() > 2 ? firstLine.substring(1, firstLine.length() - 1) : firstLine;

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
                String modifiedFirstLine = firstLine.length() > 2 ? firstLine.substring(1, firstLine.length() - 1) : firstLine;

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
                String modifiedFirstLine = firstLine.length() > 2 ? firstLine.substring(1, firstLine.length() - 1) : firstLine;

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
                if (!addtTectonNameTF.getText().trim().isEmpty() && addtTectonNameTF.getText().trim().matches("^T\\d+$")) {
                    String tectonType = addtTectonTypeCB.getSelectedItem().toString().equals("NarrowTecton")?"n":
                    addtTectonTypeCB.getSelectedItem().equals("WideTecton")?"wi":
                    addtTectonTypeCB.getSelectedItem().equals("VitalTecton")?"v":
                    addtTectonTypeCB.getSelectedItem().equals("WeakTecton")?"we":
                    addtTectonTypeCB.getSelectedItem().equals("BarrenTecton")?"b":"n";
                    System.out.print("/addt -n "+addtTectonNameTF.getText().trim()+" -t "+tectonType+"\n");
                    boolean success = iview.addt(addtTectonNameTF.getText().trim(), tectonType);
                    System.out.print(">");
                    if (success) {
                        addtTectonNameTF.setText("");
                        addtTectonTypeCB.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(null, "Tecton successfully created.", "Successful execution message", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        if ((Tecton)iview.getPlanet().get(addtTectonNameTF.getText().trim())!=null) {
                            JOptionPane.showMessageDialog(null, "This name is already taken!", "Error message", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "The operation failed!", "Error message", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please enter the required information correctly!", "Error message", JOptionPane.ERROR_MESSAGE);
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
                if (!addfFungusNameTF.getText().trim().isEmpty() && addfFungusNameTF.getText().trim().matches("^F\\d+$")) {
                    System.out.print("/addf -n "+addfFungusNameTF.getText().trim()+"\n");
                    boolean success = iview.addf(addfFungusNameTF.getText().trim());
                    System.out.print(">");
                    if (success) {
                        addfFungusNameTF.setText("");
                        JOptionPane.showMessageDialog(null, "Fungus successfully created.", "Successful execution message", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        if ((Fungus)iview.getPlanet().get(addfFungusNameTF.getText().trim())!=null) {
                            JOptionPane.showMessageDialog(null, "This name is already taken!", "Error message", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "The operation failed!", "Error message", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please enter the required information correctly!", "Error message", JOptionPane.ERROR_MESSAGE);
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
                if (!addicInsectColonyNameTF.getText().trim().isEmpty() && addicInsectColonyNameTF.getText().trim().matches("^IC\\d+$")) {
                    System.out.print("/addic -n "+addicInsectColonyNameTF.getText().trim()+" -nv "+(Integer)addicAmoutOfNutrientsSpinner.getValue()+"\n");
                    boolean success = iview.addic(addicInsectColonyNameTF.getText().trim(), (Integer)addicAmoutOfNutrientsSpinner.getValue());
                    System.out.print(">");
                    if (success) {
                        addicInsectColonyNameTF.setText("");
                        addicAmoutOfNutrientsSpinner.setValue(0);
                        JOptionPane.showMessageDialog(null, "Insect Colony successfully created.", "Successful execution message", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        if ((InsectColony)iview.getPlanet().get(addicInsectColonyNameTF.getText().trim())!=null) {
                            JOptionPane.showMessageDialog(null, "This name is already taken!", "Error message", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "The operation failed!", "Error message", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please enter the required information correctly!", "Error message", JOptionPane.ERROR_MESSAGE);
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
                if (!addfbFungusBodyNameTF.getText().trim().isEmpty() && addfbFungusBodyNameTF.getText().trim().matches("^FB\\d+$") && 
                    !addfbFungusNameTF.getText().trim().isEmpty() && addfbFungusNameTF.getText().trim().matches("^F\\d+$") && (Fungus)iview.getPlanet().get(addfbFungusNameTF.getText().trim()) != null &&
                    !addfbTectonNameTF.getText().trim().isEmpty() && addfbTectonNameTF.getText().trim().matches("^T\\d+$") && (Tecton)iview.getPlanet().get(addfbTectonNameTF.getText().trim())!= null) {
                    System.out.print("/addfb -n "+addfbFungusBodyNameTF.getText().trim()+" -f "+addfbFungusNameTF.getText().trim()+" -t "+addfbTectonNameTF.getText().trim()+" -d "+(addfbIsDeadBt.getText().equals("IsDead: no")?"n":"y")
                    +" -a "+(Integer)addfbAgeSpinner.getValue()+" -dv "+(addfbIsDevelopedBt.getText().equals("IsDeveloped: no")?"n":"y")+" -sc "+(Integer)addfbSporeCountSpinner.getValue()+" -sl "+(Integer)addfbMaxShootSpinner.getValue()+"\n");
                    boolean success = iview.addfb(addfbFungusBodyNameTF.getText().trim(), (Tecton)iview.getPlanet().get(addfbTectonNameTF.getText().trim()), (Fungus)iview.getPlanet().get(addfbFungusNameTF.getText().trim()), 
                    addfbIsDevelopedBt.getText().equals("IsDeveloped: no")?false:true, (Integer)addfbAgeSpinner.getValue(), addfbIsDeadBt.getText().equals("IsDead: no")?false:true, (Integer)addfbSporeCountSpinner.getValue(), (Integer)addfbMaxShootSpinner.getValue());
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
                        JOptionPane.showMessageDialog(null, "Fungus Body successfully created.", "Successful execution message", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        if ((FungusBody)iview.getPlanet().get(addfbFungusBodyNameTF.getText().trim())!=null) {
                            JOptionPane.showMessageDialog(null, "This name is already taken!", "Error message", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "The operation failed!", "Error message", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please enter the required information correctly!", "Error message", JOptionPane.ERROR_MESSAGE);
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
                Integer ageValue = (Integer)addfbAgeSpinner.getValue();
                Integer maxShootValue = (Integer)addfbMaxShootSpinner.getValue();
                if (ageValue>=5) {
                    addfbIsDevelopedBt.setText("IsDeveloped: yes");
                }
                else{
                    addfbIsDevelopedBt.setText("IsDeveloped: no");
                }
                if (ageValue>5 || maxShootValue == 0) {
                    addfbIsDeadBt.setText("IsDead: yes");
                }
                else{
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
                Integer ageValue = (Integer)addfbAgeSpinner.getValue();
                Integer maxShootValue = (Integer)addfbMaxShootSpinner.getValue();
                if (ageValue>5 || maxShootValue == 0) {
                    addfbIsDeadBt.setText("IsDead: yes");
                }
                else{
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
                if (!addhHyphaNameTF.getText().trim().isEmpty() && addhHyphaNameTF.getText().trim().matches("^H\\d+$") &&
                    !addhFungusNameTF.getText().trim().isEmpty() && addhFungusNameTF.getText().trim().matches("^F\\d+$") && (Fungus)iview.getPlanet().get(addhFungusNameTF.getText().trim())!=null &&
                    !addhTectonNameStartTF.getText().trim().isEmpty() && addhTectonNameStartTF.getText().trim().matches("^T\\d+$") && (Tecton)iview.getPlanet().get(addhTectonNameStartTF.getText().trim())!=null &&
                    (addhTectonNameEndTF.getText().trim().isEmpty()?true:addhTectonNameEndTF.getText().trim().matches("^T\\d+$") && (Tecton)iview.getPlanet().get(addhTectonNameEndTF.getText().trim())!=null)) {
                    System.out.print("/addh -n "+addhHyphaNameTF.getText().trim()+" -f "+addhFungusNameTF.getText().trim()+" -ts "+addhTectonNameStartTF.getText().trim()+(addhTectonNameEndTF.getText().trim().isEmpty()?"":" -tn "+addhTectonNameEndTF.getText().trim())+"\n");
                    boolean success = iview.addh(addhHyphaNameTF.getText().trim(), (Fungus)iview.getPlanet().get(addhFungusNameTF.getText().trim()), (Tecton)iview.getPlanet().get(addhTectonNameStartTF.getText().trim()), (addhTectonNameEndTF.getText().trim().isEmpty()?null:(Tecton)iview.getPlanet().get(addhTectonNameEndTF.getText().trim())));
                    System.out.print(">");
                    if (success) {
                        addhHyphaNameTF.setText("");
                        addhFungusNameTF.setText("");
                        addhTectonNameStartTF.setText("");
                        addhTectonNameEndTF.setText("");
                        JOptionPane.showMessageDialog(null, "Hypha successfully created.", "Successful execution message", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        if ((Hypha)iview.getPlanet().get(addhHyphaNameTF.getText().trim())!=null) {
                            JOptionPane.showMessageDialog(null, "This name is already taken!", "Error message", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "The operation failed!", "Error message", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please enter the required information correctly!", "Error message", JOptionPane.ERROR_MESSAGE);
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
        JPanel gameMapJPanel = new JPanel();
        JLabel insectImageIconLb = new JLabel();
        JLabel sporeImageIconLb = new JLabel();
        JLabel hyphaImageIconLb = new JLabel();
        JLabel fungusBodyImageIconLb = new JLabel();
        JLabel tectonImageIconLb = new JLabel();
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

        // menuBt beállításai
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

        // separator3 beállításai
        separator3.setOrientation(SwingConstants.VERTICAL);
        playPanel.add(separator3);
        separator3.setBounds(120, 0, 25, 46);

        // currentPlayerLb beállításai
        currentPlayerLb.setText("Current player: IC1");
        currentPlayerLb.setFont(new Font("Segoe UI", Font.BOLD, 18));
        playPanel.add(currentPlayerLb);
        currentPlayerLb.setBounds(130, 10, 159, 25);

        // amountOfNutrientsLb beállításai
        amountOfNutrientsLb.setText("Amount of nutrients collected: 0");
        amountOfNutrientsLb.setFont(new Font("Segoe UI", Font.BOLD, 18));
        playPanel.add(amountOfNutrientsLb);
        amountOfNutrientsLb.setBounds(325, 10, 279, 25);

        // separator5 beállításai
        separator5.setOrientation(SwingConstants.VERTICAL);
        playPanel.add(separator5);
        separator5.setBounds(685, 0, 25, 46);

        // roundLb beállításai
        roundLb.setText("Round: 1");
        roundLb.setBorder(null);
        roundLb.setFont(new Font("Segoe UI", Font.BOLD, 18));
        playPanel.add(roundLb);
        roundLb.setBounds(695, 10, 77, 25);

        // separator4 beállításai
        separator4.setOrientation(SwingConstants.VERTICAL);
        playPanel.add(separator4);
        separator4.setBounds(800, 0, 25, 46);

        // nextRoundBt beállításai
        nextRoundBt.setText("Next round");
        nextRoundBt.setFocusPainted(false);
        playPanel.add(nextRoundBt);
        nextRoundBt.setBounds(810, 5, 97, 32);

        // nextPlayerBt beállításai
        nextPlayerBt.setText("Next player");
        nextPlayerBt.setFocusPainted(false);
        playPanel.add(nextPlayerBt);
        nextPlayerBt.setBounds(915, 5, 98, 32);

        // separator2 beállításai
        playPanel.add(separator2);
        separator2.setBounds(0, 45, 1020, 20);

        // allSelectableEntitiesLb beállításai
        allSelectableEntitiesLb.setText("All selectable entities:");
        playPanel.add(allSelectableEntitiesLb);
        allSelectableEntitiesLb.setBounds(5, 55, 115, 16);

        // scrollPane1 és allSelectableEntitiesJList beállításai
        allSelectableEntitiesJList.setModel(new AbstractListModel<String>() {
            String[] values = GetKeysFromPlanet();
            @Override
            public int getSize() { return values.length; }
            @Override
            public String getElementAt(int i) { return values[i]; }
        });
        scrollPane1.setViewportView(allSelectableEntitiesJList);
        playPanel.add(scrollPane1);
        scrollPane1.setBounds(5, 75, 125, 470);

        // gameMapLb beállításai
        gameMapLb.setText("Game map:");
        playPanel.add(gameMapLb);
        gameMapLb.setBounds(475, 55, 61, 16);

        // gameMapJPanel beállításai
        gameMapJPanel.setBackground(new Color(0x99ccff));
        gameMapJPanel.setLayout(null);

        // insectImageIconLb beállításai
        insectImageIconLb.setIcon(null);
        gameMapJPanel.add(insectImageIconLb);
        insectImageIconLb.setBounds(new Rectangle(new Point(175, 290), insectImageIconLb.getPreferredSize()));

        // sporeImageIconLb beállításai
        sporeImageIconLb.setIcon(null);
        gameMapJPanel.add(sporeImageIconLb);
        sporeImageIconLb.setBounds(new Rectangle(new Point(170, 225), sporeImageIconLb.getPreferredSize()));

        // hyphaImageIconLb beállításai
        hyphaImageIconLb.setIcon(null);
        gameMapJPanel.add(hyphaImageIconLb);
        hyphaImageIconLb.setBounds(new Rectangle(new Point(270, 25), hyphaImageIconLb.getPreferredSize()));

        // fungusBodyImageIconLb beállításai
        fungusBodyImageIconLb.setIcon(null);
        gameMapJPanel.add(fungusBodyImageIconLb);
        fungusBodyImageIconLb.setBounds(new Rectangle(new Point(220, 50), fungusBodyImageIconLb.getPreferredSize()));

        // tectonImageIconLb beállításai
        tectonImageIconLb.setIcon(null);
        gameMapJPanel.add(tectonImageIconLb);
        tectonImageIconLb.setBounds(new Rectangle(new Point(75, 5), tectonImageIconLb.getPreferredSize()));

        // gameMapJPanel méretének kiszámítása
        Dimension preferredSize = new Dimension();
        for (int i = 0; i < gameMapJPanel.getComponentCount(); i++) {
            Rectangle bounds = gameMapJPanel.getComponent(i).getBounds();
            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
        }
        Insets insets = gameMapJPanel.getInsets();
        preferredSize.width += insets.right;
        preferredSize.height += insets.bottom;
        gameMapJPanel.setMinimumSize(preferredSize);
        gameMapJPanel.setPreferredSize(preferredSize);

        playPanel.add(gameMapJPanel);
        gameMapJPanel.setBounds(140, 75, 730, 385);

        // entitiesForOperationsLb beállításai
        entitiesForOperationsLb.setText("Entities for operations:");
        playPanel.add(entitiesForOperationsLb);
        entitiesForOperationsLb.setBounds(880, 55, 119, 16);

        // scrollPane2 és entitiesForOperationsJList beállításai
        entitiesForOperationsJList.setModel(new AbstractListModel<String>() {
            String[] values = {
                    "T2",
                    "H2",
                    "S1"
            };
            @Override
            public int getSize() { return values.length; }
            @Override
            public String getElementAt(int i) { return values[i]; }
        });
        entitiesForOperationsJList.setSelectedIndex(-1);
        scrollPane2.setViewportView(entitiesForOperationsJList);
        playPanel.add(scrollPane2);
        scrollPane2.setBounds(880, 75, 135, 470);

        // growHyphaBt beállításai
        growHyphaBt.setText("GrowHypha");
        growHyphaBt.setFocusPainted(false);
        playPanel.add(growHyphaBt);
        growHyphaBt.setBounds(140, 470, 100, 32);

        // growFungusBodyFromSporeBt beállításai
        growFungusBodyFromSporeBt.setText("GrowFungusBodyFromSpore");
        growFungusBodyFromSporeBt.setFocusPainted(false);
        playPanel.add(growFungusBodyFromSporeBt);
        growFungusBodyFromSporeBt.setBounds(250, 470, 199, 32);

        // growFungusBodyFromInsectBt beállításai
        growFungusBodyFromInsectBt.setText("GrowFungusBodyFromInsect");
        growFungusBodyFromInsectBt.setFocusPainted(false);
        playPanel.add(growFungusBodyFromInsectBt);
        growFungusBodyFromInsectBt.setBounds(460, 470, 199, 32);

        // produceSporeBt beállításai
        produceSporeBt.setText("ProduceSpore");
        produceSporeBt.setFocusPainted(false);
        playPanel.add(produceSporeBt);
        produceSporeBt.setBounds(670, 470, 116, 32);

        // shootSporeBt beállításai
        shootSporeBt.setText("ShootSpore");
        shootSporeBt.setFocusPainted(false);
        playPanel.add(shootSporeBt);
        shootSporeBt.setBounds(140, 510, 102, 32);

        // eatStunnedInsectBt beállításai
        eatStunnedInsectBt.setText("EatStunnedInsect");
        eatStunnedInsectBt.setFocusPainted(false);
        playPanel.add(eatStunnedInsectBt);
        eatStunnedInsectBt.setBounds(250, 510, 135, 32);

        // eatSporeBt beállításai
        eatSporeBt.setText("EatSpore");
        eatSporeBt.setFocusPainted(false);
        playPanel.add(eatSporeBt);
        eatSporeBt.setBounds(395, 510, 87, 32);

        // moveInsectBt beállításai
        moveInsectBt.setText("MoveInsect");
        moveInsectBt.setFocusPainted(false);
        playPanel.add(moveInsectBt);
        moveInsectBt.setBounds(490, 510, 100, 32);

        // cutHyphaBt beállításai
        cutHyphaBt.setText("CutHypha");
        cutHyphaBt.setFocusPainted(false);
        playPanel.add(cutHyphaBt);
        cutHyphaBt.setBounds(600, 510, 97, 32);

        return playPanel;
    }

    /**
     * Visszatér a View planet nem res-hifa kulcsainak tombjével.
     *
     * @return String array - Keys form View.planet
     */
    public String[] GetKeysFromPlanet(){
        LinkedList<String> keys = new LinkedList<String>();
        LinkedHashMap<String,Object> planet = (LinkedHashMap<String, Object>) iview.getPlanet();

        for (Map.Entry<String, Object> entry : planet.entrySet()) {
            // ha az entry hifa
            if(entry.getKey().startsWith("H")){
                Hypha hypha = (Hypha) entry.getValue();
                // es nem res hifa
                if(hypha.GetTectons().size() < 2){
                    keys.add(entry.getKey());
                }
            }
            else {
                keys.add(entry.getKey());
            }
        }

        return keys.toArray(new String[0]);
    }

    /**
     *  Frissiti a kivalaszthato elemek listajat (bal oldali lista).
     */
    private void RefreshSelectableEntities(){
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
    }

    /*public static void main(String[] args) {
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
            GameGUI frame = new GameGUI();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }*/
}