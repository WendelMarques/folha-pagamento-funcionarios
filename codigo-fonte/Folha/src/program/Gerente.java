package program;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Gerente implements Funcionario{
	protected int matricula;
	protected String primeiro_nome;
	protected String ultimo_nome;
	protected double salarioBase;
	protected  int numeroProjetosConcluidosMes;
	protected double descontosGerenciais;
	protected double salarioTotal;
	protected  Scanner leitura_texto;

	Scanner input = new Scanner (System.in);
	 
	//Construtor
	public Gerente() {
		this(0, "", "", 0.0, 0, 0.0);				
	}

	//Construtor
	public Gerente(int matricula, String primeiro_nome, String ultimo_nome, double salarioB, int numeroProjetosConcluidosMes, double descontosGerenciais) {
		setMatricula(matricula);
		setPrimeiroNome(primeiro_nome);
		setSalarioBase(salarioB);
		setNumeroProjetosConcluidosMes(numeroProjetosConcluidosMes);
		setDescontosGerenciais(descontosGerenciais);
	}
	

	//GRAVA UM GERENTE NO ARQUIVO
	//
	//
	//
	public void gravaGerente () {
		int resposta;
		
		do {
			
			BufferedWriter bw = null;
			int verificador = 0;
				
			//ABRE O ARQUIVO 
				//Se ele n existir, um é criado; se existir, ele abre e comeca a gravar na ultima linha, 
				//sem destruir o conteudo q ja estava nele
			try {
		   		bw = new BufferedWriter(new FileWriter("gerentes.txt", true));
			
			} catch (SecurityException securityException) {
				System.err.println("Você não tem permissao para gravar neste arquivo.");
//				System.exit( 1 );
	      
			} catch (FileNotFoundException fileNotFoundException) {
			    System.err.println( "Erro ao abrir ou criar um arquivo." );
//			    System.exit( 1 ); 
			    
			} catch (IOException e) {
//				e.printStackTrace();
				}
	   	
	 		verificador = 0;
	 		String mat  = " ", pri_nome  = " ", ult_nome  = " ", salarioB  = " ", numProjetosConcluidosMes  = " ", descGerenciais  = " " , salarioTot = " ";

	 		try {

	 			//IMPRIME O CABECALHO
	 			System.out.println("\n**************************************************************************************************************\n"
						+ "\t\t\t\t\tFOLHA DE PAGAMENTO\n\nPAGINA INICIAL > ADICIONAR GERENTE"
			     + "\n**************************************************************************************************************\n");
				
	 			// AQUI VOCE PODERIA RODAR UM ARRAY E DEPOIS USAR ELE PARA GRAVAR NO ARQUIVO 
	 			//
	 			System.out.print("Matricula: ");
				this.matricula = input.nextInt();
				System.out.printf("%s", "Primeiro Nome: ");
				this.primeiro_nome = input.next();
				System.out.printf("%s", "Ultimo nome: ");
				this.ultimo_nome = input.next();
				System.out.printf("%s", "Salario base: ");
				this.salarioBase = input.nextDouble();
				System.out.printf("%s", "Numero de projetos concluidos no mes: ");
				this.numeroProjetosConcluidosMes = input.nextInt();
				System.out.printf("%s", "Descontos gerenciais: ");
				this.descontosGerenciais = input.nextInt();
	 	   
	 	   		//CALCULA O SALARIO TOTAL
		 	   calculaSalarioTotal(this.numeroProjetosConcluidosMes, this.salarioBase, this.descontosGerenciais);
	   			
	   			//CONVERTE O VALOR DO SALARIO O TOTAL PARA STRING
		 	   salarioTot = String.valueOf(this.salarioTotal);	
	   			
	   			//CONVERTE TBM PARA STRING O Q PEGOU DA LINHA 84 ATE 95
				pri_nome = this.primeiro_nome;
				ult_nome = this.ultimo_nome;
				mat = String.valueOf(this.matricula);
				salarioB = String.valueOf(this.salarioBase);
				numProjetosConcluidosMes = String.valueOf(this.numeroProjetosConcluidosMes); 
				descGerenciais = String.valueOf(this.descontosGerenciais);
				
				//Se o usuario digitar, por exemplo, um caracter na parte da matricula, ele entra aqui
			} catch (InputMismatchException e) {
				   System.err.println("Entrada invalida. Tente novamente!");
				   verificador = 1;
//				   String buf;
//				   buf = input.nextLine();
				   
			} catch (NullPointerException e) {
				   System.err.println("Entrada invalida. Tente novamente!");
	 			   verificador = 1;
//				   String buf;
//				   buf = input.nextLine();
		    }
		   
		   
			if(verificador == 0) {
				try {
					//GRAVA DE MANEIRA FORMATADA OS DADOS NO ARQUIVO
					// 1141 Paulo Henrique 1000 2 ...
					bw.write(mat);
					bw.write(" ");
					bw.write(pri_nome);
					bw.write(" ");
					bw.write(ult_nome);
					bw.write(" ");
					bw.write(salarioB);
					bw.write(" ");
					bw.write(numProjetosConcluidosMes);
					bw.write(" ");
					bw.write(descGerenciais);
					bw.write(" ");
					bw.write(salarioTot);
	            
					bw.newLine();
					//FORCA A GRAVACAO
					bw.flush();
	            
				} catch (IOException e) {
//					e.printStackTrace();
				}
				
				try {
					bw.close();
				} catch (IOException e) {
					System.out.println("Erro ao fechar o arquivo");
				}
				
				System.out.printf("\n\n \t\t\t| Gerente %s %s adicionado com sucesso!", pri_nome, ult_nome);
			}//if 
			

			//ENQUANTO A REPOSTA FOR 1, VAI CONTINUAR NESSE WHILE E PORTANTO VAI CONTINUAR PEGADO DADOS DOS GERENTES
			//SE 0, VOLTA PARA O MENU PRINCIPAL
			Scanner resp = new Scanner (System.in);
			System.out.println("\n\nDigite 1 para adicionar outro funcionario ou 0 para voltar: ");
			resposta = resp.nextInt();

		} while (resposta == 1);

	} //gavraGerente
	

	//MOSTRA O CABECALHO DA PARTE QUE IMPRIME TODO O AQUIVO NO TEMINAL
	public void mostraCabecalho() {
		  System.out.println("\n**************************************************************************************************************\n"
					+ "\t\t\t\t\tFOLHA DE PAGAMENTO\n\nPAGINA INICIAL > LISTA DE GERENTES"
		     + "\n**************************************************************************************************************\n");
		  
		  System.out.printf( "%-15s ", "MATRICULA");
		  System.out.printf( "%-16s ", "NOME");
		  System.out.printf( "%-20s ",  "SOBRENOME");
		  System.out.printf( "%-15s ", "SALARIO BASE");
		  System.out.printf( "%-11s ", "N. PROJ.");
		  System.out.printf( "%-13s ",  "DESCONTOS");
		  System.out.printf( "%-13s\n", "SALARIO TOTAL");

	}

	//IMPRIME ARQUIVO NO TERMINAL
	public void mostraFuncionario() {
		
		  try {

		  		//ABRE O ARQUIVO
		     leitura_texto = new Scanner(new File("gerentes.txt"));
			 mostraCabecalho();

		  } //fim try
		  catch ( FileNotFoundException fileNotFoundException) {
		     System.err.println("Erro ao abrir o arquivo.");
		     //System.exit(1);
		  } // fim catch
		
		  try {
			 String nome, mat, sobrenome, salarioB, projConc, descG, salarioT;
			 
		     while ( leitura_texto.hasNext()) {

		     	//A CADA "leitura_texto.next()", ele pega um String, por isso a gravação foi formatada daqule jeito
		        mat = leitura_texto.next(); 
			 	nome = leitura_texto.next();
			 	sobrenome = leitura_texto.next();
			 	salarioB = leitura_texto.next();
			 	projConc = leitura_texto.next();
			 	descG = leitura_texto.next();
			 	salarioT = leitura_texto.next();
			 	
			 	//Imprime o q tem no arquivo
		        System.out.printf( "%-15s ", mat);
		        System.out.printf( "%-16s ", nome);
		        System.out.printf( "%-20s ", sobrenome);
		        System.out.printf( "%-15s ",  ("R$ "  +  salarioB));
		        System.out.printf( "%-11s ", projConc);
		        System.out.printf( "%-13s ", descG);
		        System.out.printf( "%-13s\n",  ("R$ "  +  salarioT));
		       	            
		     } // fim while
		  } catch ( NoSuchElementException elementException) {
		     
			  System.err.println( "Arquivo formatado incorretamente.");
		     leitura_texto.close();
		     //System.exit(1);
		     
		  } catch (IllegalStateException stateException) {
		     
			  System.err.println("Erro ao ler o arquivo.");
		     //System.exit(1);
		  } // end catch
		  catch (NullPointerException w) {
			     
			  System.err.println("Erro ao ler o arquivo.");
		     //System.exit(1);
		  } // end catch
		      
		      
	      if ( leitura_texto != null )
	    	  leitura_texto.close();
		      
	} // fim mostarFuncionario



	public void calculaSalarioTotal(int numProj, double salarioB, double descontosG) {
		double bonus;
		
		this.numeroProjetosConcluidosMes = numProj;
		this.salarioBase = salarioB;
		this.descontosGerenciais = descontosG;
		
		bonus = this.numeroProjetosConcluidosMes * this.salarioBase * 0.02; 
		
		this.salarioTotal = salarioBase + bonus - descontosG;	
	}

	//ESSE METODO SERVE PARA O USUARIO CALCULAR INDIVIDUALMENTE O SALARIO TOTAL DE UM FUNCIONARIO
	//E AÍ CHAMA O METODO ANTERIOR (calculaSalarioTotal)
	@Override
	public void calculaSalarioIndividual () {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Digite o numero de projetos concluidos no mes:");
		this.numeroProjetosConcluidosMes = input.nextInt();
		System.out.println("Digite o salario base: ");
		this.salarioBase = input.nextDouble();
		System.out.println("Digite o total de descontos gerenciais: ");
		this.descontosGerenciais = input.nextInt();
		
		this.calculaSalarioTotal(this.numeroProjetosConcluidosMes, this.salarioBase, this.descontosGerenciais);
		
		System.out.printf("\n\t\t\t| Salario total do funcionario: R$ %.2f |\n", this.getSalarioTotal());

	}
	
	
	//A PARTIR DAQUI, GETS E SETS
	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getPrimeiroNome() {
		return primeiro_nome;
	}

	public void setPrimeiroNome(String nome) {
		this.primeiro_nome = nome;
	}
	
	public String getUltimoNome() {
		return ultimo_nome;
	}

	public void setUltimoNome(String nome) {
		this.ultimo_nome = nome;
	}

	public double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}

	public int getNumeroProjetosConcluidosMes() {
		return numeroProjetosConcluidosMes;
	}

	public void setNumeroProjetosConcluidosMes(int numeroProjetosConcluidosMes) {
		this.numeroProjetosConcluidosMes = numeroProjetosConcluidosMes;
	}

	public double getDescontosGerenciais() {
		return descontosGerenciais;
	}

	public void setDescontosGerenciais(double descontosGerenciais) {
		this.descontosGerenciais = descontosGerenciais;
	}

	public double getSalarioTotal() {
		return salarioTotal;
	}

	public void setSalarioTotal(double salarioTotal) {
		this.salarioTotal = salarioTotal;
	}
}
