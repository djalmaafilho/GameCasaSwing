package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import util.JogoUtil;

public class TelaSplash extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;

	JProgressBar barra;

	public TelaSplash() {
		carregarComponentes();
	}

	private void carregarComponentes() {
		ImageIcon imagem = new ImageIcon("image/logo_qualiti.gif");
		JLabel label = new JLabel(imagem);

		JPanel painelFilhoSuperior = new JPanel();
		painelFilhoSuperior.setBackground(Color.GRAY);
		painelFilhoSuperior.add(new JLabel("Game Casa Console"));

		barra = new JProgressBar(0, 100);
		barra.setForeground(Color.BLUE);

		JPanel painel = new JPanel();
		painel.setBackground(Color.WHITE);
		painel.setLayout(new BorderLayout());
		painel.add(painelFilhoSuperior, BorderLayout.NORTH);
		painel.add(label, BorderLayout.CENTER);
		painel.add(barra, BorderLayout.SOUTH);

		this.add(painel);
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);// centraliza a tela
		this.setUndecorated(true);
		setVisible(true);
	}
	@Override
	public void run() {
		// tocarMP3("assets/alarm.wav");
		int valorLido = 0;
		while (valorLido <= 100) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				JogoUtil.salvarLog(e);
			}
			barra.setValue(valorLido++);
		}
		dispose();
	}
}