package negocio;

import java.util.EventObject;

public class JogoEvento extends EventObject{
	
	private Object informacao;
	
	public JogoEvento(Object arg0) {
		super(arg0);
	}
	public Object getInformacao() {
		return informacao;
	}
	public void setInformacao(Object informacao) {
		this.informacao = informacao;
	}
}
