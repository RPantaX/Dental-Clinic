package Modelo;

public class Trabajador {
	private int idTrabajador;
	private String nom;
	private String primerApe;
	private String segundoApe;
	private String fechaNac;
	private TipoPuesto tipoPuesto;
	private String email;
	private int celular;
	private String fechaIngreso;
	private float sueldo;
	private String username;
	private String contrasena;

	public Trabajador() {
	}

	public Trabajador(int idTrabajador, String nom, String primerApe, String segundoApe,String fechaNac ,TipoPuesto tipoPuesto,
			String email, int celular, String fechaIngreso, float sueldo, String username, String contrasena) {
		this.idTrabajador = idTrabajador;
		this.nom = nom;
		this.primerApe = primerApe;
		this.segundoApe = segundoApe;
		this.fechaNac=fechaNac;
		this.tipoPuesto = tipoPuesto;
		this.email = email;
		this.celular = celular;
		this.fechaIngreso = fechaIngreso;
		this.sueldo = sueldo;
		this.username = username;
		this.contrasena = contrasena;
	}

	public Trabajador(String nom, String primerApe, String segundoApe,String fechaNac ,TipoPuesto tipoPuesto, String email, int celular,
			String fechaIngreso, float sueldo, String username, String contrasena) {
		this.nom = nom;
		this.primerApe = primerApe;
		this.segundoApe = segundoApe;
		this.fechaNac=fechaNac;
		this.tipoPuesto = tipoPuesto;
		this.email = email;
		this.celular = celular;
		this.fechaIngreso = fechaIngreso;
		this.sueldo = sueldo;
		this.username = username;
		this.contrasena = contrasena;
	}

	public int getIdTrabajador() {
		return idTrabajador;
	}

	public void setIdTrabajador(int idTrabajador) {
		this.idTrabajador = idTrabajador;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrimerApe() {
		return primerApe;
	}

	public void setPrimerApe(String primerApe) {
		this.primerApe = primerApe;
	}

	public String getSegundoApe() {
		return segundoApe;
	}

	public void setSegundoApe(String segundoApe) {
		this.segundoApe = segundoApe;
	}
	
	public String getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(String fechaNac) {
		this.fechaNac=fechaNac;
	}

	public TipoPuesto getTipoPuesto() {
		return tipoPuesto;
	}

	public void setTipoPuesto(TipoPuesto tipoPuesto) {
		this.tipoPuesto = tipoPuesto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCelular() {
		return celular;
	}

	public void setCelular(int celular) {
		this.celular = celular;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public float getSueldo() {
		return sueldo;
	}

	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
}
