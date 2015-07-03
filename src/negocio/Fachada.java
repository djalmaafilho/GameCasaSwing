package negocio;

import java.util.Collection;

public class Fachada {

	private static Fachada f;
	private JogoControler jogoControler;
	private RelogioControler controleRelogio;

	private Fachada() throws Exception {
		jogoControler = new JogoControler();
		controleRelogio = new RelogioControler();
	}

	public static Fachada getInstancia() throws Exception {
		if (f == null) {
			f = new Fachada();
		}
		return f;
	}

	public Celula[][] getmatrizCelulas() {
		return jogoControler.getCasa().getMatriz();
	}

	public void moverPraFrente(Jogador j) throws JogoException {
		jogoControler.moverPraFrente(j);
	}

	public void moverPraTras(Jogador j) throws JogoException {
		jogoControler.moverPraTras(j);
	}

	public void moverPraEsquerda(Jogador j) throws JogoException {
		jogoControler.moverPraEsquerda(j);
	}

	public void moverPraDireita(Jogador j) throws JogoException {
		jogoControler.moverPraDireita(j);
	}
	
	public void inicializarJogador(Jogador j) throws JogoException{
		jogoControler.inicializarJogador(j);
	}
	
	public Collection<Jogador> listaJogadores(){
		return jogoControler.listaJogador();
	}
	
	// Metodos de Controle Relogio
	public void reiniciarRelogio() {
		this.controleRelogio.reiniciarRelogio();
	}

	public void incrementarRelogio() {
		this.controleRelogio.incrementarRelogio();
	}

	public void pararRelogio() {
		this.controleRelogio.pararRelogio();
	}

	public Relogio recuperarRelogio() {
		return this.controleRelogio.getRelogio();
	}

	public boolean isParadoRelogio() {
		return this.controleRelogio.isParado();
	}

	public void liberarRelogio() {
		this.controleRelogio.setParado(false);
	}

	public String recuperarTempo() {
		return this.controleRelogio.getTempo();
	}
	
	//eventos do jogo
	public void addEventosListener(JogoEventoListener listener){
		jogoControler.addJogoEventoListener(listener);
	}
	
	public void replay(){
		jogoControler.replay();
	}
}
