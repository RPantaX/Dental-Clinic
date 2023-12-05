package dao;

import java.util.List;

import Modelo.Trabajador;

public interface TrabajadoresDAO {

	public List<Trabajador> listar();

	public Trabajador dameTrabajador(int codigo);

	public int registrar(Trabajador trabajador);

	public int actualizar(Trabajador trabajador);

	public int eliminar(int codigo);
	public Trabajador validarTrabajador(String usuario, String contrasena);

	public void generarPDF();

}

