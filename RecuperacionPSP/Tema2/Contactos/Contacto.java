package Contactos;

import java.util.ArrayList;
import java.util.List;

public class Contacto {
	
	private String nombre;
	private List<String> telefonos;

	public Contacto(String nombre, String telefono) {
		this.nombre = nombre;
		this.telefonos = new ArrayList<>();
		this.telefonos.add(telefono);
	}

	public void añadirTelefono(String telefono) {
		this.telefonos.add(telefono);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<String> getTelefonos() {
		return new ArrayList<>(telefonos);
	}

	@Override
	public String toString() {
		return "Contacto{" + "nombre='" + nombre + '\'' + ", telefonos=" + telefonos + '}';
	}
}
