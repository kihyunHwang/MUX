
public class Devices 
{

	 //ledStatus = true �����ִ� ����, false ���� �ִ� ����	
	private boolean ledStatus;
	private int fanStatus; // 0:off, 1:low, 2:high
	//windowStatus = true ���� ����, false ���� ����.
	private boolean windowStatus;
	
	public Devices()
	{
		this.ledStatus = false;
		this.fanStatus = 0;
		this.ledStatus = false;
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
	
}
