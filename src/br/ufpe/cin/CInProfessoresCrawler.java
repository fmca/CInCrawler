package br.ufpe.cin;

import java.io.IOException;
import java.util.regex.Pattern;

import br.ufpe.cin.config.Propriedades;
import br.ufpe.cin.config.Valores;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class CInProfessoresCrawler extends WebCrawler {

	private String	parametroVisitarPagina;

	public CInProfessoresCrawler() {
		this.init();

	}

	/**
	 * Lê das propriedades String que filtra os links externos encontrados pelo
	 * Crawler. Somente irá explorar páginas as quais os links contém
	 * (.contains) esse parâmetro em suas URLs
	 * 
	 * @author Filipe (fmca)
	 */
	private void init() {
		this.parametroVisitarPagina = Propriedades
				.lerPropriedade(Valores.parametroVisitarPagina.name());
	}

	private final static Pattern	FILTERS	= Pattern
													.compile(".*(\\.(css|js|bmp|gif|jpe?g"
															+ "|png|tiff?|mid|mp2|mp3|mp4"
															+ "|wav|avi|mov|mpeg|ram|m4v|pdf"
															+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	/**
	 * Somente visita e explora as páginas linkadas que contém
	 * 
	 * @author Filipe (fmca)
	 * */
	@Override
	public boolean shouldVisit(WebURL url) {
		String href = url.getURL().toLowerCase();
		return !FILTERS.matcher(href).matches()
				&& href.contains(this.parametroVisitarPagina);
	}

	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		System.out.println("Visitando URL: " + url);

		if (page.getParseData() instanceof HtmlParseData) {

			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String html = htmlParseData.getHtml();

			try {
				DescritorProfessores.descreverProfessorDaPagina(html);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}