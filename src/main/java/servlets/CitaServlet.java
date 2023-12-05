package servlets;

import java.io.IOException;
import java.util.List;

import Modelo.Cita;
import dao.CitaDAO;
import dao.DAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class CitaServlet
 */
@WebServlet("/CitaServlet")
public class CitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CitaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		case "act":
					//actualizar(request,response);
					break;
		case "lis":
					listar(request,response);
					break;
		case "bus":
			        //buscar(request,response);
					break;
		case "eli":
					//eliminar(request,response);
					break;
		case "pdf":
					generarPDF(request,response);
					break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + opcion);
		}
	}
	protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);

		CitaDAO dao = fabrica.getCitaDAO();
		List<Cita> citas = dao.listar();
		System.out.println(citas.size());
		request.setAttribute("lstCita", citas);
		request.getRequestDispatcher("cita/listarCita.jsp").forward(request, response);
	}
	protected void generarPDF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);

		CitaDAO dao = fabrica.getCitaDAO();
		dao.generarPDF();
		listar(request, response);
	}

}
