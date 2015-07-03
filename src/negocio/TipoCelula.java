package negocio;

import java.awt.Image;

import javax.swing.ImageIcon;

public enum TipoCelula {

	ArtefatoVisitado("X","image/artefato_visitado.png"),Artefato("A","image/artefato.png"),Parede("#", "image/parede.png"), PortaDeEntrada("E","image/porta_entrada.png"), 
	Porta("P", "image/porta.png"), Sala("S", "image/sala.png"), Cozinha("O", "image/cozinha.png"), 
	Corredor("C", "image/corredor.png"), Quarto("Q", "image/corredor.png"), Banheiro("B", "image/banheiro.png");

	private String caratereTipoCelula;
	private String urlImagem;
	private Image imagem;

	private TipoCelula(String caratereTipoCelula, String urlImagem) {
		this.caratereTipoCelula = caratereTipoCelula;
		this.urlImagem = urlImagem;
		this.imagem = new ImageIcon(getUrlImagem()).getImage();
	}

	public String getCaratereTipoCelula() {
		return caratereTipoCelula;
	}

	public String getUrlImagem() {
		return urlImagem;
	}
	
	public Image getImage(){
		return imagem;
	}
}