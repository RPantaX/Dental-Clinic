package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Reserva;
import Modelo.TipoEstado;
import conexionBD.MySQLConexion;
import dao.ReservaDAO;

public class MySQLReservaDAO implements ReservaDAO {

	@Override
	public List<Reserva> listar() {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Reserva> lista = new ArrayList<>();

		try {
			con = MySQLConexion.getConexion();// generamos la conexión con la BD
			String sql = "SELECT ID_RE, ID_PACIENTE, FECHA, HORA, ID_ESTADO,"
					+ "		CASE ID_ESTADO"
					+ "		WHEN 1  THEN 'ACTIVA'"
					+ "        ELSE 'ANULADA'"
					+ "        END AS ESTADO,"
					+ "        PRECIO FROM RESERVA";
			pst = con.prepareStatement(sql);
			rs=pst.executeQuery();
			
			while (rs.next()) {
				Reserva reserva = new Reserva();
				reserva.setId_reserva(rs.getInt(1));
				reserva.setId_paciente(rs.getInt(2));
				reserva.setFecha(rs.getString(3));
				reserva.setHora(rs.getString(4));
				//Llenamos la clase de tipoEstado
				TipoEstado tipoEstado= new TipoEstado();
				tipoEstado.setId_estado(rs.getInt(5));
				tipoEstado.setDes_estado(rs.getString(6));
				//llenamos el estado con el tipoEstado
				reserva.setEstado(tipoEstado);
				//Seguimos con la reserva
				reserva.setPrecio(rs.getDouble(7));
				
				//listando
				lista.add(reserva);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en la sentencia" + e.getMessage());
		} finally {

			try {

				if (rs != null)		rs.close();
				if (pst != null)	pst.close();
				if (con != null)	con.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				System.out.println("Error al cerrar conexiones " + e2.getMessage());
			}
		}
		return lista;	
	}

	
	
	@Override
	public Reserva obtenerReserva(int id_reserva) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Reserva reserva  = new Reserva();

		try {
			con = MySQLConexion.getConexion();// generamos la conexión con la BD
			String sql = "SELECT ID_RE, ID_PACIENTE, FECHA, HORA, ID_ESTADO,"
					+ "		CASE ID_ESTADO"
					+ "		WHEN 1  THEN 'ACTIVA'"
					+ "        ELSE 'ANULADA'"
					+ "        END AS ESTADO,"
					+ "        PRECIO FROM RESERVA WHERE ID_RE=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id_reserva);
			rs=pst.executeQuery();
			
			while (rs.next()) {
				
				reserva.setId_reserva(rs.getInt(1));
				reserva.setId_paciente(rs.getInt(2));
				reserva.setFecha(rs.getString(3));
				reserva.setHora(rs.getString(4));
				//Llenamos la clase de tipoEstado
				TipoEstado tipoEstado= new TipoEstado();
				tipoEstado.setId_estado(rs.getInt(5));
				tipoEstado.setDes_estado(rs.getString(6));
				//llenamos el estado con el tipoEstado
				reserva.setEstado(tipoEstado);
				//Seguimos con la reserva
				reserva.setPrecio(rs.getDouble(7));

				reserva.setEstado(tipoEstado);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en la sentencia" + e.getMessage());
		} finally {

			try {

				if (rs != null)	rs.close();
				if (pst != null)	pst.close();
				if (con != null)	con.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				System.out.println("Error al cerrar conexiones " + e2.getMessage());
			}
		}
		return reserva;

	}

	@Override
	public int registrar(Reserva reserva) {
		int resultado = 0;
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = MySQLConexion.getConexion();
			String sql = "INSERT INTO RESERVA VALUES(null,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			// Parametrizar el PreparedStatement
			pst.setInt(1, reserva.getId_paciente());
			pst.setString(2, reserva.getFecha());
			pst.setString(3, reserva.getHora());
			pst.setInt(4, reserva.getEstado().getId_estado());
			pst.setDouble(5, reserva.getPrecio());
	

			resultado = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en la sentencia " + e.getMessage());
		} finally {

			try {
				if (pst != null)	pst.close();
				if (con != null)	con.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar " + e.getMessage());
			}
		}
		return resultado;
		
		
		
	}

	@Override
	public int eliminar(int id_reserva) {
		int resultado = 0;
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = MySQLConexion.getConexion();
			String sql = "DELETE FROM RESERVA WHERE ID_RE=?";
			pst = con.prepareStatement(sql);
			// Parametrizar el PreparedStatement
			pst.setInt(1, id_reserva);
			resultado = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en la sentencia " + e.getMessage());
		} finally {

			try {
				if (pst != null)	pst.close();
				if (con != null)	con.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar " + e2.getMessage());
			}
		}
		return resultado;
	}



	@Override
	public int actualizar(Reserva reserva) {
		// TODO Auto-generated method stub
		int resultado = 0;
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = MySQLConexion.getConexion();
			String sql = "UPDATE RESERVA SET FECHA=?,HORA=?,ID_ESTADO=?,PRECIO=? WHERE ID_RE=?";
			ps = cn.prepareStatement(sql);
			// Parametrizar el PreparedStatement
			ps = cn.prepareStatement(sql);
			// Parametrizar el PreparedStatement
			ps.setString(1, reserva.getFecha());
			ps.setString(2, reserva.getHora());
			ps.setInt(3, reserva.getEstado().getId_estado());
			ps.setDouble(4, reserva.getPrecio());
			ps.setInt(5, reserva.getId_reserva());

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
