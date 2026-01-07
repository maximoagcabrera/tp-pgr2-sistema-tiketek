package tp;

import java.time.LocalDate;

public class Fecha {

	private int dia;
	private int mes;
	private int anio;

	private static final int[] DIAS_POR_MES = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public Fecha(int dia, int mes, int anio) {

		if (!esFechaValida(dia, mes, anio))
			throw new IllegalArgumentException("Fecha inválida: " + dia + "/" + mes + "/" + anio);

		this.setDia(dia);
		this.setMes(mes);
		this.setAnio(anio);

	}

	public static boolean esFechaValida(int dia, int mes, int anio) {
		if (mes < 1 || mes > 12)
			return false;
		int diasMax = DIAS_POR_MES[mes - 1];
		if (mes == 2 && esBisiesto(anio))
			diasMax = 29;
		return dia >= 1 && dia <= diasMax;
	}

	public static boolean esBisiesto(int anio) {
		return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
	}

	public int getDia() {

		return dia;
	}

	public void setDia(int dia) {

		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		if (mes < 13 && mes > 0)
			this.mes = mes;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	// Compara una fecha con la fecha de hoy y devuelve true si la fecha ya paso
	public boolean fechaPasada() {
		if (this.anio < LocalDate.now().getYear()
				|| (this.anio == LocalDate.now().getYear() && this.mes < LocalDate.now().getMonthValue())
				|| (this.anio == LocalDate.now().getYear() && this.mes == LocalDate.now().getMonthValue()
						&& this.dia < LocalDate.now().getDayOfMonth()))
			return true;
		return false;
	}

	// Transforma un string en un Objeto Fecha
	public static Fecha desdeString(String fechaStr) {
		try {
			String[] partes = fechaStr.split("/");
			int dia = Integer.parseInt(partes[0]);
			int mes = Integer.parseInt(partes[1]);
			int anio = 2000 + Integer.parseInt(partes[2]);
			return new Fecha(dia, mes, anio);
		} catch (Exception e) {
			System.out.println("Formato de fecha inválido: " + fechaStr);
			return null;
		}
	}

	public String toString() {
		StringBuilder string = new StringBuilder();
		if (dia < 10)
			string.append("0");
		string.append(dia);
		string.append("/");
		if (mes < 10)
			string.append("0");
		string.append(mes);
		string.append("/");
		string.append(anio - 2000);
		return string.toString();

	}

}
