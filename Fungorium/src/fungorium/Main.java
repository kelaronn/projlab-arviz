package fungorium;

import java.util.Scanner;

public class Main {

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
                    System.out.println("Grow FungusBody selected.");
                    break;
                case 4:
                    System.out.println("Full Tecton Breaks selected.");
                    break;
                case 5:
                    System.out.println("Hypha Absorb selected.");
                    break;
                case 6:
                    System.out.println("Insect Move selected.");
                    break;
                case 7:
                    System.out.println("Insect Cut selected.");
                    break;
                case 8:
                    System.out.println("Insect EatSpore selected.");
                    break;
                case 9:
                    System.out.println("ShootSpores selected.");
                    break;
                case 10:
                    System.out.println("FungusBody death selected.");
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
        } catch (Exception e) {
            return -1;
        }
    }
}
