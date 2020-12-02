package life;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int size = scanner.nextInt();
        new Life(size).startSimulation();
    }

}

class Life {

    private static final int PAUSE = 150;
    private static final int GENERATION_COUNT = 500;

    private final GameOfLifeModel world;

    public Life(int size) {
        world = new GameOfLifeModel(size);
    }

    public void startSimulation() {
        draw();
        for (int i = 1; i < GENERATION_COUNT; i++) {
            sleep();
            world.nextGeneration();
            draw();
        }
    }

    private void draw() {
        clear();
        System.out.printf("Generation: #%d\n", world.generation());
        System.out.printf("Alive: %d\n", world.alive());
        System.out.println();
        System.out.print(world);
    }

    private static void clear() {
        System.out.print("\033[H\033[2J\033[3J");
    }

    private static void sleep() {
        try {
            Thread.sleep(PAUSE);
//            Runtime.getRuntime().exec("clear");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
