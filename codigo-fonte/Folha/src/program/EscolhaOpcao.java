package program;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class EscolhaOpcao {
	protected MenuOpcoes opcao;
	
	private final static MenuOpcoes[] choices = {
												 MenuOpcoes.LISTAR_ESTAGIAROS, 
												 MenuOpcoes.LISTAR_PROGRAMADORES,
												 MenuOpcoes.LISTAR_GERENTES,
												 MenuOpcoes.CALCULAR_SALARIO_TOTAL,
												 MenuOpcoes.ADD_GERENTE,
												 MenuOpcoes.ADD_PROGRAMADOR,
												 MenuOpcoes.ADD_ESTAGIARIO,
												 MenuOpcoes.FINALIZA_APP
												};
	
	
	private MenuOpcoes getSolicitacao() {
		Scanner textIn = new Scanner(System.in);
		int solicitacao = 1;
		
		System.out.println("\n**************************************************************************************************************\n"
				+ "\t\t\t\t\tFOLHA DE PAGAMENTO\n\nPAGINA INICIAL"
	     + "\n**************************************************************************************************************\n");
		
		
		System.out.printf("%s\n", "ESCOLHA UMA DAS OPCOES ABAIXO:\r\n\n" +
				" 	1 - Listar estagiarios\r\n" + 
				"	2 - Listar programadores\r\n" + 
				"	3 - Listar gerentes\r\n" + 
				"	4 - Calculo de salario por funcionarios\r\n" + 
				"	5 - Adicionar gerente\r\n" + 
				"	6 - Adicionar programador\r\n" + 
				"	7 - Adicionar estagiario\r\n" + 
				"	8 - Encerrar programa");
		System.out.print(">>> ");
		
		try {
			do {
				solicitacao = textIn.nextInt();
			} while ((solicitacao < 1) || (solicitacao > 8));
		}
		
		catch (NoSuchElementException elementException) {
			System.err.println("Opcao invalida. Tente novamente!");
			//System.exit(1);
		}
		

	
		return choices[solicitacao - 1];
	}
				
	public void processaSolicitacao ( ) {
		int op = 10;
		opcao = getSolicitacao();


		while(opcao != MenuOpcoes.FINALIZA_APP) {
			Gerente gerente = new Gerente();
			Programador programador = new Programador();
			Estagiario estagiario = new Estagiario();
			
			switch ( opcao ) {
				case LISTAR_ESTAGIAROS: 
					estagiario.mostraFuncionario();
					break;
					
				case LISTAR_PROGRAMADORES:
					programador.mostraFuncionario();
					break;
					
				case LISTAR_GERENTES:
					gerente.mostraFuncionario();
					break;
					
				case CALCULAR_SALARIO_TOTAL:
					op = 0;
					Scanner input = new Scanner(System.in);

					do {
						
						System.out.println("\n**************************************************************************************************************\n"
								+ "\t\t\t\t\tFOLHA DE PAGAMENTO\n\nPAGINA INICIAL > CALCULO INDIVIDUAL DE SALARIO"
					     + "\n**************************************************************************************************************\n");
					  
					
						System.out.println("Escolha uma das opcoes abaixo\n\n"
											+ "1 - Gerente\n"
											+ "2 - Programador\n"
											+ "3 - Estagiario\n"
											+ "4 - Voltar\n\n");
						System.out.print(">>> ");

						op = input.nextInt();
						
						if(op == 1)  {
							System.out.println("\n**************************************************************************************************************\n"
									+ "\t\t\t\t\tFOLHA DE PAGAMENTO\n\nPAGINA INICIAL > CALCULO INDIVIDUAL DE SALARIO > GERENTE"
						     + "\n**************************************************************************************************************\n");
						  
							gerente.calculaSalarioIndividual();
							
						}
						
						else if (op == 2)  {
							System.out.println("\n**************************************************************************************************************\n"
									+ "\t\t\t\t\tFOLHA DE PAGAMENTO\n\nPAGINA INICIAL > CALCULO INDIVIDUAL DE SALARIO > PROGRAMADOR"
						     + "\n**************************************************************************************************************\n");
						  
							programador.calculaSalarioIndividual();
						}
						
						else if (op == 3)  {
							System.out.println("\n**************************************************************************************************************\n"
									+ "\t\t\t\t\tFOLHA DE PAGAMENTO\n\nPAGINA INICIAL > CALCULO INDIVIDUAL DE ESTAGIARIO"
						     + "\n**************************************************************************************************************\n");
						  
							estagiario.calculaSalarioIndividual();
							
						}
						
						else if (op == 4)  {
							break;
				
						}
						
					} while (op < 1 || op >3);
					
					break;
					
				case ADD_GERENTE:
					gerente.gravaGerente();
					break;
					
				case ADD_PROGRAMADOR:
					programador.gravaProgramador();
					break;
				
				case ADD_ESTAGIARIO:
					estagiario.gravaEstagiario();
					break;
					
				default:
					break;
			}// fim swtich
			
			int continuacao = 1;
			System.out.printf("\n\nPressine 0 continuar ou 8 para encerrar o programa...\n>>> ");
			Scanner input = new Scanner(System.in);
			
			if(op != 4)
				continuacao = input.nextInt();
			
			if(continuacao == 8)
				break;
			
			opcao = getSolicitacao();
			
		} //fim while
	}
}
