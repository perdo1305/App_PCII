
/**
 * @author Pedro Ferreira
 * @author Bernardo Santos
 */

public class Main {
    public static void main(String[] args) {

        int opcaoMenu;


        do {
            opcaoMenu = menu();
            switch (opcaoMenu) {
                case 1://Registar Veiculo
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
                case 2://Consultar Veiculo

                    break;
                case 3://Registar Cliente

                    System.out.println("Nome: ");
                    
                    System.out.println("NIF(unico): ");
                    System.out.println("Morada: ");
                    System.out.println("Telefone: ");
                    System.out.println("Email: ");
                    System.out.println("Data de nascimento: ");

                    break;
                case 4://Consultar Cliente

                    break;
                case 5://Registar posto de carregamento
                    System.out.println("Codigo de posto: ");
                    System.out.println("Localização(morada): ");
                    System.out.println("Tipo de posto: ");
                    System.out.println("Custo por kWh(E): ");
                    System.out.println("Numero de veiculos que podem carregar em simultaneo: ");

                    break;
                case 6://Consultar posto de carregamento

                    break;
                case 7://Registar sessao de carregamento
                    System.out.println("Codigo de posto: ");
                    System.out.println("Localização(morada): ");
                    System.out.println("Tipo de posto: ");
                    System.out.println("Custo por kWh(E): ");
                    System.out.println("Numero de veiculos que podem carregar em simultaneo: ");
                    break;
                case 8://Consultar sessao de carregamento

                    break;
                case 9://Registar pagamento de sessao

                    break;
                case 10://Consultar pagamento de sessao

                    break;
                case 11://Consultar estatisticas

                    break;
                case 0://Sair
                    System.out.println("Sair");
                    break;
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
        } while (menu() != 0);
        //ola

    }

    public static int menu() {

        System.out.println("\n_________________________________");
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
        System.out.println("0  -> Sair");
        System.out.println("_________________________________");

        return Consola.lerInt("Opcao: ", 0, 11);
    }
}