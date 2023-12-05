package mantenimientos;

import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
//IMPORTES PARA LA GENERACIÓN DEL PDF
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

import Modelo.TipoPuesto;
import Modelo.Trabajador;
import conexionBD.MySQLConexion;
import dao.TrabajadoresDAO;

public class MySQLTrabajadorDAO implements TrabajadoresDAO {

	@Override
	public List<Trabajador> listar() {
		// TODO Auto-generated method stub
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Trabajador> trabajadores = new ArrayList<>();

		try {
			cn = MySQLConexion.getConexion();// generamos la conexión con la BD
			String sql = "SELECT ID_TRABAJADOR" + " ,NOM" + "	,PRIMER_APE" + " ,SEGUNDO_APE, FECHA_NACIMIENTO" + " ,ID_PUESTO"
					+ "  ,CASE ID_PUESTO" + " WHEN 1 THEN 'Odontólogo(a) Estético' WHEN 2 THEN 'Odontólogo(a) Preventiva' WHEN 3 THEN 'Odontólogo(a) Infantil'" + "     ELSE 'Recepcionista'"
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
					+ "  ,CASE ID_PUESTO" + " WHEN 1 THEN 'Odontólogo(a) Estético' WHEN 2 THEN 'Odontólogo(a) Preventiva' WHEN 3 THEN 'Odontólogo(a) Infantil'" + "ELSE 'Recepcionista'"
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

	@Override
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
				trabajador.setUsername(rs.getString(12));
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
			PdfWriter.getInstance(documento, new FileOutputStream(ruta+"/Desktop/Reporte_Trabajadores.pdf"));
			//ONSTANCIA DEÑ pdfwRITER

			documento.open();
			//agregar titulo
			PdfPTable tabla= new PdfPTable(1);
			PdfPCell celda= new PdfPCell(new Phrase("Registro de trabajadores",fuenteTitulo));
			celda.setColspan(12);
			celda.setBorderColor(BaseColor.WHITE);
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabla.addCell(celda);
			documento.add(tabla);
			//agregar parrafos
			Paragraph parrafo = new Paragraph();
			parrafo.add(new Phrase("A continuación una lista actualizada de los trabajadores: ", fuenteParrafo));
			documento.add(parrafo);
			//salto de linea
			Paragraph saltolinea = new Paragraph();
			saltolinea.add(new Phrase(Chunk.NEWLINE));
			saltolinea.add(new Phrase(Chunk.NEWLINE));
			documento.add(saltolinea);
			//tabla trabajadores
			PdfPTable tabla2= new PdfPTable(12);
			tabla2.addCell("IDTrabajador");
			tabla2.addCell("Nombre");
			tabla2.addCell("1 Apellido");
			tabla2.addCell("2 Apellido");
			tabla2.addCell("FechaNacimiento");
			tabla2.addCell("Puesto");
			tabla2.addCell("Email");
			tabla2.addCell("Celular");
			tabla2.addCell("FechaIngreso");
			tabla2.addCell("Sueldo");
			tabla2.addCell("Usuario");
			tabla2.addCell("Contraseña");

			try {
				cn = MySQLConexion.getConexion();// generamos la conexión con la BD
				String sql = "SELECT ID_TRABAJADOR" + " ,NOM" + "	,PRIMER_APE" + " ,SEGUNDO_APE, FECHA_NACIMIENTO"
						+ "  ,CASE ID_PUESTO" + " WHEN 1 THEN 'Odontólogo(a) Estético' WHEN 2 THEN 'Odontólogo(a) Preventiva' WHEN 3 THEN 'Odontólogo(a) Infantil'" + "     ELSE 'Recepcionista'"
						+ "	  END AS PUESTO" + "  ,EMAIL" + "  ,CELULAR" + "  ,FECHA_INGRESO" + "  ,SUELDO" + "  ,USERNAME"
						+ "  ,CONTRASENA " + "FROM TRABAJADOR";
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
						tabla2.addCell(rs.getString(9));
						tabla2.addCell(rs.getString(10));
						tabla2.addCell(rs.getString(11));
						tabla2.addCell(rs.getString(12));

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
