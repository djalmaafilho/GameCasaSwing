package negocio;

public class Celula implements Cloneable{

	private int linha, coluna;
	private int x, y;
	private TipoCelula tipo;

	public Celula() {
		super();
	}

	public Celula(int linha, int coluna, TipoCelula tipo) {
		super();
		this.linha = linha;
		this.coluna = coluna;
		this.tipo = tipo;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public TipoCelula getTipo() {
		return tipo;
	}

	public void setTipo(TipoCelula tipo) {
		this.tipo = tipo;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}