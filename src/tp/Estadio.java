package tp;

public class Estadio extends Sede {

//----------------------Datos--------------------------------

	private String sector;

//--------------------Constructor----------------------------

	public Estadio(String nombre, String direccion, int capacidadMaxima) {
		super(nombre, direccion, capacidadMaxima);
		this.sector = "Campo";
	}

//--------------------Operaciones----------------------------

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("\nTipo: Estadio\n");
		stringBuilder.append(super.toStringSede());
		stringBuilder.append("\nSector: ");
		stringBuilder.append(sector).append("\n");

		return stringBuilder.toString();
	}

	@Override
	public String[] getSectores() {
		String[] s = { sector };
		return s;
	}

	public String darUbicacion() {
		return "CAMPO";
	}
}
