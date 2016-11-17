package projeto.ci.mlp;

import java.util.Random;

import projeto.ci.util.Constants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Multilayer Perceptron
 *
 */
public class MlpIntegrado {

	private static double bias1[];
	private static double bias2[];

	static StringBuilder sb = new StringBuilder();
	private static BufferedWriter writer;
	private static BufferedWriter writer2;
	private static double antigodeltabs1[];
	private static double antigodeltabs2[];
	private static double deltabs1[];
	private static double deltabs2[];
	private static HashMap<String, double[]> outputs;
	private static double[][] pesos1;
	private static double[][] pesos2;

	private static double[][] antigodeltapesos1;
	private static double[][] antigodeltapesos2;

	private static double[][] deltapesos1;
	private static double[][] deltapesos2;

	private static double net1[];
	private static double net2[][];
	private static double netout[][];
	private static double erro1[][];
	private static double erro2[][];

	private static double sensibilidade1[];

	private static double fnet1[];

	private static double[][] entradas;

	private static double[][] valoresArquivo;

	private static double[][] saidas;

	// Atributo que indica o nï¿½mero de camadas escondidas.
	private static int numHidden;

	// Atributo que indica o nï¿½mero de entradas.
	private static int numInputs;

	// Atributo que indica o nï¿½mero de saï¿½das.
	private static int numOutputs;

	// Atributo que indica a quantidade de ï¿½pocas.
	private static int ciclos;

	// Atributo que indica o ciclo atual.
	private static int cicloAtual;

	// Atributo que indica o nï¿½mero de treinamentos.
	private static int numTraining;

	// Atributo que indica o valor de Lcoef (taxa de aprendizagem).
	private static double alfa;

	// Atributo que indica o valor de Motum (Momentum).
	private static double beta;

	// Atributo que indica o valor da semente do gerador de nï¿½meros
	// aleatï¿½rios.
	private static long semente;

	// NOVAS IMPLEMENTAÇÕES
	private static double[][] pesosBiasEntradas;
	private static double[][] pesosBiasSaidas;

	//private static double[] errosCVLICVP;

	/**
	 * Retorna o valor de semente
	 * 
	 * @return
	 */
	public static long getSemente() {
		return semente;
	}

	/**
	 * Atribui o valor de semente.
	 * 
	 * @param value
	 */
	public static void setSemente(long value) {
		semente = value;
	}

	/**
	 * Retorna o valor de beta
	 * 
	 * @return
	 */
	public static double getBeta() {
		return beta;
	}

	/**
	 * Atribui o valor de beta.
	 * 
	 * @param value
	 */
	public static void setBeta(double value) {
		beta = value;
	}

	/**
	 * Retorna o valor de alfa
	 * 
	 * @return
	 */
	public static double getAlfa() {
		return alfa;
	}

	/**
	 * Atribui o valor de alfa.
	 * 
	 * @param value
	 */
	public static void setAlfa(double value) {
		alfa = value;
	}

	/**
	 * Retorna o nï¿½mero de exemplos de treinamento.
	 * 
	 * @return
	 */
	public static int getNumTraining() {
		return numTraining;
	}

	/**
	 * Atribui o nï¿½mero de exemplos de treinamento.
	 * 
	 * @param value
	 */
	public static void setNumTraining(int value) {
		numTraining = value;
	}

	/**
	 * Retorna o nï¿½mero de ciclos
	 * 
	 * @return
	 */
	public static int getCiclos() {
		return ciclos;
	}

	/**
	 * Atribui o nï¿½mero de ciclos.
	 * 
	 * @param value
	 */

	/**
	 * 
	 * /** Retorna o ciclo atual
	 * 
	 * @return
	 */
	public static int getCicloAtual() {
		return cicloAtual;
	}

	/**
	 * Atribui o ciclo atual.
	 * 
	 * @param value
	 */
	public static void setCicloAtual(int value) {
		cicloAtual = value;
	}

	public static void setCiclos(int value) {
		ciclos = value;
	}

	/**
	 * Retorna o nï¿½mero de saï¿½das.
	 * 
	 * @return
	 */
	public static int getNumOutputs() {
		return numOutputs;
	}

	/**
	 * Atribui o nï¿½mero de saï¿½das.
	 * 
	 * @param value
	 */
	public static void setNumOutputs(int value) {
		numOutputs = value;

		// inicializa os vetores para bias2 e prdlbs2
		bias2 = new double[getNumOutputs()];
		antigodeltabs2 = new double[getNumOutputs()];
		deltabs2 = new double[getNumOutputs()];
	}

	/**
	 * Retorna o nï¿½mero de entradas.
	 * 
	 * @return
	 */
	public static int getNumInputs() {
		return numInputs;
	}

	/**
	 * Atribui o nï¿½mero de entradas.
	 * 
	 * @param value
	 */
	public static void setNumInputs(int value) {
		numInputs = value;

	}

