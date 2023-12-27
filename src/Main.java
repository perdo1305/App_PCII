/***
 * @Author: Pedro Ferreira - 2222035
 * @Author: Bernardo Santos -2222033
 */

import java.io.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("█▀█ █▀▀ █▀▄ █▀█ █▀█   █▀▀ █▀▀ █▀█ █▀█ █▀▀ █ █▀█ ▄▀█");
        System.out.println("█▀▀ ██▄ █▄▀ █▀▄ █▄█   █▀░ ██▄ █▀▄ █▀▄ ██▄ █ █▀▄ █▀█");
        System.out.println();
        System.out.println("█▄▄ █▀▀ █▀█ █▄░█ ▄▀█ █▀█ █▀▄ █▀█   █▀ ▄▀█ █▄░█ ▀█▀ █▀█ █▀");
        System.out.println("█▄█ ██▄ █▀▄ █░▀█ █▀█ █▀▄ █▄▀ █▄█   ▄█ █▀█ █░▀█ ░█░ █▄█ ▄█\n");

        Gestao gestao = lerFicheiro();
        if (gestao == null) {
            gestao = new Gestao();
        }

        int opcaoMenu;
        do {
            opcaoMenu = menu();

            switch (opcaoMenu) {
                case 1:// Registar Veiculo
                    gestao.criarVeiculo();
                    break;
                case 2:// Consultar Veiculo
                    gestao.consultarVeiculo();
                    break;
                case 3:// Registar Cliente
                    gestao.criarCliente();
                    break;
                case 4:// Consultar Cliente
                    gestao.consultarCliente();
                    break;
                case 5:// Alterar Dados Cliente
                    gestao.alterarDadosCliente();
                    break;
                case 6:// Registar posto de carregamento
                    gestao.criarPostoCarregamento();
                    break;
                case 7:// Consultar posto de carregamento
                    gestao.consultarPostoCarregamento();
                    break;
                case 8:// Registar sessao de carregamento
                    gestao.menuRegistarSessaoCarregamento();
                    break;
                case 9:// Consultar sessao de carregamento
                    gestao.menuConsultarSessaoCarregamento();
                    break;
                case 10:// Registar pagamento de sessao
                    gestao.menuRegistarPagamento();
                    break;
                case 11:// Consultar pagamento de sessao
                    gestao.menuConsultarPagamento();
                    break;
                case 12:// Consultar estatisticas
                    System.out.println("\n***************************************\n");
                    System.out.println("\tMenu Consultar estatisticas\n");
                    break;
                case 0:// Sair

                    System.out.println("############# FIM DO PROGRAMA #############\n");
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
        System.out.println("\n_______________________________________");
        System.out.println("\tMenu Principal\n");
        System.out.println("1  -> Registar Veiculo");
        System.out.println("2  -> Consultar Veiculo");
        System.out.println("3  -> Registar Cliente");
        System.out.println("4  -> Consultar Cliente");
        System.out.println("5  -> Alterar Dados Cliente");
        System.out.println("6  -> Registar posto de carregamento");
        System.out.println("7  -> Consultar posto de carregamento");
        System.out.println("8  -> Registar sessao de carregamento");
        System.out.println("9  -> Consultar sessao de carregamento");
        System.out.println("10 -> Registar pagamento de sessao");
        System.out.println("11 -> Consultar pagamento de sessao");
        System.out.println("12 -> Consultar estatisticas");
        System.out.println("\n0  -> Sair");

        do {
            opcao = Consola.lerInt("Opcao: ", 0, 12);
        } while (opcao < 0 || opcao > 12);
        return opcao;
    }

    public static void gravarFicheiro(Gestao gestao) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Gestao.dat"));
            out.writeObject(gestao);
            out.close();
            System.out.println("Ficheiro gravado com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

    public static Gestao lerFicheiro() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Gestao.dat"));
            Gestao gestao = (Gestao) in.readObject();
            in.close();
            System.out.println("Ficheiro lido com sucesso!");
            return gestao;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Ficheiro nao encontrado!");
            return null;
        }
    }
}