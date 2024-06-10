package Piscina;

public class Buceadores extends Thread {

	private Piscina piscina;

	public Buceadores(Piscina piscina, int id) {
		super("Buceador "+id);
		this.piscina = piscina;
	}

	@Override
	public void run() {
		try {
			while (true) {
				piscina.bucear(getName());
				Thread.sleep(6000);
			}
		} catch (InterruptedException e) {
			Thread.interrupted();
		}
	}
}
