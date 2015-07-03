package gui;

import javax.swing.JTextArea;

import util.JogoUtil;

public class TelaAjuda extends TelaBase{

	public TelaAjuda() {
		super(300, 300, "Ajuda");
		configurarTela();
	}

	@Override
	public void configurarTela() {
		try {
			String str = JogoUtil.recuperarTextoArquivo("assets/ajuda.txt");
			JTextArea texto = new JTextArea(str);
			texto.setEditable(false);
			this.add(texto);
		} catch (Exception e) {
			JogoUtil.salvarLog(e);
		}
		setVisible(true);
	}
}
