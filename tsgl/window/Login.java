package window;

import impl.ILogin;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame{
	public static JTextField txtLoginName = null;
	public static JPasswordField txtLoginPassword = null;
	public static JButton btnLogin = null;
	public Login(){
		
		GridBagLayout gridBag = new GridBagLayout();
		final JPanel panel = new JPanel(gridBag);
		JLabel labLogin = new JLabel("登陆");
		labLogin.setFont(new Font("华文新魏", Font.PLAIN, 38));
		
		JLabel labLoginName = new JLabel("用户名：");
		JLabel labLoginPassword = new JLabel("密码：");
		
		gridBag.setConstraints(labLogin, new GridBagConstraints(0, 0, 3, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		gridBag.setConstraints(labLoginName, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		gridBag.setConstraints(labLoginPassword, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		txtLoginName = new JTextField(10);
		txtLoginPassword = new JPasswordField(10);
		gridBag.setConstraints(txtLoginName, new GridBagConstraints(1, 1, 2, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		gridBag.setConstraints(txtLoginPassword, new GridBagConstraints(1, 2, 2, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		btnLogin = new JButton("登陆");
		gridBag.setConstraints(btnLogin, new GridBagConstraints(1, 3, 3, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		panel.add(labLogin);
		panel.add(labLoginName);
		panel.add(labLoginPassword);
		panel.add(txtLoginName);
		panel.add(txtLoginPassword);
		panel.add(btnLogin);
		this.add(panel);
//		this.setLayout(gridBag);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(txtLoginName.getText()!=null&&txtLoginPassword.getPassword()!=null){
					String password = "";
					for(int i = 0;i<txtLoginPassword.getPassword().length;i++)
						password += ""+txtLoginPassword.getPassword()[i];
					if(new ILogin().isLogin(txtLoginName.getText(), password)){
						if(txtLoginName.getText().equals("admin"))
							new adminUser();
						else
							new normalUser();
					}
					else
						JOptionPane.showMessageDialog(panel, new String("用户名与密码不匹配"));
				}
				else
					JOptionPane.showMessageDialog(panel, "请完善信息");
			}
		});
	}
}
