package br.ufpe.cin.saida;

import br.ufpe.cin.config.Valores;

public class SaidaFactory {

	public static ISaida criarSaida(String parametroArquivoConfig) {
		
		ISaida saida = null;
		
		if (parametroArquivoConfig.equalsIgnoreCase(Valores.excel.name())) {
			saida = new Excel();
		}
		
		return saida;
	}
}
