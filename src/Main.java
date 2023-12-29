
import java.io.*;

/**
 * Classe Main
 *
 * @version 1.0
 * @Author Pedro Ferreira nº 2222035 | Bernardo Santos nº 2222033
 */

public class Main {
    /**
     * Este é o método principal do programa.
     * <p>
     * Ele entra num ‘loop’ onde exibe o menu principal do programa e lê a opção do utilizador. Dependendo da opção escolhida pelo utilizador, chama o método correspondente da classe Gestao.
     * <p>
     * O ‘loop’ continua até que o utilizador escolha a opção para sair. Quando isso acontece, grava os dados da gestão num ficheiro.
     */
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
                case 5:// Alterar Dados /Remover Cliente
                    int opcaoAlterarCliente;
                    do {
                        opcaoAlterarCliente = menuAlterarCliente();

                        switch (opcaoAlterarCliente) {
                            case 1:// Alterar dados cliente
                                gestao.alterarDadosCliente();
                                break;
                            case 2:// Remover cliente
                                gestao.removerCliente();
                                break;
                            case 0:// Sair
                                break;
                            default:
                                System.out.println("Opcao invalida");
                                break;
                        }
                    } while (opcaoAlterarCliente != 0);
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
                    int opcaoEstatistica;
                    do {
                        opcaoEstatistica = menuEstatistica();

                        switch (opcaoEstatistica) {
                            case 1:// Listagem dos 3 postos com maior liquidacao
                                gestao.listagemPostosMaiorLiquidacao();
                                break;
                            case 2:// Listagem de sessoes de carregamento cujo custo de carregamento foi superior a
                                // X euros
                                gestao.listarSessoesComCustoSuperiorX();
                                break;
                            case 3:// Total de sessoes de carregamento realizadas (por cliente)
                                gestao.totalSessoesPorCliente();
                                break;
                            case 4:// Media de energia consumida por posto de carregamento e por tipo de veículo
                                // (eletrico ou hibrido)
                                gestao.mediaEnergiaPorPostoETipoVeiculo();
                                break;
                            case 5:// Listafem de pagamentos por efetuar (por cliente)
                                gestao.listagemPagamentosPorEfetuar();
                                break;
                            case 6:// Historico de sessoes de carregamento (por posto de carregamento)
                                gestao.historicoSessoesPorPosto();
                                break;
                            case 0:// Sair
                                break;
                            default:
                                System.out.println("Opcao invalida");
                                break;
                        }
                    } while (opcaoEstatistica != 0);
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

