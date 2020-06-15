import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Client {
	static ArrayList<File> arr=new ArrayList<File>();
	public void send(ArrayList<File>files)
	{
		try {
			Socket s=new Socket("localhost",4333);   
		
	        DataInputStream dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
	        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
	        System.out.println(files.size());

	              dos.writeInt(files.size());             //write the number of files to the server
	              dos.flush();

	             
	              for(int i = 0 ; i < files.size();i++){
	                  dos.writeUTF(files.get(i).getName());   //writing file names to data output stream
	                  dos.flush();
	              }

	          
	              int n=0;
	              for(int i =0; i < files.size(); i++){
	            	  byte[]buf = new byte[4092];
	                  System.out.println(files.get(i).getName());
	                  //create new fileinputstream for each file
	                  FileInputStream fis = new FileInputStream(files.get(i));

	                  //write file to dos
	                  while((n =fis.read(buf)) != -1){
	                      dos.write(buf,0,n);
	                      dos.flush();

	                  }
	                 
	              }
	              dos.close();
		}
	          catch (IOException e) {
	              
	              e.printStackTrace();
	          }


		}	
	public static void main(String[] args)throws FileNotFoundException {
		
	int n;
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter no of files to transfer from client to server");
	n=sc.nextInt();
	
	for(int i=0;i<n;i++)
	{
		System.out.println("enter input along with its path");
		String file=sc.next();
		System.out.println("file path is:"+file);
		File f=new File(file);
		
		arr.add(f);
		
		
	}
	Client c=new Client();
	c.send(arr);
	}

}
