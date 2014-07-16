public class ActionImage 
{
	GUI GUI; 
	public ActionImage(GUI GUI) 
	{
		this.GUI = GUI;
	}
	
	// Change Led Image
	public void changeLedImage(int code)
	{
		
		//changeLedImage2(code);
		
		if(code == 10)
		{
			GUI.led_toggle.setIcon(GUI.led_off_icon);
		}
		else if(code == 11)
		{
			GUI.led_toggle.setIcon(GUI.led_on_icon);
		}
		
	}
	
	// Change Fan Image
	public void changeFanImage(int code)
	{
		if(code == 20)
		{
			GUI.fanStatus.setIcon(GUI.fan_status_off);
		}
		else if(code == 21)
		{
			GUI.fanStatus.setIcon(GUI.fan_status_low);
		}
		else if(code == 22)
		{
			GUI.fanStatus.setIcon(GUI.fan_status_high);
		}
	}
	
}