	/**
	 * Retorna o nï¿½mero de neurï¿½nios escondidos.
	 * 
	 * @return
	 */
	public static int getNumHidden() {
		return numHidden;
	}

	/**
	 * Atribui o nï¿½mero de neurï¿½nios escondidos.
	 * 
	 * @param value
	 */
	public static void setNumHidden(int value) {
		numHidden = value;

		// Inicializa o vetor para bias1, netin1, sum1, prdlbs1, delbs1.
		bias1 = new double[getNumHidden()];
		net1 = new double[getNumHidden()];
		sensibilidade1 = new double[getNumHidden()];
		fnet1 = new double[getNumHidden()];
		antigodeltabs1 = new double[getNumHidden()];
		deltabs1 = new double[getNumHidden()];
	}

	/**
	 * Atribui o valor do bias1 no ï¿½ndice indicado.
	 * 
	 * @param index:
	 *            ï¿½ndice do vetor.
	 * @param value:
	 *            valor que serï¿½ atribuï¿½do.
	 */
	public static void setBias1(int index, double value) {
		bias1[index] = value;
	}

	/**
	 * Retorna o valor do bias1 no ï¿½ndice indicado.
	 * 
	 * @param index:
	 *            ï¿½ndice do vetor.
	 */
	public static double getBias1(int index) {
		return bias1[index];
	}

	/**
	 * Atribui o valor do bias2 no ï¿½ndice indicado.
	 * 
	 * @param index:
	 *            ï¿½ndice do vetor.
	 * @param value:
	 *            valor que serï¿½ atribuï¿½do.
	 */
	/*
	 * public static void setBias2(int index,double value){ bias2[index] =
	 * value; }
	 */

	/**
	 * Retorna o valor do bias2 no ï¿½ndice indicado.
	 * 
	 * @param index:
	 *            ï¿½ndice do vetor.
	 */
	public static double getBias2(int index) {
		return bias2[index];
	}

	/**
	 * Atribui o valor do netin1 no ï¿½ndice indicado.
	 * 
	 * @param index:
	 *            ï¿½ndice do vetor.
	 * @param value:
	 *            valor que serï¿½ atribuï¿½do.
	 */
	public static void setNet1(int index, double value) {
		net1[index] = value;
	}

	/**
	 * Retorna o valor do netin1 no ï¿½ndice indicado.
	 * 
	 * @param index:
	 *            ï¿½ndice do vetor.
	 */
	public static double getNet1(int index) {
		return net1[index];
	}

	/**
	 * Atribui o valor do sum1 no ï¿½ndice indicado.
	 * 
	 * @param index:
	 *            ï¿½ndice do vetor.
	 * @param value:
	 *            valor que serï¿½ atribuï¿½do.
	 */
	public static void setSensibilidade1(int index, double value) {
		sensibilidade1[index] = value;
	}

	/**
	 * Retorna o valor da sensibilidade no ï¿½ndice indicado.
	 * 
	 * @param index:
	 *            ï¿½ndice do vetor.
	 */
	public static double getSensibilidade1(int index) {
		return sensibilidade1[index];
	}

	/**
	 * Retorna o valor do fnet1 no ï¿½ndice indicado.
	 * 
	 * @param index:
	 *            ï¿½ndice do vetor.
	 */
	public static double getFnet1(int index) {
		return fnet1[index];
	}

	/**
	 * Atribui o valor do fnet1 no ï¿½ndice indicado.
	 * 
	 * @param index:
	 *            ï¿½ndice do vetor.
	 * @param value:
	 *            valor que serï¿½ atribuï¿½do.
	 */
	public static void setFnet1(int index, double value) {
		fnet1[index] = value;
	}

	/**
	 * Retorna o valor do Antigodeltabs2 no ï¿½ndice indicado.
	 * 
	 * @param index:
	 *            ï¿½ndice do vetor.
	 */
	public static double getAntigodeltabs2(int index) {
		return antigodeltabs2[index];
	}

	/**
	 * Atribui o valor do Antigodeltabs1 no ï¿½ndice indicado.
	 * 
	 * @param index:
	 *            ï¿½ndice do vetor.
	 * @param value:
	 *            valor que serï¿½ atribuï¿½do.
	 */
	public static void setAntigodeltabs1(int index, double value) {
		antigodeltabs1[index] = value;
	}

	/**
	 * Atribui o valor do Antigodeltabs2 no ï¿½ndice indicado.
	 * 
	 * @param index:
	 *            ï¿½ndice do vetor.
	 * @param value:
	 *            valor que serï¿½ atribuï¿½do.
	 */
	public static void setAntigodeltabs2(int index, double value) {
		antigodeltabs2[index] = value;
	}

