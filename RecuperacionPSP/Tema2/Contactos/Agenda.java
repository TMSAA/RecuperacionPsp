package Contactos;

import java.util.HashMap;
import java.util.Map;

public class Agenda {

	private static Map<String, Contacto> contactos = new HashMap<>();

	public synchronized String añadirContacto(String peticion) {
		String[] parts = peticion.split(":");
		if (parts.length == 3) {
			String name = parts[1];
			String phone = parts[2];

			if (contactos.containsKey(name)) {
				Contacto contacto = contactos.get(name);
				contacto.añadirTelefono(phone);
				return "OK";
            } else {
                contactos.put(name, new Contacto(name, phone));
                return "OK";
            }
		} else {
			return "ERR03";
		}
	}
	
	public synchronized String buscarContacto(String peticion) {
		String[] parts = peticion.split(":");
		if (parts.length == 3) {
			String name = parts[3];
			if(contactos.containsKey(name)) {
				Contacto contacto = contactos.get(name);
				return contacto.getTelefonos().toString();
			}else {
				return "ERR02";
			}
		} else {
			return "ERR03";
		}
	}
	
	public synchronized String eliminarContacto(String peticon) {
		String[] parts = peticon.split(":");
		if(parts.length == 3) {
			String name = parts[3];
			if(contactos.containsKey(name)) {
				contactos.remove(name);
				return "OK";
			}else {
				return "ERR02";
			}
		}else {
			return "ERR03";
		}
	}
	
	public synchronized String contactos() {
		if (contactos.isEmpty()) {
            return "No hay contactos";
        } else {
            StringBuilder cont = new StringBuilder("Lista de contactos:\n");
            for (Contacto contacto : contactos.values()) {
                cont.append(contacto).append("\n");
            }
            return cont.toString();
        }
	}
}
