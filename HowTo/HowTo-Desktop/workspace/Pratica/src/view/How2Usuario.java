package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JButton;

import bd.core.MeuResultSet;
import bd.daos.*;
import bd.dbos.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;
import java.sql.Date;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

/**
 * A classe How2Usuario herda de JFreme, portanto eh uma janela.
 * Nela podemos fazer operacoes como responder perguntas e validar
 * respostas com diverasas formatacoes de texto.
 * @author Vin?cius Martins Cotrim
 * @author Guilherme Jos? S. A.
 * @since 2019
 */
public class How2Usuario extends JFrame {

	/**
	 * Armazena todas as respostas no banco
	 */
	private MeuResultSet respostas = null;
	private static int idAjudante = 0;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JPasswordField txtSenhaU;
	private JTextField textField;
	private JTextField txtTotalPerguntas;
	private JTextField txtTotalRespostas;
	private JTextField txtQtdPerguntas;
	private JTextField txtPorcentagem;
	private JTextField txtAjudantes;

	/**
	 * Launch the application.
	 * @param args String nao obrigatoria para iniciar o programa
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					How2Usuario frame = new How2Usuario();
					
					if(args.length > 0)
						idAjudante = Integer.parseInt(args[0]);
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public How2Usuario() {
		setTitle("HowTo - ?rea de Trabalho");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 545);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//==================Painel principal==================
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP); 
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		//===========Painel para responder perguntas==========
		JPanel plResponder = new JPanel();
		plResponder.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Responder Perguntas", null, plResponder, null);
		plResponder.setLayout(null);
		
		//Botao para inserir texto em negrito a reposta 
		JButton btnNegrito = new JButton("Negrito");
		btnNegrito.setEnabled(false);
		btnNegrito.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNegrito.setBounds(724, 110, 125, 37);
		plResponder.add(btnNegrito);
		
		//Botao para inserir texto em italico a resposta
		JButton btnItalico = new JButton("It\u00E1lico");
		btnItalico.setEnabled(false);
		btnItalico.setFont(new Font("Arial", Font.PLAIN, 12));
		btnItalico.setBounds(724, 156, 125, 37);
		plResponder.add(btnItalico);
		
		//Botao para recarregar perguntas na tabela
		JButton btnCarregar = new JButton("Carregar Perguntas");
		btnCarregar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCarregar.setBounds(10, 421, 236, 37);
		plResponder.add(btnCarregar);
		
		//Botao para enviar a resposta apos escrever uma nova
		JButton btnEnviarResposta = new JButton("Env Resposta");
		btnEnviarResposta.setEnabled(false);
		btnEnviarResposta.setForeground(Color.BLACK);
		btnEnviarResposta.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEnviarResposta.setBounds(724, 422, 125, 37);
		plResponder.add(btnEnviarResposta);
		
		//Label para exibir o id da pergunta selecionada
		JLabel lbIdPergunta = new JLabel("|");
		lbIdPergunta.setBackground(Color.LIGHT_GRAY);
		lbIdPergunta.setEnabled(false);
		lbIdPergunta.setForeground(Color.BLACK);
		lbIdPergunta.setFont(new Font("Arial", Font.PLAIN, 12));
		lbIdPergunta.setBounds(10, 21, 37, 14);
		plResponder.add(lbIdPergunta);
		
		//Label para guia visual
		JLabel lbFerramentas = new JLabel("Ferramentas");
		lbFerramentas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbFerramentas.setBounds(743, 51, 93, 14);
		plResponder.add(lbFerramentas);
		
		//Label para guia visual
		JLabel lblFonte = new JLabel("Fonte");
		lblFonte.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFonte.setBounds(727, 85, 46, 14);
		plResponder.add(lblFonte);
		
		//Botao para exibir uma informacao extra a respeito das fontes
		JButton btnFonteInfo = new JButton("...");
		btnFonteInfo.setFont(new Font("Tunga", Font.PLAIN, 11));
		btnFonteInfo.setBounds(817, 81, 32, 23);
		plResponder.add(btnFonteInfo);
		
		//Spinner que controla o tamanho da fonte que esta n resposta
		JSpinner spFontSize = new JSpinner();
		spFontSize.setModel(new SpinnerNumberModel(13, 1, 72, 1));
		spFontSize.setBounds(770, 82, 37, 22);
		plResponder.add(spFontSize);
		
		//Botao para editar a pergunta selecionada
		JButton btnEditar = new JButton("Editar pergunta");
		btnEditar.setEnabled(false);
		btnEditar.setForeground(Color.BLACK);
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEditar.setBounds(724, 377, 125, 37);
		plResponder.add(btnEditar);
		
		//Botao para excluir alguma pergunta selecionada
		JButton btnExcluir = new JButton("Exluir pergunta");
		btnExcluir.setEnabled(false);
		btnExcluir.setForeground(Color.BLACK);
		btnExcluir.setFont(new Font("Arial", Font.PLAIN, 12));
		btnExcluir.setBounds(724, 329, 125, 37);
		plResponder.add(btnExcluir);
		
		//ComboBox para a insercao de lista e items sobre a lista
		JComboBox cbLista = new JComboBox();
		cbLista.setEnabled(false);
		cbLista.setModel(new DefaultComboBoxModel(new String[] {"Listas", "Inserir padr\u00E3o", "Inserir enumerada", "Inserir item", "Como funciona?"}));
		cbLista.setBounds(724, 204, 125, 20);
		plResponder.add(cbLista);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(256, 52, 458, 406);
		plResponder.add(scrollPane_2);
		
		//Campo onde sera formulada e escrita uma nova resposta
		JTextArea txtResposta = new JTextArea();
		scrollPane_2.setViewportView(txtResposta);
		txtResposta.setFont(new Font("Arial", Font.PLAIN, 13));
		txtResposta.setText("Digite sua resposta aqui!");
		txtResposta.setWrapStyleWord(true);
		txtResposta.setLineWrap(true);
		
		//Campo para exibicao de uma pergunta selecionada a partir da tabela
		//de perguntas
		JTextArea txtPergunta = new JTextArea();
		txtPergunta.setEditable(false);
		txtPergunta.setWrapStyleWord(true);
		txtPergunta.setLineWrap(true);
		txtPergunta.setText("Escolha uma pergunta para responder!");
		txtPergunta.setFont(new Font("Arial", Font.PLAIN, 28));
		txtPergunta.setBackground(Color.LIGHT_GRAY);
		txtPergunta.setBounds(32, 11, 687, 37);
		plResponder.add(txtPergunta);

		//Botao para exibir a pergunta completa caso ela nao caiba dentro do txtResposta
		JButton btnCompleta = new JButton("...");
		btnCompleta.setEnabled(false);
		btnCompleta.setFont(new Font("Tunga", Font.PLAIN, 11));
		btnCompleta.setBounds(817, 18, 32, 23);
		plResponder.add(btnCompleta);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 236, 360);
		plResponder.add(scrollPane);
		
		//Tabela que recebera todas as perguntas e as exibira para o usuario
		JTable tbPergunta = new JTable();
		tbPergunta.setFillsViewportHeight(true);
		scrollPane.setViewportView(tbPergunta);
		tbPergunta.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		tbPergunta.addMouseListener(new MouseAdapter() {
            @Override
             public void mouseClicked(MouseEvent e) { //Ao clicar em uma pergunta na tabela
            	try {
            		int index = tbPergunta.getSelectedRow(); //Pergamos o indice da linha
            		txtPergunta.setText(tbPergunta.getValueAt(index, 0) + ""); //Exibimos a pergunta na parte superior
            		lbIdPergunta.setText(Perguntas.getPergunta(txtPergunta.getText()).getIdPergunta() + ""); //Recuperamos e exibimos o id da pergunta selecionada
            		
            		//Habilitamos todos os botoes para que se possa escrever e editar uma nova resposta
            		btnNegrito.setEnabled(true);
					btnItalico.setEnabled(true);
					cbLista.setEnabled(true);
					btnExcluir.setEnabled(true);
					btnEditar.setEnabled(true);
					btnEnviarResposta.setEnabled(true);
					btnCompleta.setEnabled(true);
            	}
            	catch(Exception err) 
            	{
            		JOptionPane.showMessageDialog(null,err.getMessage());
            	}
             }
        });
		
		cbLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //Ao selecionarmos um item na ComboBox de lista
				String opcao = cbLista.getSelectedItem().toString(); //Pegamos a opcao escolhida
				switch(opcao) //Inserimos o texto no campo de resposta conforme a opcao escolhida
				{
					case ("Inserir padr?o"):
						txtResposta.setText(txtResposta.getText() + "\n<ul>\n"
																  + "    \n"
																  + "</ul> ");
					break;
					
					case ("Inserir enumerada"):
						txtResposta.setText(txtResposta.getText() + "\n<ol>\n"
																  + "    \n"
																  + "</ol> ");
					break;
					
					case ("Inserir item"): //Quando inserimos um item de lista
							int onde = 0;
							String texto = txtResposta.getText();
							for(int i = 0; i < texto.length() - 5; i++) //Percorremos o texto da resposta e procuramos pela ultima lista
								if(texto.substring(i, i+5).equals("</ol>") || texto.substring(i, i+5).equals("</ul>"))
									onde = i;
							
							if(onde == 0)//Se nenhuma lista foi fechada entao nao ha listas
								JOptionPane.showMessageDialog(null,"Adicione uma lista primeiro!");
							else //Caso tenha listas
							{
								//Inserimos o novo item antes de fechar a ultima lista
								String antes = texto.substring(0, onde);
								String depois = texto.substring(onde, texto.length());
								txtResposta.setText(antes + "   <li>Novo item</li>\n" + depois);
							}
					break;
		
					case ("Como funciona?"):
						JOptionPane.showMessageDialog(null,"Voc? deve selecionar se quer adicionar ao seu texto \n"
														 + "uma lista padr?o ou enumerada, ou um item de lista...");
					break;
					}
				}
		});
		
		plResponder.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) { //Quanto a TapPage de responder perguntas de tornar visivel
				btnCarregar.doClick();	//Atualizamos as perguntas para ver se ha alguma nova
			}
		});
		
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //Para recarregar as perguntas
				try {
					DefaultTableModel model  = new DefaultTableModel(); //Criamos um modelo de tabela
					model.addColumn("Perguntas:");
					
					MeuResultSet result = Perguntas.getPerguntas(); //Adquirimos todas as perguntas
					
					while(result.next()) //Como as perguntas exibidas serao apenas as nao respondidas
					{
						if(result.getString(4).equals("N")) //Caso a pergunta atual nao tenha resposta
						{
							model.addRow(new Object[] {result.getString(3)}); //Ela podera ser exibida
						}
					}
						
					tbPergunta.setModel(model); //Colocamos a tabela criada em exibicao
				}
				catch(Exception err)
				{
					JOptionPane.showMessageDialog(null,err.getMessage());
				}	
			}
		});
		
		btnEnviarResposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //Ao enviar uma resposta
				try
				{
					int opcao = JOptionPane.showConfirmDialog( //Perguntamos ao usuario se tudo esta correto
						    null,
						    "Est? tudo pronto para enviar?\n"
						    + "Essa resposta n?o poder? ser editada!",
						    "Inclis?o",
						    JOptionPane.YES_NO_OPTION);
					
					if(opcao == 0) //Caso ele realmente queira enviar
					{
						//Adiquirimos valores para gerar uma nova resposta
						int idPerg = Perguntas.getPergunta(txtPergunta.getText()).getIdPergunta();
						Date dataResposta = new Date(System.currentTimeMillis());
						
						//Instanciamos uma resposta e a inserirmos no banco de dados
						Resposta resposta = new Resposta(1, idPerg, idAjudante, dataResposta, 'S', txtResposta.getText());
						Respostas.incluir(resposta);
						
						//Atualizamos a pergunta para que conste que ela foi realmente respondida
						Pergunta pergunta = new Pergunta(Perguntas.getPergunta(idPerg));
						pergunta.setRespondida('S');
						Perguntas.alterar(pergunta);
						
						//Recarregamos as perguntas
						btnCarregar.doClick();
						
						//Resetamos os botoes ao estado padrao/sem edicao
						btnNegrito.setEnabled(false);
						btnItalico.setEnabled(false);
						cbLista.setEnabled(false);
						btnExcluir.setEnabled(false);
						btnEditar.setEnabled(false);
						btnEnviarResposta.setEnabled(false);
						btnCompleta.setEnabled(false);
						txtPergunta.setText("Escolha uma pergunta para responder!");
						txtResposta.setText("Digite sua resposta aqui!");
					}
					
				}
				catch(Exception err)
				{
					JOptionPane.showMessageDialog(null,err.getMessage());
				}
			}
		});
		
		//Avisos adicionais ao usuario
		btnFonteInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 JOptionPane.showMessageDialog(null,"O tamanho da fonte n?o ser? alterado\n"
				 									+ "na exibi??o oficial da resposta!");//
			}
		});
		
		//Mudanca do tamanho da fonte para uma melhor experiencia do usuario
		spFontSize.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				 Font nova = new Font("Arial", Font.PLAIN, (int)spFontSize.getValue());
				 txtResposta.setFont(nova);
			}
		});
		
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Ao editarmos uma pergunta
				try {
					if(txtPergunta.isEditable()) //Caso a pergunta ja estiver sendo editada
					{	
						String descricao = txtPergunta.getText(); //Pegamos a nova descricao da pergunta
						Pergunta perg = Perguntas.getPergunta(Integer.parseInt(lbIdPergunta.getText())); //Pegamos a pergunta com a descricao antiga

						if (!descricao.equals(perg.getDescricao())) //Caso houver alguma mudanca na descricao
						{
							//Fazemos as alteracoes no banco de dados
							perg.setDescricao(descricao);
							Perguntas.alterar(perg);
							txtPergunta.setText(perg.getDescricao());
							btnCarregar.doClick();
						}
						
						//Resetamos os campos
						btnNegrito.setEnabled(true);
						btnItalico.setEnabled(true);
						btnExcluir.setEnabled(true);
						btnEnviarResposta.setEnabled(true);
						cbLista.setEnabled(true);
						txtPergunta.setEditable(false);
						txtResposta.setEditable(true);
						btnEditar.setText("Editar Pergunta");
					}
					else //Caso nao
					{
						//Preparamos a edicao
						btnNegrito.setEnabled(false);
						btnItalico.setEnabled(false);
						btnExcluir.setEnabled(false);
						btnEnviarResposta.setEnabled(false);
						txtResposta.setEditable(false);
						cbLista.setEnabled(false);
						txtPergunta.setEditable(true);
						txtPergunta.grabFocus();
						btnEditar.setText("Finalizar edi??o");
					}
				}
				catch(Exception err)
				{
					JOptionPane.showMessageDialog(null,err.getMessage());
				}
			}
		});
		
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Ao excluir uma pergunta
				try {
					
					int opcao = JOptionPane.showConfirmDialog( //Vemos se o usuario realmente deseja fazer esta acao
						    null,
						    "Voc? realmente quer excluir essa pergunta?",
						    "Exclus?o",
						    JOptionPane.YES_NO_OPTION);
					
					if(opcao == 0) //Se sim, excluimos e resetamos os campos
					{
						Perguntas.excluir(Integer.parseInt(lbIdPergunta.getText()));
						btnCarregar.doClick();
						
						txtPergunta.setText("Escolha uma pergunta para responder!");
						txtResposta.setText("Digite sua resposta aqui!");
						
						btnNegrito.setEnabled(false);
						btnItalico.setEnabled(false);
						cbLista.setEnabled(false);
						btnEditar.setEnabled(false);
						btnEnviarResposta.setEnabled(false);
						btnExcluir.setEnabled(false);
						btnCompleta.setEnabled(false);
					}
					
				}
				catch(Exception err)
				{
					JOptionPane.showMessageDialog(null,err.getMessage());
				}
			}
		});
		
		btnItalico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Insercao de texto em italico
				txtResposta.setText(txtResposta.getText() + "<i>'Texto em it?lico'</i>");
			}
		});
		
		btnNegrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //Insercao de texto em italico
				txtResposta.setText(txtResposta.getText() + "<b>'Texto em negrito'</b>");
			}
		});
		
		btnCompleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Caso alguma pergunta seja muito grande, ela podera ser exibida em um MessegeDialog
				JOptionPane.showMessageDialog(null,txtPergunta.getText(), "Pergunta completa", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	
		//===========TapPage de validacoes====================
		JPanel plValidar = new JPanel(); 
		plValidar.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Valida\u00E7\u00E3o de Respostas", null, plValidar, null);
		plValidar.setLayout(null);
		
		//label para exibir o id das perguntas
		JLabel lbIdPerguntaV = new JLabel("|");
		lbIdPerguntaV.setBackground(Color.LIGHT_GRAY);
		lbIdPerguntaV.setEnabled(false);
		lbIdPerguntaV.setForeground(Color.BLACK);
		lbIdPerguntaV.setFont(new Font("Arial", Font.PLAIN, 12));
		lbIdPerguntaV.setBounds(8, 11, 37, 14);
		plValidar.add(lbIdPerguntaV);
		
		//Label para melhorar a expreriecia do usuario
		JLabel label_2 = new JLabel("Ferramentas");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(135, 348, 93, 14);
		plValidar.add(label_2);
	
		//Label para melhorar a expreriecia do usuario
		JLabel label_3 = new JLabel("Fonte");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_3.setBounds(396, 380, 46, 14);
		plValidar.add(label_3);
		
		//Spinner para mudar o tamanho da fonte
		JSpinner spFontSizeV = new JSpinner();
		spFontSizeV.setModel(new SpinnerNumberModel(13, 1, 72, 1));
		spFontSizeV.setBounds(436, 377, 53, 22);
		plValidar.add(spFontSizeV);
		
		//Informacoes sobre o tamanho da fonte
		JButton btnFonteInfoV = new JButton("!");
		btnFonteInfoV.setBounds(499, 378, 37, 21);
		plValidar.add(btnFonteInfoV);
		
		//Botao para retroceder uma resposta
		JButton btnAnterior = new JButton("\u25C4");
		btnAnterior.setEnabled(false);
		btnAnterior.setFont(new Font("Arial", Font.BOLD, 35));
		btnAnterior.setBounds(27, 183, 70, 70);
		plValidar.add(btnAnterior);
		
		//label para o botao
		JLabel lblAnterior = new JLabel("Anterior");
		lblAnterior.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAnterior.setBounds(38, 161, 62, 14);
		plValidar.add(lblAnterior);
		
		//label para o botao
		JLabel lblPrxima = new JLabel("Pr\u00F3xima");
		lblPrxima.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrxima.setBounds(761, 161, 62, 14);
		plValidar.add(lblPrxima);
		
		//Botao para avancar uma resposta
		JButton btnProxima = new JButton("\u25BA");
		btnProxima.setFont(new Font("Arial", Font.BOLD, 35));
		btnProxima.setBounds(761, 183, 70, 70);
		plValidar.add(btnProxima);
		
		//Botao para validar e enviar uma resposta ao banco de dados
		JButton btnValidarResposta = new JButton("Validar e enviar");
		btnValidarResposta.setEnabled(false);
		btnValidarResposta.setForeground(Color.BLACK);
		btnValidarResposta.setFont(new Font("Arial", Font.PLAIN, 12));
		btnValidarResposta.setBounds(550, 421, 182, 37);
		plValidar.add(btnValidarResposta);
		
		//Caso a pergunta tenha que ser editada
		JButton btnEditarResposta = new JButton("Editar Resposta");
		btnEditarResposta.setEnabled(false);
		btnEditarResposta.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEditarResposta.setBounds(551, 373, 181, 37);
		plValidar.add(btnEditarResposta);
		
		//ComboBox para inserir lista e itens de lista
		JComboBox cbListaV = new JComboBox();
		cbListaV.setModel(new DefaultComboBoxModel(new String[] {"Listas", "Inserir padr\u00E3o", "Inserir enumerada", "Inserir item", "Como funciona?"}));
		cbListaV.setEnabled(false);
		cbListaV.setBounds(261, 373, 125, 20);
		plValidar.add(cbListaV);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(120, 84, 617, 253);
		plValidar.add(scrollPane_1);
		
		//Campo onde serao exibidas e editadas as respostas
		JTextArea txtRespostaV = new JTextArea();
		scrollPane_1.setViewportView(txtRespostaV);
		txtRespostaV.setEditable(false);
		txtRespostaV.setFont(new Font("Arial", Font.PLAIN, 13));
		txtRespostaV.setText("A resposta vai estar aqui!");
		txtRespostaV.setWrapStyleWord(true);
		txtRespostaV.setLineWrap(true);
		
		//Campo para exibicao da pergunta a qual a resposta pertence
		JTextArea txtPerguntaV = new JTextArea();
		txtPerguntaV.setEditable(false);
		txtPerguntaV.setWrapStyleWord(true);
		txtPerguntaV.setLineWrap(true);
		txtPerguntaV.setFont(new Font("Arial", Font.PLAIN, 28));
		txtPerguntaV.setText("Navegue entre as respostas e as valide!");
		txtPerguntaV.setBackground(Color.LIGHT_GRAY);
		txtPerguntaV.setBounds(35, 0, 839, 85);
		plValidar.add(txtPerguntaV);
		
		//Botao de editar na secao de validacoes
		JButton btnEditarV = new JButton("Editar Pergunta");
		btnEditarV.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEditarV.setEnabled(false);
		btnEditarV.setBounds(264, 421, 122, 37);
		plValidar.add(btnEditarV);
		
		//Botao de excluir na secao de validacoes, que apaga tando a pergunta como sua resposta
		JButton btnExcluirV = new JButton("Deletar tudo");
		btnExcluirV.setForeground(Color.RED);
		btnExcluirV.setFont(new Font("Arial", Font.PLAIN, 12));
		btnExcluirV.setEnabled(false);
		btnExcluirV.setBounds(396, 421, 144, 37);
		plValidar.add(btnExcluirV);
		
		//Insercao de textos em italico
		JButton btnItalicoV = new JButton("It\u00E1lico");
		btnItalicoV.setFont(new Font("Arial", Font.PLAIN, 12));
		btnItalicoV.setEnabled(false);
		btnItalicoV.setBounds(120, 373, 122, 37);
		plValidar.add(btnItalicoV);
		
		//Insercao de textos em negrito
		JButton btnNegritoV = new JButton("Negrito");
		btnNegritoV.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNegritoV.setEnabled(false);
		btnNegritoV.setBounds(120, 421, 122, 37);
		plValidar.add(btnNegritoV);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Relat\u00F3rio", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Aqui voc\u00EA tem um pequeno resuminho de tudo!");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel.setBounds(108, 11, 636, 38);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(147, 273, 65, 25);
		panel.add(lblNewLabel_1);
		
		txtNome = new JTextField();
		txtNome.setEditable(false);
		txtNome.setBounds(222, 273, 397, 26);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(147, 310, 65, 25);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(222, 309, 397, 26);
		panel.add(txtEmail);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSenha.setBounds(147, 346, 65, 25);
		panel.add(lblSenha);
		
		JButton btnSenha = new JButton("Ver Senha");
		btnSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtSenhaU.getEchoChar() == '*') //Para visualizarmos a senha trocamos a sua representacao de '*' para letras ou vice-versa
					txtSenhaU.setEchoChar((char)0);
				else
					txtSenhaU.setEchoChar('*');
			}
		});
		btnSenha.setBounds(515, 347, 104, 26);
		panel.add(btnSenha);
		
		txtSenhaU = new JPasswordField();
		txtSenhaU.setEchoChar('*');
		txtSenhaU.setEditable(false);
		txtSenhaU.setBounds(222, 347, 283, 26);
		panel.add(txtSenhaU);
		
		JButton btnMudarEmail = new JButton("Mudar Email");
		btnMudarEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{	//Ao mudar o email
					if(btnMudarEmail.getText().equals("Mudar Email")) //Caso seja o primeiro click
					{
						btnMudarEmail.setText("Confirmar"); 
						txtEmail.setEditable(true);
					}
					else//Caso seja o segundo
					{
						String email = txtEmail.getText().trim(); //Pegamos o email
						if(!email.equals("") && email.contains((CharSequence)"@") && !email.contains((CharSequence)" ") && email.contains((CharSequence)".com")) //Verificamos sua validade
						{
							//Pegamos o ajudante e alteramos o seu email
							Ajudante ajud = Ajudantes.getAjudante(idAjudante);
							ajud.setEmail(email);
							Ajudantes.alterar(ajud);
							txtEmail.setEditable(false);
							btnMudarEmail.setText("Mudar Email");							
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Email inv?lido");
						}
					} 
				}
				catch (Exception err) 
				{
					JOptionPane.showMessageDialog(null,err.getMessage());
				}
			}
		});
		btnMudarEmail.setBounds(629, 309, 115, 26);
		panel.add(btnMudarEmail);
		
		JButton btnMudarSenha = new JButton("Mudar Senha");
		btnMudarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Ao mudar a senha
				try
				{
					if(btnMudarSenha.getText().equals("Mudar Senha")) //Caso seja o primeiro click
					{
						btnMudarSenha.setText("Confirmar");
						txtSenhaU.setEditable(true);
					}
					else
					{
						String senha = txtSenhaU.getText().trim(); //Pegamos a senha
						if(!senha.equals("") && !senha.contains((CharSequence)" ")) //Validamos a senha
						{
							//Pegamos o ajudante e o alteramos
							Ajudante ajud = Ajudantes.getAjudante(idAjudante);
							ajud.setSenha(senha);
							Ajudantes.alterar(ajud);
							txtSenhaU.setEditable(false);
							btnMudarSenha.setText("Mudar Senha");							
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Senha inv?lida");
						}
					} 
				}
				catch (Exception err) 
				{
					JOptionPane.showMessageDialog(null,err.getMessage());
				}
			}
		});
		btnMudarSenha.setBounds(629, 346, 114, 26);
		panel.add(btnMudarSenha);
		
		JButton btnMudarNome = new JButton("Mudar Nome");
		btnMudarNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try//Ao mudar o nome
				{
					if(btnMudarNome.getText().equals("Mudar Nome")) //Caso seja o primeiro click
					{
						btnMudarNome.setText("Confirmar");
						txtNome.setEditable(true);
					}
					else
					{
						String nome = txtNome.getText().trim(); //Pegamos o nome
						if(!nome.equals("")) //Validamos o nome
						{
							//Pegamos o ajudante e o alteramos
							Ajudante ajud = Ajudantes.getAjudante(idAjudante);
							ajud.setNome(nome);
							Ajudantes.alterar(ajud);
							txtNome.setEditable(false);
							btnMudarNome.setText("Mudar Nome");
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Nome inv?lido");
						}
					} 
				}
				catch (Exception err) 
				{
					JOptionPane.showMessageDialog(null,err.getMessage());
				}
			}
		});
		btnMudarNome.setBounds(630, 273, 114, 26);
		panel.add(btnMudarNome);
		
		textField = new JTextField();
		textField.setForeground(Color.BLACK);
		textField.setBackground(Color.BLACK);
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setBounds(108, 208, 636, 2);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNsTemos = new JLabel("N\u00F3s temos:");
		lblNsTemos.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNsTemos.setBounds(108, 60, 146, 38);
		panel.add(lblNsTemos);
		
		JLabel lblPerguntasFeitas = new JLabel("Perguntas feitas");
		lblPerguntasFeitas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPerguntasFeitas.setBounds(237, 113, 163, 25);
		panel.add(lblPerguntasFeitas);
		
		JLabel lblPerguntasRespondidas = new JLabel("Perguntas respondidas");
		lblPerguntasRespondidas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPerguntasRespondidas.setBounds(237, 148, 219, 25);
		panel.add(lblPerguntasRespondidas);
		
		JLabel lblVoc = new JLabel("Voc\u00EA:");
		lblVoc.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblVoc.setBounds(108, 221, 146, 38);
		panel.add(lblVoc);
		
		txtTotalPerguntas = new JTextField();
		txtTotalPerguntas.setEditable(false);
		txtTotalPerguntas.setColumns(10);
		txtTotalPerguntas.setBounds(148, 109, 79, 26);
		panel.add(txtTotalPerguntas);
		
		txtTotalRespostas = new JTextField();
		txtTotalRespostas.setEditable(false);
		txtTotalRespostas.setColumns(10);
		txtTotalRespostas.setBounds(148, 147, 79, 26);
		panel.add(txtTotalRespostas);
		
		txtQtdPerguntas = new JTextField();
		txtQtdPerguntas.setEditable(false);
		txtQtdPerguntas.setColumns(10);
		txtQtdPerguntas.setBounds(330, 392, 43, 26);
		panel.add(txtQtdPerguntas);
		
		txtPorcentagem = new JTextField();
		txtPorcentagem.setEditable(false);
		txtPorcentagem.setColumns(10);
		txtPorcentagem.setBounds(285, 424, 65, 26);
		panel.add(txtPorcentagem);
		
		JLabel lblIssoEquivaleDe = new JLabel("Isso equivale:               de TODAS as respostas!");
		lblIssoEquivaleDe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIssoEquivaleDe.setBounds(147, 425, 567, 25);
		panel.add(lblIssoEquivaleDe);
		
		JLabel lblVocJaRespondeu = new JLabel("Voc\u00EA ja respondeu:           perguntas");
		lblVocJaRespondeu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVocJaRespondeu.setBounds(147, 389, 368, 25);
		panel.add(lblVocJaRespondeu);
		
		JLabel lblAjudantes = new JLabel("Ajudantes");
		lblAjudantes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAjudantes.setBounds(530, 113, 163, 25);
		panel.add(lblAjudantes);
		
		txtAjudantes = new JTextField();
		txtAjudantes.setEditable(false);
		txtAjudantes.setColumns(10);
		txtAjudantes.setBounds(441, 109, 79, 26);
		panel.add(txtAjudantes);
        setPreferredSize(new Dimension(500, 200));
        
        addComponentListener(new ComponentAdapter() {
        	@Override
        	public void componentShown(ComponentEvent arg0) {
        		btnCarregar.doClick();
        	}
        });
        
		btnEditarV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Mesma funcionalidade do btnEditar
				try {
					if(txtPerguntaV.isEditable())
					{	
						String descricao = txtPerguntaV.getText();
						Pergunta perg = Perguntas.getPergunta(Integer.parseInt(lbIdPerguntaV.getText()));

						if (!descricao.equals(perg.getDescricao())) 
						{
							perg.setDescricao(descricao);
							Perguntas.alterar(perg);
							txtPerguntaV.setText(perg.getDescricao());
						}
						
						txtPerguntaV.setEditable(false);
						btnEditarV.setText("Editar pergunta");
					}
					else
					{
						btnEditarV.setEnabled(true);
						txtPerguntaV.setEditable(true);
						btnEditarV.setText("Finalizar edi??o");
						txtPerguntaV.grabFocus();
					}
				}
				catch(Exception err)
				{
					JOptionPane.showMessageDialog(null,err.getMessage());
				}
			}
		});
        
		btnExcluirV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //Mesma funcionaliade do btnExcluir
				try {
					int opcao = JOptionPane.showConfirmDialog(
						    null,
						    "Voc? realmente quer excluir a pergunta e essa resposta?",
						    "Exclus?o",
						    JOptionPane.YES_NO_OPTION);
					
					if(opcao == 0)
					{
						Respostas.excluir(respostas.getInt(1));
						Perguntas.excluir(Integer.parseInt(lbIdPerguntaV.getText()));
						respostas.deleteRow();
						
						try {
							if(respostas.getRow() == 1)
								btnProxima.doClick();
							
							if(respostas.isLast())
								btnAnterior.doClick();					
						}
						catch(Exception err)
						{	
							JOptionPane.showMessageDialog(null,err.getMessage());
						}
					}
				}
				catch(Exception err)
				{
					JOptionPane.showMessageDialog(null,err.getMessage());
				}
			}
		});
		
		cbListaV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //Mesma funcionalidade da cbLista
				String opcao = cbListaV.getSelectedItem().toString();
				switch(opcao)
				{
					case ("Inserir padr?o"):
						txtRespostaV.setText(txtRespostaV.getText() + "\n<ul>\n"
																  + "    \n"
																  + "</ul> ");
					break;
					
					case ("Inserir enumerada"):
						txtRespostaV.setText(txtRespostaV.getText() + "\n<ol>\n"
																  + "    \n"
																  + "</ol> ");
					break;
					
					case ("Inserir item"):
						int onde = 0;
						String texto = txtRespostaV.getText();
						for(int i = 0; i < texto.length() - 5; i++) //Percorremos o texto da resposta e verificamos onde ela esta
						if(texto.substring(i, i+5).equals("</ol>") || texto.substring(i, i+5).equals("</ul>"))
							onde = i;
					
						if(onde == 0)//Se nenhuma lista foi fechada entao nao ha listas
							JOptionPane.showMessageDialog(null,"Adicione uma lista primeiro!");
						else //Caso tenha listas
						{
							//Inserimos o novo item antes de fechar a ultima lista
							String antes = texto.substring(0, onde);
							String depois = texto.substring(onde, texto.length());
							txtRespostaV.setText(antes + "   <li>Novo item</li>\n" + depois);
						}	
					break;
		
					case ("Como funciona?"):
						JOptionPane.showMessageDialog(null,"Voc? deve selecionar se quer adicionar ao seu texto \n"
														 + "uma lista padr?o ou enumerada, ou um item de lista...");
					break;
					}
				}
		});
		
		plValidar.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) { //Quando o painel for exibido sera feita a busca por todas as respostas
				try {	
					respostas = Respostas.getRespostasInvalidas();

					try
					{
						respostas.next();
						int aux = respostas.getInt(1);
						respostas.previous();
						btnProxima.setEnabled(true);
						btnAnterior.setEnabled(false);
					}
					catch(Exception er)
					{
						JOptionPane.showMessageDialog(null,"Todas as respostas est?o v?lidas!");
						btnProxima.setEnabled(false);
						btnValidarResposta.setEnabled(false);
						btnExcluirV.setEnabled(false);
						btnEditarV.setEnabled(false);
						btnEditarResposta.setEnabled(false);
						txtPerguntaV.setText("Parece que voc? j? pode descansar agora!");
						txtRespostaV.setText("De um tempinho!");
					}
				}
				catch(Exception err)
				{
					JOptionPane.showMessageDialog(null,err.getMessage());
				}
			}
		});
		
		spFontSizeV.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) { //Mesma funcionalidade do spFontSize
				 Font nova = new Font("Arial", Font.PLAIN, (int)spFontSizeV.getValue());
				 txtRespostaV.setFont(nova);
			}
		});
		
		btnFonteInfoV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFonteInfo.doClick();
			}
		});
		
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Para navegar para a pergunta anterior
				try {
					respostas.previous(); //Voltamos uma posicao
					
					//Preparamos os campos para edicao
					btnProxima.setEnabled(true);
					btnEditarResposta.setEnabled(true);
					btnEnviarResposta.setEnabled(true);
					btnEditarV.setEnabled(true);
					btnExcluirV.setEnabled(true);
					
					//Ao achar pegamos seu id e a adquirimos por completo
					int idPerg = respostas.getInt(6);
					Pergunta perg = Perguntas.getPergunta(idPerg);
					
					//A exibimos
					txtRespostaV.setText(respostas.getString(3));
					txtPerguntaV.setText(perg.getDescricao());
					lbIdPerguntaV.setText(perg.getIdPergunta() + "");
					
					//Caso esteja na primeira posicao o usuario nao podera voltar mais
					if(respostas.getRow() == 1)
						btnAnterior.setEnabled(false);
				}
				catch(Exception err)
				{
					JOptionPane.showMessageDialog(null,err.getMessage());
				}
			}
		});

		btnProxima.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) { //Mesma funcionalidade para ir para a anterior, a diferenca eh o sentido do movimento.
														 //Este avanca "para frente" nas posicoes das 'respostas'
				try {
					respostas.next();
						
					btnAnterior.setEnabled(true);
					btnEditarResposta.setEnabled(true);
					btnValidarResposta.setEnabled(true);
					
					
					btnEditarV.setEnabled(true);
					btnExcluirV.setEnabled(true);
						
					int idPerg = respostas.getInt(6);
					Pergunta perg = Perguntas.getPergunta(idPerg);
						
					txtRespostaV.setText(respostas.getString(3));
					txtPerguntaV.setText(perg.getDescricao());
					lbIdPerguntaV.setText(perg.getIdPergunta() + "");
						
					if(respostas.getRow() == 1)
						btnAnterior.setEnabled(false);
					
					if(respostas.isLast())
						btnProxima.setEnabled(false);
				}
				catch(Exception err)
				{
					JOptionPane.showMessageDialog(null,err.getMessage());
				}
			}
		});
		
		btnEditarResposta.addActionListener(new ActionListener() { //Quando o usuario for editar uma resposta invalida
			public void actionPerformed(ActionEvent arg0) {
				if(txtRespostaV.isEditable())
				{
					//Bloqueamos os campos
					btnNegritoV.setEnabled(false);
					btnItalicoV.setEnabled(false);
					cbListaV.setEnabled(false);
					txtRespostaV.setEditable(false);
					btnProxima.setEnabled(true);
					btnAnterior.setEnabled(true);
					
					try {
						if(respostas.getRow() == 1)
							btnAnterior.setEnabled(false);
						
						if(respostas.isLast())
							btnProxima.setEnabled(false);						
					}
					catch(Exception err)
					{	
						JOptionPane.showMessageDialog(null,err.getMessage());
					}
					
					btnEditarResposta.setText("Editar Resposta");
				}
				else
				{
					//Liberamos todos os campos de edicao
					btnNegritoV.setEnabled(true);
					btnItalicoV.setEnabled(true);
					cbListaV.setEnabled(true);
					txtRespostaV.setEditable(true);	
					btnProxima.setEnabled(false);
					btnAnterior.setEnabled(false);
					btnEditarResposta.setText("Cancelar edi??o");
				}
			}
		});
		
		btnValidarResposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Quando o usuario termina a edicao de uma resposta e a envia
				try
				{
					String respostaEditada = txtRespostaV.getText();//Adquirimos a nova descricao para a resposta
					int idPergunta = Integer.parseInt(lbIdPerguntaV.getText());
					Resposta resposta = new Resposta(Respostas.getRespostaPorIdPergunta(idPergunta)); //Buscamos a resposta pelo seu id
					resposta.setDescricao(respostaEditada);
					resposta.setValida('S'); //Validamos ela
					Respostas.alterar(resposta); //A alteramos no banco de dados
				}
				catch(Exception err)
				{
					JOptionPane.showMessageDialog(null,err.getMessage());
				}

				btnProxima.doClick(); //Passamos para a proxima "automaticamente"
				JOptionPane.showMessageDialog(null,"Resposta validada com sucesso!");
			}
		});
		
		btnNegritoV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  
				txtRespostaV.setText(txtRespostaV.getText() + "<b>'Texto em negrito'</b>");
			}
		});
		
		btnItalicoV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtRespostaV.setText(txtRespostaV.getText() + "<i>'Texto em it?lico'</i>");
			}
		});
		
		panel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) { //Ao entrar na aba retalorio
				try
				{
					MeuResultSet resultSet = Perguntas.getPerguntas(); //Pegamos todas as pergunta
					resultSet.last(); //Colocamos na ultima para saber seu tamanho
					txtTotalPerguntas.setText(resultSet.getRow() + ""); //Colocamos o valor no txt do numero total de perguntas
					resultSet = Ajudantes.getAjudantes(); //Pegamos todas os ajudantes
					resultSet.last(); //Colocamos na ultima
					txtAjudantes.setText((resultSet.getRow() - 1) + ""); //Setamos o tamanho
					resultSet = Respostas.getRespostas(); //Pegamos todas as respostas
					resultSet.last(); //Colocamos na ultima para saber seu tamanho
					txtTotalRespostas.setText(resultSet.getRow() + ""); //Colocamos o valor no txt do numero total de respostas
					
					int qtdResp = 0;
					for(int i = resultSet.getRow(); i > 0; i--) //Esse for percorre todas as resposta e conta quantas perguntas foram feitas pelo ajudante logado
					{
						if(resultSet.getInt(5) == idAjudante)
						{
							qtdResp++;
						}
						resultSet.previous();
					}
					
					txtQtdPerguntas.setText(qtdResp + ""); //Colocamos a quantidade
					
					double porcent = (100 * qtdResp) / Integer.parseInt(txtTotalRespostas.getText());//Calculamos a porcentagem na participacao no total de respostas
					txtPorcentagem.setText(new Double(porcent).toString() + "%"); //Colocamos o valor
					
					Ajudante ajud = Ajudantes.getAjudante(idAjudante); //Pegamos o ajudante atual e colocamos seus dados nos txts
					txtNome.setText(ajud.getNome()); 
					txtEmail.setText(ajud.getEmail());
					txtSenhaU.setText(ajud.getSenha());
				}
				catch(Exception err)
				{
					JOptionPane.showMessageDialog(null,err.getMessage());
				}
			}
		});
	}
}
