

import java.io.*;
import java.net.*;

public class MultiServer implements Runnable {
	Socket sock;
	static int nclient = 0, nclients = 0;
	static ServerSocket servsock;

	static final String END = "end";

	public static void main(String arg[]) throws IOException {
		int port = 2000;
		servsock = new ServerSocket(port);
		Thread servDaemon = new Thread(new MultiServer());
		servDaemon.start();
		System.out.println("Server alive on " + InetAddress.getLocalHost() + " in port " + port + "!");
	}

	@Override
	public void run() {
		for (;;) {
			try {
				sock = servsock.accept();
				nclient += 1;
				nclients += 1;
				System.out.println("Connecting " + sock.getInetAddress() + " in port " + sock.getPort() + ".");
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
			Worked w = new Worked(sock, nclient);
			w.start();
		}
	}

	class Worked extends Thread {
		Socket sock;
		int clientid, markPosition;
		String strcliid;

		public Worked(Socket s, int nclient) {
			sock = s;
			clientid = nclient;
		}

		@Override
		public void run() {
			String clientData;
			PrintStream out = null;
			try {
				out = new PrintStream(sock.getOutputStream());
				BufferedReader br1 = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				out.println(clientid);

				clientData = br1.readLine();

				markPosition = clientData.indexOf("#");
				strcliid = clientData.substring(0, markPosition);
				clientData = clientData.substring(markPosition + 1, clientData.length());
				while (!clientData.equals(END)) {
					System.out.println("Cli[" + strcliid + "]: " + clientData);
					out.println("String '" + clientData + "' received.");
					out.flush();
					clientData = br1.readLine();
					markPosition = clientData.indexOf("#");
					strcliid = clientData.substring(0, markPosition);
					clientData = clientData.substring(markPosition + 1, clientData.length());
				}
				System.out.println("Disconnecting " + sock.getInetAddress() + "!");
				nclients -= 1;
				System.out.println("Current number of clients: " + nclients);
				if (nclients == 0) {
					System.out.println("Ending server...");
					sock.close();
					System.exit(0);
				}
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		}
	}
}
