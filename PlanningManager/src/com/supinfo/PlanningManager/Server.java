package com.supinfo.PlanningManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;
import java.sql.SQLException;  
import java.sql.Statement;
public class Server {
	static Connection conn;    
    static Statement st; 
    
    
	public static void Regiseter(String Name , String pwd , String role)
	{
		conn = getConnection();
		try {
			
            String sql = "INSERT INTO staff(Name, Passwd , Role)"  
                    + " VALUES ("+ "'"+ Name +"'" +"," + "'"+ pwd +"'" +"," +"'" + role + "')";
              
            st = (Statement)conn.createStatement();
              
            int count = st.executeUpdate(sql);
              
            System.out.println("staff Insert" + count + " Row ");
              
            conn.close();
             
        } catch (SQLException e) {  
            System.out.println("Insert Fail !!!" + e.getMessage());
        }  
		
		
	}
	
	public static void creatProjec(String pname , String Comp, String Bdate, String Edate, String Num_em)
	{
		conn = getConnection();
		try {  
			String sql = "INSERT INTO `test`.`project` (`PName`, `Completion`, `BeginDATE`, `EndDATE`, `Num_em`)"
					+ " VALUES ('" + pname + "', '" + Comp + "', '" + Bdate + "', '" + Edate + "', '" + Num_em + "')";  
            st = (Statement) conn.createStatement();
              
            int count = st.executeUpdate(sql);
              
            System.out.println("staff Insert " + count + " Row");
              
            conn.close(); 
        } catch (SQLException e) {  
            System.out.println("Insert Fail" + e.getMessage());  
        }	
	}
	
	public static String Loginer(String name , String pwd , String role)
	{
		String aa = "Wrong Name or Password !!!!" ;
		conn = getConnection();
		try {
			
            String sql = "select * from staff" 
                    + " Where Name = " + "'"+ name +"'" 
                    + "and Passwd = " + "'" + pwd + "'" 
                    + "and Role = "+ "'"+ role +"'";
              
            st = (Statement)conn.createStatement();
              
            ResultSet rs = st.executeQuery(sql);
            
            if (rs.next())
            {
            	aa = "access!!!!!";
            	
            }
            conn.close(); 
        } catch (SQLException e) {
        	System.out.println("Query Fail !!!" + e.getMessage());  
        }
		return aa;  
		
	}
	
	public void EditProjec(String pname , String Comp, String Bdate, String Edate, String Num_em , String old_name)
	{
		conn = getConnection();
        try {  
            String sql = "update Project set PName ='" + pname + "', Completion = '" + Comp + "', "
            		+ "BeginDATE = '" + Bdate + "', EndDATE = '" + Edate + "', Num_em = '" + Num_em +"'"
            				+ " where PName = '"+ old_name + "'";
              
            st = (Statement) conn.createStatement();
            int count = st.executeUpdate(sql);
              
            System.out.println(" Project update " + count + " Rows"); 
              
            conn.close();
              
        } catch (SQLException e) {  
            System.out.println("Update Fail !!!");  
        }  
	}
	
	public void createTask(String tname, String  Bdate, String Edate, String desc, String  status, String project)
	{
		conn = getConnection();
		try {  
			String sql = "INSERT INTO `test`.`task` (`TName`, `BDate`, `EDate`, `Description`, `Status`, `PName`)"
					+ " VALUES ('" + tname + "', '" + Bdate + "', '" + Edate + "', '" + desc + "', '" + status + "', '" + project + "')";  
            st = (Statement) conn.createStatement();
              
            int count = st.executeUpdate(sql);
              
            System.out.println("task insert " + count + " Rows");
              
            conn.close(); 
        } catch (SQLException e) {  
            System.out.println("Insert Fail !!!" + e.getMessage());
        }
	}
	
	public void editTask(String T_sql , String old_name)
	{
		conn = getConnection();
		try {  
			String sql = "update task set " + T_sql + " where TName ='" +old_name + "'";
			System.out.println(sql);
            st = (Statement) conn.createStatement();
            int count = st.executeUpdate(sql);
            System.out.println("update" + count + " rows £¡£¡£¡");
            conn.close(); 
        } catch (SQLException e) {  
            System.out.println("Update Fail !!!" + e.getMessage());
        }
	}
	
	public static Connection getConnection()
	{
		Connection con = null;
		try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
            System.out.println("Connect MYsql OK !");
        } catch (Exception e) {
            System.out.println("MYSQL ERROR:" + e.getMessage());
        }
		
