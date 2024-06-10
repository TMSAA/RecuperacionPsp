package Ejercicio1;

public class Uno extends Thread{

	public static void main(String[] args) {
		int N = 5;
		Thread[] hilos = new Thread[N];
		
		for (int i = 0; i < N; i++) {
			final int numHilo = i;
			hilos[i] = new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("Hilo "+numHilo+ " iniciando. Timpo de dormir: "+(numHilo+1)+" segundos");
					try {
						Thread.sleep((numHilo+1)*1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("Hilo "+numHilo+" hilo finalizado");
				}
			});
			hilos[i].start();
		}
		for(Thread hilo: hilos) {
			try {
				hilo.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Todos los hilos han finalizado su tarea ");
	}
}
