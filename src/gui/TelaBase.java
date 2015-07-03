package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public abstract class TelaBase extends JFrame{
	
	public TelaBase(int largura, int altura, String titulo){
		super(titulo);
		setIconImage(new ImageIcon("image/logo.gif").getImage());
		init(largura, altura);
	//	configurarTela();
	//	setVisible(true);
	}

	private void init(int largura, int altura){
		Dimension dimensao = Toolkit.getDefaultToolkit().getScreenSize();
		int y = ((int) dimensao.getHeight() / 2) - (altura / 2);
		int x = ((int) dimensao.getWidth() / 2) - (largura / 2);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(x, y, largura, altura);
		setResizable(false);
	}
	
	public abstract void configurarTela();
}
