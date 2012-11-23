package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.jdbc.ResultSetMetaData;
/**
 * Servlet implementation class for Servlet: CreateTrip
 *
 */
 public class CreateTrip extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CreateTrip() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String handle = request.getParameter("handle");
		String tripName = request.getParameter("tripName");
		String pwd = request.getParameter("pwd");
		String destinationName = request.getParameter("destName");
		String destLang = request.getParameter("destLang");
		String destLongi = request.getParameter("destLongi");
		String tripDate = request.getParameter("tripDate");
		int update=0;
		try
		{
			if(destinationName == null)
				destinationName = "default";
			
			Connection con = db.Instance.returnConnection();
			Statement statement = con.createStatement(); 
			String query = "insert into trip_owner (owner, tripName, destName, destLang, destLongi, tripDate, active, tripPwd) values ('"+handle+"','"+tripName+"','"+destinationName+"',"+Double.parseDouble(destLang)+","+Double.parseDouble(destLongi)+",'"+tripDate+"','Y','"+pwd+"')";
			out.println(query);
			update =statement.executeUpdate(query);						
			query = "select tripName from feedback.trip_owner where owner='"+handle+"' and active = 'Y'";
			ResultSet resultSet = statement.executeQuery(query);
			
			ResultSetMetaData meta = (ResultSetMetaData) resultSet.getMetaData();
			out.println(CreateXML.generateTripXML(resultSet,1));
			con.close();
			out.println(CreateXML.generateXML(1, "success","CreateTrip"));
		}
		catch(SQLException e)
		{
			out.println(CreateXML.generateXML(0, "DbProb","CreateTrip"));
			e.printStackTrace();
		}
		catch(Exception e)
		{
			out.println(CreateXML.generateXML(0, "Problem","CreateTrip"));
			e.printStackTrace();
		}
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}