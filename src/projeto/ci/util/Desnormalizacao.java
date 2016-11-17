package projeto.ci.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import projeto.ci.ga.Individuo;

public class Desnormalizacao {

	public Individuo Desnormaliza(Individuo individuo) throws IOException {

		// Ler o arquivo que será normalizado
		BufferedReader dataBR = new BufferedReader(new FileReader(new File("data/max_min.csv")));

		String line = "";
		ArrayList<String[]> dataArr = new ArrayList<String[]>();

		while ((line = dataBR.readLine()) != null) {
			String[] value = line.split(";");
			dataArr.add(value);
		}

		double[][] dados = new double[dataArr.size()][dataArr.get(0).length];

		for (int i = 0; i < dataArr.size(); i++) {
			for (int x = 0; x < dataArr.get(i).length; x++) {
				dados[i][x] = Double.parseDouble(dataArr.get(i)[x].replace(',', '.'));
			}
		}

		// dados[0] - Min
		// dados[1] - Max
		//for (int i = 0; i < dataArr.size(); i++) {
			for (int j = 0; j < individuo.getGenes().length; j++) {
				individuo.setGenesPosicao(j, ((dados[1][j] - dados[0][j]) * individuo.getIndexGenes(j)) + dados[0][j]);
			}
		//}

		dataBR.close();
		return individuo;

	}
}
