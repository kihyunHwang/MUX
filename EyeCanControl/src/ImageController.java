public class ImageController 
{
	GUI GUI; 
	
	public ImageController(GUI GUI) 
	{
		this.GUI = GUI;
	}
	public void changeImage(int code){
		GUI.setButtonImage(code);
	}

}
