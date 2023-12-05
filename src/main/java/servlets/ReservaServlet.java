package servlets;
import java.io.IOException;
import java.util.List;

import Modelo.Reserva;
import Modelo.TipoEstado;
import dao.DAOFactory;
import dao.ReservaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReservaServlet
 */
@WebServlet("/ReservaServlet")
public class ReservaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservaServlet() {
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
		String opcion =request.getParameter("opcion");
		System.out.print(" get opcion -->" +opcion);

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

			String opcion =request.getParameter("opcion");
			System.out.println(" opcion -->" +opcion);

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
		String mensaje="";
		int id_reserva= Integer.parseInt(request.getParameter("cod"));

		DAOFactory RESERVA =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ReservaDAO dao= RESERVA.getReservaDAO();

		int ok=dao.eliminar(id_reserva);

		if(ok==0) {
			mensaje+="<script> alert('"+" Error al eliminar los datos" + "') </script>";
		}else {
			mensaje+="<script> alert('"+" Eliminación de la reserva  "+id_reserva+" EXITOSA" + "') </script>";
		}

		request.setAttribute("mensaje", mensaje);

		listar(request, response);
	}
	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Entradas
		 int codigo= Integer.parseInt(request.getParameter("cod"));

		 //Procesos
		 DAOFactory RESERVA =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		 ReservaDAO dao= RESERVA.getReservaDAO();
		 Reserva reserva= dao.obtenerReserva(codigo);

		 //Salidas
		 request.setAttribute("reserva", reserva);
		 request.getRequestDispatcher("reserva/actualizarReserva.jsp").forward(request, response);
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("reserva/registrarReserva.jsp").forward(request, response);
	}
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DAOFactory RESERVA = DAOFactory.getDAOFactory(DAOFactory.MYSQL);

		ReservaDAO dao=RESERVA.getReservaDAO();

		List<Reserva> lista = dao.listar();

		request.setAttribute("lstReservas", lista);
		request.getRequestDispatcher("reserva/listarReserva.jsp").forward(request,  response);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//variables
		String mensaje="";

		//entradas
		int codigo= Integer.parseInt(request.getParameter("txtCodigo"));
		int idPaciente=Integer.parseInt(request.getParameter("txtIdPaciente"));
		String fecha= request.getParameter("txtFecha");
		String hora = request.getParameter("txtHora");

		int IdEstado= Integer.parseInt(request.getParameter("cboEstado"));
		Double precio  = Double.parseDouble(request.getParameter("txtPrecio"));



		///Enviando las entradas al constructor
		 TipoEstado objTipoEstado= new TipoEstado();
		 objTipoEstado.setId_estado(IdEstado);

		//Contructor con  8 parámetros
		 Reserva objReserva= new Reserva(codigo,idPaciente,fecha, hora, objTipoEstado, precio);

		//Procesos
		DAOFactory RESERVA =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ReservaDAO dao= RESERVA.getReservaDAO();

		//pasamos el objeto a registrar
		int ok= dao.actualizar(objReserva);

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
				int id_paciente= Integer.parseInt(request.getParameter("txtIdPaciente"));
				String fecha = request.getParameter("txtFecha");
				String hora = request.getParameter("txtHora");
				int IdEstado = Integer.parseInt(request.getParameter("cboEstado"));
				Double precio = Double.parseDouble(request.getParameter("txtPrecio"));

				///Enviando las entradas al constructor
				 TipoEstado objTipoEstado= new TipoEstado();
				 objTipoEstado.setId_estado(IdEstado);


				//Contructor con  7 parámetros
				 Reserva objReserva= new Reserva(id_paciente,fecha, hora, objTipoEstado, precio);

				//Procesos
				DAOFactory RESERVA =DAOFactory.getDAOFactory(DAOFactory.MYSQL);
				ReservaDAO dao= RESERVA.getReservaDAO();

				//pasamos el objeto a registrar
				int ok= dao.registrar(objReserva);

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
		DAOFactory RESERVA = DAOFactory.getDAOFactory(DAOFactory.MYSQL);

		ReservaDAO dao= RESERVA.getReservaDAO();
		dao.generarPDF();
		listar(request, response);
	}





}
