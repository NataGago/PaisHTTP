package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Pais;
import service.PresidenteService;

public class ListarPaisesBuscar implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String chave = request.getParameter("data[Search]");
		PresidenteService presidente = new PresidenteService();
		ArrayList<Pais> lista = null;
		HttpSession session = request.getSession();
		
		//Listar paises pela busca
		if(chave != null && chave.length()>0) {
			lista = presidente.listarPaises(chave);
		}else {
			lista = presidente.listarPaises();
		}
		session.setAttribute("lista", lista);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ListarPaises.jsp");
		dispatcher.forward(request, response);	
	}

}
