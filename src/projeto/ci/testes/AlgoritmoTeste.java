package projeto.ci.testes;

import java.io.IOException;
import java.util.Arrays;

import projeto.ci.ga.Algoritmo;
import projeto.ci.ga.Individuo;
import projeto.ci.ga.Populacao;
import projeto.ci.mlp.MlpIntegrado;
import projeto.ci.util.Constants;

public class AlgoritmoTeste {

	public static void main(String[] args) throws IOException {
		MlpIntegrado mlp = new MlpIntegrado();
		mlp.treinar();

		Algoritmo alg = new Algoritmo();
		//alg.setSolucao(geraSolucao(4));
		alg.setTaxaDeCrossover(Constants.CR0SS0VER_RATE);
		alg.setTaxaDeMutacao(Constants.MUTATION_RATE);

		// Inicializando a primeira população com indivíduos aleatórios
		Populacao pop = new Populacao(Constants.POPULATION_SIZE, Constants.INITIALISE, mlp);

		boolean temSolucao = false;
		int geracao = 0;

		do {
			geracao++;

			// cria nova populacao
			pop = Algoritmo.novaGeracao(pop, mlp);

			System.out.println("---------------");
			System.out.println("Generation: " + geracao + " | Fitness: " + Arrays.toString(pop.getIndividuo(0).getFitness())
					+ " | Individuo: " + Arrays.toString(pop.getIndividuo(0).getGenes()));
			System.out.println("---------------");
			if(temSolucao = alg.hasSolucao(pop.getIndividuo(0))) System.out.println("Solução: " + Arrays.toString(pop.getIndividuo(0).getGenes())
			+ Arrays.toString(pop.getIndividuo(0).getFitness()));
		} while (!temSolucao && geracao < Constants.MAX_GENERATION);
		Individuo alfa = alg.desnormalizacao(pop.getIndividuo(0));
		
		System.out.println("Solução: " + Arrays.toString(alfa.getGenes())+ Arrays.toString(alfa.getFitness()));
	}

}
