package window;

import impl.IBook;
import impl.IBorrow;
import impl.ILogin;
import impl.IReader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import entity.Book;
import entity.Borrow;
import entity.Reader;


public class adminUser extends JFrame{
	private static JPanel topPanel = null;
	private static JPanel leftPanel = null;
	private static JTree tree = null;
	public static JTable tableReaderWeiHu = null;
	public static JTable tableBookWeiHu = null;
	public static JTable tableBookBorrow = null;
	private static JPanel rightPanel = null;
	IReader operationReader = new IReader();
	IBook operationBook = new IBook();
	IBorrow operationBorrow = new IBorrow();
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
	public static Vector<String>[] borrowListCastToVector(List<Borrow> borrow){
		Vector<String>[]v = new Vector[borrow.size()];
		for(int i = 0;i < borrow.size();i++){	
			v[i] = new Vector<String>();
			v[i].add(borrow.get(i).getBookID());
			v[i].add(borrow.get(i).getUserName());
			v[i].add(String.valueOf(borrow.get(i).getBorrowDate()));
			v[i].add(String.valueOf(borrow.get(i).getBackDate()));
		}
		return v;
	}
	public static void clearModel(DefaultTableModel model){
		while(model.getRowCount()>0){
		      model.removeRow(model.getRowCount()-1);
		 }
	}
	public adminUser(){
	
		top();
		left();
		rightPanel = new JPanel();
		
		this.setSize(1050, 600);
		this.setVisible(true);
	}
	public void top(){
		topPanel = new JPanel();
//		topPanel.setSize(1050,50);
		JLabel title = new JLabel("管理员用户");
		title.setFont(new Font("华文新魏", Font.PLAIN, 38));
		JLabel currentUser = new JLabel("当前用户："+ILogin.currentUser);
		topPanel.add(title);
		topPanel.add(currentUser);
		this.add(topPanel, BorderLayout.NORTH);
	}
	public void left(){
		
		//创建树结点
		DefaultMutableTreeNode menu = new DefaultMutableTreeNode("管理员用户功能");
		DefaultMutableTreeNode menu1 =  new DefaultMutableTreeNode("查询管理");
		DefaultMutableTreeNode menu11 = new DefaultMutableTreeNode("读者信息");
		DefaultMutableTreeNode menu12 = new DefaultMutableTreeNode("图书信息");
		menu1.add(menu11);
		menu1.add(menu12);
		DefaultMutableTreeNode menu2 = new DefaultMutableTreeNode("系统管理");
		DefaultMutableTreeNode menu21 = new DefaultMutableTreeNode("密码修改");
		DefaultMutableTreeNode menu22 = new DefaultMutableTreeNode("退出系统");
		menu2.add(menu21);
		menu2.add(menu22);
		DefaultMutableTreeNode menu3 = new DefaultMutableTreeNode("基础维护");
		DefaultMutableTreeNode menu31 = new DefaultMutableTreeNode("读者维护");
		DefaultMutableTreeNode menu32 = new DefaultMutableTreeNode("图书维护");
		menu3.add(menu31);
		menu3.add(menu32);
		DefaultMutableTreeNode menu4 = new DefaultMutableTreeNode("借阅管理");
		DefaultMutableTreeNode menu41 = new DefaultMutableTreeNode("借书");
		DefaultMutableTreeNode menu42 = new DefaultMutableTreeNode("还书");
		menu4.add(menu41);
		menu4.add(menu42);
		menu.add(menu1);
		menu.add(menu3);
		menu.add(menu4);
		menu.add(menu2);
		tree = new JTree(menu);
		
		
		leftPanel = new JPanel();
		leftPanel.setSize(150,550);
		leftPanel.setBackground(Color.white);
		leftPanel.add(tree);
//		leftPanel = new JScrollPane(tree);
//		leftPanel.setBackground(new Color(6));
//		leftPanel.setPreferredSize(new Dimension(150,550));
		
		this.add(leftPanel, BorderLayout.WEST);
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();           
				if (node == null) 
					return;           
				Object nodeInfo = node.getUserObject(); 
				if(nodeInfo.equals("读者信息"))
					rightPanelReader();
				if(nodeInfo.equals("图书信息"))
					rightPanelBooks();
				if(nodeInfo.equals("密码修改"))
					rightPanelRePassword();
				if(nodeInfo.equals("读者维护"))
					rightPanelReaderWeiHu();
				if(nodeInfo.equals("图书维护"))
					rightPanelBookWeiHu();
				if(nodeInfo.equals("借书"))
					rightPanelJie();
				if(nodeInfo.equals("还书"))
					rightPanelHuan();
				if(nodeInfo.equals("退出系统"))
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
		//添加标题信息
		Border titleRightPanelReaderInfoTopa=BorderFactory.createTitledBorder("当前用户信息");            
		rightPanelReaderInfoTopa.setBorder(titleRightPanelReaderInfoTopa); 
		
		rightPanelReaderInfoTopa.setLayout(new GridLayout(3, 2));
		rightPanelReaderInfoTopa.add(new JLabel("姓名："+operationReader.queryCurrentUserInfo().getName()), 0, 0);
		rightPanelReaderInfoTopa.add(new JLabel("性别："+operationReader.queryCurrentUserInfo().getSex()), 1, 0);
		rightPanelReaderInfoTopa.add(new JLabel("读者类型："+operationReader.queryCurrentUserInfo().getType()), 2, 1);
		rightPanelReaderInfoTopa.add(new JLabel("最大借阅数："+operationReader.queryCurrentUserInfo().getMax_num()), 3, 1);
		rightPanelReaderInfoTopa.add(new JLabel("最大借阅日："+operationReader.queryCurrentUserInfo().getDays_num()), 3, 2);
		
		
		rightPanelReaderInfoTop.add(rightPanelReaderInfoTopa, BorderLayout.CENTER);
		
//-------------------------------------------rightPanelReaderInfoBottom----------------------------------------------		
		JPanel rightPanelReaderInfoBottom = new JPanel(new BorderLayout());
		//添加标题信息
		Border titleRightPanelReaderInfoBottom=BorderFactory.createTitledBorder("查询用户信息");            
		rightPanelReaderInfoBottom.setBorder(titleRightPanelReaderInfoBottom); 
		JPanel rightPanelReaderInfoBottom1 = new JPanel();
		rightPanelReaderInfoBottom1.add(new JLabel("请输入用户名："));
		final JTextField txtQueryReaderByName = new JTextField(10);
		JButton btnQueryReaderByName = new JButton("查询");
		
		rightPanelReaderInfoBottom1.add(txtQueryReaderByName);
		rightPanelReaderInfoBottom1.add(btnQueryReaderByName);
		
		
		JTable table = null;
		Vector<Vector<String>> v1 = null;
		List<Reader> reader = operationReader.queryAllReader();
		v1 = new Vector<Vector<String>>();
		Vector<String> v2 = new Vector<String>();

		for(int i = 0;i<readerListCastToVector(reader).length;i++)
			v1.add(readerListCastToVector(reader)[i]);
		
		v2.add("用户名");
		v2.add("姓名");
		v2.add("性别");
		v2.add("读者类型");
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
		//添加标题信息
		Border titleRightPanel=BorderFactory.createTitledBorder("查询图书信息");  
		rightPanel.setBorder(titleRightPanel); 
		JPanel rightPanelBooksTop = new JPanel();
		rightPanelBooksTop.add(new JLabel("图书名称："));
		final JTextField txtQueryBookName = new JTextField(10);
		rightPanelBooksTop.add(txtQueryBookName);
		JButton btnQueryBookByName = new JButton("查询");
		rightPanelBooksTop.add(btnQueryBookByName);
		
		
		JTable table = null;
		Vector<Vector<String>> v1 = null;
		List<Book> book = operationBook.queryAllBooks();
		v1 = new Vector<Vector<String>>();
		Vector<String> v2 = new Vector<String>();

		for(int i = 0;i<bookListCastToVector(book).length;i++)
			v1.add(bookListCastToVector(book)[i]);
		
		v2.add("book_id");
		v2.add("名称");
		v2.add("类别");
		v2.add("作者");
		v2.add("翻译");
		v2.add("出版社");
		v2.add("出版日期");
		v2.add("剩余量");
		v2.add("价格");
		
		
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
		
		//添加标题信息
		Border titleRePasswordPanel=BorderFactory.createTitledBorder("修改密码");            
		rePasswordPanel.setBorder(titleRePasswordPanel);   
		
		JPanel panel1 = new JPanel();
		JLabel oldPassword = new JLabel("原始密码：");
		panel1.add(oldPassword);
		final JTextField txtOldPassword = new JTextField(10);
		panel1.add(txtOldPassword);
		gridBag.setConstraints(panel1, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		JPanel panel2 = new JPanel();
		JLabel newPassword = new JLabel("新的密码：");
		panel2.add(newPassword);
		final JTextField txtNewPassword = new JTextField(10);
		panel2.add(txtNewPassword);
		gridBag.setConstraints(panel2, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		JPanel panel3 = new JPanel();
		JLabel againNewPassword = new JLabel("再次输入：");
		panel3.add(againNewPassword);
		final JTextField txtAgainNewPassword = new JTextField(10);
		panel3.add(txtAgainNewPassword);
		gridBag.setConstraints(panel3, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		JButton btnUpdatePassword = new JButton("修改");
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
							JOptionPane.showMessageDialog(rightPanel, "修改成功！");
						}
						else
							JOptionPane.showMessageDialog(rightPanel, "两次输入的密码不匹配！请重新输入");
					}
					else
						JOptionPane.showMessageDialog(rightPanel, "原始密码输入错误！请重新输入");
				}
				else
					JOptionPane.showMessageDialog(rightPanel, "请完善信息！");
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
	public void rightPanelReaderWeiHu(){
		this.remove(rightPanel);
		rightPanel = new JPanel(new BorderLayout());
		
		
		//---------------------------readerWeiHuWestPanel----------------------------
		
		
		Vector<Vector<String>> v1 = null;
		List<Reader> reader = operationReader.queryAllReader();
		v1 = new Vector<Vector<String>>();
		Vector<String> v2 = new Vector<String>();

		for(int i = 0;i<readerListCastToVector(reader).length;i++)
			v1.add(readerListCastToVector(reader)[i]);
		
		v2.add("用户名");
		v2.add("姓名");
		v2.add("性别");
		v2.add("读者类型");
		v2.add("maxNum");
		v2.add("daysNum");
		
		
		final DefaultTableModel model = new DefaultTableModel(v1,v2);
		tableReaderWeiHu = new JTable(model);
		
		JScrollPane readerWeiHuWestPanel = new JScrollPane(tableReaderWeiHu);
		
//		readerWeiHuWestPanel.add(tableReaderWeiHu.getTableHeader(), BorderLayout.PAGE_START);  
//		readerWeiHuWestPanel.add(tableReaderWeiHu, BorderLayout.CENTER);
		
		
		//-------------------------------------readerWeiHuEastPanel---------------------------------------------------
		
		
		GridBagLayout gridBag = new GridBagLayout();
		JPanel readerWeiHuEastPanel = new JPanel(gridBag);
		//添加标题信息
		Border titleReaderWeiHuEastPanel=BorderFactory.createTitledBorder("读者信息");            
		readerWeiHuEastPanel.setBorder(titleReaderWeiHuEastPanel);   
		
		
		
        JLabel lb1 = new JLabel("用户名:");
		final JTextField textUserName = new JTextField(10);
		gridBag.setConstraints(lb1, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		gridBag.setConstraints(textUserName, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		
		
		JLabel lb2 = new JLabel("姓名:");
		final JTextField textReaderName = new JTextField(10);
		gridBag.setConstraints(lb2, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		gridBag.setConstraints(textReaderName, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		JLabel lb3 = new JLabel("性别:");
		final JTextField textSex = new JTextField(10);
		gridBag.setConstraints(lb3, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		gridBag.setConstraints(textSex, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		JLabel lb4 = new JLabel("类型:");
		final JTextField textReaderType = new JTextField(10);
		gridBag.setConstraints(lb4, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		gridBag.setConstraints(textReaderType, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		JLabel lb5 = new JLabel("maxNum:");
		final JTextField textMaxNum = new JTextField(10);
		gridBag.setConstraints(lb5, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		gridBag.setConstraints(textMaxNum, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		
		JLabel lb6 = new JLabel("daysNum:");
		final JTextField textDaysNum = new JTextField(10);
		gridBag.setConstraints(lb6, new GridBagConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		gridBag.setConstraints(textDaysNum, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		readerWeiHuEastPanel.add(lb1);
		readerWeiHuEastPanel.add(lb2);
		readerWeiHuEastPanel.add(lb3);
		readerWeiHuEastPanel.add(lb4);
		readerWeiHuEastPanel.add(lb5);
		readerWeiHuEastPanel.add(lb6);
		readerWeiHuEastPanel.add(textUserName);
		readerWeiHuEastPanel.add(textReaderName);
		readerWeiHuEastPanel.add(textSex);
		readerWeiHuEastPanel.add(textReaderType);
		readerWeiHuEastPanel.add(textMaxNum);
		readerWeiHuEastPanel.add(textDaysNum);
		
		//--------------------------------readerWeiHuBottomPanel----------------------------------------
		JPanel readerWeiHuBottomPanel = new JPanel();
		JButton btnAdd = new JButton("增加");
		JButton btnDel = new JButton("删除");
		JButton btnUpdate = new JButton("修改");
		readerWeiHuBottomPanel.add(btnAdd);
		readerWeiHuBottomPanel.add(btnDel);
		readerWeiHuBottomPanel.add(btnUpdate);
		
		
		
		rightPanel.add(readerWeiHuWestPanel, BorderLayout.CENTER);
		rightPanel.add(readerWeiHuEastPanel, BorderLayout.EAST);
		rightPanel.add(readerWeiHuBottomPanel, BorderLayout.SOUTH);
		this.add(rightPanel, BorderLayout.CENTER);
		
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Reader reader = new Reader();
				reader.setUserName(textUserName.getText());
				reader.setName(textReaderName.getText());
				reader.setSex(textSex.getText());
				reader.setType(textReaderType.getText());
				reader.setMax_num(Integer.parseInt(textMaxNum.getText()));
				reader.setDays_num(Integer.parseInt(textDaysNum.getText()));
				operationReader.addReader(reader);
				Vector<String> v = new Vector<String>();
				v.add(reader.getUserName());
				v.add(reader.getName());
				v.add(reader.getSex());
				v.add(reader.getType());
				v.add(String.valueOf(reader.getMax_num()));
				v.add(String.valueOf(reader.getDays_num()));
				model.addRow(v);
			}
		});
		btnDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int option = JOptionPane.showConfirmDialog(null, "确认删除？");
				if(option == JOptionPane.YES_OPTION);{
					int []index = tableReaderWeiHu.getSelectedRows();
					for(int i=0;i<index.length;i++){
						String userName = String.valueOf(tableReaderWeiHu.getValueAt(index[i], 0));
						operationReader.delReader(textUserName.getText());
						model.removeRow(index[i]);
					}
					JOptionPane.showMessageDialog(rightPanel, "删除成功！");
				}
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int option = JOptionPane.showConfirmDialog(null, "确认修改？");
				if(option == JOptionPane.YES_OPTION);{
					String userName = String.valueOf(tableReaderWeiHu.getValueAt(tableReaderWeiHu.getSelectedRow(), 0));
					Reader reader = new Reader();
					reader.setUserName(textUserName.getText());
					reader.setName(textReaderName.getText());
					reader.setSex(textSex.getText());
					reader.setType(textReaderType.getText());
					reader.setMax_num(Integer.parseInt(textMaxNum.getText()));
					reader.setDays_num(Integer.parseInt(textDaysNum.getText()));
					operationReader.updateReader(reader, userName);	
					JOptionPane.showMessageDialog(rightPanel, "修改成功！");
					model.setValueAt(textReaderName.getText(), tableReaderWeiHu.getSelectedRow(), 1);
					model.setValueAt(textSex.getText(), tableReaderWeiHu.getSelectedRow(), 2);
					model.setValueAt(textReaderType.getText(), tableReaderWeiHu.getSelectedRow(), 3);
					model.setValueAt(textMaxNum.getText(), tableReaderWeiHu.getSelectedRow(), 4);
					model.setValueAt(textDaysNum.getText(), tableReaderWeiHu.getSelectedRow(), 5);
				}
			}
		});
		tableReaderWeiHu.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				int []index = tableReaderWeiHu.getSelectedRows();
				for(int i=0;i<index.length;i++){
					String userName = String.valueOf(tableReaderWeiHu.getValueAt(index[i], 0));
					String name = String.valueOf(tableReaderWeiHu.getValueAt(index[i], 1));
					String sex = String.valueOf(tableReaderWeiHu.getValueAt(index[i], 2));
					String type = String.valueOf(tableReaderWeiHu.getValueAt(index[i], 3));
					String maxNum = String.valueOf(tableReaderWeiHu.getValueAt(index[i], 4));
					String daysNum = String.valueOf(tableReaderWeiHu.getValueAt(index[i], 5));
					textUserName.setText(userName);
					textReaderName.setText(name);
					textSex.setText(sex);
					textReaderType.setText(type);
					textMaxNum.setText(maxNum);
					textDaysNum.setText(daysNum);
				}
			}
		});
		
		
		
	}
	public void rightPanelBookWeiHu(){
		this.remove(rightPanel);
		rightPanel = new JPanel(new BorderLayout());
		
		
		//---------------------------bookWeiHuWestPanel----------------------------
		
		
		Vector<Vector<String>> v1 = null;
		List<Book> bookList = operationBook.queryAllBooks();
		v1 = new Vector<Vector<String>>();
		Vector<String> v2 = new Vector<String>();

		for(int i = 0;i<bookListCastToVector(bookList).length;i++)
			v1.add(bookListCastToVector(bookList)[i]);
		
		v2.add("id");
		v2.add("名称");
		v2.add("类别");
		v2.add("作者");
		v2.add("翻译");
		v2.add("出版社");
		v2.add("出版日期");
		v2.add("剩余量");
		v2.add("价格");
		
		
		final DefaultTableModel model = new DefaultTableModel(v1,v2);
		tableBookWeiHu = new JTable(model);
	
		
		JScrollPane bookWeiHuWestPanel = new JScrollPane(tableBookWeiHu);
//		bookWeiHuWestPanel.add(tableBookWeiHu.getTableHeader(), BorderLayout.PAGE_START);  
//		bookWeiHuWestPanel.add(tableBookWeiHu, BorderLayout.CENTER);
		
		
		//-------------------------------------bookWeiHuEastPanel---------------------------------------------------
		GridBagLayout gridBag = new GridBagLayout();
		JPanel bookWeiHuEastPanel = new JPanel(gridBag);
		
		//添加标题信息
		Border titleBookWeiHuEastPanel=BorderFactory.createTitledBorder("图书信息");            
		bookWeiHuEastPanel.setBorder(titleBookWeiHuEastPanel);  
		
		
		JLabel lb1 = new JLabel("bookID:");
		final JTextField textBookID = new JTextField(10);
		gridBag.setConstraints(lb1, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		gridBag.setConstraints(textBookID, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		JLabel lb2 = new JLabel("名称:");
		final JTextField textBookName = new JTextField(10);
		gridBag.setConstraints(lb2, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		gridBag.setConstraints(textBookName, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		JLabel lb3 = new JLabel("类别:");
		final JTextField textType = new JTextField(10);
		gridBag.setConstraints(lb3, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		gridBag.setConstraints(textType, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		JLabel lb4 = new JLabel("作者:");
		final JTextField textAuthor = new JTextField(10);
		gridBag.setConstraints(lb4, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		gridBag.setConstraints(textAuthor, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		JLabel lb5 = new JLabel("翻译:");
		final JTextField textTranslator = new JTextField(10);
		gridBag.setConstraints(lb5, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		gridBag.setConstraints(textTranslator, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		JLabel lb6 = new JLabel("出版社:");
		final JTextField textPublisher = new JTextField(10);
		gridBag.setConstraints(lb6, new GridBagConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		gridBag.setConstraints(textPublisher, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		JLabel lb7 = new JLabel("出版日期:");
		final JTextField textPublisherTime = new JTextField(10);
		gridBag.setConstraints(lb7, new GridBagConstraints(0, 6, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		gridBag.setConstraints(textPublisherTime, new GridBagConstraints(1, 6, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		JLabel lb8 = new JLabel("剩余量:");
		final JTextField textStock = new JTextField(10);
		gridBag.setConstraints(lb8, new GridBagConstraints(0, 7, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		gridBag.setConstraints(textStock, new GridBagConstraints(1, 7, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		JLabel lb9 = new JLabel("价格:");
		final JTextField textPrice = new JTextField(10);
		gridBag.setConstraints(lb9, new GridBagConstraints(0, 8, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		gridBag.setConstraints(textPrice, new GridBagConstraints(1, 8, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		bookWeiHuEastPanel.add(lb1);
		bookWeiHuEastPanel.add(lb2);
		bookWeiHuEastPanel.add(lb3);
		bookWeiHuEastPanel.add(lb4);
		bookWeiHuEastPanel.add(lb5);
		bookWeiHuEastPanel.add(lb6);
		bookWeiHuEastPanel.add(lb7);
		bookWeiHuEastPanel.add(lb8);
		bookWeiHuEastPanel.add(lb9);
		bookWeiHuEastPanel.add(textBookID);
		bookWeiHuEastPanel.add(textBookName);
		bookWeiHuEastPanel.add(textType);
		bookWeiHuEastPanel.add(textAuthor);
		bookWeiHuEastPanel.add(textTranslator);
		bookWeiHuEastPanel.add(textPublisher);
		bookWeiHuEastPanel.add(textPublisherTime);
		bookWeiHuEastPanel.add(textStock);
		bookWeiHuEastPanel.add(textPrice);
		
		
		//--------------------------------bookWeiHuBottomPanel----------------------------------------
		JPanel bookWeiHuBottomPanel = new JPanel();
		JButton btnAdd = new JButton("增加");
		JButton btnDel = new JButton("删除");
		JButton btnUpdate = new JButton("修改");
		bookWeiHuBottomPanel.add(btnAdd);
		bookWeiHuBottomPanel.add(btnDel);
		bookWeiHuBottomPanel.add(btnUpdate);
		
		
		
		rightPanel.add(bookWeiHuWestPanel, BorderLayout.CENTER);
		rightPanel.add(bookWeiHuEastPanel, BorderLayout.EAST);
		rightPanel.add(bookWeiHuBottomPanel, BorderLayout.SOUTH);
		this.add(rightPanel, BorderLayout.CENTER);
		
		
		
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Book book = new Book();
				book.setId(Integer.parseInt(textBookID.getText()));
				book.setName(textBookName.getText());
				book.setType(textType.getText());
				book.setAuthor(textAuthor.getText());
				book.setTranslator(textTranslator.getText());
				book.setPublisher(textPublisher.getText());
				book.setPublisher_time(Date.valueOf(textPublisherTime.getText()));
				book.setStock(Integer.parseInt(textStock.getText()));
				book.setPrice(Double.parseDouble(textPrice.getText()));
				
				operationBook.addBook(book);
				Vector<String> v = new Vector<String>();
				v.add(String.valueOf(book.getId()));
				v.add(book.getName());
				v.add(book.getType());
				v.add(book.getAuthor());
				v.add(book.getTranslator());
				v.add(book.getPublisher());
				v.add(String.valueOf(book.getPublisher_time()));
				v.add(String.valueOf(book.getStock()));
				v.add(String.valueOf(book.getPrice()));
				model.addRow(v);
			}
		});
		btnDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int option = JOptionPane.showConfirmDialog(null, "确认删除？");
				if(option == JOptionPane.YES_OPTION);{
					int []index = tableBookWeiHu.getSelectedRows();
					for(int i=0;i<index.length;i++){
						String bookID = String.valueOf(tableBookWeiHu.getValueAt(index[i], 0));
						operationBook.delBook(Integer.parseInt(bookID));
						model.removeRow(index[i]);
					}
					JOptionPane.showMessageDialog(rightPanel, "删除成功！");
				}
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int option = JOptionPane.showConfirmDialog(null, "确认修改？");
				if(option == JOptionPane.YES_OPTION);{
					String bookID = String.valueOf(tableBookWeiHu.getValueAt(tableBookWeiHu.getSelectedRow(), 0));
					Book book = new Book();
					book.setId(Integer.parseInt(textBookID.getText()));
					book.setName(textBookName.getText());
					book.setType(textType.getText());
					book.setAuthor(textAuthor.getText());
					book.setTranslator(textTranslator.getText());
					book.setPublisher(textPublisher.getText());
					book.setPublisher_time(Date.valueOf(textPublisherTime.getText()));
					book.setStock(Integer.parseInt(textStock.getText()));
					book.setPrice(Double.parseDouble(textPrice.getText()));
					
					operationBook.updateBook(book, Integer.parseInt(bookID));
						
					JOptionPane.showMessageDialog(rightPanel, "修改成功！");
					model.setValueAt(textBookName.getText(), tableBookWeiHu.getSelectedRow(), 1);
					model.setValueAt(textType.getText(), tableBookWeiHu.getSelectedRow(), 2);
					model.setValueAt(textAuthor.getText(), tableBookWeiHu.getSelectedRow(), 3);
					model.setValueAt(textTranslator.getText(), tableBookWeiHu.getSelectedRow(), 4);
					model.setValueAt(textPublisher.getText(), tableBookWeiHu.getSelectedRow(), 5);
					model.setValueAt(textPublisherTime.getText(), tableBookWeiHu.getSelectedRow(), 6);
					model.setValueAt(textStock.getText(), tableBookWeiHu.getSelectedRow(), 7);
					model.setValueAt(textPrice.getText(), tableBookWeiHu.getSelectedRow(), 8);
				}
			}
		});
		tableBookWeiHu.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				int []index = tableBookWeiHu.getSelectedRows();
				for(int i=0;i<index.length;i++){
					String bookID = String.valueOf(tableBookWeiHu.getValueAt(index[i], 0));
					String name = String.valueOf(tableBookWeiHu.getValueAt(index[i], 1));
					String type = String.valueOf(tableBookWeiHu.getValueAt(index[i], 2));
					String author = String.valueOf(tableBookWeiHu.getValueAt(index[i], 3));
					String translator = String.valueOf(tableBookWeiHu.getValueAt(index[i], 4));
					String publisher = String.valueOf(tableBookWeiHu.getValueAt(index[i], 5));
					String publisher_time = String.valueOf(tableBookWeiHu.getValueAt(index[i], 6));
					String stock = String.valueOf(tableBookWeiHu.getValueAt(index[i], 7));
					String price = String.valueOf(tableBookWeiHu.getValueAt(index[i], 8));
					textBookID.setText(bookID);
					textBookName.setText(name);
					textType.setText(type);
					textAuthor.setText(author);
					textTranslator.setText(translator);
					textPublisher.setText(publisher);
					textPublisherTime.setText(publisher_time);
					textStock.setText(stock);
					textPrice.setText(price);
				}
			}
		});
	}
	public void rightPanelJie(){
		this.remove(rightPanel);
		rightPanel = new JPanel(new BorderLayout());
//		--------------------bookBorrowTop---------------------------------------------------
		JPanel bookBorrowTop = new JPanel(new BorderLayout());
		//添加标题信息
		Border titleBookBorrowTop=BorderFactory.createTitledBorder("借阅者信息");            
		bookBorrowTop.setBorder(titleBookBorrowTop); 
//		--------------------bookBorrowTopa-----------------------------------
		JPanel bookBorrowTopa = new JPanel();
		bookBorrowTopa.add(new JLabel("用户名："));
		final JTextField txtUserName = new JTextField(10);
		bookBorrowTopa.add(txtUserName);
		JButton btnQueryByUserName = new JButton("查询");
		bookBorrowTopa.add(btnQueryByUserName);
		
//		-------------------------bookBorrowTopb---------------------------------
		
		JTable tableReader = null;
		Vector<Vector<String>> v1 = null;
		List<Reader> reader = operationReader.queryAllReader();
		v1 = new Vector<Vector<String>>();
		Vector<String> v2 = new Vector<String>();

		for(int i = 0;i<readerListCastToVector(reader).length;i++)
			v1.add(readerListCastToVector(reader)[i]);
		
		v2.add("用户名");
		v2.add("姓名");
		v2.add("性别");
		v2.add("读者类型");
		v2.add("maxNum");
		v2.add("daysNum");
		
		
		final DefaultTableModel model = new DefaultTableModel(v1,v2);
		tableReader = new JTable(model);
		JScrollPane bookBorrowTopb = new JScrollPane(tableReader); 
		bookBorrowTopb.setPreferredSize(new Dimension(700,200));
//		tableReader.setFillsViewportHeight(true);

		
		
		bookBorrowTop.add(bookBorrowTopa, BorderLayout.NORTH);
		bookBorrowTop.add(bookBorrowTopb, BorderLayout.CENTER);
//		----------------------------bookBorrowCenter--------------------------------
		JPanel bookBorrowCenter = new JPanel(new BorderLayout());
		//添加标题信息
		Border titleBookBorrowCenter=BorderFactory.createTitledBorder("借阅图书信息");            
		bookBorrowCenter.setBorder(titleBookBorrowCenter); 
//		--------------------bookBorrowCentera-----------------------------------
		JPanel bookBorrowCentera = new JPanel();
		bookBorrowCentera.add(new JLabel("书籍ID："));
		final JTextField txtBookName = new JTextField(10);
		bookBorrowCentera.add(txtBookName);
		JButton btnQueryByBookName = new JButton("查询");
		bookBorrowCentera.add(btnQueryByBookName);
		
//		--------------------------------------------------------------------	
		JTable tableBook = null;
		Vector<Vector<String>> v11 = null;
		List<Book> book = operationBook.queryAllBooks();
		v11 = new Vector<Vector<String>>();
		Vector<String> v21 = new Vector<String>();

		for(int i = 0;i<bookListCastToVector(book).length;i++)
			v11.add(bookListCastToVector(book)[i]);
		
		v21.add("id");
		v21.add("名称");
		v21.add("类别");
		v21.add("作者");
		v21.add("翻译");
		v21.add("出版社");
		v21.add("出版日期");
		v21.add("剩余量");
		v21.add("价格");
		
		
		final DefaultTableModel model1 = new DefaultTableModel(v11,v21);
		tableBook = new JTable(model1);
		
		JScrollPane bookBorrowCenterb = new JScrollPane(tableBook);  
		bookBorrowCenterb.setPreferredSize(new Dimension(700,200));
//		tableBook.setFillsViewportHeight(true);
		
		
		bookBorrowCenter.add(bookBorrowCentera, BorderLayout.NORTH);
		bookBorrowCenter.add(bookBorrowCenterb, BorderLayout.CENTER);
		
		
//		-----------------------------------bookBorrowBottom------------------------------------
		JPanel bookBorrowBottom = new JPanel();
		JButton btnBorrow = new JButton("借书");
		bookBorrowBottom.add(btnBorrow);
		
		
		
		rightPanel.add(bookBorrowTop, BorderLayout.NORTH);
		rightPanel.add(bookBorrowCenter, BorderLayout.CENTER);
		rightPanel.add(bookBorrowBottom, BorderLayout.SOUTH);
		this.add(rightPanel, BorderLayout.CENTER);
		
		
		btnQueryByUserName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				clearModel(model);
				List<Reader> readerList = operationReader.queryReaderByUserName(txtUserName.getText());
				for(int i = 0;i<readerList.size();i++){
					Vector<String> v = new Vector<String>();
					v.add(readerList.get(i).getUserName());
					v.add(readerList.get(i).getName());
					v.add(readerList.get(i).getSex());
					v.add(readerList.get(i).getType());
					v.add(String.valueOf(readerList.get(i).getMax_num()));
					v.add(String.valueOf(readerList.get(i).getDays_num()));
					model.addRow(v);
				}
			}
		});
		btnQueryByBookName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				clearModel(model1);
				List<Book> bookList = operationBook.queryBooksByBookID(Integer.parseInt(txtBookName.getText()));
				for(int i = 0;i<bookList.size();i++){
					Vector<String> v = new Vector<String>();
					v.add(String.valueOf(bookList.get(i).getId()));
					v.add(bookList.get(i).getName());
					v.add(bookList.get(i).getType());
					v.add(bookList.get(i).getAuthor());
					v.add(bookList.get(i).getTranslator());
					v.add(bookList.get(i).getPublisher());
					v.add(String.valueOf(bookList.get(i).getPublisher_time()));
					v.add(String.valueOf(bookList.get(i).getStock()));
					v.add(String.valueOf(bookList.get(i).getPrice()));
					model1.addRow(v);
				}
			}
		});
		btnBorrow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int option = JOptionPane.showConfirmDialog(null, "确认借书？");
				if(option == JOptionPane.YES_OPTION){
					Borrow borrow = new Borrow();
					borrow.setBookID(txtBookName.getText());
					borrow.setUserName(txtUserName.getText());
					Date borrowDate = new Date(System.currentTimeMillis());
					borrow.setBorrowDate(borrowDate);
					Date backDate = new Date(borrowDate.getTime()+Long.parseLong((String) model.getValueAt(0, 5))*24*3600*1000);
					borrow.setBackDate(backDate);
					operationBorrow.addBorrowInfo(borrow);
					JOptionPane.showMessageDialog(rightPanel, "借书成功！");
				}
			}
		});
		
	}
	public void rightPanelHuan(){
		this.remove(rightPanel);
		rightPanel = new JPanel(new BorderLayout());
		//添加标题信息
		Border titleRightPanel=BorderFactory.createTitledBorder("还书信息");            
		rightPanel.setBorder(titleRightPanel); 
//		-------------------------bookBackTop-------------------------------
		JPanel bookBackPanelTop = new JPanel();
		bookBackPanelTop.add(new JLabel("用户名："));
		final JTextField txtUserName = new JTextField(10);
		bookBackPanelTop.add(txtUserName);
		JButton btnReaderQueryByName = new JButton("查询");
		bookBackPanelTop.add(btnReaderQueryByName);
//		-------------------------bookBackCenter---------------------------------
//		JPanel bookBackPanelCenter = new JPanel();
		
		
		Vector<Vector<String>> v1 = null;
		List<Borrow> borrowList = operationBorrow.queryAllBorrowInfo(txtUserName.getText());
		v1 = new Vector<Vector<String>>();
		Vector<String> v2 = new Vector<String>();

		for(int i = 0;i<borrowListCastToVector(borrowList).length;i++)
			v1.add(borrowListCastToVector(borrowList)[i]);
		
		v2.add("book_id");
		v2.add("userName");
		v2.add("借书日期");
		v2.add("还书日期");
		
		final DefaultTableModel model1 = new DefaultTableModel(v1,v2);
		tableBookBorrow = new JTable(model1);
		
		JScrollPane bookBackPanelCenter = new JScrollPane(tableBookBorrow); 
//		bookBackPanelCenter.setPreferredSize(new Dimension(700,300));
//		tableBookBorrow.setFillsViewportHeight(true);
//		-------------------------bookBackBottom-------------------------------------
		JPanel bookBackPanelBottom = new JPanel();
		JButton btnBookBack = new JButton("还书");
		bookBackPanelBottom.add(btnBookBack);
		
		
		rightPanel.add(bookBackPanelTop, BorderLayout.NORTH);
		rightPanel.add(bookBackPanelCenter, BorderLayout.CENTER);
		rightPanel.add(bookBackPanelBottom, BorderLayout.SOUTH);
		this.add(rightPanel, BorderLayout.CENTER);
		
		btnReaderQueryByName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearModel(model1);
				List<Borrow> borrowList = operationBorrow.queryAllBorrowInfo(txtUserName.getText());
				for(int i = 0;i<borrowList.size();i++){
					Vector<String> v = new Vector<String>();
					v.add(borrowList.get(i).getBookID());
					v.add(borrowList.get(i).getUserName());
					v.add(String.valueOf(borrowList.get(i).getBorrowDate()));
					v.add(String.valueOf(borrowList.get(i).getBackDate()));
					model1.addRow(v);
				}
			}
		});
		
		
		btnBookBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int option = JOptionPane.showConfirmDialog(null, "确认还书？");
				if(option == JOptionPane.YES_OPTION);{
					int []index = tableBookBorrow.getSelectedRows();
					for(int i=0;i<index.length;i++){
						String bookID = String.valueOf(tableBookBorrow.getValueAt(index[i], 0));
						Date backTime = Date.valueOf(String.valueOf(tableBookBorrow.getValueAt(index[i], 3)));
						if(backTime.getTime()<System.currentTimeMillis()){
							String overDay = String.valueOf(Math.floor((System.currentTimeMillis()-backTime.getTime())*1.0/1000/3600/24));
							JOptionPane.showMessageDialog(rightPanel, "已逾期"+overDay+"天");
						}
						operationBorrow.delBorrowInfo(bookID);
						model1.removeRow(index[i]);
					}
					JOptionPane.showMessageDialog(rightPanel, "还书成功！");
				}
			}
		});
	}
}
