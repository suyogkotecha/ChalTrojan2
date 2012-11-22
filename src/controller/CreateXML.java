package controller;

import java.sql.ResultSet;

import com.mysql.jdbc.ResultSetMetaData;

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
			ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData();
			int cols = meta.getColumnCount();
			int i=0;
			sb.append("<trips>");
			while(rs.next())
			{
				sb.append("<trip>").append(rs.getString("tripName")).append("</trip>");
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
}
