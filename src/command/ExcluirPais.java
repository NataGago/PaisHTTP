package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Pais;
import service.PaisService;

public class ExcluirPais implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
    	String pId = request.getParameter("id");
		
		int id = -1;
		
		try {
			
			id = Integer.parseInt(pId);
		} catch (NumberFormatException e) {

		}

		//instanciar o javabean
		Pais pais = new Pais();
		pais.setId(id);
				
		//instanciar o service
		PaisService ps = new PaisService();
		
		//Fazer as chamadas de dispatcher e HTTPSession
        RequestDispatcher view = null;
        HttpSession session = request.getSession();
        
        // metodo de exclus�o do Pais por ID
        ps.excluir(pais.getId());
        @SuppressWarnings("unchecked")
    	ArrayList<Pais> lista = (ArrayList<Pais>) session.getAttribute("lista");
    	lista.remove(busca(pais, lista));
    	session.setAttribute("lista", lista);
    	view = request.getRequestDispatcher("ListarPaises.jsp");
    	
    	view.forward(request, response);
	}
	
	public int busca(Pais pais, ArrayList<Pais> lista){
		Pais to;
		for(int i = 0; i < lista.size(); i++) {
			to = lista.get(i);
			if(to.getId() == pais.getId()) {
				return i;
			}
		}
		return -1;
	}
}
