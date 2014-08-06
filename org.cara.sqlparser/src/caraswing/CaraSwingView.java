/*
 * CARA Project!
 */
package caraswing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.Timer;

import org.cara.define.FieldLengthOutput;
import org.cara.extra.ExtraFileWriter;
import org.jdesktop.application.Action;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.TaskMonitor;
 
 /**
 * The Query SQL Parser's main frame..
 * 
 * @author GUSSET
 * @version 0.1, 10/30/2010
 */
public class CaraSwingView extends FrameView {

	public CaraSwingView(SingleFrameApplication app) {
		super(app);

		/* Set the Skin, No use now */
//		 try
//		 {
		// com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel
		// com.sun.java.swing.plaf.motif.MotifLookAndFeel
		// com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel
		// //Change the UI theme : )
//		 UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//		 }catch(Exception e){}
		 

		initComponents();

		// status bar initialization - message timeout, idle icon and busy
		// animation, etc
		ResourceMap resourceMap = getResourceMap();
		int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
		messageTimer = new Timer(messageTimeout, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusMessageLabel.setText("");
			}
		});
		messageTimer.setRepeats(false);
		int busyAnimationRate = resourceMap
				.getInteger("StatusBar.busyAnimationRate");
		for (int i = 0; i < busyIcons.length; i++) {
			busyIcons[i] = resourceMap
					.getIcon("StatusBar.busyIcons[" + i + "]");
		}
		busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
				statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
			}
		});
		idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
		statusAnimationLabel.setIcon(idleIcon);

		// connecting action tasks to status bar via TaskMonitor
		TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
		taskMonitor
				.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
					public void propertyChange(
							java.beans.PropertyChangeEvent evt) {
						String propertyName = evt.getPropertyName();
						if ("started".equals(propertyName)) {
							if (!busyIconTimer.isRunning()) {
								statusAnimationLabel.setIcon(busyIcons[0]);
								busyIconIndex = 0;
								busyIconTimer.start();
							}

						} else if ("done".equals(propertyName)) {
							busyIconTimer.stop();
							statusAnimationLabel.setIcon(idleIcon);

						} else if ("message".equals(propertyName)) {
							String text = (String) (evt.getNewValue());
							statusMessageLabel.setText((text == null) ? ""
									: text);
							messageTimer.restart();
						} else if ("progress".equals(propertyName)) {
							int value = (Integer) (evt.getNewValue());

						}
					}
				});
	}

	@Action
	public void showAboutBox() {
		if (aboutBox == null) {
			JFrame mainFrame = CaraSwingApp.getApplication().getMainFrame();
			aboutBox = new CaraSwingAboutBox(mainFrame);
			aboutBox.setLocationRelativeTo(mainFrame);
		}
		CaraSwingApp.getApplication().show(aboutBox);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		mainPanel = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jButtonQuit = new javax.swing.JButton();
		jLabelQuerySql = new javax.swing.JLabel();
		jCheckBoxLengthFile = new javax.swing.JCheckBox();
		jCheckBoxExtraFile = new javax.swing.JCheckBox();
		jButtonGenerate = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextAreaSql = new javax.swing.JTextArea();
		jLabel4 = new javax.swing.JLabel();
		jTextFieldPackageName = new javax.swing.JTextField();
		jLabel5 = new javax.swing.JLabel();
		jTextFieldClassName = new javax.swing.JTextField();
		jLabel6 = new javax.swing.JLabel();
		jTextFieldXmlName = new javax.swing.JTextField();
		jLabel7 = new javax.swing.JLabel();
		jTextFieldDatasetName = new javax.swing.JTextField();
		jButtonOption = new javax.swing.JButton();
		menuBar = new javax.swing.JMenuBar();
		statusPanel = new javax.swing.JPanel();
		javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
		statusMessageLabel = new javax.swing.JLabel();
		statusAnimationLabel = new javax.swing.JLabel();

		mainPanel.setName("mainPanel"); // NOI18N
		mainPanel.setPreferredSize(new java.awt.Dimension(600, 450));

		jPanel1.setName("jPanel1"); // NOI18N
		jPanel1.setPreferredSize(new java.awt.Dimension(600, 200));

		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application
				.getInstance(caraswing.CaraSwingApp.class).getContext()
				.getResourceMap(CaraSwingView.class);
		jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
		jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
		jLabel1.setMaximumSize(new java.awt.Dimension(600, 200));
		jLabel1.setMinimumSize(new java.awt.Dimension(600, 200));
		jLabel1.setName("jLabel1"); // NOI18N

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel1Layout.createSequentialGroup().addComponent(jLabel1,
						javax.swing.GroupLayout.PREFERRED_SIZE, 168,
						javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		jPanel2.setName("jPanel2"); // NOI18N

		jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
		jLabel2.setName("jLabel2"); // NOI18N

		jButtonQuit.setText(resourceMap.getString("jButtonQuit.text")); // NOI18N
		jButtonQuit.setName("jButtonQuit"); // NOI18N
		jButtonQuit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonQuitActionPerformed(evt);
			}
		});

		jLabelQuerySql.setText(resourceMap.getString("jLabelQuerySql.text")); // NOI18N
		jLabelQuerySql.setName("jLabelQuerySql"); // NOI18N

		jCheckBoxLengthFile.setText(resourceMap
				.getString("jCheckBoxLengthFile.text")); // NOI18N
		jCheckBoxLengthFile.setName("jCheckBoxLengthFile"); // NOI18N

		jCheckBoxExtraFile.setText(resourceMap
				.getString("jCheckBoxExtraFile.text")); // NOI18N
		jCheckBoxExtraFile.setName("jCheckBoxExtraFile"); // NOI18N

		jButtonGenerate.setText(resourceMap.getString("jButtonGenerate.text")); // NOI18N
		jButtonGenerate.setName("jButtonGenerate"); // NOI18N
		jButtonGenerate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonGenerateActionPerformed(evt);
			}
		});

		jScrollPane1.setName("jScrollPane1"); // NOI18N

		jTextAreaSql.setColumns(20);
		jTextAreaSql.setRows(5);
		jTextAreaSql.setName("jTextAreaSql"); // NOI18N
		jScrollPane1.setViewportView(jTextAreaSql);

		jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
		jLabel4.setName("jLabel4"); // NOI18N

		jTextFieldPackageName.setText(resourceMap
				.getString("jTextFieldPackageName.text")); // NOI18N
		jTextFieldPackageName.setName("jTextFieldPackageName"); // NOI18N

		jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
		jLabel5.setName("jLabel5"); // NOI18N

		jTextFieldClassName.setText(resourceMap
				.getString("jTextFieldClassName.text")); // NOI18N
		jTextFieldClassName.setName("jTextFieldClassName"); // NOI18N

		jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
		jLabel6.setName("jLabel6"); // NOI18N

		jTextFieldXmlName.setText(resourceMap
				.getString("jTextFieldXmlName.text")); // NOI18N
		jTextFieldXmlName.setName("jTextFieldXmlName"); // NOI18N

		jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
		jLabel7.setName("jLabel7"); // NOI18N

		jTextFieldDatasetName.setText(resourceMap
				.getString("jTextFieldDatasetName.text")); // NOI18N
		jTextFieldDatasetName.setName("jTextFieldDatasetName"); // NOI18N

		jButtonOption.setText(resourceMap.getString("jButtonOption.text")); // NOI18N
		jButtonOption.setName("jButtonOption"); // NOI18N
		jButtonOption.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonOptionActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelQuerySql)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				jScrollPane1,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				363,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								false)
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								jPanel2Layout
																										.createSequentialGroup()
																										.addGroup(
																												jPanel2Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jLabel6)
																														.addComponent(
																																jLabel5)
																														.addComponent(
																																jLabel4)
																														.addComponent(
																																jLabel7))
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																										.addGroup(
																												jPanel2Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jTextFieldPackageName,
																																javax.swing.GroupLayout.Alignment.TRAILING,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																131,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING,
																																jPanel2Layout
																																		.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING,
																																				false)
																																		.addComponent(
																																				jTextFieldXmlName,
																																				javax.swing.GroupLayout.Alignment.TRAILING)
																																		.addComponent(
																																				jTextFieldClassName,
																																				javax.swing.GroupLayout.Alignment.TRAILING,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				130,
																																				Short.MAX_VALUE)
																																		.addComponent(
																																				jTextFieldDatasetName,
																																				javax.swing.GroupLayout.Alignment.TRAILING))))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								jPanel2Layout
																										.createSequentialGroup()
																										.addGroup(
																												jPanel2Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jCheckBoxLengthFile)
																														.addComponent(
																																jCheckBoxExtraFile))
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												jButtonGenerate,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												81,
																												javax.swing.GroupLayout.PREFERRED_SIZE))))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				226,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				192,
																				Short.MAX_VALUE)
																		.addComponent(
																				jButtonOption)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButtonQuit)))
										.addContainerGap()));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addComponent(jLabelQuerySql)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jTextFieldPackageName,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel4))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jTextFieldClassName,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel5))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jTextFieldXmlName,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel6))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jTextFieldDatasetName,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel7))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel2Layout
																										.createSequentialGroup()
																										.addComponent(
																												jCheckBoxLengthFile)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jCheckBoxExtraFile))
																						.addComponent(
																								jButtonGenerate,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								65,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																215,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabel2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																23,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jButtonQuit)
														.addComponent(
																jButtonOption))));

		javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(
				mainPanel);
		mainPanel.setLayout(mainPanelLayout);
		mainPanelLayout.setHorizontalGroup(mainPanelLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(jPanel2,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		mainPanelLayout
				.setVerticalGroup(mainPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								mainPanelLayout
										.createSequentialGroup()
										.addComponent(
												jPanel1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												172,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		menuBar.setName("menuBar"); // NOI18N

		statusPanel.setName("statusPanel"); // NOI18N

		statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

		statusMessageLabel.setName("statusMessageLabel"); // NOI18N

		statusAnimationLabel
				.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

		javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(
				statusPanel);
		statusPanel.setLayout(statusPanelLayout);
		statusPanelLayout
				.setHorizontalGroup(statusPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(statusPanelSeparator,
								javax.swing.GroupLayout.DEFAULT_SIZE, 600,
								Short.MAX_VALUE)
						.addGroup(
								statusPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(statusMessageLabel)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												580, Short.MAX_VALUE)
										.addComponent(statusAnimationLabel)
										.addContainerGap()));
		statusPanelLayout
				.setVerticalGroup(statusPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								statusPanelLayout
										.createSequentialGroup()
										.addComponent(
												statusPanelSeparator,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												2,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												statusPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																statusMessageLabel)
														.addComponent(
																statusAnimationLabel))
										.addGap(3, 3, 3)));

		setComponent(mainPanel);
		setMenuBar(menuBar);
		setStatusBar(statusPanel);

		// Added for manual initComponents
		initManual();
	}// </editor-fold>//GEN-END:initComponents

	private void jButtonQuitActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonQuitActionPerformed
		// TODO add your handling code here:
		/* Quit Cara Program */
		System.exit(busyIconIndex);
	}// GEN-LAST:event_jButtonQuitActionPerformed

	private void jButtonOptionActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonOptionActionPerformed
		// TODO add your handling code here:
		/* Show the CaraOption */
