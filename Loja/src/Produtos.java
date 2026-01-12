import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DAO.CategoriaDao;
import DAO.ProdutoDao;
import Model.CategoriaM;
import Model.ProdutoM;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class Produtos extends JDialog {
    private JLabel lbNome, lbValor;
    private JTextField txNome, txValor, txQtd, txId;
    private JComboBox<CategoriaM> comboEstado;
    private JButton btSalvar, btAlterar, btExcluir;
    private JList<String> lista;
    private JScrollPane scrollPane;

    private DefaultListModel<String> dados = new DefaultListModel<>();

    public Produtos() throws SQLException {
        ProdutoDao produtoDao = new ProdutoDao();

        this.setTitle("Cadastro de Produto");
        this.setModal(false);
        this.setSize(500, 470);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        lbNome = new JLabel("Nome:");
        lbNome.setBounds(10, 8, 100, 25);
        add(lbNome);

        txNome = new JTextField();
        txNome.setBounds(70, 8, 150, 25);
        add(txNome);

        lbNome = new JLabel("Categoria:");
        lbNome.setBounds(8, 40, 100, 25);
        add(lbNome);
        
        CategoriaDao categoriaDao = new CategoriaDao();
        List<CategoriaM> categoria = categoriaDao.getLista();
        
        Vector<CategoriaM> model = new Vector<CategoriaM>();
        
        for(CategoriaM cat : categoria) {
        	model.add(cat);
        }
        comboEstado = new JComboBox<>();
        comboEstado.setModel(new DefaultComboBoxModel<CategoriaM>(model));
        
  
        comboEstado.setBounds(70, 40, 100, 25);
        add(comboEstado);

        lbValor = new JLabel("Valor:");
        lbValor.setBounds(10, 70, 100, 25);
        add(lbValor);

        txValor = new JTextField();
        txValor.setBounds(70, 70, 150, 25);
        add(txValor);

        lbNome = new JLabel("QTD:");
        lbNome.setBounds(10, 100, 100, 25);
        add(lbNome);

        txQtd = new JTextField();
        txQtd.setBounds(70, 100, 150, 25);
        add(txQtd);

        txId = new JTextField();
        txId.setBounds(300, 8, 150, 25);
        txId.setVisible(false);
        add(txId);

        // Preencher a JList com dados do banco
        preencherLista();

        lista = new JList<>(dados);
        scrollPane = new JScrollPane(lista);
        scrollPane.setBounds(10, 180, 470, 100);
        add(scrollPane);

        btSalvar = new JButton("Salvar");
        btSalvar.setBounds(10, 130, 80, 25);
        add(btSalvar);

        btAlterar = new JButton("Alterar");
        btAlterar.setBounds(100, 130, 80, 25);
        add(btAlterar);
        
        btExcluir = new JButton("Excluir");
        btExcluir.setBounds(190, 130, 80, 25);
        add(btExcluir);
        
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
                    double valor = Double.parseDouble(txValor.getText());
                    int qtd = Integer.parseInt(txQtd.getText());

                    ProdutoM produto = new ProdutoM();
                    CategoriaM categoria = (CategoriaM) comboEstado.getSelectedItem();
                    produto.setNomeProduto(nome);
                    produto.setValorProduto(valor);
                    produto.setQuantiProduto(qtd);
                    produto.setCodCategoria(categoria.getCodCategoria());
                    produtoDao.adicionar(produto);

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
                    double valor = Double.parseDouble(txValor.getText());
                    int qtd = Integer.parseInt(txQtd.getText());
                    int id = Integer.parseInt(txId.getText());

                    ProdutoM produto = new ProdutoM();
                    CategoriaM categoria = (CategoriaM) comboEstado.getSelectedItem();
                    produto.setNomeProduto(nome);
                    produto.setValorProduto(valor);
                    produto.setQuantiProduto(qtd);
                    produto.setCodProduto(id);
                    produto.setCodCategoria(categoria.getCodCategoria());
                    produtoDao.alterar(produto);

                    
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

                    ProdutoM produto = new ProdutoM();
                    produto.setCodProduto(id);
                    

                    produtoDao.excluir(produto);

                   
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
    	ProdutoDao produtoDao = new ProdutoDao();
    	CategoriaDao categoriaDao = new CategoriaDao();
        List<ProdutoM> produtos = produtoDao.getLista(); 
        for (ProdutoM pro : produtos) {
            dados.addElement("ID: " + pro.getCodProduto() + ", Produto: " + pro.getNomeProduto() + ", Categoria: " + categoriaDao.pegaCategoriaPorCod(pro.getCodCategoria()).get(0).getNomeCategoria() +
                             ", Valor: " + pro.getValorProduto() + ", Quantidade: " + pro.getQuantiProduto());
        }
    }
    
    private void buscarLista() throws SQLException {
    	ProdutoDao produtoDao = new ProdutoDao();
    	CategoriaDao categoriaDao = new CategoriaDao();

    	
    	List<ProdutoM> produtos = produtoDao.getLista(); 
        ProdutoM produto = produtos.get(lista.getSelectedIndex());
        
        txNome.setText(produto.getNomeProduto());
        txValor.setText(Double.toString(produto.getValorProduto()));
        txQtd.setText(Integer.toString(produto.getQuantiProduto()));
        txId.setText(Integer.toString(produto.getCodProduto()));
        
        comboEstado.getModel().setSelectedItem(categoriaDao.pegaCategoriaPorCod(produto.getCodCategoria()).get(0));
    }
    
    
}
