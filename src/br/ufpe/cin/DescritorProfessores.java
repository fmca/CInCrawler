package br.ufpe.cin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.select.Elements;

import br.ufpe.cin.entidade.Professor;

public class DescritorProfessores {

	static List<Professor>	professores	= new ArrayList<Professor>();

	public static List<Professor> getProfessores() {
		return professores;
	}

	public static void setProfessores(List<Professor> professores) {
		DescritorProfessores.professores = professores;
	}

	public static void addProfessor(Professor professor) {
		professores.add(professor);
	}

	/**
	 * Atrav�s do conte�do da p�gina html, busca as informa��es do professor e
	 * cria uma entidade {@link br.ufpe.cin.Professor} com os dados encontrados.
	 * Por fim, o adiciona na lista de professores est�tica "professores"
	 * 
	 * @author Filipe (fmca)
	 * @param html
	 *            String com o conte�do no formato HTML da p�gina de detalhes
	 *            sobre um professor encontrado pelo Crawler
	 * @see CInProfessoresCrawler
	 * 
	 */
	public static void descreverProfessorDaPagina(String html)
			throws IOException {

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

				strings[j] = strings[j].trim().replace("<div>", "")
						.replace("</div>", "")
						.replace("<font color=\"#000000\">", "").trim()
						.replace("</font>", "")
						.replace("�reas de Interesse", "").replace("<em>", "")
						.replace("�reas de Interesse", "").replace("</em>", "")
						.trim().replace("\n", " ");

				if (strings[j].contains("Fone:")) {
					fone = strings[j].substring(6);
				} else if (strings[j].contains("Fax:")) {
					fax = strings[j].substring(5);
				} else if (strings[j].contains("Sala:")) {
					sala = strings[j].substring(6);
				} else {

					if (!strings[j].contains("<")
							&& !strings[j].trim().equals(":")) {
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

				Professor prof = new Professor(nomeProfessor, urlImagem, areas,
						fone, fax, pagina, email, lattes, sala);

				addProfessor(prof);

			}
		} else {
			System.out.println("null");
		}

	}
}
