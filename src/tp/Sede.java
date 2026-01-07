package tp;

public abstract class Sede {

//----------------------Datos--------------------------------

	protected String nombre;
	protected String direccion;
	protected int capacidadMaxima;

//--------------------Constructor----------------------------

	public Sede(String nombre, String direccion, int capacidadMaxima) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidadMaxima = capacidadMaxima;
	}

//--------------------Operaciones----------------------------

	public String toStringSede() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("Nombre Sede: ");
		stringBuilder.append(nombre);
		stringBuilder.append("\nDireccion: ");
		stringBuilder.append(direccion);
		stringBuilder.append("\nCapacidad máxima: ");
		stringBuilder.append(capacidadMaxima);

		return stringBuilder.toString(); // Convierte a stringBuilder en String
	}

	public String getNombre() {
		return nombre;
	}

	public abstract String[] getSectores();

	public int getCapacidadMaxima(int sector) {
		return capacidadMaxima;
	}

}
