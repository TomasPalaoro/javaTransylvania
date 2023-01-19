package modelo;

/*import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;*/

public class Usuario {
	
	public Usuario login(String username, String password) {
		
		try {
			//TODO
			/*
			URL url = new URL("http://example.com");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			
			Map<String, String> parameters = new HashMap<>();
			parameters.put("param1", "val");

			con.setDoOutput(true);
			DataOutputStream out = new DataOutputStream(con.getOutputStream());
			out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
			out.flush();
			out.close();
			
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);*/
		} catch (Exception e) {
			e.getStackTrace();
		}		
		return new Usuario();
	}

}
