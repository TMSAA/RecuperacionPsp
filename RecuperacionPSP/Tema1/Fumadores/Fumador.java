package Fumadores;

import java.util.Random;


public class Fumador extends Thread{

	private long retardo;
	private Mesa mesa;
	private Ingredientes ingrediente;
	Random random = new Random();
	int IngredinteAleatorio = random.nextInt(Ingredientes.values().length);
	
	public Fumador(Mesa mesa, long retardo, Ingredientes ingrediente) {
		super("Fumador");
		this.mesa = mesa;
		this.retardo = retardo;
		this.ingrediente = ingrediente;
	}
	@Override
	public void run() {
		while (true) {
			mesa.retirar(ingrediente);
			System.out.println("Fumador con el ingrediente "+ingrediente+" empieza a fumar");
			Main.actualizar("Fumador con el ingrediente "+ingrediente+" empieza a fumar \n");
			try {
				Thread.sleep(retardo);
			} catch (InterruptedException e) {
				Thread.interrupted();
			}
		}
	}
}
