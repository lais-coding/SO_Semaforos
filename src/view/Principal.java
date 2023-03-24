package view;

import java.util.concurrent.Semaphore;

import controller.CompraIngresso;

public class Principal {

	public static void main (String args[]) {
		
		int permissao = 1; //Permissão de 1 de cada vez
		Semaphore semaforo = new Semaphore(permissao); 
		int ingressos = 100;
		
		//ALTERAR PARA 300
		for(int comprador = 0; comprador <= 300; comprador++) {
			Thread comprarIngresso = new CompraIngresso (comprador, semaforo);
			comprarIngresso.start();
		}
		
	}

	
}
