package com.supinfo.PlanningManager;

import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.*;
	

@SuppressWarnings("serial")
public class Client extends JFrame
{
	Scanner scan = new Scanner(System.in);
	public String L_user;
	public String L_PWD;
	public String staff_role;
	public JPanel pnluser;
	public JLabel lbluserLogIn;
	public JLabel lbluserName;
	public JLabel lbluserPWD;
	public JTextField txtName;
	public JPasswordField pwdPwd;
	public JButton btnSub;
	public JButton btnRegister;
	public JRadioButton Manager = new JRadioButton("Manager" , true);
	public JRadioButton employee = new JRadioButton("employee" , false);
	public ButtonGroup bg = new ButtonGroup();
	public Client()
	{
		pnluser = new JPanel();
		lbluserLogIn = new JLabel();
		lbluserName = new JLabel();
		lbluserPWD = new JLabel();
		txtName = new JTextField();
		pwdPwd = new JPasswordField();
		btnSub = new JButton();
		btnRegister = new JButton();
		userInit();
	}
		 
	public void userInit()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300,300);
		this.setResizable(false);
		this.setTitle("LOGIN");
		this.pnluser.setLayout(null);
		this.pnluser.setBackground(Color.white);
		this.lbluserLogIn.setText("Login");
		this.lbluserLogIn.setFont(new Font("宋体",Font.BOLD | Font.ITALIC,14));
		this.lbluserLogIn.setForeground(Color.RED);
		this.lbluserName.setText("User:");
		this.lbluserPWD.setText("Password:");
		this.btnSub.setText("Sub");
		this.btnRegister.setText("Regist");
		this.lbluserLogIn.setBounds(120,15,60,20);
		this.lbluserName.setBounds(50,55,60,20);
		this.lbluserPWD.setBounds(40,85,70,25);
		this.txtName.setBounds(110,55,120,20);
		this.pwdPwd.setBounds(110,85,120,20);
		this.Manager.setBounds(55, 120, 100, 20);
		this.Manager.addActionListener(new ActionListener()
		{
	    public void actionPerformed(ActionEvent e)
	    {
	     	staff_role = "Manager";
	    }
		}
		); 
		this.employee.setBounds(155, 120, 100, 20);
		this.employee.addActionListener(new ActionListener()
		{
	    public void actionPerformed(ActionEvent e)
	    {
	     	staff_role = "Employee";
	    }
		}
		); 
		this.btnSub.setBounds(85,160,60,20);
		this.btnSub.addActionListener(new ActionListener()
		{
	    public void actionPerformed(ActionEvent e)
	    {
	    	btnsub_ActionEvent(e);
	    }    
		}
		); 
		this.btnRegister.setBounds(155,160,70,20);
		this.btnRegister.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				btnRegister_ActionEvent(e);
	    }    
		}
		);   
		this.pnluser.add(lbluserLogIn);
		this.pnluser.add(lbluserName);
		this.pnluser.add(lbluserPWD);
		this.pnluser.add(txtName);
		this.pnluser.add(pwdPwd);
		this.pnluser.add(btnSub);
		this.pnluser.add(btnRegister);
        this.bg.add(Manager);
        this.bg.add(employee);
        this.pnluser.add(Manager);  
        this.pnluser.add(employee);
        pnluser.updateUI();
		this.add(pnluser);
		this.setVisible(true);
	}
		 
	public void btnsub_ActionEvent(ActionEvent e)
	{
		String name = txtName.getText();
		String pwd = String.valueOf(pwdPwd.getPassword());
		if(name.equals(""))
		{
			JOptionPane.showMessageDialog(null,"User is empty!!!!","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if (pwd.equals(""))
		{
		   JOptionPane.showMessageDialog(null,"Password is empty!!!!","Error",JOptionPane.ERROR_MESSAGE);
		   return;
		}
		else if(true)
		{
		   Login_socket(name , pwd , this.staff_role );
		}
	}
		 
	public void btnRegister_ActionEvent(ActionEvent e)
	{
		
		pnluser.removeAll();
		JLabel New_LogIn = new JLabel();
		JLabel New_Name = new JLabel();
		JLabel New_PWD = new JLabel();
		JLabel role = new JLabel();
		final JTextField txtrole = new JTextField(); 
		final JTextField New_txtName = new JTextField();
		final JPasswordField New_pwdPwd = new JPasswordField();
		JButton btnCre = new JButton();
		this.setTitle("Regist");
		pnluser.setLayout(null);
		pnluser.setBackground(Color.white);
		New_LogIn.setText("Regist");//设置标签标题
		New_LogIn.setFont(new Font("宋体",Font.BOLD | Font.ITALIC,14));//设置标签字体
		New_LogIn.setForeground(Color.RED);
		New_Name.setText("User:");
		New_PWD.setText("Password:");
		role.setText("Role:");
		btnCre.setText("Sub");
		New_LogIn.setBounds(120,15,60,20);
		New_Name.setBounds(50,55,60,20);
		New_PWD.setBounds(40,85,70,25);
		New_txtName.setBounds(110,55,120,20);
		New_pwdPwd.setBounds(110,85,120,20);
		role.setBounds(50, 120, 100, 20);
		txtrole.setBounds(110,120,120,20);
		btnCre.setBounds(85,160,60,20);
		btnCre.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
		    	String name = New_txtName.getText();
		    	String pwd = String.valueOf(New_pwdPwd.getPassword());
		    	String Role = txtrole.getText();
		    	if(name.equals(""))
				{
					JOptionPane.showMessageDialog(null,"User is empty!!!!","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if (pwd.equals(""))
				{
				   JOptionPane.showMessageDialog(null,"Password is empty!!!!","Error",JOptionPane.ERROR_MESSAGE);
				   return;
				}
				else if (pwd.equals(""))
				{
				   JOptionPane.showMessageDialog(null,"Role is empty!!!!,Please Input 'Manager' or  'Employee'","Error",JOptionPane.ERROR_MESSAGE);
				   return;
				}
				else if ((!Role.equals("Manager"))&&(!Role.equals("Employee")))
				{
				   JOptionPane.showMessageDialog(null,"Please Input 'Manager' or  'Employee'","Error",JOptionPane.ERROR_MESSAGE);
				   return;
				}
				else if(true)
				{
					Socket(name , pwd , Role);
					JOptionPane.showMessageDialog(null,"OK");
					pnluser.removeAll();
					userInit();
				}
			}
		}
		);
		
		pnluser.add(New_LogIn);
		pnluser.add(New_Name);
		pnluser.add(New_PWD);
		pnluser.add(New_txtName);
		pnluser.add(New_pwdPwd);
		pnluser.add(btnCre);
		pnluser.add(role);  
        pnluser.add(txtrole);
        pnluser.updateUI();
		this.add(pnluser);
		this.setVisible(true);
	}
	
	public static void Socket(String name , String pwd, String Role)
	{
		Socket socket = null;
	 try {  

            socket = new Socket("localhost", 1234);    
            DataInputStream input = new DataInputStream(socket.getInputStream());    
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());    

            out.writeUTF("regiset#" + name + "#" + pwd + "#" + Role);
                
            String ret = input.readUTF();     
            System.out.println(ret);    

            out.close();  
            input.close();
            socket.close();
        } catch (Exception e) {  
            System.out.println("Client Error:" + e.getMessage());   
        }
	}
	public void Login_socket(String name , String pwd , String role )
	{
		Socket socket = null;
	 try {  

            socket = new Socket("localhost", 1234);    

            DataInputStream input = new DataInputStream(socket.getInputStream());    
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());    

            out.writeUTF("Login#" + name + "#" + pwd + "#" + role);
            String ret = input.readUTF();
        	System.out.println(ret);
        	String temp_a[] = ret.split("#");
        	if(temp_a[0].equals("access!!!!!"))
        	{
        		L_user = name ;
        		L_PWD = pwd ;
        		pnluser.removeAll();
        		int High = 0;
        		while(true)
        		{
        			String Pjec_t = input.readUTF();
        			if(Pjec_t.equals("Project_OK"))
        			{
        				break;
        			}
        			if(staff_role.equals("Manager"))
        			{
        				Pject_board_M(Pjec_t , High);
        				High = High + 30;
        			}
        			else if (staff_role.equals("Employee"))
        			{
        				Pject_board_E(Pjec_t , High);
        				High = High + 30;
        			}
        		}
        	}
            out.close();  
            input.close();
            socket.close();
        } catch (Exception e) {  
            System.out.println("Client Error:" + e.getMessage());
        }
	}

	public void socket_Create_project(String pname, String txt_Comp, String txt_Bdate, String txt_Edate, String txt_Number)
	{
		Socket socket = null;
		 try {  

	            socket = new Socket("localhost", 1234);    

	            DataInputStream input = new DataInputStream(socket.getInputStream());    
	            DataOutputStream out = new DataOutputStream(socket.getOutputStream());    

	            out.writeUTF("Create_project#" + pname + "#" + txt_Comp + "#" + txt_Bdate + "#" + txt_Edate + "#" + txt_Number);
	                
	            String ret = input.readUTF();     
	            System.out.println(ret);
	            out.close();  
	            input.close();
	            socket.close();
	        } catch (Exception e) {  
	            System.out.println("Client Error:" + e.getMessage());   
	        }
		
	}
	
	//----------------------------------------------------------------------
	public void socket_Edit_project(String pname, String Comp, String Bdate, String Edate, String Number , String old_name)
	{
		Socket socket = null;
		 try {

	            socket = new Socket("localhost", 1234);
	            DataInputStream input = new DataInputStream(socket.getInputStream());    
	            DataOutputStream out = new DataOutputStream(socket.getOutputStream());       
	            out.writeUTF("Edit_project#" + pname + "#" + Comp + "#" + Bdate + "#" + Edate + "#" + Number + "#" + old_name);
	                
	            String ret = input.readUTF();     
	            System.out.println(ret);    
	            out.close();  
	            input.close();
	            socket.close();
	        } catch (Exception e) {  
	            System.out.println("Client Error :" + e.getMessage());   
	        }
		
		
	}

	public void Pject_board_M(String Pjec , int High )
	{
		final String M[] = Pjec.split("#");
		JLabel TP = new JLabel();
		JLabel TC = new JLabel();
		JLabel TB = new JLabel();
		JLabel TE = new JLabel();
		JLabel TM = new JLabel();
		JButton Create = new JButton();
		JLabel comp1 = new JLabel();
		JLabel Bdate1 = new JLabel();
		JLabel Edate1 = new JLabel();
		JButton proj1 = new JButton();
		JButton Manage1 = new JButton();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setResizable(false);
		this.setTitle("Project Dashboard");
		pnluser.setLayout(null);
		pnluser.setBackground(Color.white);
		TP.setText("    Project");
		TP.setBounds(10, 10, 120, 20);
		TC.setText("Comp");
		TC.setBounds(130, 10, 50, 20);
		TB.setText("BeginDate");
		TB.setBounds(190, 10, 100, 20);
		TE.setText("EndDate");
		TE.setBounds(300, 10, 100, 20);
		TM.setText("Edit");
		TM.setBounds(410, 10, 100, 20);
		Create.setText("Create");
		Create.setBounds(40, 400, 85, 20);
		Create.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				CreateProject();
	    }    
		}
		);
		proj1.setText(M[0]);
		proj1.setBounds(10, 40+High, 100, 20);
		proj1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				socket_Show_Task(M[0]);
	    }    
		}
		);
		comp1.setText(M[1]);
		comp1.setBounds(130, 40+High, 50, 20);
		Bdate1.setText(M[2]);
		Bdate1.setBounds(190, 40+High, 100, 20);
		Edate1.setText(M[3]);
		Edate1.setBounds(300, 40+High, 100, 20);
		Manage1.setText("Edit");
		Manage1.setBounds(410, 40+High, 60, 20);
		Manage1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				Swing_Edit_project(M[0],M[1],M[2],M[3],M[4]);
	    }    
		}
		);
		
		pnluser.add(TP);
		pnluser.add(TC);
		pnluser.add(TB);
		pnluser.add(TE);
		pnluser.add(TM);
		pnluser.add(Create);
		pnluser.add(proj1);
		pnluser.add(comp1);
		pnluser.add(Bdate1);
		pnluser.add(Edate1);
		pnluser.add(Manage1);
		pnluser.updateUI();
		this.add(pnluser);
		this.setVisible(true);
	}
	public void Pject_board_E(String Pjec , int High )
	{
		final String M[] = Pjec.split("#");
		JLabel TP = new JLabel();
		JLabel TC = new JLabel();
		JLabel TB = new JLabel();
		JLabel TE = new JLabel();
		JLabel TM = new JLabel();
		JLabel comp1 = new JLabel();
		JLabel Bdate1 = new JLabel();
		JLabel Edate1 = new JLabel();
		JButton proj1 = new JButton();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setResizable(false);
		this.setTitle("Project Dashboard");
		pnluser.setLayout(null);
		pnluser.setBackground(Color.white);
		TP.setText("    Project");
		TP.setBounds(10, 10, 120, 20);
		TC.setText("Comp");
		TC.setBounds(130, 10, 50, 20);
		TB.setText("BeginDate");
		TB.setBounds(190, 10, 100, 20);
		TE.setText("EndDate");
		TE.setBounds(300, 10, 100, 20);
		TM.setText("Edit");
		TM.setBounds(410, 10, 100, 20);
		proj1.setText(M[0]);
		proj1.setBounds(10, 40+High, 100, 20);
		proj1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				socket_Show_Task(M[0]);
	    }    
		}
		);
		comp1.setText(M[1]);
		comp1.setBounds(130, 40+High, 50, 20);
		Bdate1.setText(M[2]);
		Bdate1.setBounds(190, 40+High, 100, 20);
		Edate1.setText(M[3]);
		Edate1.setBounds(300, 40+High, 100, 20);
		
		pnluser.add(TP);
		pnluser.add(TC);
		pnluser.add(TB);
		pnluser.add(TE);
		pnluser.add(TM);
		pnluser.add(proj1);
		pnluser.add(comp1);
		pnluser.add(Bdate1);
		pnluser.add(Edate1);
		pnluser.updateUI();
		this.add(pnluser);
		this.setVisible(true);
		
	}
		 
	public void Swing_Edit_project( final String x1, final String x2, final String x3, final String x4, final String x5)
	{
		pnluser.removeAll();
		JLabel Pname = new JLabel();
		final JTextField E_txt_pname = new JTextField(x1);
		JLabel Comp = new JLabel();
		final JTextField E_txt_Comp = new JTextField(x2);
		JLabel a = new JLabel();
		JLabel Bdate = new JLabel();
		final JTextField E_txt_Bdate = new JTextField(x3);
		JLabel b = new JLabel();
		JLabel Edate = new JLabel();
		final JTextField E_txt_Edate = new JTextField(x4);
		JLabel c = new JLabel();
		JLabel Number = new JLabel();
		final JTextField E_txt_Number= new JTextField(x5);
		JButton sub = new JButton();
		
		Pname.setText("Project Name:");
		Pname.setBounds(40, 40, 120, 20);
		E_txt_pname.setBounds(170, 40, 120, 20);
		Comp.setText("Completion:");
		Comp.setBounds(40, 70, 120, 20);
		E_txt_Comp.setBounds(170, 70, 120, 20);
		a.setText("(Please enter the format like 'x%' !)");
		a.setBounds(170, 90, 300, 20);
		Bdate.setText("Begin Date:");
		Bdate.setBounds(40, 110, 120, 20);
		E_txt_Bdate.setBounds(170, 110, 120, 20);
		b.setText("(Please enter the format like '9999-12-30' !)");
		b.setBounds(170, 130, 300, 20);
		Edate.setText("End Date:");
		Edate.setBounds(40, 150, 120, 20);
		E_txt_Edate.setBounds(170, 150, 120, 20);
		c.setText("(Please enter the format like '9999-12-30' !)");
		c.setBounds(170, 170, 300, 20);
		Number.setText("Number of Em:");
		Number.setBounds(40, 200, 120, 20);
		E_txt_Number.setBounds(170, 200, 120, 20);
		sub.setText("Sub");
		sub.setBounds(40, 400, 85, 20);
		sub.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				//----------------------------------------------------------
				String pname = E_txt_pname.getText();
				String Comp = E_txt_Comp.getText();
				String Bdate = E_txt_Bdate.getText();
				String Edate = E_txt_Edate.getText();
				String Number = E_txt_Number.getText();
				if((!pname.equals(x1))||(!Comp.equals(x2))||(!Bdate.equals(x3))||(!Edate.equals(x4))||(!Number.equals(x5)))
				{
					socket_Edit_project( pname, Comp, Bdate, Edate, Number , x1);
					JOptionPane.showMessageDialog(null,"Update Ok ！！");
					Login_socket(L_user, L_PWD, staff_role);
				}
				
	    }
		}
		);
		pnluser.add(Pname);
		pnluser.add(E_txt_pname);
		pnluser.add(Comp);
		pnluser.add(E_txt_Comp);
		pnluser.add(a);
		pnluser.add(Bdate);
		pnluser.add(E_txt_Bdate);
		pnluser.add(b);
		pnluser.add(Edate);
		pnluser.add(E_txt_Edate);
		pnluser.add(c);
		pnluser.add(Number);
		pnluser.add(E_txt_Number);
		pnluser.add(sub);
		
		pnluser.updateUI();
		this.add(pnluser);
		this.setVisible(true);
		
		
		
		
		
	}
	public void socket_Show_Task(String project) 
	{
		Socket socket = null;
		 try {  

	            socket = new Socket("localhost", 1234);    

	            DataInputStream input = new DataInputStream(socket.getInputStream());    
	            DataOutputStream out = new DataOutputStream(socket.getOutputStream());     
	            out.writeUTF("Task#" + project);
	            System.out.println(" Task\t    Begin Time\t\t    End Time\t\t Status\t PName \t Author");
	            while(true)
	            {
	            	String ret = input.readUTF();
	            	if(ret.equals("OK"))
	            	{
	            		break;
	            	}
	            	String Tk[] = ret.split("#");
	            	System.out.println(Tk[0]+"\t"+Tk[1]+"\t"+Tk[2]+"\t  "+Tk[4]+"\t "+Tk[5]+ "\t"+ L_user);
	            	System.out.println("\t" + Tk[3]);
	            }
	            out.close();  
	            input.close();
	            socket.close();
	            socket_create_Task(project);
	        } catch (Exception e) {  
	            System.out.println("Client Error :" + e.getMessage());   
	        }
	}

	public void socket_create_Task(String project)
	{
		Scanner scan = new Scanner(System.in);
		Socket socket = null;
		 try {  

	            socket = new Socket("localhost", 1234);        
	            DataInputStream input = new DataInputStream(socket.getInputStream());
	            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				System.out.println("1.Create new Task");
		        System.out.println("2.Edit Task");
		        System.out.println("3.Delete Task");
		        System.out.println("4.Exit");
		        System.out.println("Please Put the Number ( '1' , '2' , '3' or '4')");
		        int chose = scan.nextInt();
		        if(chose == 1)
		        {	
		        	scan.nextLine();
		        	System.out.println("input Task Name ：");
		        	String tname = scan.nextLine();
		        	System.out.println("input Task BeginDate(The type Like '9999-12-30 23:59:59') ：");
		        	String Bdate = scan.nextLine();
		        	System.out.println("input Task EndDate(The type Like '9999-12-30 23:59:59') ：");
		        	String Edate = scan.nextLine();
		        	System.out.println("input Description :");
		        	String desc = scan.nextLine();
		        	System.out.println("input Task Status('Completed' or 'Not') ：");
		        	String status = scan.nextLine();
		        	
		        	out.writeUTF("Create_Task#" + tname + "#" + Bdate + "#" + Edate+ "#" + desc+ "#" + status+ "#" + project );
		        
		        }
		        else if (chose == 2)
		        {
		        	scan.nextLine();
		        	System.out.println("Which Task do you want to Modify ? ");
		        	System.out.print("Please Input Task Name :");
		        	String Tname = scan.nextLine();
		        	System.out.println("Please Choose Where do you want to change \n,"
		        			+ "'TName' , 'BDate' , 'EDate' , 'Description' , 'Status'");
		        	System.out.println("Use the Type like <TName = 'XXXXX'> to change the value!!\n "
		        			+ "You can choose More then One , use  ',' to separated them!!!");
		        	String Tsql = scan.nextLine();
		        	out.writeUTF("Edit_Task#" + Tsql + "#" + Tname  );

		        }
		        out.close();  
	            input.close();
	            socket.close();
		        if(chose != 4)
		        {
		        	socket_Show_Task(project);
		        }
		        else
		        	Login_socket(L_user, L_PWD, staff_role);
       	
	        } catch (Exception e) {  
	            System.out.println("Client Error :" + e.getMessage());   
	        }
		 scan.close();
	}
	
	public void CreateProject()
	{
		pnluser.removeAll();
		JLabel Pname = new JLabel();
		final JTextField txt_pname = new JTextField();
		JLabel Comp = new JLabel();
		final JTextField txt_Comp = new JTextField();
		JLabel a = new JLabel();
		JLabel Bdate = new JLabel();
		final JTextField txt_Bdate = new JTextField();
		JLabel b = new JLabel();
		JLabel Edate = new JLabel();
		final JTextField txt_Edate = new JTextField();
		JLabel c = new JLabel();
		JLabel Number = new JLabel();
		final JTextField txt_Number= new JTextField();
		JButton sub = new JButton();
		
		Pname.setText("Project Name:");
		Pname.setBounds(40, 40, 120, 20);
		txt_pname.setBounds(170, 40, 120, 20);
		Comp.setText("Completion:");
		Comp.setBounds(40, 70, 120, 20);
		txt_Comp.setBounds(170, 70, 120, 20);
		a.setText("(Please enter the format like 'x%' !)");
		a.setBounds(170, 90, 300, 20);
		Bdate.setText("Begin Date:");
		Bdate.setBounds(40, 110, 120, 20);
		txt_Bdate.setBounds(170, 110, 120, 20);
		b.setText("(Please enter the format like '9999-12-30' !)");
		b.setBounds(170, 130, 300, 20);
		Edate.setText("End Date:");
		Edate.setBounds(40, 150, 120, 20);
		txt_Edate.setBounds(170, 150, 120, 20);
		c.setText("(Please enter the format like '9999-12-30' !)");
		c.setBounds(170, 170, 300, 20);
		Number.setText("Number of Em:");
		Number.setBounds(40, 200, 120, 20);
		txt_Number.setBounds(170, 200, 120, 20);
		sub.setText("Sub");
		sub.setBounds(40, 400, 85, 20);
		sub.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				//----------------------------------------------------------
				String pname = txt_pname.getText();
				String Comp = txt_Comp.getText();
				String Bdate = txt_Bdate.getText();
				String Edate = txt_Edate.getText();
				String Number = txt_Number.getText();
				
				socket_Create_project( pname, Comp, Bdate, Edate, Number);
				JOptionPane.showMessageDialog(null,"Create OK ！！");
				Login_socket(L_user, L_PWD, staff_role);
				
	    }
		}
		);
		pnluser.add(Pname);
		pnluser.add(txt_pname);
		pnluser.add(Comp);
		pnluser.add(txt_Comp);
		pnluser.add(a);
		pnluser.add(Bdate);
		pnluser.add(txt_Bdate);
		pnluser.add(b);
		pnluser.add(Edate);
		pnluser.add(txt_Edate);
		pnluser.add(c);
		pnluser.add(Number);
		pnluser.add(txt_Number);
		pnluser.add(sub);
		
		pnluser.updateUI();
		this.add(pnluser);
		this.setVisible(true);
		
		
		
	}
	public static void main(String[] args)

	{
		new Client();
		
	}
}
