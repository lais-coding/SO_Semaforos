package controller;

import java.util.concurrent.Semaphore;

public class CompraIngresso extends Thread {
	
	private int comprador;
	private Semaphore semaforo;
	static int ingressos = 100;
	

	public CompraIngresso(int comprador, Semaphore semaforo) {
		this.comprador = comprador;
		this.semaforo = semaforo; 
	}

	

	public void run() {
		
		LoginSistema();
		ProcessoCompra();
		
		//Início da seção crítica (1 comprador por vez)
		try {
			semaforo.acquire();
			ValidacaoCompra();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			semaforo.release();
		}
		//Fim da seção crítica
		
		
		}
	

	private void LoginSistema() {
		
		int TempoLogin = (int) ((Math.random() * 51) + 2000); //Tempo de 50 milisegundos a 2 segundos
		
		if(TempoLogin > 1000) { //Se tempo de login for superior a 1 segundo, então...
			System.out.println("# " +comprador+ " não conseguiu fazer login - \n TIMEOUT == " +TempoLogin);
			System.out.println(" ");
		}
		else {
			ProcessoCompra();
		}
		
	}

	private void ProcessoCompra() {
		
		int TempoCompra = (int) ((Math.random() * 2001) + 1000); // Tempo de compra de 1 a 3 segundos
		
		if(TempoCompra > 2500) {
			System.out.println("# " +comprador+ " não poderá fazer a compra - \n ESTOURO DE SESSÃO == " +TempoCompra);
			System.out.println(" ");
		
		}
		else {
			ValidacaoCompra();
		}
		
	}

	private void ValidacaoCompra() {
	
		if (ingressos > 0) {
			int compra = (int) ((Math.random() * 4) + 1);
			ingressos = ingressos - compra;
			System.out.println("# " +comprador+ " comprou " +compra+ " ingresso(s). \n INGRESSOS DISPONÍVEIS: " +ingressos);
			System.out.println(" ");
		}
		else {
			System.out.println("Não foi possível efetuar a compra \nINGRESSOS DISPONÍVEIS: " +ingressos);
		
		}
	
}
	
}
