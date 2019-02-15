
/*Digital Attendence system.This is GUI based application developed for taking attendance digitally
and this uses the MYSQL database.
In this attendence is taken digitally and also attendence percentage can be also calculated
by just clicking on single button "status"......

*/

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.lang.*;

//log class for login of staff

class log extends JFrame implements ActionListener
{

	JLabel l1,l2;
	JTextField t1;
	JPasswordField t2;
	JButton b;

	//constructor log() is defined for loging in staffs 

	log()
	{
		getContentPane().setLayout(new GridLayout(3,2));
		l1=new JLabel("Username:");
		l2=new JLabel("Password:");
		b=new JButton("Next");
		t1=new JTextField(30);
		t2=new JPasswordField(30);
		
		this.add(l1);
		this.add(t1);
		add(l2);
		add(t2);
		add(b);
		t1.addActionListener(this);
		t2.addActionListener(this);
		b.addActionListener(this);
		
		t1.requestFocus();

	}
	
	
	//Method for checking validity of log input and it checks the enterd data with already
	//stored in database "attendencesystem" and tablenamed log
	
	
	public void actionPerformed(ActionEvent ae)
	{
				

		if(ae.getSource()==b)
		{
			try
			{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/attendencesystem","root","");
			Statement st=con.createStatement();
			String str="select * from log where username='" + t1.getText() + "' and password='" + t2.getText() +   "'";
			ResultSet rs = st.executeQuery(str);			

	if(rs.next())
	{
	 
		this.setVisible(false);
		det d= new det(t1.getText());
		d.resize(400,400);
		d.setVisible(true);
	}
	else

	{
		JOptionPane.showMessageDialog(null,"Wrong userid or password");
		t2.setText("");
		t2.requestFocus();
	}			

	con.close();
	}
	catch(Exception ee) 
	{
		ee.printStackTrace();
		System.out.println(ee.getMessage());
	}			
			
		}
	}
}




class details extends JFrame implements ActionListener{
JLabel name[],roll[];
JTable t;
JLabel lab;

//This class is used to give description


details(String course, String dept, String sem, String date, String period, String staffid)
{
	
	
	JPanel p1= new JPanel();
	lab=new JLabel("ROLL NO AND THEIR PERCENTAGE");
	add(lab, BorderLayout.NORTH);
	add(p1);	
	
	try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/attendencesystem","root","");
			Statement st;
			String str="select count(studid) from sheet where status='p' group by studid";
			st=con.createStatement();
			ResultSet rs=st.executeQuery(str);
			ResultSetMetaData rms=rs.getMetaData();

				int n=0;
	
		while(rs.next())
			n++;
		p1.setLayout(new FlowLayout());

		
		roll=new JLabel[n];
		name=new JLabel[n];
		int present[]=new int[n];
		int i=0;
		rs=st.executeQuery(str);
		while(rs.next())
		{
			
			present[i]=rs.getInt(1);
		
		System.out.println(present[i]);
		i++;
		}
		
		System.out.println("HO");
		
String qry="select count(studid) from sheet group by(studid)";
rs=st.executeQuery(qry);
rms=rs.getMetaData();
int m=0;
while(rs.next())
	m++;
int cl=rms.getColumnCount();
int tot[]=new int[m];
rs=st.executeQuery(qry);

String clabel[]={rms.getColumnLabel(1),"Attendence percentage"};


i=0;
while(rs.next())
{
tot[i]=rs.getInt(1);
System.out.println(tot[i]);

i++;
}


String per[]=new String[m];
for(i=0;i<m;i++){
	
per[i]=(present[i]*100)/tot[i]+"%";
System.out.println(per[i]);
					
}
str="select distinct studid from sheet";
		rs=st.executeQuery(str);
i=0;
int r[]=new int[n];
		while(rs.next())
		{
			System.out.println(rs.getInt(1));
			r[i]=rs.getInt(1);
		
			
		i++;
		}
		String data[][] = new String[n][2];
		
		
		for(i=0;i<n;i++)
			data[i][0]=(r[i]+"");
		for(i=0;i<n;i++)
			data[i][1]=per[i];

		t=new JTable(data,clabel);
JScrollPane sp=new JScrollPane(t);
		
add(sp,BorderLayout.CENTER);
		
		}catch(Exception e)
{ System.out.println(e);
}

	
}


	public void actionPerformed(ActionEvent ae)
	{

	}
}


//class containing method for opening GUI after successfull login

class det extends JFrame implements ActionListener
{
	JComboBox cb1,cb2,cb3;
	JPanel p1,p2;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	JTextField t1,t2,t3,t4,t5,t6,t7,t8;
	JButton b,b1;
	String u="";
	
	//constructor defined for opening JFrame
	
