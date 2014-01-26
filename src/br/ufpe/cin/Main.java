package br.ufpe.cin;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Main {

	public static String	urlPaginaProfessores	= "http://www2.cin.ufpe.br/site/listaContatos.php?s=1&c=8&p=";

	public static void main(String[] args) throws Exception {
		String crawlStorageFolder = "/data/crawl/root";
		int numberOfCrawlers = 7;

		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);

		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher,
				robotstxtServer);

		int numPaginasProfessores = 6;
		int min = 0;

		for (int i = min; i < numPaginasProfessores; i++) {
			controller.addSeed(urlPaginaProfessores + i);
		}

		controller.start(CInProfessoresCrawler.class, numberOfCrawlers);

		Excel.escreverExcel(DescritorProfessores.professores);
	}
}
