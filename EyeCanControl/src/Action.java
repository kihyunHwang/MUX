import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import javax.swing.JOptionPane;

public class Action
{
	private Communicator Communicator;
	private Devices Devices;
	private Setting Setting;
	private ImageController ImageController;
	private PrintWriter out;
	
	//final static int initStatus[] = {99};
	//final static int Stop[] = {90};

	public Action(Communicator Communicator, Devices Devices, Setting Setting)
	{
		this.Communicator = Communicator;
		this.Devices = Devices;
		this.Setting = Setting;
	}
	
	public void setImageController (ImageController ImageController)
	{
		this.ImageController = ImageController;
	}
	
	// 아두이노로 데이터 보내고 Device 상태 변경
	public void CallSendData(int code)
	{
		Communicator.writeData(code); // 아두이노로 데이터 전송
		ImageController.changeImage(code);
		
		if(code == 10)
		{
			Devices.setLedStatus(false);
		}
		else if(code == 11)
		{
			Devices.setLedStatus(true);
		}
		else if(code == 20)
		{
			Devices.setFanStatus(0);
		}
		else if(code == 21)
		{
			Devices.setFanStatus(1);
		}
		else if(code == 22)
		{
			Devices.setFanStatus(2);
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
	
	// App으로 초기 상태 전송 
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
		out.println(CurrentStatus); 
	}

	// 응급 메시지 전송
	public void SendEmergencyMessage() throws IOException
	{
		Sender sender = new Sender("AIzaSyBPaueJFJJ1o_4lg5IMUKp6Vkkf-swn9gM");
		// Test Device
		String regId = "APA91bHoSrHz1oD768bxCXiusFAnZ16niUIR8yb2qTh7pIpWqhfTTa1-kTrT4A_P9lfECa3pIu_gGjX8J67nl-UzmyO7f2QsfDCCRX37fj0ElbVmzhPHHmoq7nDKtBGE3wDfzbInMCGYHR7ergI0zWAUU6msM2FMqJ9sTsFXE9efFZhSgh9vGYw";
		
		// 응급 메시지 셋팅
		String EmergencyMessage;
		EmergencyMessage = Setting.getEmergencyMessage();
		
		//msg에 응급메시지를 넣어서 보낸다
		Message message = new Message.Builder().addData("msg", EmergencyMessage).build();   
		
		List<String> list = new ArrayList<String>();
		list.add(regId);
		
		// 메시지 전송
		MulticastResult multiResult = sender.send(message, list, 5);

		if (multiResult != null) 
		{
			List<Result> resultList = multiResult.getResults();
			for (Result result : resultList) 
			{
				System.out.println("응급 메시지 전송 완료 : " + result.getMessageId());
			}
		}
		
		// 전송 완료 메시지 창 띄우기
		new Thread() {   
			public void run() {   
				try {   
						Thread.sleep(2000);   
				} catch (InterruptedException e) {}   
				JOptionPane.getRootFrame().dispose();      
			}   
		}.start();   
		JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "보호자에게 메시지를 전송하였습니다. \n(이 창은 2초 후에 자동으로 닫힙니다.) ");
	}
}
