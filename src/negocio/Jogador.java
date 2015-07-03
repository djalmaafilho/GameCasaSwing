package negocio;

import java.util.TreeSet;

public class Jogador implements Movimento{
	private String urlImagem; 
	private TreeSet<MovimentoJogador> historico;
	private Celula celula;
	private String nome;
	private int pontos;
	private int qtdArtefatosVisitadosFase;
	public Jogador(Celula celula, String nome) {
		super();
		new Jogador();
		this.celula = celula;
		this.nome = nome;
	}

	public Jogador() {
		this.historico = new TreeSet<MovimentoJogador>();
	}

	public void zerarQtdArtefatosVisitadosFase(){
		qtdArtefatosVisitadosFase = 0;
	}
	@Override
	public void moverPraFrente() {
		MovimentoJogador mov = new MovimentoJogador(System.currentTimeMillis(),
				celula, TipoMovimento.FRENTE);
		gravarMovimentoHistorico(mov);
	}

	@Override
	public void moverPraTras() {
		MovimentoJogador mov = new MovimentoJogador(System.currentTimeMillis(),
				celula, TipoMovimento.TRAS);
		gravarMovimentoHistorico(mov);	}

	@Override
	public void moverPraEsquerda() {
		MovimentoJogador mov = new MovimentoJogador(System.currentTimeMillis(),
				celula, TipoMovimento.ESQUERDA);
		gravarMovimentoHistorico(mov);	
	}

	@Override
	public void moverPraDireita() {
		MovimentoJogador mov = new MovimentoJogador(System.currentTimeMillis(),
				celula, TipoMovimento.DIREITA);
		gravarMovimentoHistorico(mov);		
	}

	public Celula getCelula() {
		return celula;
	}

	public void setCelula(Celula celula) {
		this.celula = celula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	private void gravarMovimentoHistorico(MovimentoJogador movimento){
		historico.add(movimento);
	}

	public TreeSet<MovimentoJogador> getHistorico() {
		return historico;
	}

	public int getPontos() {
		return pontos;
	}

	public void pontuar(int pontos) {
		this.pontos += pontos;
	}

	public int getQtdArtefatosVisitadosFase() {
		return qtdArtefatosVisitadosFase;
	}

	public void incrementarQtdArtefatosVisitadosFase() {
		this.qtdArtefatosVisitadosFase ++;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}
	
	
	
}