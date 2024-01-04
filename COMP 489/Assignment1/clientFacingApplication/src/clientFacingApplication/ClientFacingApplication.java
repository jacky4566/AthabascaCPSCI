package clientFacingApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.Proxy;
import java.util.Properties;
import java.util.Scanner;

public class ClientFacingApplication {

	private final static String PROXPORT = "8080";
	private final static String PROXADDR = "localhost";

	public static void main(String[] args) {
		System.out.println(
				"This application takes an HTTP request in the form of a URL and sends it to a proxy server. Reponse is displayed on the terminal");
		System.out.println("Proxy Address: \t" + PROXADDR);
		System.out.println("Proxy Port: \t" + PROXPORT);

		// Start a new scanner
		Scanner scanner = new Scanner(System.in);
		
		//Setup System Proxy conditions
		System.setProperty("http.proxyHost", PROXADDR);
		System.setProperty("http.proxyPort", PROXPORT);
		System.setProperty("https.proxyHost", PROXADDR);
		System.setProperty("https.proxyPort", PROXPORT);

		while (true) {
			// Get the target URL from the user
			System.out.println("Enter the target URL (e.g., http://www.google.com) ");
			System.out.println("or type 'exit' to quit: ");
			String path = scanner.nextLine();

			if ("exit".equalsIgnoreCase(path)) {
				break;
			}

			if (path.toLowerCase().startsWith("http")) {
				try {
					// Setup Proxy
					URL server = new URL(path);
					HttpURLConnection connection = (HttpURLConnection) server.openConnection();

					// Open connection
					connection.connect();

					// Get response code
					int responseCode = connection.getResponseCode();
					System.out.println("Response Code: " + responseCode);

					// Read and display response data
					try (BufferedReader reader = new BufferedReader(
							new InputStreamReader(connection.getInputStream()))) {
						String line;
						StringBuilder response = new StringBuilder();

						while ((line = reader.readLine()) != null) {
							response.append(line);
						}

						System.out.println("Response Data:");
						System.out.println(response.toString());
					}

					// Clean up resources
					connection.disconnect();
				} catch (Exception e) {
					System.out.println("Something Went Wrong");
					e.printStackTrace();
				}
			} else {
				System.out.println("Only HTTP is supported");
			}
		}
		// Cleanup and close
		scanner.close();
		System.out.println("Exit");

	}
}
