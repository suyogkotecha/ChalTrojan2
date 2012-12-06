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
 * Servlet implementation class for Servlet: GetLocation
 *
 */
 public class GetLocation extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public GetLocation() {
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
		String query1="select owner from trip_owner where tripName ='"+tripId+"'";
		String query2 = "select handle from trip where tripId in(select idtrip_owner from trip_owner where tripName='"+tripId+"')";
		Connection con = null;		
			try
			{		
				con = db.Instance.returnConnection();
				Statement statement = con.createStatement();			
				ResultSet rs1= statement.executeQuery(query1);
				//out.println(query2);
				ResultSet rs2 = con.createStatement().executeQuery(query2);
				
				String listHandles = CreateXML.returnListHandles(rs1, rs2, handle);
//				out.println(listHandles);
				String query3 = "select lat, lng, handle from location where idlocation in (select max(idlocation) from location group by handle having handle in"+listHandles+")" ;
				//out.println(query3);
				ResultSet rs3 = con.createStatement().executeQuery(query3);
				out.print(CreateXML.locationXML(rs3));
			}
			catch(SQLException e)
			{
				out.println(CreateXML.generateXML(0, "DbProblem","GetLocation"));
				e.printStackTrace();
			}
			catch(Exception e)
			{
				out.println(CreateXML.generateXML(0, "Problem","GetLocation"));
				e.printStackTrace();
			}
			finally
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					out.println(CreateXML.generateXML(0, "DbProblem","GetLocation"));
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