	/**
	 * Retorna o valor do Antigodeltabs1 no ï¿½ndice indicado.
	 * 
	 * @param index:
	 *            ï¿½ndice do vetor.
	 */
	public static double getAntigodeltabs1(int index) {
		return antigodeltabs1[index];
	}

	/**
	 * Atribui o valor do delbs1 no ï¿½ndice indicado.
	 * 
	 * @param index:
	 *            ï¿½ndice do vetor.
	 * @param value:
	 *            valor que serï¿½ atribuï¿½do.
	 */
	public static void setDeltabs1(int index, double value) {
		deltabs1[index] = value;
	}

	/**
	 * Retorna o valor do deltabs1 no indice indicado.
	 * 
	 * @param index:
	 *            indice do vetor.
	 */
	public static double getDeltabs1(int index) {
		return deltabs1[index];
	}

	/**
	 * Atribui o valor do deltabs2 no indice indicado.
	 * 
	 * @param index:
	 *            indice do vetor.
	 * @param value:
	 *            valor que sera atribuido.
	 */
	public static void setDeltabs2(int index, double value) {
		deltabs2[index] = value;
	}

	/**
	 * Retorna o valor do deltabs2 no ï¿½ndice indicado.
	 * 
	 * @param index:
	 *            ï¿½ndice do vetor.
	 */
	public static double getDeltabs2(int index) {
		return deltabs2[index];
	}

	/**
	 * Atribui o valor do bias2 no ï¿½ndice indicado.
	 * 
	 * @param index
	 * @param value
	 */
	public static void setBias2(int index, double value) {
		bias2[index] = value;
	}

	/**
	 * Retorna o valor do Pesos1
	 * 
	 * @return
	 */
	public static double getPesos1(int row, int col) {
		return pesos1[row][col];
	}

	/**
	 * Atribui o valor de Pesos1.
	 * 
	 * @param row
	 * @param col
	 * @param value
	 */
	public static void setPesos1(int row, int col, double value) {
		pesos1[row][col] = value;
	}

	/**
	 * Retorna o valor do Pesos2
	 * 
	 * @return
	 */
	public static double getPesos2(int row, int col) {
		return pesos2[row][col];
	}

	/**
	 * Atribui o valor de Pesos2.
	 * 
	 * @param row
	 * @param col
	 * @param value
	 */
	public static void setPesos2(int row, int col, double value) {
		pesos2[row][col] = value;
	}

	/**
	 * Retorna o valor do Antigodeltapesos1
	 * 
	 * @return
	 */
	public static double getAntigodeltapesos1(int row, int col) {
		return antigodeltapesos1[row][col];
	}

	/**
	 * Atribui o valor de Antigodeltapesos2.
	 * 
	 * @param row
	 * @param col
	 * @param value
	 */
	public static void setAntigodeltapesos2(int row, int col, double value) {
		antigodeltapesos2[row][col] = value;
	}

	/**
	 * Retorna o valor do Antigodeltapesos2
	 * 
	 * @return
	 */
	public static double getAntigodeltapesos2(int row, int col) {
		return antigodeltapesos2[row][col];
	}

	/**
	 * Atribui o valor de Antigodeltapesos1.
	 * 
	 * @param row
	 * @param col
	 * @param value
	 */
	public static void setAntigodeltapesos1(int row, int col, double value) {
		antigodeltapesos1[row][col] = value;
	}

	/**
	 * Retorna o valor do Deltapesos1
	 * 
	 * @return
	 */
	public static double getDeltapesos1(int row, int col) {
		return deltapesos1[row][col];
	}

	/**
	 * Atribui o valor de Deltapesos1.
	 * 
	 * @param row
	 * @param col
	 * @param value
	 */
	public static void setDeltapesos1(int row, int col, double value) {
		deltapesos1[row][col] = value;
	}

	/**
	 * Retorna o valor do Deltapesos2
	 * 
	 * @return
	 */
	public static double getDeltapesos2(int row, int col) {
		return deltapesos2[row][col];
	}

	/**
	 * Atribui o valor de Deltapesos2.
	 * 
	 * @param row
	 * @param col
	 * @param value
	 */
	public static void setDeltapesos2(int row, int col, double value) {
		deltapesos2[row][col] = value;
	}

	/**
	 * Inicializa o valor da matriz Pesos1.
	 * 
	 * @param row
	 * @param col
	 */
	public static void initPesos1(int row, int col) {
		pesos1 = new double[row][col];
	}

	/**
	 * Retorna o valor do Net2
	 * 
	 * @return
	 */
	public static double getNet2(int row, int col) {
		return net2[row][col];
	}

	/**
	 * Atribui o valor de Net2.
	 * 
	 * @param row
	 * @param col
	 * @param value
	 */
	public static void setNet2(int row, int col, double value) {
		net2[row][col] = value;
	}

