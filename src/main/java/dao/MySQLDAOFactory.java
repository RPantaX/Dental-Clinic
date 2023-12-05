package dao;


import mantenimientos.MySQLCitaDAO;
import mantenimientos.MySQLPacienteDAO;
import mantenimientos.MySQLReservaDAO;
import mantenimientos.MySQLTrabajadorDAO;
import mantenimientos.MySQLHistorialDAO;

public class MySQLDAOFactory extends DAOFactory {

	@Override
	public TrabajadoresDAO getTrabajadorDAO() {

		return new MySQLTrabajadorDAO();
	}
	@Override
	public ReservaDAO getReservaDAO() {
		// TODO Auto-generated method stub
		return new MySQLReservaDAO();
	}
	@Override
	public CitaDAO getCitaDAO() {
		// TODO Auto-generated method stub
		return new MySQLCitaDAO();
	}
	@Override
	public PacienteDAO getPacienteDAO() {
		// TODO Auto-generated method stub
		return new MySQLPacienteDAO();
	}
	@Override
	public HistorialDAO getHistorialDAO() {
		// TODO Auto-generated method stub
		return new MySQLHistorialDAO();
	}
}
