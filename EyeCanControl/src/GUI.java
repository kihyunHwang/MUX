
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Font;
import java.io.IOException;

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
	private ImageIcon fan_status_off;
	private ImageIcon fan_status_low;
	private ImageIcon fan_status_high;
	private ImageIcon led_on_icon;
	private ImageIcon led_off_icon;
	private ImageIcon SettingIcon;
	private ImageIcon window_open_icon;
	private ImageIcon window_close_icon;
	private ImageIcon emergency_message_icon;
	private JButton led_toggle;
	private JButton fan_off;
	private JButton fan_low;
	private JButton fan_high;
	private JButton fanStatus;
	private JButton window_toggle;
	private JButton emergency_message;
	private JButton btnSetting;
	
	public GUI(Action listener, Devices Devices) 
	{
		this.Action = listener;
		// 초기 상태를 결정하기 위해서 읽어온다.
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
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("돋움", Font.PLAIN, 12)));
		/* 왜 필요한거지??
		Image fan_off_image = fan_off_icon.getImage();
		BufferedImage fan_off_bi = new BufferedImage(fan_off_image.getWidth(null), fan_off_image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics g = fan_off_bi.createGraphics();
		g.drawImage(fan_off_image, 0, 0, this);
		*/
		// 선풍기 off 버튼
		fan_off_icon = new ImageIcon("image/fan_off.png");
		fan_off = new JButton(fan_off_icon);
		fan_off.setBorder(emptyBorder);
		fan_off.setName("fan_off");
		fan_off.setBounds(250, 180, 48, 48);
		fan_off.addActionListener(this);
		getContentPane().add(fan_off);
		
		//선풍기 약 버튼
		fan_low_icon = new ImageIcon("image/fan_low.png");
		fan_low = new JButton(fan_low_icon);	
		fan_low.setBorder(emptyBorder);		
		fan_low.setName("fan_low");
		fan_low.setBounds(50, 180, 48, 48);
		fan_low.addActionListener(this);
		getContentPane().add(fan_low);
		
		//선풍기 강 버튼
		fan_high_icon = new ImageIcon("image/fan_high.png");
		fan_high = new JButton(fan_high_icon);
		fan_high.setBorder(emptyBorder);
		fan_high.setName("fan_high");
		fan_high.setBounds(150, 180, 48, 48);
		fan_high.addActionListener(this);
		getContentPane().add(fan_high);
		
		// 선풍기 현재 상태 이미지
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
		
		// LED 초기 상태 이미지 셋팅
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
		
		// 창문 초기 상태 이미지 셋팅
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
		
		// 응급 메시지
		emergency_message_icon = new ImageIcon("image/emergency_message.png");
		
		emergency_message = new JButton(emergency_message_icon);
		emergency_message.setBorder(emptyBorder);
		emergency_message.setBounds(550, 80, 128, 128);
		emergency_message.addActionListener(this);
		getContentPane().add(emergency_message);
		
		// 환경설정
		SettingIcon = new ImageIcon("image/setting.png");
		btnSetting = new JButton(SettingIcon);	
		btnSetting.setBorder(emptyBorder);		
		btnSetting.setBounds(300, 300, 110, 150);
		btnSetting.addActionListener(this);
		getContentPane().add(btnSetting);
		
		setVisible(true);
	
	}

	// code = 10 : LEDOff, 11 : LEDOn 
	// 20 : FanOff, 21 : FanLow, 22 : FanHigh, 
	// 30 : WindowClose, 31 : WindowOpen
	public void setButtonImage(int code)
	{
		if(code == 10)
		{
			this.led_toggle.setIcon(led_off_icon);
		}
		else if(code == 11)
		{
			this.led_toggle.setIcon(led_on_icon);
		}
		else if(code == 20)
		{
			this.fanStatus.setIcon(fan_status_off);
		}
		else if(code == 21)
		{
			this.fanStatus.setIcon(fan_status_low);
		}
		else if(code == 22)
		{
			this.fanStatus.setIcon(fan_status_high);
		}
		else if(code == 30)
		{
			this.window_toggle.setIcon(window_close_icon);
		}
		else if(code == 31)
		{
			this.window_toggle.setIcon(window_open_icon);
		}
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
		
		if(e.getSource().equals(led_toggle)) // 전등 On or Off
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
		else if(e.getSource().equals(fan_off)) // 선풍기 Off
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
				JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "이미 선풍기가 꺼져있습니다. \n(이 창은 2초 후에 자동으로 닫힙니다.) ");   
			}
			else
			{
				Action.CallSendData(FanOff); 
				System.out.println("fan is now off");
			}
		}
		else if(e.getSource().equals(fan_low)) // 선풍기 약
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
				JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "이미 선풍기가 약풍으로 설정되어 있습니다. \n(이 창은 2초 후에 자동으로 닫힙니다.) ");
			}
			else
			{
				Action.CallSendData(FanLow);
				System.out.println("fan is low");
			}
		}
		else if(e.getSource().equals(fan_high)) // 선풍기 강
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
				JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "이미 선풍기가 강풍으로 설정되어 있습니다. \n(이 창은 2초 후에 자동으로 닫힙니다.) ");
			}
			else
			{
				Action.CallSendData(FanHigh);
				System.out.println("fan is high");
			}
		}
		else if(e.getSource().equals(emergency_message)) // Emergency Message
		{
			try {
				Action.SendEmergencyMessage();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(window_toggle)) // 창문 open or close
		{
			if(Devices.getWindowStatus())
	        {
	        	Action.CallSendData(WindowClose);
	        	System.out.println("WINDOW CLOSE");
	        }
	        else 
	        { 
	        	Action.CallSendData(WindowOpen);
	        	System.out.println("WINDOW OPEN");
	        }	
		}
		else if(e.getSource().equals(btnSetting)) // Setting
		{
			SettingWin.setTitle("EyeCanControl-환경설정");
			SettingWin.setBounds(300, 100, 300, 300);
			SettingWin.setVisible(true);
		}
	}


}