	/**
	 * Retorna o valor do Erro1
	 * 
	 * @return
	 */
	public static double getErro1(int row, int col) {
		return erro1[row][col];
	}

	/**
	 * Atribui o valor de Erro1.
	 * 
	 * @param row
	 * @param col
	 * @param value
	 */
	public static void setErro1(int row, int col, double value) {
		erro1[row][col] = value;
	}

	/**
	 * Retorna o valor do Netout
	 * 
	 * @return
	 */
	public static double getNetout(int row, int col) {
		return netout[row][col];
	}

	/**
	 * Atribui o valor de Netout.
	 * 
	 * @param row
	 * @param col
	 * @param value
	 */
	public static void setNetout(int row, int col, double value) {
		netout[row][col] = value;
	}

	/**
	 * Retorna o valor do erro2
	 * 
	 * @return
	 */
	public static double getErro2(int row, int col) {
		return erro2[row][col];
	}

	/**
	 * Atribui o valor de erro2.
	 * 
	 * @param row
	 * @param col
	 * @param value
	 */
	public static void setErro2(int row, int col, double value) {
		erro2[row][col] = value;
	}

	/**
	 * Inicializa o valor da matriz Pesos2.
	 * 
	 * @param row
	 * @param col
	 */
	public static void initPesos2(int row, int col) {
		pesos2 = new double[row][col];
	}

	/**
	 * Inicializa o valor da matriz Prdlwt1.
	 * 
	 * @param row
	 * @param col
	 */
	public static void initAntigodeltapesos1(int row, int col) {
		antigodeltapesos1 = new double[row][col];
	}

	/**
	 * Inicializa o valor da matriz Antigodeltapesos2.
	 * 
	 * @param row
	 * @param col
	 */
	public static void initAntigodeltapesos2(int row, int col) {
		antigodeltapesos2 = new double[row][col];
	}

	/**
	 * Inicializa o valor da matriz Deltapesos1.
	 * 
	 * @param row
	 * @param col
	 */
	public static void initDeltapesos1(int row, int col) {
		deltapesos1 = new double[row][col];
	}

	/**
	 * Inicializa o valor da matriz Deltapesos2.
	 * 
	 * @param row
	 * @param col
	 */
	public static void initDeltapesos2(int row, int col) {
		deltapesos2 = new double[row][col];
	}

	/**
	 * Inicializa o valor da matriz Net2.
	 * 
	 * @param row
	 * @param col
	 */
	public static void initNet2(int row, int col) {
		net2 = new double[row][col];
	}

	/**
	 * Inicializa o valor da matriz Erro1.
	 * 
	 * @param row
	 * @param col
	 */
	public static void initErro1(int row, int col) {
		erro1 = new double[row][col];
	}

	/**
	 * Inicializa o valor da matriz Net2.
	 * 
	 * @param row
	 * @param col
	 */
	public static void initNetout(int row, int col) {
		netout = new double[row][col];
	}

	/**
	 * Inicializa o valor da matriz erro2.
	 * 
	 * @param row
	 * @param col
	 */
	public static void initErro2(int row, int col) {
		erro2 = new double[row][col];
	}

	/**
	 * Retorna o valor da matriz de entrada.
	 * 
	 * @param row
	 * @param col
	 */
	public static double getEntradas(int row, int col) {
		// System.out.println("getEntradas");
		return entradas[row][col];
	}

	/**
	 * Retorna o valor da matriz de saidas.
	 * 
	 * @param row
	 * @param col
	 */
	public static double getSaidas(int row, int col) {
		// System.out.println("getSaidas");
		return saidas[row][col];
	}

	public static double setFunction(double net) {
		return (double) (Math.exp((double) ((-1.0d) * (net))));
	}

	// public static double setFunctionB (double net){
	// double eb;
	// return eb = (double) (Math.exp((double) ((-1.0d) * (net))));
	// }
	/**
	 * Inicializacao dos pesos.
	 *
	 */
	public static void randomize() {

		// Cria uma instï¿½ncia de Random
		Random random = new Random(getSemente());

		for (int j = 0; j < getNumHidden(); j++) {
			setBias1(j, -1 + random.nextInt(8192) / 8192);
			setAntigodeltabs1(j, 0.0d);
			setDeltabs1(j, 0.0d);

			for (int i = 0; i < getNumInputs(); i++) {
				setPesos1(j, i, random.nextInt(8192) / 8192 - 0.5d);
				setAntigodeltapesos1(j, i, 0.0d);
				setDeltapesos1(j, i, 0.0d);
			}
		}

		for (int j = 0; j < getNumOutputs(); j++) {
			setBias2(j, -0.1d + random.nextInt(8192) / 8192);
			setAntigodeltabs2(j, 0.0d);
			for (int i = 0; i < getNumHidden(); i++) {
				setPesos2(j, i, 0.1d * random.nextInt(8192) / 8192 - 0.05);
				setAntigodeltapesos2(j, i, 0.0d);
				setDeltapesos2(j, i, 0.0d);
			}
		}
	}

