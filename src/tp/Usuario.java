package tp;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Usuario {

	// ----------------------Datos--------------------------------

	private String email;
	private String nombre;
	private String apellido;
	private String contrasenia;
	private Map<String, Entrada> entradas;

	// --------------------Constructor----------------------------

	public Usuario(String email, String nombre, String apellido, String contrasenia) {
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenia = contrasenia;
		entradas = new HashMap<>();

	}

	// --------------------Operaciones----------------------------

	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("\nUsuario: \n");
		string.append(nombre);
		string.append(" ");
		string.append(apellido);
		string.append("\nEmail: ");
		string.append(email);
		string.append("\nContraseña: ");
		string.append(contrasenia).append("\n");

		return string.toString();
	}

	public boolean comprarEntrada(Entrada e) {
		entradas.put(e.getCodigo(), e);
		return true;
	}

	public void eliminarEntrada(IEntrada entrada) {
		// Asegura que la entrada esté en posesión del usuario y la elimina
		Entrada ent = (Entrada) entrada;
		if (entradas.containsValue(ent)) {
			String s = ent.getCodigo();
			entradas.remove(s);
			entrada = null;
		}
	}

	// crea un código para la entrada cuando la compra el usuario
	public String crearCodigoEntrada() {
		String codigo = "";
		for (int i = 0; i < 3; i++) {
			Random random = new Random();
			int numero = random.nextInt(9) + 1; // numeros entre 1 y 9
			codigo += numero;
		}
		return codigo + (entradas.size() + 1);
	}
	// -------------------Getter/Setter---------------------------

	public Map<String, Entrada> getEntradas() {
		return entradas;
	}

	public void setEntradas(Map<String, Entrada> entradas) {
		this.entradas = entradas;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}
