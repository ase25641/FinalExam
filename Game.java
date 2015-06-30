import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;


public class Game{
	public static void main(String[] args) {
	int port=8888;
	int select=0;
	System.out.println("1||2||3		"
			+ "1=伺服端	"
			+ "2=接收端	"
			+ "3=單人"	);
	Scanner sc=new Scanner(System.in);
	select=sc.nextInt();
	switch(select){
	case 1:{
		startSever(port);
		break;
	}
	case 2:{
		String ip;
		System.out.println("請問你的IP");
		ip=sc.next();
		startClient(port,ip);
		break;
	}
	case 3:{
		alone();
		break;
	}
	}
	
}
	public static void startSever(int port){
		ServerSocket ss;
		String data,choose;
		boolean a=true;
		Scanner sc=new Scanner(System.in);
		int total=30,total2=30,choose1=0,clientin;
		try {
			ss=new ServerSocket(port);
			String serverIP = InetAddress.getLocalHost().getHostAddress(); 
			System.out.println(serverIP);
			Socket ws=ss.accept();//wait client
			System.out.println("連線電腦IP " + 
					ws.getInetAddress().getHostAddress());
			while(a) {
				
				BufferedReader br = new BufferedReader(new InputStreamReader(ws.getInputStream()));//C enter
				
				BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(ws.getOutputStream()));//C enter

				BufferedReader sbr = new BufferedReader(new InputStreamReader(System.in));
			

				
				System.out.println("現在不是你的回合");
				System.out.print("接收端輸入	");
				
				choose=br.readLine();
				choose1=Integer.parseInt(choose);
				
				data=br.readLine();//
				//System.out.println(data);//
				clientin=Integer.parseInt(data);//Client math
			
				switch(choose1){
				case 1:{
					total-=clientin;
					System.out.println("數字"+total+"	"+total2);
					break;
				}
				case 2:{
					total2-=clientin;
					System.out.println("數字:"+total+"	"+total2);
					break;
				}
				case 3:{
					total-=clientin;
					total2-=clientin;
					System.out.println("數字:"+total+"	"+total2);
					break;
				}
				}
				if(total==0&&total2==0){
				System.out.println("你輸了!");
				br.close();
				bw.close();
				sbr.close();
				a=false;
				}
				else{
				System.out.println("換你輸入");//
				System.out.println("請選擇1~3");
				choose=sbr.readLine();
				choose1=Integer.parseInt(choose);
				while(choose1>3||choose1<0){
				if(choose1>3||choose1<0){
					System.out.println("輸入錯誤	");
				}
				choose=sbr.readLine();
				choose1=Integer.parseInt(choose);
				//System.out.println(choose1);
				}
				System.out.println("輸入成功");
				bw.write(choose);
				bw.newLine();
				bw.flush();
				System.out.println("請輸入數字");
				data=sbr.readLine();
				clientin=Integer.parseInt(data);
				switch(choose1){
				case 1:{
					while(clientin<=0||(total-clientin)<0){
						System.out.println("輸入錯誤");
						data=sbr.readLine();
						clientin=Integer.parseInt(data);
						
					}
					total-=clientin;
					System.out.println("數字:"+total+"	"+total2);
					break;
				}
				case 2:{
					while(clientin<=0||(total2-clientin)<0){
						System.out.println("輸入錯誤");
						data=sbr.readLine();
						clientin=Integer.parseInt(data);
						
					}
					total2-=clientin;
					System.out.println("數字:"+total+"	"+total2);
					break;
				}
				case 3:{
					while(clientin<=0||(total-clientin)<0||(total2-clientin)<0){
						System.out.println("輸入錯誤");
						data=sbr.readLine();
						clientin=Integer.parseInt(data);
						
					}
					total-=clientin;
					total2-=clientin;
					System.out.println("數字:"+total+"	"+total2);
					break;
				}
				}
				bw.write(data);
				bw.newLine();
				bw.flush();
				if(total==0&&total2==0){
				System.out.println("你贏了!");
				br.close();
				bw.close();
				sbr.close();
				a=false;
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
		String data,choose="";
		boolean a = true;
		int total=30,total2=30,choose1=0,serverin=0;
		try {
			Socket connect=new Socket(ip,port);
			System.out.println("連線上囉");
			
			while(a){
				BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()));//
			
				BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));//
		
				BufferedReader sbr = new BufferedReader(new InputStreamReader(System.in));//
				
				
				System.out.println("數字:"+total+"	"+total2);
				System.out.println("你的時間");
				System.out.println("請選擇 1||2||3");
				choose=sbr.readLine();
				choose1=Integer.parseInt(choose);
				while(choose1>3||choose1<0){
				if(choose1>3||choose1<0){
					System.out.println("輸入錯誤	");
				}
				choose=sbr.readLine();
				choose1=Integer.parseInt(choose);
				//System.out.println(choose1);
				}
				System.out.println("輸入成功");
				bw.write(choose);
				bw.newLine();
				bw.flush();
				System.out.println("你的數字");
				data=sbr.readLine();
				serverin=Integer.parseInt(data);
				switch(choose1){
				case 1:{
					while(serverin<=0||(total-serverin)<0){
						System.out.println("輸入錯誤");
						data=sbr.readLine();
						serverin=Integer.parseInt(data);
						
					}
					total-=serverin;
					System.out.println("數字:"+total+"	"+total2);
					break;
				}
				case 2:{
					while(serverin<=0||(total2-serverin)<0){
						System.out.println("輸入錯誤");
						data=sbr.readLine();
						serverin=Integer.parseInt(data);
						
					}
					total2-=serverin;
					System.out.println("數字:"+total+"	"+total2);
					break;
				}
				case 3:{
					while(serverin<=0||(total-serverin)<0||(total2-serverin)<0){
						System.out.println("輸入錯誤");
						data=sbr.readLine();
						serverin=Integer.parseInt(data);
						
					}
					total-=serverin;
					total2-=serverin;
					System.out.println("數字:"+total+"	"+total2);
					break;
				}
				}
				bw.write(data);
				bw.newLine();
				bw.flush();
				if(total==0&&total2==0){
					System.out.println("你贏了");
					br.close();
					bw.close();
					sbr.close();
					a=false;
					break;
				}
				else{
				System.out.println("伺服端回合");
				choose=br.readLine();//S choose
				
				//System.out.println(choose);
				
				choose1=Integer.parseInt(choose);
				data=br.readLine();
				serverin=Integer.parseInt(data);
				switch(choose1){
				case 1:{
					total-=serverin;
					System.out.println("數字:"+total+"	"+total2);
					break;
				}
				case 2:{
					total2-=serverin;
					System.out.println("數字:"+total+"	"+total2);
					break;
				}
				case 3:{
					total-=serverin;
					total2-=serverin;
					System.out.println("數字:"+total+"	"+total2);
					break;
				}
				}
				if(total==0&&total2==0){
					System.out.println("你輸了");
					br.close();
					bw.close();
					sbr.close();
					a=false;
				}
				}
				
			}
			
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void alone(){
		int AI[][] = {{0,0,0},
				  {0,1,2},
				  {0,3,5},
				  {0,4,7},
				  {0,6,10},
				  {0,8,13},
				  {0,9,15},
				  {0,11,18},
				  {0,12,20},
				  {0,14,23},
				  {0,16,26},
				  {0,17,28}};
	int cut = 0;
	int round = 1;
	int player = 0;
	int i = 0;
	int X = 0;
	int both = 0;
	int Lp = 30, Rp = 30;
	int number = 0,choice = 0;
	int start=0;
	int enemy=0;
	int lr=0;
	Random ran = new Random();
	Scanner input=new Scanner (System.in);
	
	
	
	System.out.println("先攻請輸入1、後攻請輸入2");
	while(start!=1 && start!=2){
	start = input.nextInt();
	if(start == 1){
		System.out.println("您選擇先攻");
	} else if(start == 2){
		System.out.println("您選擇後攻");
	} else{
		System.out.println("輸入錯誤，請重新輸入");
	}
	}
	
	while(Lp!=0 || Rp!=0){
		if(start == 2){
			lr = (ran.nextInt(2)+1);
			if(lr == 1){
				enemy = (ran.nextInt(5)+6);
				System.out.println("電腦將左邊減"+enemy);
				Lp=Lp-enemy;
				round++;
			}
			if(lr == 2){
				enemy = (ran.nextInt(5)+6);
				System.out.println("電腦將右邊減"+enemy);
				Rp=Rp-enemy;
				round++;
			}
			start = 1;
		}
		else if(start == 1){
		}
		System.out.println("目前數字為："+Lp+","+Rp);
		System.out.println("要減左邊請輸入1,減右邊請輸入2,兩邊同時減請輸入3");
		choice = input.nextInt();
		if(choice == 1){
			System.out.println("請問要減多少?");
			number = input.nextInt();
			if(number<=0){
				System.out.println("不能減那個數字");
				continue;
			}
			if((Lp-number)<0){
				System.out.println("不能減那個數字");
				continue;
			}
			Lp = Lp-number;
			System.out.println("你將數字減至"+Lp+","+Rp);
			player=2;
		}
		else if(choice == 2){
			System.out.println("請問要減多少?");
			number = input.nextInt();
			if(number<=0){
				System.out.println("不能減那個數字");
				continue;
			}
			if((Rp-number)<0){
				System.out.println("不能減那個數字");
				continue;
			}
			Rp = Rp-number;
			System.out.println("你將數字減至"+Lp+","+Rp);
			player=2;
		}
		else if(choice == 3){
			System.out.println("請問要減多少?");
			number = input.nextInt();
			if(Lp+Rp==60 && number==30){
				System.out.println("不能減那個數字");
				continue;
			}
			if(number<=0){
				System.out.println("不能減那個數字");
				continue;
			}
			if((Lp-number)<0||(Rp-number)<0){
				System.out.println("不能減那個數字");
				continue;
			}
			Lp = Lp-number;
			Rp = Rp-number;
			System.out.println("你將數字減至"+Lp+","+Rp);
			player=2;
		}
		else{
			continue;
		}
		if(player==2){
			if(Lp==Rp && (Lp+Rp)!=60 && (Lp+Rp)!=0 ){
				System.out.println("電腦將兩邊各減"+Rp);
				Lp=0;
				Rp=0;
				System.out.println("現在數字為"+Lp+","+Rp);
				player=1;
				continue;
			}
			else if(Lp==0 && (Lp+Rp)!=0){
				System.out.println("電腦把右邊減"+Rp);
				Rp=0;
				System.out.println("現在數字為"+Lp+","+Rp);
				player=1;
				continue;
			}
			else if(Rp==0 && (Lp+Rp)!=0){
				System.out.println("電腦把左邊減"+Lp);
				Lp=0;
				System.out.println("現在數字為"+Lp+","+Rp);
				player=1;
				continue;
			}
			if(Lp>Rp){
				X = Lp-Rp;
			}
			else if(Rp>Lp){
				X = Rp-Lp;
			}
				
				if(X<12&&round==1){
					both = ((Lp+Rp-AI[X][1]-AI[X][2])/2);
					Lp=Lp-both;
					Rp=Rp-both;
					System.out.println("電腦將兩邊各減"+both);
					System.out.println("現在數字為"+Lp+","+Rp);
					player=1;
					round++;
				}
				else if(Lp-Rp>0){
					for(i=1;i<=11;i++){
						if(Rp == AI[i][1]){
							cut = Lp-AI[i][2];
							if(cut>0){
							Lp = Lp-cut;
							System.out.println("電腦把左邊減"+cut);
							System.out.println("現在數字為"+Lp+","+Rp);
							player=1;
							round++;
							}
							if(cut<=0){
								both = ((Lp+Rp-AI[X][1]-AI[X][2])/2);
								if(both==0){
									lr = (ran.nextInt(2)+1);
									if(lr == 1){
										if(Lp!=0){
										enemy = (ran.nextInt(9)+1);
										while((Lp-enemy)<0){
											enemy = (ran.nextInt(6)+1);
										}
										System.out.println("電腦將左邊減"+enemy);
										Lp=Lp-enemy;
										} else if(Lp == 0){
											lr = 2;
										}
									}
									if(lr == 2){
										if(Rp!=0){
										enemy = (ran.nextInt(9)+1);
										while((Rp-enemy)<0){
											enemy = (ran.nextInt(6)+1);
										}
										System.out.println("電腦將右邊減"+enemy);
										Rp=Rp-enemy;
										} else if(Rp == 0){
											lr = 1;
										}
									}
								}
								else if(both!=0){
									if((Lp+Rp)==0){
										continue;
								}
								Lp=Lp-both;
								Rp=Rp-both;
								System.out.println("電腦將兩邊各減"+both);
								System.out.println("現在數字為"+Lp+","+Rp);
								player=1;
								round++;
								}
							}
						}
						else if(Rp == AI[i][2]){
							cut = Lp-AI[i][1];
							if(cut>0){
							Lp = Lp-cut;
							System.out.println("電腦把左邊減"+cut);
							System.out.println("現在數字為"+Lp+","+Rp);
							player=1;
							round++;
							}
							if(cut<=0){
								both = ((Lp+Rp-AI[X][1]-AI[X][2])/2);
								if(both==0){
									lr = (ran.nextInt(2)+1);
									if(lr == 1){
										if(Lp!=0){
										enemy = (ran.nextInt(9)+1);
										while((Lp-enemy)<0){
											enemy = (ran.nextInt(6)+1);
										}
										System.out.println("電腦將左邊減"+enemy);
										Lp=Lp-enemy;
										} else if(Lp == 0){
											lr = 2;
										}
									}
									if(lr == 2){
										if(Rp!=0){
										enemy = (ran.nextInt(9)+1);
										while((Rp-enemy)<0){
											enemy = (ran.nextInt(6)+1);
										}
										System.out.println("電腦將右邊減"+enemy);
										Rp=Rp-enemy;
										} else if(Rp == 0){
											lr = 1;
										}
									}
								}
								else if(both!=0){
									if((Lp+Rp)==0){
										continue;
									
								}
								Lp=Lp-both;
								Rp=Rp-both;
								System.out.println("電腦將兩邊各減"+both);
								System.out.println("現在數字為"+Lp+","+Rp);
								player=1;
								round++;
								}
							}
						}
					}
				}
				else if(Lp-Rp<0){
					for(i=1;i<=11;i++){
						if(Lp == AI[i][1]){
							cut = Rp-AI[i][2];
							if(cut>0){
							Rp = Rp-cut;
							System.out.println("電腦把右邊減"+cut);
							System.out.println("現在數字為"+Lp+","+Rp);
							player=1;
							round++;
							}
							if(cut<=0){
								both = ((Lp+Rp-AI[X][1]-AI[X][2])/2);
								if(both==0){
									lr = (ran.nextInt(2)+1);
									if(lr == 1){
										if(Lp!=0){
										enemy = (ran.nextInt(9)+1);
										while((Lp-enemy)<0){
											enemy = (ran.nextInt(6)+1);
										}
										System.out.println("電腦將左邊減"+enemy);
										Lp=Lp-enemy;
										} else if(Lp == 0){
											lr = 2;
										}
									}
									if(lr == 2){
										if(Rp!=0){
										enemy = (ran.nextInt(9)+1);
										while((Rp-enemy)<0){
											enemy = (ran.nextInt(6)+1);
										}
										System.out.println("電腦將右邊減"+enemy);
										Rp=Rp-enemy;
										} else if(Rp == 0){
											lr = 1;
										}
									}
								}
								else if(both!=0){
									if((Lp+Rp)==0){
										continue;
									}
								Lp=Lp-both;
								Rp=Rp-both;
								System.out.println("電腦將兩邊各減"+both);
								System.out.println("現在數字為"+Lp+","+Rp);
								player=1;
								round++;
								}
							}
						}
						else if(Lp == AI[i][2]){
							cut = Rp-AI[i][1];
							if(cut>0){
							Rp = Rp-cut;
							System.out.println("電腦把右邊減"+cut);
							System.out.println("現在數字為"+Lp+","+Rp);
							player=1;
							round++;
							}
							if(cut<=0){
								both = ((Lp+Rp-AI[X][1]-AI[X][2])/2);
								if(both==0){
									lr = (ran.nextInt(2)+1);
									if(lr == 1){
										if(Lp!=0){
										enemy = (ran.nextInt(9)+1);
										while((Lp-enemy)<0){
											enemy = (ran.nextInt(6)+1);
										}
										System.out.println("電腦將左邊減"+enemy);
										Lp=Lp-enemy;
										} else if(Lp == 0){
											lr = 2;
										}
									}
									if(lr == 2){
										if(Rp!=0){
										enemy = (ran.nextInt(9)+1);
										while((Rp-enemy)<0){
											enemy = (ran.nextInt(6)+1);
										}
										System.out.println("電腦將右邊減"+enemy);
										Rp=Rp-enemy;
										} else if(Rp == 0){
											lr = 1;
										}
									}
								}
								else if(both!=0){
									if((Lp+Rp)==0){
										continue;
									}
								Lp=Lp-both;
								Rp=Rp-both;
								System.out.println("電腦將兩邊各減"+both);
								System.out.println("現在數字為"+Lp+","+Rp);
								player=1;
								round++;
								}
							}
						}
					}
				}
						if(Lp>Rp && player!=1){
									lr = (ran.nextInt(2)+1);							lr = (ran.nextInt(2)+1);
									if(lr == 1){
										if(Lp!=0){
										enemy = (ran.nextInt(9)+1);
										while((Lp-enemy)<0){
											enemy = (ran.nextInt(6)+1);
										}
										System.out.println("電腦將左邊減"+enemy);
										Lp=Lp-enemy;
										} else if(Lp == 0){
											lr = 2;
										}
									}
									if(lr == 2){
										if(Rp!=0){
										enemy = (ran.nextInt(9)+1);
										while((Rp-enemy)<0){
											enemy = (ran.nextInt(6)+1);
										}
										System.out.println("電腦將右邊減"+enemy);
										Rp=Rp-enemy;
										} else if(Rp == 0){
											lr = 1;
										}
									}
									player=1;
									round++;
						}
						else if(Lp<Rp && player!=1){
									lr = (ran.nextInt(2)+1);							lr = (ran.nextInt(2)+1);
									if(lr == 1){
										if(Lp!=0){
										enemy = (ran.nextInt(9)+1);
										while((Lp-enemy)<0){
											enemy = (ran.nextInt(6)+1);
										}
										System.out.println("電腦將左邊減"+enemy);
										Lp=Lp-enemy;
										} else if(Lp == 0){
											lr = 2;
										}
									}
									if(lr == 2){
										if(Rp!=0){
										enemy = (ran.nextInt(9)+1);
										while((Rp-enemy)<0){
											enemy = (ran.nextInt(6)+1);
										}
										System.out.println("電腦將右邊減"+enemy);
										Rp=Rp-enemy;
										} else if(Rp == 0){
											lr = 1;
										}
									}
									player=1;
									round++;
						}
		}
	}
	

	if(player==1){
	System.out.println("GAMEOVER！ 電腦勝利！");
	}
	else if(player==2){
		System.out.println("恭喜你勝利！");
	}
	}
}