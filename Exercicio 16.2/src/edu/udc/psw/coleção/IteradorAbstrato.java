package edu.udc.psw.cole��o;

public interface IteradorAbstrato<E> {
	public boolean proximo();
	public boolean anterior();
	public E dadoAtual();
}