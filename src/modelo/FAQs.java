package modelo;

public class FAQs {

	String categoria;
	String respuesta;
	String idioma;
	
	public FAQs(String idioma, String categoria, String respuesta) {
		this.categoria = categoria;
		this.respuesta = respuesta;
		this.idioma = idioma;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
}
