package caraswing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.cara.core.CaraManager;

public class CaraMainFrame extends JFrame {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		CaraMainFrame caraMainFrame = new CaraMainFrame();
		caraMainFrame.initMainFrame();
	}

	public CaraMainFrame() {
		// 初始化工作。
		CaraManager.initCaraManager();
	}

	public void initMainFrame() {
		mainPanel = new JPanel();
		jButtonQuerySqlParser = new JButton("Query SQL Parser");
		jButtonCreateSqlParser = new JButton("Create SQL Parser");

		jButtonQuerySqlParser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CaraSwingApp.launch(CaraSwingApp.class, null);
			}
		});

		mainPanel.add(jButtonQuerySqlParser);
		mainPanel.add(jButtonCreateSqlParser);
		this.add(mainPanel);
		this.setSize(new Dimension(300, 200));
		this.setPreferredSize(new Dimension(300, 200));
		this.setTitle("Cara SQL Parser");
		this.setVisible(true);
	}

	private JPanel mainPanel;
	private JButton jButtonQuerySqlParser;
	private JButton jButtonCreateSqlParser;

	/**/
	public CaraSwingApp caraSwingApp;

	/* This value is used for Create SQL Parser Window storage */

}
