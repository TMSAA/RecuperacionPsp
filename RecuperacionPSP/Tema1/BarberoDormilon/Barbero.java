package BarberoDormilon;

public class Barbero {

	private boolean barberoDurmiendo = true;
	private boolean trabajar = false;

	public synchronized void cortarPelo() {
		while (trabajar == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.interrupted();
			}
		}
		if (barberoDurmiendo == true) {
			System.out.println(Thread.currentThread().getName() + " desierta al barbero");
			Main.actualizar(Thread.currentThread().getName() + " desierta al barbero\n");
			barberoDurmiendo = false;
		}
		System.out.println("Babero corta el pelo a " + Thread.currentThread().getName());
		Main.actualizar("Babero corta el pelo a " + Thread.currentThread().getName() + "\n");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.interrupted();
		}
		notifyAll();
	}

}
