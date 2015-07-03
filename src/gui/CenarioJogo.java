package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import negocio.Celula;
import negocio.Jogador;

public class CenarioJogo extends JPanel{
	private static final long serialVersionUID = 1L;
	private int largura, altura;
	private int x, y;
	private Celula[][] celulas;
	private ArrayList<Jogador> jogadores;
	private int larguraFigura;
	private int alturaFigura;
	
	public CenarioJogo(Celula[][] celulas) {
		setFocusable(true);
		this.setCelulas(celulas);
		this.setBackground(Color.GRAY);
	}

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Image img = null;
		if(largura == 0){
			largura = g.getClipBounds().width;
		}
		if(altura == 0){
			altura = g.getClipBounds().height;
		}
		int qtdLinha = getCelulas().length;
		int qtdColuna = 0;
		for(int i = 0; i <  getCelulas().length; i++){
			if(qtdColuna < getCelulas()[i].length){
				qtdColuna = getCelulas()[i].length;
			}
		}
		
		larguraFigura = largura / (qtdColuna);
		alturaFigura = altura / (qtdLinha);
		x = 2 * larguraFigura / 3;
		y = 0;
		for (int linha = 0; linha < getCelulas().length; linha++) {
			for (int coluna = 0; coluna < getCelulas()[linha].length; coluna++) {
				img = selecionarImagem(getCelulas()[linha][coluna]);
				g.drawImage(img, x, y, larguraFigura, alturaFigura, null);
				getCelulas()[linha][coluna].setX(x);
				getCelulas()[linha][coluna].setY(y);
				x += larguraFigura;
			}
			x = 2 * larguraFigura / 3;
			y += alturaFigura;
		}
		if (jogadores != null) {
			for(int i = 0; i < jogadores.size(); i++){
				img = new ImageIcon(jogadores.get(i).getUrlImagem()).getImage();
				Celula cel = jogadores.get(i).getCelula();
				
				g.drawImage(img, cel.getX(), cel.getY(), larguraFigura,
						alturaFigura, null);
			}
		}
	}

	private Image selecionarImagem(Celula cel) {
		return cel.getTipo().getImage();
	}

	public void atualizar(ArrayList<Jogador> jogadores) {
		this.jogadores = jogadores;
		repaint(500);
	}

	public void setCelulas(Celula[][] celulas) {
		this.celulas = celulas;
	}

	public Celula[][] getCelulas() {
		return celulas;
	}
}