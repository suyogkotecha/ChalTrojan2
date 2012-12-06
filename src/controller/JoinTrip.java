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

import com.mysql.jdbc.ResultSetMetaData;

/**
 * Servlet implementation class for Servlet: JoinTrip
 *
 */
 public class JoinTrip extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public JoinTrip() {
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
		String pwd = request.getParameter("pwd");
		
		//Check if password for tripId
		
		//out.print(query);
		int colCount=0;
		try
		{
			String query="select count(*) from trip_owner where tripName = '"+tripId+"' and tripPwd='"+pwd+"'";
			Connection con = db.Instance.returnConnection();
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			ResultSetMetaData meta = (ResultSetMetaData) resultSet.getMetaData();
			int cols = meta.getColumnCount();
			resultSet.next();
			
			for  (int i = 1; i<= cols; i++){
				colCount = Integer.parseInt(resultSet.getString(i));				
			}						
			if(colCount == 0)//User present, return success
			{
				out.println(CreateXML.generateXML(0, "PwdWrong","JoinTrip"));
				con.close();
				return;
			}
			con.close();
		}
		catch(SQLException e)
		{
			out.println(CreateXML.generateXML(0, "DbProblem","JoinTrip"));
			e.printStackTrace();
		}
		catch(Exception e)
		{
			out.println(CreateXML.generateXML(0, "Problem","JoinTrip"));
			e.printStackTrace();
		}
		if(colCount == 1)
		{
			int ifUserExist=0;
			
			if(CreateXML.userPresent(out, handle, "trip",tripId,"JoinTrip"))
			{
				//update; make it active
				Connection con = null;
				String query="update feedback.trip set active=1 where tripId in (select idtrip_owner from trip_owner where tripName='"+tripId+"') and handle='"+handle+"'";
				try
				{		
					con = db.Instance.returnConnection();
					Statement statement = con.createStatement();			
					int update = statement.executeUpdate(query);
					if(update==0)
					{
						out.println(CreateXML.generateXML(0, "DbProblem","JoinTrip"));
					}
					else
					{
						out.println(CreateXML.generateXML(1, "success","JoinTrip"));
					}
				}
				catch(SQLException e)
				{
					out.println(CreateXML.generateXML(0, "DbProblem","JoinTrip"));
					e.printStackTrace();
				}
				catch(Exception e)
				{
					out.println(CreateXML.generateXML(0, "Problem","JoinTrip"));
					e.printStackTrace();
				}
				finally
				{
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						out.println(CreateXML.generateXML(0, "DbProblem","JoinTrip"));
						e.printStackTrace();
					}
				}
			}
			else
			{
				//insert user
				insertUser(handle, tripId,out);
			}
		}
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void insertUser(String handle, String tripId, PrintWriter out)
	{
		Connection con=null;
		int update=0;
		try
		{
			String query1 = "INSERT INTO trip(tripid, handle,active) values ( (select idtrip_owner from trip_owner where tripName='"+tripId+"'),'"+handle+"',1)";
			//out.println(query1);
			con = db.Instance.returnConnection();
			Statement statement = con.createStatement();
			//out.println(query1);
			update = statement.executeUpdate(query1);
			if(update==0)
			{
				out.println(CreateXML.generateXML(0, "DbProblemNotUpdated","JoinTripInsertUser"));
			}
			else				
			{
				out.println(CreateXML.generateXML(1, "Success","TripJoined"));
			}
		}
		catch(SQLException e)
		{
			out.println(CreateXML.generateXML(0, "DbProblem","JoinTripUpsert"));
			e.printStackTrace();
		}
		catch(Exception e)
		{
			out.println(CreateXML.generateXML(0, "Problem","JoinTripUpsertConClose"));
			e.printStackTrace();
		}
		finally
		{
			try {
				//System.out.println("Connection closed");
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.println(CreateXML.generateXML(0, "DbProblem","JoinTripUpsertConClose"));
				e.printStackTrace();
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}