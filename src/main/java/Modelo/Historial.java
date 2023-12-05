package Modelo;

public class Historial {
	private int idHist;
	private int idPac;
	private String desc;
	private String fecha;
	private int idTrabajador;
	private String nombresTrab;
	public Historial() {
	}
	public Historial(int idHist, int idPac, String desc, String fecha,int idTrabajador, String nombresTrab) {
		this.idHist = idHist;
		this.idPac = idPac;
		this.desc = desc;
		this.fecha=fecha;
		this.idTrabajador = idTrabajador;
		this.nombresTrab=nombresTrab;
	}
	public Historial(int idPac, String desc, String fecha,int idTrabajador, String nombresTrab) {
		this.idPac = idPac;
		this.desc = desc;
		this.fecha=fecha;
		this.idTrabajador = idTrabajador;
		this.nombresTrab=nombresTrab;
	}
	public Historial(int idTrabajador, String desc) {
		this.idTrabajador = idTrabajador;
		this.desc = desc;
	}
	public int getIdHist() {
		return idHist;
	}
	public void setIdHist(int idHist) {
		this.idHist = idHist;
	}
	public int getIdPac() {
		return idPac;
	}
	public void setIdPac(int idPac) {
		this.idPac = idPac;
	}

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String hora) {
		this.fecha=hora;
	}
	public int getIdTrabajador() {
		return idTrabajador;
	}
	public void setIdTrabajador(int idTrabajador) {
		this.idTrabajador = idTrabajador;
	}
	public String getNombresTrab() {
		return nombresTrab;
	}
	public void setNombresTrab(String nombresTrab) {
		this.nombresTrab=nombresTrab;
	}
	
}