	public static void Forward() {
		double answer = 0.0;
		try {
			// File file = new File("MLPIntegradoSaida.csv");
			// file.createNewFile();
			PrintWriter pw = new PrintWriter(new File("data/pesos_bias.csv"));
			// writer = new BufferedWriter(new FileWriter(file));

			for (int kl = 0; kl < getCiclos(); kl++) {
				setCicloAtual(getCicloAtual() + 1);
				for (int itr = 0; itr < getNumTraining(); itr++) {
					System.out.println("-------------" + (itr + 1));
					System.out.println("Entradas do cíclo [" + (itr + 1) + "]: " + Arrays.toString(entradas[itr]));
					System.out.println("saidas do cíclo [" + (itr + 1) + "]: " + Arrays.toString(saidas[itr]));
					double ea, eb;

					// System.out.println("1");
					for (int j = 0; j < getNumHidden(); j++) {
						setNet1(j, getBias1(j));
						System.out.println(entradas[0].length + "-" + saidas[0].length);

						for (int i = 0; i < getNumInputs(); i++) {
							setNet1(j, getNet1(j) + (getPesos1(j, i) * getEntradas(itr, i)));
						}

						setFnet1(j, (double) (1.0) / (1.0 + (setFunction(getNet1(j)))));
						System.out.println("Net [1 camada][neuronio " + (j + 1) + "]: " + getNet1(j));
						System.out.println("fNet [1 camada][neuronio " + (j + 1) + "]: " + getFnet1(j));

					}

					System.out.println("------------------------");

					// System.out.println("2");
					for (int j = 0; j < getNumOutputs(); j++) {
						setNet2(itr, j, getBias2(j));
						for (int i = 0; i < getNumHidden(); i++) {
							setNet2(itr, j, getNet2(itr, j) + (getPesos2(j, i) * getFnet1(i)));
						}

						setNetout(itr, j, (double) (1.0 / (1.0 + setFunction(getNet2(itr, j)))));

						System.out.println("Net [2 camada][neuronio " + (j + 1) + "]: " + getNet2(itr, j));
						System.out.println("fNet [2 camada][neuronio " + (j + 1) + "]: " + getNetout(itr, j));
					}

					System.out.println("------------------------");
					// System.out.println("3");

					// Reajustando os pesos
					for (int j = 0; j < getNumOutputs(); j++) {

						System.out.println("Saida: " + getSaidas(itr, j));
						System.out.println("Saida Net: " + getNetout(itr, j));

						setErro2(itr, j, (getSaidas(itr, j) - getNetout(itr, j)));

						System.out.println("Erro: " + (getSaidas(itr, j) - getNetout(itr, j)));
						// impressao dos dados de saida
						System.out.println("Ciclo:" + " " + getCicloAtual() + "  " + "Exemplo:" + " " + (itr + 1));

						System.out.println("Saida desejada:" + " " + getSaidas(itr, j) + "  " + "Saida calculada:" + " "
								+ getNetout(itr, j));
						System.out.println("Erro:" + " " + getErro2(itr, j));

						if (kl == getCiclos() - 1) {
							answer += getErro2(itr, j);
						}

						setDeltabs2(j, (getAlfa() * getErro2(itr, j)) * getNetout(itr, j) * (1.0 - getNetout(itr, j))
								+ (getBeta() * getAntigodeltabs2(j)));

						for (int i = 0; i < getNumHidden(); i++) {
							setDeltapesos2(j, i,
									(getAlfa() * getErro2(itr, j) * getNetout(itr, j) * (1.0 - getNetout(itr, j))
											* getFnet1(i)) + (getBeta() * getAntigodeltapesos2(j, i)));
						}
					}
					System.out.println("4");
					for (int j = 0; j < getNumHidden(); j++) {
						setSensibilidade1(j, 0.0d);
						for (int i = 0; i < getNumOutputs(); i++) {
							setSensibilidade1(j, getSensibilidade1(j) + (getErro2(itr, i) * getPesos2(i, j)));
						}

						setErro1(itr, j, (getFnet1(j)) * (1.0d - getFnet1(j)) * getSensibilidade1(j));
						setDeltabs1(j, (getAlfa() * getErro1(itr, j)) + (getBeta() * getAntigodeltabs1(j)));

						for (int ii = 0; ii < getNumInputs(); ii++) {
							setDeltapesos1(j, ii, (getAlfa() * getErro1(itr, j)) * (getEntradas(itr, ii))
									+ (getBeta() * getAntigodeltapesos1(j, ii)));
						}
					}

					System.out.println("5");
					for (int j = 0; j < getNumHidden(); j++) {
						setBias1(j, getDeltabs1(j) + getBias1(j));
						setAntigodeltabs1(j, getDeltabs1(j));
						System.out.println("bias:" + " " + (j + 1) + "     " + getBias1(j) + "  ");
						if (kl + 1 == ciclos && itr + 1 == numTraining) {
							// writer.write("bias:" + " " + (j + 1) + " " +
							// getBias1(j) + " ");
							// writer.newLine();
							// sb.append((j + 1));
							// sb.append(";");
							sb.append(getBias1(j));
							sb.append('\n');
							// sb.append(';');
						}
						for (int ii = 0; ii < getNumInputs(); ii++) {

							setPesos1(j, ii, getPesos1(j, ii) + (getDeltapesos1(j, ii)));
							System.out.println("Peso:" + " " + (j + 1) + " " + (ii + 1) + "    " + getPesos1(j, ii));
							if (kl + 1 == ciclos && itr + 1 == numTraining) {
								// writer.write("Peso:" + " " + (j + 1) + " " +
								// (ii + 1) + " " + getPesos1(j, ii));
								// writer.newLine();
								// sb.append((j + 1) +" "+ (ii + 1) );
								// sb.append(";");
								sb.append(getPesos1(j, ii));
								sb.append('\n');
								// sb.append(';');
							}

							setAntigodeltapesos1(j, ii, getDeltapesos1(j, ii));
						}
					}

					System.out.println("6");
					for (int j = 0; j < getNumOutputs(); j++) {
						setBias2(j, getDeltabs2(j) + getBias2(j));
						setAntigodeltabs2(j, getDeltabs2(j));
						System.out.println("bias:" + " " + (j + 1) + "     " + getBias2(j) + "  ");
						if (kl + 1 == ciclos && itr + 1 == numTraining) {
							// writer.write("bias:" + " " + (j + 1) + " " +
							// getBias2(j) + " ");
							// writer.newLine();
							// sb.append((j + 1));
							// sb.append(";");
							sb.append(getBias2(j));
							sb.append('\n');
							// sb.append(';');
						}
						for (int i = 0; i < getNumHidden(); i++) {
							setPesos2(j, i, getPesos2(j, i) + (getDeltapesos2(j, i)));
							setAntigodeltapesos2(j, i, getDeltapesos2(j, i));
							System.out.println("Peso:" + " " + (j + 1) + " " + (i + 1) + "    " + getPesos2(j, i));
							if (kl + 1 == ciclos && itr + 1 == numTraining) {
								// writer.write("Peso:" + " " + (j + 1) + " " +
								// (i + 1) + " " + getPesos2(j, i));
								// writer.newLine();
								// sb.append((j + 1) + " " + (i + 1) );
								// sb.append(";");
								sb.append(getPesos2(j, i));
								sb.append('\n');
								// sb.append(';');

							}

						}
					}
				}
			}
			pw.write(sb.toString());
			pw.close();

			System.out.println("Erro final: " + answer);
			// writer.flush();
			// writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			System.out.println(e);
			System.exit(1);
		} catch (IOException e) {
			System.out.println("something messed up");
			System.exit(1);
		}
	}

