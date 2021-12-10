

import java.net.*;
import java.io.*;

public class MultiClient {
	
	static final String END = "end";
	
	public static void main(String arg[]) throws IOException {
		Socket sock = new Socket(InetAddress.getLocalHost(), 2000);

		BufferedReader br1 = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		//System.out.println("1"+br1.readLine());
		PrintStream printStreamDAT = new PrintStream(sock.getOutputStream());
		//System.out.println("2"+(sock.getOutputStream()));
		String strcliid = br1.readLine();
		System.out.println("Client n." + strcliid + " in server.");
		String consoleData;
		String serverData;
		BufferedReader consoleDataInputReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("Client[" + strcliid + "]: ");
			consoleData = consoleDataInputReader.readLine();
			while (!consoleData.equals(END)) {
				consoleData = strcliid + "#" + consoleData;
				System.out.println(consoleData);
				printStreamDAT.println(consoleData);
				printStreamDAT.flush();
				serverData = br1.readLine();
				System.out.println("Server: " + serverData);
				System.out.print("Client[" + strcliid + "]: ");
				consoleData = consoleDataInputReader.readLine();
			}
			consoleData = strcliid + "#" + consoleData;
			printStreamDAT.println(consoleData);
			printStreamDAT.flush();
			sock.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
