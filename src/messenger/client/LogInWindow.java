package messenger.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LogInWindow extends JFrame {
	private JPanel contentPane;
	private JTextField ID;
	private JButton loginbtn, joinbtn,findid,findpw;
	private JPasswordField pw;
	private JLabel main_img;
	private JFrame jframe2;
	
	
	public LogInWindow() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 369, 331);
		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		main_img = new JLabel(new ImageIcon("img/다운로드.jpg"));
		main_img.setBounds(39, 5, 266, 190);
		add(main_img);
		
		JLabel idl = new JLabel("아이디");
		idl.setBounds(18, 205, 51, 15);
		contentPane.add(idl);
		
		ID = new JTextField();
		ID.setBounds(81, 205, 179, 21);
		ID.setToolTipText("ID");
		contentPane.add(ID);
		ID.setColumns(10);
		
		JLabel pwl = new JLabel("비밀번호");
		pwl.setBounds(18, 230, 58, 15);
		contentPane.add(pwl);
		
		pw = new JPasswordField();
		pw.setBounds(81, 227, 179, 21);
		pw.setToolTipText("PASSWORD");
		contentPane.add(pw);
		

		
		loginbtn = new JButton("로그인");
		loginbtn.setBounds(264, 205, 77, 40);
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
