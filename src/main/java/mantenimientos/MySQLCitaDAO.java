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
			PdfWriter.getInstance(documento, new FileOutputStream(ruta+"/Desktop/Reporte_Citas.pdf"));
			//INSTANCIA DEÑ pdfwRITER

			documento.open();
			//agregar titulo
			PdfPTable tabla= new PdfPTable(1);
			PdfPCell celda= new PdfPCell(new Phrase("Registro de Citas",fuenteTitulo));
			celda.setColspan(8);
			celda.setBorderColor(BaseColor.WHITE);
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabla.addCell(celda);
			documento.add(tabla);
			//agregar parrafos
			Paragraph parrafo = new Paragraph();
			parrafo.add(new Phrase("A continuación una lista actualizada de las citas: ", fuenteParrafo));
			documento.add(parrafo);
			//salto de linea
			Paragraph saltolinea = new Paragraph();
			saltolinea.add(new Phrase(Chunk.NEWLINE));
			saltolinea.add(new Phrase(Chunk.NEWLINE));
			documento.add(saltolinea);
			//tabla trabajadores
			PdfPTable tabla2= new PdfPTable(8);
			tabla2.addCell("ID Cita");
			tabla2.addCell("ID Reserva");
			tabla2.addCell("Fecha");
			tabla2.addCell("Hora");
			tabla2.addCell("Paciente");
			tabla2.addCell("Trabajador");
			tabla2.addCell("Puesto");
			tabla2.addCell("Sala");
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
						tabla2.addCell(rs.getString(7));
						tabla2.addCell(rs.getString(8));
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
