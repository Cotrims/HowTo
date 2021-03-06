package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import bd.daos.*;
import bd.dbos.*;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import java.awt.SystemColor;


/**
 * A classe How2 herda de JFreme, portanto eh uma janela.
 * Nela podemos fazer o login e abrir a janela How2Usuario
 * que eh uma especia de area de trabalho para formular respostas.
 * @author Vin?cius Martins Cotrim
 * @author Guilherme Jos? S. A.
 * @since 2019
 */
public class How2 {

	private JFrame frmHow;
	private JTextField txtEmail;
	private JPasswordField pwfSenha;

	/**
	 * Launch the application.
	 * @param args String nao obrigatoria para iniciar o programa
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					How2 window = new How2();
					window.frmHow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public How2() {
		initialize();
	}
	
	public static void openWebpage(String urlString) {
	    try {
	        Desktop.getDesktop().browse(new URL(urlString).toURI()); //Metodo para abrir um link na web
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHow = new JFrame();
		frmHow.setResizable(false);
		frmHow.setTitle("How2");
		frmHow.setBounds(100, 100, 590, 330);
		frmHow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHow.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmHow.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBorder(UIManager.getBorder("DesktopIcon.border"));
		panel_1.setBounds(310, 11, 254, 270);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		//btn para logar
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(85, 25, 99, 57);
		panel_1.add(lblLogin);
		lblLogin.setFont(new Font("Arial", Font.PLAIN, 35));
		
		//txt para digitar o email
		JLabel lblNewLabel = new JLabel("Email: ");
		lblNewLabel.setBounds(19, 113, 46, 14);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtEmail = new JTextField();
		txtEmail.setBounds(63, 111, 169, 20);
		panel_1.add(txtEmail);
		txtEmail.setColumns(10);
		
		//txt para digitar a senha
		JLabel lblSenha = new JLabel("Senha: ");
		lblSenha.setBounds(19, 148, 46, 14);
		panel_1.add(lblSenha);
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JButton btnNewButton = new JButton("Logar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(141, 183, 91, 23);
		panel_1.add(btnNewButton);
		
		//Caso o usuario nao tenha um cadastro, o click desta label o redicionara para o cadastro na web
		JLabel lblNCadastro = new JLabel("N\u00E3o tenho cadastro");
		lblNCadastro.setForeground(Color.BLUE);
		lblNCadastro.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNCadastro.setBounds(19, 187, 126, 14);
		panel_1.add(lblNCadastro);
		
		pwfSenha = new JPasswordField();
		pwfSenha.setBounds(63, 146, 169, 20);
		panel_1.add(pwfSenha);
		
		JLabel lblTextoDeBoas = new JLabel("Bem-vindos...");
		lblTextoDeBoas.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextoDeBoas.setFont(new Font("Arial", Font.BOLD, 18));
		lblTextoDeBoas.setBounds(10, 11, 137, 35);
		panel.add(lblTextoDeBoas);
		
		JLabel lblEntoVamosL = new JLabel("Ent\u00E3o vamos l\u00E1, bom samaritano,");
		lblEntoVamosL.setFont(new Font("Arial", Font.BOLD, 17));
		lblEntoVamosL.setBounds(10, 218, 269, 22);
		panel.add(lblEntoVamosL);
		
		JLabel lblHPerguntasPara = new JLabel("h\u00E1 perguntas para serem respondidas!");
		lblHPerguntasPara.setFont(new Font("Arial", Font.BOLD, 15));
		lblHPerguntasPara.setBounds(20, 239, 305, 30);
		panel.add(lblHPerguntasPara);
		
		JLabel lblAoAplicativoOficial = new JLabel("ao aplicativo oficial do HowTo!");
		lblAoAplicativoOficial.setFont(new Font("Arial", Font.PLAIN, 18));
		lblAoAplicativoOficial.setBounds(46, 45, 254, 22);
		panel.add(lblAoAplicativoOficial);
		
		JTextArea txtrAtravsDessePortal = new JTextArea();
		txtrAtravsDessePortal.setBackground(SystemColor.menu);
		txtrAtravsDessePortal.setFont(new Font("Malgun Gothic", Font.PLAIN, 17));
		txtrAtravsDessePortal.setWrapStyleWord(true);
		txtrAtravsDessePortal.setEditable(false);
		txtrAtravsDessePortal.setLineWrap(true);
		txtrAtravsDessePortal.setText("Atrav\u00E9s desse portal \u00E9 que voc\u00EA, gentil instrutor, pode acessar as perguntas e ser uma boa alma, ajundando algu\u00E9m em necessidade.");
		txtrAtravsDessePortal.setBounds(20, 92, 290, 106);
		panel.add(txtrAtravsDessePortal);
		
		//================ LOGIN ================
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = txtEmail.getText(); //Pegamos o emial
				String senha = pwfSenha.getText(); //Pegamos a senha
				
				try
				{
					if(!email.equals("") && !senha.equals("")) //So podera ser feito login caso o usuario tenha digitado email e senha
						if(Ajudantes.cadastrado(email)) //Vemos se possui alguma conta com o email fornecidos
						{
							Ajudante ajud = new Ajudante(Ajudantes.getAjudante(email));
							if(ajud.getSenha().equals(senha))
							{
								How2Usuario.main(new String[] {ajud.getIdAjudante() + ""});//Abrimos a nova janela
								frmHow.setVisible(false);
							}
							else
								JOptionPane.showMessageDialog(null,"Hey " + ajud.getNome() +", Voc? digitou sua senha incorretamente!"); //Caso tenha errado a senha
						}
						else
							JOptionPane.showMessageDialog(null,"Voc? n?o possui um cadastro!"); //Caso nao tenha um cadastro
					
				}
				catch(Exception err)
				{
					System.err.println();
				}
			}
		});
		
		//================ LINK PRA CADASTRO ================
		lblNCadastro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				openWebpage("http://localhost:3000/respostas");
			}
		});
	}
}
