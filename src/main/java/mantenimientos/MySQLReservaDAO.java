package mantenimientos;

import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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



	@Override
	public void generarPDF() {
		// TODO Auto-generated method stub
		Font fuenteTitulo=FontFactory.getFont(FontFactory.TIMES_ROMAN, 16);
		Font fuenteParrafo=FontFactory.getFont(FontFactory.HELVETICA, 11);
		Document documento= new Document(PageSize.A4,35,30,50,50);
		Connection cn= null;
		PreparedStatement ps= null;
		ResultSet rs=null;

		try {
			String ruta= System.getProperty("user.home");
			PdfWriter.getInstance(documento, new FileOutputStream(ruta+"/Desktop/Reporte_Reservas.pdf"));
			//ONSTANCIA DEÑ pdfwRITER

			documento.open();
			//agregar titulo
			PdfPTable tabla= new PdfPTable(1);
			PdfPCell celda= new PdfPCell(new Phrase("Registro de Reservas",fuenteTitulo));
			celda.setColspan(6);
			celda.setBorderColor(BaseColor.WHITE);
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabla.addCell(celda);
			documento.add(tabla);
			//agregar parrafos
			Paragraph parrafo = new Paragraph();
			parrafo.add(new Phrase("A continuación una lista actualizada de las Reservas: ", fuenteParrafo));
			documento.add(parrafo);
			//salto de linea
			Paragraph saltolinea = new Paragraph();
			saltolinea.add(new Phrase(Chunk.NEWLINE));
			saltolinea.add(new Phrase(Chunk.NEWLINE));
			documento.add(saltolinea);
			//tabla trabajadores
			PdfPTable tabla2= new PdfPTable(6);
			tabla2.addCell("ID Reserva");
			tabla2.addCell("ID Paciente");
			tabla2.addCell("Fecha");
			tabla2.addCell("Hora");
			tabla2.addCell("Estado");
			tabla2.addCell("Precio");


			try {
				cn = MySQLConexion.getConexion();// generamos la conexión con la BD
				String sql = "SELECT ID_RE, ID_PACIENTE, FECHA, HORA, "
						+ "		CASE ID_ESTADO"
						+ "		WHEN 1  THEN 'ACTIVA'"
						+ "        ELSE 'ANULADA'"
						+ "        END AS ESTADO,"
						+ "        PRECIO FROM RESERVA";
				ps = cn.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					do {
						tabla2.addCell(rs.getString(1));
						tabla2.addCell(rs.getString(2));
						tabla2.addCell(rs.getString(3));
						tabla2.addCell(rs.getString(4));
						tabla2.addCell(rs.getString(5));
						tabla2.addCell(rs.getString(6));

					}while(rs.next());
					documento.add(tabla2);
				}
			} catch (DocumentException |SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error en la sentencia" + e.getMessage());
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

			documento.close();
		} catch(DocumentException | HeadlessException | FileNotFoundException s) {
			System.out.println("Error en el PDF ");
		}
	}

}
