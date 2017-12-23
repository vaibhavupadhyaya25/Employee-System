package mypack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/DisplayDatabase")
public class DisplayDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayDatabase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("class successfully loaded");
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/employeerecords","root","admin");
			System.out.println("successfully connected");
			String SQL = "select * from employee";
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			while(rs.next())
			{
				out.println("The records are as follows");
				out.println("Employee name is:  " + rs.getString(1) + "  ,  ");
				out.println("Employee job is:  " + rs.getString(2) + "  ,  ");
				out.println("Employee salary is:  " + rs.getString(3) + "    ");
				out.println("<br>");
			}
			System.out.println("executed");
			out.println("<html>");
			out.println("<head>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h5>To return to to index page <a href = 'Index.html'>Click here</a></h5>");
			out.println("</body>");
			out.println("</html>");
			
			
			
			conn.close();
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e);
			
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
