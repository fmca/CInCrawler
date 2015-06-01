package br.ufpe.cin.crawler;

import com.beust.jcommander.Parameter;

public class Config {
	
	@Parameter(names = {"-h", "--help", "--h"}, help = true)
    private boolean help = false;
	
	public enum Entidade{ docente}
	@Parameter(names={"-e", "-entidade"}, description="Qual entidade o crawler deve gerar. (apenas docentes implementado)")
	private Entidade entidade = Entidade.docente;
	
	public enum TipoSaida { xml, json, excel}
	@Parameter(names={"-s", "-saida"}, description="Tipo de saída.")
	private TipoSaida tiposaida = TipoSaida.xml;
	
	@Parameter(names={"-u", "-url"}, description="Url a ser visitada")
	private String url = "http://www2.cin.ufpe.br/";
	
	@Parameter(names={"-v", "-visitar"}, description="String que a url deve conter para ser visitada", required=true)
	private String visitar;
	
	@Parameter(names={"-f", "-filtrar"}, description="String que a url deve conter para ser processada", required=true)
	private String filtrar;
	
	@Parameter(names={"-c", "-crawlers"}, description="Número de crawlers concorrentes")
	private int numCrawlers = 7;

	public Entidade getEntidade() {
		return entidade;
	}

	public TipoSaida getTipoSaida() {
		return tiposaida;
	}

	public String getUrl() {
		return url;
	}

	public String getFiltrar() {
		return filtrar;
	}

	public int getNumCrawlers() {
		return numCrawlers;
	}

	public boolean isHelp() {
		return help;
	}

	public String getVisitar() {
		return visitar;
	}

}
