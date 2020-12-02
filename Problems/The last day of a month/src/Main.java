import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        int dayOfYear = scanner.nextInt();
        LocalDate date = LocalDate.ofYearDay(year, dayOfYear);
        boolean isLastDayOfMonth = date.getDayOfMonth() == date.lengthOfMonth();
        System.out.println(isLastDayOfMonth);
    }
}