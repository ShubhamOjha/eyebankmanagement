package eyebankmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTML;

import com.dbutil.CrudeOperation;

/**
 * Servlet implementation class Donate
 */
@WebServlet("/Donate")
public class Donate extends HttpServlet {
	private static ResultSet rs=null;
	private static Connection con=null;
	private static PreparedStatement ps,psu=null;

	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Donate() {
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

		HttpSession hs=request.getSession(false);
		String uid=(String)hs.getAttribute("userdetails");
		int price=Integer.parseInt(request.getParameter("price"));
		String donorid=uid;
		String status="false";
		
		
		
		
		
	
		try
		{
		
			String strsql="select * from donor where donorid=?";
			con=CrudeOperation.createConnection();
			
				ps=con.prepareStatement(strsql);
				ps.setString(1,uid);
				rs=ps.executeQuery();
				rs.next();
				String color=rs.getString("eyecolor");
			
				String strins="insert into eye(donorid,color,price,status) values(?,?,?,?)";
			
			psu=con.prepareStatement(strins);
            psu.setString(1, donorid);
            psu.setString(2, color);
            psu.setInt(3, price);
            psu.setString(4, status);
          
            
       
            
      
            
            int rw=psu.executeUpdate();
		
		if(rw>0)
		{
			String message = "Amount has succesfully transferred";
			request.getSession().setAttribute("message", message);
		
			response.sendRedirect("/eyebankmanagement/jsp/donate.jsp");
			
	
		}
		else
		{
			response.sendRedirect("/eyebankmanagement/jsp/donor.jsp");
			/*String message = "Message failed";
			request.getSession().setAttribute("message", message);*/
		
		}
		
		
		}
		
		catch(Exception se)
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
