package projeto.ci.ga;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import projeto.ci.mlp.MlpIntegrado;
import projeto.ci.util.Constants;
import projeto.ci.util.Desnormalizacao;

/**
 * 
 * @author Manoel Classe responsável por fazer o cross-over, mutação e
 *         reprodução
 *
 */

// criar MLP como objeto do GA
public class Algoritmo {

	private static double[][] solucao;
	private static double taxaDeCrossover;
	private static double taxaDeMutacao;
	private double genes;
	private static int tournamentSize;

	/**
	 * Gera uma nova população
	 * 
	 * @param pop
	 * @param elitismo
	 *            - Serve para verificar se o indivíduo está eleito para
	 *            continuar na população.
	 * @return Populacao
	 * @throws IOException
	 */
	public Algoritmo() throws IOException {
	}

	public static Populacao novaGeracao(Populacao populacao, MlpIntegrado mlp)
			throws IOException {
		Random r = new Random();

		//Trabalhando sempre com a mesma pop
		for (int i = 0; i < populacao.size()-1; i++) {
			// Seleciona os dois individuos que irão fazer o cruzamento

			// Realiza o torneio
			Individuo[] pais = selecaoTorneio(populacao, mlp);

			// Gera 2 objetos do tipo individuo
			Individuo[] filhos = new Individuo[2];

			if (r.nextDouble() <= taxaDeCrossover) {
				// crossover
				filhos = crossoverPorCorte(pais[0], pais[1], mlp);

				populacao.setIndividuo(filhos[0], i);
				populacao.setIndividuo(filhos[1], i+1);
			} else {
				populacao.setIndividuo(pais[0], i);
				populacao.setIndividuo(pais[1], i+1);
			}


		}



		Individuo[] individuosaux;
		individuosaux = populacao.ordenaPopulacao(populacao.getIndividuos());
		System.out.println("Primeira ordenação, antes da mutação");
		mostrar(individuosaux);

		individuosaux = mutacao(individuosaux, mlp);


		System.out.println("Resultado da Mutação");
		mostrar(individuosaux);

		populacao.setIndividuos(populacao.ordenaPopulacao(individuosaux));
		System.out.println("Segunda ordenação");

		mostrar(individuosaux);
		return populacao;
	}
	
	public Individuo desnormalizacao(Individuo individuo) throws IOException{
		Desnormalizacao desn = new Desnormalizacao();
		
		return desn.Desnormaliza(individuo);
		
	}

	public boolean hasSolucao(Individuo individuo) {
		if (individuo.getFitness()[0] <= Constants.SOLUCAO_CVLI && individuo.getFitness()[1] <= Constants.SOLUCAO_CVP){
			return true;}

		return false;

	}

	public static void mostrar (Individuo[] individuos){
		for (int i = 0; i < individuos.length; i++){
			System.out.println("Individuo " + i + " " + Arrays.toString(individuos[i].getGenes()) 
			+ Arrays.toString(individuos[i].getFitness()));
		}
		System.out.println();
	}

	/**
	 * Faz a seleção dos melhores indivíduos para fazer o crossover atraves de
	 * um torneio.
	 * 
	 * @param populacao
	 * @return Individuos[2] - Os melhores da população enviada.
	 * @throws IOException
	 */
	public static Individuo[] selecaoTorneio(Populacao populacao, MlpIntegrado mlp) throws IOException {
		Random r = new Random();

		Individuo n1 = populacao.getIndividuo(r.nextInt(populacao.size()));
		Individuo n2 = populacao.getIndividuo(r.nextInt(populacao.size()));
		Individuo n3 = populacao.getIndividuo(r.nextInt(populacao.size()));
		// Populacao populacaoIntermediaria = new Populacao(3, true);
		Populacao populacaoIntermediaria = new Populacao(3, true, mlp);

		// seleciona 3 indivíduos aleatóriamente na população
		populacaoIntermediaria.setIndividuo(n1);// r.nextInt(populacao.getTamPopulacao())));
		populacaoIntermediaria.setIndividuo(n2);
		populacaoIntermediaria.setIndividuo(n3);

		// ordena a população
		// populacaoIntermediaria.ordenaPopulacao();

		Individuo[] pais = new Individuo[2];

		// seleciona os 2 melhores deste população
		pais[0] = populacaoIntermediaria.getIndividuo(0);
		pais[1] = populacaoIntermediaria.getIndividuo(1);

		return pais;
	}

