package window;

import impl.IBook;
import impl.ILogin;
import impl.IReader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import entity.Book;
import entity.Reader;



public class normalUser extends JFrame{

	private static JPanel topPanel = null;
	private static JPanel leftPanel = null;
	private static JTree tree = null;
	
	private static JPanel rightPanel = null;
	IReader operationReader = new IReader();
	IBook operationBook = new IBook();
	public static Vector<String>[] readerListCastToVector(List<Reader> reader){
		Vector<String>[]v = new Vector[reader.size()];
		for(int i = 0;i < reader.size();i++){	
			v[i] = new Vector<String>();
			v[i].add(reader.get(i).getUserName());
			v[i].add(reader.get(i).getName());
			v[i].add(reader.get(i).getSex());
			v[i].add(reader.get(i).getType());
			v[i].add(String.valueOf(reader.get(i).getMax_num()));
			v[i].add(String.valueOf(reader.get(i).getDays_num()));
		}
		return v;
	}
	public static Vector<String>[] bookListCastToVector(List<Book> book){
		Vector<String>[]v = new Vector[book.size()];
		for(int i = 0;i < book.size();i++){	
			v[i] = new Vector<String>();
			v[i].add(String.valueOf(book.get(i).getId()));
			v[i].add(book.get(i).getName());
			v[i].add(book.get(i).getType());
			v[i].add(book.get(i).getAuthor());
			v[i].add(book.get(i).getTranslator());
			v[i].add(book.get(i).getPublisher());
			v[i].add(String.valueOf(book.get(i).getPublisher_time()));
			v[i].add(String.valueOf(book.get(i).getStock()));
			v[i].add(String.valueOf(book.get(i).getPrice()));
			
		}
		return v;
	}
	public static void clearModel(DefaultTableModel model){
		while(model.getRowCount()>0){
		      model.removeRow(model.getRowCount()-1);
		 }
	}
	public normalUser(){
		
		
		top();
		left();
		
		rightPanel = new JPanel();
		
		this.setVisible(true);
		this.setSize(1050, 600);
		
		
		
	}
	public void top(){
		topPanel = new JPanel();
		
		JLabel title = new JLabel("��ͨ�û�");
		title.setFont(new Font("������κ", Font.PLAIN, 38));
		JLabel currentUser = new JLabel("��ǰ�û���"+ILogin.currentUser);
		topPanel.add(title);
		topPanel.add(currentUser);
		this.add(topPanel, BorderLayout.NORTH);
	}
	public void left(){
		leftPanel = new JPanel();
		
		//���������
		DefaultMutableTreeNode menu = new DefaultMutableTreeNode("��ͨ�û�����");
		DefaultMutableTreeNode menu1 =  new DefaultMutableTreeNode("��ѯ����");
		DefaultMutableTreeNode menu11 = new DefaultMutableTreeNode("������Ϣ");
		DefaultMutableTreeNode menu12 = new DefaultMutableTreeNode("ͼ����Ϣ");
		menu1.add(menu11);
		menu1.add(menu12);
		DefaultMutableTreeNode menu2 = new DefaultMutableTreeNode("ϵͳ����");
		DefaultMutableTreeNode menu21 = new DefaultMutableTreeNode("�����޸�");
		DefaultMutableTreeNode menu22 = new DefaultMutableTreeNode("�˳�ϵͳ");
		menu2.add(menu21);
		menu2.add(menu22);
		menu.add(menu1);
		menu.add(menu2);
		tree = new JTree(menu);
		
		leftPanel.setSize(150,550);
		leftPanel.setBackground(Color.white);
		leftPanel.add(tree);
		
		this.add(leftPanel, BorderLayout.WEST);
		
		
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();           
				if (node == null) 
					return;           
				Object nodeInfo = node.getUserObject(); 
				if(nodeInfo.equals("������Ϣ"))
					rightPanelReader();
				if(nodeInfo.equals("ͼ����Ϣ"))
					rightPanelBooks();
				if(nodeInfo.equals("�����޸�"))
					rightPanelRePassword();
				if(nodeInfo.equals("�˳�ϵͳ"))
					System.exit(0);
			}
		});
		
	}
	public void rightPanelReader(){
		this.remove(rightPanel);
		rightPanel = new JPanel(new BorderLayout());
//		JPanel rightPanelReaderInfo = new JPanel();
//		rightPanelReaderInfo.setLayout(new BorderLayout());
		
		JPanel rightPanelReaderInfoTop = new JPanel(new BorderLayout());
		JPanel rightPanelReaderInfoTopa = new JPanel(new BorderLayout());
		//��ӱ�����Ϣ
		Border titleRightPanelReaderInfoTopa=BorderFactory.createTitledBorder("��ǰ�û���Ϣ");            
		rightPanelReaderInfoTopa.setBorder(titleRightPanelReaderInfoTopa); 
		
		rightPanelReaderInfoTopa.setLayout(new GridLayout(3, 2));
		rightPanelReaderInfoTopa.add(new JLabel("������"+operationReader.queryCurrentUserInfo().getName()), 0, 0);
		rightPanelReaderInfoTopa.add(new JLabel("�Ա�"+operationReader.queryCurrentUserInfo().getSex()), 1, 0);
		rightPanelReaderInfoTopa.add(new JLabel("�������ͣ�"+operationReader.queryCurrentUserInfo().getType()), 2, 1);
		rightPanelReaderInfoTopa.add(new JLabel("����������"+operationReader.queryCurrentUserInfo().getMax_num()), 3, 1);
		rightPanelReaderInfoTopa.add(new JLabel("�������գ�"+operationReader.queryCurrentUserInfo().getDays_num()), 3, 2);
		
		
		rightPanelReaderInfoTop.add(rightPanelReaderInfoTopa, BorderLayout.CENTER);
		
//-------------------------------------------rightPanelReaderInfoBottom----------------------------------------------		
		JPanel rightPanelReaderInfoBottom = new JPanel(new BorderLayout());
		//��ӱ�����Ϣ
		Border titleRightPanelReaderInfoBottom=BorderFactory.createTitledBorder("��ѯ�û���Ϣ");            
		rightPanelReaderInfoBottom.setBorder(titleRightPanelReaderInfoBottom); 
		JPanel rightPanelReaderInfoBottom1 = new JPanel();
		rightPanelReaderInfoBottom1.add(new JLabel("�������û�����"));
		final JTextField txtQueryReaderByName = new JTextField(10);
		JButton btnQueryReaderByName = new JButton("��ѯ");
		
		rightPanelReaderInfoBottom1.add(txtQueryReaderByName);
		rightPanelReaderInfoBottom1.add(btnQueryReaderByName);
		
		
		JTable table = null;
		Vector<Vector<String>> v1 = null;
		List<Reader> reader = operationReader.queryAllReader();
		v1 = new Vector<Vector<String>>();
		Vector<String> v2 = new Vector<String>();

		for(int i = 0;i<readerListCastToVector(reader).length;i++)
			v1.add(readerListCastToVector(reader)[i]);
		
		v2.add("�û���");
		v2.add("����");
		v2.add("�Ա�");
		v2.add("��������");
		v2.add("maxNum");
		v2.add("daysNum");
		
		
		final DefaultTableModel model = new DefaultTableModel(v1,v2);
		table = new JTable(model);
		
		JScrollPane rightPanelReaderInfoBottom2 = new JScrollPane(table);
		
//		rightPanelReaderInfoBottom.add(table.getTableHeader(), BorderLayout.PAGE_START);  
//		rightPanelReaderInfoBottom.add(table, BorderLayout.CENTER);
		
		
		btnQueryReaderByName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				clearModel(model);
				List<Reader> readerList = operationReader.queryReaderByName(txtQueryReaderByName.getText());
				for(int i = 0;i<readerList.size();i++){
					Vector<String> v = new Vector<String>();
					v.add(readerList.get(i).getName());
					v.add(readerList.get(i).getSex());
					v.add(readerList.get(i).getType());
					v.add(String.valueOf(readerList.get(i).getMax_num()));
					v.add(String.valueOf(readerList.get(i).getDays_num()));
					model.addRow(v);
				}
			}
		});
		
		rightPanelReaderInfoBottom.add(rightPanelReaderInfoBottom1, BorderLayout.NORTH);
		rightPanelReaderInfoBottom.add(rightPanelReaderInfoBottom2, BorderLayout.CENTER);
		
		rightPanel.add(rightPanelReaderInfoTop, BorderLayout.NORTH);
		rightPanel.add(rightPanelReaderInfoBottom, BorderLayout.CENTER);
		
