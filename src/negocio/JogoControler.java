package negocio;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import util.JogoUtil;
import dados.RepositorioJogadorMap;

public class JogoControler {
	private boolean replayRodando;
	private RepositorioJogadorMap repositorioJogador;
	private FaseJogo faseJogo;
	private Casa casa;
	List<JogoEventoListener> listaListeners;
	private int qtdArtefatosFase;
	private String[] dadosValidos = { "A", "#", "E", "P", "S", "O", "C", "Q",
			"B", "\n" };
	
	public JogoControler() throws Exception {
		super();
		repositorioJogador = new RepositorioJogadorMap();
		faseJogo = FaseJogo.PRIMEIRA;
		casa = new Casa();
		casa.setMatriz(initMatriz(faseJogo));
		carregarArtefatosRandomicos();
		listaListeners = new ArrayList<JogoEventoListener>();
		qtdArtefatosFase = calcularQtdArtefatosFase();
	}

	public void addJogoEventoListener(JogoEventoListener listener) {
		if (!listaListeners.contains(listener)) {
			listaListeners.add(listener);
		}
	}

	private Celula[][] initMatriz(FaseJogo fase) throws Exception {
		String txtCasa = JogoUtil.recuperarTextoArquivo(fase.getMapaFase());
		txtCasa = txtCasa.replace("\r", "");
		Celula[][] matrizCelula = null;
		if (isDadosValidos(txtCasa)) {
			String[][] matrizString = recuperarMatrizString(txtCasa);
			matrizCelula = new Celula[matrizString.length][];
			
			for (int linha = 0; linha < matrizCelula.length; linha++) {
				matrizCelula[linha] = new Celula[matrizString[linha].length];
				for (int coluna = 0; coluna < matrizCelula[linha].length; coluna++) {
					matrizCelula[linha][coluna] = new Celula(linha, coluna,
							recuperarTipoCelula(matrizString[linha][coluna]));
				}
			}
		} else {
			throw new Exception("dados invalidos no carregamento do jogo!!!");
		}
		return matrizCelula;
	}

	private String[][] recuperarMatrizString(String txtCasa) {
		String[] linhas = txtCasa.split("\n");
		for (int i = 0; i < linhas.length; i++) {
			linhas[i] = linhas[i].replace("\n", "");
		}
		String[][] matrizString = new String[linhas.length][];
		String auxCaractere = null;
		for (int linha = 0; linha < linhas.length; linha++) {
			matrizString[linha] = new String[linhas[linha].length()];

			for (int coluna = 0; coluna < linhas[linha].length(); coluna++) {
				auxCaractere = "" + linhas[linha].charAt(coluna);
				matrizString[linha][coluna] = auxCaractere;
			}
		}
		return matrizString;
	}

	private boolean isDadosValidos(String txt) {
		boolean valido = false;
		if (txt.contains("\r")) {
			return false;
		}
		for (int i = 0; i < txt.length(); i++) {
			valido = false;
			for (String aux : dadosValidos) {
				valido = aux.equals("" + txt.charAt(i));
				if (valido == true) {
					break;
				}
			}
			if (valido == false) {
				return valido;
			}
		}
		return valido;
	}

	private TipoCelula recuperarTipoCelula(String str) {

		if (str.equals(TipoCelula.Parede.getCaratereTipoCelula())) {
			return TipoCelula.Parede;

		} else if (str.equals(TipoCelula.Banheiro.getCaratereTipoCelula())) {
			return TipoCelula.Banheiro;

		} else if (str.equals(TipoCelula.Corredor.getCaratereTipoCelula())) {
			return TipoCelula.Corredor;

		} else if (str.equals(TipoCelula.Cozinha.getCaratereTipoCelula())) {
			return TipoCelula.Cozinha;

		} else if (str.equals(TipoCelula.Porta.getCaratereTipoCelula())) {
			return TipoCelula.Porta;

		} else if (str
				.equals(TipoCelula.PortaDeEntrada.getCaratereTipoCelula())) {
			return TipoCelula.PortaDeEntrada;

		} else if (str.equals(TipoCelula.Quarto.getCaratereTipoCelula())) {
			return TipoCelula.Quarto;

		} else if (str.equals(TipoCelula.Artefato.getCaratereTipoCelula())) {
			return TipoCelula.Artefato;

		} else if (str.equals(TipoCelula.ArtefatoVisitado
				.getCaratereTipoCelula())) {
			return TipoCelula.ArtefatoVisitado;

		} else {
			return TipoCelula.Sala;
		}
	}

	public Casa getCasa() {
		return casa;
	}

	public Celula recuperarCelulaInicio() {
		int l, c;
		l = c = 0;
		label: for (int linha = 0; linha < casa.getMatriz().length; linha++) {
			for (int coluna = 0; coluna < casa.getMatriz()[linha].length; coluna++) {
				if (casa.getMatriz()[linha][coluna].getTipo().equals(
						TipoCelula.PortaDeEntrada)) {
					l = linha;
					c = coluna;
					break label;
				}
			}
		}
		return casa.getMatriz()[l][c];
	}

