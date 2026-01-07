package tp;

public class Teatro extends Sede {

//----------------------Datos--------------------------------

	private int asientosPorFila;
	// Las 3 listas deben tener la misma longitud y reprensetar correctamente su
	// sector
	private String[] sectores;
	private int[] capacidad;
	private int[] porcentajeAdicional;

//--------------------Constructor----------------------------

	public Teatro(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, String[] sectores,
			int[] capacidad, int[] porcentajeAdicional) {

		super(nombre, direccion, capacidadMaxima);

		this.asientosPorFila = asientosPorFila;
		this.sectores = sectores;
		this.capacidad = capacidad;
		this.porcentajeAdicional = porcentajeAdicional;

		capacidadCorrecta();

	}

//--------------------Operaciones----------------------------

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("\nTipo: Teatro\n");
		stringBuilder.append(super.toStringSede());
		stringBuilder.append("\nAsientos por fila: ");
		stringBuilder.append(asientosPorFila);

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
