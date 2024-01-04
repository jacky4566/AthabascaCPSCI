import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//see https://github.com/stefano-lupo/Java-Proxy-Server/blob/master/src/RequestHandler.java

public class proxyServer {
	private static final int PORT = 8080;
	private static final int EXECNUMTHREADS = 5;

	public static void main(String[] args) {
		// Create a thread pool
		ExecutorService executorService = Executors.newFixedThreadPool(EXECNUMTHREADS);

		// Create a new ServerSocket
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Now serving localhost on " + PORT);

			while (true) {
				// Accept new connections and give them to the thread pool
				Socket clientSocket = serverSocket.accept();
				executorService.submit(() -> handleClientRequest(clientSocket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			executorService.shutdown();
		}
	}

	private static void handleClientRequest(Socket clientSocket) {
		System.out.println("New Request");
		try (clientSocket){
			InputStream clientIn = clientSocket.getInputStream();
			OutputStream clientOut = clientSocket.getOutputStream();

			byte[] buffer = new byte[4096];
			int bytesRead;

			// Read the client's request
			bytesRead = clientIn.read(buffer);
			String request = new String(buffer, 0, bytesRead);

			// Extract the target URL from the request
			String targetUrl = extractTargetUrl(request);

			// Open a connection to the target server
			try (Socket targetSocket = new Socket(targetUrl, 80)) {
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
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String extractTargetUrl(String request) {
		// Extract Target URL from request
		String[] lines = request.split("\\r\\n");
		String[] requestLine = lines[0].split(" ");
		return requestLine[1];
	}
}