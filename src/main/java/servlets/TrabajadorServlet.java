package servlets;
import java.io.IOException;
import java.util.List;

import Modelo.TipoPuesto;
import Modelo.Trabajador;
import dao.DAOFactory;
import dao.TrabajadoresDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PeliculaServlet
 */
@WebServlet("/TrabajadorServlet")
public class TrabajadorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrabajadorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String opcion=request.getParameter("opcion");
		System.out.println("get opcion --->" +opcion);

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String opcion = request.getParameter("opcion");
		System.out.println("Valor del boton :" + opcion);

		switch (opcion) {
		case "reg":
					registrar(request, response);
					break;
		case "act":
					actualizar(request,response);
					break;
		case "lis":
					listar(request,response);
					break;
		case "nue":
					nuevo(request,response);
					break;
		case "bus":
					buscar(request,response);
					break;
		case "eli":
					eliminar(request,response);
					break;
		case "pdf":
					generarPDF(request,response);
					break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + opcion);
		}

	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//variables
		String mensaje="";

		//Entradas
		int codigo=  Integer.parseInt(request.getParameter("cod"));

		//Procesos
		DAOFactory fabrica =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		TrabajadoresDAO dao= fabrica.getTrabajadorDAO();

		int ok=dao.eliminar(codigo);

		if(ok==0) {
			mensaje+=" <script> alert('"+" Error al eliminar los datos" +"') </script>";

		}else {
			mensaje+=" <script> alert('"+"Eliminación del trabajador  "+codigo+" EXITOSA" +"') </script>";

		}

		//Salida
		request.setAttribute("mensaje", mensaje);

		//Invocamos a Listar
		listar(request,response);

	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Entradas
		 int codigo= Integer.parseInt(request.getParameter("cod"));

		 //Procesos
		 DAOFactory fabrica =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		 TrabajadoresDAO dao= fabrica.getTrabajadorDAO();
		 Trabajador trabajador= dao.dameTrabajador(codigo);

		 //Salidas
		 request.setAttribute("trabajador", trabajador);
		 request.getRequestDispatcher("trabajador/actualizarTrabajador.jsp").forward(request, response);
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("trabajador/registrarTrabajador.jsp").forward(request, response);

	}

	protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);

		TrabajadoresDAO dao = fabrica.getTrabajadorDAO();
		List<Trabajador> trabajadores = dao.listar();
		System.out.println(trabajadores.size());
		request.setAttribute("lstTrabajadores", trabajadores);
		request.getRequestDispatcher("trabajador/listarTrabajador.jsp").forward(request, response);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//variables
		String mensaje="";

		//entradas
		int codigo= Integer.parseInt(request.getParameter("txtCodigo"));
		String nombre= request.getParameter("txtNombres");
		String primerApe = request.getParameter("txtPrimerApellido");
		String segundoApe = request.getParameter("txtSegundoApellido");
		String fechaNac=request.getParameter("txtFechaNac");
		int codTipoPuesto= Integer.parseInt(request.getParameter("cboTipoPuesto"));
		String email = request.getParameter("txtEmail");
		int celular  = Integer.parseInt(request.getParameter("txtCelular"));
		String fecha_ingreso= request.getParameter("txtFechaIngreso");
		float sueldo= Float.parseFloat(request.getParameter("txtSueldo"));
		String usuario= request.getParameter("txtUsuario");
		String contrasena= request.getParameter("txtContrasena");


		///Enviando las entradas al constructor
		 TipoPuesto objTipoPuesto= new TipoPuesto();
		 objTipoPuesto.setIdPuesto(codTipoPuesto);

		//Contructor con  8 parámetros
		 Trabajador objTrabajador= new Trabajador(codigo,nombre,primerApe,segundoApe,fechaNac, objTipoPuesto, email,celular,fecha_ingreso,sueldo,usuario,contrasena);

		//Procesos
		DAOFactory fabrica =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		TrabajadoresDAO dao= fabrica.getTrabajadorDAO();

		//pasamos el objeto a registrar
		int ok= dao.actualizar(objTrabajador);

		if(ok == 0) {
					mensaje+=" <script> alert('"+" Error al registrar los datos" +"')</script>";
		}else {
					mensaje+=" <script> alert('"+" Registro exitoso" +"')</script>";
			}


		request.setAttribute("mensaje", mensaje);

		//invocar a listar
		listar(request, response);

	}

	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//variables
		String mensaje="";

		//entradas
		String nombre= request.getParameter("txtNombres");
		String primerApe = request.getParameter("txtPrimerApellido");
		String segundoApe = request.getParameter("txtSegundoApellido");
		String fechaNac=request.getParameter("txtFechaNac");
		int codTipoPuesto= Integer.parseInt(request.getParameter("cboTipoPuesto"));
		String email = request.getParameter("txtEmail");
		int celular  = Integer.parseInt(request.getParameter("txtCelular"));
		String fecha_ingreso= request.getParameter("txtFechaIngreso");
		float sueldo= Float.parseFloat(request.getParameter("txtSueldo"));
		String usuario= request.getParameter("txtUsuario");
		String contrasena= request.getParameter("txtContrasena");


		///Enviando las entradas al constructor
		 TipoPuesto objTipoPuesto= new TipoPuesto();
		 objTipoPuesto.setIdPuesto(codTipoPuesto);


		//Contructor con  7 parámetros
		 Trabajador objTrabajador= new Trabajador(nombre,primerApe,segundoApe,fechaNac, objTipoPuesto, email,celular,fecha_ingreso,sueldo,usuario,contrasena);

		//Procesos
		DAOFactory fabrica =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		TrabajadoresDAO dao= fabrica.getTrabajadorDAO();

		//pasamos el objeto a registrar
		int ok= dao.registrar(objTrabajador);

		if(ok == 0) {
					mensaje+=" <script> alert('"+" Error al registrar los datos" +"')</script>";
		}else {
					mensaje+=" <script> alert('"+" Registro exitoso" +"')</script>";
			}

		request.setAttribute("mensaje", mensaje);

		//invocar a listar
		listar(request, response);

	}
	protected void generarPDF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);

		TrabajadoresDAO dao = fabrica.getTrabajadorDAO();
		dao.generarPDF();
		listar(request, response);
	}
}
