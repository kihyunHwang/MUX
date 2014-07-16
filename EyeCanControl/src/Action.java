import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class Action
{
	private Communicator Communicator;
	private Devices Devices;
	private Setting Setting;
	private ActionImage ActionImage;
	private PrintWriter out;
	
	//final static int initStatus[] = {99};
	//final static int Stop[] = {90};

	public Action(Communicator Communicator, Devices Devices, Setting Setting)
	{
		this.Communicator = Communicator;
		this.Devices = Devices;
		this.Setting = Setting;
	}
	
	public void SetActionImage(ActionImage ActionImage)
	{
		this.ActionImage = ActionImage;
	}

	// �Ƶ��̳�� ������ ������ Device ���� ����
	public void CallSendData(int code)
	{
		Communicator.writeData(code); // �Ƶ��̳�� ������ ����
		
		if(code == 10)
		{
			Devices.setLedStatus(false);
			ActionImage.changeLedImage(code);
		}
		else if(code == 11)
		{
			Devices.setLedStatus(true);
			ActionImage.changeLedImage(code);
		}
		else if(code == 20)
		{
			Devices.setFanStatus(0);
			ActionImage.changeFanImage(code);
		}
		else if(code == 21)
		{
			Devices.setFanStatus(1);
			ActionImage.changeFanImage(code);
		}
		else if(code == 22)
		{
			Devices.setFanStatus(2);
			ActionImage.changeFanImage(code);
		}
		else if(code == 30)
		{
			Devices.setWindowStatus(false);
		}
		else if(code == 31)
		{
			Devices.setWindowStatus(true);
		}
	}

	public void SendInitStatus(PrintWriter out)
	{
		String LedStatus;
		String FanStatus;
		String CurrentStatus;
		
		this.out = out;
		
		// Led
		if(Devices.getLedStatus())
		{
			LedStatus = "11";
		}
		else
		{
			LedStatus = "10";
		}
		// Fan
		FanStatus = "2" + Integer.toString(Devices.getFanStatus());
		
		// CurrentStatus
		CurrentStatus = LedStatus + FanStatus;
		out.print(CurrentStatus); 
	}

	public void SendEmergencyMessage()
	{
		String EmergencyMessage;
		EmergencyMessage = Setting.getEmergencyMessage();
		out.println(EmergencyMessage);
		new Thread() {   
			public void run() {   
				try {   
						Thread.sleep(2000);   
				} catch (InterruptedException e) {}   
				JOptionPane.getRootFrame().dispose();      
			}   
		}.start();   
		JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "��ȣ�ڿ��� �޽����� �����Ͽ����ϴ�. \n(�� â�� 2�� �Ŀ� �ڵ����� �����ϴ�.) ");
	}
}
