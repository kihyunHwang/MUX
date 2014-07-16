import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class SettingWin extends JFrame 
{
	private Setting Setting;
	private JPanel SettingPanel;
	private JTabbedPane TabPane;
	
	public SettingWin(Setting Setting)
	{
		this.Setting = Setting;
		createUI();
		getContentPane().add(SettingPanel);
	}
	
	public void createUI()
	{
		SettingPanel = new JPanel();
		SettingPanel.setLayout(new BorderLayout());
		SettingPanel.setBackground(Color.WHITE);
		
		TabPane = new JTabbedPane();
		
		SettingIP SettingIp = new SettingIP(Setting, TabPane);
		SettingMessage SettingMessage = new SettingMessage(Setting, TabPane);
		SettingInfo SettingInfo = new SettingInfo(TabPane);
		
		SettingPanel.add(TabPane, BorderLayout.CENTER);
	}
}
