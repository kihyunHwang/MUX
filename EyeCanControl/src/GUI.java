
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Font;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.FontUIResource;


public class GUI extends JFrame implements ActionListener
{
	private Action Action;
	private Devices Devices;
	private SettingWin SettingWin;
	private ImageIcon fan_off_icon;
	private ImageIcon fan_low_icon;
	private ImageIcon fan_high_icon;
	public ImageIcon fan_status_off;
	public ImageIcon fan_status_low;
	public ImageIcon fan_status_high;
	public ImageIcon led_on_icon;
	public ImageIcon led_off_icon;
	private ImageIcon SettingIcon;
	private ImageIcon window_open_icon;
	private ImageIcon window_close_icon;
	private ImageIcon emergency_message_icon;
	public JButton led_toggle;
	private JButton fan_off;
	private JButton fan_low;
	private JButton fan_high;
	public JButton fanStatus;
	private JButton window_toggle;
	private JButton emergency_message;
	private JButton btnSetting;
	
	public GUI(Action listener, Devices Devices) 
	{
		this.Action = listener;
		// �ʱ� ���¸� �����ϱ� ���ؼ� �о�´�.
		this.Devices = Devices;
		SettingWin = new SettingWin();
		
	}
	
	public void createUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0, 800, 600);
		setResizable(false);
		getContentPane().setLayout(null);
		setTitle("EyeCanControl");
		getContentPane().setBackground(Color.white);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("����", Font.PLAIN, 12)));
		/* �� �ʿ��Ѱ���??
		Image fan_off_image = fan_off_icon.getImage();
		BufferedImage fan_off_bi = new BufferedImage(fan_off_image.getWidth(null), fan_off_image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics g = fan_off_bi.createGraphics();
		g.drawImage(fan_off_image, 0, 0, this);
		*/
		// ��ǳ�� off ��ư
		fan_off_icon = new ImageIcon("image/fan_off.png");
		fan_off = new JButton(fan_off_icon);
		fan_off.setBorder(emptyBorder);
		fan_off.setName("fan_off");
		fan_off.setBounds(250, 180, 48, 48);
		fan_off.addActionListener(this);
		getContentPane().add(fan_off);
		
		//��ǳ�� �� ��ư
		fan_low_icon = new ImageIcon("image/fan_low.png");
		fan_low = new JButton(fan_low_icon);	
		fan_low.setBorder(emptyBorder);		
		fan_low.setName("fan_low");
		fan_low.setBounds(50, 180, 48, 48);
		fan_low.addActionListener(this);
		getContentPane().add(fan_low);
		
		//��ǳ�� �� ��ư
		fan_high_icon = new ImageIcon("image/fan_high.png");
		fan_high = new JButton(fan_high_icon);
		fan_high.setBorder(emptyBorder);
		fan_high.setName("fan_high");
		fan_high.setBounds(150, 180, 48, 48);
		fan_high.addActionListener(this);
		getContentPane().add(fan_high);
		
		// ��ǳ�� ���� ���� �̹���
		this.fanStatus = new JButton();
		fan_status_off = new ImageIcon("image/fan_status_off.png");
		fan_status_low = new ImageIcon("image/fan_status_low.png");
		fan_status_high = new ImageIcon("image/fan_status_high.png");
		
		if(Devices.getFanStatus() == 0)
		{
			fanStatus.setIcon(fan_status_off);
		}
		else if(Devices.getFanStatus() == 1)
		{
			fanStatus.setIcon(fan_status_low);
		}
		else 
		{
			fanStatus.setIcon(fan_status_high);
		}
		
		fanStatus.setBounds(100, 20, 110, 150);
		fanStatus.setBorder(emptyBorder);
		getContentPane().add(fanStatus);
		
		//LED on/off
		led_on_icon = new ImageIcon("image/led_on.png");
		led_off_icon = new ImageIcon("image/led_off.png");
		this.led_toggle = new JButton(led_off_icon);
		
		// LED �ʱ� ���� �̹��� ���� 
		if(Devices.getLedStatus())
		{
			led_toggle.setIcon(led_on_icon);
		}
		else
		{
			led_toggle.setIcon(led_off_icon);
		}
		led_toggle.setName("led_toggle");
		led_toggle.setBounds(50, 300, 110, 150);
		led_toggle.addActionListener(this);
		led_toggle.setBorder(emptyBorder);
		getContentPane().add(led_toggle);
		
		
		
		//â�� �ʱ� ���� �̹��� ����
		window_open_icon = new ImageIcon("image/window_open.png");
		window_close_icon = new ImageIcon("image/window_close.png");
		
		window_toggle = new JButton(window_close_icon); 
		
		if(Devices.getWindowStatus())
		{
			window_toggle.setIcon(window_open_icon);
		}
		else
		{
			window_toggle.setIcon(window_close_icon);
		}
		
		window_toggle.setName("window_toggle");
		window_toggle.setBounds(300, 80, 200, 150);
		window_toggle.addActionListener(this);
		window_toggle.setBorder(emptyBorder);
		getContentPane().add(window_toggle);
		
		// ���� �޽���
		emergency_message_icon = new ImageIcon("image/emergency_message.png");
		
		emergency_message = new JButton(emergency_message_icon);
		emergency_message.setBorder(emptyBorder);
		emergency_message.setBounds(550, 80, 128, 128);
		emergency_message.addActionListener(this);
		getContentPane().add(emergency_message);
		
		
		// ȯ�漳��
		SettingIcon = new ImageIcon("image/setting.png");
		btnSetting = new JButton(SettingIcon);	
		btnSetting.setBorder(emptyBorder);		
		btnSetting.setBounds(300, 300, 110, 150);
		btnSetting.addActionListener(this);
		getContentPane().add(btnSetting);
		
		setVisible(true);
	
	}

	public void actionPerformed(ActionEvent e) 
	{
		final int LedOff = 10;
		final int LedOn = 11;
		final int FanOff = 20;
		final int FanLow = 21;
		final int FanHigh = 22;
		final int WindowClose = 30;
		final int WindowOpen = 31;
		
		if(e.getSource().equals(led_toggle)) // ���� On or Off
		{
			if(Devices.getLedStatus())
	        {
	        	Action.CallSendData(LedOff);
	        	System.out.println("LED OFF");
	        }
	        else 
	        { 
	        	Action.CallSendData(LedOn);
	        	System.out.println("LED ON");
	        }	
		}
		else if(e.getSource().equals(fan_off)) // ��ǳ�� Off
		{
			if(Devices.getFanStatus() == 0)
			{
				new Thread() {   
					public void run() {   
						try {   
								Thread.sleep(2000);   
						} catch (InterruptedException e) {}   
						JOptionPane.getRootFrame().dispose();      
					}   
				}.start();   
				JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "�̹� ��ǳ�Ⱑ �����ֽ��ϴ�. \n(�� â�� 2�� �Ŀ� �ڵ����� �����ϴ�.) ");   
			}
			else
			{
				Action.CallSendData(FanOff); 
				System.out.println("fan is now off");
			}
		}
		else if(e.getSource().equals(fan_low)) // ��ǳ�� �� 
		{
			if(Devices.getFanStatus() == 1)
			{
				new Thread() {   
					public void run() {   
						try {   
								Thread.sleep(2000);   
						} catch (InterruptedException e) {}   
						JOptionPane.getRootFrame().dispose();      
					}   
				}.start();   
				JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "�̹� ��ǳ�Ⱑ ��ǳ���� �����Ǿ� �ֽ��ϴ�. \n(�� â�� 2�� �Ŀ� �ڵ����� �����ϴ�.) ");
			}
			else
			{
				Action.CallSendData(FanLow);
				System.out.println("fan is low");
			}
		}
		else if(e.getSource().equals(fan_high)) // ��ǳ�� ��
		{
			if(Devices.getFanStatus() == 2)
			{
				new Thread() {   
					public void run() {   
						try {   
								Thread.sleep(2000);   
						} catch (InterruptedException e) {}   
						JOptionPane.getRootFrame().dispose();      
					}   
				}.start();   
				JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "�̹� ��ǳ�Ⱑ ��ǳ���� �����Ǿ� �ֽ��ϴ�. \n(�� â�� 2�� �Ŀ� �ڵ����� �����ϴ�.) ");
			}
			else
			{
				Action.CallSendData(FanHigh);
				System.out.println("fan is high");
			}
		}
		else if(e.getSource().equals(emergency_message)) // Emergency Message
		{
			Action.SendEmergencyMessage();
		}
		else if(e.getSource().equals(window_toggle)) // â�� open or close 
		{
			if(Devices.getWindowStatus())
	        {
	        	Action.CallSendData(WindowClose);
	        	window_toggle.setIcon(window_close_icon);
	        	System.out.println("WINDOW CLOSE");
	        }
	        else 
	        { 
	        	Action.CallSendData(WindowOpen);
	        	window_toggle.setIcon(window_open_icon);
	        	System.out.println("WINDOW OPEN");
	        }	
		}
		else if(e.getSource().equals(btnSetting)) // Setting
		{
			SettingWin.setTitle("EyeCanControl-ȯ�漳��");
			SettingWin.setBounds(300, 100, 300, 300);
			SettingWin.setVisible(true);
		}
	}


}
