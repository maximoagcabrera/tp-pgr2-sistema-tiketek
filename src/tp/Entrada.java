package tp;

public class Entrada implements IEntrada {

//----------------------Datos--------------------------------

	private String codigo;
	private String nombreEspectaculo;
	private Fecha fecha;
	private String sector;
	private double costo;
	private Funcion funcion;

	// Datos sede numerada
	private Integer asiento;

//--------------------Constructor----------------------------

	public Entrada() {
		this.codigo = null;
		this.nombreEspectaculo = null;
		this.fecha = null;
		this.sector = null;
		this.costo = 0;
		this.funcion = null;
	}

	// Entrada de una función en un estadio
	public Entrada(String codigo, String nombreEspectaculo, Fecha fecha, String sector, double costo, Funcion funcion) {
		this.codigo = codigo;
		this.nombreEspectaculo = nombreEspectaculo;
		this.fecha = fecha;
		this.sector = sector;
		this.funcion = funcion;
		this.costo = funcion.darCostoEntrada();

	}

	// Entrada para sede numerada
	public Entrada(String codigo, String nombreEspectaculo, Fecha fecha, String sector, double costo, Funcion funcion,
			int asiento) {
		this.codigo = codigo;
		this.nombreEspectaculo = nombreEspectaculo;
		this.fecha = fecha;
		this.sector = sector;
		this.funcion = funcion;
		this.costo = funcion.darCostoEntrada();
		this.setSector(sector);
		this.setAsiento(asiento);
	}

//--------------------Operaciones----------------------------	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombreEspectaculo() {
		return nombreEspectaculo;
	}

	public void setNombreEspectaculo(String nombreEspectaculo) {
		this.nombreEspectaculo = nombreEspectaculo;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public Funcion getFuncion() {
		return funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	public Integer getAsiento() {
		return asiento;
	}

	public void setAsiento(Integer asiento) {
		this.asiento = asiento;
	}

	@Override
	public double precio() {
		return costo;
	}

	@Override
	public String ubicacion() {
		return this.sector;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();

		string.append("ENTRADA: ");
		string.append(this.codigo);
		string.append(" - ");
		string.append(this.nombreEspectaculo);
		string.append(" - ");
		string.append(this.fecha.toString());

		if (this.fecha.fechaPasada()) {
			string.append(" P");
		}

		string.append(" - ");
		string.append(this.funcion.getSede().getNombre());
		string.append(" - ");
		string.append(sector);

		if (funcion.getSede() instanceof Teatro || funcion.getSede() instanceof MiniEstadio) {
			string.append(funcion.obtenerUbicacionDesdeAsiento(sector, asiento));
		}

		return string.toString();
	}

}
