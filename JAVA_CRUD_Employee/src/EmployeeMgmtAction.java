import java.sql.*;
import java.util.Scanner;
class EmployeeMgmtAction {

	String sURL="jdbc:oracle:thin:@localhost:1521:XE";
	String sUserName="tiger";
	String sPwd="tiger123";

	public void addEmployee(Employee e) {
		int id = e.getId();
		String name = e.getName();
		int age = e.getAge();
		double salary = e.getSalary();
		try {
			Connection conn = getDBConnection();
			String query="INSERT INTO Employee (id, name, age, salary) values ('"+id+"','"+name+"','"+age+"','"+salary+"')";
			System.out.println("query = "+query);
			Statement stmt = conn.createStatement();
			int n= stmt.executeUpdate(query);
			System.out.println( n +" rows inserted");
		}catch(Exception ex){
			System.out.println(ex);
		}	
	}

	public void removeEmployee(int id) {
		try {
			Connection conn = getDBConnection();
			String query = "DELETE  FROM Employee where id ='"+id+"' ";
			System.out.println("query = "+query);
			Statement stmt = conn.createStatement();
			int n= stmt.executeUpdate(query);
			System.out.println( n +" rows deleted");
		}catch(Exception ex){
			System.out.println(ex);
		}
	}

	public void displayEmployees() {
		try {
			Connection conn = getDBConnection();
			String query = "select * from employee";
			System.out.println("Query = "+query);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String salary = rs.getString("salary");
				System.out.println("\tEmp ID : "+id+"  Name : "+name+" \t age : "+age+" \t Salary : "+salary);
			}	
		}catch(Exception e){
			System.out.println(e);
		}
	}

	public void updateEmployee(){
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t\t 1 - Name Updates");
		System.out.println("\t\t\t 2 - Age Updates");
		System.out.println("\t\t\t 2 - Salary Updates");
		System.out.print("\t\t Choose Option to Updates data -->> ");
		int opt = sc.nextInt();
		String query = null;
		if(opt==1){
			System.out.print("\n\t\t\tEnter employee ID number : ");
			int id = sc.nextInt();
			System.out.print("\t\t\tEnter Names to Updates  : ");
			String name = sc.next();
			query = "UPDATE Employee SET name ='"+name+"' where id ='"+id+"' ";
		}
		else if(opt==2){
			System.out.print("\n\t\t\tEnter employee ID number : ");
			int id = sc.nextInt();
			System.out.print("\t\t\tEnter Age to updates  : ");
			int age = sc.nextInt();
			query = "UPDATE Employee SET age ='"+age+"' where id ='"+id+"' ";

		}
		else if(opt==3){
			System.out.print("\n\t\t\tEnter employee ID number : ");
			int id = sc.nextInt();
			System.out.print("\t\t\tEnter Salary to Updates  : ");
			double salary = sc.nextDouble();
			query = "UPDATE employee SET salary ='"+salary+"' where id ='"+id+"' ";
		}
		else {
			System.out.println("\n\t\t\tChoose correct option ......");
			updateEmployee();
		}

		try{
			Connection conn = getDBConnection();
			System.out.println("query = "+query);
			Statement stmt = conn.createStatement();
			int n= stmt.executeUpdate(query);
			System.out.println( n +" rows updated");
		}catch (Exception e){
			System.out.println(e);
		}
	}

	public Connection getDBConnection() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		Connection conn = DriverManager.getConnection(sURL,sUserName,sPwd);
		return conn;
	}
}