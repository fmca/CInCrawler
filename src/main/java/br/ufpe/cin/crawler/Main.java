package br.ufpe.cin.crawler;

import java.io.File;
import java.nio.file.Files;

import com.beust.jcommander.JCommander;

import br.ufpe.cin.crawler.docente.Docente;
import br.ufpe.cin.crawler.docente.DocenteCrawler;
import br.ufpe.cin.crawler.docente.saida.DocenteSaidaFactory;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Main {

	public static void main(String[] args) throws Exception {

		Config config = new Config();
		JCommander jCommander = new JCommander(config, args);
		
		if(config.isHelp()){
			jCommander.usage();
		}else{
			String pastaArmazenamentoCrawl = Files.createTempDirectory(
					new File(System.getProperty("java.io.tmpdir")).toPath(),
					"cincrawler").toString();
			

			CrawlConfig crawlConfig = new CrawlConfig();
			crawlConfig.setCrawlStorageFolder(pastaArmazenamentoCrawl);

			PageFetcher pageFetcher = new PageFetcher(crawlConfig);
			RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
			RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
					pageFetcher);
			CrawlController controller = new CrawlController(crawlConfig,
					pageFetcher, robotstxtServer);

			controller.addSeed(config.getUrl());

			switch (config.getEntidade()) {
			case docente:
				controller.setCustomData(config);
				controller.start(DocenteCrawler.class, config.getNumCrawlers());
				ISaida<Docente> saida = DocenteSaidaFactory.criarSaida(config.getTipoSaida());
				saida.imprimir(DocenteCrawler.docentes);
				break;
			default:
				throw new UnsupportedOperationException();
			}
		}
		
		
		
	}
}
