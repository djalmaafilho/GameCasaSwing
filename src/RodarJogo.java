import java.io.Console;
import java.util.Scanner;

import negocio.JogoControler;
import negocio.Celula;
import negocio.Fachada;
import negocio.Jogador;
import negocio.JogoException;
import negocio.MovimentoJogador;

public class RodarJogo {

	public static void main(String[] args) {
		try {

			// iniciar e apresentar mapa
			Fachada f = Fachada.getInstancia();
			System.out
					.println("----------------------Jogo Iniciado-----------------------");
			System.out
					.println(" Tecle enter para colocar o jogador na entrada");

			Celula[][] celulas = f.getmatrizCelulas();
			for (int linha = 0; linha < celulas.length; linha++) {
				for (int coluna = 0; coluna < celulas[linha].length; coluna++) {
					System.out.print(celulas[linha][coluna].getTipo()
							.getCaratereTipoCelula());
				}
				System.out.println("");
			}

			System.out
					.println("------------------------------------------------------------");

			// carregar jogadores
			Jogador j = new Jogador();
			j.setNome("Jose");

			f.inicializarJogador(j);

			// captura de teclas
			Scanner scn = new Scanner(System.in);
			String comando = scn.nextLine();

			
			//laco de repeticao interacao com usuario
			while (!comando.equalsIgnoreCase("sair")) {
				// limpar console
				for (int i = 0; i < 40; ++i) {
					System.out.println();
				}

				//critica de teclas
				try {

					if (comando.equalsIgnoreCase("f")) {
						f.moverPraFrente(j);
					}
					if (comando.equalsIgnoreCase("d")) {
						f.moverPraDireita(j);
					}
					if (comando.equalsIgnoreCase("e")) {
						f.moverPraEsquerda(j);
					}

					if (comando.equalsIgnoreCase("t")) {
						f.moverPraTras(j);
					}

				} catch (JogoException e) {
					System.out.println(e.getMessage());
				}

				// imprimir mapa atualizado

				System.out
						.println("---------------------------- COMANDOS -----------------------------");

				System.out
						.println(" f (Frente) - t (Trás) - e (Esquerda) - d (Direita) - sair (SAIR)");

				System.out
						.println("--------------------------------------------------------------------");
				System.out.println("Jogador: " + j.getNome() + " Posicao: "
						+ "linha: " + j.getCelula().getLinha() + " coluna: "
						+ j.getCelula().getColuna() + " tipo: "
						+ j.getCelula().getTipo());
				System.out
						.println("--------------------------------------------------------------------");

				celulas = f.getmatrizCelulas();
				for (int linha = 0; linha < celulas.length; linha++) {
					for (int coluna = 0; coluna < celulas[linha].length; coluna++) {
						if (linha == j.getCelula().getLinha()
								&& coluna == j.getCelula().getColuna()) {
							System.out.print("j");
						} else {
							System.out.print(celulas[linha][coluna].getTipo()
									.getCaratereTipoCelula());
						}
					}
					System.out.println("");
				}
				System.out
						.println("---------------------------------------------------------------------");
				
				//ler teclas novamente
				comando = scn.nextLine();
			}
			
			System.out
					.println("--------------------------Historico ------------------------------------");

			for (MovimentoJogador movimento : j.getHistorico()) {
				System.out.println("Linha: " + movimento.getCelula().getLinha()
						+ " Coluna: " + movimento.getCelula().getColuna()
						+ " Tipo: " + movimento.getCelula().getTipo()
						+ " Movimento: "
						+ movimento.getTipoMovimento().toString());
			}

			System.out
					.println("--------------------------Fim De Jogo ------------------------------------");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
