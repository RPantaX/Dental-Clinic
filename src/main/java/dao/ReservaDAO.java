package dao;
import java.util.List;

import Modelo.Reserva;

public interface ReservaDAO {

	public List<Reserva> listar();

	public Reserva obtenerReserva(int id_reserva);

	public int registrar(Reserva reserva);

	public int eliminar(int id_reserva);

	public int actualizar(Reserva reserva);
	public void generarPDF();
}