	/**
	 * Mï¿½todo principal
	 * 
	 * @param args
	 * @throws IOException
	 */
	public void treinar() throws IOException {
		// Inicializa os valores da matriz de entradas (1x7).
		// Integrar!
		// Open csv saveInArray

		readData("data/2014_2015_2016_efetivos_normalizado.csv");

//		String csvFile1 = "Dados_Tratados/2015_Normalizado.csv";
//		String csvFile2 = "Dados_Tratados/2016_Normalizado.csv";
//		String csvFileOutput1 = "Dados_Tratados/2015_efetivos.csv";
//		String csvFileOutput2 = "Dados_Tratados/2016_efetivos.csv";
//		readData(csvFile1, csvFile2);
//		setOutputs(csvFileOutput1, csvFileOutput2);

		randomData();
		// entradas[colunas][linhas]

		System.out.println(entradas.length);
		System.out.println(entradas[0].length);
		System.out.println(saidas.length);
		System.out.println(saidas[0].length);

		// Analise Parametrica para entender quais valores iniciais
		// Fatores ---> CVP / CVLI
		// -----------------------------------------
		// setSemente(1315);
		setSemente(100);
		setCiclos(100);
		setCicloAtual(0);
		setNumInputs(entradas[0].length);
		setNumHidden(2);
		setNumOutputs(saidas[0].length);
		setNumTraining(10);
		setAlfa(0.8);
		setBeta(0.3);
		// -----------------------------------------

		// Inicializar o tamanho das matrizes.
		initPesos1(getNumHidden(), getNumInputs());
		initAntigodeltapesos1(getNumHidden(), getNumInputs());
		initDeltapesos1(getNumHidden(), getNumInputs());

		initPesos2(getNumOutputs(), getNumHidden());
		initAntigodeltapesos2(getNumOutputs(), getNumHidden());
		initDeltapesos2(getNumOutputs(), getNumHidden());

		initNet2(getNumTraining(), getNumOutputs());
		initNetout(getNumTraining(), getNumOutputs());
		initErro2(getNumTraining(), getNumOutputs());

		initErro1(getNumTraining(), getNumHidden());

		// --------------------------------------------

		// Inicia os pesos aleatï¿½rios.
		randomize();
		// mlp
		Forward();
	}

	
	private static void randomData() {}

