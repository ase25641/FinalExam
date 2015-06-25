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


public class Sever1{
	public static void main(String[] args) {
	int port=8888;
	int select=0;
	System.out.println("1||2||3		"
			+ "1=Server	"
			+ "2=Client"
			+ "3=alone"	);
	Scanner sc=new Scanner(System.in);
	select=sc.nextInt();
	switch(select){
	case 1:{
		startSever(port);
		break;
	}
	case 2:{
		String ip;
		System.out.println("where are your IP");
		ip=sc.next();
		startClient(port,ip);
		break;
	}
	case 3:{
		
	}
	}
	
}
	public static void startSever(int port){
		ServerSocket ss;
		String data,choose;
		boolean a=true;
		Scanner sc=new Scanner(System.in);
		int total=30,total2=30,choose1=0,clientin;
		try {//IO?嚙�
			ss=new ServerSocket(port);
			String serverIP = InetAddress.getLocalHost().getHostAddress(); 
			System.out.println(serverIP);
			Socket ws=ss.accept();//wait client
			System.out.println("Connection from Client IP: " + 
					ws.getInetAddress().getHostAddress());
			while(a) {
				
				BufferedReader br = new BufferedReader(new InputStreamReader(ws.getInputStream()));//C enter
				
				BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(ws.getOutputStream()));//C enter

				BufferedReader sbr = new BufferedReader(new InputStreamReader(System.in));
			
				if(total<=0&&total2<=0){
					br.close();
					bw.close();
					sbr.close();
					a=false;
				}else if(total==0&&total2==0){
					System.out.println("you win");
				}
				System.out.println("Now are not you time!!!");
				System.out.print("C time	");
				choose=br.readLine();
				choose1=Integer.parseInt(choose);
				data=br.readLine();//
				//System.out.println(data);//
				clientin=Integer.parseInt(data);//Client math
				switch(choose1){
				case 1:{
					total-=clientin;
					System.out.println("Math:"+total+"	"+total2);
					break;
				}
				case 2:{
					total2-=clientin;
					System.out.println("Math:"+total+"	"+total2);
					break;
				}
				case 3:{
					total-=clientin;
					total2-=clientin;
					System.out.println("Math:"+total+"	"+total2);
					break;
				}
				}
				System.out.println("your time");//
				System.out.println("your choose");
				choose=sbr.readLine();
				choose1=Integer.parseInt(choose);
				bw.write(choose);
				bw.newLine();
				bw.flush();
				System.out.println("your math");
				data=sbr.readLine();
				clientin=Integer.parseInt(data);
				bw.write(data);
				bw.newLine();
				bw.flush();
				switch(choose1){
				case 1:{
					total-=clientin;
					System.out.println("Math:"+total+"	"+total2);
					break;
				}
				case 2:{
					total2-=clientin;
					System.out.println("Math:"+total+"	"+total2);
					break;
				}
				case 3:{
					total-=clientin;
					total2-=clientin;
					System.out.println("Math:"+total+"	"+total2);
					break;
				}
				}
			}
			
		} catch (IOException e) {//
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void startClient(int port,String ip){
		Scanner sc=new Scanner(System.in);
		String data,choose;
		boolean a = true;
		int total=30,total2=30,choose1,serverin;
		try {
			Socket connect=new Socket(ip,port);
			System.out.println("is online");
			
			while(a){
				BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()));//
			
				BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));//
		
				BufferedReader sbr = new BufferedReader(new InputStreamReader(System.in));//
				
				if(total<0&&total2<0){
					br.close();
					bw.close();
					sbr.close();
					a=false;
				}
				else if(total==0&&total2==0){
					System.out.println("you win");
				}
				System.out.println("Math:"+total+"	"+total2);
				System.out.println("your time");
				System.out.println("your choose 1||2||3");
				choose=sbr.readLine();
				choose1=Integer.parseInt(choose);
				bw.write(choose);
				bw.newLine();
				bw.flush();
				System.out.println("your math");
				data=sbr.readLine();
				serverin=Integer.parseInt(data);
				bw.write(data);//
				bw.newLine();
				bw.flush();
				switch(choose1){
				case 1:{
					total-=serverin;
					System.out.println("Math:"+total+"	"+total2);
					break;
				}
				case 2:{
					total2-=serverin;
					System.out.println("Math:"+total+"	"+total2);
					break;
				}
				case 3:{
					total-=serverin;
					total2-=serverin;
					System.out.println("Math:"+total+"	"+total2);
					break;
				}
				}
				//System.out.println("Math:"+total+"	"+total2);
				System.out.println("S time");
				choose=br.readLine();//S choose
				
				//System.out.println(choose);
				
				choose1=Integer.parseInt(choose);
				data=br.readLine();
				serverin=Integer.parseInt(data);
				switch(choose1){
				case 1:{
					total-=serverin;
					System.out.println("Math:"+total+"	"+total2);
					break;
				}
				case 2:{
					total2-=serverin;
					System.out.println("Math:"+total+"	"+total2);
					break;
				}
				case 3:{
					total-=serverin;
					total2-=serverin;
					System.out.println("Math:"+total+"	"+total2);
					break;
				}
				}
				
				
			}
			
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}