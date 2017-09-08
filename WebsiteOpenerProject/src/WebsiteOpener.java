import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URI;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WebsiteOpener extends Library implements ActionListener{
	
	private JFrame frame;
	private JPanel panel1;
	private JPanel panel2;
	private JButton button,button2;
	private JButton[] buttons;	
	private JLabel[] text;
	private JTextField[] field;
	private URI[] web;
	private String[][] data;	
	
	//NEW WEBSITE PANEL INFO
	private String website_name;
	private String website_url;
	private String website_dis;	
	
	//DATA
	private Data dat;
	
	public void init(){
		frame=new JFrame();
		panel1=new JPanel();
		panel2=new JPanel();
		text=new JLabel[5];
		field=new JTextField[5];
		buttons=new JButton[15];
		web=new URI[15];
		data=new String[15][4];//data[1]:WEBSITE NAME data[2]:URL data[3]:WEBSITE DISCRIPTION
		dat=new Data();
		dat.dataInit(frame,panel1,this,buttons,data,web);//recieving data variables at the start of the program from txt file
	}
	public WebsiteOpener(){
		init();
		
		//FRAME Settings
		frame.setSize(300,450);
		frame.setVisible(true);
		frame.setTitle("Website Opener");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);	
		
		
		//Panels Settings
		panel2.setVisible(false);
		panel1.setLayout(new GridLayout(0,2,5,5));
		panel2.setLayout(null);
		panel2.setBackground(new Color(45,45,45));
		frame.add(panel1,BorderLayout.PAGE_START);		   
		frame.add(panel2);
		
		//MENUBAR
		JMenuBar bar=new JMenuBar();
		JMenu Website=new JMenu("Website");
		JMenuItem nWebsite=new JMenuItem("New Website");
		JMenuItem rWebsite=new JMenuItem("Remove Website");
		JMenuItem about=new JMenuItem("About");
		frame.setJMenuBar(bar);
		bar.add(Website);
		Website.add(nWebsite);
		Website.add(rWebsite);
		Website.add(about);
		nWebsite.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ADD NEW WEBSITE
				resetTextField(field,panel1,panel2);
				panel1.setVisible(false);
				panel2.setVisible(true);
			}
		});
		rWebsite.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// REMOVE WEBSITE
				if(buttonsCounter>0){
					removeButton=true;
					JOptionPane.showMessageDialog(panel1,"Click button for remove it!");
				}
				else JOptionPane.showMessageDialog(panel1,"There is no website that you saved.");
			}
		});
		about.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog (null, "This program made by Oktay Koçer.\n17.08.2016\nGITHUB: oktaykcr", "About", JOptionPane.INFORMATION_MESSAGE);	
			}
		});
		
		
		addButton(frame,panel2,this,button,"<<","Menu",10,15,50,25);
		addText(panel2,text,0,"ADD WEBSITE",font,80,0,150,50);
		addText(panel2,text,1,"Website Name",font2,10,50,150,25);
		addTextField(panel2,field,0,font2,10,85,200,25);
		addText(panel2,text,2,"Website URL",font2,10,120,150,25);
		addTextField(panel2,field,1,font2,10,155,200,25);
		addText(panel2,text,3,"Discription",font2,10,190,150,25);
		addTextField(panel2, field,2,font2,10,225,200,25);
		addButton(frame,panel2,this,button2,"Add","When you click create website button",80,280,100,40);
		frame.revalidate();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Add")){
			try{
				if(buttonsCounter<14){
					website_name=field[0].getText();
					website_url=field[1].getText();
					website_dis=field[2].getText();
					setWebsiteName(website_name);
					setWebsiteUrl(website_url);
					setWebsiteDis(website_dis);
					if(!(field[0].getText().isEmpty()) && !(field[1].getText().isEmpty())){
						BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file_name,true));
						dat.setData(data,website_name,website_url,website_dis,buttonsCounter);
						dat.writeData(panel2,data,buttonsCounter);
						setURIConnection(web,buttonsCounter,data[buttonsCounter][2]);
						addButton(frame,panel1,this,buttons,buttonsCounter,data[buttonsCounter][1],data[buttonsCounter][3]);
						buttonsCounter++;						
						resetTextField(field,panel1,panel2);
						bufferedWriter.close();
					}
					else{JOptionPane.showMessageDialog(panel2,"Plase fill in the blanks!");}
				}
				else JOptionPane.showMessageDialog(panel1,"Memory is Full!");
			}catch(Exception ex){
				ex.printStackTrace();
				JOptionPane.showMessageDialog(panel2,"ERROR");
				System.exit(0);
			}
		}
		if(removeButton==false){
			for(int i=0;i<buttonsCounter;i++)
				if(e.getActionCommand().equals(data[i][1])){
					getURIConnection(web,i);
				}
		}
		else if(buttonsCounter>0 && removeButton==true){
			for(int i=0;i<buttonsCounter;i++)
				if(e.getActionCommand().equals(data[i][1])){
					removeButton(panel1,(JButton)e.getSource(),data,i);
				}
			panel1.revalidate();
			removeButton=false;
		}
		else{
			JOptionPane.showMessageDialog(panel1,"There is no website in your program!");
		}
		if(e.getActionCommand().equals("<<")){
			panel1.setVisible(true);
			panel2.setVisible(false);
		}
		
	}	
	public void setURIConnection(URI[] url,int index,String www){
		try{
			url[index]=new URI(www);
		}catch(Exception ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(panel1,"Internet connection error!");
		}
	}
	public void getURIConnection(URI[] url,int index){
		try {
			desktop.browse(web[index]);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(panel1,"Internet connection error!");
		}
	}
	
	public void setWebsiteName(String name){
		this.website_name=name;
	}
	public void setWebsiteUrl(String url){
		this.website_url=url;
	}
	public void setWebsiteDis(String dis){
		this.website_dis=dis;
	}
	public String getWebsiteName(){
		return website_name;
	}
	public String getWebsiteUrl(){
		return website_url;
	}
	public String getWebsiteDis(){
		return website_dis;
	}
	public String getButtonCounter(){
		return String.valueOf(buttonsCounter);
	}

}
