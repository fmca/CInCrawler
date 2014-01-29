package br.ufpe.cin.interno.saida;

import java.util.List;

import br.ufpe.cin.interno.entidade.Professor;


public interface ISaida {

	public void imprimir(List<Professor> professores);
}
