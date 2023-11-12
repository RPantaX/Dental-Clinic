package Modelo;

public class Cita{
	
		private int idCita;
		private int idReserva;
		private String fecha;
		private String hora;
		private String nombresPaciente;
		private String nombresTrabajador;
		private String puesto;
		private int sala;
		public Cita(){
			
		}
		public Cita(int idCita, int idReserva, String fecha, String hora, String nombresPaciente,
				String nombresTrabajador,String puesto, int sala) {
			this.idCita = idCita;
			this.idReserva = idReserva;
			this.fecha = fecha;
			this.hora = hora;
			this.nombresPaciente = nombresPaciente;
			this.nombresTrabajador = nombresTrabajador;
			this.puesto=puesto;
			this.sala = sala;
		}
		public int getIdCita() {
			return idCita;
		}
		public void setIdCita(int idCita) {
			this.idCita = idCita;
		}
		public int getIdReserva() {
			return idReserva;
		}
		public void setIdReserva(int idReserva) {
			this.idReserva = idReserva;
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
		public String getNombresPaciente() {
			return nombresPaciente;
		}
		public void setNombresPaciente(String nombresPaciente) {
			this.nombresPaciente = nombresPaciente;
		}
		public String getNombresTrabajador() {
			return nombresTrabajador;
		}
		public void setNombresTrabajador(String nombresTrabajador) {
			this.nombresTrabajador = nombresTrabajador;
		}
		public String getPuesto() {
			return puesto;
		}
		public void setPuesto(String puesto) {
			this.puesto=puesto;
		}
		public int getSala() {
			return sala;
		}
		public void setSala(int sala) {
			this.sala = sala;
		}
		
		
}
