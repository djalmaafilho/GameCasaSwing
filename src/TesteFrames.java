import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TesteFrames {

	public static void main(String[] args) {

		JFrame tela = new JFrame();
		tela.setBounds(100, 100, 300, 300);
		tela.setBackground(Color.blue);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon imagem = new ImageIcon("image"
				+ File.separator + "logo_qualiti_fundo_transp.gif");
		JLabel label = new JLabel(imagem);
		
		JPanel painel1 = new JPanel();
		painel1.setLayout(new BorderLayout());
		painel1.add(label, BorderLayout.CENTER);
		tela.add(painel1);
		tela.setVisible(true);

		while (true) {
			painel1.setBackground(new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
