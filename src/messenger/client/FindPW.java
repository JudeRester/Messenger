package messenger.client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import messenger.server.MessengerDAOimpl;
import validate.Validation;

public class FindPW extends JFrame{
	private JPanel contentPane;
	private JTextField id;
	private JTextField birth;
	private JTextField phone;
	private Validation val = new Validation();
	public FindPW(JFrame jframe, String title) {
		setResizable(false);
		setVisible(true);
		setTitle("비밀번호 찾기");
		setBounds(100, 100, 238, 174);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.BLACK);
		
		id = new JTextField();
		id.setBounds(94, 10, 116, 21);
		contentPane.add(id);
		id.setColumns(10);
		
		birth = new JTextField();
		birth.setColumns(10);
		birth.setBounds(94, 42, 116, 21);
		contentPane.add(birth);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(94, 75, 116, 21);
		contentPane.add(phone);
		
		JLabel label = new JLabel("아이디");
		label.setBounds(12, 10, 57, 15);
		label.setForeground(Color.LIGHT_GRAY);

		contentPane.add(label);
		
		JLabel label_1 = new JLabel("생년월일");
		label_1.setBounds(12, 42, 57, 15);
		label_1.setForeground(Color.LIGHT_GRAY);

		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("휴대폰번호");
		label_2.setBounds(12, 75, 70, 15);
		label_2.setForeground(Color.LIGHT_GRAY);
		contentPane.add(label_2);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
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
				if(birth.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "생년월일을 입력해주세요", "", JOptionPane.WARNING_MESSAGE);
					birth.requestFocus();
					return;
				}
				if(!val.DateVali(birth.getText())){
					JOptionPane.showMessageDialog(null, "올바른 날짜를 입력하세요", "", JOptionPane.WARNING_MESSAGE);
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
					String a =null;
					a = dao.findPasswd(id.getText(), birth.getText(), phone.getText());
					if(a==null) {
						JOptionPane.showMessageDialog(null, "입력하신 정보와 일치하는 비밀번호가 없습니다.", "", JOptionPane.WARNING_MESSAGE);
						return;
					}
					JOptionPane.showMessageDialog(null, "입력하신 정보와 일치하는 비밀번호\r\n"+"\""+a+"\"", "", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		submit.setBounds(12, 106, 97, 23);
		contentPane.add(submit);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel.setBounds(113, 106, 97, 23);
		contentPane.add(cancel);
		
	}
}
