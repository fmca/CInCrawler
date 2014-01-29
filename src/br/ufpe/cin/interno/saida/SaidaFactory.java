package br.ufpe.cin.interno.saida;

import br.ufpe.cin.interno.config.Valores;

public class SaidaFactory {

	public static ISaida criarSaida(String parametroArquivoConfig) {

		ISaida saida = null;
		if (parametroArquivoConfig.equalsIgnoreCase(Valores.excel.name())) {
			saida = new Excel();
		} else if (parametroArquivoConfig.equalsIgnoreCase(Valores.json.name())) {
			saida = new Json();
		} else if (parametroArquivoConfig.equalsIgnoreCase(Valores.xml.name())) {
			saida = new XML();
		}

		return saida;
	}
}
