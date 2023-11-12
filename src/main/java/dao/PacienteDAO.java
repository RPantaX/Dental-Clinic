package dao;

import java.util.List;

import Modelo.Paciente;

public interface PacienteDAO {
	public List<Paciente> listar();

	public Paciente damePaciente(int codigo);

	public int registrar(Paciente paciente);

	public int actualizar(Paciente paciente);

	public int eliminar(int codigo);
	
}
