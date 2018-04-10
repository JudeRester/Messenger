package messenger.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.collections4.bag.TreeBag;

import messenger.common.MemberDTO;
import messenger.server.MessengerDAOimpl;
import validate.Validation;

public class Mainframe {
	static JFrame jframe;
	private static JPanel contentPane;
	private JTextField birth, phone, name, alias;
	private JPasswordField passch;
	private JPasswordField passwd;
	private JPasswordField passwd2;
	private MemberDTO member;
	private DefaultMutableTreeNode rootNodenew;
	private ArrayList<String> asd;
	Validation val = new Validation();
	static private String lalala;
	

		
	public Mainframe(String id) {
		jframe= new JFrame();
		
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setBounds(100, 100, 285, 421);
		jframe.setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		jframe.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("메뉴");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("로그아웃");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jframe.dispose();
				LogInWindow L = new LogInWindow();
				L.setVisible(true);
				L.setTitle("J Messenger");
			}
		});
		mntmNewMenuItem.setSelected(true);
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jframe.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		contentPane.add(tabbedPane);
		class treenodecre{
//			DefaultMutableTreeNode rootNode;
			public DefaultMutableTreeNode createNodes()
			{
				try {
					MessengerDAOimpl dao = new MessengerDAOimpl();
					asd = dao.loadfriend(id);
					
				}catch(SQLException e) {
					e.printStackTrace();
				}
				DefaultMutableTreeNode rootNode=new DefaultMutableTreeNode("친구");

				for(int i =0;i<asd.size();i++) {
					rootNode.add(new DefaultMutableTreeNode(asd.get(i)));
				}
				return rootNode;
			}
		}
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("친구목록", null, panel_1, null);
		panel_1.setLayout(null);
		
		
		

		
//		scrollPane.setViewportView(tree);
//		tree.setBounds(0, 0, 323, 355);
//		rootNodenew= new DefaultMutableTreeNode("친구목록") {
//				{
					//					node_1.add(new DefaultMutableTreeNode("blue"));