//		rightPanel.add(rightPanel);
		this.add(rightPanel, BorderLayout.CENTER);
//		this.add(rightPanelReaderInfo, BorderLayout.CENTER);
	}
	public void rightPanelBooks(){
		this.remove(rightPanel);
		rightPanel = new JPanel(new BorderLayout());
		//��ӱ�����Ϣ
		Border titleRightPanel=BorderFactory.createTitledBorder("��ѯͼ����Ϣ");  
		rightPanel.setBorder(titleRightPanel); 
		JPanel rightPanelBooksTop = new JPanel();
		rightPanelBooksTop.add(new JLabel("ͼ�����ƣ�"));
		final JTextField txtQueryBookName = new JTextField(10);
		rightPanelBooksTop.add(txtQueryBookName);
		JButton btnQueryBookByName = new JButton("��ѯ");
		rightPanelBooksTop.add(btnQueryBookByName);
		
		
		JTable table = null;
		Vector<Vector<String>> v1 = null;
		List<Book> book = operationBook.queryAllBooks();
		v1 = new Vector<Vector<String>>();
		Vector<String> v2 = new Vector<String>();

		for(int i = 0;i<bookListCastToVector(book).length;i++)
			v1.add(bookListCastToVector(book)[i]);
		
		v2.add("book_id");
		v2.add("����");
		v2.add("���");
		v2.add("����");
		v2.add("����");
		v2.add("������");
		v2.add("��������");
		v2.add("ʣ����");
		v2.add("�۸�");
		
		
		final DefaultTableModel model = new DefaultTableModel(v1,v2);
		table = new JTable(model);
		
		
		JScrollPane rightPanelBookInfo = new JScrollPane(table);
//		rightPanelBookInfo.add(table.getTableHeader(), BorderLayout.PAGE_START);  
//		rightPanelBookInfo.add(table, BorderLayout.CENTER);
		
		rightPanel.add(rightPanelBooksTop, BorderLayout.NORTH);
		rightPanel.add(rightPanelBookInfo, BorderLayout.CENTER);
		
		
		btnQueryBookByName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				clearModel(model);
				List<Book> bookList = operationBook.queryBooksByName(txtQueryBookName.getText());
				for(int i = 0;i<bookList.size();i++){
					Vector<String> v = new Vector<String>();
					v.add(bookList.get(i).getName());
					v.add(bookList.get(i).getType());
					v.add(bookList.get(i).getAuthor());
					v.add(bookList.get(i).getTranslator());
					v.add(bookList.get(i).getPublisher());
					v.add(String.valueOf(bookList.get(i).getPublisher_time()));
					v.add(String.valueOf(bookList.get(i).getStock()));
					v.add(String.valueOf(bookList.get(i).getPrice()));
					model.addRow(v);
				}
			}
		});
		
		
		this.add(rightPanel, BorderLayout.CENTER);
