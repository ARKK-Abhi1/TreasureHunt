package in.leaf.abhi.treasurehunt;

/**
 * Created by 500060150 on 04-01-2018.
 */

final public class BackgroundThread implements Runnable {
    private Runnable task;
    private boolean suspended;
    private boolean alive;
    private static BackgroundThread thisReference;
    private BackgroundThread() {
        new Thread(this).start();
        thisReference=this;
        alive=true;
    }

    synchronized public void execute(Runnable task) throws IllegalStateException {
        /* This while loop will cause any threads other than the thread
           running the run() method to wait(after notify() in resume() is called
           until the current task is finished
         */
        while(this.task!=null) {
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

    public static BackgroundThread getBackgroundThread() {
        if(thisReference!=null) {
            System.out.println("this reference");
            return thisReference;
        }
        return new BackgroundThread();
    }

    public static BackgroundThread getBackgroundThread(boolean newThread) {
        if(newThread||thisReference==null)
            return new BackgroundThread();
        else
            return thisReference;
    }
    synchronized private void resume() {
        suspended=false;
        notifyAll();
    }

    @Override
    synchronized public void run() {
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

    public void stop() {
        alive=false;
    }

    private void suspend() {
        suspended=true;
    }
}
