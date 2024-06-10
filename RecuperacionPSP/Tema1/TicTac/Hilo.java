package TicTac;

public class Hilo extends Thread{

	private Object lock;

    public Hilo(Object lock) {
        this.lock = lock;
    }
	@Override
	public void run() {
		while(true) {
            synchronized (lock) {
                System.out.println("TIC");
                lock.notify(); 
                try {
                    lock.wait(); 
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
		}
	}
}
