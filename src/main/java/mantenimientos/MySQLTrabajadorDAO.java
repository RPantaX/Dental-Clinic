package mantenimientos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import Modelo.*;
import dao.TrabajadoresDAO;
import conexionBD.MySQLConexion;

public class MySQLTrabajadorDAO implements TrabajadoresDAO {

	public List<Trabajador> listar() {
		// TODO Auto-generated method stub
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Trabajador> trabajadores = new ArrayList<>();

		try {
			cn = MySQLConexion.getConexion();// generamos la conexión con la BD
			String sql = "SELECT ID_TRABAJADOR" + " ,NOM" + "	,PRIMER_APE" + " ,SEGUNDO_APE, FECHA_NACIMIENTO" + " ,ID_PUESTO"
					+ "  ,CASE ID_PUESTO" + "		WHEN 1 THEN 'Odontólogo(a)' WHEN 2 THEN 'Odontólogo(a) Preventiva' WHEN 3 THEN 'Odontólogo(a) Infantil'" + "     ELSE 'Recepcionista'"
					+ "	  END AS PUESTO" + "  ,EMAIL" + "  ,CELULAR" + "  ,FECHA_INGRESO" + "  ,SUELDO" + "  ,USERNAME"
					+ "  ,CONTRASENA " + "FROM TRABAJADOR";
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Trabajador trabajador = new Trabajador();
				trabajador.setIdTrabajador(rs.getInt(1));
				trabajador.setNom(rs.getString(2));
				trabajador.setPrimerApe(rs.getString(3));
				trabajador.setSegundoApe(rs.getString(4));
				trabajador.setFechaNac(rs.getString(5));
				// Llenamos la clase de tipoPuesto
				TipoPuesto tipoPuesto = new TipoPuesto();
				tipoPuesto.setIdPuesto(rs.getInt(6));
				tipoPuesto.setDesPuesto(rs.getString(7));

				trabajador.setTipoPuesto(tipoPuesto);
				// SEGUIMOS CON TRABAJADOR
				trabajador.setEmail(rs.getString(8));
				trabajador.setCelular(rs.getInt(9));
				trabajador.setFechaIngreso(rs.getString(10));
				trabajador.setSueldo(rs.getFloat(11));
				trabajador.setUsername(rs.getString(12));
				trabajador.setContrasena(rs.getString(13));

				trabajadores.add(trabajador);
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
		return trabajadores;
	}

	@Override
	public Trabajador dameTrabajador(int codigo) {

		ResultSet rs = null;
		Connection cn = null;
		PreparedStatement ps = null;
		Trabajador trabajador = new Trabajador();

		try {
			cn = MySQLConexion.getConexion();
			String sql = "SELECT ID_TRABAJADOR" + " ,NOM" + "	,PRIMER_APE" + " ,SEGUNDO_APE, FECHA_NACIMIENTO" + " ,ID_PUESTO"
					+ "  ,CASE ID_PUESTO" + "WHEN 1 THEN 'Odontólogo(a) Estético' WHEN 2 THEN 'Odontólogo(a) Preventiva' WHEN 3 THEN 'Odontólogo(a) Infantil'" + "ELSE 'Recepcionista'"
					+ "	  END AS PUESTO" + "  ,EMAIL" + "  ,CELULAR" + "  ,FECHA_INGRESO" + "  ,SUELDO" + "  ,USERNAME"
					+ "  ,CONTRASENA " + "FROM TRABAJADOR WHERE ID_TRABAJADOR=?";
			ps = cn.prepareStatement(sql);
			ps.setInt(1, codigo);
			// ejecucion
			rs = ps.executeQuery();

			while (rs.next()) {
				trabajador.setIdTrabajador(rs.getInt(1));
				trabajador.setNom(rs.getString(2));
				trabajador.setPrimerApe(rs.getString(3));
				trabajador.setSegundoApe(rs.getString(4));
				trabajador.setFechaNac(rs.getString(5));
				// Llenamos la clase de tipoPuesto
				TipoPuesto tipoPuesto = new TipoPuesto();
				tipoPuesto.setIdPuesto(rs.getInt(6));
				tipoPuesto.setDesPuesto(rs.getString(7));
				trabajador.setTipoPuesto(tipoPuesto);
				// SEGUIMOS CON TRABAJADOR
				trabajador.setEmail(rs.getString(8));
				trabajador.setCelular(rs.getInt(9));
				trabajador.setFechaIngreso(rs.getString(10));
				trabajador.setSueldo(rs.getFloat(11));
				trabajador.setUsername(rs.getString(12));
				trabajador.setContrasena(rs.getString(13));

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
		return trabajador;
	}

	@Override
	public int registrar(Trabajador trabajador) {
		// TODO Auto-generated method stub
		int resultado = 0;
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = MySQLConexion.getConexion();
			String sql = "INSERT INTO TRABAJADOR VALUES(null,?,?,?,?,?,?,?,?,?,?,?)";
			ps = cn.prepareStatement(sql);
			// Parametrizar el PreparedStatement
			ps.setString(1, trabajador.getNom());
			ps.setString(2, trabajador.getPrimerApe());
			ps.setString(3, trabajador.getSegundoApe());
			ps.setString(4, trabajador.getFechaNac());
			ps.setInt(5, trabajador.getTipoPuesto().getIdPuesto());
			ps.setString(6, trabajador.getEmail());
			ps.setInt(7, trabajador.getCelular());
			ps.setString(8, trabajador.getFechaIngreso());
			ps.setFloat(9, trabajador.getSueldo());
			ps.setString(10, trabajador.getUsername());
			ps.setString(11, trabajador.getContrasena());

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
	public int actualizar(Trabajador trabajador) {
		// TODO Auto-generated method stub
		int resultado = 0;
		Connection cn = null;
		PreparedStatement ps = null;

		try {
			cn = MySQLConexion.getConexion();
			String sql = "UPDATE TRABAJADOR SET NOM=?,PRIMER_APE=?,SEGUNDO_APE=?, FECHA_NACIMIENTO=?,ID_PUESTO=?,EMAIL=?,CELULAR=?,FECHA_INGRESO=?,SUELDO=?,USERNAME=?,CONTRASENA=? WHERE ID_TRABAJADOR=?";
			ps = cn.prepareStatement(sql);
			// Parametrizar el PreparedStatement
			ps.setString(1, trabajador.getNom());
			ps.setString(2, trabajador.getPrimerApe());
			ps.setString(3, trabajador.getSegundoApe());
			ps.setString(4, trabajador.getFechaNac());
			ps.setInt(5, trabajador.getTipoPuesto().getIdPuesto());
			ps.setString(6, trabajador.getEmail());
			ps.setInt(7, trabajador.getCelular());
			ps.setString(8, trabajador.getFechaIngreso());
			ps.setFloat(9, trabajador.getSueldo());
			ps.setString(10, trabajador.getUsername());
			ps.setString(11, trabajador.getContrasena());
			ps.setInt(12, trabajador.getIdTrabajador());

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
			String sql = "DELETE FROM TRABAJADOR WHERE ID_TRABAJADOR=?";
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

	public Trabajador validarTrabajador(String usuario, String contrasena) {

		// Creando los objetos
		Trabajador trabajador = null;
		Connection con = null;
		CallableStatement cst = null;
		ResultSet rs = null;

		try {
			con = MySQLConexion.getConexion();
			String sql = " call validaAcceso(?,?) ";
			cst = con.prepareCall(sql);

			// Parametrizar
			cst.setString(1, usuario);
			cst.setString(2, contrasena);

			// Ejecución
			rs = cst.executeQuery();
			while (rs.next()) {
				trabajador= new Trabajador();
				trabajador.setIdTrabajador(rs.getInt(1));
				trabajador.setNom(rs.getString(2));
				trabajador.setPrimerApe(rs.getString(3));
				trabajador.setSegundoApe(rs.getString(4));
				trabajador.setFechaNac(rs.getString(5));
				// Llenamos la clase de tipoPuesto
				TipoPuesto tipoPuesto = new TipoPuesto();
				tipoPuesto.setIdPuesto(rs.getInt(6));
				tipoPuesto.setDesPuesto(rs.getString(7));
				trabajador.setTipoPuesto(tipoPuesto);
				// SEGUIMOS CON TRABAJADOR
				trabajador.setEmail(rs.getString(8));
				trabajador.setCelular(rs.getInt(9));
				trabajador.setFechaIngreso(rs.getString(10));
				trabajador.setSueldo(rs.getFloat(11));
				trabajador.setUsername(rs.getString(121));
				trabajador.setContrasena(rs.getString(13));
			}

		} catch (Exception e) {
			System.out.println("Error en la sentencia " + e.getMessage());
		} finally {

			try {
				if (cst != null)
					cst.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar " + e2.getMessage());
			}
		}

		return trabajador;
	}

}
