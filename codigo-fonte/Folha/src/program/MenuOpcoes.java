package program;


public enum MenuOpcoes {
	
	LISTAR_ESTAGIAROS( 1 ),
	LISTAR_PROGRAMADORES(2),
	LISTAR_GERENTES(3),
	CALCULAR_SALARIO_TOTAL(4),
	ADD_GERENTE(5),
	ADD_PROGRAMADOR(6),
	ADD_ESTAGIARIO(7),
	FINALIZA_APP(8);
	
	private final int valor;
	
	MenuOpcoes(int valorOpcao) {
		valor = valorOpcao;
	}
	
	public int getValor () {
		return valor;
	}
}