		return con;

	}
	
	
	public static void main(String[] args) 
	{
		System.out.println("Server Turn On !!!! \n");    
        Server server = new Server();    
        server.init();
	}


	public void init() 
	{    
	    try {    
	    	@SuppressWarnings("resource")
			ServerSocket listen = new ServerSocket(1234);
	        while (true) {     
	            Socket client = listen.accept();    
	            new HandlerThread(client);    
	        }    
	    } catch (Exception e) {    
	        System.out.println("Server Error : " + e.getMessage());    
	    }    
	}
	private class HandlerThread implements Runnable 
	{    
        private Socket socket;    
        public HandlerThread(Socket client) {    
            socket = client;    
            new Thread(this).start();    
        }
        public void run()
        {
			try {
				System.out.println("Client connection accepted : "+ socket.getInetAddress().getCanonicalHostName());
				DataInputStream input = new DataInputStream(socket.getInputStream());  
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	            String clientInputStr = input.readUTF();
	            System.out.println("Client_TxT :" + clientInputStr);
				String a[] = clientInputStr.split("#");

				if(a[0].equals("regiset"))
				{
					Regiseter(a[1], a[2], a[3]);
					out.writeUTF("Regiset OK!!!");
				}
				else if(a[0].equals("Login"))
				{
					String sentence = Loginer(a[1], a[2], a[3]);
					out.writeUTF(sentence);
					conn = getConnection();
			        try {  
			            String sql = "select * from Project";
			            st = (Statement) conn.createStatement();
			              
			            ResultSet rs = st.executeQuery(sql);
			            while(rs.next()) 
			            {
			                String name = rs.getString("PName");  
			                String Comp = rs.getString("Completion");  
			                String BDATE = rs.getString("BeginDATE");  
			                String EDATE = rs.getString("EndDATE");  
			                String MName = rs.getString("Num_em");    

			                String temp = name + "#" + Comp + "#" + BDATE + "#" + EDATE + "#" + MName ;
			                out.writeUTF(temp);
			            }
			            out.writeUTF("Project_OK");
				        conn.close();
				              
			        } catch (SQLException e) {  
			            System.out.println("Query Fail !!!");
			        }
				}
				else if (a[0].equals("Create_project"))
				{
					creatProjec(a[1],a[2],a[3],a[4],a[5]);
					out.writeUTF("Create Ok £¡£¡£¡");
				}
				else if (a[0].equals("Edit_project"))
				{
					EditProjec(a[1],a[2],a[3],a[4],a[5],a[6]);
					out.writeUTF("Update Ok £¡£¡£¡");
				}
				else if (a[0].equals("Task"))
				{
					conn = getConnection();
			        try {  
			            String sql = "select * from task where PName = " + "'" + a[1] + "'";
			            st = (Statement) conn.createStatement();
			            ResultSet rs = st.executeQuery(sql); 
			            System.out.println(sql);
			            while (rs.next()) {
			                  
			                String name = rs.getString("TName");  
			                 
			                String BDATE = rs.getString("BDate");  
			                String EDATE = rs.getString("EDate");  
			                String des = rs.getString("Description");
			                String status = rs.getString("Status");
			                String Pname = rs.getString("PName");
			                
			                String temp = name + "#" + BDATE + "#" + EDATE + "#" + des + "#" + status + "#" + Pname ;
			                System.out.println(temp);
			                
			            }
			            System.out.println("!!!!!!!!!!!!!");
			            out.writeUTF("OK");
			            conn.close();
			              
			        } catch (SQLException e) {  
			            System.out.println("Query Fail !!!" + e.getMessage());
			        }
				}
				else if (a[0].equals("Create_Task"))
				{
					
					createTask(a[1],a[2],a[3],a[4],a[5],a[6]);
					out.writeUTF("OK");

				}
				else if(a[0].equals("Edit_Task"))
				{
					editTask(a[1] , a[2]);
					out.writeUTF("OK");
				}
				input.close();
				out.close();
				
				} catch (IOException e) {
					System.out.println("Fail !!!" + e.getMessage());
				} finally {    
					if (socket != null) 
					{    
						try {    
							socket.close();    
							} 
						catch (Exception e) 
							{    
								socket = null;    
								System.out.println("Server finally Error :" + e.getMessage());    
							}
		
					}
				}
        }
	}
}
	
	
	

