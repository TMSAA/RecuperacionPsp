package TicTac;

public class Ej {

	public static void main(String[] args) {
		
		Object lock = new Object();
		Hilo h1 = new Hilo(lock);
		Hilo1 h2 = new Hilo1(lock);
		
		h1.start();
		h2.start();
	}
}
