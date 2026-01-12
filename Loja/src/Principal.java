import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.*;

public class Principal extends JFrame{
 
    public Principal(){   
              
    	this.setSize(800,600);
    	this.setTitle("Loja Boti-Caro");
    	this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    	this.setLocationRelativeTo(null);
    	this.setExtendedState(MAXIMIZED_BOTH);
    	
    	
        JMenu arq = new JMenu("Arquivo");
        JMenu cad = new JMenu("Cadastrar");        
        JMenu aju = new JMenu("Ajuda");
        JMenu men = new JMenu("Menu");
               
        JMenuItem sair = new JMenuItem("Sair"); 
        JMenuItem pro = new JMenuItem("Produtos");
        JMenuItem cate = new JMenuItem("Categoria");
        JMenuItem sob = new JMenuItem("Sobre");
        JMenuItem cli = new JMenuItem("Cliente");
                      
        arq.add(sair);
        cad.add(pro);
        cad.add(cate);
        aju.add(sob);
        men.add(cli);
       
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        bar.add(arq);
        bar.add(cad); 
        bar.add(aju);
        bar.add(men);
        

             
       sair.addActionListener(
         new ActionListener(){
             public void actionPerformed(ActionEvent event){
               System.exit(0);
             }
         }
       );
       
       pro.addActionListener(
         new ActionListener(){
             public void actionPerformed(ActionEvent event){
            	 Produtos produto;
				try {
					produto = new Produtos();
					 produto.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
            
             }
         }
       );
       
       cate.addActionListener(
    	         new ActionListener(){
    	             public void actionPerformed(ActionEvent event){	             
    	             try {
    	            	 Categoria categoria = new Categoria();
    	            	 categoria.setVisible(true);
    					} catch (SQLException e) {
    						e.printStackTrace();
    					}
    	             }
    	         }
    	       );
       
      this.setVisible(true);
    } 
}