package TicTac;

public class Hilo1 extends Thread{
	private Object lock;

    public Hilo1(Object lock) {
        this.lock = lock;
    }
	@Override
	public void run() {
		while(true) {
            synchronized (lock) {
                System.out.println("TAC");
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
