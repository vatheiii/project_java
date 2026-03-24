import java.util.InputMismatchException;
import java.util.Scanner;

public final class ExceptionRentalSystem {
    private ExceptionRentalSystem() {
    }

    public static int readInt(Scanner sc) {
        while (true) {
            try {
                if (!sc.hasNextInt()) {
                    throw new InputMismatchException();
                }
                return sc.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Please enter a whole number.");
                if (sc.hasNext()) {
                    sc.next().trim();
                }
            }
        }
    }

    public static Integer readMenuIntOrNull(Scanner sc, String message) {
        try {
            if (!sc.hasNextInt()) {
                throw new InputMismatchException();
            }
            return sc.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println(message);
            if (sc.hasNext()) {
                sc.next().trim();
            }
            return null;
        }
    }

    public static double readDouble(Scanner sc) {
        while (true) {
            try {
                if (!sc.hasNextDouble()) {
                    throw new InputMismatchException();
                }
                return sc.nextDouble();
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Please enter a valid number.");
                if (sc.hasNext()) {
                    sc.next().trim();
                }
            }
        }
    }
}
