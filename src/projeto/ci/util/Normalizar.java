package projeto.ci.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @author Manoel
 * Classe que faz a normalização os dados.
 */
public class Normalizar {

	public void normaliza(String file1) throws IOException {
		// Ler o arquivo que será normalizado
		BufferedReader dataBR = new BufferedReader(new FileReader(new File(file1)));

		// Arquivo que irá gravar os dados normalizados
		PrintWriter pw = new PrintWriter(new File("data/2014_2015_2016_efetivos_normalizado.csv"));

		StringBuilder sbNormalizado = new StringBuilder();

		String line = "";
		ArrayList<String[]> dataArr = new ArrayList<String[]>();
		Random r = new Random();

		while ((line = dataBR.readLine()) != null) {
			String[] value = line.split(";");
			dataArr.add(value);
		}

		double[][] dados = new double[dataArr.size()][dataArr.get(0).length],
				dadosNormalizados = new double[dataArr.size()][dataArr.get(0).length];
		double min[] = new double[dataArr.get(0).length], max[] = new double[dataArr.get(0).length];

		for (int x = 0; x < min.length; x++) {
			min[x] = r.nextDouble() * 10000;
		}

		for (int i = 0; i < dataArr.size(); i++) {
			for (int x = 0; x < dataArr.get(i).length; x++) {
				dados[i][x] = Double.parseDouble(dataArr.get(i)[x].replace(',', '.'));
			}
		}

		/*
		 * Min e Max
		 */
		// Colunas
		for (int x = 0; x < dataArr.get(0).length; x++)
			// Linhas
			for (int i = 0; i <= dataArr.size() - 1; i++) {
				if (dados[i][x] < min[x]) {
					min[x] = dados[i][x];
				}
				if (dados[i][x] > max[x]) {
					max[x] = dados[i][x];
				}
			}

		// Stringbuilder para inserir os dados normalizados
		for (int l = 0; l < dataArr.size(); l++) {
			for (int c = 0; c < dataArr.get(0).length; c++) {
				dadosNormalizados[l][c] = (dados[l][c] - min[c]) / (max[c] - min[c]);
				sbNormalizado.append((double) (dados[l][c] - min[c]) / (max[c] - min[c]));
				
				if (dataArr.get(0).length - 1 != c)
					sbNormalizado.append(";");

			}
			sbNormalizado.append("\n");
		}

		 System.out.println(Arrays.toString(min));
		 System.out.println(Arrays.toString(max));
		// System.out.println(Arrays.toString(dadosNormalizados[0]));
		//

		pw.write(sbNormalizado.toString());
		pw.close();
		dataBR.close();
	}

}
