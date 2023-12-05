package dao;
import java.util.List;
import Modelo.Historial;

public interface HistorialDAO {
	public List<Historial> listar(int codigo);
	public int registrar(Historial historial, int idPac);
	public void generarPDF();
}
