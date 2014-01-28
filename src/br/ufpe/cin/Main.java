package br.ufpe.cin;

import br.ufpe.cin.config.Propriedades;
import br.ufpe.cin.config.Valores;
import br.ufpe.cin.saida.ISaida;
import br.ufpe.cin.saida.SaidaFactory;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Main {

	public static void main(String[] args) throws Exception {

		// Lê do arquivo config.properties qual deverá ser a saída (Somente
		// excel por enquanto)
		String parametroFormatoSaida = Propriedades
				.lerPropriedade(Valores.saida.name());
		ISaida formatoSaida = SaidaFactory.criarSaida(parametroFormatoSaida);

		// Parte principal da url que contém a lista de professores do CIn
		String urlPaginaProfessores = Propriedades
				.lerPropriedade(Valores.urlPaginaProfessores.name());
		// Número de páginas que contém links para mais detalhes de cada
		// professor
		int numPaginasProfessores = Integer.decode(Propriedades
				.lerPropriedade(Valores.maxNumPagina.name()));
		int min = Integer.decode(Propriedades
				.lerPropriedade(Valores.minNumPagina.name()));

		// Configurações do crawler (atualmente, 7 threads)
		String crawlStorageFolder = "/data/crawl/root";
		int numberOfCrawlers = Integer.decode(Propriedades
				.lerPropriedade(Valores.numCrawlers.name()));

		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);

		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher,
				robotstxtServer);

		for (int i = min; i <= numPaginasProfessores; i++) {
			controller.addSeed(urlPaginaProfessores + i);
		}

		// Começa o processo de rastrear informações
		controller.start(CInProfessoresCrawler.class, numberOfCrawlers);

		// Após buscar todas as informações, imprime as informações dos
		// professores no formato de arquivo escolhido
		formatoSaida.imprimir(DescritorProfessores.professores);
	}
}