    /**
     * Este método é usado para exibir o menu principal do programa e ler a opção do utilizador.
     * <p>
     * O menu lista todas as opções disponíveis no programa, incluindo registrar e consultar veículos, clientes, postos de carregamento, sessões de carregamento e pagamentos, alterar dados ou remover clientes, e consultar estatísticas.
     * <p>
     * O utilizador é solicitado a fornecer a opção desejada. A opção é lida até que um valor válido seja fornecido.
     * <p>
     * O método retorna a opção escolhida pelo utilizador.
     *
     * @return A opção escolhida pelo utilizador.
     */
    public static int menu() {
        int opcao;
        System.out.println("\n_______________________________________");
        System.out.println("\tMenu Principal\n");
        System.out.println("1  -> Registar Veiculo");
        System.out.println("2  -> Consultar Veiculo");
        System.out.println("3  -> Registar Cliente");
        System.out.println("4  -> Consultar Cliente");
        System.out.println("5  -> Alterar Dados / Remover Cliente");
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

    /**
     * Este método é usado para exibir o menu de estatísticas do programa e ler a opção do utilizador.
     * <p>
     * O menu lista todas as opções de estatísticas disponíveis no programa, incluindo a listagem dos 3 postos com maior liquidação, listagem de sessões de carregamento cujo custo foi superior a X euros, total de sessões de carregamento realizadas por cliente, média de energia consumida por posto de carregamento e por tipo de veículo, listagem de pagamentos por efetuar e histórico de sessões de carregamento por posto de carregamento.
     * <p>
     * O utilizador é solicitado a fornecer a opção desejada. A opção é lida até que um valor válido seja fornecido.
     * <p>
     * O método retorna a opção escolhida pelo utilizador.
     *
     * @return A opção escolhida pelo utilizador.
     */
    public static int menuEstatistica() {
        int opcao;
        System.out.println("\n_______________________________________");
        System.out.println("\tMenu Estatisticas\n");
        System.out.println("1  -> Listagem dos 3 postos com maior liquidacao");
        System.out
                .println("2  -> Listagem de sessoes de carregamento cujo custo de carregamento foi superior a X euros");
        System.out.println("3  -> Total de sessoes de carregamento realizadas (por cliente)");
        System.out.println(
                "4  -> Media de energia consumida por posto de carregamento e por tipo de veiculo (eletrico ou hibrido)");
        System.out.println("5  -> Listagem de pagamentos por efetuar (por cliente)");
        System.out.println("6  -> Historico de sessoes de carregamento (por posto de carregamento)");
        System.out.println("\n0  -> Sair");

        do {
            opcao = Consola.lerInt("Opcao: ", 0, 6);
        } while (opcao < 0 || opcao > 6);
        return opcao;
    }

    /**
     * Este método é usado para exibir o menu de alterações do cliente e ler a opção do utilizador.
     * <p>
     * O menu lista todas as opções disponíveis para alterar os dados do cliente, incluindo alterar dados do cliente e remover cliente.
     * <p>
     * O utilizador é solicitado a fornecer a opção desejada. A opção é lida até que um valor válido seja fornecido.
     * <p>
     * O método retorna a opção escolhida pelo utilizador.
     *
     * @return A opção escolhida pelo utilizador.
     */
    public static int menuAlterarCliente() {
        int opcao;
        System.out.println("\n_______________________________________");
        System.out.println("\tMenu Alterar Cliente\n");
        System.out.println("1  -> Alterar dados cliente");
        System.out.println("2  -> Remover cliente");
        System.out.println("\n0  -> Sair");

        do {
            opcao = Consola.lerInt("Opcao: ", 0, 2);
        } while (opcao < 0 || opcao > 2);
        return opcao;
    }

    /**
     * Este método é usado para gravar os dados da gestão num arquivo.
     * <p>
     * Ele tenta abrir um fluxo de saída para o arquivo "Gestao.dat". Se o arquivo não for encontrado, exibe uma mensagem informando que o arquivo não foi encontrado.
     * <p>
     * Em seguida, tenta escrever o objeto Gestao no arquivo. Se ocorrer um erro durante a gravação, exibe uma mensagem informando que ocorreu um erro ao gravar o arquivo.
     * <p>
     * Se a gravação for bem-sucedida, exibe uma mensagem informando que o arquivo foi gravado com sucesso.
     *
     * @param gestao O objeto Gestao a ser gravado no arquivo.
     */
    public static void gravarFicheiro(Gestao gestao) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Gestao.dat"));
            out.writeObject(gestao);
            out.close();
            System.out.println("Ficheiro gravado com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro nao encontrado!");
        } catch (IOException e) {
            System.out.println("Erro a gravar ficheiro!");
        }
    }

    /**
     * Este método é usado para ler os dados da gestão de um arquivo.
     * <p>
     * Ele tenta abrir um fluxo de entrada para o arquivo "Gestao.dat". Se o arquivo não for encontrado, exibe uma mensagem informando que o arquivo não foi encontrado e retorna null.
     * <p>
     * Em seguida, tenta ler o objeto Gestao do arquivo. Se ocorrer um erro durante a leitura, exibe uma mensagem informando que ocorreu um erro ao ler o arquivo e retorna null.
     * <p>
     * Se a leitura for bem-sucedida, exibe uma mensagem informando que o arquivo foi lido com sucesso e retorna o objeto Gestao lido do arquivo.
     *
     * @return O objeto Gestao lido do arquivo, ou null se o arquivo não foi encontrado ou ocorreu um erro durante a leitura.
     */
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