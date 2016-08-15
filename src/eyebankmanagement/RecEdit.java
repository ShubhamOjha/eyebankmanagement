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
 * Servlet implementation class RecEdit
 */
@WebServlet("/RecEdit")
public class RecEdit extends HttpServlet {
	private static ResultSet rs=null;
	private static Connection con=null;
	private static PreparedStatement ps,psu=null;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecEdit() {
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
		String em=request.getParameter("txtem");
		String ph=request.getParameter("txtph");
		String add=request.getParameter("add");
		String ftnm=request.getParameter("ftnm");
		String mtnm=request.getParameter("mtnm");
		
		
		String strupdate="update recipient set email=?,phoneno=?,address=?,fname=?,mname=? where recipientid=?";
		con=CrudeOperation.createConnection();
		try
		{
			ps=con.prepareStatement(strupdate);
			ps.setString(1, em);
			ps.setString(2, ph);
			
			ps.setString(3, add);
			ps.setString(4, ftnm);
			ps.setString(5, mtnm);
			ps.setString(6, uid);
			int rw=ps.executeUpdate();
		
		if(rw>0)
		{
			
			response.sendRedirect("/eyebankmanagement/jsp/recview.jsp");
			
	
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
