import java.util.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {

	public void receive()
	{
		try{
			
			ServerSocket ss=new ServerSocket(4333);
			Socket s=ss.accept(); 
			 DataInputStream dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
		        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
//		read the number of files from the client
		        int count = dis.readInt();
		        ArrayList<File>files = new ArrayList<File>(count);
		        System.out.println("Number of Files to be received: " +count);
		        //read file names, add files to arraylist
		        for(int i = 0; i< count;i++){
		            File file = new File(dis.readUTF());
		            files.add(file);
		        }
		        int n = 0;
		        
		        for(int i = 0; i < files.size();i++){
		        	  byte[]buf = new byte[4092];
		            System.out.println("Receiving file: " + files.get(i).getName());
		            //create a new fileoutputstream for each new file
		            FileOutputStream fos = new FileOutputStream("D:\\Another_file" +files.get(i).getName());
		            //read file
		            while((n = dis.read(buf)) != -1){
		                fos.write(buf,0,n);
		                fos.flush();
		            }
		            fos.close();
		          
		            
		        }

		    } catch (IOException e) {
		        
		        e.printStackTrace();

		    }

		}
	
	public static void main(String[] args){
		Server s=new Server();
		s.receive();
	}

}
/*Enter no of files to transfer from client to server
3
enter input along with its path
D:\\Tcp_wordfile1.docx
file path is:D:\\Tcp_wordfile1.docx
enter input along with its path
D:\\Tcp_textfile1.txt
file path is:D:\\Tcp_textfile1.txt
enter input along with its path
D:\\Tcp_pdf1.pdf
Number of Files to be received: 3
Receiving file: Tcp_pdf1.pdf
Receiving file: Tcp_textfil1.txt
Receiving file: Tcp_wordfile1.docx

/*Hello,This is a text file which is sent from client to server over TCP Connction.
 Hello this is a word file which is sent from client to server over TCP connection.
  Hello this is a pdf file sent from client to server over tcp connection.*/
