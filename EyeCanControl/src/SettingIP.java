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


public class SettingIP extends Setting implements ActionListener
{
	private JTabbedPane TabPane;
	private JPanel SetIpPanel;
	private JTextField txtServerIP;
	private JTextField txtServerPort;
	private JButton btnSave;
	private Font font1 = new Font("����", Font.PLAIN, 12);
	
	public SettingIP(JTabbedPane Panel) 
	{
		//super();
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
		txtComPort.setText(ComPort);
		txtComPort.setEditable(false);
		SetIpPanel.add(txtComPort);
		
		JLabel lblServerIP = new JLabel("ServerIP : ");
		lblServerIP.setBounds(20, 60, 70, 20);
		SetIpPanel.add(lblServerIP);
		
		txtServerIP = new JTextField();
		txtServerIP.setBounds(100, 60, 90, 20);
		txtServerIP.setText(ServerIP);
		SetIpPanel.add(txtServerIP);
		
		JLabel lblServerPort = new JLabel("ServerPort : ");
		lblServerPort.setBounds(20, 100, 80, 20);
		SetIpPanel.add(lblServerPort);
		
		txtServerPort = new JTextField();
		txtServerPort.setBounds(100, 100, 50, 20);
		txtServerPort.setText(Integer.toString(ServerPort));
		SetIpPanel.add(txtServerPort);
		
		btnSave = new JButton("����");
		btnSave.setBounds(80, 150, 70, 20);
		btnSave.setFont(font1);
		SetIpPanel.add(btnSave);
		btnSave.addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource().equals(btnSave)) // ���� ��ư Ŭ�� ��
		{
			String ServerIP;
			int ServerPort;
			ServerIP = txtServerIP.getText();
			ServerPort = Integer.parseInt(txtServerPort.getText());
			setServerIP(ServerIP);
			setServerPort(ServerPort);
			
			new Thread() {   
				public void run() {   
					try {   
							Thread.sleep(2000);   
					} catch (InterruptedException e) {}   
					JOptionPane.getRootFrame().dispose();      
				}   
			}.start();   
			JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "����Ǿ����ϴ�. \n(�� â�� 2�� �Ŀ� �ڵ����� �����ϴ�.) ");
			/** ���� �ٽ� ���� �ڵ� �߰��ؾ��� !! **/
		}
		
		
	}
}