//					node_1.add(new DefaultMutableTreeNode("violet"));
//					node_1.add(new DefaultMutableTreeNode("red"));
//					node_1.add(new DefaultMutableTreeNode("yellow"));
//					add(node_1);
//					node_1 = new DefaultMutableTreeNode("custom List");
//					node_1.add(new DefaultMutableTreeNode("basketball"));
//					node_1.add(new DefaultMutableTreeNode("soccer"));
//					node_1.add(new DefaultMutableTreeNode("football"));
//					node_1.add(new DefaultMutableTreeNode("hockey"));
//					add(node_1);
//					node_1 = new DefaultMutableTreeNode("food");
//					node_1.add(new DefaultMutableTreeNode("hot dogs"));
//					node_1.add(new DefaultMutableTreeNode("pizza"));
//					node_1.add(new DefaultMutableTreeNode("ravioli"));
//					node_1.add(new DefaultMutableTreeNode("bananas"));
//					add(node_1);
//				}
//			};
		treenodecre trc = new treenodecre();
		rootNodenew=trc.createNodes();
		JTree tree = new JTree(rootNodenew);
		JScrollPane scrollPane = new JScrollPane(tree);
		JPopupMenu popupMenu = new JPopupMenu();
		scrollPane.setBounds(0, 0, 254, 321);
		panel_1.add(scrollPane);
		lalala = (String)tree.getLastSelectedPathComponent();
		addPopup(tree, popupMenu);
		JButton btnNewButton_2 = new JButton("친구 추가");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new searchFriend(id);
			}
		});
		scrollPane.setColumnHeaderView(btnNewButton_2);
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("삭제");
		popupMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new MenuActionListener());
		JPanel panel = new JPanel();
		tabbedPane.addTab("개인정보 수정", null, panel, null);
		panel.setLayout(new CardLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, "name_11400799903521");
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("Summit");
		btnNewButton.setBounds(80, 183, 97, 23);
		panel_2.add(btnNewButton);
		
		passwd = new JPasswordField();
		passwd.setBounds(36, 150, 186, 23);
		panel_2.add(passwd);
		
		JLabel lblp = new JLabel("개인정보 수정을 위해 ");
		lblp.setBounds(69, 100, 128, 23);
		panel_2.add(lblp);
		
		JLabel lblp2 = new JLabel("비밀번호를 입력해 주세요");
		lblp2.setBounds(58, 117, 155, 23);
		panel_2.add(lblp2);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, "name_11888456807511");
		panel_3.setLayout(null);
		
		String[] item = {"--지역--","서울","경기","부산","광주","인천","대구","대전","청주"};
		
		JLabel lblNewLabel_8 = new JLabel("별명");
		lblNewLabel_8.setBounds(12, 21, 57, 15);
		panel_3.add(lblNewLabel_8);
		
		JLabel lblNewLabel_2 = new JLabel("패스워드");
		lblNewLabel_2.setBounds(12, 71, 92, 16);
		panel_3.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("패스워드 확인");
		lblNewLabel_3.setBounds(12, 98, 92, 16);
		panel_3.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("지역");
		lblNewLabel_4.setBounds(12, 148, 92, 16);
		panel_3.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("생년월일");
		lblNewLabel_6.setBounds(12, 197, 92, 16);
		panel_3.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("휴대폰번호");
		lblNewLabel_7.setBounds(12, 223, 92, 16);
		panel_3.add(lblNewLabel_7);
		
		alias = new JTextField();
		alias.setBounds(116, 21, 117, 16);
		panel_3.add(alias);
		alias.setColumns(10);
		
		passwd2 = new JPasswordField();
		passwd2.setBounds(116, 72, 117, 16);
		panel_3.add(passwd2);
		
		passch = new JPasswordField();
		passch.setBounds(116, 98, 117, 16);
		panel_3.add(passch);
		JComboBox loc = new JComboBox(item);
		loc.setBounds(116, 148, 117, 21);
		panel_3.add(loc);
		
		birth = new JTextField();
		birth.setBounds(116, 197, 117, 16);
		panel_3.add(birth);
		birth.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(116, 223, 117, 16);
		panel_3.add(phone);
		phone.setColumns(10);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					MessengerDAOimpl dao = new MessengerDAOimpl();
					char[] pass = passwd.getPassword();
					String pass_1;
					pass_1 = new String(pass, 0, pass.length);//passwordField의 인자를 String 값으로 반환하여 pass_1에 저장
					
					int a = dao.Login(id, pass_1);
					if(a==1) {
						member =  new MemberDTO();
						panel_2.setVisible(false);
						panel_3.setVisible(true);
						passwd.setText("");
						
						member = dao.getInfo(id);
						alias.setText(member.getAlias());
						loc.setSelectedItem(member.getLoc());
						passwd2.setText(member.getPasswd());
						passch.setText(member.getPasswd());
						alias.setText(member.getAlias());
						birth.setText(member.getBirth());
						phone.setText(member.getPhone());
						
						
					}else {
						JOptionPane.showMessageDialog(null, "비밀번호를 확인해 주세요", "", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		JButton btnNewButton_1 = new JButton("저장");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				char[] pass = passwd2.getPassword();
				String pass_1;
				pass_1 = new String(pass, 0, pass.length);//passwordField의 인자를 String 값으로 반환하여 pass_1에 저장
				char[] passc = passch.getPassword();
				String passch_1 = new String(passc,0,passc.length);
				
				if(pass_1.equals("")) {
					JOptionPane.showMessageDialog(null, "패스워드를 입력해주세요", "", JOptionPane.WARNING_MESSAGE);
					passwd.requestFocus();
					return;
				}
				if(!val.PassVali(pass_1)) {
					JOptionPane.showMessageDialog(null, "패스워드는 8~16자리로 영문 대/소문자, 숫자, \n\r특수문자(!,@,#,$,%,^,&,*,(,)) 만 사용 할 수 있습니다.", "", JOptionPane.WARNING_MESSAGE);
					passwd.requestFocus();
					return;
				}
				if(!passch_1.equals(pass_1)) {
					JOptionPane.showMessageDialog(null, "패스워드를 확인해 주세요","",JOptionPane.WARNING_MESSAGE);
					passch.requestFocus();
					return;
				}
				if(loc.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null, "지역을 선택해 주세요", "", JOptionPane.WARNING_MESSAGE);
					loc.requestFocus();
					return;
				}
				if(birth.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "생년월일을 입력해주세요", "", JOptionPane.WARNING_MESSAGE);
					birth.requestFocus();
					return;
				}
				if(!val.DateVali(birth.getText())){
					JOptionPane.showMessageDialog(null, "올바른 날짜를 입력하세요(ex.19940307)", "", JOptionPane.WARNING_MESSAGE);
					birth.requestFocus();
					return;
				}
				if(phone.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "휴대폰 번호를 입력해 주세요", "", JOptionPane.WARNING_MESSAGE);
					phone.requestFocus();
					return;
				}
				if(!val.PhoneVali(phone.getText())) {
					JOptionPane.showMessageDialog(null, "올바른 번호를 입력하세요", "", JOptionPane.WARNING_MESSAGE);
					phone.requestFocus();
					return;
				}
				try {
					MessengerDAOimpl dao = new MessengerDAOimpl();
					dao.changeInfo(id, pass_1, alias.getText(), loc.getSelectedItem().toString(), birth.getText(), phone.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "정보 수정이 완료되었습니다.", "", JOptionPane.INFORMATION_MESSAGE);
				panel_2.setVisible(true);
				panel_3.setVisible(false);
				
			}
		});
		btnNewButton_1.setBounds(76, 275, 97, 23);
		panel_3.add(btnNewButton_1);
		
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	class MenuActionListener implements ActionListener { 
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand(); 
			switch(cmd) { // 메뉴 아이템의 종류 구분
				case "삭제" :
					try {
					MessengerDAOimpl dao = new MessengerDAOimpl();
					
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
//				case "Hide" :
//					imgLabel.setVisible(false); break;
//				case "ReShow" :
//					imgLabel.setVisible(true); break;
//				case "Exit" :
//					System.exit(0); break;
			}
		}
	}
}
