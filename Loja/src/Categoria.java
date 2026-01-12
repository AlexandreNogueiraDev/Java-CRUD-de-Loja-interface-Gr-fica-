import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import Model.CategoriaM;
import Model.ProdutoM;
import DAO.CategoriaDao;
import DAO.ProdutoDao;

	public class Categoria extends JDialog{
		private JLabel lbNome;
		private JTextField txNome, txId;
		private JComboBox comboEstado;
	    private JButton btSalvar, btAlterar, btExcluir;
		private JList lista;
		private JScrollPane scrollPane;
		
		private DefaultListModel<String> dados = new DefaultListModel<>();
			
			public Categoria() throws SQLException {
				CategoriaDao categoriaDao = new CategoriaDao();
				
				this.setTitle("Cadastro de Categoria");
			    this.setModal(false);
			    this.setSize(600,600);  
			    this.setResizable(true);
			    this.setLocationRelativeTo(null);
			    this.setLayout(null);
			    
			    lbNome = new JLabel();
				lbNome.setText("Nome:");
				lbNome.setBounds(10, 8, 100, 25);
				add(lbNome);
				
				txNome = new JTextField();
				txNome.setBounds(70, 8, 150, 25);
				add(txNome);
				
				txId = new JTextField();
		        txId.setBounds(300, 8, 150, 25);
		        txId.setVisible(false);
		        add(txId);
				
				btSalvar = new JButton();
				btSalvar.setText("Salvar");
				btSalvar.setBounds(10,130,80,25);
				add(btSalvar);
				
				btAlterar = new JButton("Alterar");
			    btAlterar.setBounds(100, 130, 80, 25);
			    add(btAlterar);
			        
			    btExcluir = new JButton("Excluir");
			    btExcluir.setBounds(190, 130, 80, 25);
			    add(btExcluir);
				
			    preencherLista();
				lista = new JList<>(dados);
			    scrollPane = new JScrollPane(lista);
			    scrollPane.setBounds(10, 180, 470, 100);
			    add(scrollPane);
			    
			    lista.addMouseListener(new MouseAdapter()
		        {

		            public void mousePressed(MouseEvent e)
		            {
		              
		                	try {
								buscarLista();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}                	
		                
		            }
		        });

		        btSalvar.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                try {
		                    String nome = txNome.getText();
		                   

		                    CategoriaM categoria = new CategoriaM();
		                    categoria.setNomeCategoria(nome);
		                    

		                    categoriaDao.adicionar(categoria);

		                    // Atualizar a lista após salvar
		                    dados.clear();
		                    preencherLista();
		                } catch (SQLException e1) {
		                    System.out.println("Erro: " + e1.getMessage());
		                    e1.printStackTrace();
		                }
		            }
		        });
		        
		        btAlterar.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                try {
		                    String nome = txNome.getText();
		                    int id = Integer.parseInt(txId.getText());

		                    CategoriaM categoria = new CategoriaM();
		                    categoria.setNomeCategoria(nome);
		                    categoria.setCodCategoria(id);

		                    categoriaDao.alterar(categoria);

		                    // Atualizar a lista após salvar
		                    dados.clear();
		                    preencherLista();
		                } catch (SQLException e1) {
		                    System.out.println("Erro: " + e1.getMessage());
		                    e1.printStackTrace();
		                }
		            }
		        });
		        
		        btExcluir.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                try {
		                    int id = Integer.parseInt(txId.getText());

		                    CategoriaM categoria = new CategoriaM();
		                    categoria.setCodCategoria(id);
		                    

		                    categoriaDao.excluir(categoria);

		                    // Atualizar a lista após salvar
		                    dados.clear();
		                    preencherLista();
		                } catch (SQLException e1) {
		                    System.out.println("Erro: " + e1.getMessage());
		                    e1.printStackTrace();
		                }
		            }
		        });
		    }
			
		
		private void preencherLista() throws SQLException {
			CategoriaDao categoriaDao = new CategoriaDao();
			List<CategoriaM> categoria = categoriaDao.getLista(); // Supondo que exista um método buscarTodos()
			for (CategoriaM pro : categoria) {
			    dados.addElement("ID: " + pro.getCodCategoria() + ", Categoria: " + pro.getNomeCategoria());
			        }
			    }
		
		private void buscarLista() throws SQLException {
			CategoriaDao categoriaDao = new CategoriaDao();
	    	
			List<CategoriaM> categorias = categoriaDao.getLista(); // Supondo que exista um método buscarTodos()
	        CategoriaM categoria = categorias.get(lista.getSelectedIndex());
	        
	        txNome.setText(categoria.getNomeCategoria());
	        txId.setText(Integer.toString(categoria.getCodCategoria()));
	    }
	    
	}