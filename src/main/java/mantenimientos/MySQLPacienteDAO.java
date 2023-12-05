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
			ps.setString(1, paciente.getNombrePac());
			ps.setString(2, paciente.getPrimerApePac());
			ps.setString(3, paciente.getSegundoApePac());
			ps.setString(4, paciente.getEmailPac());
			ps.setInt(5, paciente.getCelularPac());
			ps.setString(6, paciente.getFechaRegPac());
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
			PdfWriter.getInstance(documento, new FileOutputStream(ruta+"/Desktop/Reporte_Pacientes.pdf"));
			//ONSTANCIA DEÑ pdfwRITER

			documento.open();
			//agregar titulo
			PdfPTable tabla= new PdfPTable(1);
			PdfPCell celda= new PdfPCell(new Phrase("Registro de Pacientes",fuenteTitulo));
			celda.setColspan(7);
			celda.setBorderColor(BaseColor.WHITE);
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabla.addCell(celda);
			documento.add(tabla);
			//agregar parrafos
			Paragraph parrafo = new Paragraph();
			parrafo.add(new Phrase("A continuación una lista actualizada de los Pacientes: ", fuenteParrafo));
			documento.add(parrafo);
			//salto de linea
			Paragraph saltolinea = new Paragraph();
			saltolinea.add(new Phrase(Chunk.NEWLINE));
			saltolinea.add(new Phrase(Chunk.NEWLINE));
			documento.add(saltolinea);
			//tabla trabajadores
			PdfPTable tabla2= new PdfPTable(7);
			tabla2.addCell("ID");
			tabla2.addCell("Nombre");
			tabla2.addCell("1 Apellido");
			tabla2.addCell("2 Apellido");
			tabla2.addCell("Email");
			tabla2.addCell("Celular");
			tabla2.addCell("Fecha registro");
			try {
				cn = MySQLConexion.getConexion();// generamos la conexión con la BD
				String sql = "SELECT *FROM PACIENTE";
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
