package ServidorAritmetico;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		int port = 9999;

		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Servidor de operaciones iniciado en el puerto " + port);

			while (true) {
				try (Socket clientSocket = serverSocket.accept()) {
					System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

					DataInputStream input = new DataInputStream(clientSocket.getInputStream());
					DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

					String operation = input.readUTF();
					double operand1 = input.readDouble();
					double operand2 = input.readDouble();

					double result = 0;
					boolean validOperation = true;

					switch (operation.toLowerCase()) {
					case "suma":
						result = operand1 + operand2;
						break;
					case "resta":
						result = operand1 - operand2;
						break;
					case "multiplicacion":
						result = operand1 * operand2;
						break;
					case "division":
						if (operand2 != 0) {
							result = operand1 / operand2;
						} else {
							validOperation = false;
							output.writeUTF("Error: División por cero");
						}
						break;
					default:
						validOperation = false;
						output.writeUTF("Error: Operación no válida");
					}

					if (validOperation) {
						output.writeUTF("Resultado: " + result);
					}
					output.flush();
				} catch (IOException e) {
					System.out.println("Error al manejar la conexión del cliente: " + e.getMessage());
				}
			}
		} catch (IOException e) {
			System.out.println("No se pudo iniciar el servidor en el puerto " + port + ": " + e.getMessage());
		}
	}
}
