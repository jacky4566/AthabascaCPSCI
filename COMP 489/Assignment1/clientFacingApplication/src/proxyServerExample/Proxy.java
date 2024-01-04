package proxyServerExample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

//See https://stackoverflow.com/questions/9357585/creating-a-java-proxy-server-that-accepts-https
//see http://www.jcgonzalez.com/java-simple-proxy-socket-server-examples#4

public class Proxy implements Runnable {
	private static final Logger LOGGER = Logger.getLogger(MainApp.class.getName());

	private int proxyPort;
	private ServerSocket serverSocket;

	public Proxy(int port) {
		proxyPort = port;
	}

	public void stop() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			LOGGER.info("Proxy Start");
			serverSocket = new ServerSocket(proxyPort);
			System.out.println("Proxy server is listening on port " + proxyPort);

			try {
				while (true) {
					Socket clientSocket = serverSocket.accept();
					Thread thread = new Thread(() -> handleClientRequest(clientSocket));
					thread.start();
				}
			} catch (IOException e) {
				e.printStackTrace(); // TODO: implement catch
			}

		} catch (java.net.SocketException e) {
			LOGGER.warning("SocketException");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void handleClientRequest(Socket clientSocket) {
		try {
			InputStream clientIn = clientSocket.getInputStream();
			OutputStream clientOut = clientSocket.getOutputStream();

			byte[] buffer = new byte[4096];
			int bytesRead;

			// Read the client's request
			bytesRead = clientIn.read(buffer);
			String request = new String(buffer, 0, bytesRead);

			// Extract the target URL from the request
			String targetUrl = extractTargetUrl(request);
			LOGGER.info(targetUrl);

			// Open a connection to the target server
			Socket targetSocket = new Socket(targetUrl, 80);
			InputStream targetIn = targetSocket.getInputStream();
			OutputStream targetOut = targetSocket.getOutputStream();

			// Forward the client's request to the target server
			targetOut.write(request.getBytes());
			targetOut.flush();

			// Forward the target server's response to the client
			while ((bytesRead = targetIn.read(buffer)) != -1) {
				clientOut.write(buffer, 0, bytesRead);
				clientOut.flush();
			}

			// Close the sockets
			targetSocket.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String extractTargetUrl(String request) {
		String[] lines = request.split("\\r\\n");
		String[] requestLine = lines[0].split(" ");
        // Use regex to remove "http://"
        String cleanedUrl = requestLine[1].replaceFirst("^http://", "");
		return cleanedUrl;
	}

}
