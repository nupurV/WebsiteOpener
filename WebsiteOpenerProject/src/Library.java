import java.awt.Desktop;

public class Library {
	public java.awt.Font font=new java.awt.Font("Courier",java.awt.Font.BOLD,16);
	public java.awt.Font font2=new java.awt.Font("Courier",java.awt.Font.PLAIN,16);
	public String file_name="WebsiteOpenerData.txt";//txt data file name
	public java.awt.Desktop desktop=Desktop.getDesktop();//Dekstop browser connection
	public boolean removeButton=false;//to remove website button
	public static int buttonsCounter;//number of buttons 
	
	
	public void addButton(javax.swing.JFrame frame,javax.swing.JPanel panel,java.awt.event.ActionListener al,javax.swing.JButton[] button,int index,String name,String dis){
		button[index]=new javax.swing.JButton(name);
		panel.add(button[index]);
		button[index].addActionListener(al);
		button[index].setToolTipText(dis);
		button[index].setPreferredSize(new java.awt.Dimension(100,50));
		frame.revalidate();
	}
	public void addButton(javax.swing.JFrame frame,javax.swing.JPanel panel,java.awt.event.ActionListener al,javax.swing.JButton button,String name,String dis,int x,int y,int width,int height){
		button=new javax.swing.JButton(name);
		panel.add(button);
		button.addActionListener(al);
		button.setBounds(x, y, width, height);
		button.setToolTipText(dis);
		frame.revalidate();
	}
	public void removeButton(javax.swing.JPanel panel,javax.swing.JButton button,String[][] data,int i){
		Data dat=new Data();
		panel.remove(button);
		panel.repaint();
		String line;
		int ti=i;//temp_i veriable to keep i var
		line=data[ti+1][0];// is there any next button to control
		int j=0;//to write file according to data id
		try {
			java.io.BufferedWriter bufferedWriter=new java.io.BufferedWriter(new java.io.FileWriter(file_name,false));
			if(line!=null){
				while(data[ti][0]!=null){
					data[ti][1]=data[ti+1][1];
					data[ti][2]=data[ti+1][2];
					data[ti][3]=data[ti+1][3];
					if(data[ti+1][0]!=null){
						ti++;
					}
					else{
						data[ti][0]=null;
						break;
					}
				}
				while(data[j][0]!=null){
					dat.writeData(panel,data,j);
					j++;
				}
				buttonsCounter--;
			}
			else{
				data[ti][0]=null;
				data[ti][1]=null;
				data[ti][2]=null;
				data[ti][3]=null;
				while(data[j][0]!=null){
					dat.writeData(panel,data,j);
					j++;
				}
				buttonsCounter--;
			}
	        bufferedWriter.close();
			}
	        catch(java.io.FileNotFoundException ex) {
	        	javax.swing.JOptionPane.showMessageDialog(panel,"The File create successfully");             
	        }
	        catch(java.io.IOException ex) {
	        	javax.swing.JOptionPane.showMessageDialog(panel,"Error reading file!");                 
	        }
	}
	public void addText(javax.swing.JPanel panel,javax.swing.JLabel[] label,int index,String text,java.awt.Font font,int x,int y,int width,int height){
		label[index]=new javax.swing.JLabel();
		panel.add(label[index]);
		label[index].setText(text);
		label[index].setFont(font);
		label[index].setForeground(java.awt.Color.white);
		label[index].setBounds(x, y, width, height);
	}
	public void addTextField(javax.swing.JPanel panel,javax.swing.JTextField[] field,int index,java.awt.Font font,int x,int y,int width,int height){
		field[index]=new javax.swing.JTextField();
		panel.add(field[index]);
		field[index].setFont(font);
		field[index].setForeground(java.awt.Color.black);
		field[index].setBounds(x, y, width, height);
	}
	public void addTextArea(javax.swing.JPanel panel,java.awt.TextArea ta,java.awt.Font font,int x,int y,int width,int height){
		ta=new java.awt.TextArea();
		panel.add(ta);
		ta.setFont(font);
		ta.setForeground(java.awt.Color.black);
		ta.setBounds(x, y, width, height);
	}
	public void resetTextField(javax.swing.JTextField[] field,javax.swing.JPanel panel1,javax.swing.JPanel panel2){
		for(int i=0;i<3;i++){
			field[i].setText("");
			panel1.setVisible(true);
			panel2.setVisible(false);
		}	
	}
}
