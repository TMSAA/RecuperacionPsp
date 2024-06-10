package Contador;

public class UnaTarea implements Runnable{

	Contador contador;
	
	public UnaTarea(Contador contador) {
		this.contador = contador;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			contador.incrementar();
		}
		
	}
	
	public static void main(String[] args) {
		Contador contador = new Contador(100);
		Thread h1 = new Thread(new UnaTarea(contador));
		Thread h2 = new Thread(new UnaTarea(contador));
		h1.start();
		h2.start();
		System.out.println(contador.getValor());
	}

}