	public void moverPraFrente(Jogador j) throws JogoException {
		int linha = j.getCelula().getLinha();
		int coluna = j.getCelula().getColuna();
		linha++;
		atualizarStatusJogo(linha, coluna, j);
		j.moverPraFrente();
		repositorioJogador.atualizar(j);
	}

	public void moverPraTras(Jogador j) throws JogoException {
		int linha = j.getCelula().getLinha();
		int coluna = j.getCelula().getColuna();
		linha--;
		atualizarStatusJogo(linha, coluna, j);
		j.moverPraTras();
		repositorioJogador.atualizar(j);
	}

	public void moverPraEsquerda(Jogador j) throws JogoException {
		int linha = j.getCelula().getLinha();
		int coluna = j.getCelula().getColuna();
		coluna--;
		atualizarStatusJogo(linha, coluna, j);
		j.moverPraEsquerda();
		repositorioJogador.atualizar(j);
	}

	public void moverPraDireita(Jogador j) throws JogoException {
		int linha = j.getCelula().getLinha();
		int coluna = j.getCelula().getColuna();
		coluna++;
		atualizarStatusJogo(linha, coluna, j);
		j.moverPraDireita();
		repositorioJogador.atualizar(j);
	}

	private void atualizarStatusJogo(int linha, int coluna, Jogador j)
			throws JogoException {
		validarMovimento(linha, coluna);
		j.setCelula(casa.getMatriz()[linha][coluna]);
		boolean marcarPonto = validacaoParaVisitaArtefato(linha, coluna);
		if (marcarPonto) {
			// mudar a celula
			Celula cel = new Celula(linha, coluna, TipoCelula.ArtefatoVisitado);
			cel.setX(casa.getMatriz()[linha][coluna].getX());
			cel.setY(casa.getMatriz()[linha][coluna].getY());
			casa.getMatriz()[linha][coluna] = cel;

			j.pontuar(calcularPontos());
			j.incrementarQtdArtefatosVisitadosFase();
			notificarJogadorFezPonto();
		}

		if (ocorreuFimFase()) {
			setarParaProximaFase();
			try {
				casa.setMatriz(initMatriz(faseJogo));
				carregarArtefatosRandomicos();
			} catch (Exception e) {
				JogoUtil.salvarLog(e);
			}
			qtdArtefatosFase = calcularQtdArtefatosFase();
			repositorioJogador.zerarQtdArtefatosVisitadosFase();
			notificarFaseConcluida();
		}
	}

	private int calcularPontos() {
		return 100;
	}

	private void setarParaProximaFase() {
		switch (faseJogo) {
		case PRIMEIRA:
			faseJogo = FaseJogo.SEGUNDA;
			break;
		case SEGUNDA:
			faseJogo = FaseJogo.TERCEIRA;
			break;
		case TERCEIRA:
			faseJogo = FaseJogo.QUARTA;
			break;
		case QUARTA:
			faseJogo = FaseJogo.QUINTA;
			break;
		case QUINTA:
			faseJogo = FaseJogo.SEXTA;
			break;
		case SEXTA:
			faseJogo = FaseJogo.FIM;
			break;	
		default:
			break;
		}
	}

	private int calcularQtdArtefatosFase() {
		int qtdArtefatos = 0;
		for (Celula[] arrayCelula : casa.getMatriz()) {
			for (Celula celula : arrayCelula) {
				if (celula.getTipo().equals(TipoCelula.Artefato)) {
					qtdArtefatos++;
				}
			}
		}
		return qtdArtefatos;
	}

	private void carregarArtefatosRandomicos(){
		int qtdArtefatos = 0;
		int probabilidade = 100;
		int qtdArtefatoRandomico = faseJogo.getQtdArtefatoRandomico();
		int comparadorRandomico = (int) (probabilidade * Math.random());
		while(qtdArtefatos < qtdArtefatoRandomico){
			for (Celula[] arrayCelula : casa.getMatriz()) {
				for (Celula celula : arrayCelula) {
					if(comparadorRandomico == (int) (probabilidade * Math.random())){
						if (!celula.getTipo().equals(TipoCelula.Parede) && 
								!celula.getTipo().equals(TipoCelula.Artefato) &&
								!celula.getTipo().equals(TipoCelula.Porta)&&
								!celula.getTipo().equals(TipoCelula.PortaDeEntrada)) {
							celula.setTipo(TipoCelula.Artefato);
							qtdArtefatos++;
						}
					}
					if(qtdArtefatos == qtdArtefatoRandomico){
						return;
					}
				}
			}
		}
	}
	
