import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) {
        Gestao gestao = new Gestao();
        lerFicheiro(gestao);
        int opcaoMenu;

        do {
            opcaoMenu = menu();

            switch (opcaoMenu) {
                case 1:// Registar Veiculo
                    gestao.criarVeiculo();
                    break;
                case 2:// Consultar Veiculo
                    // TODO na primeira ve que iniciamos o menu conculta nao aparece nada
                    gestao.consultarVeiculo();
                    break;
                case 3:// Registar Cliente
                    gestao.criarCliente();
                    break;
                case 4:// Consultar Cliente
                    gestao.consultarCliente();
                    break;
                case 5:// Registar posto de carregamento
                    gestao.criarPostoCarregamento();
                    break;
                case 6:// Consultar posto de carregamento
                    gestao.consultarPostoCarregamento();
                    break;
                case 7:// Registar sessao de carregamento
                    System.out.println("Matricula: ");
                    System.out.println("Hora de inicio: ");
                    System.out.println("Hora de fim: ");
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
                    System.out.println("FIM DO PROGRAMA");
                    System.out.println("TO BE CONTINUED...");

                    break;
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
        } while (opcaoMenu != 0);

        gravarFicheiro(gestao);
    }

    public static int menu() {
        int opcao;
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


        do {
            opcao = Consola.lerInt("Opcao: ", 0, 11);
        } while (opcao < 0 || opcao > 11);
        return opcao;
    }

    public static void gravarFicheiro(Gestao gestao) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Gestao.dat"));
            out.writeObject(gestao);
            out.close();
            System.out.println("Ficheiro gravado com sucesso!");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Ficheiro nao encontrado!");
        }
    }

    public static void lerFicheiro(Gestao gestao) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Gestao.dat"));
            gestao = (Gestao) in.readObject();
            in.close();
            System.out.println("Ficheiro lido com sucesso!");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Ficheiro nao encontrado!");
        }
    }

}