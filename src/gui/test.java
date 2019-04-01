package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;

import boyer_moore.BoyerMoore;
import fileList.listFile;
import fileList.xulyDuLieu;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Label;

public class test {

	private JFrame frame;
	private JTextField txtLink;
	private String fileName;
	private xulyDuLieu xl;
	private listFile files = new listFile();
	private List listSort;
	private List list;
	private JTextField txtTimKiem;
	private JTextField textField;
	private int offset;
	private String txt = "";
	int k;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public test() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 50, 800,650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtLink = new JTextField();
		txtLink.setEnabled(false);
		panel.add(txtLink);
		txtLink.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblNhpChui = new JLabel("     Nhập chuỗi :");
		panel_2.add(lblNhpChui);
		
		txtTimKiem = new JTextField();
		panel_2.add(txtTimKiem);
		txtTimKiem.setToolTipText("Nhập chuỗi cần tìm !");
		txtTimKiem.setColumns(10);
		
		
		JButton btnSapXep = new JButton("Sắp Xếp");
		panel_2.add(btnSapXep);
		
		Label label = new Label("");
		panel_2.add(label);
		
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblDanhSch = new JLabel("     Danh Sách Đồ Án");
		panel_4.add(lblDanhSch);
		
		listSort = new List();
		panel_3.add(listSort, BorderLayout.CENTER);
		
		list = new List();
		panel_3.add(list, BorderLayout.WEST);
		
		JPanel panel_5 = new JPanel();
		frame.getContentPane().add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new GridLayout(2, 3, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("");
		panel_6.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("");
		panel_6.add(lblNewLabel_4);
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.WEST);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("    ");
		panel_7.add(lblNewLabel_6, BorderLayout.WEST);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("FILE");
		menuBar.add(mnFile);
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		
		JScrollPane scrollPane_1 = new JScrollPane(textPane);
		panel_5.add(scrollPane_1, BorderLayout.SOUTH);
		
		
		panel_5.add(scrollPane_1, BorderLayout.CENTER);
		
		JLabel lblNewLabel_5 = new JLabel("  ");
		scrollPane_1.setColumnHeaderView(lblNewLabel_5);
		
		JLabel label_1 = new JLabel("    ");
		panel_5.add(label_1, BorderLayout.EAST);
		listSort.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		    	try {
		    		String[] temp;
		    		for(int i = 0;i<=files.size();i++) {
		    			temp = listSort.getSelectedItem().toString().split(":");
			    		if(files.getFile().get(i).getPath().equals(temp[0])) {
			    			textPane.setText(files.getFile().get(i).getText());
			    			break;
			    		}
			    	}
			    	
			    	txt = textPane.getText().toString();
			    	offset = 0;
			    	DefaultHighlightPainter cyanPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.cyan);
				    String inputPat = txtTimKiem.getText();
					BoyerMoore bm = new BoyerMoore(inputPat);
						while(txt.length()>= inputPat.length()&&bm.search(txt)!= txt.length()) {
							offset = offset+ bm.search(txt);
						    	 try {
						    			textPane.getHighlighter().addHighlight(
						    					offset,offset+inputPat.length(), cyanPainter);
						    			offset += inputPat.length();
						    			txt = textPane.getText().substring(offset);
						     			
								} catch (BadLocationException e) {
									e.printStackTrace();
								}
						}
		    	}catch (Exception e) {
				}
		    	
		    }
		});
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listSort.clear();
					textPane.setText(null);
					JFileChooser chooser = new JFileChooser();
					chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					chooser.showDialog(frame, "Chon");
					fileName = chooser.getSelectedFile().getPath();
					txtLink.setText(fileName);
					xl = new xulyDuLieu();
					files = xl.readData(fileName);
					for(int i = 0;i<files.getFile().size();i++) {
						String[] temp = files.getFile().get(i).getPath().toString().split("\\.");
						
						if(temp[temp.length-1].equals("doc")||temp[temp.length-1].equals("docx"))
							list.add(files.getFile().get(i).getPath());
					}
					setVisible(true);
					
				}catch (Exception e1) {
					
				}
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		txtTimKiem.addMouseListener(new MouseAdapter(){
			   public void mouseReleased(MouseEvent e) {
				   textPane.getHighlighter().removeAllHighlights();
				   textPane.setText(null);
				   listSort.clear();
			    	offset = 0;
				   }
		});
		btnSapXep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!txtTimKiem.getText().equals("")) {
					listSort.clear();
					xl = new xulyDuLieu();
					xl.sortList(txtTimKiem.getText(), files);
					files.sortFile();
					for(int i = 0;i<files.getFile().size();i++) {
						if(files.getFile().get(i).getId()>0)
							listSort.add(files.getFile().get(i).getPath()+": "+files.getFile().get(i).getId() + "lần");
					}
					
				}
			}
		});
		textField = new JTextField();
		mnFile.add(textField);
		textField.setColumns(10);
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
	}
 
}