	det(String uname)
	{
		u=uname;
		setLayout(new GridLayout(3,1));
		l1=new JLabel(" Staffid:");
		l2=new JLabel("Staff Name:");
		l3=new JLabel("Enter Course:");
		l4=new JLabel("Enter Subject:");
		l5=new JLabel("Enter Date:");
		l6=new JLabel("Enter Department:");
		l7=new JLabel("Enter period:");
		l8=new JLabel("Enter Semester:");
		p1=new JPanel();
		setLayout(new GridLayout(10,2));
		
		String mn[]={"january","february","mar","april","may","june","july","august","oct","nov","dec"};
	JPanel p=new JPanel();

	p1=new JPanel();


	cb1=new JComboBox();
	cb2=new JComboBox();
	cb3=new JComboBox();

	for(int i=1;i<=31;i++)
	cb1.addItem(i+"");

	for(int i=0;i<mn.length;i++)
	cb2.addItem(mn[i]);

	for(int i=2017;i<=2030;i++)
	cb3.addItem(i+"");
	cb1.addActionListener(this);
	cb2.addActionListener(this);
	cb3.addActionListener(this);

		t1=new JTextField(30);
		t2=new JTextField(30);
		t3=new JTextField(30);
		t4=new JTextField(30);
		t5=new JTextField(30);
		t6=new JTextField(30);
		t7=new JTextField(30);
		t8=new JTextField(30);
		b1=new JButton("Status");//button to see the status of attendence
		b=new JButton("Attendence sheet");//button to open attendence system
		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(l3);
		add(t3);
		add(l4);
		add(t4);
	
		add(l5);
		add(t5);
		
		add(l6);
		add(t6);
		add(l7);
		add(t7);
		add(l8);
		add(t8);
		
		
		t1.addActionListener(this);
		t5.addActionListener(this);	
		add(b);
		add(b1);
		b.addActionListener(this);
		b1.addActionListener(this);
		
		t2.requestFocus();
		t1.setText(uname);
	}
	
	
	
	
	
	public void actionPerformed(ActionEvent ae)
	{

t5.setText(cb1.getSelectedItem()+" / "+cb2.getSelectedItem()+" / "+cb3.getSelectedItem());	
		
		if(ae.getSource()==b)
		{
			attend at=new attend(t3.getText(),t6.getText(),t8.getText(),t5.getText(),t7.getText(),t1.getText());
			at.resize(500,500);
			at.setVisible(true);
			this.setVisible(false);
		}
		if(ae.getSource()==b1)
		{
			details d=new details(t3.getText(),t6.getText(),t8.getText(),t5.getText(),t7.getText(),t1.getText());
			d.resize(500,500);
			d.setVisible(true);
		}
		
	}

}

class attend extends JFrame implements ActionListener,ItemListener
{
String state[];
int n,m;

JLabel lab, studid[], studname[];
Checkbox status[];	
JButton b1;
String course, dept,sem,dt, period,staffid;



attend(String course, String dept, String sem, String date, String period, String staffid)
{ 
	this.course=course;
	this.dept= dept;
	this.sem=sem;
	this.dt=date;
	this.period=period;
	this.staffid = staffid;
	
	JPanel p1= new JPanel();
	lab= new JLabel("date"+date + " Period : " + period + " Staff id : " + staffid + " Semester : " + sem + " course : " + course + " (" + dept + ")" );
	  add(lab, BorderLayout.NORTH);
	date=date;
	  add(p1);	  
	try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost/attendencesystem","root","");
	Statement st=con.createStatement();
	String qry="select * from next where  dept='"+ dept +"' and sem=" +sem  ;
	
	ResultSet rs=st.executeQuery(qry);
	n=0;
	while(rs.next())
		n++;
	m=n;
	p1.setLayout(new GridLayout(n+1,3));
	studid= new JLabel[n];
								
	studname=new JLabel[n];
	status =new Checkbox[n];
	rs=st.executeQuery(qry);
	
	int i=0;
		while(rs.next())
		{
		
		studid[i]=new JLabel(rs.getInt(1)+"");
		studname[i]=new JLabel(rs.getString(2));
		status[i] =new Checkbox("Present",null,true);
		p1.add(studid[i]);
		p1.add(studname[i]);
		p1.add(status[i]);
		i++;	
	
	}
	
	}catch(Exception ee){}
	b1=new JButton("Store");
	
	p1.add(b1);
	b1.addActionListener(this);
}

//interface implementation

public void itemStateChanged(ItemEvent ie)
{

}


	public void actionPerformed(ActionEvent ae)
	{
	
	try
	{
				
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/attendencesystem","root","");
		Statement st=con.createStatement();
	for(int i=0;i<m;i++)
	{
	 
		String qry="insert into sheet values(" + studid[i].getText() + ",'" + dt + "','" + (status[i].getState()?"p" : "a") + "'," + period + "," + staffid + ")";
		System.out.println(qry);
		st.executeUpdate(qry);
	}
	con.close();				
	JOptionPane.showMessageDialog(null,"Record inserted");
			}
		
	catch(Exception ee)
	{
		ee.printStackTrace();
		System.out.println(ee.getMessage());
	}		
	
		
	
}
}


public class digital_attendence extends JFrame implements ActionListener
{

	JLabel r;
	digital_attendence()
	{
		getContentPane().setBackground(Color.black);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
	
	}
	

	public static void main(String arg[])
	{
		
		digital_attendence lbs =new digital_attendence();
		lbs.setVisible(true);
		lbs.resize(300,150);
		
		
		log lg=new log();
		lg.setVisible(true);
		lg.resize(300,150);
		
	}
}