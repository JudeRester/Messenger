package messenger.client;
import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

class ChattingWindow extends JFrame {
	static String myid;
	static String fri;
	private JPanel contentPane;
	private JTextField tf1;
	private JTextArea ta1;
	
	public ChattingWindow(String id, String fid) {
		myid=id;
		fri = fid;
		setTitle(fid+"님과의 채팅");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 457, 485);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel p1 = new JPanel();
		contentPane.add(p1, BorderLayout.CENTER);
		p1.setLayout(new BorderLayout(0, 0));
		
		ta1 = new JTextArea();
		p1.add(ta1);
		JScrollPane js = new JScrollPane(ta1);
		p1.add(js);
		
		JPanel p2 = new JPanel();
		contentPane.add(p2, BorderLayout.SOUTH);
		
		tf1 = new JTextField();
		tf1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == Event.ENTER) {			
					send(tf1.getText());
					tf1.setText("");
				}
			}
		});		

		p2.add(tf1);
		tf1.setColumns(31);
		
		JButton btn1 = new JButton("전송");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(tf1.getText());
				tf1.setText("");
				
			}
		});
		p2.add(btn1);
		tf1.requestFocus();
		ta1.setEditable(false);
		setResizable(false);
	}




	void send(String data) {
		
		ta1.append("\r\n");
		ta1.append("["+myid+"]:"+data);

	}
}
