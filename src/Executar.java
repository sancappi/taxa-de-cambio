import conversor.Conversor;
import excecao.ErroAoEfetuarConversaoException;

import java.util.Scanner;

public class Executar {
    public static void main(String[] args) throws ErroAoEfetuarConversaoException {
        Scanner valorDigitado = new Scanner(System.in);

        while (true) {
            System.out.println("\n******** Escolha a moeda de origem ********\n");
            System.out.println("1 - ARS Peso argentino");
            System.out.println("2 - BOB Boliviano boliviano");
            System.out.println("3 - BRL Real brasileiro");
            System.out.println("4 - CLP Peso chileno");
            System.out.println("5 - COP Peso colombiano");
            System.out.println("6 - USD Dólar americano");
            System.out.println("0 - Sair\n");
            System.out.println("*******************************************");


            int numeroMoedaOrigem = valorDigitado.nextInt();

            if (numeroMoedaOrigem == 0) {
                System.out.println("Aplicação encerrada.");
                break;
            } else if(numeroMoedaOrigem < 1 || numeroMoedaOrigem > 6){
                System.out.println("Por favor, digite um número válido.");
                continue;
            }


            System.out.println("\n******** Escolha a moeda de destino *******\n");
            System.out.println("1 - ARS Peso argentino");
            System.out.println("2 - BOB Boliviano boliviano");
            System.out.println("3 - BRL Real brasileiro");
            System.out.println("4 - CLP Peso chileno");
            System.out.println("5 - COP Peso colombiano");
            System.out.println("6 - USD Dólar americano");
            System.out.println("\n*******************************************");


            int numeroMoedaDestino = valorDigitado.nextInt();

            if(numeroMoedaDestino < 1 || numeroMoedaDestino > 6){
                System.out.println("Por favor, recomece o processo e utilize \nos números listados no menu.");
                continue;
            }

            System.out.println("Digite o valor que deseja converter: ");
            System.out.println("\n*******************************************");
            double valorParaConverter = valorDigitado.nextDouble();

            String moedaOrigem = getMoeda(numeroMoedaOrigem);
            String moedaDestino = getMoeda(numeroMoedaDestino);

            Conversor conversor = new Conversor();
            conversor.conversor(moedaOrigem, moedaDestino, valorParaConverter);
        }
        valorDigitado.close();
    }

    private static String getMoeda(int numero) {
        switch (numero) {
            case 1:
                return "ARS";
            case 2:
                return "BOB";
            case 3:
                return "BRL";
            case 4:
                return "CLP";
            case 5:
                return "COP";
            case 6:
                return "USD";
            default:
                return "";
        }
    }
}

