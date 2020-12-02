package life;

public class GameOfLifeController {

    private static final int MIN_DELAY = 1;
    private static final int MAX_DELAY = 400;

    private enum State {
        STOPPED,
        RUNNING,
        PAUSED
    }

    private State state = State.STOPPED;
    private Thread updateThread;
    private int speed = 50;

    private final GameOfLifeModel model;
    private final Runnable updateCallback;

    public GameOfLifeController(GameOfLifeModel model, Runnable updateCallback) {
        this.model = model;
        this.updateCallback = updateCallback;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void start() {
        if (state != State.PAUSED && state != State.STOPPED) {
            return;
        }
        if (state == State.STOPPED) {
            model.reset();
            setState(State.PAUSED);
        }
        resume();
    }

    public void pause() {
        if (state != State.RUNNING) {
            return;
        }
        setState(State.PAUSED);
    }

    public void restart() {
        pause();
        stop();
        start();
    }

    private void resume() {
        if (state != State.PAUSED) {
            return;
        }
        setState(State.RUNNING);
    }

    private void stop() {
        setState(State.STOPPED);
    }

    private void setState(State state) {
        this.state = state;
        System.out.println(state);
        switch (state) {
            case RUNNING:
                startUpdateThread();
                break;
            case PAUSED:
                stopUpdateThread();
                break;
            case STOPPED:
                model.reset();
                break;
            default:
                throw new RuntimeException("Unsupported state: " + state);
        }
    }

    private int updateDelayMs() {
        int delay = MAX_DELAY + MIN_DELAY;
        delay -= MAX_DELAY * speed * 0.01;
        return delay;
    }

    private void startUpdateThread() {
        if (updateThread != null) {
            return;
        }
        updateThread = new Thread(() -> {
            System.out.println("Start update thread");
            while (!Thread.interrupted()) {
                model.nextGeneration();
                updateCallback.run();
                try {
                    Thread.sleep(updateDelayMs());
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("Finish update thread");
        });
        updateThread.start();
    }

    private void stopUpdateThread() {
        if (updateThread == null) {
            return;
        }
        updateThread.interrupt();
        try {
            updateThread.join();
        } catch (InterruptedException ignored) { }
        updateThread = null;
    }
}
