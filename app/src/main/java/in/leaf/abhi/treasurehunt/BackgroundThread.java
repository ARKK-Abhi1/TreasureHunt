package in.leaf.abhi.treasurehunt;

/**
 * Created by 500060150 on 04-01-2018.
 */

final public class BackgroundThread implements Runnable {
    private Runnable task;
    private boolean suspended;
    private boolean alive;

    public BackgroundThread(Runnable taks) {
        this.task=taks;
        new Thread(this).start();
    }

    public void stop() {
        alive=false;
    }

    private void suspend() {
        suspended=true;
    }

    synchronized private void resume() {
        suspended=false;
        notifyAll();
    }

    synchronized public void execute(Runnable task) throws IllegalStateException {
        /* This while loop will cause any threads other than the thread
           running the run() method to wait(after notify() in resume() is called
           until the current task is finished
         */
        while(task!=null) {
            try {
                wait();
            }catch (InterruptedException iE) {
                iE.printStackTrace();
            }
        }
        if(!alive) {
            /* notify any other waiting threads(if any) so that they can resume. This is
              necessary because the thread executing the run() method has stopped,
              thus there will be not other means to notify the threads which have called
              execute() method on this object.
             */
            notifyAll();
            throw new IllegalStateException();
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
                    /* Notify the threads WAITING(if any) to execute their tasks that
                       the previous task has been completed.
                     */
                    notifyAll();
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
        /* call to notify is important before the thread dies otherwise other WAITING(if any)
           threads will be dead-locked
        */
        notifyAll();
    }
}
