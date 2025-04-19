package fungorium;

import java.util.Scanner;

public class View {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
}
