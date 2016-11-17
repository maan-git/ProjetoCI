package projeto.ci.ga;
import java.io.IOException;
import java.util.Random;
import projeto.ci.mlp.MlpIntegrado;
import projeto.ci.util.Constants;

public class Individuo {

	private double[] genes = new double[Constants.GENE_SIZE];
	private double[] fitness = new double[Constants.FITNESS_SIZE];
	
	
	public Individuo(){}
	
	/**
	 * Gera individuos com genes aleatórios
	 * 
	 * @param numGenes
	 * return Individuo
	 * @throws IOException 
	 */
	public Individuo (MlpIntegrado mlp) throws IOException{
		this.genes = new double[Constants.GENE_SIZE];
		Random rIndividuo = new Random();
		
		double[] genesAux = new double[Constants.GENE_SIZE];

		for (int x = 0; x < this.genes.length; x++) {
			if (x == 0) {
				genesAux[x] = Constants.AIS;
			} else if (x == 1) {
				genesAux[x] = Constants.ano;
			} else if (x == 2) {
				genesAux[x] = Constants.mes;
			} else {
				genesAux[x] = Double.valueOf((rIndividuo.nextDouble()));
			}

		}
		
		this.genes = genesAux;
		this.fitness = mlp.evaluate(this.genes, mlp);
		
	}
	
	public Individuo (MlpIntegrado mlp, double[] genes) throws IOException{
		this.genes = new double[Constants.GENE_SIZE];		
		this.genes = genes;
		this.fitness = mlp.evaluate(this.genes, mlp);
		
	}

	public void setFitness(double[] fitness){
		this.fitness = fitness;
	}

	
	public void setGenesPosicao(int index, double value){
		this.genes[index] = value;
	}
	
	public void setFitnessPosicao(int index, double value){
		this.fitness[index] = value;
	}
	public void setGenes(double[] genes) {
		this.genes = genes;
	}

	public double[] getGenes() {
		return genes;
	}

	public double getIndexGenes(int index) {
		return genes[index];
	}

	public double[] getFitness() {
		return fitness;
	}
	
	public double getIndexFitness(int index) {
		return fitness[index];
	}
	
	public int size(){
		return genes.length;
	}

}