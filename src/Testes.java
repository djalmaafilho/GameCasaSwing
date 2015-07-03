
import java.util.Scanner;
import negocio.Fachada;
import negocio.Jogador;
import negocio.JogoException;

public class Testes {

	public static void main(String[] args) {
		try {
			
			System.out.println("------------Jogo Iniciado--------------");
			
			Fachada f = Fachada.getInstancia();
			
			Jogador j = new Jogador();
			j.setNome("Jose");
			
			f.inicializarJogador(j);
			
			Scanner scn = new Scanner(System.in);
			String comando = scn.nextLine();
			
			
			while(!comando.equalsIgnoreCase("sair")){
				System.out.println(comando);
				
				try {
					
					if(comando.equalsIgnoreCase("f")){
						f.moverPraFrente(j);
					}
					if(comando.equalsIgnoreCase("d")){
						f.moverPraDireita(j);
					}
					if(comando.equalsIgnoreCase("e")){
						f.moverPraEsquerda(j);
					}
					
					if(comando.equalsIgnoreCase("t")){
						f.moverPraTras(j);
					}
					
				} catch (JogoException e) {
					System.out.println(e.getMessage());
				}
				
				System.out.println(j.getCelula().getLinha() +" "+j.getCelula().getColuna()+""+
						j.getCelula().getTipo());
				
				comando = scn.nextLine();
			}
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
