package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: UpdateLocation
 * 
 */
 public class UpdateLocation extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public UpdateLocation() {
		super();
	}   	
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		PrintWriter out = response.getWriter();
		
		String handle = request.getParameter("handle");
		String tripId = request.getParameter("tripId");
		String lat = request.getParameter("lat");
		String longi = request.getParameter("long");
		
		String query="insert into location (tripid,handle, lat, lng, timeUp) values ("+tripId+",'"+handle+"',"+lat+","+longi+",'"+dateFormat.format(date).toString()+"')";
		Connection con = null;		
			try
			{		
				con = db.Instance.returnConnection();
				Statement statement = con.createStatement();			
				int update = statement.executeUpdate(query);
				if(update==0)
				{
					out.println(CreateXML.generateXML(0, "DbProblem","UpdateLocation"));
				}
				else
				{
					out.println(CreateXML.generateXML(1, "success","UpdateLocation"));
				}
			}
			catch(SQLException e)
			{
				out.println(CreateXML.generateXML(0, "DbProblem","UpdateLocation"));
				e.printStackTrace();
			}
			catch(Exception e)
			{
				out.println(CreateXML.generateXML(0, "Problem","UpdateLocation"));
				e.printStackTrace();
			}
			finally
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					out.println(CreateXML.generateXML(0, "DbProblem","UpdateLocation"));
					e.printStackTrace();
				}
			}		
	}  	
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}