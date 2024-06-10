package BarberoDormilon;

public class Cliente extends Thread{

	private Barbero barbero;
	private Barberia barberia;
	private long crecerPelo;
	
	public Cliente(Barbero barbero, Barberia baberia, String nombre) {
		super(nombre);
		this.barbero = barbero;
		this.barberia = baberia;
		crecerPelo = (long) ((Math.random()*1000000 % 9001)+1000);
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				barberia.ocuparSitioLibre();
				barbero.cortarPelo();
				barberia.dejarSitioLibre();
				Thread.sleep(crecerPelo);
			} catch (InterruptedException e) {
				Thread.interrupted();
			}
		}
	}
}
