package eyebankmanagement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbutil.*;

/**
 * Servlet implementation class DonStatus
 */
@WebServlet("/DonStatus")
public class DonStatus extends HttpServlet {
	Connection con=null;
	PreparedStatement ps=null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String[] accarr=request.getParameterValues("chk");
		
		String strupdate="update logindetails set status=? where userid=?";
		con=CrudeOperation.createConnection();
		String status="true";
		int rw=-1;
		try
		{ 
			ps=con.prepareStatement(strupdate);
			for(int i=0;i<accarr.length;i++)
			{
				ps.setString(1,status);
				ps.setString(2, accarr[i]);
				 
				
			}
			rw=ps.executeUpdate();
			if(rw>0)
			{
				response.sendRedirect("/eyebankmanagement/jsp/donstatus.jsp");
			}
			
		
		
		
			
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
		
		finally
		{
			try{
				if(ps!=null)
				{
					ps.close();
				}
				}
			
			catch(SQLException se)
			{
				System.out.println(se);
			}
		}
	}

}
