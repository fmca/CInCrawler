package br.ufpe.cin.saida;

import java.util.List;

import br.ufpe.cin.entidade.Professor;


public interface ISaida {

	public void imprimir(List<Professor> professores);
}
