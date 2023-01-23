package modelo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import utils.ParameterStringBuilder;

public class Usuario {
	
	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String POST_URL = "http://localhost/gestionhotelera/sw_user.php";
	
	static String action = "login";
	static String user = "{\"email\":\"j@j.com\", \"password\":\"1234\"}";
	
	public Usuario login(String username, String password) {
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
		
		enviarParametros(con);
		
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
			System.out.println(leerJSON(response.toString()));
		} else {
			System.out.println("POST request did not work.");
		}
	}
	
	@SuppressWarnings("deprecation")
	private static String leerJSON(String response) {
		JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
		String success = jsonObject.get("success").getAsString();
		return success;
	}
	
	private static void enviarParametros(HttpURLConnection con) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put("action", action);
		parameters.put("user", user);

		con.setDoOutput(true);
		try {
			DataOutputStream out = new DataOutputStream(con.getOutputStream());
			out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.getStackTrace();
		}		
	}

}