//		caraOption.setVisible(true);
		showAboutBox();
	}// GEN-LAST:event_jButtonOptionActionPerformed

	private void jButtonGenerateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonGenerateActionPerformed
		// TODO add your handling code here:
		if (jCheckBoxLengthFile.isSelected()){
			String inputSql = jTextAreaSql.getText();
			String txnName  = jTextFieldClassName.getText();
			String fileName = "Txn"+txnName+"Length.java";
			String packageName = jTextFieldPackageName.getText();
			fieldLengthOutput.writeJavaFile(inputSql, fileName, packageName, txnName);
		}
		if (jCheckBoxExtraFile.isSelected()){
			String inputSql = jTextAreaSql.getText();
			String datasetName = jTextFieldDatasetName.getText();
			String xmlName = jTextFieldXmlName.getText();
			extraFileWriter.writeExtraFile(datasetName, inputSql, xmlName);
		}
		
	}// GEN-LAST:event_jButtonGenerateActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButtonGenerate;
	private javax.swing.JButton jButtonOption;
	private javax.swing.JButton jButtonQuit;
	private javax.swing.JCheckBox jCheckBoxExtraFile;
	private javax.swing.JCheckBox jCheckBoxLengthFile;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabelQuerySql;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextAreaSql;
	private javax.swing.JTextField jTextFieldClassName;
	private javax.swing.JTextField jTextFieldDatasetName;
	private javax.swing.JTextField jTextFieldPackageName;
	private javax.swing.JTextField jTextFieldXmlName;
	private javax.swing.JPanel mainPanel;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JLabel statusAnimationLabel;
	private javax.swing.JLabel statusMessageLabel;
	private javax.swing.JPanel statusPanel;
	// End of variables declaration//GEN-END:variables

	private final Timer messageTimer;
	private final Timer busyIconTimer;
	private final Icon idleIcon;
	private final Icon[] busyIcons = new Icon[15];
	private int busyIconIndex = 0;

	private JDialog aboutBox;

	/*
	 * Manual Added
	 * <p>
	 * 下面为手动添加。
	 * </p>
	 */
	
	/**
	 * 手动做一些界面的设置。
	 */
	private void initManual() {
		this.getFrame().setTitle("Cara Query SQL Parser");
		this.getFrame().setResizable(false);
	}

	/**
	 * This value is used for Option Window storage .
	 * <p>
	 * 选项窗口。
	 * </p>
	 */
	private CaraOption caraOption = new CaraOption();
	
	/**
	 * 警告提示。
	 */
	private JDialog alertDialog;
	
	private FieldLengthOutput fieldLengthOutput = new FieldLengthOutput ();
	private ExtraFileWriter extraFileWriter = new ExtraFileWriter ();
}
