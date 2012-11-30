package controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.ResultSetMetaData;

//import com.mysql.jdbc.ResultSetMetaData;

public class CreateXML {
	public static String generateXML(int success, String message, String from)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<root><header><success>").append(success).append("</success><message>");
		sb.append(message).append("</message><from>").append(from).append("</from></header><data></data></root>");
		return sb.toString();
	}
	public static String generateTripXML(ResultSet rs, int success)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<root><header><success>").append(1).append("</success><message>");
		sb.append("TripCreated").append("</message><from>").append("CreateTrip").append("</from></header><data>");
		try
		{
			/*ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData();*/
			/*int cols = meta.getColumnCount();
			int i=0;*/
			sb.append("<trips>");
			while(rs.next())
			{
				sb.append("<trip id=\"").append(rs.getString("idtrip_owner")).append("\">").append(rs.getString("tripName")).append("</trip>");
			}
			sb.append("</trips>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		sb.append("</data></root>");
		return sb.toString();
	}
	public static String generateTripDetailsXML(ResultSet rs)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<root><header><success>").append(1).append("</success><message>");
		sb.append("TripDetails").append("</message><from>").append("GetTripDetails").append("</from></header><data>");
		
		try
		{
			rs.next();
			sb.append("<Details>");
			sb.append("<destName>").append(rs.getString("destName")).append("</destName>");
			sb.append("<tripDate>").append(rs.getString("tripDate")).append("</tripDate>");
			sb.append("<tripPwd>").append(rs.getString("tripPwd")).append("</tripPwd>");
			sb.append("<active>").append(rs.getString("active")).append("</active>");
			sb.append("</Details>");
		}
		catch(Exception e)
		{
			sb = new StringBuffer();
			sb.append("<root><header><success>").append(0).append("</success><message>");
			sb.append("TripDetails").append("</message><from>").append("GetTripDetails").append("</from></header><data>");
			e.printStackTrace();
		}
		sb.append("</data></root>");
		return sb.toString();
	}
	public static boolean userPresent(PrintWriter out, String handle, String table, String className)
	{
		String query = "select count(*) from feedback."+table+" where handle="+handle;
		if(returnCount(query, className,out)==1)
			return true;
		else
			return false;
	}
	public static boolean userPresent(PrintWriter out, String handle, String table, String tripId, String className)
	{
		String query = "select count(*) from feedback."+table+" where handle='"+handle+"' and tripid="+tripId;
		if(returnCount(query, className,out)==1)
			return true;
		else
			return false;
	}
	public static int returnCount(String query, String className, PrintWriter out)
	{
		
		int count=0;
		Connection con = null;
		try
		{			
			con = db.Instance.returnConnection();
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			ResultSetMetaData meta = (ResultSetMetaData) resultSet.getMetaData();
			int cols = meta.getColumnCount();
			resultSet.next();
		
			for  (int i = 1; i<= cols; i++)
			{
				count = Integer.parseInt(resultSet.getString(i));				
			}
		}
		catch(SQLException e)
		{
			out.println(CreateXML.generateXML(0, "DbProblem",className));
			e.printStackTrace();
		}
		catch(Exception e)
		{
			out.println(CreateXML.generateXML(0, "Problem",className));
			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
}
