package br.ufpe.cin.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Propriedades {

	public static String lerPropriedade(String propriedade) {

		Properties prop = new Properties();
		InputStream input = null;
		String retorno = null;

		try {

			input = new FileInputStream("config.properties");

			prop.load(input);

			retorno = prop.getProperty(propriedade);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return retorno;

	}
}