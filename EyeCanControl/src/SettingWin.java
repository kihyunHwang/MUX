import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


public class SettingWin extends JFrame implements ActionListener 
{
	private Setting Setting;
//	private JPanel SettingPanel;
//	private JTabbedPane TabPane;
	private Font font1 = new Font("돋움", Font.PLAIN, 12);
//	private JTabbedPane TabPane;
//	private JPanel SetMessagePanel;
	private JTextField txtMessage;
	private JButton btnSave;
	private Process Process;
	
	public SettingWin(Setting Setting)
	{
		this.Setting = Setting;
		createUI();
	}
	
	public void createUI()
	{
	/*
		SetMessagePanel = new JPanel();
		SetMessagePanel.setLayout(null);
		SetMessagePanel.setBackground(Color.white);
		SetMessagePanel.setVisible(true);
		*/
		//for contentPanel 
		setTitle("Setting about Message");
		getContentPane().setSize(300, 350);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		//	getContentPane().add(SetMessagePanel);
		
		
		
		JLabel lblMessage = new JLabel("응급메시지 설정");
		lblMessage.setBounds(20, 20, 100, 20);
		lblMessage.setFont(font1);
		//	SetMessagePanel.add(lblMessage);
		
		getContentPane().add(lblMessage);
		
		txtMessage = new JTextField();
		txtMessage.setBounds(20, 50, 100, 100);
		txtMessage.setText(Setting.getEmergencyMessage());
		txtMessage.addActionListener(this);
		
//		SetMessagePanel.add(txtMessage);
		
		getContentPane().add(txtMessage);
		btnSave = new JButton("저장");
		btnSave.setBounds(80, 150, 70, 30);
		btnSave.setFont(font1);
		btnSave.setVisible(true);
		getContentPane().add(btnSave);
//		SetMessagePanel.add(btnSave);
		btnSave.addActionListener(this);
		
		
		
	//	TabPane = new JTabbedPane();
		
		//SettingIP SettingIp = new SettingIP(Setting, TabPane);
	//	SettingMessage SettingMessage = new SettingMessage(Setting, TabPane);
	//	SettingInfo SettingInfo = new SettingInfo(TabPane);
		
//		getContentPane().add(SetMessagePanel);
		setVisible(true);
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnSave)) // 저장 버튼 클릭 시
		{
			String EmergencyMessage;
			EmergencyMessage = txtMessage.getText();
			Setting.setEmergencyMessage(EmergencyMessage);
			
			new Thread() {   
				public void run() {   
					try {   
							Thread.sleep(2000);   
					} catch (InterruptedException e) {}   
					JOptionPane.getRootFrame().dispose();      
				}   
			}.start();   
			try {
				Process = new ProcessBuilder("osk.exe").start();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.err.println("에러! 외부 명령 실행에 실패했습니다.\n" + e1.getMessage());
			}
			JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "저장되었습니다. \n(이 창은 2초 후에 자동으로 닫힙니다.) ");	
		}
		else if(e.getSource().equals(txtMessage))
		{
			System.out.println("is clicked!!");
			try {
				Process Process = new ProcessBuilder("notepad.exe").start();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.err.println("에러! 외부 명령 실행에 실패했습니다.\n" + e1.getMessage());
			}
		}
	}
}

