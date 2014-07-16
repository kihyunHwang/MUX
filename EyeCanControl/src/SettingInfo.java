import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class SettingInfo 
{
	private JTabbedPane TabPane;
	private JPanel SetInfoPanel;
	
	public SettingInfo(JTabbedPane Panel)
	{
		this.TabPane = Panel;
		createSetInfoPanel();
		TabPane.addTab("About EyeCanControl", SetInfoPanel);
	}
	
	public void createSetInfoPanel() 
	{
		
		SetInfoPanel = new JPanel();
		SetInfoPanel.setLayout(null);
		SetInfoPanel.setBackground(Color.WHITE);
		
		JLabel lblInfo = new JLabel("Version : 1.0");
		lblInfo.setBounds(20, 20, 70, 20);
		SetInfoPanel.add(lblInfo);
		
	}
}
