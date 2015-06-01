package br.ufpe.cin.crawler.docente.saida;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.ufpe.cin.crawler.ISaida;
import br.ufpe.cin.crawler.docente.Docente;
import br.ufpe.cin.crawler.docente.Docente.Atributos;
import br.ufpe.cin.crawler.utils.Log;

class SaidaJson implements ISaida<Docente> {

	@Override
	public void imprimir(List<Docente> docentes) {
		JSONArray json = new JSONArray();

		for (Docente p : docentes) {
			JSONObject profJson = new JSONObject();

			try {
				profJson.put(Atributos.nome.name(), p.getNome());
				profJson.put(Atributos.email.name(), p.getEmail());
				profJson.put(Atributos.lattes.name(), p.getLattes());
				profJson.put(Atributos.sala.name(), p.getSala());
				profJson.put(Atributos.pagina.name(), p.getPagina());
				profJson.put(Atributos.fone.name(), p.getFone());
				profJson.put(Atributos.fax.name(), p.getFax());

				JSONArray areasJson = new JSONArray();
				for (String area : p.getAreasInteresse()) {
					JSONObject areaJson = new JSONObject();
					areaJson.put(Atributos.areasInteresse.name(), area);
					areasJson.put(areaJson);
				}

				profJson.put(Atributos.areasInteresse.name(), areasJson);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			json.put(profJson);

		}

		try {

			String nomeArquivo = Docente.class.getName() + ".json";
			FileWriter file = new FileWriter(nomeArquivo);
			json.write(file);
			file.flush();
			file.close();
			Log.i(nomeArquivo + " finalizado!");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
