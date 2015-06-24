import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Sever{
	public static void main(String[] args) {
	int port=8888;
	int select=0;
	System.out.println("??入1||2"
			+ "1=等待對手"
			+ "2=尋找對手");
	Scanner sc=new Scanner(System.in);
	select=sc.nextInt();
	switch(select){
	case 1:{
		startSever(port);
		break;
	}
	case 2:{
		String ip;
		System.out.println("??入對方IP");
		ip=sc.next();
		startClient(port,ip);
		break;
	}
	}
	
}
	public static void startSever(int port){
		ServerSocket ss;
		String data;
		Scanner sc=new Scanner(System.in);
		int Out=0;
		try {//IO?入
			ss=new ServerSocket(port);
			String serverIP = InetAddress.getLocalHost().getHostAddress(); 
			System.out.println(serverIP);
			Socket ws=ss.accept();//等待連?
			System.out.println("Connection from Client IP: " + 
					ws.getInetAddress().getHostAddress());
			while(true) {
				BufferedReader br = new BufferedReader(new InputStreamReader(ws.getInputStream()));//C端取得值
				BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(ws.getOutputStream()));//C端取得值
				BufferedReader sbr = new BufferedReader(new InputStreamReader(System.in));
				
				System.out.println("現在不是你的回合!!!");
				System.out.print("C端?入:	");
				data=br.readLine();//將C端取得值丟入?數
				System.out.println(data);//顯示得到的值
				System.out.println("換您??入");//取得S端?出的值
				data=sbr.readLine();
				bw.write(data);
				bw.newLine();
				bw.flush();
			}
			
		} catch (IOException e) {//錯誤?息
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void startClient(int port,String ip){
		Scanner sc=new Scanner(System.in);
		String data;
		try {
			Socket connect=new Socket(ip,port);
			System.out.println("已連?");
			System.out.println("由您先攻:");
			while(true){
				BufferedReader sbr = new BufferedReader(new InputStreamReader(System.in));
				BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
				BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));
				System.out.println("??入數字");
				data=sbr.readLine();
				bw.write(data);//C端?出值
				
				bw.newLine();
				bw.flush();
				System.out.println("S端?入");
				data=br.readLine();
				System.out.println(data);
				
				
			}
			
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}