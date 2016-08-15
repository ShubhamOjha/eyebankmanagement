package eyebankmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dbutil.CrudeOperation;

/**
 * Servlet implementation class Payment
 */
@WebServlet("/Payment")
public class Payment extends HttpServlet {
	private static ResultSet rs=null;
	private static Connection con=null;
	private static PreparedStatement ps,psu=null;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payment() {
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
		HttpSession hs=request.getSession(false);
		String uid=(String)hs.getAttribute("userdetails");
		
		
		int eyeid=Integer.parseInt(request.getParameter("eyeid"));
		
		
		
		String status="true";
		String strupdate="update eye set recipientid=?,status=? where eyeid=?";
		con=CrudeOperation.createConnection();
		
		try
		{
			ps=con.prepareStatement(strupdate);
			ps.setString(1, uid);
			ps.setString(2, status);
			ps.setInt(3, eyeid);
			int rw=ps.executeUpdate();
		
		if(rw>0)
		{
			String message = "Payment Successful";
			request.getSession().setAttribute("message", message);
			
			response.sendRedirect("/eyebankmanagement/html/paysuccess.html");
			
	
		}
		
		
		}
		
		catch(SQLException se)
		{
			System.out.println(se);
		}
		finally
		{  try{
			if(ps!=null)
			
			{
				ps.close();
				
			}}
			catch(SQLException e)
			     {
				System.out.println(e);
			      }
				
			}
	}

}
