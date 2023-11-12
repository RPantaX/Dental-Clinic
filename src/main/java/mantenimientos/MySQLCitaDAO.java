package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modelo.Cita;
import conexionBD.MySQLConexion;
import dao.CitaDAO;

public class MySQLCitaDAO implements CitaDAO{

	@Override
	public List<Cita> listar() {
		// TODO Auto-generated method stub
				Connection cn = null;
				PreparedStatement pst = null;
				ResultSet rs = null;
				List<Cita> lista = new ArrayList<>();

				try {
					cn = MySQLConexion.getConexion();// generamos la conexión con la BD
					String sql = "SELECT C.ID_CITA, C.ID_RE, R.FECHA, R.HORA,CONCAT (P.NOMBRE,' ',P.PRIMER_APE,' ',P.SEGUNDO_APE) AS PACIENTE,"
							+ " CONCAT (T.NOM,' ',T.PRIMER_APE,' ',T.SEGUNDO_APE) AS TRABAJADOR, "
							+ "CASE T.ID_PUESTO "
							+ "WHEN 1 THEN 'Odontólogo(a)' "
							+ "WHEN 2 THEN 'Odontólogo(a) Preventiva' "
							+ "WHEN 3 THEN 'Odontólogo(a) Infantil' "
							+ "ELSE 'Recepcionista' "
							+ "END "
							+ "AS PUESTO, C.SALA "
							+ "FROM PACIENTE P "
							+ "INNER JOIN RESERVA R ON P.ID_PACIENTE=R.ID_PACIENTE "
							+ "INNER JOIN CITA C ON R.ID_RE=C.ID_RE "
							+ "INNER JOIN TRABAJADOR T ON C.ID_TRABAJADOR=T.ID_TRABAJADOR";
					pst = cn.prepareStatement(sql);
					rs = pst.executeQuery();
					while (rs.next()) {
						Cita cita = new Cita();
						cita.setIdCita(rs.getInt(1));
						cita.setIdReserva(rs.getInt(2));
						cita.setFecha(rs.getString(3));
						cita.setHora(rs.getString(4));
						cita.setNombresPaciente(rs.getString(5));
						cita.setNombresTrabajador(rs.getString(6));
						cita.setPuesto(rs.getString(7));
						cita.setSala(rs.getInt(8));
						lista.add(cita);
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

}
