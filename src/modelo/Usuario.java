package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Usuario {
	
	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String POST_URL = "http://localhost/gestionhotelera/sw_user.php";
	
	public Usuario login(String username, String password) {
		String action = "login";
		String user = "{\"email\":\"j@j.com\", \"password\":\"1234\"}";
		try {
			sendPOST();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Usuario();
	}
	
	private static void sendPOST() throws IOException {
		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request did not work.");
		}

	}

}
