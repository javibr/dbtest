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

@WebServlet("/error")
public class error extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/* RequestDispatcher */
		req.getRequestDispatcher("error.jsp").forward(req, resp);

	}
}
