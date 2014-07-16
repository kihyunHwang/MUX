
public class EyeCanControl 
{
	private Setting Setting;
	private static Communicator Communicator;
	private String ComPort;
	
	public EyeCanControl(Setting Setting)
	{
		this.Setting = Setting;
		ComPort = Setting.getComPort();
		System.out.println("init ComPort = " + ComPort);
		createObjects();
	}
	
	private void createObjects()
	{
		Communicator = new Communicator(ComPort);
		Communicator.searchForPorts();
		Communicator.connect();
        if (Communicator.getConnected() == true)
        {
            if (Communicator.initIOStream() == true)
            {
            	Communicator.initListener();
            }
        }
	}
	
	public static void main(String[] args) 
	{
		Setting Setting = new Setting();
		Devices Devices = new Devices();
		EyeCanControl ECC = new EyeCanControl(Setting);
		Action Action = new Action(Communicator, Devices, Setting);
		GUI GUI = new GUI(Action, Devices);
		GUI.createUI();
		ActionImage ActionImage = new ActionImage(GUI);
		Action.SetActionImage(ActionImage);
		Thread desktopServerThread = new Thread(new TCPserver(Action, Setting));
		desktopServerThread.start(); // APP ������ ���� thread ����
	}
}