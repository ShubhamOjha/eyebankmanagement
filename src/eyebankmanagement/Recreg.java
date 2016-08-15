package eyebankmanagement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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

import com.dbutil.CrudeOperation;

/**
 * Servlet implementation class Recreg
 */
@WebServlet("/Recreg")
public class Recreg extends HttpServlet {
	private static ResultSet rs=null;
	private static Connection con=null;
	private static PreparedStatement ps,psu=null;
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recreg() {
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

		String un=request.getParameter("txtnm");
		String upass=request.getParameter("pwd");
		String uid=request.getParameter("txtid");
		String em=request.getParameter("txtem");
		int ph=Integer.parseInt(request.getParameter("txtno"));
		String rdg=request.getParameter("rgender");
		String add=request.getParameter("add");
		String ftnm=request.getParameter("ftnm");
		String mtnm=request.getParameter("mtnm");
		String dob = request.getParameter("dob");
		
		
            con=CrudeOperation.createConnection();
		String usertype="recipient";
		String status="true";
		
		String strins="insert into logindetails values(?,?,?,?)";
		String strinsert="insert into recipient values(?,?,?,?,?,?,?,?,?,?)";//place holder
		try
		{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dt=sdf.parse(dob);
			java.sql.Date sddob=new java.sql.Date(dt.getTime());
			
			
			java.util.Date d=new java.util.Date();
			java.sql.Date sd=new java.sql.Date(d.getTime());
			
			
			psu=con.prepareStatement(strins);
			ps=con.prepareStatement(strinsert);
            ps.setString(1, uid);
            ps.setString(2, un);
            ps.setString(3, add);
            ps.setString(4, em);
            ps.setString(5, rdg);
            ps.setDate(6, sd);
            ps.setDate(7, sddob);
            ps.setInt(8, ph);
            ps.setString(9, ftnm);
            ps.setString(10, mtnm);
            
            
            
            psu.setString(1, uid);
            psu.setString(2, upass);
            psu.setString(3, usertype);
            psu.setString(4, status);
		
		int rs=ps.executeUpdate();
		int rw=psu.executeUpdate();
		if(rs>0&&rw>0)
		{
			
			response.sendRedirect("/eyebankmanagement/html/login.html");
			
	
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
