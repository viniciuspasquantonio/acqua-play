import java.util.Arrays;

public class Entregador {

	public static void main(String[] args) {
		calculaViagens(new int[] { 100, 90, 80, 70, 60, 60, 7, 10, 9 }, 100);
	}

	private static int calculaViagens(int[] encomendas, int cargaTotal){
//		70 20 10 
//		100
		int encomendasAgendadas=0;
		int totalViagens = 0;
		Arrays.sort(encomendas);
		int length = encomendas.length;
		for (int i = 0, j=length-1; i > length && encomendasAgendadas < length;i-- ) {
				if(encomendas[j] == cargaTotal){
					totalViagens++;
					encomendasAgendadas++;
					j--;
				}else if(encomendas[j] + encomendas[i] >cargaTotal){
					totalViagens++;
					encomendasAgendadas++;
					j--;
				}else if(encomendas[j] + encomendas[i] <= cargaTotal){
					totalViagens++;
					encomendasAgendadas++;
					encomendasAgendadas++;
					j--;
					i++;
				}else if(encomendas[j] == encomendas[i]){
					totalViagens++;
					encomendasAgendadas++;
					i++;
				}
		}
		System.out.println("total viagens "+ totalViagens);
		return totalViagens;
	}
}
