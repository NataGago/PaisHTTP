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

public class AlterarPais implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
    	String pId = request.getParameter("id");
		String pNome = request.getParameter("nome");
		long pPopulacao = Long.parseLong(request.getParameter("populacao"));
		double pArea = Double.parseDouble(request.getParameter("area"));
		int id = -1;
		try {
			id = Integer.parseInt(pId);
		} catch (NumberFormatException e) {

		}

		//instanciar o javabean
		Pais pais = new Pais();
		pais.setId(id);
		pais.setNome(pNome);
		pais.setPopulacao(pPopulacao);
		pais.setArea(pArea);
		
		//instanciar o service
		PaisService ps = new PaisService();
		
		//Fazer as chamadas de dispatcher e HTTPSession
        RequestDispatcher view = null;
        HttpSession session = request.getSession();
        
        //M�todo de Altera��o do Pais
        ps.atualizar(pais);
        @SuppressWarnings("unchecked")
    	ArrayList<Pais> lista = (ArrayList<Pais>)session.getAttribute("lista");
    	int pos = busca(pais, lista);
    	lista.remove(pos);
    	lista.add(pos, pais);
    	session.setAttribute("lista", lista);
    	request.setAttribute("pais", pais);
    	view = request.getRequestDispatcher("VisualizarPais.jsp");
    	
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
