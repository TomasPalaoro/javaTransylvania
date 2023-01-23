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

	private static final String POST_URL = "http://localhost/gestionhotelera/sw_user.php";
	
	public boolean login(String username, String password) {
		try {
			if (sendPOST(username,password)) {
				System.out.println("LOGGEADO CORRECTAMENTE");
				return true;
			}
			else {
				System.out.println("INCORRECTO");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private static boolean sendPOST(String username, String password) throws IOException {
		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("POST");
		
		/****ENVIAR PARÁMETROS*****/
		Map<String, String> parameters = new HashMap<>();
		String action = "login";
		String user = "{\"email\":\""+username+"\", \"password\":\""+password+"\"}";
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
		/*********/
		
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);
		
		if (responseCode == HttpURLConnection.HTTP_OK) {
			/****OBTENER RESPUESTA******/
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());
			
			/*****COMPROBAR ESTADO DEL SUCCESS*******/
			if (leerJSON(response.toString()).equals("true")) return true;
			else return false;
			
		} else {
			System.err.println("La petición POST no ha funcionado");
			return false;
		}
	}
	
	/**
	 * Parsea un json de respuesta utilizando las dependencias Google GSON
	 * para obtener el resultado de uno de los parámetros
	 * @param response JSON de entrada
	 * @return success true o false
	 */
	@SuppressWarnings("deprecation")
	private static String leerJSON(String response) {
		JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
		String success = jsonObject.get("success").getAsString();
		return success;
	}

}
