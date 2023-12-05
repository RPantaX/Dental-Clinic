package servlets;

import java.io.IOException;
import java.util.List;

import Modelo.Paciente;
import dao.DAOFactory;
import dao.PacienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class PacienteServlet
 */
@WebServlet("/PacienteServlet")
public class PacienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PacienteServlet() {
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
		// TODO Auto-generated method stub
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
		PacienteDAO dao= fabrica.getPacienteDAO();

		int ok=dao.eliminar(codigo);

		if(ok==0) {
			mensaje+=" <script> alert('"+" Error al eliminar los datos" +"') </script>";

		}else {
			mensaje+=" <script> alert('"+"Eliminación del paciente  "+codigo+" EXITOSA" +"') </script>";

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
		 PacienteDAO dao= fabrica.getPacienteDAO();
		 Paciente paciente= dao.damePaciente(codigo);

		 //Salidas
		 request.setAttribute("paciente", paciente);
		 request.getRequestDispatcher("paciente/actualizarPaciente.jsp").forward(request, response);
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("paciente/registrarPaciente.jsp").forward(request, response);

	}

	protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);

		PacienteDAO dao= fabrica.getPacienteDAO();
		List<Paciente> pacientes = dao.listar();
		System.out.println(pacientes.size());
		request.setAttribute("lstPacientes", pacientes);
		request.getRequestDispatcher("paciente/listarPaciente.jsp").forward(request, response);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//variables
		String mensaje="";

		//entradas
		int codigo= Integer.parseInt(request.getParameter("txtCodigo"));
		String nombre= request.getParameter("txtNombre");
		String primerApe = request.getParameter("txtPrimerApe");
		String segundoApe = request.getParameter("txtSegundoApe");
		String email = request.getParameter("txtEmail");
		int celular  = Integer.parseInt(request.getParameter("txtCelular"));
		String fecha= request.getParameter("txtFecha");

		//Contructor con  8 parámetros
		 Paciente objPaciente= new Paciente(codigo,nombre,primerApe,segundoApe,email, celular,fecha);
		//Procesos
		DAOFactory fabrica =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		PacienteDAO dao= fabrica.getPacienteDAO();

		//pasamos el objeto a registrar
		int ok= dao.actualizar(objPaciente);

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

		String nombre= request.getParameter("txtNombre");
		String primerApe = request.getParameter("txtPrimerApe");
		String segundoApe = request.getParameter("txtSegundoApe");
		String email = request.getParameter("txtEmail");
		int celular  = Integer.parseInt(request.getParameter("txtCelular"));
		String fecha= request.getParameter("txtFecha");


		///Enviando las entradas al constructor

		//Contructor con  7 parámetros
		Paciente objPaciente= new Paciente(nombre,primerApe,segundoApe,email, celular,fecha);

		//Procesos
		DAOFactory fabrica =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		PacienteDAO dao= fabrica.getPacienteDAO();

		//pasamos el objeto a registrar
		int ok= dao.registrar(objPaciente);

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

		PacienteDAO dao= fabrica.getPacienteDAO();
		dao.generarPDF();
		listar(request, response);
	}
}
