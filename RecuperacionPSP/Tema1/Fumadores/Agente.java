package Fumadores;

import java.util.Random;


public class Agente extends Thread{

	private long retardo;
	private Mesa mesa;
	Random random = new Random();
	int IngredinteAleatorio = random.nextInt(Ingredientes.values().length);
	public Agente(Mesa mesa, long retardo) {
		super("Agente");
		this.retardo = retardo;
		this.mesa = mesa;
	}
	@Override
	public void run() {
		while(true) {
			Ingredientes ingrediente1 = Ingredientes.values()[IngredinteAleatorio];
			 Ingredientes ingrediente2;
	            do {
	                ingrediente2 = Ingredientes.values()[random.nextInt(Ingredientes.values().length)];
	            } while (ingrediente2 == ingrediente1);
			mesa.colocar(ingrediente1, ingrediente2);
			System.out.println("Angente coloca ingrediente: " + ingrediente1);
			Main.actualizar("Angente coloca ingrediente: " + ingrediente1+"\n");
			System.out.println("Angente colcoa ingrediente: " + ingrediente2);
			Main.actualizar("Angente colcoa ingrediente: " + ingrediente2+"\n");
			try {
				Thread.sleep(retardo);
			} catch (InterruptedException e) {
				Thread.interrupted();
			}
		}
	}
}
