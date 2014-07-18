
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
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
	private Setting Setting;
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
	private ImageIcon north_image;
	private JButton led_toggle;
	private JButton fan_off;
	private JButton fan_low;
	private JButton fan_high;
	private JButton fanStatus;
	private JButton window_toggle;
	private JButton emergency_message;
	private JButton btnSetting;
	private JButton north_button;
	private boolean is_fan_clicked = false;
	
	public GUI(Action listener, Devices Devices, Setting Setting) 
	{
		this.Action = listener;
		// 초기 상태를 결정하기 위해서 읽어온다.
		this.Devices = Devices;
		SettingWin = new SettingWin(Setting);
		
	}
	
	public void createUI()
	{
		Border emptyBorder = BorderFactory.createEmptyBorder();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.white);
		setBounds(0,0, 900, 500);
		
		setResizable(false);
		
		//getContentPane().setLayout(new BorderLayout());
		setLayout(null);
		setTitle("EyeCanControl");
		getContentPane().setBackground(Color.white);
	//	getContentPane().addMouseListener();
		
		
		//맨위에 버튼
		north_image = new ImageIcon("image/EyeCanControl.png");	
		north_button = new JButton(north_image);
		north_button.setBorder(emptyBorder);		
		north_button.setBounds(0,0, 900, 71);
		north_button.addActionListener(this);
		getContentPane().add(north_button);
		
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("돋움", Font.PLAIN, 12)));
		
		
		// 선풍기 off 버튼
		fan_off_icon = new ImageIcon("image/fan_off.png");
		fan_off = new JButton(fan_off_icon);
		fan_off.setBorder(emptyBorder);
		fan_off.setName("fan_off");
		fan_off.setBounds(210, 80, 48, 48);
		fan_off.setBackground(Color.white);
		fan_off.addActionListener(this);
//		getContentPane().add(fan_off);
		
		//선풍기 약 버튼
		fan_low_icon = new ImageIcon("image/fan_low.png");
		fan_low = new JButton(fan_low_icon);	
		fan_low.setBorder(emptyBorder);		
		fan_low.setName("fan_low");
		fan_low.setBounds(210, 150, 48, 48);
		fan_low.addActionListener(this);
		fan_low.setBackground(Color.white);
//		getContentPane().add(fan_low);
		
		//선풍기 강 버튼
		fan_high_icon = new ImageIcon("image/fan_high.png");
		fan_high = new JButton(fan_high_icon);
		fan_high.setBorder(emptyBorder);
		fan_high.setName("fan_high");
		fan_high.setBounds(210, 220, 48, 48);
		fan_high.addActionListener(this);
		fan_high.setBackground(Color.white);
//		getContentPane().add(fan_high);
		
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
		
		fanStatus.setBounds(50, 110, 128, 128);
		fanStatus.setBorder(emptyBorder);
		fanStatus.addActionListener(this);
		getContentPane().add(fanStatus);
		
		//LED on/off 버튼
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
		led_toggle.setBounds(50, 300, 128, 128);
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
		window_toggle.setBounds(328, 110, 128, 128);
		window_toggle.addActionListener(this);
		window_toggle.setBorder(emptyBorder);
		getContentPane().add(window_toggle);
		
		// 응급 메시지
		emergency_message_icon = new ImageIcon("image/emergency_message.png");
		emergency_message = new JButton(emergency_message_icon);
		emergency_message.setBorder(emptyBorder);
		emergency_message.setBounds(606, 110, 128, 128);
		emergency_message.addActionListener(this);
		emergency_message.setBackground(Color.white);
		getContentPane().add(emergency_message);
		
		// 환경설정
		SettingIcon = new ImageIcon("image/setting.png");
		btnSetting = new JButton(SettingIcon);	
		btnSetting.setBorder(emptyBorder);		
		btnSetting.setBounds(328, 300, 128, 128);
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
		
		if(e.getSource().equals(north_button))
		{
			ECCPopup ECCPopup = new ECCPopup();
			ECCPopup.createUI();
		}
		else if(e.getSource().equals(led_toggle)) // 전등 On or Off
		{
			if(Devices.getLedStatus())
	        {
	        	Action.CallSendData(LedOff, 0);
	        	System.out.println("LED OFF");
	        }
	        else 
	        { 
	        	Action.CallSendData(LedOn, 0);
	        	System.out.println("LED ON");
	        }	
			if(is_fan_clicked == true)
			{
				getContentPane().remove(fan_high);
				getContentPane().remove(fan_low);
				getContentPane().remove(fan_off);
				getContentPane().repaint();
				is_fan_clicked = false;
			}
		}
		else if(e.getSource().equals(fanStatus)) // 선풍기 
		{
			if(is_fan_clicked == false)
			{
				getContentPane().add(fan_high);
				getContentPane().add(fan_low);
				getContentPane().add(fan_off);
				getContentPane().repaint();
				is_fan_clicked = true;
			}
			else 
			{
				getContentPane().remove(fan_high);
				getContentPane().remove(fan_low);
				getContentPane().remove(fan_off);
				getContentPane().repaint();
				is_fan_clicked = false;
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
				Action.CallSendData(FanOff, 0); 
				System.out.println("fan is now off");
				getContentPane().remove(fan_high);
				getContentPane().remove(fan_low);
				getContentPane().remove(fan_off);
				getContentPane().repaint();
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
				Action.CallSendData(FanLow, 0);
				System.out.println("fan is low");
				getContentPane().remove(fan_high);
				getContentPane().remove(fan_low);
				getContentPane().remove(fan_off);
				getContentPane().repaint();
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
				Action.CallSendData(FanHigh, 0);
				System.out.println("fan is high");
				getContentPane().remove(fan_high);
				getContentPane().remove(fan_low);
				getContentPane().remove(fan_off);
				getContentPane().repaint();
			}
			
		}
		else if(e.getSource().equals(emergency_message)) // Emergency Message
		{
			try {
				Action.SendEmergencyMessage();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(is_fan_clicked == true)
			{
				getContentPane().remove(fan_high);
				getContentPane().remove(fan_low);
				getContentPane().remove(fan_off);
				getContentPane().repaint();
				is_fan_clicked = false;
			}
		}
		else if(e.getSource().equals(window_toggle)) // 창문 open or close
		{
			if(Devices.getWindowStatus())
	        {
	        	Action.CallSendData(WindowClose, 0);
	        	System.out.println("WINDOW CLOSE");
	        }
	        else 
	        { 
	        	Action.CallSendData(WindowOpen, 0);
	        	System.out.println("WINDOW OPEN");
	        }
			
			if(is_fan_clicked == true)
			{
				getContentPane().remove(fan_high);
				getContentPane().remove(fan_low);
				getContentPane().remove(fan_off);
				getContentPane().repaint();
				is_fan_clicked = false;
			}
		}
		else if(e.getSource().equals(btnSetting)) // Setting
		{
			SettingWin.setTitle("EyeCanControl-환경설정");
			SettingWin.setBounds(300, 100, 350, 300);
			SettingWin.setVisible(true);
			
			
			if(is_fan_clicked == true)
			{
				getContentPane().remove(fan_high);
				getContentPane().remove(fan_low);
				getContentPane().remove(fan_off);
				getContentPane().repaint();
				is_fan_clicked = false;
			}
		}
	}
}
