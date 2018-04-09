package messenger.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class searchFriend extends JFrame{
	private JPanel contentPane;
	private JTextField id;
	private JTable table;
	public searchFriend() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 336, 271);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		id = new JTextField();
		id.setBounds(67, 11, 159, 21);
		contentPane.add(id);
		id.setColumns(10);
		
		JButton searchbtn = new JButton("검색");
		searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		searchbtn.setBounds(238, 10, 64, 23);
		contentPane.add(searchbtn);
		
		JLabel label = new JLabel("아이디");
		label.setBounds(24, 14, 45, 15);
		contentPane.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 42, 278, 157);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			
			},
			new String[] {
				"아이디", "이름", "별칭"
			}
		));
		table.setBounds(24, 42, 278, 157);
		scrollPane.setViewportView(table);
		
		JButton addfriend = new JButton("친구 추가");
		addfriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addfriend.setBounds(104, 204, 97, 23);
		contentPane.add(addfriend);
	}
}
