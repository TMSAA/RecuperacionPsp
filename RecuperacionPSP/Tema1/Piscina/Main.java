package Piscina;

public class Main {

	public static void main(String[] args) {
		Piscina piscina = new Piscina(8);
		Nadadores[] nadadores = new Nadadores[10];
		Buceadores[] buceadores = new Buceadores[10];
		
		for (int i = 0; i < nadadores.length; i++) {
			nadadores[i] = new Nadadores(piscina, i+1);
		}
		
		for (int i = 0; i < buceadores.length; i++) {
			buceadores[i] = new Buceadores(piscina, i+1);
		}
		
		for (int i = 0; i < nadadores.length; i++) {
			nadadores[i].start();
		}
		for (int i = 0; i < buceadores.length; i++) {
			buceadores[i].start();
		}
	}
}
