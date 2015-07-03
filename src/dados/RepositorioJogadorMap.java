package dados;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import negocio.Jogador;
import negocio.JogoException;

public class RepositorioJogadorMap implements IRepositorioJogador {
	private SortedMap<String, Jogador> mapa;

	public RepositorioJogadorMap() {
		super();
		this.mapa = new TreeMap<String, Jogador>();
	}

	@Override
	public void atualizar(Jogador j) throws JogoException {
		boolean existe = existe(j.getNome());
		if (existe == true) {
			mapa.remove(j.getNome());
			mapa.put(j.getNome(), j);
		} else {
			throw new JogoException("Jogador: " + j.getNome()
					+ " Nao existe!!!");
		}
	}

	@Override
	public boolean existe(String nome) {
		return mapa.containsKey(nome);
	}

	@Override
	public void inserir(Jogador j) throws JogoException {
		boolean existe = existe(j.getNome());
		if (existe == false) {
			mapa.put(j.getNome(), j);
		} else {
			throw new JogoException("Jogador: " + j.getNome()
					+ " Nao existe!!!");
		}
	}

	@Override
	public Jogador procurar(String nome) throws JogoException {
		Jogador c = null;
		boolean existe = existe(nome);
		if (existe == true) {
			c = (Jogador) mapa.get(nome);
		} else {
			throw new JogoException("Jogador: " + nome + " Nao existe!!!");
		}
		return c;
	}

	@Override
	public void remover(String nome) throws JogoException {
		boolean existe = existe(nome);
		if (existe == true) {
			mapa.remove(nome);
		} else {
			throw new JogoException("Jogador: " + nome + " Nao existe!!!");
		}
	}

	public void zerarQtdArtefatosVisitadosFase() {
		for(Jogador j :mapa.values()){
			j.zerarQtdArtefatosVisitadosFase();
		}
	}
	
	public Collection<Jogador> listaJogadores(){
		return mapa.values();
	}
	
}