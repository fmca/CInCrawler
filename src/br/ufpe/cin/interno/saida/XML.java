package br.ufpe.cin.interno.saida;

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

import br.ufpe.cin.interno.config.Propriedades;
import br.ufpe.cin.interno.config.Valores;
import br.ufpe.cin.interno.entidade.Professor;

public class XML implements ISaida {

	@Override
	public void imprimir(List<Professor> professores) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();

			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element professoresXML = doc.createElement(Valores.professores
					.name());
			doc.appendChild(professoresXML);

			for (Professor p : professores) {
				Element profXML = doc.createElement(Valores.professor.name());

				profXML.setAttribute(Valores.nome.name(), p.getNome());
				profXML.setAttribute(Valores.email.name(), p.getEmail());
				profXML.setAttribute(Valores.url.name(), p.getPagina());

				Element contatoXML = doc.createElement(Valores.contato.name());
				profXML.appendChild(contatoXML);

				Element foneXML = doc.createElement(Valores.fone.name());
				foneXML.setTextContent(p.getFone());
				contatoXML.appendChild(foneXML);

				Element faxXML = doc.createElement(Valores.fax.name());
				faxXML.setTextContent(p.getFax());
				contatoXML.appendChild(faxXML);

				Element salaXML = doc.createElement(Valores.sala.name());
				salaXML.setTextContent(p.getSala());
				contatoXML.appendChild(salaXML);

				Element lattesXML = doc.createElement(Valores.lattes.name());
				lattesXML.setAttribute(Valores.url.name(), p.getLattes());
				contatoXML.appendChild(lattesXML);

				Element areasXML = doc.createElement(Valores.areas.name());
				for (String a : p.getAreasInteresse()) {
					Element area = doc.createElement(Valores.area.name());
					area.setAttribute(Valores.nome.name(), a);
					areasXML.appendChild(area);
				}
				profXML.appendChild(areasXML);
				professoresXML.appendChild(profXML);
			}

			String nomeArquivo = Propriedades
					.lerPropriedade(Valores.nomeArquivoSaida.name());
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(nomeArquivo
					+ ".xml"));

			transformer.transform(source, result);

			System.out.println("Arquivo XML escrito com êxito!");

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
