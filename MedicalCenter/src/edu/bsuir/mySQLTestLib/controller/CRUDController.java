package edu.bsuir.mySQLTestLib.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.bsuir.mySQLTestLib.config.ConfigurationException;
import edu.bsuir.mySQLTestLib.dao.AbstractDAO;
import edu.bsuir.mySQLTestLib.dao.DiseasesDAO;
import edu.bsuir.mySQLTestLib.dao.DoctorsDAO;
import edu.bsuir.mySQLTestLib.dao.PaymentsDAO;
import edu.bsuir.mySQLTestLib.dao.ProceduresDAO;
import edu.bsuir.mySQLTestLib.dao.UsersDAO;

@WebServlet("/adminTables")
public class CRUDController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(CRUDController.class);

	private static Map<String, AbstractDAO> listDAO = Collections.unmodifiableMap(new HashMap<String, AbstractDAO>() {
		{
			put("Diseases", new DiseasesDAO());
			put("Users", new UsersDAO());
			put("Doctors", new DoctorsDAO());
			put("Procedures", new ProceduresDAO());
			put("Payments", new PaymentsDAO());
		}
	});

	private static Map<String, String> listJspHandlers = Collections.unmodifiableMap(new HashMap<String, String>() {
		{
			put("Diseases", "DiseasesCRUD.jsp");
			put("Users", "UsersCRUD.jsp");
			put("Doctors", "DoctorCRUD.jsp");
			put("Procedures", "ProceduresCRUD.jsp");
			put("Payments", "PaymentsCRUD.jsp");
			put("AllVariants", "tablesVariants.jsp");
		}
	});

	private void sendResponse(HttpServletRequest request, HttpServletResponse response, String servletHandler,
			List<?> args) {
		try {
			request.setAttribute("args", args);
			request.getRequestDispatcher(listJspHandlers.get(servletHandler)).forward(request, response);
			log.info("Request was successfully executed");
		} catch (Exception e) {
			log.warn("Error while executing doGet");
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String DAOname = request.getParameter("dao");
		if (DAOname == null) {
			sendResponse(request, response, "AllVariants", null);
		} else {
			try {
				if (listDAO.containsKey(DAOname)) {
					List<?> list = listDAO.get(DAOname).getAll();
					
					sendResponse(request, response, DAOname, list);
				}
			} catch (ConfigurationException |SQLException  e) {
				log.warn("Exception occured while doGet");
			} 
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		Map<String, String> responseFromJsp = new HashMap<String, String>();
		Enumeration en = request.getParameterNames();
		while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			String[] values = request.getParameterValues(name);
			String value = "";
			for (int i = 0; i < values.length; i++) {
				value += values[i];
			}
			responseFromJsp.put(name, value);
		}
		listDAO.get(responseFromJsp.get("dao")).Methods( responseFromJsp);
		doGet(request, response);
		
	}
}
