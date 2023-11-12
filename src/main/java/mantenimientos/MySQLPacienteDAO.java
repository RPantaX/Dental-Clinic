package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Paciente;

import conexionBD.MySQLConexion;
import dao.PacienteDAO;

public class MySQLPacienteDAO implements PacienteDAO{

	@Override
	public List<Paciente> listar() {
		// TODO Auto-generated method stub
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Paciente> lista = new ArrayList<>();

		try {
			cn = MySQLConexion.getConexion();// generamos la conexión con la BD
			String sql = "SELECT *FROM PACIENTE";
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setIdPac(rs.getInt(1));
				paciente.setNombrePac(rs.getString(2));
				paciente.setPrimerApePac(rs.getString(3));
				paciente.setSegundoApePac(rs.getString(4));
				paciente.setEmailPac(rs.getString(5));
				paciente.setCelularPac(rs.getInt(6));
				paciente.setFechaRegPac(rs.getString(7));
				lista.add(paciente);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en la sentencia" + e.getMessage());
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error al cerrar conexiones " + e.getMessage());
			}
		}
		return lista;
	}

	@Override
	public Paciente damePaciente(int codigo) {
		ResultSet rs = null;
		Connection cn = null;
		PreparedStatement ps = null;
		Paciente paciente = new Paciente();

		try {
			cn = MySQLConexion.getConexion();
			String sql = "SELECT *FROM PACIENTE WHERE ID_PACIENTE=?";
			ps = cn.prepareStatement(sql);
			ps.setInt(1, codigo);
			// ejecucion
			rs = ps.executeQuery();

			while (rs.next()) {
				
				paciente.setIdPac(rs.getInt(1));
				paciente.setNombrePac(rs.getString(2));
				paciente.setPrimerApePac(rs.getString(3));
				paciente.setSegundoApePac(rs.getString(4));
				paciente.setEmailPac(rs.getString(5));
				paciente.setCelularPac(rs.getInt(6));
				paciente.setFechaRegPac(rs.getString(7));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en la sentencia " + e.getMessage());
		} finally {

			try {

				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error al cerrar conexiones " + e.getMessage());
			}
		}
		return paciente;
	}

	@Override
	public int registrar(Paciente paciente) {
		// TODO Auto-generated method stub
		int resultado = 0;
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = MySQLConexion.getConexion();
			String sql = "INSERT INTO PACIENTE VALUES(null,?,?,?,?,?,?)";
			ps = cn.prepareStatement(sql);
			// Parametrizar el PreparedStatement
			ps.setInt(1, paciente.getIdPac());
			ps.setString(2, paciente.getNombrePac());
			ps.setString(3, paciente.getPrimerApePac());
			ps.setString(4, paciente.getSegundoApePac());
			ps.setString(5, paciente.getEmailPac());
			ps.setInt(6, paciente.getCelularPac());
			ps.setString(7, paciente.getFechaRegPac());
			resultado = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en la sentencia " + e.getMessage());
		} finally {

			try {
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar " + e.getMessage());
			}
		}
		return resultado;
	}

	@Override
	public int actualizar(Paciente paciente) {
		// TODO Auto-generated method stub
		int resultado = 0;
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = MySQLConexion.getConexion();
			String sql = "UPDATE PACIENTE SET NOMBRE=?,PRIMER_APE=?,SEGUNDO_APE=?, EMAIL=?,CELULAR=?,FECHA_REGISTRO=? WHERE ID_PACIENTE=?";
			ps = cn.prepareStatement(sql);
			// Parametrizar el PreparedStatement
			ps.setString(1, paciente.getNombrePac());
			ps.setString(2, paciente.getPrimerApePac());
			ps.setString(3, paciente.getSegundoApePac());
			ps.setString(4, paciente.getEmailPac());
			ps.setInt(5, paciente.getCelularPac());
			ps.setString(6, paciente.getFechaRegPac());
			ps.setInt(7, paciente.getIdPac());

			resultado = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en la sentencia " + e.getMessage());
		} finally {

			try {
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar " + e.getMessage());
			}
		}
		return resultado;
	}

	@Override
	public int eliminar(int codigo) {
		// TODO Auto-generated method stub
		int resultado = 0;
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = MySQLConexion.getConexion();
			String sql = "DELETE FROM PACIENTE WHERE ID_PACIENTE=?";
			ps = cn.prepareStatement(sql);
			// Parametrizar el PreparedStatement
			ps.setInt(1, codigo);
			resultado = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en la sentencia " + e.getMessage());
		} finally {

			try {
				if (ps != null)
					ps.close();
				if (cn != null)
					cn.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar " + e.getMessage());
			}
		}
		return resultado;
	}

}
