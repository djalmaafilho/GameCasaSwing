import util.JogoUtil;
import gui.TelaJogo;
import gui.TelaSplash;
public class IniciarJogoGui {
	public static void main(String[] args) {
		Thread t = new Thread(new TelaSplash());
		t.start();
		try {
			t.join();
			new TelaJogo();
		} catch (InterruptedException e) {
			JogoUtil.salvarLog(e);
		}
	}	
}