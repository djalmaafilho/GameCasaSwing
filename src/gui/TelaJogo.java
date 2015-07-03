package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import negocio.Celula;
import negocio.Fachada;
import negocio.Jogador;
import negocio.JogoEvento;
import negocio.JogoEventoListener;
import negocio.JogoException;
import util.JogoUtil;
import util.Player;

public class TelaJogo extends TelaBase implements KeyListener, ActionListener,
		JogoEventoListener{
	private static final long serialVersionUID = 1L;
	private CenarioJogo cenarioReplay;
	private JButton btAjuda;
	private JButton btReplay;
	private Player player;
	private int indiceImagemComercial;
	private List<Image> listaFiguras;
	private ExecutorService pollThreads;
	private JLabel labelRelogio;
	private JLabel labelComerciais;
	private JLabel labelPontosJogador;
	private boolean jogoLigado;
	private CenarioJogo cenario;
	private Fachada f;
	private Celula[][] celulas;
	private boolean tamTelaAjustado;

	public TelaJogo() {
		super(800, 480, "Jogo Casa Console");
		try {
			f = Fachada.getInstancia();
			f.addEventosListener(this);
			
			Jogador j = new Jogador();
			j.setNome("Azul");
			j.setUrlImagem("image/jogador.png");
			f.inicializarJogador(j);
			
			j = new Jogador();
			j.setNome("Verde");
			j.setUrlImagem("image/jogador1.png");
			f.inicializarJogador(j);

			celulas = f.getmatrizCelulas();
			cenario = new CenarioJogo(celulas);
			cenario.addKeyListener(this);
		} catch (Exception e) {
			JogoUtil.salvarLog(e);
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.exit(0);
		}
		pollThreads = Executors.newFixedThreadPool(f.listaJogadores().size());
		listaFiguras = new ArrayList<Image>();
		iniciarComerciais();
		player = new Player();
		configurarTela();
	}

	@Override
	public void configurarTela() {
		setLayout(new BorderLayout(0, 0));
		JPanel painelSuperior = new JPanel();
		JPanel painelEsquerda = new JPanel();
		JPanel painelDireita = new JPanel();
		JPanel painelInferior = new JPanel();
		painelSuperior.setBackground(Color.GRAY);
		painelInferior.setBackground(Color.GRAY);
		painelEsquerda.setBackground(Color.GRAY);
		painelDireita.setBackground(Color.GRAY);

		btAjuda = new JButton("?");
		btAjuda.setBackground(Color.RED);
		btAjuda.setForeground(Color.WHITE);
		btAjuda.addActionListener(this);
		
		btReplay = new JButton("replay");
		btReplay.addActionListener(this);

		labelRelogio = new JLabel("00:00");
		labelRelogio.setForeground(Color.WHITE);

		labelPontosJogador = new JLabel("0000");
		labelPontosJogador.setForeground(Color.WHITE);

		JPanel painelCentroSuperior = new JPanel();
		painelCentroSuperior.setBackground(Color.GRAY);
		painelCentroSuperior.add(new JLabel("Tempo: "));
		painelCentroSuperior.add(labelRelogio);
		painelCentroSuperior.add(new JLabel(" Pontos: "));
		painelCentroSuperior.add(labelPontosJogador);
		
		
		painelSuperior.setLayout(new BorderLayout());
		painelSuperior.add(painelCentroSuperior, BorderLayout.CENTER);
		
		painelSuperior.add(btAjuda, BorderLayout.LINE_END);
		painelSuperior.add(btReplay, BorderLayout.LINE_START);

		labelComerciais = new JLabel("Comerciais");
		painelInferior.add(labelComerciais);
		painelInferior.setSize(this.getWidth(), 30);

		this.setLocation(this.getX(), 0);
		this.add(painelSuperior, BorderLayout.NORTH);
		this.add(painelInferior, BorderLayout.SOUTH);
		this.add(painelEsquerda, BorderLayout.WEST);
		this.add(painelDireita, BorderLayout.EAST);
		this.add(cenario, BorderLayout.CENTER);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		cenario.requestFocus();
	}

	private void iniciarComerciais() {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					listaFiguras.addAll(JogoUtil.carregarComerciais());
				} catch (MalformedURLException e) {
					JogoUtil.salvarLog(e);
				} catch (IOException e) {
					JogoUtil.salvarLog(e);
				}
			}
		};
		this.pollThreads.execute(t);
		exibirComerciais();
	}

	@Override
	public void keyPressed(KeyEvent evento) {
		try {
			Jogador j = (Jogador)f.listaJogadores().toArray()[0];
			if (evento.getKeyCode() == KeyEvent.VK_LEFT) {
				f.moverPraEsquerda(j);
			} else if (evento.getKeyCode() == KeyEvent.VK_RIGHT) {
				f.moverPraDireita(j);
			} else if (evento.getKeyCode() == KeyEvent.VK_DOWN) {
				f.moverPraFrente(j);
			} else if (evento.getKeyCode() == KeyEvent.VK_UP) {
				f.moverPraTras(j);
			}else if(f.listaJogadores().size() > 1){
				j = (Jogador)f.listaJogadores().toArray()[1];
				if (evento.getKeyCode() == KeyEvent.VK_A) {
					f.moverPraEsquerda(j);
				} else if (evento.getKeyCode() == KeyEvent.VK_D) {
					f.moverPraDireita(j);
				} else if (evento.getKeyCode() == KeyEvent.VK_S) {
					f.moverPraFrente(j);
				} else if (evento.getKeyCode() == KeyEvent.VK_W) {
					f.moverPraTras(j);
				}
			} else {
				return;
			}
			
			if (!jogoLigado) {
				player.tocarMID("assets/music.mid", true);
				jogoLigado = true;
				iniciarRelogio();
			}
			
			cenario.atualizar(new ArrayList<Jogador>(f.listaJogadores()));
		} catch (JogoException e) {
			Thread t = new Thread() {
				@Override
				public void run() {
					player.tocarAudio("assets/explosion.wav");
				}
			};
			this.pollThreads.execute(t);
		}
	}
	
	private void exibirComerciais() {
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				ImageIcon imgIcon = null;
				if (!listaFiguras.isEmpty()) {
					labelComerciais.setText("");
					Image img = listaFiguras.get(indiceImagemComercial);
					imgIcon = new ImageIcon(img.getScaledInstance(
							TelaJogo.this.getBounds().width, 100,
							Image.SCALE_AREA_AVERAGING));
					labelComerciais.setIcon(imgIcon);
					if (indiceImagemComercial < listaFiguras.size() - 1) {
						indiceImagemComercial++;
					} else {
						indiceImagemComercial = 0;
					}
				}
				if (imgIcon != null && tamTelaAjustado == false) {
					tamTelaAjustado = true;
					TelaJogo.this.setBounds(
							TelaJogo.this.getBounds().x,
							TelaJogo.this.getBounds().y,
							TelaJogo.this.getBounds().width,
							TelaJogo.this.getBounds().height
									+ imgIcon.getIconHeight());
				}
			}
		};
		timer.schedule(timerTask, 1000, 5000);
	}

	private void iniciarRelogio() {
		Timer timer = new Timer();
		f.liberarRelogio();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				f.incrementarRelogio();
				labelRelogio.setText(f.recuperarTempo());
			}
		};
		timer.schedule(timerTask, 1000, 1000);
	}

	@Override
	public void keyReleased(KeyEvent evento) {

	}

	@Override
	public void keyTyped(KeyEvent evento) {

	}

	@Override
	public void dispose() {
		super.dispose();
		player.pararMID();
		pollThreads.shutdown();
		new TelaHistorico();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAjuda) {
			new TelaAjuda().setLocationRelativeTo(this);
		}else if(e.getSource() == btReplay){
			f.replay();
		}
	}

	@Override
	public void jogadorFezPonto(JogoEvento evento) {
		String pontos = "";
		
		for(Jogador j: f.listaJogadores()){
			pontos+=" ";
			pontos+=j.getNome();
			pontos+=":";
			pontos+=j.getPontos();
		}
		
		labelPontosJogador.setText(pontos);
		Thread t = new Thread() {
			@Override
			public void run() {
				player.tocarAudio("assets/win2.wav");
			}
		};
		this.pollThreads.execute(t);
	}

	@Override
	public void faseConcluida(JogoEvento evento) {
		cenario.atualizar(new ArrayList<Jogador>(f.listaJogadores()));
		JOptionPane.showMessageDialog(this, "Parabéns! \n Fase concluida");
		celulas = f.getmatrizCelulas();
		cenario.setCelulas(celulas);
		for(Jogador j:f.listaJogadores()){
			try {
				f.inicializarJogador(j);
			} catch (JogoException e) {
				JogoUtil.salvarLog(e);
			}
		}
	}

	@Override
	public void fimJogo(JogoEvento evento) {
		player.pararMID();
		celulas = f.getmatrizCelulas();
		cenario.setCelulas(celulas);
		f.pararRelogio();
		cenario.removeKeyListener(this);
	}

	@Override
	public void replay(JogoEvento evento) {
		cenarioReplay = new CenarioJogo(f.getmatrizCelulas());
		JFrame telaReplay = new JFrame();
		telaReplay.setBounds(this.getBounds());
		telaReplay.setTitle("Replay");
		telaReplay.add(cenarioReplay);
		telaReplay.setVisible(true);
		cenarioReplay.requestFocus();
	}

	@Override
	public void atualizaReplay(JogoEvento evento) {
		ArrayList<Jogador> lista = (ArrayList<Jogador>)evento.getInformacao();
		cenarioReplay.atualizar(lista);
	}
}