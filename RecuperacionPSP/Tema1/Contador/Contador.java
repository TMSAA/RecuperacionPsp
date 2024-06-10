package Contador;

public class Contador {

	long valor;
	
	public Contador() {
		
	}
	
	public Contador(long valor) {
		this.valor = valor;
	}
	
	public void incrementar() {
		valor++;
	}
	
	public long getValor() {
		return valor;
	}
}
