
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class TCPserver implements Runnable {
//	public static final int ServerPort = 9000; // ex: 5555
//	public static final String ServerIP = "192.168.0.13"; // ex: 192.168.0.100
	private Action Action;
	private Setting Setting;
	private int ServerPort;
	private String ServerIP;
	private boolean isStart = false;
	
	final int led_off = 10;
	final int led_on = 11;
	final int fan_off = 20;
	final int fan_low = 21;
	final int fan_high = 22;
	final int window_close = 30;
	final int window_open = 31;
 
	public TCPserver(Action Action, Setting setting)
	{
		this.Action = Action;
		this.Setting = setting;
		this.ServerIP = Setting.getServerIP();
		this.ServerPort = Setting.getServerPort();
	}
	
	public void run() 
	{
		try{
			System.out.println("S: Connecting...");
			System.out.println("ServerPort : " + ServerPort);
			ServerSocket serverSocket = new ServerSocket(ServerPort);
			while (true) 
			{
				Socket client = serverSocket.accept();
				System.out.println("\n S: Receiving...");
				
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
					PrintWriter out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(client.getOutputStream())),true);
					String str = in.readLine();
					System.out.println("S: Received: '" + str + "'");
					
					if(str.equals("10")) // Led off
					{
						Action.CallSendData(led_off, 1);
					}
					else if(str.equals("11")) // Led on
					{
						Action.CallSendData(led_on, 1);
					}
					else if(str.equals("20")) // Fan off
					{
						Action.CallSendData(fan_off, 1);
					}
					else if(str.equals("21")) // Fan low
					{
						Action.CallSendData(fan_low, 1);
					}
					else if(str.equals("22")) // Fan high
					{
						Action.CallSendData(fan_high, 1);
					}
					else if(str.equals("30")) // Window close
					{
						Action.CallSendData(window_close, 1);
					}
					else if(str.equals("31")) // Window open
					{
						Action.CallSendData(window_open, 1);
					}
					else if(str.equals("50")) // 소변통 비움 
					{
						Action.ConfirmUrine();
					}
					else if(str.startsWith("IP=")) // App에서 IP 전송
					{
						String AppIp;
						AppIp = str.substring(3);
						Action.setAppIP(AppIp);
					}
					else if(str.equals("99")) // 현재 Device들의 상태 전송 요청
					{	
						Action.SendInitStatus(out);
					}
					else if(str.equals("90")) // App 종료
					{
						Action.setIsAppConnected(false);
					}
					
					//out.println("Server Received "+ str);
				} catch(Exception e) {
					System.out.println("S: Error");
					e.printStackTrace();
				} finally {
					client.close();
					System.out.println("S: Done.");
				}
			}
		} catch (Exception e) {
			System.out.println("S: Error");
			e.printStackTrace();
		}
	}
}
