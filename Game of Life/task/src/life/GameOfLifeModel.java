package life;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class GameOfLifeModel {
    public static final char ALIVE_CELL = 'O';
    public static final char DEAD_CELL = ' ';
    private static final Random randomGen = new Random();

    private final char[] world;
    private final int size;
    private int generationNumber;

    public GameOfLifeModel(int size) {
        this.world = new char[size * size];
        this.size = size;
        generate();
    }

    public boolean isAlive(int row, int col) {
        return get(row, col) == ALIVE_CELL;
    }

    public char[] getWorld() {
        return world;
    }

    public int getSize() {
        return size;
    }

    public void reset() {
        generationNumber = 0;
        generate();
    }

    private void generate() {
        generationNumber++;
        for (int i = 0; i < world.length; i++) {
            boolean alive = randomGen.nextBoolean();
            world[i] = alive ? ALIVE_CELL : DEAD_CELL;
        }
    }
    public int generation() {
        return generationNumber;
    }

    public int alive() {
        long count = IntStream.range(0, world.length)
            .mapToObj(i -> world[i])
            .filter(c -> c == ALIVE_CELL)
            .count();
        return (int) count;
    }

    public void nextGeneration() {
        generationNumber++;
        char[] newWorld = Arrays.copyOf(world, world.length);
        for (int i = 0; i < world.length; i++) {
            int neighbors = countNeighbors(i);
            if (world[i] == ALIVE_CELL) {
                if (neighbors < 2 || neighbors > 3) {
                    newWorld[i] = DEAD_CELL;
                }
            }
            if (world[i] == DEAD_CELL) {
                if (neighbors == 3) {
                    newWorld[i] = ALIVE_CELL;
                }
            }
        }
        updateWorld(newWorld);
    }

    private void updateWorld(char[] newWorld) {
        System.arraycopy(newWorld, 0, world, 0, world.length);
    }

    private char get(int row, int col) {
        return world[index(row, col)];
    }

    private int countNeighbors(int index) {
        int count = 0;
        int startRow = row(index) - 1;
        int startCol = col(index) - 1;
        for (int row = startRow; row <= startRow + 2; row++) {
            for (int col = startCol; col <= startCol + 2; col++) {
                if (index(row, col) == index) {
                    continue;
                }
                int wRow = wrap(row);
                int wCol = wrap(col);
                if (get(wRow, wCol) == ALIVE_CELL) {
                    count++;
                }
            }
        }
        return count;
    }

    private int wrap(int val) {
        int wrapped = val;
        if (wrapped >= size) {
            wrapped %= size;
        }
        if (wrapped < 0) {
            wrapped += size;
        }
        return wrapped;
    }

    @Override
    public String toString() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                out.print(world[index(row, col)]);
            }
            out.println();
        }
        return baos.toString();
    }

    private int row(int index) {
        return index / size;
    }

    private int col(int index) {
        return index % size;
    }

    private int index(int row, int col) {
        return row * size + col;
    }
}
