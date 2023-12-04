import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) {

        int opcaoMenu;

        do {
            opcaoMenu = menu();

            switch (opcaoMenu) {
                case 1:// Registar Veiculo
                    int choice;
                    System.out.println("Marca: ");
                    System.out.println("Modelo: ");
                    System.out.println("Matricula: ");
                    System.out.println("Data de Registo do veiculo: ");
                    System.out.println("Potencia do veiculo(cv): ");
                    System.out.println("Capacidade da bateria(kwh): ");
                    System.out.println("Autonomia: ");
                    choice = Consola.lerInt("", 1, 6);

                    break;
                case 2:// Consultar Veiculo
                System.out.println("\n***************************************\n");
                System.out.println("\tMenu Consultar Veiculo\n");
                    break;
                case 3:// Registar Cliente
                System.out.println("\n***************************************\n");
                System.out.println("\tMenu Registar Cleinte\n");
                    Gestao.criarCliente();

                    break;
                case 4:// Consultar Cliente
                    Gestao.consultarCliente();
                System.out.println("\n***************************************\n");
                System.out.println("\tMenu Consultar Cliente\n");
                    break;
                case 5:// Registar posto de carregamento

                System.out.println("\n***************************************\n");
                System.out.println("\tMenu Registar posto de carregamento\n");

                    System.out.println("Codigo de posto: ");
                    System.out.println("Localização(morada): ");
                    System.out.println("Tipo de posto: ");
                    System.out.println("Custo por kWh(E): ");
                    System.out.println("Numero de veiculos que podem carregar em simultaneo: ");

                    break;
                case 6:// Consultar posto de carregamento
                System.out.println("\n***************************************\n");
                System.out.println("\tMenu Consultar posto de carregamento\n");
                    break;
                case 7:// Registar sessao de carregamento
                    System.out.println("Matricula: ");
                    System.out.println("Hora de inicio: ");
                    System.out.println("Hora de fim: ");
                    System.out.println("");
                    System.out.println("Matricula:");
                case 8:// Consultar sessao de carregamento
                System.out.println("\n***************************************\n");
                System.out.println("\tMenu Consultar sessao de carregamento\n");
                    break;
                case 9:// Registar pagamento de sessao
                System.out.println("\n***************************************\n");
                System.out.println("\tMenu Registar pagamento de sessao\n");
                    break;
                case 10:// Consultar pagamento de sessao
                System.out.println("\n***************************************\n");
                System.out.println("\tMenu Consultar pagamento de sessao\n");
                    break;
                case 11:// Consultar estatisticas
                System.out.println("\n***************************************\n");
                System.out.println("\tMenu Consultar estatisticas\n");
                    break;
                case 0:// Sair
                    System.out.println("Sair");
                    break;
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
        } while (menu() != 0);
        // ola

    }

    public static int menu() {

        System.out.println("\n_______________________________________\n");
        System.out.println("1  -> Registar Veiculo");
        System.out.println("2  -> Consultar Veiculo");
        System.out.println("3  -> Registar Cliente");
        System.out.println("4  -> Consultar Cliente");
        System.out.println("5  -> Registar posto de carregamento");
        System.out.println("6  -> Consultar posto de carregamento");
        System.out.println("7  -> Registar sessao de carregamento");
        System.out.println("8  -> Consultar sessao de carregamento");
        System.out.println("9  -> Registar pagamento de sessao");
        System.out.println("10 -> Consultar pagamento de sessao");
        System.out.println("11 -> Consultar estatisticas");
        System.out.println("\n0  -> Sair");
        System.out.println("_______________________________________");

        return Consola.lerInt("Opcao: ", 0, 11);
    }

}