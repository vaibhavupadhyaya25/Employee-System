package mypack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class SearchDatabase
 */
@WebServlet("/SearchDatabase")
public class SearchDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchDatabase() {
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
			Scanner input = new Scanner(System.in);
			System.out.println("enter the name you want to search for:  ");
			String name12 = input.next();
			String SQL = "select * from employee where name = ?";
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(SQL);
			pst.setString(1,name12);
			ResultSet rs = pst.executeQuery();
			if(rs.next() == true)
			{
			do
			{
				System.out.println("employee profile is:  " + rs.getString(1));
				System.out.println("employee jobs is:  " + rs.getString(2));
				System.out.println("------------------------------------");
			}while(rs.next());
			System.out.println("executed");
			}
			else
			{
				System.out.println("record not found");
			}
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
