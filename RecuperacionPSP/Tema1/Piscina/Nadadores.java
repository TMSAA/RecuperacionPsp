package Piscina;

public class Nadadores extends Thread{

	private Piscina piscina;

	public Nadadores(Piscina piscina, int id) {
		super("Nadador "+id);
		this.piscina = piscina;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				piscina.nadar(getName());
				Thread.sleep(6000);
			}
		} catch (InterruptedException e) {
			Thread.interrupted();
		}
	}
}
