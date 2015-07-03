package negocio;

public interface JogoEventoListener {
	public void jogadorFezPonto(JogoEvento evento);
	public void faseConcluida(JogoEvento evento);
	public void fimJogo(JogoEvento evento);
	public void replay(JogoEvento evento);
	public void atualizaReplay(JogoEvento evento);
}