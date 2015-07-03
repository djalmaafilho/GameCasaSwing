package dados;

import negocio.Jogador;
import negocio.JogoException;

public interface IRepositorioJogador {
	
	void inserir(Jogador c) throws JogoException;
	void atualizar(Jogador c)throws  JogoException;
	Jogador procurar(String nome)throws JogoException;
	void remover(String cpf) throws JogoException;
	boolean existe(String nome);
}
