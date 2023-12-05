package Modelo;

public class Paciente {
	private int idPac;
	private String nombrePac;
	private String primerApePac;
	private String segundoApePac;
	private String emailPac;
	private int celularPac;
	private String fechaRegPac;

	public Paciente() {}

	public Paciente(int idPac, String nombrePac, String primerApePac, String segundoApePac, String emailPac,
			int celularPac, String fechaRegPac) {
		this.idPac = idPac;
		this.nombrePac = nombrePac;
		this.primerApePac = primerApePac;
		this.segundoApePac = segundoApePac;
		this.emailPac = emailPac;
		this.celularPac = celularPac;
		this.fechaRegPac = fechaRegPac;
	}
	public Paciente(String nombrePac, String primerApePac, String segundoApePac, String emailPac,
			int celularPac, String fechaRegPac) {
		this.nombrePac = nombrePac;
		this.primerApePac = primerApePac;
		this.segundoApePac = segundoApePac;
		this.emailPac = emailPac;
		this.celularPac = celularPac;
		this.fechaRegPac = fechaRegPac;
	}
	public int getIdPac() {
		return idPac;
	}
	public void setIdPac(int idPac) {
		this.idPac = idPac;
	}
	public String getNombrePac() {
		return nombrePac;
	}
	public void setNombrePac(String nombrePac) {
		this.nombrePac = nombrePac;
	}
	public String getPrimerApePac() {
		return primerApePac;
	}
	public void setPrimerApePac(String primerApePac) {
		this.primerApePac = primerApePac;
	}
	public String getSegundoApePac() {
		return segundoApePac;
	}
	public void setSegundoApePac(String segundoApePac) {
		this.segundoApePac = segundoApePac;
	}
	public String getEmailPac() {
		return emailPac;
	}
	public void setEmailPac(String emailPac) {
		this.emailPac = emailPac;
	}
	public int getCelularPac() {
		return celularPac;
	}
	public void setCelularPac(int celularPac) {
		this.celularPac = celularPac;
	}
	public String getFechaRegPac() {
		return fechaRegPac;
	}
	public void setFechaRegPac(String fechaRegPac) {
		this.fechaRegPac = fechaRegPac;
	}


}
