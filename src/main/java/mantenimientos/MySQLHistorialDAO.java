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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Modelo.Historial;
import conexionBD.MySQLConexion;
import dao.HistorialDAO;

public class MySQLHistorialDAO implements HistorialDAO{

	@Override
	public List<Historial> listar(int codigo) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Historial> lista = new ArrayList<>();

		try {
			con = MySQLConexion.getConexion();// generamos la conexión con la BD
			String sql = "SELECT H.ID_HIST, H.ID_PACIENTE, H.DESCRIPCION, H.FECHA, H.ID_TRABAJADOR, "
					+ "CONCAT (T.NOM,' ',T.PRIMER_APE,' ',T.SEGUNDO_APE) AS TRABAJADOR "
					+ " FROM HISTORIAL H "
					+ "INNER JOIN TRABAJADOR T ON H.ID_TRABAJADOR=T.ID_TRABAJADOR "
					+ " WHERE H.ID_PACIENTE=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, codigo);
			rs=pst.executeQuery();

			while (rs.next()) {
				Historial historial = new Historial();
				historial.setIdHist(rs.getInt(1));
				historial.setIdPac(rs.getInt(2));
				historial.setDesc(rs.getString(3));
				historial.setFecha(rs.getString(4));
				historial.setIdTrabajador(rs.getInt(5));
				historial.setNombresTrab(rs.getString(6));
				//listando
				lista.add(historial);
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
	public int registrar(Historial historial, int idPac) {
		int resultado = 0;
		Connection con = null;
		PreparedStatement pst = null;
		LocalDate fechaActual = LocalDate.now();

        // Formatear la fecha si es necesario (opcional)
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaActual.format(formato);
		try {
			con = MySQLConexion.getConexion();
			String sql = "INSERT INTO HISTORIAL VALUES(null,?,?,?,?)";
			pst = con.prepareStatement(sql);
			// Parametrizar el PreparedStatement
			pst.setInt(1, idPac);
			pst.setString(2, historial.getDesc());
			pst.setString(3, "2023-12-02");
			pst.setInt(4, historial.getIdTrabajador());


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
