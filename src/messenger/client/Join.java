package messenger.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Join extends JFrame{
	private JPanel contentPane;
	private JTextField id, birth, phone, name, alias;
	private JPasswordField passwd;
	private JPasswordField passch;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	PreparedStatement pstmt;
	Connection conn;

	public Join(JFrame frame, String title) {
		

		setBounds(100, 100, 261, 342);
		setResizable(false);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String[] item = {"--지역--","서울","경기","부산","광주","인천","대구","대전","청주"};
		
		JLabel lblNewLabel_1 = new JLabel("아이디");
		lblNewLabel_1.setBounds(12, 10, 92, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("이름");
		lblNewLabel.setBounds(12, 36, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_8 = new JLabel("별명");
		lblNewLabel_8.setBounds(12, 61, 57, 15);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_2 = new JLabel("패스워드");
		lblNewLabel_2.setBounds(12, 89, 92, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("패스워드 확인");
		lblNewLabel_3.setBounds(12, 115, 92, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("지역");
		lblNewLabel_4.setBounds(12, 141, 92, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("성별");
		lblNewLabel_5.setBounds(12, 178, 92, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("생년월일");
		lblNewLabel_6.setBounds(12, 212, 92, 16);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("휴대폰번호");
		lblNewLabel_7.setBounds(12, 238, 92, 16);
		contentPane.add(lblNewLabel_7);
		
		id = new JTextField();
		id.setBounds(116, 10, 117, 16);
		contentPane.add(id);
		id.setColumns(10);
		
		name = new JTextField();
		name.setBounds(116, 35, 117, 16);
		contentPane.add(name);
		name.setColumns(10);
		
		alias = new JTextField();
		alias.setBounds(116, 61, 117, 16);
		contentPane.add(alias);
		alias.setColumns(10);
		
		passwd = new JPasswordField();
		passwd.setBounds(116, 87, 117, 16);
		contentPane.add(passwd);
		
		passch = new JPasswordField();
		passch.setBounds(116, 113, 117, 16);
		contentPane.add(passch);
		JComboBox loc = new JComboBox(item);
		loc.setBounds(116, 139, 117, 21);
		contentPane.add(loc);
		
		birth = new JTextField();
		birth.setBounds(116, 212, 117, 16);
		contentPane.add(birth);
		birth.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(116, 238, 117, 16);
		contentPane.add(phone);
		phone.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(116, 170, 117, 32);
		contentPane.add(panel_2);
		
		JRadioButton radioButton = new JRadioButton("남");
		buttonGroup.add(radioButton);
		panel_2.add(radioButton);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("여");
		buttonGroup.add(rdbtnNewRadioButton);
		panel_2.add(rdbtnNewRadioButton);
		
		JButton joinb = new JButton("가입");
		joinb.setBounds(12, 273, 104, 21);
		contentPane.add(joinb);
		
		joinb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				char[] pass = passwd.getPassword();
				String pass_1;
				String sex="";
				pass_1 = new String(pass, 0, pass.length);//passwordField의 인자를 String 값으로 반환하여 pass_1에 저장
				char[] passc = passch.getPassword();
				String passch_1 = new String(passc,0,passc.length);
				if(id.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해 주세요", "", JOptionPane.WARNING_MESSAGE);
					id.requestFocus();
					return;
					}
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
				
				Enumeration<AbstractButton> enums = buttonGroup.getElements();
				while(enums.hasMoreElements()) {            // hasMoreElements() Enum에 더 꺼낼 개체가 있는지 체크한다. 없으며 false 반환
				    AbstractButton ab = enums.nextElement();    // 제네릭스가 AbstractButton 이니까 당연히 AbstractButton으로 받아야함
				    JRadioButton jb = (JRadioButton)ab;         // 형변환. 물론 윗줄과 이줄을 합쳐서 바로 형변환 해서 받아도 된다.
				    if(jb.isSelected())                         // 받아낸 라디오버튼의 체크 상태를 확인한다. 체크되었을경우 true 반환.
				        sex = jb.getText().trim(); //getText() 메소드로 문자열 받아낸다.
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
		cancelb.setBounds(129, 273, 104, 21);
		contentPane.add(cancelb);
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

