package br.ufpe.cin.crawler.docente;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.select.Elements;

class DocenteWebpage {

	/**
	 * Através do conteúdo da página html, busca as informações do professor e
	 * cria uma entidade {@link br.ufpe.cin.crawler.docente.Docente} com os
	 * dados encontrados. Por fim, o adiciona na lista de professores estática
	 * "docentes"
	 * 
	 * @author Filipe (fmca)
	 * @param html
	 *            String com o conteúdo no formato HTML da página de detalhes
	 *            sobre um docente encontrado pelo Crawler
	 * @see DocenteCrawler
	 * 
	 */
	public static Docente descreverDocente(String html) throws IOException {

		Docente docente = null;
		Document doc = null;

		doc = Jsoup.parse(html, "UTF-8");

		doc.outputSettings().escapeMode(EscapeMode.xhtml);

		Elements conteudo = doc.getElementsByTag("a");
		Element conteudoPrincipal = doc.getElementsByClass("conteudo").first();

		if (conteudoPrincipal != null) {
			Element imagem = conteudoPrincipal.getElementsByTag("img").first();
			Element nomeH3 = conteudoPrincipal.getElementsByTag("h3").first();
			String urlImagem = "";

			if (imagem != null) {
				urlImagem = imagem.attr("src");
			}

			String nomeProfessor = nomeH3.text();
			String[] strings = conteudoPrincipal.html().split("<br />");

			String fone = "";
			String fax = "";
			String areas = "";
			String sala = "";

			for (int j = 0; j < strings.length; j++) {

				strings[j] = Jsoup.parse(strings[j]).text()
						.replace("�reas de Interesse:", "")
						.replace("�reas de Interesse", "");

				/*
				 * strings[j] = strings[j].trim().replace("<div>", "")
				 * .replace("</div>", "") .replace("<font color=\"#000000\">",
				 * "").trim() .replace("</font>", "")
				 * .replace("�reas de Interesse", "").replace("<em>", "")
				 * .replace("</em>", "").trim().replace("\n",
				 * " ").replace("<b>", "");
				 */
				if (strings[j].contains("Fone:")) {
					fone = strings[j].substring(6);
				} else if (strings[j].contains("Fax:")) {
					fax = strings[j].substring(5);
				} else if (strings[j].contains("Sala:")) {
					sala = strings[j].substring(6);
				} else {

					if (strings[j].contains("<")
							|| strings[j].contains("P�gina")
							|| strings[j].contains("Email")
							|| strings[j].contains("E-mail")
							|| strings[j].contains("Curr�culo")
							|| strings[j].contains("Lattes")
							|| strings[j].contains("Gradua��o")
							|| strings[j].contains("Professores")) {

						// NADA
					} else {
						if (!strings[j].isEmpty()) {
							if (!areas.isEmpty()) {
								areas += ", ";
							}

							areas += strings[j];
						}
					}
				}

			}

			if (!nomeProfessor.isEmpty() && !fone.isEmpty()) {

				String lattes = conteudo.get(conteudo.size() - 4).attr("href");
				String email = conteudo.get(conteudo.size() - 3).text();
				String pagina = conteudo.get(conteudo.size() - 2).text();

				docente = new Docente(nomeProfessor, urlImagem, areas, fone,
						fax, pagina, email, lattes, sala);

			}
		}

		return docente;

	}
}
