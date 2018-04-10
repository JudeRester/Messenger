package messenger.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import messenger.common.MemberDTO;
import messenger.server.MessengerDAOimpl;

public class searchFriend extends JFrame{
	private JPanel contentPane;
	private JTextField id;
	private JTable table;
	private ArrayList<MemberDTO> result;
	public searchFriend(String myid) {
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
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"아이디", "이름", "별칭"}
			));
		table.setBounds(24, 42, 278, 157);
		JButton searchbtn = new JButton("검색");
		searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table.setModel(new DefaultTableModel(
							new Object[][] {},
							new String[] {"아이디", "이름", "별칭"}
						));
					MessengerDAOimpl dao = new MessengerDAOimpl();
					result = dao.searchingFri("%"+id.getText()+"%");
					for(int i=0;i<result.size();i++) {
						if(!myid.equals(result.get(i).getId())) {
						Object[] tmp = {result.get(i).getId(),result.get(i).getName(),result.get(i).getAlias()};
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.addRow(tmp);
						}
					}
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
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
		
		scrollPane.setViewportView(table);
		
		JButton addfriend = new JButton("친구 추가");
		addfriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				MessengerDAOimpl dao = new MessengerDAOimpl();
				String fi = (String)table.getValueAt(table.getSelectedRow(), 0);
				dao.addFriend(myid, fi);
				JOptionPane.showMessageDialog(null, fi+"님이 친구로 추가되었습니다" , "", JOptionPane.INFORMATION_MESSAGE);
				
				Mainframe.jframe.dispose();
				new Mainframe(myid);

				}catch(SQLException e1) {
				e1.printStackTrace();
				}
			}
		});
		addfriend.setBounds(104, 204, 97, 23);
		contentPane.add(addfriend);
	}
}
