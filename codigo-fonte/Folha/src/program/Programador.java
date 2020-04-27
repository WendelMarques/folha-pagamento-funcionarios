package program;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.Reader;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Programador extends Gerente{
	
	Scanner input = new Scanner (System.in);
	 	
	public Programador() {
		this(0, "", "", 0.0, 0);				
	}

	public Programador(int matricula, String primeiro_nome, String ultimo_nome, double salarioB, int numeroProjetosConcluidosMes) {
		setMatricula(matricula);
		setPrimeiroNome(primeiro_nome);
		setSalarioBase(salarioB);
		setNumeroProjetosConcluidosMes(numeroProjetosConcluidosMes);
	}
	
	public void gravaProgramador () {
		
		int resposta;

		do {
			BufferedWriter bw = null;
			int verificador = 0;
				
			try {
		   		bw = new BufferedWriter(new FileWriter("programadores.txt", true));
			
			} catch (SecurityException securityException) {
					System.err.println("Você não tem permissao para gravar neste arquivo.");
					//System.exit( 1 );
		      
				} catch (FileNotFoundException fileNotFoundException) {
				    System.err.println( "Erro ao abrir ou criar um arquivo." );
				    //System.exit( 1 ); 
				    
				} catch (IOException e) {
					//e.printStackTrace();
			 }
	   	
	 
	 		verificador = 0;
	 		String mat  = " ", pri_nome  = " ", ult_nome  = " ", salarioB  = " ", numProjetosConcluidosMes  = " ", salarioTot = " ";
	
	 		try {
	 			
	 			System.out.println("\n**************************************************************************************************************\n"
						+ "\t\t\t\t\tFOLHA DE PAGAMENTO\n\nPAGINA INICIAL > ADICIONAR PROGRAMADOR"
			     + "\n**************************************************************************************************************\n");
				
	 			
	 			System.out.print("Matricula:");
				this.matricula = input.nextInt();
				System.out.printf("%s", "Primeiro Nome:");
				this.primeiro_nome = input.next();
				System.out.printf("%s", "Ultimo nome:");
				this.ultimo_nome = input.next();
				System.out.printf("%s", "Salario base:");
				this.salarioBase = input.nextDouble();
				System.out.printf("%s", "Numero de projetos concluidos no mes:");
				this.numeroProjetosConcluidosMes = input.nextInt();
	 
	 	   
	 	 	    calculaSalarioTotal(this.numeroProjetosConcluidosMes, this.salarioBase);
		 	    salarioTot = String.valueOf(this.salarioTotal);	
	   
				pri_nome = this.primeiro_nome;
				ult_nome = this.ultimo_nome;
				mat = String.valueOf(this.matricula);
				salarioB = String.valueOf(this.salarioBase);
				numProjetosConcluidosMes = String.valueOf(this.numeroProjetosConcluidosMes); 
				
			} catch (InputMismatchException e) {
				   System.err.println("Entrada invalida. Tente novamente!");
				   verificador = 1;
	//			   String buf;
	//			   buf = input.nextLine();
				   
			} catch (NullPointerException e) {
				   System.err.println("Entrada invalida. Tente novamente!");
	 			   verificador = 1;
	//			   String buf;
	//			   buf = input.nextLine();
		    }
		   
		   
			if(verificador == 0) {
				try {
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
					bw.write(salarioTot);
	            
					bw.newLine();
					bw.flush();
	            
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					bw.close();
				} catch (IOException e) {
					System.out.println("Erro ao fechar o arquivo.");
	
				}
				
				System.out.printf("\n\n \t\t\t| Programador %s %s adicionado com sucesso!", pri_nome, ult_nome);
	
			}//if 
			Scanner resp = new Scanner (System.in);
			System.out.println("\n\nDigite 1 para adicionar outro funcionario ou 0 para voltar: ");
			resposta = resp.nextInt();
			
		} while (resposta == 1);
		
		
		
	
	} //gavraGerente

	@Override
	public void mostraCabecalho() {
		  System.out.println("\n**************************************************************************************************************\n"
					+ "\t\t\t\t\tFOLHA DE PAGAMENTO\n\nPAGINA INICIAL > LISTA DE PROGRAMADORES"
		     + "\n**************************************************************************************************************\n");
		  
		  System.out.printf( "%-15s ", "MATRICULA");
		  System.out.printf( "%-16s ", "NOME");
		  System.out.printf( "%-20s ",  "SOBRENOME");
		  System.out.printf( "%-15s ", "SALARIO BASE");
		  System.out.printf( "%-11s ", "N. PROJ.");
		  System.out.printf( "%-13s\n", "SALARIO TOTAL");

	}
	
	@Override
	public void mostraFuncionario() {
				  
		  try {
		     leitura_texto = new Scanner(new File("programadores.txt"));
			 mostraCabecalho();

		  } //fim try
		  catch ( FileNotFoundException fileNotFoundException) {
		     System.err.println("Arquivo nao encontrado!");
		     //System.exit(1);
		  } // fim catch
		
		  try {
			 String nome, mat, sobrenome, salarioB, projConc, descG, salarioT;
			 

		     while ( leitura_texto.hasNext()) {
		        mat = leitura_texto.next(); 
			 	nome = leitura_texto.next();
			 	sobrenome = leitura_texto.next();
			 	salarioB = leitura_texto.next();
			 	projConc = leitura_texto.next();
			 	salarioT = leitura_texto.next();
			 	
		        System.out.printf( "%-15s ", mat);
		        System.out.printf( "%-16s ", nome);
		        System.out.printf( "%-20s ", sobrenome);
		        System.out.printf( "%-15s ",  ("R$ "  +  salarioB));
		        System.out.printf( "%-11s ", projConc);
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

	
	public void calculaSalarioTotal(int numProj, double salarioB) {
		double bonus;
		
		this.numeroProjetosConcluidosMes = numProj;
		this.salarioBase = salarioB;
		
		bonus = this.numeroProjetosConcluidosMes * this.salarioBase * 0.01; 
		
		this.salarioTotal = salarioBase + bonus;	
	}
	
	@Override
	public void calculaSalarioIndividual () {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Digite o numero de projetos concluidos no mes:");
		this.numeroProjetosConcluidosMes = input.nextInt();
		System.out.println("Digite o salario base: ");
		this.salarioBase = input.nextDouble();
		
		this.calculaSalarioTotal(this.numeroProjetosConcluidosMes, this.salarioBase);
		
		System.out.printf("\n\t\t\t| Salario total do funcionario: R$ %.2f |\n", this.getSalarioTotal());

	}

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
