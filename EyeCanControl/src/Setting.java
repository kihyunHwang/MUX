
public class Setting 
{
	protected String ServerIP;
	protected int ServerPort;
	protected String ComPort;
	protected String EmergencyMessage;
	
	public Setting()
	{
		this.ServerIP = "192.168.0.13";
		this.ServerPort = 9000;
		this.ComPort = "COM4";
		this.EmergencyMessage = "긴급 상황!!";
	}

	public String getServerIP() 
	{
		return ServerIP;
	}

	public void setServerIP(String serverIP) 
	{
		this.ServerIP = serverIP;
	}

	public int getServerPort() 
	{
		return ServerPort;
	}

	public void setServerPort(int serverPort) 
	{
		this.ServerPort = serverPort;
	}

	public String getComPort() 
	{
		return ComPort;
	}

	public void setComPort(String comPort) 
	{
		this.ComPort = comPort;
	}

	public String getEmergencyMessage() 
	{
		return EmergencyMessage;
	}

	public void setEmergencyMessage(String emergencyMessage) 
	{
		this.EmergencyMessage = emergencyMessage;
		System.out.println("chage : " + EmergencyMessage);
	}
	
	
	
}
