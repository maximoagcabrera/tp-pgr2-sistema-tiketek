package tp;

public class MiniEstadio extends Sede {

	// ----------------------Datos--------------------------------

	private int asientosPorFila;
	private double precioConsumicion;
	private int cantidadPuestos;

	// Las 3 listas deben tener la misma longitud y reprensetar correctamente su
	// sector
	private String[] sectores;
	private int[] capacidad;
	private int[] porcentajeAdicional;

	// --------------------Constructor----------------------------

	public MiniEstadio(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, int cantidadPuestos,
			double precioConsumicion, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {

		super(nombre, direccion, capacidadMaxima);

		this.asientosPorFila = asientosPorFila;
		this.precioConsumicion = precioConsumicion;
		this.cantidadPuestos = cantidadPuestos;
		this.sectores = sectores;
		this.capacidad = capacidad;
		this.porcentajeAdicional = porcentajeAdicional;

		capacidadCorrecta();
	}

	// --------------------Operaciones----------------------------

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("\nTipo: MiniEstadio\n");
		stringBuilder.append(super.toStringSede());
		stringBuilder.append("\nAsientos por fila: ");
		stringBuilder.append(asientosPorFila);
		stringBuilder.append("\nPrecio de consumicion general: ");
		stringBuilder.append(precioConsumicion);
		stringBuilder.append("\nCantidad de puestos: ");
		stringBuilder.append(cantidadPuestos);

		for (int i = 0; i < sectores.length; i++) {
			stringBuilder.append("\nSector: ");
			stringBuilder.append(sectores[i]);
			stringBuilder.append("- Capacidad: ");
			stringBuilder.append(capacidad[i]);
			stringBuilder.append("- Porcentaje adicional: ");
			stringBuilder.append(porcentajeAdicional[i]).append("\n");
		}
		return stringBuilder.toString();
	}

	public void capacidadCorrecta() {
		int total = 0;
		for (int i = 0; i < sectores.length; i++) {
			total += capacidad[i];
		}
		if (capacidadMaxima < total) {
			throw new RuntimeException("Error: Capacidad de sectores mayor a capacidad máxima de la sede");
		}
	}

	public int getAsientosPorFila() {
		return asientosPorFila;
	}

	public void setAsientosPorFila(int asientosPorFila) {
		this.asientosPorFila = asientosPorFila;
	}

	public double getPrecioConsumicion() {
		return precioConsumicion;
	}

	public void setPrecioConsumicion(double precioConsumicion) {
		this.precioConsumicion = precioConsumicion;
	}

	public int getCantidadPuestos() {
		return cantidadPuestos;
	}

	public void setCantidadPuestos(int cantidadPuestos) {
		this.cantidadPuestos = cantidadPuestos;
	}

	@Override
	public String[] getSectores() {
		return sectores;
	}

	public void setSectores(String[] sectores) {
		this.sectores = sectores;
	}

	public int[] getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int[] capacidad) {
		this.capacidad = capacidad;
	}

	public int[] getPorcentajeAdicional() {
		return porcentajeAdicional;
	}

	public void setPorcentajeAdicional(int[] porcentajeAdicional) {
		this.porcentajeAdicional = porcentajeAdicional;
	}

	@Override
	public int getCapacidadMaxima(int numeroSector) {
		return capacidad[numeroSector];
	}

}