	/*
	 * public static double[] evaluate(){ double[] mathSquareError = new
	 * Double()[numOutputs] return 1;
	 * 
	 * }
	 */
	public static void setOutputs(String file1, String file2) throws IOException {
		outputs = new HashMap<String, double[]>();
		BufferedReader dataBR1 = new BufferedReader(new FileReader(new File(file1)));
		BufferedReader dataBR2 = new BufferedReader(new FileReader(new File(file2)));
		String line = "";

		ArrayList<String[]> dataArr = new ArrayList<String[]>();

		while ((line = dataBR1.readLine()) != null) {
			String[] value = line.split(";");
			dataArr.add(value);
		}
		while ((line = dataBR2.readLine()) != null) {
			String[] value = line.split(";");
			dataArr.add(value);
		}
		double[][] entradasIniciais = new double[dataArr.size()][dataArr.get(0).length - 2];
		double[][] saidasIniciais = new double[dataArr.size()][2];
		for (int i = 0; i < dataArr.size(); i++) {
			for (int x = 0; x < dataArr.get(i).length - 1; x++) {
				if (x == dataArr.get(i).length - 2) {
					saidasIniciais[i][0] = Double.parseDouble(dataArr.get(i)[x].replace(',', '.'));
					saidasIniciais[i][1] = Double.parseDouble(dataArr.get(i)[x + 1].replace(',', '.'));
					// outputs.put(dataArr.get(i)[0] + "_" + dataArr.get(i)[1]+
					// "_" + dataArr.get(i)[2], saidasIniciais[i] );
					outputs.put(dataArr.get(i)[0] + "_" + dataArr.get(i)[1], saidasIniciais[i]);
				} else {
					entradasIniciais[i][x] = Double.parseDouble(dataArr.get(i)[x].replace(',', '.'));
				}
			}
		}
		dataBR1.close();
		dataBR2.close();
	}

	public static void readData(String file1, String file2) throws IOException {

		BufferedReader dataBR1 = new BufferedReader(new FileReader(new File(file1)));
		BufferedReader dataBR2 = new BufferedReader(new FileReader(new File(file2)));
		String line = "";

		ArrayList<String[]> dataArr = new ArrayList<String[]>();

		while ((line = dataBR1.readLine()) != null) {
			String[] value = line.split(";");
			dataArr.add(value);
		}
		while ((line = dataBR2.readLine()) != null) {
			String[] value = line.split(";");
			dataArr.add(value);
		}
		// System.out.println("total: " + dataArr.get(0).length);

		double[][] entradasIniciais = new double[dataArr.size()][dataArr.get(0).length - 2];
		double[][] saidasIniciais = new double[dataArr.size()][2];

		for (int i = 0; i < dataArr.size(); i++) {
			for (int x = 0; x < dataArr.get(i).length - 1; x++) {
				if (x == dataArr.get(i).length - 2) {
					saidasIniciais[i][0] = Double.parseDouble(dataArr.get(i)[x].replace(',', '.'));
					saidasIniciais[i][1] = Double.parseDouble(dataArr.get(i)[x + 1].replace(',', '.'));
				} else {
					entradasIniciais[i][x] = Double.parseDouble(dataArr.get(i)[x].replace(',', '.'));

				}

			}
		}

		// System.out.println(entradasIniciais);
		dataBR1.close();
		dataBR2.close();

		entradas = entradasIniciais;
		saidas = saidasIniciais;
	}

	/**
	 * Método que ler os arquivos de entrada para treino da MLP
	 * 
	 * @param file1
	 * @throws IOException
	 */
	public static void readData(String file1) throws IOException {
		BufferedReader dataBR1 = new BufferedReader(new FileReader(new File(file1)));

		String line = "";

		ArrayList<String[]> dataArr = new ArrayList<String[]>();

		while ((line = dataBR1.readLine()) != null) {
			String[] value = line.split(";");
			dataArr.add(value);
		}
		// System.out.println("total: " + dataArr.get(0).length);
		System.out.println(Arrays.toString(dataArr.get(0)));
		System.out.println("Quantidade de linhas: " + dataArr.size());
		double[][] entradasIniciais = new double[dataArr.size()][dataArr.get(0).length - 2];
		double[][] saidasIniciais = new double[dataArr.size()][2];

		for (int i = 0; i < dataArr.size(); i++) {
			for (int x = 0; x < dataArr.get(i).length - 1; x++) {
				if (x == dataArr.get(i).length - 2) {
					saidasIniciais[i][0] = Double.parseDouble(dataArr.get(i)[x].replace(',', '.'));
					saidasIniciais[i][1] = Double.parseDouble(dataArr.get(i)[x + 1].replace(',', '.'));
				} else {
					entradasIniciais[i][x] = Double.parseDouble(dataArr.get(i)[x].replace(',', '.'));

				}

			}

			System.out.println("Linha: " + (i + 1));
		}

		System.out.println(entradasIniciais);
		dataBR1.close();

		entradas = entradasIniciais;
		saidas = saidasIniciais;
	}

