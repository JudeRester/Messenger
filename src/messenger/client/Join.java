package messenger.client;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import messenger.common.MemberDTO;
import messenger.server.MessengerDAOimpl;
import validate.Validation;

public class Join extends JFrame{
	private JPanel contentPane;
	private JTextField id, birth, phone, name, alias;
	private JPasswordField passwd;
	private JPasswordField passch;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	PreparedStatement pstmt;
	Connection conn;
	Validation val = new Validation();
	
	public Join(JFrame frame, String title) {
		setBounds(100,100,245, 358);
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		setVisible(true);
		
	    Point mainFrameLocation = new Point(0, 0);
	    Point mouseClickedLocation = new Point(0, 0);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getLocationOnScreen().x - mouseClickedLocation.x,
                e.getLocationOnScreen().y - mouseClickedLocation.y);
//				Rectangle size = getBounds();
//				setBounds( e.getX() + size.x , e.getY() + size.y, size.width, size.height ); 
			}
		});
		String[] item = {"--지역--","서울","경기","부산","광주","인천","대구","대전","청주"};
		
		JLabel lblNewLabel_1 = new JLabel("아이디(이메일)");
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setBounds(12, 10, 92, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("이름");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(12, 68, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_8 = new JLabel("별명");
		lblNewLabel_8.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_8.setBounds(12, 93, 57, 15);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_2 = new JLabel("패스워드");
		lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2.setBounds(12, 118, 92, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("패스워드 확인");
		lblNewLabel_3.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_3.setBounds(12, 170, 92, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("지역");
		lblNewLabel_4.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_4.setBounds(12, 196, 92, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("성별");
		lblNewLabel_5.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_5.setBounds(12, 230, 92, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("생년월일");
		lblNewLabel_6.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_6.setBounds(12, 269, 92, 16);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("휴대폰번호");
		lblNewLabel_7.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_7.setBounds(12, 295, 92, 16);
		contentPane.add(lblNewLabel_7);
		
		id = new JTextField();
		id.setBounds(116, 10, 117, 16);
		contentPane.add(id);
		id.setColumns(10);
		
		name = new JTextField();
		name.setBounds(116, 67, 117, 16);
		contentPane.add(name);
		name.setColumns(10);
		
		alias = new JTextField();
		alias.setBounds(116, 93, 117, 16);
		contentPane.add(alias);
		alias.setColumns(10);
		
		passwd = new JPasswordField();
		passwd.setBounds(116, 119, 117, 16);
		contentPane.add(passwd);
		
		passch = new JPasswordField();
		passch.setBounds(116, 170, 117, 16);
		contentPane.add(passch);
		JComboBox loc = new JComboBox(item);
		loc.setBounds(116, 196, 117, 21);
		contentPane.add(loc);
		
		birth = new JTextField();
		birth.setBounds(116, 269, 117, 16);
		contentPane.add(birth);
		birth.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(116, 295, 117, 16);
		contentPane.add(phone);
		phone.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.LIGHT_GRAY);
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(116, 227, 117, 32);
		contentPane.add(panel_2);
		
		JRadioButton radioButton = new JRadioButton("남");
		radioButton.setForeground(Color.LIGHT_GRAY);
		radioButton.setBackground(Color.BLACK);
		buttonGroup.add(radioButton);
		panel_2.add(radioButton);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("여");
		rdbtnNewRadioButton.setForeground(Color.LIGHT_GRAY);
		rdbtnNewRadioButton.setBackground(Color.BLACK);
		buttonGroup.add(rdbtnNewRadioButton);
		panel_2.add(rdbtnNewRadioButton);
		
		JButton joinb = new JButton("가입");
		joinb.setEnabled(false);
		joinb.setBounds(12, 321, 104, 21);
		contentPane.add(joinb);
		
		JButton idcheck = new JButton("중복 확인");
		idcheck.setBounds(116, 36, 117, 21);
		contentPane.add(idcheck);
		idcheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(id.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해 주세요", "", JOptionPane.WARNING_MESSAGE);
					id.requestFocus();
					return;
					}
				if(!val.EmailVali(id.getText())) {
					JOptionPane.showMessageDialog(null, "올바른 이메일 형식을 사용하세요", "", JOptionPane.WARNING_MESSAGE);
					id.requestFocus();
					return;
				}
				
				MemberDTO member = new MemberDTO();

				try {
					MessengerDAOimpl dao = new MessengerDAOimpl();
					String a = dao.checkID(id.getText());
					if(a.equals(id.getText())) {
						JOptionPane.showMessageDialog(null, "사용중인 아이디 입니다.", "", JOptionPane.WARNING_MESSAGE);
						a= "";
						return;
					}else if(!a.equals(id.getText())){
						JOptionPane.showMessageDialog(null, "사용가능한 아이디 입니다.", "", JOptionPane.INFORMATION_MESSAGE);
					joinb.setEnabled(true);
					}
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		joinb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				char[] pass = passwd.getPassword();
				String pass_1;
				String sex="";
				pass_1 = new String(pass, 0, pass.length);//passwordField의 인자를 String 값으로 반환하여 pass_1에 저장
				char[] passc = passch.getPassword();
				String passch_1 = new String(passc,0,passc.length);
				
				if(name.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "이름을 입력해 주세요", "", JOptionPane.WARNING_MESSAGE);
					name.requestFocus();
					return;
				}
				if(alias.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "별명을 입력해주세요", "", JOptionPane.WARNING_MESSAGE);
					alias.requestFocus();
					return;
				}
				if(pass_1.equals("")) {
					JOptionPane.showMessageDialog(null, "패스워드를 입력해주세요", "", JOptionPane.WARNING_MESSAGE);
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
				int rc = 0;
				Enumeration<AbstractButton> enums = buttonGroup.getElements();
				while(enums.hasMoreElements()) {            // hasMoreElements() Enum에 더 꺼낼 개체가 있는지 체크한다. 없으며 false 반환	
				    AbstractButton ab = enums.nextElement();    // 제네릭스가 AbstractButton 이니까 당연히 AbstractButton으로 받아야함
				    JRadioButton jb = (JRadioButton)ab;         // 형변환. 물론 윗줄과 이줄을 합쳐서 바로 형변환 해서 받아도 된다.
				    if(jb.isSelected()) {			// 받아낸 라디오버튼의 체크 상태를 확인한다. 체크되었을경우 true 반환.
				        sex = jb.getText().trim(); //getText() 메소드로 문자열 받아낸다.
				        rc+=1;
				    }
				}
				if(rc==0) {
					JOptionPane.showMessageDialog(null, "성별을 체크해주세요", "", JOptionPane.WARNING_MESSAGE);
					radioButton.requestFocus();
					return;
				}
				if(birth.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "생년월일을 입력해주세요", "", JOptionPane.WARNING_MESSAGE);
					birth.requestFocus();
					return;
				}
				if(phone.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "휴대폰 번호를 입력해 주세요", "", JOptionPane.WARNING_MESSAGE);
					phone.requestFocus();
					return;
				}
				
				MemberDTO dto = new MemberDTO(id.getText(),pass_1, name.getText(), alias.getText(), 
							String.valueOf(loc.getSelectedItem()),sex, birth.getText(), phone.getText());
				try {
					MessengerDAOimpl dao = new MessengerDAOimpl();
					dao.insertMember(dto);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		JButton cancelb = new JButton("취소");
		cancelb.setBounds(129, 321, 104, 21);
		contentPane.add(cancelb);
		
		JLabel lblNewLabel_9 = new JLabel("최소 8자리 최대 16자리");
		lblNewLabel_9.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_9.setBounds(86, 145, 147, 15);
		contentPane.add(lblNewLabel_9);
		cancelb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
//				StringBuffer cont = new StringBuffer();
//				cont.append("insert into member(id,passwd,loc,sex,birth,phone) values(?,?,?,?,?,?)");
//				
//				try {
//					pstmt = conn.prepareStatement(cont.toString());
//					pstmt.setString(1, id.getText());
//					pstmt.setString(2, pass_1);
//					pstmt.setString(3, loc.toString());
//					pstmt.setString(4, gibonCode);
//					pstmt.setString(5, birth.getText());
//					pstmt.setString(6, phone.getText());
//					
//					pstmt.executeUpdate();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}

