import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;


public class ECCPopup extends JFrame  
{
	private JPanel ECCPanel;
	private JButton aboutECC;
	private ImageIcon aboutECC_icon;


	public ECCPopup()
	{
		createUI();
		getContentPane().add(ECCPanel);
	}
	
	public void createUI()
	{
		// for JFrame 
		setBounds(300, 100, 350, 300);
		setVisible(true);
		setResizable(false);
		setBackground(Color.white);
		setTitle("About EyeCanControl");
		
		// Panel 설정
		Border emptyBorder = BorderFactory.createEmptyBorder();
		
		ECCPanel = new JPanel();
		ECCPanel.setLayout(new BorderLayout());
		ECCPanel.setBackground(Color.WHITE);
		
		//버튼 설정

		aboutECC_icon = new ImageIcon("image/about_ECC.png");
		aboutECC = new JButton(aboutECC_icon);
		aboutECC.setBounds(0,0,350, 300);
		aboutECC.setBorder(emptyBorder);
		
		ECCPanel.add(aboutECC);
		//SettingIP SettingIp = new SettingIP(Setting, TabPane);
	}

}

