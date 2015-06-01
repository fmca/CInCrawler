package br.ufpe.cin.crawler.docente.saida;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.ufpe.cin.crawler.ISaida;
import br.ufpe.cin.crawler.docente.Docente;
import br.ufpe.cin.crawler.docente.Docente.Atributos;
import br.ufpe.cin.crawler.utils.Log;

class SaidaXML implements ISaida<Docente> {

	@Override
	public void imprimir(List<Docente> docentes) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();

			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element professoresXML = doc.createElement("lista-"+Docente.class.getName());
			doc.appendChild(professoresXML);

			for (Docente p : docentes) {
				Element profXML = doc.createElement(Docente.class.getName());

				profXML.setAttribute(Atributos.nome.name(), p.getNome());
				profXML.setAttribute(Atributos.email.name(), p.getEmail());
				profXML.setAttribute(Atributos.pagina.name(), p.getPagina());

				Element contatoXML = doc.createElement("contato");
				profXML.appendChild(contatoXML);

				Element foneXML = doc.createElement(Atributos.fone.name());
				foneXML.setTextContent(p.getFone());
				contatoXML.appendChild(foneXML);

				Element faxXML = doc.createElement(Atributos.fax.name());
				faxXML.setTextContent(p.getFax());
				contatoXML.appendChild(faxXML);

				Element salaXML = doc.createElement(Atributos.sala.name());
				salaXML.setTextContent(p.getSala());
				contatoXML.appendChild(salaXML);

				Element lattesXML = doc.createElement(Atributos.lattes.name());
				lattesXML.setAttribute("url", p.getLattes());
				contatoXML.appendChild(lattesXML);

				Element areasXML = doc.createElement(Atributos.areasInteresse.name());
				for (String a : p.getAreasInteresse()) {
					Element area = doc.createElement("area");
					area.setAttribute(Atributos.nome.name(), a);
					areasXML.appendChild(area);
				}
				profXML.appendChild(areasXML);
				professoresXML.appendChild(profXML);
			}

			String nomeArquivo = Docente.class.getName() + ".xml";
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(nomeArquivo));

			transformer.transform(source, result);

			Log.i(nomeArquivo + " finalizado!");

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
