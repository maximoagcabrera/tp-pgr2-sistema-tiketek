package tp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Funcion {

	// ----------------------Datos--------------------------------

	private String nombreEspectaculo;
	private String fecha; // en formato: dd/mm/YY
	private Sede sede;
	private double precioBase;
	private List<Entrada> listaEntradasVendidas;
	private int[] entradasVendidasPorSector; // Cuenta cuantas entradas se vendieron en cada sector de la sede

	private Set<Integer> asientos; // Lista que controla qué número de asientos están reservados

	// --------------------Constructor----------------------------

	public Funcion(String nombreEspectaculo, String fecha, Sede sede, double precioBase) {
		this.setNombreEspectaculo(nombreEspectaculo);
		this.setFecha(fecha);
		this.setSede(sede);
		this.setPrecioBase(precioBase);
		listaEntradasVendidas = new ArrayList<>();
		this.entradasVendidasPorSector = new int[sede.getSectores().length];
		this.asientos = new HashSet<>();
	}

	// --------------------Operaciones----------------------------

	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("\n{Funcion: ");
		string.append(fecha);
		string.append(" - ");
		string.append(nombreEspectaculo);
		string.append(" - Sede: ");
		string.append(sede.getNombre());
		string.append("}\n");
		return string.toString();
	}

	public void agregarEntradaVendida(Entrada e) {

		listaEntradasVendidas.add(e);
	}

	public double darCostoEntrada() {
		return getPrecioBase();
	}

	public double darCostoEntrada(String sector) {
		double ret = 0;
		if (sede instanceof Teatro) {
			Teatro teatro = (Teatro) sede;
			String[] sectores = teatro.getSectores();
			for (int i = 0; i < sectores.length; i++) {
				if (sectores[i].equals(sector)) {
					ret = this.precioBase + this.precioBase * teatro.getPorcentajeAdicional()[i] / 100;
				}
			}
		}

		if (sede instanceof MiniEstadio) {
			MiniEstadio miniEstadio = (MiniEstadio) sede;
			String[] sectores = miniEstadio.getSectores();
			for (int i = 0; i < sectores.length; i++) {
				if (sectores[i].equals(sector)) {
					ret = this.precioBase + (this.precioBase * miniEstadio.getPorcentajeAdicional()[i] / 100)
							+ miniEstadio.getPrecioConsumicion();
				}
			}
		}
		return ret;
	}

	// Calcula el total recaudado por la función
	public double recaudado() {
		double total = 0;
		if (this.sede instanceof Estadio) {
			for (int entrada = 0; entrada < listaEntradasVendidas.size(); entrada++) {
				total += darCostoEntrada();
			}
		}

		for (Entrada entrada : listaEntradasVendidas) {
			total += this.darCostoEntrada(entrada.getSector());
		}
		return total;
	}

	public int cantSectores() {
		return sede.getSectores().length;
	}

	// Cuenta cuantas entradas se vendieron en un sector especifico
	public void sumarEntradas(String sector, int cantAsientos) {
		String[] sectores = sede.getSectores();
		for (int i = 0; i < sectores.length; i++) {
			if (sectores[i].equals(sector)) {
				entradasVendidasPorSector[i] += cantAsientos;
			}
		}
	}

	// Crea un string que describe la función
	public String listar() {
		String funcionListada;
		if (cantSectores() == 1) {
			funcionListada = " - " + "(" + fecha + ") " + sede.getNombre() + "-" + entradasVendidasPorSector[0] + "/"
					+ sede.getCapacidadMaxima(0) + "\n";
		} else {
			funcionListada = " - " + "(" + fecha + ") " + sede.getNombre() + " - ";
			for (int i = 0; i < sede.getSectores().length; i++) {
				if (i < sede.getSectores().length - 1) {
					funcionListada += sede.getSectores()[i] + ": " + entradasVendidasPorSector[i] + "/"
							+ sede.getCapacidadMaxima(i) + " | ";
				} else {
					funcionListada += sede.getSectores()[i] + ": " + entradasVendidasPorSector[i] + "/"
							+ sede.getCapacidadMaxima(i) + "\n";
				}
			}
		}
		return funcionListada;
	}

	public String darUbicacion(String sector, int asiento) {
		return obtenerUbicacionDesdeAsiento(sector, asiento);
	}

	// Crea un string con la ubicacion específica de un asiento: fila y asiento
	public String obtenerUbicacionDesdeAsiento(String sector, int asiento) {
		String ret = "";
		if (sede instanceof Teatro) {
			Teatro aux = (Teatro) sede;

			for (int sec = 0; sec < aux.getSectores().length; sec++) {
				if (aux.getSectores()[sec].equals(sector) && asiento > aux.getCapacidad()[sec]) {
					throw new RuntimeException("Número de asiento inválido");
				}
			}

			int fila = (asiento / aux.getAsientosPorFila()) + 1;
			int lugar = (asiento % aux.getAsientosPorFila());
			ret = " f:" + fila + " a:" + lugar;
		}

		if (sede instanceof MiniEstadio) {
			MiniEstadio aux = (MiniEstadio) sede;

			for (int sec = 0; sec < aux.getSectores().length; sec++) {
				if (aux.getSectores()[sec].equals(sector) && asiento > aux.getCapacidad()[sec]) {
					throw new RuntimeException("Número de asiento inválido");
				}
			}

			int fila = (asiento / aux.getAsientosPorFila()) + 1;
			int lugar = (asiento % aux.getAsientosPorFila());
			ret = " f:" + fila + " a:" + lugar;
		}
		return ret;
	}

	public boolean asignarLugares(int cantidad) {
		if (cantidad + listaEntradasVendidas.size() <= sede.capacidadMaxima) {
			return true;
		}
		return false;
	}

	// Confirma que no estén ocupados los asientos para cuando quieran comprar la
	// entrada
	public boolean asignarAsientos(String sector, int[] asientosSolicitados) {
		int[] asientosEnSede = new int[asientosSolicitados.length];
		for (int asiento = 0; asiento < asientosEnSede.length; asiento++) {
			asientosEnSede[asiento] = numeroAsientoEnSede(sector, asientosSolicitados[asiento]);
		}

		boolean ret = true;
		for (int asiento = 0; asiento < asientosEnSede.length; asiento++) {
			ret = ret && !asientos.contains(asientosEnSede[asiento]);
		}
		if (ret) {
			for (int asiento = 0; asiento < asientosEnSede.length; asiento++) {
				asientos.add(asientosEnSede[asiento]);
			}
		}
		return ret;
	}

	// Convierte el número de un asiento de un sector en el número de asiento de la
	// sede en general
	// para agregarlo a la lista de asientos ocupados
	public int numeroAsientoEnSede(String sector, int asiento) {
		if (sede instanceof Teatro) {
			Teatro aux = (Teatro) sede;

			int actual = 0;
			for (int i = 0; i < aux.getSectores().length; i++) {
				if (aux.getSectores()[i].equals(sector) && asiento <= aux.getCapacidadMaxima(i)) {
					return actual + asiento;
				}
				actual += aux.getCapacidad()[i];
			}
		}
		if (sede instanceof MiniEstadio) {
			MiniEstadio aux = (MiniEstadio) sede;

			int actual = 0;
			for (int i = 0; i < aux.getSectores().length; i++) {
				if (aux.getSectores()[i].equals(sector) && asiento <= aux.getCapacidadMaxima(i)) {
					return actual + asiento;
				}
				actual += aux.getCapacidad()[i];
			}
		}
		return 0;
	}

	public int[] getEntradasVendidas() {
		return entradasVendidasPorSector;
	}

	public void setEntradasVendidas(int[] entradasVendidas) {
		this.entradasVendidasPorSector = entradasVendidas;
	}

	public String getNombreEspectaculo() {
		return nombreEspectaculo;
	}

	public void setNombreEspectaculo(String nombreEspectaculo) {
		this.nombreEspectaculo = nombreEspectaculo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public Set<Integer> getAsientos() {
		return asientos;
	}

	public void setAsientos(Set<Integer> asientos) {
		this.asientos = asientos;
	}

	public List<Entrada> getListaEntradasVendidas() {
		return listaEntradasVendidas;
	}

	public void setListaEntradasVendidas(List<Entrada> listaEntradasVendidas) {
		this.listaEntradasVendidas = listaEntradasVendidas;
	}

}