	/**
	 * @author Manoel Método que ler os arquivos de pesos e bias
	 * @param nome
	 *            do arquivo
	 * @throws IOException
	 */
	public static void readDataEvaluate(String file1) throws IOException {
		BufferedReader dataBR1 = new BufferedReader(new FileReader(new File(file1)));
		String line = "";

		ArrayList<String[]> dataArr = new ArrayList<String[]>();

		while ((line = dataBR1.readLine()) != null) {
			String[] value = line.split("\n");
			dataArr.add(value);
		}

		// Pega pesos e baia da camanda 1 [camada][pesos/baia "entradas"]
		// numEntradas+1
		double[][] pesosBaiaEntradasAux = new double[numHidden][getNumInputs() + 1];
		double[][] pesosBaiaSaidaAux = new double[numOutputs][getNumOutputs() + 1];

		int qtd = 0;
		for (int i = 0; i < numHidden; i++) {

			for (int j = 0; j < getNumInputs() + 1; j++) {
				pesosBaiaEntradasAux[i][j] = Double.parseDouble(dataArr.get(qtd)[0].replace(',', '.'));
				qtd++;
			}
		}

		for (int i = 0; i < numOutputs; i++) {
			for (int j = 0; j < getNumOutputs() + 1; j++) {
				pesosBaiaSaidaAux[i][j] = Double.parseDouble(dataArr.get(qtd)[0].replace(',', '.'));
				qtd++;
			}
		}

		dataBR1.close();
		pesosBiasEntradas = pesosBaiaEntradasAux;
		pesosBiasSaidas = pesosBaiaSaidaAux;
	}

	/**
	 * @author Manoel Método que avalia o indivíduo
	 * @param indv
	 * @throws IOException
	 */
	public double[] evaluate(double[] indv, MlpIntegrado mlp) throws IOException {
		
		int numHiddenAux = mlp.getNumHidden();
		int numOutputsAux = mlp.getNumOutputs();

		double[][] netEntradas = new double[numHiddenAux][mlp.getNumInputs() + 1];
		double[][] netSaidas = new double[numOutputsAux][mlp.getNumOutputs() + 1];

		double[] fnetEntradas = new double[numHiddenAux];
		double[] fnetSaidas = new double[numOutputsAux];

		
		Double ea = 0.0;
		Double net = 0.0;
		Double netSaida = 0.0;

		readDataEvaluate("data/pesos_bias.csv");

				
		for (int i = 0; i < numHiddenAux; i++) {
			for (int j = 0; j < mlp.getNumInputs() + 1; j++) {
				if (j == 0) {					
					net += pesosBiasEntradas[i][j];
				} else {
					net += pesosBiasEntradas[i][j] * indv[j - 1];
				}

				netEntradas[i][j] = net;
			}
			ea = Math.ulp((double) (Math.exp((double) ((-1.0d) * (net)))));
			fnetEntradas[i] = (double) (1.0) / (1.0 + (ea));

		}
		
		for (int i = 0; i < numOutputsAux; i++) {
			for (int j = 0; j < mlp.getNumOutputs() + 1; j++) {
				if (j == 0) {
					netSaida += pesosBiasSaidas[i][j];
				} else {
					netSaida += pesosBiasSaidas[i][j] * fnetEntradas[j - 1];
				}

				netSaidas[i][j] = netSaida;
			}
			ea = (double) (Math.exp((double) ((-1.0d) * (netSaida))));
			fnetSaidas[i] = (double) (1.0) / (1.0 + (ea));

		}

		double[] errosCVLICVP = new double[2];
		for (int k = 0; k < mlp.entradas.length; k++) {
			if ((mlp.entradas[k][0] == Constants.AIS) && (mlp.entradas[k][1] == Constants.ano)
					&& (mlp.entradas[k][2] == Constants.mes)) {
				errosCVLICVP[0] = saidas[k][0]*Constants.MINIMIZACAO - fnetSaidas[0];
				errosCVLICVP[1] = saidas[k][1]*Constants.MINIMIZACAO - fnetSaidas[1];

			}
		}
		

		return errosCVLICVP;
	}
}
