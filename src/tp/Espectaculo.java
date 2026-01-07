package tp;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Espectaculo {

//----------------------Datos--------------------------------

	private String nombre;
	private String codigo; // Codigo de 6 cifras con numeros entre 1 y 9 incluyendolos
	private Map<String, Funcion> funciones; // clave: fecha
	private Map<String, Double> recaudadoPorSede;

//--------------------Constructor----------------------------	

	public Espectaculo(String nombre) {
		this.setNombre(nombre);
		codigo = crearCodigo();
		this.funciones = new HashMap<>();
		this.recaudadoPorSede = new HashMap<>();

	}

//--------------------Operaciones----------------------------	

	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("\nEspectaculo: \n");
		string.append(nombre);
		string.append("\nCodigo: \n");
		string.append(codigo);
		string.append("\nFunciones: \n");
		string.append(funciones.values().toString());
		return string.toString();
	}

	public String crearCodigo() {
		codigo = "";
		for (int i = 0; i < 6; i++) {
			Random random = new Random();
			int numero = random.nextInt(9) + 1; // numeros entre 1 y 9
			codigo += numero;
		}
		return codigo;
	}

	public void agregarFuncion(Funcion funcion) {
		funciones.put(funcion.getFecha(), funcion);
	}

	public Funcion darFuncion(String fecha) {
		return funciones.get(fecha);
	}

	// Suma lo recaudado por las funciones
	public double totalRecaudado() {
		double total = 0;
		for (Funcion funcion : funciones.values()) {
			total += funcion.recaudado();
			double actual = recaudadoPorSede.get(funcion.getSede().getNombre());
			this.recaudadoPorSede.put(funcion.getSede().getNombre(), actual + funcion.recaudado());
		}
		return total;
	}

	public double recaudadoPorSede(String nombreSede) {
		double recaudado = recaudadoPorSede.get(nombreSede);
		return recaudado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Map<String, Double> getRecaudadoPorSede() {
		return recaudadoPorSede;
	}

	public void setRecaudadoPorSede(Map<String, Double> recaudadoPorSede) {
		this.recaudadoPorSede = recaudadoPorSede;
	}

	public Map<String, Funcion> getFunciones() {
		return funciones;
	}
}
