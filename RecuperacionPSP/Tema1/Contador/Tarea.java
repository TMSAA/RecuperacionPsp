package Contador;

public class Tarea implements Runnable{

	Contador contador;
	
	public Tarea(Contador contador) {
		this.contador = contador;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			contador.incrementar();
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		Contador contador = new Contador(150);
		Tarea t = new Tarea(contador);
		Thread h1 = new Thread(t);
		Thread h2 = new Thread(t);
		h1.start();
		h2.start();
		h1.join();
		h2.join();
		System.out.println(contador.getValor());
	}
}
