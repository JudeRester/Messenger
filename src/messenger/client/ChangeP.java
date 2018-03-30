package messenger.client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class ChangeP extends JFrame {
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	public ChangeP(JFrame jframe, String title) {
		setResizable(false);
		setVisible(true);
		setBounds(100, 100, 242, 156);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("새 비밀번호");
		label.setForeground(Color.LIGHT_GRAY);
		label.setBounds(12, 20, 81, 15);
		contentPane.add(label);

		JLabel lblNewLabel = new JLabel("비밀번호 확인");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(12, 53, 81, 15);
		contentPane.add(lblNewLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(105, 17, 114, 21);
		contentPane.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(105, 50, 114, 21);
		contentPane.add(passwordField_1);

		JButton submitb = new JButton("SUBMIT");
		submitb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		submitb.setBounds(12, 81, 97, 23);
		contentPane.add(submitb);

		JButton cancelb = new JButton("CANCEL");
		cancelb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelb.setBounds(122, 81, 97, 23);
		contentPane.add(cancelb);
	}
}
