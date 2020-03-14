package edu.udc.psw.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.udc.psw.formas.FormaGeometrica;
import edu.udc.psw.formas.Linha;
import edu.udc.psw.formas.Ponto;
import edu.udc.psw.formas.Triangulo;

public class PainelDesenho extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private JLabel status;
	
	private FormaGeometrica formaAtual;
	private int estado;
	private List<FormaGeometrica> listaFormas;
	
	public PainelDesenho(JLabel status) {
		this.status= status;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		formaAtual = null;
		listaFormas = new LinkedList<FormaGeometrica>();
	}
	
	public void formaAtual(FormaGeometrica forma) {
		formaAtual = forma;
		estado = 0;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if(formaAtual != null)		
			formaAtual.desenhar(g);
		for(FormaGeometrica f : listaFormas) {
			f.desenhar(g);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(formaAtual != null && formaAtual.getClass().equals(Ponto.class)) {
			//((Ponto)formaAtual).setX(e.getX()); 
			
			formaAtual = new Ponto(e.getX(), e.getY());
			
			listaFormas.add(formaAtual);
			formaAtual = formaAtual.clone();
			
			repaint();
		}
		else if(formaAtual != null && formaAtual.getClass().equals(Linha.class)) {
			
			switch(estado) {
				case 0:
				
					((Linha)formaAtual).setA(new Ponto(e.getX(), e.getY()));
					((Linha)formaAtual).setB(new Ponto(e.getX(), e.getY()));
					estado = 1;
					repaint();
					break;
			
				case 1: {
					((Linha)formaAtual).setB(new Ponto(e.getX(), e.getY()));
					repaint();
					estado = 0;
					listaFormas.add(formaAtual);
					formaAtual = formaAtual.clone();
				}
			
			}
		}
		else if(formaAtual != null && formaAtual.getClass().equals(Triangulo.class)){
			
			switch(estado) {
			case 0:
					((Triangulo)formaAtual).setA(new Ponto(e.getX(), e.getY()));
					((Triangulo)formaAtual).setB(new Ponto(e.getX(), e.getY()));
					((Triangulo)formaAtual).setC(new Ponto(e.getX(), e.getY()));
					estado = 1;
					repaint();
					break;
		
			case 1: 
					((Triangulo)formaAtual).setB(new Ponto(e.getX(), e.getY()));
					((Triangulo)formaAtual).setC(new Ponto(e.getX(), e.getY()));
					repaint();
					estado = 2;
					break;
			case 2:
					((Triangulo)formaAtual).setC(new Ponto(e.getX(), e.getY()));
					repaint();
					estado = 0;
					listaFormas.add(formaAtual);
					formaAtual = formaAtual.clone();
					break;
			}	
		}
		String msg = String.format( "Mouse clicado em (%d; %d)", e.getX(), e.getY());
		if(formaAtual != null)
			msg = msg + "- desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		
		if(formaAtual != null && formaAtual.getClass().equals(Linha.class)) {
			if(estado == 1) {
				((Linha)formaAtual).setB(new Ponto(e.getX(),e.getY()));
				repaint();
			}
		}
		
		else if(formaAtual != null && formaAtual.getClass().equals(Linha.class)) {
			
			switch(estado) {
			
				case 1: {
					((Linha)formaAtual).setB(new Ponto(e.getX(), e.getY()));
					repaint();
					estado = 0;
				}
			
			}
		}
		else if(formaAtual != null && formaAtual.getClass().equals(Triangulo.class)){
			
			switch(estado) {

				case 1: 
					((Triangulo)formaAtual).setB(new Ponto(e.getX(), e.getY()));
					((Triangulo)formaAtual).setC(new Ponto(e.getX(), e.getY()));
					repaint();
					
					break;
				case 2:
					((Triangulo)formaAtual).setC(new Ponto(e.getX(), e.getY()));
					repaint();
				
					break;
			}	
		String msg = String.format( "Mouse movimentado em (%d; %d)", e.getX(), e.getY());
		if(formaAtual != null)
			msg = msg + "- desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);		
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		String msg = String.format( "Mouse pressionado em (%d; %d)", e.getX(), e.getY());
		if(formaAtual != null)
			msg = msg + "- desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		String msg = String.format( "Bot�o solto em (%d; %d)", e.getX(), e.getY());
		status.setText(msg);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		String msg = String.format( "Mouse arrastado em (%d; %d)", e.getX(), e.getY());
		if(formaAtual != null)
			msg = msg + "- desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		String msg = String.format( "Mouse saiu do painel em (%d; %d)", e.getX(), e.getY());
		if(formaAtual != null)
			msg = msg + "- desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		String msg = String.format( "Mouse arrastado em (%d; %d)", e.getX(), e.getY());
		if(formaAtual != null)
			msg = msg + "- desenhando " + formaAtual.getNome() + " em " + formaAtual;
		status.setText(msg);
	}


}
