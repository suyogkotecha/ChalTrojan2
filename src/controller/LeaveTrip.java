package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: LeaveTrip
 *
 */
 public class LeaveTrip extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LeaveTrip() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String handle = request.getParameter("handle");
		String tripId = request.getParameter("tripId");
		String query = "update feedback.trip set active=0 where tripid="+tripId+" and handle='"+handle+"'";
		
		//out.println(query);
		Connection con = null;
		try
		{		
			con = db.Instance.returnConnection();
			Statement statement = con.createStatement();			
			int update = statement.executeUpdate(query);
			if(update==0)
			{
				out.println(CreateXML.generateXML(0, "DbProblem","LeaveTrip"));
			}
			else
			{
				out.println(CreateXML.generateXML(1, "success","LeaveTrip"));
			}
		}
		catch(SQLException e)
		{
			out.println(CreateXML.generateXML(0, "DbProblem","LeaveTrip"));
		}
		catch(Exception e)
		{
			out.println(CreateXML.generateXML(0, "Problem","LeaveTrip"));
			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.println(CreateXML.generateXML(0, "DbProblem","LeaveTrip"));
				e.printStackTrace();
			}
		}
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}