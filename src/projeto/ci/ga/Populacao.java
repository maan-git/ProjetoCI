package projeto.ci.ga;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import projeto.ci.mlp.MlpIntegrado;
import projeto.ci.util.Constants;

public class Populacao {

	private Individuo[] individuos;
	private int populacaoSize;

	public Populacao() {

	}

	/**
	 * Construtor que cria uma população com indivíduos aleatórios.
	 * 
	 * @param tamPop
	 * @param inicializar
	 * @throws IOException
	 */
	public Populacao(int tamPop, boolean inicializar, MlpIntegrado mlp) throws IOException {

		this.populacaoSize = tamPop;
		individuos = new Individuo[tamPop];

		if (inicializar) {

			for (int i = 0; i < tamPop; i++) {
				//Gera individuos randomicos
				Individuo ind = new Individuo(mlp);
				// ind.geraIndividuoRandomico(mlp);
				saveIndividuo(i, ind);
			}
		}
		individuos = ordenaPopulacao(individuos);
	}
	
	
	/**
	 * Retorna o melhor indivíduo
	 * @return
	 */
	public Individuo getIndividuoFittest() {
		return ordenaPopulacao(individuos)[0];
		
//		Individuo ind = individuos[0];
//		System.out.println("indivíduo default: " + Arrays.toString(ind.getGenes()));
//		for (int i = 0; i < size(); i++) {
//
//			if ((ind.getFitness()[0] >= getIndividuo(i).getFitness()[0])
//					&& (ind.getFitness()[1] >= getIndividuo(i).getFitness()[1])) {
//				ind = getIndividuo(i);
//
//			}
//		}
//		System.out.println("indivíduo BEST: " + Arrays.toString(ind.getGenes()));
//		return ind;
	}

	// coloca um indivíduo em uma certa posição da população
	public void setIndividuo(Individuo individuo, int posicao) {
		individuos[posicao] = individuo;
	}

	// coloca um indivíduo na próxima posição disponível da população
	public void setIndividuo(Individuo individuo) {
		for (int i = 0; i < individuos.length; i++) {
			if (individuos[i] == null) {
				individuos[i] = individuo;
				return;
			}
		}
	}

	/**
	 * Verifica se já existe a solução
	 * 
	 * @param solution
	 * @return
	 */
	// verifica se algum indivíduo da população possui a solução
	

	/**
	 * ordena a população pelo valor de aptidão de cada indivíduo, do maior
	 * valor para o menor, assim se eu quiser obter o melhor indivíduo desta
	 * população, acesso a posição 0 do array de indivíduos
	 */
	public Individuo[] ordenaPopulacao(Individuo[] individuos) {
		int i;
		boolean trocou = true;
		double[][] auxiliar = new double[size()][2];
		Individuo indTemp = new Individuo();
		while (trocou) {
			trocou = false;
			for (i = 0; i < size() - 1; i++) {
				double aux = (individuos[i].getFitness()[0] * Constants.IMPORTANCIA_CVLI) + (individuos[i].getFitness()[1] * Constants.IMPORTANCIA_CVP);
				double aux2 = (individuos[i + 1].getFitness()[0] * Constants.IMPORTANCIA_CVLI) + (individuos[i + 1].getFitness()[1] * Constants.IMPORTANCIA_CVP);
				if (aux > aux2) {
					indTemp = individuos[i];
					individuos[i] = individuos[i + 1];
					individuos[i + 1] = indTemp;

					trocou = true;
				}
			}
		}
		return individuos;
	}

	public int getIndividuosSize() {
		int num = 0;
		for (int i = 0; i < individuos.length; i++) {
			if (individuos[i] != null) {
				num++;
			}
		}
		return num;
	}

	public int getPopulacaoSize() {
		return populacaoSize;
	}

	public int size() {
		return individuos.length;
	}

	public void saveIndividuo(int pos, Individuo individuo) {
		individuos[pos] = individuo;
	}

	public Individuo getIndividuo(int pos) {
		return individuos[pos];
	}
	
	public Individuo[] getIndividuos(){
		return individuos;
	}
	
	public void setIndividuos(Individuo[] indivs){
		individuos = indivs;
	}

}
