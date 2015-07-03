package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import util.JogoUtil;

import negocio.Fachada;
import negocio.Jogador;
import negocio.MovimentoJogador;

public class TelaHistorico extends TelaBase{

	Collection<Jogador> jogadores;
	
	public TelaHistorico() {
		super(600, 300, "Historico");
		try {
			this.jogadores = Fachada.getInstancia().listaJogadores();
			configurarTela();
		} catch (Exception e) {
			JogoUtil.salvarLog(e);
		}
	}

	@Override
	public void configurarTela() {
	
		
		TreeMap<Long, Map<String, MovimentoJogador>> mapaHistorico = new TreeMap<Long, Map<String, MovimentoJogador>>();
		for(Jogador j : jogadores){
			for (MovimentoJogador movimento : j.getHistorico()) {
				HashMap<String, MovimentoJogador> mapa = 
					new HashMap<String, MovimentoJogador>();
				mapa.put(j.getNome(), movimento);
				mapaHistorico.put(movimento.getTime(), mapa);
			}
		}
		
		
		Object[][] matriz = new Object[mapaHistorico.size()][];		
		Object[] cabecalho = new String[]{"Time","Coluna","Linha","Tipo Celula","Movimento","Jogador"};
		
		int i = 0;
		for(Long l : mapaHistorico.keySet()){
			Map<String, MovimentoJogador> mapa = mapaHistorico.get(l);
			String jogador = (String)mapa.keySet().toArray()[0];
			MovimentoJogador m = (MovimentoJogador)mapa.values().toArray()[0];
			matriz[i] = new Object[]{l ,m.getCelula().getColuna(),
					m.getCelula().getLinha(),
					m.getCelula().getTipo(),m.getTipoMovimento(), jogador};
			i++;
		}
		
		
		JTable tabel = new JTable(matriz, cabecalho);
		
		JScrollPane scroll = new JScrollPane(tabel);
		
		setLayout(new BorderLayout());
		getContentPane().add(scroll, BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		JButton botao = new JButton("Replay");
	}
	

}