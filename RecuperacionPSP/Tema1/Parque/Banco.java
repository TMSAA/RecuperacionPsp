package Parque;

public class Banco {

	private int plazasLibres;
	private boolean pausa = false;

	public Banco(int plazas) {
		plazasLibres = plazas;
	}

	public synchronized void ocuparSitioLibre() {
		while (plazasLibres == 0 || pausa) {
			try {
				System.out.println(Thread.currentThread().getName() + " espera");
				Main.actualizar(Thread.currentThread().getName() + " espera \n");
				wait();
			} catch (InterruptedException e) {
				Thread.interrupted();
			}
		}
		plazasLibres--;

	}

	public synchronized void liberarSitioOcupado() {
		
			plazasLibres++;
			notifyAll();
		
	}

	public synchronized void Pausar() {
		pausa = true;
	}
	
	public synchronized void Reanudar() {
		pausa = false;
		notifyAll();
	}
}
