package Fumadores;

import java.util.Random;

public enum Ingredientes {

	tabaco,papel,cerilla;
	
	private static final Random random = new Random();
	
	public static Ingredientes randomIngredinte() {
		Ingredientes[] ingredientes = values();
		return ingredientes[random.nextInt(ingredientes.length)];
	}
}
