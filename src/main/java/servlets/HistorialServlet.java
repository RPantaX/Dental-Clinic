package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Modelo.Historial;
import Modelo.Paciente;
import dao.DAOFactory;
import dao.PacienteDAO;
import dao.HistorialDAO;

/**
 * Servlet implementation class HistorialServlet
 */
@WebServlet("/HistorialServlet")
public class HistorialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistorialServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcion = request.getParameter("opcion");
		System.out.println("Valor del boton :" + opcion);

		switch (opcion) {
		case "reg":
					registrar(request, response);
					break;
		case "act":
					//actualizar(request,response);
					break;
		case "lis":
					listar(request,response);
					break;
		case "nue":
					nuevo(request,response);
					break;
		case "bus":
					//buscar(request,response);
					break;
		case "eli":
					//eliminar(request,response);
					break;
		case "pdf":
					//generarPDF(request,response);
					break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + opcion);
		}
	}
//variable global
	int codigoReg= 0;
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		int codigo= Integer.parseInt(request.getParameter("cod"));
		HistorialDAO dao= fabrica.getHistorialDAO();
		List<Historial> historial = dao.listar(codigo);
		System.out.println(historial.size());
		request.setAttribute("lstHistorial", historial);
		request.getRequestDispatcher("historial/listarHistorial.jsp").forward(request, response);
	}
	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		codigoReg= Integer.parseInt(request.getParameter("cod"));
		request.getRequestDispatcher("historial/registrarHistorial.jsp").forward(request, response);
	}
	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//variables
				String mensaje="";
				//entradas
				String descripcion= request.getParameter("txtDesc");
				int idTrabajador  = Integer.parseInt(request.getParameter("txtIdPac"));

				//Contructor con  7 parámetros
				Historial objHistorial= new Historial(idTrabajador,descripcion);

				//Procesos
				DAOFactory fabrica =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				HistorialDAO dao= fabrica.getHistorialDAO();

				//pasamos el objeto a registrar
				int ok= dao.registrar(objHistorial, codigoReg);

				if(ok == 0) {
							mensaje+=" <script> alert('"+" Error al registrar los datos" +"')</script>";
				}else {
							mensaje+=" <script> alert('"+" Registro exitoso" +"')</script>";
					}

				request.setAttribute("mensaje", mensaje);

				//invocar a listar
				request.getRequestDispatcher("HistorialServlet?opcion=lis&cod="+codigoReg).forward(request, response);
	}

	



}
