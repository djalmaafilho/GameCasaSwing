package negocio;

public class MovimentoJogador implements Comparable{

	private long time;
	private Celula celula;
	private TipoMovimento tipoMovimento;
	
	public MovimentoJogador() {
		super();
	}

	public MovimentoJogador(long time, Celula celula,
			TipoMovimento tipoMovimento) {
		super();
		this.time = time;
		this.celula = celula;
		this.tipoMovimento = tipoMovimento;
	}

	public Celula getCelula() {
		return celula;
	}

	public void setCelula(Celula celula) {
		this.celula = celula;
	}

	public TipoMovimento getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(TipoMovimento tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public int compareTo(Object arg0) {
		MovimentoJogador m = (MovimentoJogador) arg0;
		return (int )(this.time - m.time);
	}
}