package br.ufpe.cin.crawler.docente.saida;

import br.ufpe.cin.crawler.ISaida;
import br.ufpe.cin.crawler.Config.TipoSaida;
import br.ufpe.cin.crawler.docente.Docente;

public class DocenteSaidaFactory {
	
	public static ISaida<Docente> criarSaida(TipoSaida tipoSaida){
		switch (tipoSaida) {
		case xml:
			return new SaidaXML();
		case json:
			return new SaidaJson();
		case excel:
			return new SaidaExcel();
		default:
			throw new UnsupportedOperationException();
		}
	}

}
