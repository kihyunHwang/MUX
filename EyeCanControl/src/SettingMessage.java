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


public class SettingMessage implements ActionListener
{
	private Setting Setting;
	private JTabbedPane TabPane;
	private JPanel SetMessagePanel;
	private JTextField txtMessage;
	private JButton btnSave;
	private Font font1 = new Font("돋움", Font.PLAIN, 12);
	
	public SettingMessage(Setting Setting, JTabbedPane Panel) 
	{
		this.Setting = Setting;
		this.TabPane = Panel;
		createSetMessagePanel();
		TabPane.addTab("응급메시지", SetMessagePanel);
	}
	
	public void createSetMessagePanel()
	{
		SetMessagePanel = new JPanel();
		SetMessagePanel.setLayout(null);
		SetMessagePanel.setBackground(Color.WHITE);
		
		JLabel lblMessage = new JLabel("응급메시지 설정");
		lblMessage.setBounds(20, 20, 100, 20);
		lblMessage.setFont(font1);
		SetMessagePanel.add(lblMessage);
		
		txtMessage = new JTextField();
		txtMessage.setBounds(20, 50, 100, 20);
		txtMessage.setText(Setting.getEmergencyMessage());
		SetMessagePanel.add(txtMessage);
		
		btnSave = new JButton("저장");
		btnSave.setBounds(80, 150, 70, 30);
		btnSave.setFont(font1);
		SetMessagePanel.add(btnSave);
		btnSave.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
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
			JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "저장되었습니다. \n(이 창은 2초 후에 자동으로 닫힙니다.) ");
		}
		
	}
}
