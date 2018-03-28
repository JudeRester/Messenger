package messenger.client;

import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class FindID extends JFrame{
	private JPanel contentPane;
	private JTextField name;
	private JTextField birth;
	private JTextField phone;
	
	public FindID(JFrame jframe, String title) {
		setResizable(false);
		setVisible(true);

		setTitle("아이디 찾기");
		setBounds(100, 100, 238, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		name = new JTextField();
		name.setBounds(94, 10, 116, 21);
		contentPane.add(name);
		name.setColumns(10);
		
		birth = new JTextField();
		birth.setColumns(10);
		birth.setBounds(94, 42, 116, 21);
		contentPane.add(birth);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(94, 75, 116, 21);
		contentPane.add(phone);
		
		JLabel label = new JLabel("이름");
		label.setBounds(12, 10, 57, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("생년월일");
		label_1.setBounds(12, 42, 57, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("휴대폰번호");
		label_2.setBounds(12, 75, 70, 15);
		contentPane.add(label_2);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
