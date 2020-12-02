import java.util.Scanner;

class StringProcessor extends Thread {

    final Scanner scanner = new Scanner(System.in); // use it to read string from the standard input

    @Override
    public void run() {
        // implement this method
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String transformed = line.toUpperCase();
            if (transformed.equals(line)) {
                break;
            }
            System.out.println(transformed);
        }
        System.out.println("FINISHED");
    }
}