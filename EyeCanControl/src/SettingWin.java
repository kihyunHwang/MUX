import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class SettingWin extends JFrame 
{
	private JPanel SettingPanel;
	private JTabbedPane TabPane;
	
	public SettingWin()
	{
		createUI();
		getContentPane().add(SettingPanel);
	}
	
	public void createUI()
	{
		SettingPanel = new JPanel();
		SettingPanel.setLayout(new BorderLayout());
		SettingPanel.setBackground(Color.WHITE);
		
		TabPane = new JTabbedPane();
		
		SettingIP SettingIp = new SettingIP(TabPane);
		SettingInfo SettingInfo = new SettingInfo(TabPane);
		
		SettingPanel.add(TabPane, BorderLayout.CENTER);
	}
}
