package in.leaf.abhi.treasurehunt;

/**
 * Created by 500060150 on 04-01-2018.
 */

public class BackgroundThread implements Runnable {
    private Runnable task;
    private boolean suspended;
    private boolean alive;

    public BackgroundThread(Runnable taks) {
        this.task=taks;
        new Thread(this).start();
    }

    synchronized public void stop() {
        alive=false;
        notify();
    }

    private void suspend() {
        suspended=true;
    }

    synchronized private void resume() {
        suspended=false;
        notify();
    }

    synchronized public void execute(Runnable task) {
        while(task!=null) {
            try {
                wait();
            }catch (InterruptedException iE) {
                iE.printStackTrace();
            }
        }
        this.task=task;
        resume();
    }

    @Override
    synchronized public void run() {
        alive=true;
        while(alive) {
            while(suspended) {
                try {
                    wait();
                }catch(InterruptedException iE) {
                    iE.printStackTrace();
                }
            }
            if(task!=null)
                task.run();
            task=null;
            System.out.println("Task Completed\n\n\n\n");
            suspend();
        }
    }
}