	public static Individuo[] mutacao (Individuo[] individuos, MlpIntegrado mlp) throws IOException{
		int lin = (int) Math.round(Constants.QUANTOS_MUTARAO*individuos.length);
		Random rand = new Random();
		for (int i = individuos.length-lin; i< individuos.length; i++){
			for (int j = 3; j< Constants.GENE_SIZE; j++){
				individuos[i].setGenesPosicao(j, (Math.abs(individuos[i].getIndexGenes(j)+(rand.nextDouble()*(2))-1)*Constants.MUTATION_RATE));
				individuos[i].setFitness(mlp.evaluate(individuos[i].getGenes(), mlp));
			}
		}

		return individuos;
	}


	/**
	 * Método que faz o crossover de dois indivíduos
	 * 
	 * @param indi1
	 * @param indi2
	 * @return Individuos[2]
	 * @throws IOException
	 */
	public static Individuo[] crossoverPorCorte(Individuo indi1, Individuo indi2, MlpIntegrado mlp) throws IOException {
		Random rCrossover = new Random();

		int pontoCorte1 = rCrossover.nextInt((indi1.getGenes().length / 2) - 2) + 1;
		int pontoCorte2 = rCrossover.nextInt((indi1.getGenes().length / 2) - 2) + indi1.getGenes().length / 2;

		Individuo[] filhos = new Individuo[2];

		double[] genePai1 = indi1.getGenes();
		double[] genePai2 = indi2.getGenes();

		double[] geneFilho1 = new double[genePai1.length];
		double[] geneFilho2 = new double[genePai1.length];

		// Realiza o corte
		// Filho 01
		for (int x = 0; x < pontoCorte1; x++) {
			geneFilho1[x] = genePai1[x];
		}

		for (int x = pontoCorte1; x < pontoCorte2; x++) {
			geneFilho1[x] = genePai2[x];// Cruzamento
		}

		for (int x = pontoCorte2; x < genePai1.length; x++) {
			geneFilho1[x] = genePai1[x];
		}

		// Filho 02
		for (int x = 0; x < pontoCorte1; x++) {
			geneFilho2[x] = genePai1[x];
		}

		for (int x = pontoCorte1; x < pontoCorte2; x++) {
			geneFilho1[x] = genePai2[x];// Cruzamento
		}

		for (int x = pontoCorte2; x < genePai2.length; x++) {
			geneFilho2[x] = genePai1[x];
		}

		Individuo ind1 = new Individuo(mlp, geneFilho1);
		filhos[0] = ind1;
		// filhos[0] = ind1.geraIndividuo(geneFilho1);

		Individuo ind2 = new Individuo(mlp, geneFilho2);
		filhos[1] = ind2;
		// Individuo ind2 = new Individuo();
		// filhos[2] = ind2.geraIndividuo(geneFilho2);

		return filhos;
	}

	// public void setSolucao(double[][] solucao) {
	// Algoritmo.solucao = solucao;
	// }
	//
	// public static double[][] getSolucao() {
	// return solucao;
	// }

	public void setTaxaDeCrossover(double taxaDeCross) {
		Algoritmo.taxaDeCrossover = taxaDeCross;
	}

	public double getTaxaDeCrossover() {
		return taxaDeCrossover;
	}

	public void setTaxaDeMutacao(double taxaDeMut) {
		Algoritmo.taxaDeMutacao = taxaDeMut;
	}

	public static double getTaxaDeMutacao() {
		return taxaDeMutacao;
	}

	public void setGenes(double genes) {
		this.genes = genes;
	}

	public double getGenes() {
		return genes;
	}

}
