package fungorium;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tester tester = new Tester();
        while (true) {
            
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
                    System.out.println("Grow Hypha selected.");
                    break;
                case 2:
                    System.out.println("Atrophy of Hypha selected.");
                    break;
                case 3:
                    System.out.println("Grow FungusBody selected."); //ez egész jól néz ki
                    Tester tester3 = new Tester();
                    tester3.TectonBreakInit();
                    tester3.Test_GrowFungusBodySuccess();
                    tester3.Test_GrowFungusBodyOnWeakTecton();
                    tester3.Test_GrowFungusBodyOnBody();
                    tester3.Test_GrowFungusBodyNotEnoughSpores();
                    break;
                case 4:
                    Tester tester4 = new Tester();
                    tester4.Test_FullTectonBreaks();
                    break;
                case 5:
                    System.out.println("Hypha Absorb selected.");
                    Tester tester5 = new Tester();
                    tester5.TectonBreakInit();
                    tester5.Test_HyphaAbsorbSuccessful();
                    tester5.Test_HyphaAbsorbUnsuccessful();
                    break;
                case 6:
                    System.out.println("Insect Move selected.");
                    Tester tester6 = new Tester();
                    tester6.Test_MoveUnsuccessful();
                    tester6.Test_MoveSuccessful();
                    break;
                case 7:
                    Tester tester7 = new Tester();
                    tester7.Test_CutNotAble();
                    tester7.Test_CutNoBridge();
                    tester7.Test_CutSuccessful();
                    break;
                case 8:
                    Tester tester8 = new Tester();
                    tester8.Test_EatSporeUnsuccessful();
                    tester8.Test_EatSporeSuccessful();
                    break;
                case 9:
                    tester.Test_BasicShootSporesSuccessful();
                    break;
                case 10:
                    tester.Test_FungusBodyDieSuccessful();
                    break;
                case 11:
                    tester.Test_ProduceSporeSuccessful();
                    break;
                default:
                    System.out.println("There is no such option, please try again.");
            }
        }
        scanner.close();
    }

    /**
     * Beolvassa és ellenőrzi a felhasználó által küldött bemenetet.
     * @param scanner Scanner objektum a felhasználói bemenet olvasásához
     * @return a kiválasztott menüopció sorszáma egész számként (parseInt segítségével az összes létező karaktert egész számmá castoljuk)
     * Ha a bemenet nem érvényes szám, akkor a függvény -1-el tér vissza.
     */
    private static int getInput(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
