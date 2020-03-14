package edu.udc.psw.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import edu.udc.psw.formas.Linha;
import edu.udc.psw.formas.Ponto;
import edu.udc.psw.formas.Triangulo;

public class JanelaAplicação extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public JanelaAplicação(){
		super("Aplicação de desenho com o mouse");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 768);
		
		this.setLayout(new BorderLayout());
		
		JLabel status = new JLabel("Status da Aplicação");
		this.add(status, BorderLayout.SOUTH);
		
		PainelDesenho painel = new PainelDesenho(status);
		this.add(painel, BorderLayout.CENTER);
		
		
		//Criar Menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		mnArquivo.setMnemonic('A');
		menuBar.add(mnArquivo);
		
		JMenu mnFiguras = new JMenu("Figuras");
		mnFiguras.setMnemonic('F');
		menuBar.add(mnFiguras);
		
		JMenuItem mntmPonto = new JMenuItem("Ponto");
		mntmPonto.setMnemonic('P');
		mntmPonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				painel.formaAtual(new Ponto(0, 0));
			}
		});
		mnFiguras.add(mntmPonto);
		
		JMenuItem mntmLinha = new JMenuItem("Linha");
		mntmLinha.setMnemonic('L');
		mntmLinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painel.formaAtual(new Linha(new Ponto(0, 0), new Ponto(0,0)));
			}
		});
		mnFiguras.add(mntmLinha);
		
		JMenuItem mntmTriangulo = new JMenuItem("Triangulo");
		mntmTriangulo.setMnemonic('T');
		mntmTriangulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painel.formaAtual(new Triangulo(new Ponto(0, 0), new Ponto(0,0), new Ponto(0, 0)));
			}
		});
		mnFiguras.add(mntmTriangulo);
	}
}
