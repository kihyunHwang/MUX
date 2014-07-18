
public class Devices 
{

	// ledStatus = true 켜져있는 상태, false 꺼져 있는 상태
	private boolean ledStatus;
	private int fanStatus; // 0:off, 1:low, 2:high
	// windowStatus = true 열린 상태, false 닫힌 상태
	private boolean windowStatus;
	private boolean urineStatus; // true:메세지전송, false:default
	
	public Devices()
	{
		this.ledStatus = false;
		this.fanStatus = 0;
		this.ledStatus = false;
		this.windowStatus = false;
		this.urineStatus = false;
	}

	
	public boolean getLedStatus() 
	{
		return ledStatus;
	}

	public void setLedStatus(boolean ledStatus) 
	{
		this.ledStatus = ledStatus;
	}

	public int getFanStatus() 
	{
		return fanStatus;
	}

	public void setFanStatus(int fanStatus) 
	{
		this.fanStatus = fanStatus;
	}
	
	public boolean getWindowStatus() 
	{
		return windowStatus;
	}

	public void setWindowStatus(boolean windowStatus) 
	{
		this.windowStatus = windowStatus;
	}


	public boolean getUrineStatus() 
	{
		return urineStatus;
	}


	public void setUrineStatus(boolean urineStatus) 
	{
		this.urineStatus = urineStatus;
	}
	
	
}
