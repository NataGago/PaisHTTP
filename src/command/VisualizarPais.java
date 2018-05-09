package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pais;
import service.PaisService;

public class VisualizarPais implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
    	String pId = request.getParameter("id");
		String pNome = request.getParameter("nome");
		String pPopulacao = request.getParameter("populacao");
		String pArea = request.getParameter("area");
		int id = -1;
		long populacao = 0;
		double area = 0.0;
		try {
			populacao = Long.parseLong(pPopulacao);
			area = Double.parseDouble(pArea);
			id = Integer.parseInt(pId);
		} catch (NumberFormatException e) {

		}

		//instanciar o javabean
		Pais pais = new Pais();
		pais.setId(id);
		pais.setNome(pNome);
		pais.setPopulacao(populacao);
		pais.setArea(area);
		
		//instanciar o service
		PaisService ps = new PaisService();
		
		//Fazer as chamadas de dispatcher e HTTPSession
        RequestDispatcher view = null;
        
        //M�todo de visualiza��o do Pais
        pais = ps.carregar(pais.getId());
    	request.setAttribute("pais", pais);
    	view = request.getRequestDispatcher("VisualizarPais.jsp");
    	
        view.forward(request, response);

	}

}
