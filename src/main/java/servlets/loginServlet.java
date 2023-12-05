package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;

import Modelo.Trabajador;
import dao.DAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
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
		case "log":
					validarUsuario(request,response);
					break;
		case "ini":
					irAInicio(request,response);
					break;

		case "cer":
					cerrarSesion(request,response);
					break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + opcion);
		}
	}

	private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Invalida la sesión actual
	    HttpSession session = request.getSession(false); // Obtenemos la sesión actual sin crear una nueva si no existe

	    if (session != null) {
	        session.invalidate(); // Invalida la sesión actual si existe
	    }

	    request.getRequestDispatcher("logeo/login.jsp").forward(request, response);

	}

	private void irAInicio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("logeo/principal.jsp").forward(request, response);

	}

	private void validarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Variables
		 String mensaje="", url="";


		//Entrada de datos
		String usuario = request.getParameter("txtUsuario");
		String contrasena = request.getParameter("txtContrasena");


		//Proceso
		DAOFactory fabrica=  DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		Trabajador objTra = fabrica.getTrabajadorDAO().validarTrabajador(usuario, contrasena);

		if (objTra!=null) {

			HttpSession miSession = request.getSession();
			System.out.println("ID Session: "+ miSession.getId());
			System.out.println("Fecha-----: "+ new SimpleDateFormat().format(miSession.getCreationTime()));
			System.out.println("Duración--: "+ miSession.getMaxInactiveInterval());
			/*
			//COOKIES
			boolean nuevoUsuario =true;
			Cookie[] cookies=request.getCookies();
			//Buscamos si ya existe una cookie creada con anterioridad
			//llamada visitanteRecurrente
			if(cookies!=null) {
				for(Cookie c:cookies) {
					if(c.getName().equals("visitanteRecurrente") && c.getValue().equals("si")) {
						//si ya existe la cookie es un usuario recurrente
						nuevoUsuario = false;
						break;

					}
				}
			}
			String mensajeCook=null;
			if(nuevoUsuario) {
				Cookie visitanteCookie = new Cookie("VisitanteRecurrente","si");
				response.addCookie(visitanteCookie);
				mensajeCook="Gracias por visitar nustro sitio por primera vez";
			}
			else {
				mensajeCook="Gracias por visitar NUEVAMENTE nuestro sitio";
			}
			request.setAttribute("mensajeCook",mensajeCook);*/
			request.getSession().setAttribute("datosTrabajador", objTra);
			url="logeo/principal.jsp";


		}else {
			mensaje+="Usuario o clave incorrecto";
			url="logeo/login.jsp";
			request.setAttribute("mensaje",mensaje);

		}

		//Salida a la página principal
		request.getRequestDispatcher(url).forward(request, response);

	}

}
