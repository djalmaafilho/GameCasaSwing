package negocio;

public enum FaseJogo {

	PRIMEIRA("res/mapa_pri_fase.txt"), SEGUNDA("res/mapa_seg_fase.txt",5),
	TERCEIRA("res/mapa_ter_fase.txt",10),QUARTA("res/mapa_qua_fase.txt", 20),
	QUINTA("res/mapa_qui_fase.txt", 40),SEXTA("res/mapa_sex_fase.txt", 60),
	FIM("res/mapa_fim_jogo.txt");

	private String mapaFase;
	private int qtdArtefatoRandomico;
	
	private FaseJogo(String mapaFase) {
		this.mapaFase = mapaFase;
	}

	private FaseJogo(String mapaFase, int qtdArtefatoRandomico) {
		this.mapaFase = mapaFase;
		this.qtdArtefatoRandomico = qtdArtefatoRandomico;
	}
	public String getMapaFase() {
		return mapaFase;
	}
	
	public int getQtdArtefatoRandomico(){
		return qtdArtefatoRandomico;
	}
}