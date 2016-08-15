package eyebankmanagement;
import com.dbutil.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	
	private Connection con=null;
	private PreparedStatement ps=null;
	
	private ResultSet rs=null;
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		response.setContentType("text/html");
		String ui=request.getParameter("txtid");
		String upass=request.getParameter("pwd");
		
		
	
		String status="true";
		
		String strsql="select * from logindetails where userid=? and userpass=? and status=?";
		con=CrudeOperation.createConnection();
		try{
			
			ps=con.prepareStatement(strsql);
			ps.setString(1,ui);
			ps.setString(2,upass);
			ps.setString(3,status);
			rs=ps.executeQuery();
			// boolean b=rs.next();
			if(rs.next())
			{
				HttpSession hs=request.getSession();
				hs.setAttribute("userdetails", ui);
				String ut=rs.getString("usertype");
	
				
				if(ut.equals("admin"))
						{
							response.sendRedirect("/eyebankmanagement/jsp/admin.jsp");
						}
				if(ut.equals("donor"))
						{
							response.sendRedirect("/eyebankmanagement/jsp/donor.jsp");
						}
				if(ut.equals("recipient"))
						{
							response.sendRedirect("/eyebankmanagement/jsp/recipient.jsp");
						}
				
				
				
			}
				else
						{
							response.sendRedirect("/eyebankmanagement/html/login.html? msg= INVALID USER ID OR PASSWORD");
						}
			  
			}
			
			catch (SQLException se)
			{
				System.out.println(se);
			
			}
			
		finally 
			{
				try {
						if(rs!=null)
							{
								rs.close();
							}
						
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
