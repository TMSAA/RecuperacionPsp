package Contactos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		String serverAddress = "localhost"; 
		int port = 9999;

		try (Socket socket = new Socket(serverAddress, port);
				DataInputStream input = new DataInputStream(socket.getInputStream());
				DataOutputStream output = new DataOutputStream(socket.getOutputStream());
				Scanner scanner = new Scanner(System.in)) {

			System.out.println("Conectado al servidor en " + serverAddress + ":" + port);
			System.out.println("Escribe tu solicitud:");

			while (true) {
				System.out.println("1. Añadir contacto (nombre:telefono)");
				System.out.println("2. Buscar contacto (buscar:nombre)");
				System.out.println("3. Eliminar contacto (eliminar:nombre)");
				System.out.println("4. Listar todos los contactos (contactos)");
				System.out.println("5. Salir");

				String option = scanner.nextLine();
				String request;

				switch (option) {
				case "1":
					System.out.print("Introduce el nombre: ");
					String nombre = scanner.nextLine();
					System.out.print("Introduce el teléfono: ");
					String telefono = scanner.nextLine();
					request = "nombre:" + nombre + ":" + telefono;
					break;
				case "2":
					System.out.print("Introduce el nombre: ");
					nombre = scanner.nextLine();
					request = "buscar:" + nombre;
					break;
				case "3":
					System.out.print("Introduce el nombre: ");
					nombre = scanner.nextLine();
					request = "eliminar:" + nombre;
					break;
				case "4":
					request = "contactos";
					break;
				case "5":
					System.out.println("Cerrando el cliente.");
					return;
				default:
					System.out.println("Opción no válida. Inténtalo de nuevo.");
					continue;
				}

				output.writeUTF(request);
				output.flush();

				String response = input.readUTF();
				System.out.println("Respuesta del servidor:\n" + response);
			}
		} catch (IOException e) {
			System.err.println("Error al conectar con el servidor: " + e.getMessage());
		}
	}
}
