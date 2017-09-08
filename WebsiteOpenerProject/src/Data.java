
import java.io.IOException;

public class Data extends Library {
	
	private java.io.BufferedWriter bufferedWriter;
	private java.io.BufferedReader bufferedReader;
	
	public void dataInit(javax.swing.JFrame frame,javax.swing.JPanel panel,java.awt.event.ActionListener al,javax.swing.JButton[] buttons,String[][] data,java.net.URI[] web){//FILE PART READ
			try {
				java.io.File f=new java.io.File(file_name);
				if(f.exists() && !(f.isDirectory())){
			        bufferedReader = new java.io.BufferedReader(new java.io.FileReader(file_name)); 
					bufferedWriter=new java.io.BufferedWriter(new java.io.FileWriter(file_name,true));
					readData(panel,data);
					String line=data[0][0];					
					if(line==null){
						javax.swing.JOptionPane.showMessageDialog(panel,"There are no data in your program!");
					}
					else{
						int i=0;
						while(line!=null){
							line=data[i][0];
							i++;
						}
						buttonsCounter=Integer.parseInt(data[i-2][0])+1;
						for(int j=0;j<buttonsCounter;j++){
							try{
								web[j]=new java.net.URI(data[j][2]);
							}catch(Exception ex){
								ex.printStackTrace();
								javax.swing.JOptionPane.showMessageDialog(panel,"Internet connection error!");
							}
							addButton(frame,panel,al,buttons,j,data[j][1],data[j][3]);
						}
						frame.revalidate();
					}
					bufferedWriter.close();
					bufferedReader.close();
				}
				else{
					f.createNewFile();
			        bufferedReader = new java.io.BufferedReader(new java.io.FileReader(file_name)); 
					bufferedWriter=new java.io.BufferedWriter(new java.io.FileWriter(file_name,true));
					readData(panel,data);
					String line=data[0][0];
					int i=0;
					if(line==null){
						javax.swing.JOptionPane.showMessageDialog(panel,"There are no data in your program!");
					}
					else{
						while(line!=null){
							line=data[i][0];
							i++;
						}
						buttonsCounter=Integer.parseInt(data[i-2][0])+1;
						for(int j=0;j<buttonsCounter;j++){
							try{
								web[j]=new java.net.URI(data[j][2]);
							}catch(Exception ex){
								ex.printStackTrace();
								javax.swing.JOptionPane.showMessageDialog(panel,"Internet connection error!");
							}
							addButton(frame,panel,al,buttons,j,data[j][1],data[j][3]);
						}
						frame.revalidate();
					}
					bufferedWriter.close();
					bufferedReader.close();
				}
		    }
			catch(java.io.FileNotFoundException ex) {
			       ex.printStackTrace();             
			     }
		    catch(IOException ex) {
			       javax.swing.JOptionPane.showMessageDialog(panel,"Error reading file!");                 
			     }
	}
	public void setData(String[][] data,String websiteName,String websiteURL,String websiteDiscription,int counter){
		data[counter][0]=String.valueOf(buttonsCounter);
		data[counter][1]=websiteName;
		data[counter][2]=websiteURL;
		data[counter][3]=websiteDiscription;
	}
	public void writeData(javax.swing.JPanel panel,String[][] data,int counter) throws IOException{
		try {
			bufferedWriter=new java.io.BufferedWriter(new java.io.FileWriter(file_name,true));
			for(int i=0;i<4;i++){
				bufferedWriter.write(data[counter][i]);
				bufferedWriter.newLine();
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
			javax.swing.JOptionPane.showMessageDialog(panel,"An error occurred while writing data!");
		}finally {
			bufferedWriter.close();
		}
	}

	public void readData(javax.swing.JPanel panel,String[][] data){
		String line=null;
		int i=0;
		int j=0;
		try {
			while((line=bufferedReader.readLine()) != null){
        	   if(j!=4){
        		   data[i][j]=line;
            	   j++;  
        	   }
        	   else{
        		   i++;
        		   j=0;
        		   data[i][j]=line;
        		   j++;
        	   }
            }  
           bufferedReader.close();
        }
        catch(java.io.FileNotFoundException ex) {
        	javax.swing.JOptionPane.showMessageDialog(panel,"The File create successfully");             
        }
        catch(IOException ex) {
        	javax.swing.JOptionPane.showMessageDialog(panel,"Error reading file!");                 
        }
	}

}
