package br.ufpe.cin.crawler.docente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import br.ufpe.cin.crawler.Config;
import br.ufpe.cin.crawler.utils.Log;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class DocenteCrawler extends WebCrawler {

	public static List<Docente> docentes = new ArrayList<Docente>();

	public DocenteCrawler() {
	}

	private final static Pattern FILTERS = Pattern
			.compile(".*(\\.(css|js|bmp|gif|jpe?g"
					+ "|png|tiff?|mid|mp2|mp3|mp4"
					+ "|wav|avi|mov|mpeg|ram|m4v|pdf"
					+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	@Override
	public boolean shouldVisit(WebURL url) {
		Config config = ((Config) getMyController().getCustomData());
		String href = url.getURL().toLowerCase();
		return !FILTERS.matcher(href).matches()
				&& (href.contains(config.getVisitar().toLowerCase()) || href.contains(config.getFiltrar().toLowerCase())) ;
	}

	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		Log.d("Visitando URL: " + url);
		Config config = ((Config) getMyController().getCustomData());
		if (url.toLowerCase().contains(config.getFiltrar().toLowerCase())) {
			Log.d("Processando URL: " + url);

			if (page.getParseData() instanceof HtmlParseData) {

				HtmlParseData htmlParseData = (HtmlParseData) page
						.getParseData();
				String html = htmlParseData.getHtml();

				try {
					Docente docente = DocenteWebpage.descreverDocente(html);
					if (docente != null) {
						docentes.add(docente);
					} else {
						Log.d("#visit() Docente null - " + url);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}
}