	private boolean ocorreuFimFase() {
		int somaArtefatos = 0;
		for(Jogador jogador: repositorioJogador.listaJogadores()){
			somaArtefatos += jogador.getQtdArtefatosVisitadosFase();
		}
		
		if (qtdArtefatosFase == somaArtefatos) {
			return true;
		}
		return false;
	}

	private boolean validacaoParaVisitaArtefato(int linha, int coluna) {
		if (casa.getMatriz()[linha][coluna].getTipo().equals(
				TipoCelula.Artefato)) {
			return true;
		}
		return false;
	}

	private void validarMovimento(int linha, int coluna) throws JogoException {
		if (linha < 0 || coluna < 0) {
			throw new JogoException(
					"Movimento Invalido!!! Fora dos Limites do Jogo");
		} else if (linha >= casa.getMatriz().length
				|| coluna >= casa.getMatriz()[linha].length) {
			throw new JogoException(
					"Movimento Invalido!!! Fora dos Limites do Jogo");
		} else if (casa.getMatriz()[linha][coluna].getTipo() == TipoCelula.Parede) {
			throw new JogoException("Movimento Invalido!!! Parede!!!");
		}
	}

	public void inicializarJogador(Jogador j) throws JogoException {
		if(!repositorioJogador.existe(j.getNome())){
			repositorioJogador.inserir(j);
		}
		j.setCelula(recuperarCelulaInicio());
	}

	public void notificarJogadorFezPonto() {
		for (JogoEventoListener listener : listaListeners) {
			listener.jogadorFezPonto(new JogoEvento(this));
		}
	}
	
	public void notificarFaseConcluida(){
		for (JogoEventoListener listener : listaListeners) {
			if(this.faseJogo.equals(FaseJogo.FIM)){
				listener.fimJogo(new JogoEvento(this));
			}else{
				listener.faseConcluida(new JogoEvento(this));
			}
		}
	}
	
	public void notificarReplay(){
		for (JogoEventoListener listener : listaListeners) {
			listener.replay(new JogoEvento(this));
		}
	}
	
	public void notificarAtualizacaoReplay(ArrayList<Jogador> lista){
		for (JogoEventoListener listener : listaListeners) {
			JogoEvento evento = new JogoEvento(this);
			evento.setInformacao(lista);
			listener.atualizaReplay(evento);
		}
	}
	
	public Collection<Jogador> listaJogador(){
		return repositorioJogador.listaJogadores();
	}
	
	public synchronized void replay(){
		while(replayRodando == true){
			return;
		}
		replayRodando = true;
		
		new Thread(){
			public void run() {
				try {
					notificarReplay();
					executarReplay();
				} catch (Exception e) {
					JogoUtil.salvarLog(e);
				}finally{
					replayRodando = false;					
				}
			};
		}.start();
	}
	
	private void executarReplay() throws InterruptedException{
		ArrayList<Jogador> listaJogador = new ArrayList<Jogador>();
		for(Jogador j : listaJogador()){
			Jogador jogadorAux = new Jogador();
			jogadorAux.setCelula(recuperarCelulaInicio());
			jogadorAux.setNome(j.getNome());
			jogadorAux.setUrlImagem(j.getUrlImagem());
			listaJogador.add(jogadorAux);			
		}
		notificarAtualizacaoReplay(listaJogador);
		
		TreeMap<Long, Map<String, MovimentoJogador>> mapaHistorico = 
			new TreeMap<Long, Map<String, MovimentoJogador>>();
		for(Jogador j : listaJogador()){
			for (MovimentoJogador movimento : j.getHistorico()) {
				HashMap<String, MovimentoJogador> mapa = 
					new HashMap<String, MovimentoJogador>();
				mapa.put(j.getNome(), movimento);
				mapaHistorico.put(movimento.getTime(), mapa);
			}
		}				
		
		Iterator<Long> it = mapaHistorico.keySet().iterator();
		if(it.hasNext()){
			Long passoAnterior;
			Long chave = it.next();
			passoAnterior = chave;
			Map<String, MovimentoJogador> mapa =  mapaHistorico.get(chave);
			
			String nome = (String)mapa.keySet().toArray()[0];
			MovimentoJogador mov = mapa.get(nome);
			
			for (Jogador jogador : listaJogador) {
				if(jogador.getNome().equals(nome)){
					jogador.setCelula(mov.getCelula());
				}
			}
			notificarAtualizacaoReplay(listaJogador);
			
			while(it.hasNext()){
					chave = it.next();
					Thread.sleep(chave  - passoAnterior);
					
					mapa =  mapaHistorico.get(chave);
					nome = (String)mapa.keySet().toArray()[0];
					mov = mapa.get(nome);
					
					for (Jogador jogador : listaJogador) {
						if(jogador.getNome().equals(nome)){
							jogador.setCelula(mov.getCelula());
						}
					}
					notificarAtualizacaoReplay(listaJogador);
					passoAnterior = chave;
			}
		}
		
	}
}