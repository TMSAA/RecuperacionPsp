package Contactos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Peticion implements Runnable {

	private Socket socket;
	private Agenda agenda;
	private DataOutputStream out;
	private DataInputStream in;

	public Peticion(Socket socket, Agenda agenda) throws IOException {
		this.socket = socket;
		socket.setSoTimeout(30000);
		this.agenda = agenda;
		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());
	}

	@Override
	public void run() {
		try {

			while (true) {
				String peticion = in.readUTF();
				if (peticion.startsWith("nombre:")) {
					enviarRespuesta(agenda.añadirContacto(peticion));
				} else if (peticion.startsWith("buscar:")) {
					enviarRespuesta(agenda.buscarContacto(peticion));
				} else if (peticion.startsWith("eliminar:")) {
					enviarRespuesta(agenda.eliminarContacto(peticion));
				} else if (peticion.equals("contactos")) {
					enviarRespuesta(agenda.contactos());
				} else {
					enviarRespuesta("ERR04: petecion no valida");
				}
			}
		} catch (SocketTimeoutException e) {
			enviarRespuesta("ERROR: Read timed out");
		} catch (EOFException e) {
			enviarRespuesta("ERROR: Se esperaba una peticion");
		} catch (IOException e) {
			enviarRespuesta("ERROR: "+e.getLocalizedMessage());
		}

	}

	private void enviarRespuesta(String respuesta) {
		System.out.println(socket.getInetAddress() + " -> " + respuesta);
		try {
			out.writeUTF(respuesta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
