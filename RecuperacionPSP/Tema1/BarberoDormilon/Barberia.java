package BarberoDormilon;

public class Barberia {

	private int sillas;
	private boolean pausa = false;

	public Barberia(int sillas) {
		super();
		this.sillas = sillas;
	}

	public synchronized void ocuparSitioLibre() throws InterruptedException {
		while (sillas == 0 | pausa) {
			System.out.println("La barberia esta llena " + Thread.currentThread().getName() + " se va");
			Main.actualizar("La barberia esta llena " + Thread.currentThread().getName() + " se va\n");
			wait();
		}
		sillas--;
		System.out.println(Thread.currentThread().getName() + " espera su turno");
		Main.actualizar(Thread.currentThread().getName() + " espera su turno\n");
	}

	public synchronized void dejarSitioLibre() {
		while (pausa) {
			sillas++;
			notifyAll();
		}
	}
	
	public synchronized void Pausar() {
		pausa = true;
	}
	
	public synchronized void Reanudar() {
		pausa = false;
	}
}
