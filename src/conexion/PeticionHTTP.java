package conexion;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import modelo.Usuario;

/**
 * Clase encargada de realizar la petición HTTP del login de la aplicación.
 * 
 * @author Tomas
 *
 */
public class PeticionHTTP {
	public static boolean loginPOST(Usuario usuario, String URL) throws IOException {
		URL obj = new URL(URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("POST");

		/**** ENVIAR PARÁMETROS *****/
		Map<String, String> parameters = new HashMap<>();
		String action = "login";
		String user = "{\"email\":\"" + usuario.getEmail() + "\", \"password\":\"" + usuario.getPassword() + "\"}";
		parameters.put("action", action);
		parameters.put("user", user);

		con.setDoOutput(true);
		try {
			DataOutputStream out = new DataOutputStream(con.getOutputStream());
			out.writeBytes(getParamsString(parameters));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
		/*********/

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) {
			/**** OBTENER RESPUESTA ******/
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());

			/***** COMPROBAR ESTADO DEL SUCCESS *******/
			if (leerJSON(response.toString()).equals("true")) {
				Usuario datosUsuario = usuarioDesdeJSON(response.toString());

				/*** ACTUALIZAR DATOS AL USUARIO LOGGEADO ****/
				usuario.setToken(datosUsuario.getToken());
				usuario.setFecha_validez_token(datosUsuario.getFecha_validez_token());
				usuario.setNombre(datosUsuario.getNombre());
				usuario.setApellidos(datosUsuario.getApellidos());
				usuario.setTelefono(datosUsuario.getTelefono());
				usuario.setFecha_baja(datosUsuario.getFecha_baja());
				usuario.setCreated_at(datosUsuario.getCreated_at());
				usuario.setUpdated_at(datosUsuario.getUpdated_at());
				return true;
			} else
				return false;

		} else {
			System.err.println("La petición POST no ha funcionado");
			return false;
		}
	}

	/**
	 * Parsea un json de respuesta utilizando las dependencias Google GSON para
	 * obtener el resultado de uno de los parámetros
	 * 
	 * @param response JSON de entrada
	 * @return success true o false
	 */
	@SuppressWarnings("deprecation")
	private static String leerJSON(String response) {
		try {
			JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
			String success = jsonObject.get("success").getAsString();
			return success;
		} catch (Exception e) {
			System.err.println("Error en leerJSON");
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	private static Usuario usuarioDesdeJSON(String json) {
		try {
			JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
			JsonObject data = jsonObject.get("data").getAsJsonObject();

			Gson gson = new Gson();
			Usuario datosUsuario = gson.fromJson(data, Usuario.class);
			return datosUsuario;
		} catch (Exception e) {
			System.err.println("Error en usuarioDesdeJSON");
			return null;
		}
	}

	private static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			result.append("&");
		}

		String resultString = result.toString();
		return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
	}
}
