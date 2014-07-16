import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


public class SettingIP implements ActionListener
{
	private Setting Setting;
	private JTabbedPane TabPane;
	private JPanel SetIpPanel;
	private JTextField txtServerIP;
	private JTextField txtServerPort;
	private JButton btnSave;
	private Font font1 = new Font("돋움", Font.PLAIN, 12);
	
	public SettingIP(Setting Setting, JTabbedPane Panel) 
	{
		this.Setting = Setting;
		this.TabPane = Panel;
		createSetIpPanel();
		TabPane.addTab("IP&PORT", SetIpPanel);
	}
	
	public void createSetIpPanel()
	{
		SetIpPanel = new JPanel();
		SetIpPanel.setLayout(null);
		SetIpPanel.setBackground(Color.WHITE);
		
		JLabel lblComPort = new JLabel("COMPORT : ");
		lblComPort.setBounds(20, 20, 70, 20);
		SetIpPanel.add(lblComPort);
		
		JTextField txtComPort = new JTextField();
		txtComPort.setBounds(100, 20, 50, 20);
		txtComPort.setText(Setting.getComPort());
		txtComPort.setEditable(false);
		SetIpPanel.add(txtComPort);
		
		JLabel lblServerIP = new JLabel("ServerIP : ");
		lblServerIP.setBounds(20, 60, 70, 20);
		SetIpPanel.add(lblServerIP);
		
		txtServerIP = new JTextField();
		txtServerIP.setBounds(100, 60, 90, 20);
		txtServerIP.setText(Setting.getServerIP());
		SetIpPanel.add(txtServerIP);
		
		JLabel lblServerPort = new JLabel("ServerPort : ");
		lblServerPort.setBounds(20, 100, 80, 20);
		SetIpPanel.add(lblServerPort);
		
		txtServerPort = new JTextField();
		txtServerPort.setBounds(100, 100, 50, 20);
		txtServerPort.setText(Integer.toString(Setting.getServerPort()));
		SetIpPanel.add(txtServerPort);
		
		btnSave = new JButton("저장");
		btnSave.setBounds(80, 150, 70, 30);
		btnSave.setFont(font1);
		SetIpPanel.add(btnSave);
		btnSave.addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource().equals(btnSave)) // 저장 버튼 클릭 시
		{
			String ServerIP;
			int ServerPort;
			ServerIP = txtServerIP.getText();
			ServerPort = Integer.parseInt(txtServerPort.getText());
			Setting.setServerIP(ServerIP);
			Setting.setServerPort(ServerPort);
			
			new Thread() {   
				public void run() {   
					try {   
							Thread.sleep(2000);   
					} catch (InterruptedException e) {}   
					JOptionPane.getRootFrame().dispose();      
				}   
			}.start();   
			JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "저장되었습니다. \n(이 창은 2초 후에 자동으로 닫힙니다.) ");
			/** 서버 다시 연결 코드 추가해야함 !! **/
		}
		
		
	}
}
