import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Classe que disponibiliza um conjunto de metodos para ler dados a partir da
 * consola.
 *
 * @author Anabela
 * @version 1.1
 */
public class Consola {

    /**
     * Representa a consola.
     */
    public static Scanner sc = new Scanner(System.in);

    /**
     * Permite ler do teclado um valor inteiro compreendido entre um mnimo e um
     * máximo.
     *
     * @param texto texto a apresentar no ecrã.
     * @param min valor mánimo que poderá ser introduzido.
     * @param max valor máximo que poderá ser introduzido.
     * @return Valor inteiro.
     */
    public static int lerInt(String texto, int min, int max) {
        int num = -1;

        do {
            System.out.print(texto);
            if (sc.hasNextInt()) {
                num = sc.nextInt();
                if (num < min || num > max) {
                    System.out.println("Numero inválido. Numero deve estar compreendido entre " + min + " e " + max + ".");
                }
            } else {
                System.out.println("Tem de introduzir um numero inteiro!");
            }
            sc.nextLine();
        } while (num < min || num > max);
        return num;
    }

    /**
     * Permite ler do teclado um valor inteiro longo compreendido entre um
     * mínimo e um máximo.
     *
     * @param texto texto a apresentar no ecrã.
     * @param min valor mínimo que poderá ser introduzido.
     * @param max valor máximo que poderá ser introduzido.
     * @return Valor inteiro longo.
     */
    public static long lerLong(String texto, long min, long max) {
        long num = -1;

        do {
            System.out.print(texto);
            if (sc.hasNextLong()) {
                num = sc.nextLong();
                if (num < min || num > max) {
                    System.out.println("Numero invalido. Numero deve estar compreendido entre " + min + " e " + max + ".");
                }
            } else {
                System.out.println("Tem de introduzir um numero inteiro!");
            }
            sc.nextLine();
        } while (num < min || num > max);
        return num;
    }

    /**
     * Permite ler do teclado um valor real compreendido entre um mínimo e um
     * máximo.
     *
     * @param texto texto a apresentar no ecrã.
     * @param min valor mínimo que poderá ser introduzido.
     * @param max valor máximo que poderá ser introduzido.
     * @return Valor real.
     */
    public static float lerFloat(String texto, float min, float max) {
        float num = -1;

        do {
            System.out.print(texto);
            if (sc.hasNextFloat()) {
                num = sc.nextFloat();
                if (num < min || num > max) {
                    System.out.println("Numero invalido. Numero deve estar compreendido entre " + min + " e " + max + ".");
                }
            } else {
                System.out.println("Tem de introduzir um numero real!");
            }
            sc.nextLine();
        } while (num < min || num > max);
        return num;
    }

    /**
     * Permite ler do teclado um valor real (double) compreendido entre um
     * mínimo e um máximo.
     *
     * @param texto texto a apresentar no ecrã.
     * @param min valor mínimo que poderá ser introduzido.
     * @param max valor máximo que poderá ser introduzido.
     * @return Valor real (double).
     */
    public static double lerDouble(String texto, double min, double max) {
        double num = -1;

        do {
            System.out.print(texto);
            if (sc.hasNextDouble()) {
                num = sc.nextDouble();
                if (num < min || num > max) {
                    System.out.println("Numero invalido. Numero deve estar compreendido entre " + min + " e " + max + ".");
                }
            } else {
                System.out.println("Tem de introduzir um numero real!");
            }
            sc.nextLine();
        } while (num < min || num > max);
        return num;
    }

    /**
     * Permite ler do teclado um caracter dentro de um conjunto de valores
     * (opcoes) possíveis.
     *
     * @param texto texto a apresentar no ecrã.
     * @param opcoes valores possíveis de entrada.
     * @return Caracter.
     */
    public static char lerChar(String texto, String opcoes) {
        char ch = '\0';

        do {
            System.out.print(texto);
            ch = sc.next().charAt(0);
            if (opcoes.indexOf(ch) == -1) {
                System.out.println("Opcao invalida. Caracter deve ser um dos seguintes: " + opcoes + ".");
            }
            sc.nextLine();
        } while (opcoes.indexOf(ch) == -1);
        return ch;
    }

    /**
     * Permite ler do teclado uma string.
     *
     * @param texto texto a apresentar no ecrã.
     * @return String.
     */
    public static String lerString(String texto) {
        String str = "";

        do {
            System.out.print(texto);
            str = sc.nextLine();
            if (str.length() == 0) {
                System.out.println("String vazia.");
            }

        } while (str.length() == 0);
        return str;
    }

    /**
     * Permite ler do teclado uma data no formato indicado.
     *
     * @param texto
     * @param formatoData
     * @return objeto Date
     */
    public static Date lerData(String texto, String formatoData) {
        String str = "";
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat(formatoData);

        boolean erroData;
        do {
            erroData = false;
            try {
                do {
                    System.out.print(texto);
                    str = sc.nextLine();
                    if (str.length() == 0) {
                        System.out.println("String vazia.");
                    }

                } while (str.length() == 0);
                date = dateFormat.parse(str);

            } catch (ParseException e) {
                erroData = true;
                System.out.println("Data invalida. Deve estar no formato: " + formatoData);
            }
        } while (erroData);
        return date;
    }
    
/**
 * Permite travar a execução do programa até que o utilizador pressione a tecla Enter.
 * @return void
 */
    public static void PressioneEnterParaContinuar() {
        System.out.println("Pressione Enter para continuar...");
        sc.nextLine();
    }
}
