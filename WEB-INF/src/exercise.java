import java.io.IOException;
import java.io.PrintWriter;

import Model.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import javax.naming.NamingException;

@WebServlet("/exercise")
public class exercise extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try (DBManager db = new DBManager()) {
			HttpSession session = req.getSession();
			contact contact = new contact();
			contact.setFirstName(req.getParameter("firstName"));
			contact.setLastName(req.getParameter("lastName"));
			contact.setAdress(req.getParameter("adress"));
			contact.setMail(req.getParameter("email"));
			contact.setNumber(req.getParameter("phone"));
/*
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String adress = req.getParameter("adress");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
*/
			
			if ( db.registerContact(contact) ) {
				
				resp.sendRedirect("exercise");
				
						

			} else {
					/* RequestDispatcher */
					String error = "Contact is already created";
					session.setAttribute("error", error);
					resp.sendRedirect("error");
			}
			
		}catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("SQLException: "+ex.getMessage());
			System.out.println("SQLState: "+ex.getSQLState());
			System.out.println("VendorError: "+ex.getErrorCode());
		}
		catch (NamingException na){
		na.printStackTrace();
		
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/* RequestDispatcher */
		req.getRequestDispatcher("exercise.jsp").forward(req, resp);

	}

}
