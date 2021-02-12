package Thiago_Rocha.geradorUsuario;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;

import me.legrange.mikrotik.ApiConnection;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.JPanel;

public class Main{

	private JFrame frmNovoUsuarioMikrotik;
	private JTextField nomeTxt;
	private JTextField senhaTxt;
	private JTextField horaTxt;
	private JTextField minutoTxt;

	private static String documento = "frontint.txt";
	private static String senha = "isgi@infoservi";	
	private static String login = "admin";
	private static String ip = "192.168.1.224";
	private static String profile = "hospedes";
	private static String server = "wifi hospedes";
	private static String[] profiles;
	private static String[] servers;
	

	public static String[] getProfiles() {
		return profiles;
	}
	public static void setProfiles(String[] profiles) {
		Main.profiles = profiles;
	}
	public static String[] getServers() {
		return servers;
	}
	public static void setServers(String[] servers) {
		Main.servers = servers;
	}
	public static String getDocumento() {
		return documento;
	}
	public static String getServer() {
		return server;
	}
	public static void setServer(String server) {
		Main.server = server;
	}
	public static void setDocumento(String documento) {
		Main.documento = documento;
	}
	public static String getSenha() {
		return senha;
	}
	public static void setSenha(String senha) {
		Main.senha = senha;
	}
	public static String getLogin() {
		return login;
	}
	public static void setLogin(String login) {
		Main.login = login;
	}
	public static String getIp() {
		return ip;
	}
	public static void setIp(String ip) {
		Main.ip = ip;
	}
	public static String getProfile() {
		return profile;
	}
	public static void setProfile(String profile) {
		Main.profile = profile;
	}