//		this.add(rightPanelBooksTop, BorderLayout.NORTH);
//		this.add(rightPanelBookInfo, BorderLayout.CENTER);
	}
	public void rightPanelRePassword(){
		this.remove(rightPanel);
		rightPanel = new JPanel();
		
		GridBagLayout gridBag = new GridBagLayout();
		JPanel rePasswordPanel = new JPanel(gridBag);
		
		//��ӱ�����Ϣ
		Border titleRePasswordPanel=BorderFactory.createTitledBorder("�޸�����");            
		rePasswordPanel.setBorder(titleRePasswordPanel);   
		
		JPanel panel1 = new JPanel();
		JLabel oldPassword = new JLabel("ԭʼ���룺");
		panel1.add(oldPassword);
		final JTextField txtOldPassword = new JTextField(10);
		panel1.add(txtOldPassword);
		gridBag.setConstraints(panel1, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		JPanel panel2 = new JPanel();
		JLabel newPassword = new JLabel("�µ����룺");
		panel2.add(newPassword);
		final JTextField txtNewPassword = new JTextField(10);
		panel2.add(txtNewPassword);
		gridBag.setConstraints(panel2, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		JPanel panel3 = new JPanel();
		JLabel againNewPassword = new JLabel("�ٴ����룺");
		panel3.add(againNewPassword);
		final JTextField txtAgainNewPassword = new JTextField(10);
		panel3.add(txtAgainNewPassword);
		gridBag.setConstraints(panel3, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		JButton btnUpdatePassword = new JButton("�޸�");
		gridBag.setConstraints(btnUpdatePassword, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		btnUpdatePassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!txtOldPassword.getText().equals("")&&!txtNewPassword.getText().equals("")&&!txtAgainNewPassword.getText().equals("")){
					if(txtOldPassword.getText().equals(operationReader.queryCurrentUserInfo().getPassword())){						
						if(txtNewPassword.getText().equals(txtAgainNewPassword.getText())){
							operationReader.rePassword(txtNewPassword.getText());
							JOptionPane.showMessageDialog(rightPanel, "�޸ĳɹ���");
						}
						else
							JOptionPane.showMessageDialog(rightPanel, "������������벻ƥ�䣡����������");
					}
					else
						JOptionPane.showMessageDialog(rightPanel, "ԭʼ���������������������");
				}
				else
					JOptionPane.showMessageDialog(rightPanel, "��������Ϣ��");
			}
		});
		
		rePasswordPanel.add(panel1);
		rePasswordPanel.add(panel2);
		rePasswordPanel.add(panel3);
		rePasswordPanel.add(btnUpdatePassword);

		rightPanel.add(rePasswordPanel);
		this.add(rightPanel, BorderLayout.CENTER);
//		this.add(rePasswordPanel, BorderLayout.CENTER);
	}
}
