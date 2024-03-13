import java.util.Scanner;
import java.time.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj wiek: ");
        int age = scanner.nextInt();
        System.out.print("Czy jestes mieszkancem Warszawy? (tak/nie): ");
        String resident = scanner.next().toLowerCase();
        String day = LocalDate.now().getDayOfWeek().name().toLowerCase();
        double ticketPrice = calculateTicketPrice(age, resident.equals("tak"), day);
        String discountInfo = calculateDiscountInfo(age, resident.equals("tak"), day);
        System.out.println("Dane klienta: Wiek: " + age + " lat, Mieszkaniec Warszawy: " + resident + ", Dzien tygodnia: " + day);
        System.out.println("Cena biletu po rabacie: " + ticketPrice + " zł");
        System.out.println("Informacja o zniżce: " + discountInfo);
        runUnitTests();
    }

    public static double calculateTicketPrice(int age, boolean isWarsawResident, String day) {
        double basePrice = 40;
        double ticketPrice = basePrice;
        if (age < 10 || day.equals("thursday")) {
            ticketPrice = 0;
        } else if (age >= 10 && age <= 18) {
            ticketPrice *= 0.5;
        }
        if (isWarsawResident) {
            ticketPrice *= 0.9;
        }
        return ticketPrice;
    }

    public static String calculateDiscountInfo(int age, boolean isWarsawResident, String day) {
        StringBuilder discountInfo = new StringBuilder();
        if (age < 10 || day.equals("thursday")) {
            discountInfo.append("Bezpłatny wstep");
        } else if (age >= 10 && age <= 18) {
            discountInfo.append("50% znizki");
        }
        if (isWarsawResident) {
            if (discountInfo.length() > 0) {
                discountInfo.append(", ");
            }
            discountInfo.append("Dodatkowa 10% znizki dla mieszkancow Warszawy");
        }
        return discountInfo.toString();
    }

    public static void runUnitTests() {
        testCalculateTicketPrice();
        testCalculateDiscountInfo();
    }

    public static void testCalculateTicketPrice() {
        assert calculateTicketPrice(5, false, "monday") == 0;
        assert calculateTicketPrice(15, false, "tuesday") == 20;
        assert calculateTicketPrice(25, true, "wednesday") == 18;
        assert calculateTicketPrice(30, false, "thursday") == 0;
    }

    public static void testCalculateDiscountInfo() {
        assert calculateDiscountInfo(5, false, "monday").equals("Bezpłatny wstęp");
        assert calculateDiscountInfo(15, false, "tuesday").equals("50% zniżki");
        assert calculateDiscountInfo(25, true, "wednesday").equals("Dodatkowa 10% zniżki dla mieszkańców Warszawy");
        assert calculateDiscountInfo(30, false, "thursday").equals("Bezpłatny wstęp");
    }
}