package messenger.client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import messenger.server.MessengerDAOimpl;

public class LogInWindow extends JFrame {
	private JPanel contentPane;
	private JTextField id;
	private JButton loginbtn, joinbtn,findid,findpw;
	private JPasswordField passwd;
	private JLabel main_img;
	private JFrame jframe2;
	
	
	public LogInWindow() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 369, 331);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.BLACK);
		
		
		main_img = new JLabel(new ImageIcon("img/다운로드.jpg"));
		main_img.setBounds(39, 5, 266, 190);
		add(main_img);
		
		JLabel idl = new JLabel("아이디");
		idl.setForeground(Color.LIGHT_GRAY);
		idl.setBounds(18, 205, 51, 15);
		contentPane.add(idl);
		
		id = new JTextField();
		id.setBounds(81, 205, 179, 21);
		id.setToolTipText("id");
		contentPane.add(id);
		id.setColumns(10);
		
		JLabel pwl = new JLabel("비밀번호");
		pwl.setBounds(18, 230, 58, 15);
		pwl.setForeground(Color.LIGHT_GRAY);
		contentPane.add(pwl);
		
		passwd = new JPasswordField();
		passwd.setBounds(81, 227, 179, 21);
		passwd.setToolTipText("PASSWORD");
		contentPane.add(passwd);
		

		
		loginbtn = new JButton("로그인");
		loginbtn.setBounds(264, 205, 77, 40);
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					char[] pass = passwd.getPassword();
					String pass_1;
					pass_1 = new String(pass, 0, pass.length);//passwordField의 인자를 String 값으로 반환하여 pass_1에 저장
					MessengerDAOimpl dao = new MessengerDAOimpl();
					int a = dao.Login(id.getText(), pass_1);
					if(a==1) {
						System.out.println(1);
						new Mainframe(id.getText());
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 확인해 주세요", "", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
			contentPane.add(loginbtn);
		
		joinbtn = new JButton("회원가입");
		joinbtn.setBounds(12, 255, 86, 23);
		joinbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Join(jframe2, "Join");

				}
			});
			contentPane.add(joinbtn);
		
		findid = new JButton("아이디 찾기");
		findid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FindID(jframe2, "아이디찾기");
			}
		});
		findid.setBounds(102, 255, 103, 23);
		contentPane.add(findid);
			
		findpw = new JButton("비밀번호 찾기");
		findpw.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new FindPW(jframe2, "비밀번호 찾기");
			}
		});
		findpw.setBounds(208, 255, 133, 23);
		contentPane.add(findpw);
		}
}
