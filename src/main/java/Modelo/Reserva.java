package Modelo;
public class Reserva {
	
	private int id_reserva;
	private int id_paciente;
	private String fecha;
	private String hora;
	private TipoEstado estado;
	private double precio;
	
	public Reserva() {
		
	}
	
	public Reserva(int id_reserva, int id_paciente, String fecha, String hora, TipoEstado estado, double precio) {
		
		this.id_reserva = id_reserva;
		this.id_paciente = id_paciente;
		this.fecha = fecha;
		this.hora = hora;
		this.estado = estado;
		this.precio = precio;
	}
	
	public Reserva(int id_paciente, String fecha, String hora, TipoEstado estado, double precio) {
		
		this.id_paciente = id_paciente;
		this.fecha = fecha;
		this.hora = hora;
		this.estado = estado;
		this.precio = precio;
	}
	
	public int getId_reserva() {
		return id_reserva;
	}

	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}

	public int getId_paciente() {
		return id_paciente;
	}

	public void setId_paciente(int id_paciente) {
		this.id_paciente = id_paciente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public TipoEstado getEstado() {
		return estado;
	}

	public void setEstado(TipoEstado estado) {
		this.estado = estado;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}