	/**
	 * Launch the application.
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmNovoUsuarioMikrotik.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Main() throws IOException {
		lerConfig();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmNovoUsuarioMikrotik = new JFrame();
		frmNovoUsuarioMikrotik.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNovoUsuarioMikrotik.setResizable(false);
		frmNovoUsuarioMikrotik.setTitle("Usuario MikroTik");
		frmNovoUsuarioMikrotik.setIconImage(Toolkit.getDefaultToolkit().getImage("icone.png"));
		frmNovoUsuarioMikrotik.getContentPane().setBackground(new Color(245, 255, 250));
		frmNovoUsuarioMikrotik.setBounds(100, 100, 422, 298);
		frmNovoUsuarioMikrotik.getContentPane().setLayout(null);

		
		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(22, 62, 148, 29);
		frmNovoUsuarioMikrotik.getContentPane().add(lblNewLabel);

		final JComboBox profilesCombo = new JComboBox(profiles);
		profilesCombo.setToolTipText("Informe o perfil do usuario");
		profilesCombo.setBounds(22, 147, 186, 22);
		frmNovoUsuarioMikrotik.getContentPane().add(profilesCombo);

		final JComboBox serversCombo = new JComboBox(servers);
		serversCombo.setToolTipText("Informe o servidor da rede de internet");
		serversCombo.setBounds(233, 147, 160, 22);
		frmNovoUsuarioMikrotik.getContentPane().add(serversCombo);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSenha.setBounds(233, 62, 160, 29);
		frmNovoUsuarioMikrotik.getContentPane().add(lblSenha);

		
		JLabel lblProfile = new JLabel("Perfil de Usuário");
		lblProfile.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblProfile.setBounds(22, 120, 148, 29);
		frmNovoUsuarioMikrotik.getContentPane().add(lblProfile);

		JLabel lblServer = new JLabel("Servidor");
		lblServer.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblServer.setBounds(233, 120, 160, 29);
		frmNovoUsuarioMikrotik.getContentPane().add(lblServer);

		
	
		final JButton btnNewButton = new JButton("Criar");
		btnNewButton.setEnabled(false);
		
	
		
		final JRadioButton rdbtnCriar = new JRadioButton("Criar usuário");
		rdbtnCriar.setBackground(new Color(245, 255, 250));
		rdbtnCriar.setSelected(true);
		rdbtnCriar.setBounds(22, 21, 109, 23);
		frmNovoUsuarioMikrotik.getContentPane().add(rdbtnCriar);
		
		final JRadioButton rdbtnDeletarUsuario = new JRadioButton("Remover usuário");
		rdbtnDeletarUsuario.setBackground(new Color(245, 255, 250));
		rdbtnDeletarUsuario.setBounds(229, 21, 148, 23);
		frmNovoUsuarioMikrotik.getContentPane().add(rdbtnDeletarUsuario);

		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnDeletarUsuario);
		bg.add(rdbtnCriar);
		
		rdbtnCriar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(rdbtnDeletarUsuario.isSelected()) {
					senhaTxt.setEnabled(false);
					senhaTxt.setText("");
					horaTxt.setEnabled(false);
					minutoTxt.setEnabled(false);
					profilesCombo.setEnabled(false);
					serversCombo.setEnabled(false);
					btnNewButton.setText("Remover");
					btnNewButton.setEnabled(true);
				}
				else if(rdbtnCriar.isSelected()) {
					senhaTxt.setEnabled(true);
					horaTxt.setEnabled(true);
					minutoTxt.setEnabled(true);
					profilesCombo.setEnabled(true);
					serversCombo.setEnabled(true);
					btnNewButton.setText("Criar");
					btnNewButton.setEnabled(false);
				}
				
			}
		});
		rdbtnDeletarUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(rdbtnDeletarUsuario.isSelected()) {
					senhaTxt.setEnabled(false);
					horaTxt.setEnabled(false);
					minutoTxt.setEnabled(false);
					profilesCombo.setEnabled(false);
					serversCombo.setEnabled(false);
					btnNewButton.setText("Remover");
					btnNewButton.setEnabled(true);
				}
				else if(rdbtnCriar.isSelected()) {
					senhaTxt.setEnabled(true);
					horaTxt.setEnabled(true);
					minutoTxt.setEnabled(true);
					profilesCombo.setEnabled(true);
					serversCombo.setEnabled(true);
					btnNewButton.setText("Criar");
					btnNewButton.setEnabled(false);
				}
				
			}
		});
	
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(rdbtnCriar.isSelected()) {
					if (profilesCombo.getSelectedItem().toString() != "--SELECIONE--" ||nomeTxt.getText().isEmpty() != true && senhaTxt.getText().isEmpty() ) {
						try {
							
							//Iniciando com a conexão ao MikroTik.
							ApiConnection con = ApiConnection.connect(ip);
							
							//Efetuando o login no Mikrotik
							con.login(login,senha);	
							
							con.execute("/ip/hotspot/user/add password=\""+senhaTxt.getText()+"\" name=\""+nomeTxt.getText()+"\" profile=\""+profilesCombo.getSelectedItem().toString()+"\" server=\""+serversCombo.getSelectedItem().toString()+"\" limit-uptime=\""+horaTxt.getText()+":"+minutoTxt.getText()+":00 \"");
							//Fechando a conexão
							con.close();
							JOptionPane.showMessageDialog(null, "Gravado com sucesso!");
							
							nomeTxt.setText("");
							senhaTxt.setText("");
							horaTxt.setText("001");
							minutoTxt.setText("00");
							btnNewButton.setEnabled(false);
							
						}catch (Exception e) {
							// TODO: handle exception
							JOptionPane.showMessageDialog(null, "Falha na gravação do voucher! \n Verifique se o usuario já existe \n e tente novamente!");
							
						}	
					}
					else {
						btnNewButton.setEnabled(false);
						JOptionPane.showMessageDialog(null, "Preencha os campos devidamente!");
					}
					
				}else if(rdbtnDeletarUsuario.isSelected()) {
					try {
						
						//Iniciando com a conexão ao MikroTik.
						ApiConnection con = ApiConnection.connect(ip);
						
						//Efetuando o login no Mikrotik
						con.login(login,senha);	
						
						con.execute("/ip/hotspot/user/remove .id="+nomeTxt.getText());
						//Fechando a conexão
						con.close();
						JOptionPane.showMessageDialog(null, "Removido com sucesso!");
						
						nomeTxt.setText("");
						senhaTxt.setText("");
						horaTxt.setText("001");
						minutoTxt.setText("00");
						btnNewButton.setEnabled(false);
						
					}catch (Exception e) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "Falha na remoção! \n Verifique o usuario");
						
					}
				}
				
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(192, 195, 106, 45);
		frmNovoUsuarioMikrotik.getContentPane().add(btnNewButton);

		nomeTxt = new JTextField();
		nomeTxt.setBounds(22, 89, 186, 20);
		frmNovoUsuarioMikrotik.getContentPane().add(nomeTxt);
		nomeTxt.setColumns(10);

		senhaTxt = new JTextField();
		senhaTxt.setColumns(10);
		senhaTxt.setBounds(233, 89, 160, 20);
		frmNovoUsuarioMikrotik.getContentPane().add(senhaTxt);
		
		nomeTxt.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(nomeTxt.getText().isEmpty()!= true && senhaTxt.getText().isEmpty()!= true && rdbtnCriar.isSelected()) {
					btnNewButton.setEnabled(true);
				}
				else if(rdbtnDeletarUsuario.isSelected() == true) {
					btnNewButton.setEnabled(true);
				}
				else{
					btnNewButton.setEnabled(false);
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(nomeTxt.getText().isEmpty() != true && senhaTxt.getText().isEmpty() != true && rdbtnCriar.isSelected()) {
					btnNewButton.setEnabled(true);
				}
				else if(rdbtnDeletarUsuario.isSelected() == true) {
					btnNewButton.setEnabled(true);
				}
				else{
					btnNewButton.setEnabled(false);
				}
				
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(nomeTxt.getText().isEmpty()!= true && senhaTxt.getText().isEmpty() != true && rdbtnCriar.isSelected()) {
					btnNewButton.setEnabled(true);
				}
				else if(rdbtnDeletarUsuario.isSelected() == true) {
					btnNewButton.setEnabled(true);
				}
				else{
					btnNewButton.setEnabled(false);
				}
				
			}
		});
		
		
		senhaTxt.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(nomeTxt.getText().isEmpty()!= true && senhaTxt.getText().isEmpty()!= true && rdbtnCriar.isSelected()) {
					btnNewButton.setEnabled(true);
				}
				else {
					btnNewButton.setEnabled(false);
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(nomeTxt.getText().isEmpty() != true && senhaTxt.getText().isEmpty() != true && rdbtnCriar.isSelected()) {
					btnNewButton.setEnabled(true);
				}
				else if(rdbtnDeletarUsuario.isSelected() == true) {
					btnNewButton.setEnabled(true);
				}
				else{
					btnNewButton.setEnabled(false);
				}
				
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(nomeTxt.getText().isEmpty()!= true && senhaTxt.getText().isEmpty() != true && rdbtnCriar.isSelected()) {
					btnNewButton.setEnabled(true);
				}
				else if(rdbtnDeletarUsuario.isSelected() == true) {
					btnNewButton.setEnabled(true);
				}
				else{
					btnNewButton.setEnabled(false);
				}
				
				
			}
		});
		
		
		JButton btnNewButton_1 = new JButton("Sair");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(327, 217, 66, 23);
		frmNovoUsuarioMikrotik.getContentPane().add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("Validade");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(22, 179, 88, 29);
		frmNovoUsuarioMikrotik.getContentPane().add(lblNewLabel_1);

		horaTxt = new JTextField();
		horaTxt.setText("001");
		horaTxt.setBounds(22, 208, 40, 20);
		frmNovoUsuarioMikrotik.getContentPane().add(horaTxt);
		horaTxt.setColumns(10);
		
		horaTxt.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if(horaTxt.getText().length() <=0 || Integer.parseInt(horaTxt.getText()) == 0) {
						return;		
					}
					else if(horaTxt.getText().length() > 3) {
						horaTxt.setText("001");						
					}					
				} catch (Exception e) {
					// TODO: handle exception
					horaTxt.setText("001");	
					return;
				}
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		horaTxt.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if(horaTxt.getText().length() <=0) {
						return;		
					}
					else if(horaTxt.getText().length() > 3) {
						horaTxt.setText("001");						
					}					
				} catch (Exception e) {
					// TODO: handle exception
					horaTxt.setText("001");	
					return;
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if(horaTxt.getText().length() <=0 || Integer.parseInt(horaTxt.getText()) == 0) {
						return;		
					}
					else if(horaTxt.getText().length() > 3) {
						horaTxt.setText("001");						
					}					
				} catch (Exception e) {
					// TODO: handle exception
					horaTxt.setText("001");	
					return;
				}

			}
		});

		
		minutoTxt = new JTextField();
		minutoTxt.setColumns(3);
		minutoTxt.setBounds(74, 208, 40, 20);
		frmNovoUsuarioMikrotik.getContentPane().add(minutoTxt);

		minutoTxt.setText("00");

		minutoTxt.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if(minutoTxt.getText().length() <=0 || Integer.parseInt(minutoTxt.getText()) == 0) {
						return;		
					}
					else if(minutoTxt.getText().length() > 3) {
						minutoTxt.setText("00");						
					}					
				} catch (Exception e) {
					// TODO: handle exception
					minutoTxt.setText("00");	
					return;
				}
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		

		minutoTxt.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if(minutoTxt.getText().length() <=0) {
						return;		
					}
					else if(minutoTxt.getText().length() > 2 || Integer.parseInt(minutoTxt.getText()) >= 60) {
						minutoTxt.setText("59");						
					}					
				} catch (Exception e) {
					// TODO: handle exception
					return;
				}
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if(minutoTxt.getText().length() <=0) {
						return;		
					}
					else if(minutoTxt.getText().length() > 2 || Integer.parseInt(minutoTxt.getText()) >= 60) {
						minutoTxt.setText("59");						
					}					
				} 
				catch (Exception e) {
					// TODO: handle exception
					return;
				}

			}
		});

		JLabel lblNewLabel_2 = new JLabel(":");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(64, 195, 7, 40);
		frmNovoUsuarioMikrotik.getContentPane().add(lblNewLabel_2);
		
		JLabel lblGeradorDeSenhas = new JLabel("Gerador de Senhas - Versão WR_1.01 r.2");
		lblGeradorDeSenhas.setForeground(Color.RED);
		lblGeradorDeSenhas.setFont(new Font("Segoe UI", Font.BOLD, 8));
		lblGeradorDeSenhas.setBounds(248, 247, 160, 10);
		frmNovoUsuarioMikrotik.getContentPane().add(lblGeradorDeSenhas);
	}

	public static void lerConfig() throws IOException {

		//Criando um objeto do tipo Properties que auxilia na leitura das configurações
		Properties prop = new Properties();

		//Lendo o arquivo de configurações
		prop.load(new FileInputStream(new File("config.properties")));


		System.out.println(prop.getProperty("documento"));
		System.out.println(prop.getProperty("login"));
		System.out.println(prop.getProperty("senha"));
		System.out.println(prop.getProperty("ip"));
		System.out.println(prop.getProperty("profiles"));
		System.out.println(prop.getProperty("servers"));


		//Setando os campos de configuração de acordo com o arquivo de configuração.
		setDocumento(prop.getProperty("documento"));
		setIp(prop.getProperty("ip"));
		setLogin(prop.getProperty("login"));
		setSenha(prop.getProperty("senha"));
		setProfile(prop.getProperty("profile"));
		setServer(prop.getProperty("server"));
		setProfiles(prop.getProperty("profiles").toString().split(","));
		setServers(prop.getProperty("servers").toString().split(","));


	}
}
