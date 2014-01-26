package br.ufpe.cin;

import java.io.IOException;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class CInProfessoresCrawler extends WebCrawler {

	private final static Pattern	FILTERS	= Pattern
													.compile(".*(\\.(css|js|bmp|gif|jpe?g"
															+ "|png|tiff?|mid|mp2|mp3|mp4"
															+ "|wav|avi|mov|mpeg|ram|m4v|pdf"
															+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	@Override
	public boolean shouldVisit(WebURL url) {
		String href = url.getURL().toLowerCase();
		return !FILTERS.matcher(href).matches() && href.contains("lercontato");
	}

	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		System.out.println("URL: " + url);

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