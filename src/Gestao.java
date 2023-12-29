
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.io.Serializable;

/**
 * Esta classe é usada para gerir todos os dados do programa.
 * <p>
 * Também contém métodos para criar, consultar, alterar e remover veículos, clientes e postos de carregamento.
 * <p>
 * Também contém métodos para criar, consultar, alterar e remover sessões de carregamento.
 * <p>
 * Também contém métodos para criar, consultar, alterar e remover pagamentos.
 * <p>
 * Também contém métodos para gerar estatísticas.
 * <p>
 * Também contem metodos para proteção dos dados inseridos pelo utilizador
 */
public class Gestao implements Serializable {
    static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private final ArrayList<Cliente> clientes = new ArrayList<>();
    private final ArrayList<Veiculo> veiculos = new ArrayList<>();
    private final ArrayList<PostoCarregamento> postos = new ArrayList<>();
    private final ArrayList<SessaoCarregamento> sessoes = new ArrayList<>();
    private final ArrayList<Pagamento> pagamentos = new ArrayList<>();

    private final Map<String, SessaoCarregamento> sessoesCarregamento = new HashMap<>();
    public Gestao() {
    }

    /**
     * Cria um veículo, seja ele elétrico ou híbrido.
     * <p>
     * O utilizador é solicitado a fornecer detalhes como tipo de veículo, marca,
     * modelo, matrícula, data de registro, potência, capacidade da bateria e
     * autonomia.
     * <p>
     * Para veículos elétricos, o utilizador também precisa fornecer a velocidade de
     * carregamento e o tempo de carregamento é calculado.
     * <p>
     * Para veículos híbridos, o utilizador precisa fornecer a capacidade do depósito de
     * combustível e o consumo de combustível.
     * <p>
     * O veículo é então adicionado à lista de veículos.
     */
    public void criarVeiculo() {
        String marca, modelo, matricula, tipo_veiculo;
        Date data_registo = null;
        int potencia, capacidade_bateria, autonomia;

        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Criar veiculo\n");
        // tipo de veiculo E (eletrico) ou H (hibrido)
        do {
            tipo_veiculo = Consola.lerString("Tipo de veiculo ([E]letrico/[H]ibrido): ");
        } while (!tipo_veiculo.equalsIgnoreCase("E") && !tipo_veiculo.equalsIgnoreCase("H"));

        do {
            marca = Consola.lerString("Marca: ");
        } while (marca.isEmpty());
        do {
            modelo = Consola.lerString("Modelo: ");
        } while (modelo.isEmpty());
        do {
            // verificar se o formato da matricula esta correto
            matricula = getStringMatricula();
            // colocar a matricula em maiusculas
            matricula = matricula.toUpperCase();
        } while (matricula.isEmpty());

        boolean error;
        do {
            error = false;
            try {
                String data = Consola.lerString("Data de registo do veiculo (dd-mm-yyyy): ");
                data_registo = dateFormat.parse(data);
                if (Integer.parseInt(data.substring(0, 2)) < 1 || Integer.parseInt(data.substring(0, 2)) > 31
                        || Integer.parseInt(data.substring(3, 5)) < 1 || Integer.parseInt(data.substring(3, 5)) > 12
                        || Integer.parseInt(data.substring(6, 10)) < 1900
                        || Integer.parseInt(data.substring(6, 10)) > 2024) {
                    System.out.println("Data invalida");
                    error = true;
                }
            } catch (Exception e) {
                System.out.println("Data invalida");
                error = true;
            }
        } while (error);

        do {
            potencia = Consola.lerInt("Potencia do veiculo (Cv): ", 1, 999999999);
        } while (potencia == 0);
        do {
            capacidade_bateria = Consola.lerInt("Capacidade da bateria (kwh): ", 1, 999999999);
        } while (capacidade_bateria == 0);
        do {
            autonomia = Consola.lerInt("Autonomia (Km/carga): ", 1, 999999999);
        } while (autonomia == 0);

        float tempo_carregamento;
        double velocidadeCarregamento = 0.00;

        if (tipo_veiculo.equalsIgnoreCase("E")) {
            do {
                System.out.println("Velocidade de carregamento (Kw/h): ");
                System.out.println("1 - Normal(2.3Kw/h)");
                System.out.println("2 - Rapido(7.4Kw/h)");
                System.out.println("3 - Ultra-Rapido(160Kw/h)");
                int opcao = Consola.lerInt("Opcao:", 1, 3);
                velocidadeCarregamento = switch (opcao) {
                    case 1 -> 2.3;
                    case 2 -> 7.4;
                    case 3 -> 160;
                    default -> 0;
                };

            } while (velocidadeCarregamento == 0);

            do {
                // valor da valocidade e capacidade da bateria
                tempo_carregamento = (float) (capacidade_bateria / velocidadeCarregamento);
                double minutos = tempo_carregamento * 60;
                // transformar minutos em horas e minutos
                System.out.println("Tempo de carregamento (0% -> 100%): " + (int) minutos / 60 + " horas e "
                        + (int) minutos % 60 + " minutos");

            } while (tempo_carregamento == 0);

            VeiculoEletrico veiculo = new VeiculoEletrico(marca, modelo, matricula, data_registo, autonomia,
                    capacidade_bateria, autonomia, velocidadeCarregamento, tempo_carregamento);
            veiculos.add(veiculo);
            System.out.println("Veiculo criado com sucesso!");
            Consola.PressioneEnterParaContinuar();
        } else {
            double consumo_combustivel;
            do {
                consumo_combustivel = Consola.lerDouble("Consumo de combustivel: ", 1, 999999999);
            } while (consumo_combustivel == 0);
            int cilindrada;
            do {
                cilindrada = Consola.lerInt("Cilindrada: ", 1, 999999999);
            } while (cilindrada == 0);
            double emissao;
            do {
                emissao = Consola.lerDouble("Emissao: ", 1, 999999999);
            } while (emissao == 0);

            VeiculoHibrido veiculo = new VeiculoHibrido(marca, modelo, matricula, data_registo, autonomia,
                    capacidade_bateria, autonomia, velocidadeCarregamento, cilindrada, consumo_combustivel, emissao);
            veiculos.add(veiculo);
            System.out.println("Veiculo criado com sucesso");
            Consola.PressioneEnterParaContinuar();
        }
    }

    /**
     * Procura um veículo na arraylist e retorna a sua posicao
     *
     * @param matricula matricula do veiculo a procurar
     * @return posicao do veiculo na arraylist
     */
    private int procurarVeiculo(String matricula) {
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getMatricula().equalsIgnoreCase(matricula)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Cria um veículo, seja ele elétrico ou híbrido.
     * <p>
     * O utilizador é solicitado a fornecer detalhes como tipo de veículo, marca,
     * modelo, matrícula, data de registro, potência, capacidade da bateria e
     * autonomia.
     * <p>
     * Para veículos elétricos, o utilizador também precisa fornecer a velocidade de
     * carregamento e o tempo de carregamento é calculado.
     * <p>
     * Para veículos híbridos, o utilizador precisa fornecer a capacidade do depósito de
     * combustível e o consumo de combustível.
     * <p>
     * O veículo é então adicionado à lista de veículos.
     */
    public void consultarVeiculo() {
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Consultar veiculo\n");
        String matricula;
        if (MostrarVeiculos()) {
            Consola.PressioneEnterParaContinuar();
            return;
        }
        do {
            matricula = getStringMatricula();
        } while (matricula.isEmpty());
        // return posicao do veiculo na arraylist
        int posicao = procurarVeiculo(matricula);
        if (posicao == -1) {
            System.out.println("Veiculo não encontrado");
        } else {
            System.out.println("\t | |");
            System.out.println("\t V V");
            System.out.println("\n" + veiculos.get(posicao).toString());
        }
        Consola.PressioneEnterParaContinuar();
    }

    /**
     * Mostra todos os veículos registados
     *
     * @return true se nao existirem veiculos registados
     */
    private boolean MostrarVeiculos() {
        if (veiculos.isEmpty()) {
            System.out.println("Não existem veiculos registados");
            return true;
        }
        System.out.println("Veiculos registados: ");
        for (Veiculo veiculo : veiculos) {
            // formato "XX-XX-XX (marca / modelo)"
            System.out.println("-> Matricula: " + veiculo.getMatricula() + " (" + veiculo.getMarca() + " / "
                    + veiculo.getModelo() + ")");
        }
        return false;
    }

    /**
     * Recebe a matricula do veiculo e verifica se esta no formato XX-XX-XX
     *
     * @return matricula do veiculo em formato XX-XX-XX
     */
    private String getStringMatricula() {
        String matricula;
        matricula = Consola.lerString("Matricula (XX-XX-XX): ");
        if (matricula.length() != 8) {
            System.out.println("Matricula invalida");
            matricula = "";
        } else {
            if (matricula.charAt(2) != '-' || matricula.charAt(5) != '-') {
                System.out.println("Matricula invalida");
                matricula = "";
            }
        }
        return matricula;
    }

    /**
     * Cria um cliente.
     * <p>
     * O utilizador é solicitado a fornecer detalhes como nome, telemóvel, NIF, morada,
     * email e data de nascimento.
     * <p>
     * O método verifica se o NIF fornecido já existe. Se existir, o utilizador é
     * solicitado a fornecer um NIF diferente.
     * <p>
     * Se a data de nascimento fornecida for inválida, o utilizador é solicitado a
     * fornecer uma data válida.
     * <p>
     * Após todos os detalhes serem fornecidos corretamente, um novo objeto Cliente
     * é criado e adicionado à lista de clientes.
     * <p>
     * Por fim, é exibida uma mensagem informando que o cliente foi registrado com
     * sucesso.
     */
    public void criarCliente() {
        String morada, email, nome;
        int nif, telemovel;
        Date data_nascimento = null;

        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Criar cliente\n");
        do {
            nome = Consola.lerString("Nome: ");
        } while (nome.isEmpty());
        do {
            telemovel = Consola.lerInt("Telemovel: ", 100000000, 999999999);
        } while (telemovel == 0);
        do {
            nif = Consola.lerInt("NIF: ", 100000000, 999999999);
            // verificar se o nif ja existe
            if (procurarCliente(nif) != -1) {
                System.out.println("NIF já existe");
                nif = 0;
            }
        } while (nif == 0);
        do {
            morada = Consola.lerString("Morada: ");
        } while (morada == null || morada.isEmpty());
        do {
            email = Consola.lerString("Email: ");
        } while (email.isEmpty());
        boolean error = false;
        do {
            try {
                data_nascimento = dateFormat.parse(Consola.lerString("Data de nascimento dd-mm-yyyy: "));
            } catch (Exception e) {
                System.out.println("Data invalida");
                error = true;
            }
        } while (error);
        Cliente cliente = new Cliente(nome, nif, morada, telemovel, email, data_nascimento);
        clientes.add(cliente);
        System.out.println("Cliente registado com sucesso");
        Consola.PressioneEnterParaContinuar();
    }

    /**
     * Consulta um cliente na lista de clientes.
     * <p>
     * O utilizador é solicitado a fornecer o NIF do cliente que deseja consultar.
     * <p>
     * Se o NIF fornecido corresponder a um cliente na lista, os detalhes desse
     * cliente são exibidos.
     * <p>
     * Se o NIF fornecido não corresponder a nenhum cliente na lista, é exibida uma
     * mensagem informando que o cliente não foi encontrado.
     */
    public void consultarCliente() {
        int nif;
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Consultar cliente\n");

        if (MostrarClientes()) {
            Consola.PressioneEnterParaContinuar();
            return;
        }
        do {
            nif = Consola.lerInt("NIF do cliente a procurar: ", 100000000, 999999999);
        } while (nif == 0);
        // return posicao do cliente na arraylist
        int posicao = procurarCliente(nif);
        if (posicao == -1) {
            System.out.println("Cliente não encontrado");
        } else {
            System.out.println("\t | |");
            System.out.println("\t V V");
            System.out.println("\n" + clientes.get(posicao).toString());
        }
        Consola.PressioneEnterParaContinuar();
    }

    /**
     * Procura um cliente na lista de clientes pelo seu NIF.
     *
     * @param nif O NIF do cliente a ser procurado.
     * @return O índice do cliente na lista se encontrado, -1 caso contrário.
     */
    public int procurarCliente(int nif) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getNif() == nif) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Altera os dados de um cliente existente.
     * <p>
     * O utilizador é solicitado a fornecer o NIF do cliente cujos dados deseja
     * alterar.
     * <p>
     * Se o cliente com o NIF fornecido for encontrado, o utilizador é então solicitado
     * a escolher o dado que deseja alterar.
     * <p>
     * O utilizador pode escolher alterar o nome, telemóvel, NIF, morada, email ou data
     * de nascimento do cliente.
     * <p>
     * Após a alteração, é exibida uma mensagem informando que os dados foram
     * alterados com sucesso.
     * <p>
     * Se o cliente com o NIF fornecido não for encontrado, é exibida uma mensagem
     * informando que o cliente não foi encontrado.
     */
    public void alterarDadosCliente() {
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Alterar dados cliente\n");

        if (MostrarClientes()) {
            Consola.PressioneEnterParaContinuar();
            return;
        }
        System.out.println("Inisira o NIF do cliente que pretende alterar os dados: ");
        int nif = Consola.lerInt("", 100000000, 999999999);
        int posicao = procurarCliente(nif);
        if (procurarCliente(nif) == -1) {
            System.out.println("Cliente não encontrado");
        } else {
            System.out.println("O que pretende alterar?");
            System.out.println("1 - Nome");
            System.out.println("2 - Telemovel");
            System.out.println("3 - NIF");
            System.out.println("4 - Morada");
            System.out.println("5 - Email");
            System.out.println("6 - Data de nascimento");
            int opcao = Consola.lerInt("Opcao:", 1, 6);
            switch (opcao) {
                case 1:
                    do {
                        String nome = Consola.lerString("Novo Nome: ");
                        clientes.get(posicao).setNome(nome);
                    } while (clientes.get(posicao).getNome().isEmpty());
                    break;
                case 2:
                    do {
                        int telemovel = Consola.lerInt("Novo Telemovel: ", 100000000, 999999999);
                        clientes.get(posicao).setTelemovel(telemovel);
                    } while (clientes.get(posicao).getTelemovel() == 0);
                    break;
                case 3:
                    do {
                        int nif2 = Consola.lerInt("Novo NIF: ", 100000000, 999999999);
                        clientes.get(posicao).setNif(nif2);
                    } while (clientes.get(posicao).getNif() == 0);
                    break;
                case 4:
                    do {
                        String morada = Consola.lerString("Nova Morada: ");
                        clientes.get(posicao).setMorada(morada);
                    } while (clientes.get(posicao).getMorada().isEmpty());
                    break;
                case 5:
                    do {
                        String email = Consola.lerString("Novo Email: ");
                        clientes.get(posicao).setEmail(email);
                    } while (clientes.get(posicao).getEmail().isEmpty());
                    break;
                case 6:
                    Date data_nascimento = null;
                    boolean error = false;
                    do {
                        try {
                            data_nascimento = dateFormat.parse(Consola.lerString("Novas Data de nascimento: "));
                        } catch (Exception e) {
                            System.out.println("Data invalida");
                            error = true;
                        }
                    } while (error);
                    clientes.get(posicao).setData_nascimento(data_nascimento);
                    break;
                default:
                    break;
            }
            System.out.println("Dados alterados com sucesso");
            Consola.PressioneEnterParaContinuar();
        }
    }

    /**
     * Remove um cliente da lista de clientes.
     * <p>
     * O utilizador é solicitado a fornecer o NIF do cliente que deseja remover.
     * <p>
     * Se o cliente com o NIF fornecido for encontrado, o cliente é removido da
     * lista e é exibida uma mensagem informando que o cliente foi removido com
     * sucesso.
     * <p>
     * Se o cliente com o NIF fornecido não for encontrado, é exibida uma mensagem
     * informando que o cliente não foi encontrado.
     */
    public void removerCliente() {
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Remover cliente\n");

        if (MostrarClientes()) {
            Consola.PressioneEnterParaContinuar();
            return;
        }

        System.out.println("Inisira o NIF do cliente que pretende remover: ");
        int nif = Consola.lerInt("", 100000000, 999999999);
        int posicao = procurarCliente(nif);
        if (posicao == -1) {
            System.out.println("Cliente não encontrado");
        } else {
            clientes.remove(posicao);
            System.out.println("Cliente removido com sucesso");
        }
        Consola.PressioneEnterParaContinuar();
    }

    /**
     * Este método é usado para criar um posto de carregamento.
     * <p>
     * O utilizador é solicitado a fornecer detalhes como o código do posto, localização, tipo de posto, custo por kWh e o número de veículos que podem carregar simultaneamente.
     * <p>
     * O tipo de posto pode ser um dos seguintes: Posto de Carregamento Normal (PCN), Posto de Carregamento Rápido (PCR) ou Posto de Carregamento Ultrarrápido (PCUR).
     * <p>
     * Após todos os detalhes serem fornecidos corretamente, um novo objeto PostoCarregamento é criado e adicionado à lista de postos.
     * <p>
     * Por fim, é exibida uma mensagem informando que o posto de carregamento foi criado com sucesso.
     */
    public void criarPostoCarregamento() {
        int codigo_posto, numero_veiculos;
        String localizacao, tipo_posto;
        double custo_kwh;

        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Criar posto de carregamento\n");
        do {
            codigo_posto = Consola.lerInt("Codigo do posto: ", 1, 999999999);
        } while (codigo_posto == 0);
        do {
            localizacao = Consola.lerString("Localização (Morada): ");
        } while (localizacao.isEmpty());
        do {
            System.out.println("Tipo de posto de carregamento: ");
            System.out.println("1 - Posto de Carregamento Normal (PCN)");
            System.out.println("2 - Posto de Carregamento Rápido (PCR)");
            System.out.println("3 - Posto de Carregamento Ultrarrápido (PCUR)");
            int opcao = Consola.lerInt("Opcao:", 1, 3);
            tipo_posto = switch (opcao) {
                case 1 -> "PCN";
                case 2 -> "PCR";
                case 3 -> "PCUR";
                default -> "";
            };
        } while (tipo_posto.isEmpty());
        do {
            custo_kwh = Consola.lerDouble("Custo por kWh (E): ", 0, 999999999);
        } while (custo_kwh == 0);
        do {
            numero_veiculos = Consola.lerInt("Numero de veiculos que podem carregar em simultaneo: ", 1, 999999999);
        } while (numero_veiculos == 0);

        PostoCarregamento posto = new PostoCarregamento(codigo_posto, localizacao, tipo_posto, custo_kwh,
                numero_veiculos, 0);
        postos.add(posto);

        System.out.println("Posto de carregamento criado com sucesso!");
        Consola.PressioneEnterParaContinuar();
    }

    /**
     * Este método é usado para consultar um posto de carregamento.
     * <p>
     * Primeiro, verifica se existem postos de carregamento registrados. Se não houver, exibe uma mensagem informando que não existem postos de carregamento registrados e retorna.
     * <p>
     * Em seguida, lista todos os postos de carregamento registrados.
     * <p>
     * O utilizador é então solicitado a fornecer o código do posto que deseja consultar. O código do posto é lido até que um valor válido seja fornecido.
     * <p>
     * O método então procura o posto com o código fornecido. Se o posto não for encontrado, exibe uma mensagem informando que o posto de carregamento não foi encontrado.
     * <p>
     * Se o posto for encontrado, exibe as informações do posto.
     */
    public void consultarPostoCarregamento() {
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Consultar posto de carregamento\n");
        int codigo_posto;
        if (postos.isEmpty()) {
            System.out.println("Não existem postos de carregamento registados");
            return;
        }
        // listar todos os postos de carregamento
        System.out.println("Postos de carregamento registados: ");
        for (PostoCarregamento posto : postos) {
            System.out.println("-> Codigo do posto: " + posto.getCodigo_posto());
        }
        do {
            codigo_posto = Consola.lerInt("Codigo do posto: ", 1, 999999999);
        } while (codigo_posto == 0);
        // return posicao do cliente na arraylist
        int posicao = procurarPosto(codigo_posto);
        if (posicao == -1) {
            System.out.println("Posto de carregamento não encontrado");
        } else {

            System.out.println("\n" + postos.get(posicao).toString());
        }
        Consola.PressioneEnterParaContinuar();
    }

    /**
     * Este método é usado para procurar um posto de carregamento na lista de postos.
     * <p>
     * Ele percorre a lista de postos e compara o código do posto de cada objeto PostoCarregamento com o código do posto fornecido.
     * <p>
     * Se o código do posto do objeto PostoCarregamento corresponder ao código do posto fornecido, o método retorna o índice desse objeto na lista.
     * <p>
     * Se o método percorrer toda a lista e não encontrar um objeto PostoCarregamento com o código do posto fornecido, ele retorna -1.
     *
     * @param codigo_posto O código do posto a ser procurado.
     * @return O índice do posto na lista se encontrado, -1 caso contrário.
     */
    public int procurarPosto(int codigo_posto) {
        for (int i = 0; i < postos.size(); i++) {
            if (postos.get(i).getCodigo_posto() == codigo_posto) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Este método é usado para registrar uma nova sessão de carregamento.
     * <p>
     * Primeiro, verifica se existem clientes registrados. Se não houver, retorna.
     * <p>
     * Em seguida, solicita ao utilizador que forneça o NIF do cliente. Se o cliente for encontrado, ele é selecionado para a sessão de carregamento.
     * <p>
     * O mesmo processo é repetido para o veículo. Se o veículo for encontrado, ele é selecionado para a sessão de carregamento.
     * <p>
     * Depois, verifica se existem postos de carregamento registrados. Se não houver, retorna.
     * <p>
     * Solicita ao utilizador que forneça o código do posto de carregamento. Se o posto for encontrado, ele é selecionado para a sessão de carregamento.
     * <p>
     * O método então calcula a velocidade de carregamento com base no tipo de posto e solicita ao utilizador que forneça um código único para a sessão.
     * <p>
     * A data e hora de início da sessão são registradas e a data e hora de término são calculadas com base na capacidade da bateria e na velocidade de carregamento.
     * <p>
     * Por fim, uma nova sessão de carregamento é criada com todas as informações coletadas e registrada no sistema.
     * <p>
     * Uma mensagem é exibida informando que a sessão de carregamento foi registrada com sucesso.
     */
    public void menuRegistarSessaoCarregamento() {
        // FIXME qunado o veiculo ´e hibrido o custo da 0 fix
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Registar sessao de carregamento\n");
        Cliente cliente = null;
        if (MostrarClientes())
            return;

        int nif = Consola.lerInt("Inisira o NIF do cliente: ", 100000000, 999999999);
        int posicao = procurarCliente(nif);
        if (posicao == -1) {
            System.out.println("Cliente não encontrado");
        } else {
            cliente = clientes.get(posicao);
        }
        Veiculo veiculo = null;
        if (MostrarVeiculos())
            return;

        String matricula = getStringMatricula();
        posicao = procurarVeiculo(matricula);

        if (posicao == -1) {
            System.out.println("Veiculo não encontrado");
        } else {
            veiculo = veiculos.get(posicao);

        }
        assert veiculo != null;
        double capacidade_bateria = veiculo.getCapacidade_bateria();

        PostoCarregamento posto = null;
        if (postos.isEmpty()) {
            System.out.println("Não existem postos de carregamento registados");
            return;
        }
        System.out.println("Postos de carregamento registados: ");
        // nr do posto de carregamento , localizacao e tipo de posto
        for (PostoCarregamento posto2 : postos) {
            System.out.println("-> Codigo do posto: " + posto2.getCodigo_posto() + " | Localizacao: "
                    + posto2.getLocalizacao() + " | Tipo de posto: " + posto2.getTipo_posto());
        }

        double custo_kwh = 0;
        int codigo_posto = Consola.lerInt("Inisira o codigo do posto de carregamento: ", 1, 999999999);
        posicao = procurarPosto(codigo_posto);

        if (posicao == -1) {
            System.out.println("Posto de carregamento não encontrado");
        } else {
            posto = postos.get(posicao);
            custo_kwh = posto.getCusto_kwh();
            // TODO verificar se o posto tem vagas
            if (posto.getNumero_veiculos() == 0) {
                System.out.println("O posto de carregamento não tem vagas");
                return;
            }
            /*/
            if (posto.getNumero_veiculos() >= posto.getMaximo_veiculos()) {
                System.out.println("O posto de carregamento não tem vagas");
                return;
            }
            */
        }
        assert posto != null;
        double velocidadeCarregamento = posto.getTipo_posto().equals("PCN") ? 2.3
                : posto.getTipo_posto().equals("PCR") ? 7.4 : 160;

        String codigo_sessao = Consola.lerString("Codigo unico de sessao: ");

        LocalDateTime data_inicio = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = data_inicio.format(formatter);
        System.out.println("Data de inicio: " + formattedDate);

        LocalDateTime data_fim;
        String estado_pagamento = "Nao pago";

        // data de fim e igual à data de inicio + tempo de carregamento
        data_fim = data_inicio.plusHours((long) (capacidade_bateria / velocidadeCarregamento));
        formattedDate = data_fim.format(formatter);
        System.out.println("O carregamento de 0% a 100% demora " + (int) (capacidade_bateria / velocidadeCarregamento)
                + " horas e " + (int) ((capacidade_bateria / velocidadeCarregamento) * 60) % 60 + " minutos");
        System.out.println("Data de fim Calculada: " + formattedDate);
        double custo_sessao = custo_kwh * capacidade_bateria;

        SessaoCarregamento sessao = new SessaoCarregamento(matricula, custo_kwh, estado_pagamento, custo_sessao,
                cliente, veiculo, codigo_sessao, data_inicio,
                data_fim, capacidade_bateria, posto);
        registarSessaoCarregamento(sessao);
        System.out.println("Sessao de carregamento registada com sucesso!");
        System.out.println("Para pagar a sessao de carregamento va ate ao menu 'registo de pagamento'");
        Consola.PressioneEnterParaContinuar();
    }

    /**
     * Este método é usado para exibir todos os clientes registrados.
     * <p>
     * Se não houver clientes registrados, ele exibe uma mensagem informando que não existem clientes registrados e retorna verdadeiro.
     * <p>
     * Se houver clientes registrados, ele exibe uma lista de todos os clientes registrados e retorna falso.
     *
     * @return Verdadeiro se não houver clientes registrados, falso caso contrário.
     */
    private boolean MostrarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Não existem clientes registados");
            return true;
        }
        System.out.println("Clientes registados: ");
        for (Cliente cliente2 : clientes) {
            // formato "Nome (NIF)"
            System.out.println("-> " + cliente2.getNome() + " (" + cliente2.getNif() + ")");
        }
        return false;
    }

    /**
     * Este método é usado para consultar uma sessão de carregamento.
     * <p>
     * Primeiro, verifica se existem sessões de carregamento registradas. Se não houver, exibe uma mensagem informando que não existem sessões de carregamento registradas e retorna.
     * <p>
     * Em seguida, lista todas as sessões de carregamento registradas.
     * <p>
     * O utilizador é então solicitado a fornecer o código da sessão que deseja consultar. O código da sessão é lido até que um valor válido seja fornecido.
     * <p>
     * O método então procura a sessão com o código fornecido. Se a sessão não for encontrada, exibe uma mensagem informando que a sessão de carregamento não foi encontrada.
     * <p>
     * Se a sessão for encontrada, exibe as informações da sessão.
     */
    public void menuConsultarSessaoCarregamento() {
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Consultar sessao de carregamento\n");

        if (sessoesCarregamento.isEmpty()) {
            System.out.println("Não existem sessões de carregamento registadas");
            return;
        }
        System.out.println("Sessoes de carregamento registadas: ");
        for (String codigo_sessao : sessoesCarregamento.keySet()) {
            System.out.println("Codigo da sessao: " + codigo_sessao);
        }

        String codigo_sessao = Consola.lerString("\nInsira o codigo da sessao que deseja consultar: ");
        System.out.println("\n");

        SessaoCarregamento sessao = consultarSessaoCarregamento(codigo_sessao);

        if (sessao == null) {
            System.out.println("Sessao de carregamento não encontrada");
        } else {
            System.out.println(sessao);
        }
        Consola.PressioneEnterParaContinuar();
    }

    /**
     * Este método é usado para registrar uma nova sessão de carregamento.
     * <p>
     * A sessão de carregamento é adicionada a um mapa onde a chave é o código da sessão e o valor é o objeto da sessão de carregamento.
     *
     * @param sessao A sessão de carregamento a ser registrada.
     */
    public void registarSessaoCarregamento(SessaoCarregamento sessao) {
        sessoesCarregamento.put(sessao.getCodigo_sessao(), sessao);
    }

    /**
     * Este método é usado para consultar uma sessão de carregamento.
     * <p>
     * Ele recebe o código da sessão como parâmetro e retorna o objeto SessaoCarregamento correspondente.
     * <p>
     * Se a sessão de carregamento com o código fornecido não for encontrada, o método retorna null.
     *
     * @param codigo_sessao O código da sessão a ser consultada.
     * @return O objeto SessaoCarregamento correspondente ao código fornecido, ou null se não for encontrado.
     */
    public SessaoCarregamento consultarSessaoCarregamento(String codigo_sessao) {
        return sessoesCarregamento.get(codigo_sessao);
    }

    /**
     * Este método é usado para registrar um novo pagamento.
     * <p>
     * Ele recebe como parâmetros uma sessão de carregamento, um método de pagamento, uma data e hora de transação e um booleano indicando se o pagamento foi efetuado.
     * <p>
     * Um novo objeto Pagamento é criado com essas informações e adicionado à lista de pagamentos.
     *
     * @param sessao            A sessão de carregamento para a qual o pagamento está a ser registrado.
     * @param metodoPagamento   O método de pagamento usado.
     * @param DataHoraTransacao A data e hora da transação.
     * @param pago              Um booleano indicando se o pagamento foi efetuado.
     */
    public void registarPagamento(SessaoCarregamento sessao, String metodoPagamento,
                                  LocalDateTime DataHoraTransacao, boolean pago) {
        Pagamento pagamento = new Pagamento(sessao, metodoPagamento, DataHoraTransacao, pago);
        pagamentos.add(pagamento);
    }

    /**
     * Este método é usado para consultar um pagamento por sessão.
     * <p>
     * Ele percorre a lista de pagamentos e compara o código da sessão de cada objeto Pagamento com o código da sessão fornecido.
     * <p>
     * Se o código da sessão do objeto Pagamento corresponder ao código da sessão fornecido, o método retorna o objeto Pagamento.
     * <p>
     * Se o método percorrer toda a lista e não encontrar um objeto Pagamento com o código da sessão fornecido, ele retorna null.
     *
     * @param codigoSessao O código da sessão a ser consultada.
     * @return O objeto Pagamento correspondente ao código da sessão fornecido, ou null se não for encontrado.
     */
    public Pagamento consultarPagamentoPorSessao(String codigoSessao) {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento.getSessao().getCodigo_sessao().equals(codigoSessao)) {
                return pagamento;
            }
        }
        return null;
    }

    /**
     * Este método é usado para registrar um pagamento para uma sessão de carregamento.
     * <p>
     * Primeiro, verifica se existem sessões de carregamento não pagas. Se não houver, retorna.
     * <p>
     * Em seguida, lista todas as sessões de carregamento não pagas.
     * <p>
     * O utilizador é então solicitado a fornecer o código da sessão que deseja pagar.
     * <p>
     * O método então procura a sessão com o código fornecido. Se a sessão não for encontrada, exibe uma mensagem informando que a sessão de carregamento não foi encontrada.
     * <p>
     * Se a sessão for encontrada, exibe as informações da sessão.
     * <p>
     * O utilizador é então solicitado a escolher o método de pagamento.
     * <p>
     * O método de pagamento, a data e hora da transação são registrados e o estado do pagamento da sessão é alterado para "Pago".
     * <p>
     * Por fim, um novo objeto Pagamento é criado com essas informações e adicionado à lista de pagamentos.
     */
    public void menuRegistarPagamento() {
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Registar pagamento de sessao\n");
        // se nao existirem sessoes de carregamento nao pagas
        if (sessoesCarregamento.isEmpty()) {
            System.out.println("Não existem sessões de carregamento não pagas");
            Consola.PressioneEnterParaContinuar();
            return;
        }
        // lista de sessoes de carregamento nao pagas
        System.out.println("Sessoes de carregamento nao pagas: ");
        for (String codigo_sessao : sessoesCarregamento.keySet()) {
            if (!sessoesCarregamento.get(codigo_sessao).estado_pagamento.equals("Pago")) {
                System.out.println("Codigo da sessao: " + codigo_sessao);
            }
        }

        String codigo_sessao = Consola.lerString("\nInsira o codigo da sessao que deseja pagar: ");
        System.out.println("\n");
        // mostrar sessao
        System.out.println("Sessao de carregamento: ");
        SessaoCarregamento sessao = consultarSessaoCarregamento(codigo_sessao);
        if (sessao == null) {
            System.out.println("Sessao de carregamento não encontrada");
        } else {
            System.out.println(sessao);
        }
        // inserir metodo de pagamento
        System.out.println("Metodo de pagamento: ");
        System.out.println("1 - MBway");
        System.out.println("2 - Debito Direto");
        System.out.println("3 - Transferencia Bancaria");
        int opcao = Consola.lerInt("Opcao: ", 1, 3);
        String metodoPagamento = switch (opcao) {
            case 1 -> "MBway";
            case 2 -> "Debito Direto";
            case 3 -> "Transferencia Bancaria";
            default -> "";
        };
        // metodo de pagamento + data e hora de transacao
        assert sessao != null;
        System.out.println("Metodo de pagamento: " + metodoPagamento + "Preco a pagar: " + sessao.getCusto_sessao());
        LocalDateTime dataTransacao = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("Data de transacao: " + dataTransacao.format(formatter));
        System.out.println("Pagamento efetuado com sucesso!");
        sessao.setEstado_pagamento("Pago");
        sessao.getPostoCarregamento()
                .setValor_faturado(sessao.getPostoCarregamento().getValor_faturado() + sessao.getCusto_sessao());
        registarPagamento(sessao, metodoPagamento, dataTransacao, true);
    }

    /**
     * Este método é usado para consultar um pagamento de uma sessão de carregamento.
     * <p>
     * Primeiro, verifica se existem pagamentos registrados. Se não houver, exibe uma mensagem informando que não existem pagamentos registrados e retorna.
     * <p>
     * Em seguida, lista todos os pagamentos registrados.
     * <p>
     * O utilizador é então solicitado a fornecer o código da sessão que deseja consultar. O código da sessão é lido até que um valor válido seja fornecido.
     * <p>
     * O método então procura o pagamento com o código da sessão fornecido. Se o pagamento não for encontrado, exibe uma mensagem informando que o pagamento não foi encontrado.
     * <p>
     * Se o pagamento for encontrado, exibe as informações do pagamento.
     */
    public void menuConsultarPagamento() {
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Consultar pagamento de sessao\n");

        if (pagamentos.isEmpty()) {
            System.out.println("Não existem pagamentos registados");
            return;
        }
        System.out.println("Pagamentos registados: ");
        for (Pagamento pagamento : pagamentos) {
            System.out.println("Codigo da sessao: " + pagamento.getSessao().getCodigo_sessao());
        }
        String codigo_sessao = Consola.lerString("\nInsira o codigo da sessao que deseja consultar: ");
        System.out.println("\n");
        Pagamento pagamento = consultarPagamentoPorSessao(codigo_sessao);
        if (pagamento == null) {
            System.out.println("Pagamento não encontrado");
        } else {
            System.out.println(pagamento);
        }
        Consola.PressioneEnterParaContinuar();
    }

    /**
     * Este método é usado para listar os 3 postos de carregamento com maior valor faturado.
     * <p>
     * Primeiro, verifica se existem postos de carregamento registrados. Se não houver, exibe uma mensagem informando que não existem postos de carregamento registrados e retorna.
     * <p>
     * Em seguida, cria uma lista de postos de carregamento e ordena essa lista em ordem decrescente de valor faturado.
     * <p>
     * Depois, determina o tamanho da lista, que será o menor entre o tamanho da lista de postos ordenados e 3.
     * <p>
     * Exibe os postos de carregamento com maior valor faturado. Se o valor faturado for 0, informa que não existem pagamentos registrados neste posto.
     */
    public void listagemPostosMaiorLiquidacao() {
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Listagem dos 3 postos de carregamento com maior valor faturado\n");

        if (postos.isEmpty()) {
            System.out.println("Não existem postos de carregamento registados");
            return;
        }

        List<PostoCarregamento> postosOrdenados = new ArrayList<>(postos);
        postosOrdenados.sort(Comparator.comparing(PostoCarregamento::getValor_faturado).reversed());

        int size = Math.min(postosOrdenados.size(), 3);
        System.out.println("Postos de carregamento com maior valor faturado: ");
        for (int i = 0; i < size; i++) {
            PostoCarregamento posto = postosOrdenados.get(i);
            double valorFaturado = posto.getValor_faturado();
            if (valorFaturado == 0) {
                System.out.println("Codigo do posto: " + posto.getCodigo_posto()
                        + " | Valor faturado: Não existem pagamentos registados neste posto");
            } else {
                System.out
                        .println("Codigo do posto: " + posto.getCodigo_posto() + " | Valor faturado: " + valorFaturado);
            }
        }
        System.out.println();
        Consola.PressioneEnterParaContinuar();
    }

    /**
     * Este método é usado para listar todas as sessões de carregamento cujo custo é superior a um valor especificado.
     * <p>
     * Primeiro, cria uma lista vazia para armazenar as sessões de carregamento que atendem ao critério.
     * <p>
     * Em seguida, percorre todas as sessões de carregamento registradas. Para cada sessão, verifica se o custo da sessão é superior ao valor especificado. Se for, adiciona a sessão à lista.
     * <p>
     * Por fim, exibe todas as sessões na lista. Para cada sessão, exibe o código da sessão e o custo da sessão.
     */
    public void listarSessoesComCustoSuperiorX() {
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Listagem de sessões de carregamento cujo custo é superior a n euros\n");

        List<SessaoCarregamento> sessoesComCustoSuperiorAX = new ArrayList<>();
        double custo = Consola.lerDouble("Insira o custo: ", 0, 999999999);
        for (SessaoCarregamento sessao : sessoesCarregamento.values()) {
            if (sessao.getCusto_sessao() > custo) {
                sessoesComCustoSuperiorAX.add(sessao);
            }
        }
        for (SessaoCarregamento sessao : sessoesComCustoSuperiorAX) {
            // codigo de sessao, preco a pagar, estado de pagamento, cliente, veiculo, posto
            // de carregamento
            System.out.println("Codigo da sessao: " + sessao.getCodigo_sessao() + " | Preco a pagar: "
                    + sessao.getCusto_sessao() + " | Estado de pagamento: " + sessao.getEstado_pagamento()
                    + " | Cliente: " + sessao.getCliente().getNome() + " | Veiculo: "
                    + sessao.getVeiculo().getMatricula()
                    + " | Posto de carregamento: " + sessao.getPostoCarregamento().getCodigo_posto());
        }
        System.out.println("Total de sessoes com custo superior a " + custo + " euros: "
                + sessoesComCustoSuperiorAX.size() + "\n");
        Consola.PressioneEnterParaContinuar();
    }

    /**
     * Este método é usado para calcular o total de sessões de carregamento realizadas por um cliente específico.
     * <p>
     * Primeiro, verifica se existem clientes registrados. Se não houver, retorna.
     * <p>
     * Em seguida, solicita ao utilizador que forneça o NIF do cliente que deseja consultar. O NIF é lido até que um valor válido seja fornecido.
     * <p>
     * O método então procura o cliente com o NIF fornecido. Se o cliente não for encontrado, exibe uma mensagem informando que o cliente não foi encontrado.
     * <p>
     * Se o cliente for encontrado, cria uma lista para armazenar as sessões de carregamento desse cliente.
     * <p>
     * Percorre todas as sessões de carregamento registradas e, para cada sessão, verifica se o NIF do cliente da sessão corresponde ao NIF do cliente fornecido. Se corresponder, adiciona a sessão à lista de sessões do cliente.
     * <p>
     * Calcula o total de sessões como o tamanho da lista de sessões do cliente.
     * <p>
     * Por fim, exibe o nome do cliente e o total de sessões.
     */
    public void totalSessoesPorCliente() {
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Total de sessões de carregamento realizados (por cliente)\n");
        int nif;
        if (MostrarClientes())
            return;
        do {
            nif = Consola.lerInt("NIF do cliente a procurar: ", 100000000, 999999999);
        } while (nif == 0);
        // return posicao do cliente na arraylist
        int posicao = procurarCliente(nif);
        if (posicao == -1) {
            System.out.println("Cliente não encontrado");
        } else {
            Cliente cliente = clientes.get(posicao);
            List<SessaoCarregamento> sessoesPorCliente = new ArrayList<>();
            for (SessaoCarregamento sessao : sessoesCarregamento.values()) {
                if (sessao.getCliente().getNif() == cliente.getNif()) {
                    sessoesPorCliente.add(sessao);
                }
            }
            int totalSessoes = sessoesPorCliente.size();
            System.out.println("Cliente: " + cliente.getNome() + " -> Total de sessões: " + totalSessoes);
        }
        System.out.println();
        Consola.PressioneEnterParaContinuar();
    }

    /**
     * Este método é usado para calcular a média de energia consumida por posto de carregamento e por tipo de veículo (híbridos/elétricos).
     * <p>
     * Primeiro, para cada posto de carregamento, cria uma lista de sessões de carregamento que ocorreram nesse posto. Calcula o total de energia consumida nessas sessões e, em seguida, calcula a média dividindo o total de energia pelo número de sessões. Exibe a média de energia para o posto de carregamento.
     * <p>
     * Em seguida, para cada tipo de veículo (elétrico e híbrido), cria uma lista de sessões de carregamento que envolvem veículos desse tipo. Calcula o total de energia consumida nessas sessões e, em seguida, calcula a média dividindo o total de energia pelo número de sessões. Exibe a média de energia para o tipo de veículo.
     */
    public void mediaEnergiaPorPostoETipoVeiculo() {
        System.out.println("\n***************************************\n");
        System.out.println(
                "\tMenu Média de energia consumida por posto de carregamento e por tipo de veículo (híbridos/elétricos)\n");

        // media de energia por posto de carregamento
        for (PostoCarregamento posto : postos) {
            List<SessaoCarregamento> sessoesPorPosto = new ArrayList<>();
            for (SessaoCarregamento sessao : sessoes) {
                if (sessao.getPostoCarregamento().getCodigo_posto() == posto.getCodigo_posto()) {
                    sessoesPorPosto.add(sessao);
                }
            }
            double totalEnergia = 0;
            for (SessaoCarregamento sessao : sessoesPorPosto) {
                totalEnergia += sessao.getEnergia_consumida();
            }
            double mediaEnergia = 0;
            if (!sessoesPorPosto.isEmpty()) {
                mediaEnergia = totalEnergia / sessoesPorPosto.size();
            }
            System.out.println("Posto: " + posto.getCodigo_posto() + " | Media de energia: " + mediaEnergia);
        }

        int totalSessoesEletricos = 0;
        int totalSessoesHibridos = 0;
        double totalEnergiaEletricos = 0;
        double totalEnergiaHibridos = 0;
        for (Veiculo veiculo : veiculos) {
            List<SessaoCarregamento> sessoesPorVeiculo = new ArrayList<>();
            for (SessaoCarregamento sessao : sessoes) {
                if (sessao.getVeiculo().getMatricula().equals(veiculo.getMatricula())) {
                    sessoesPorVeiculo.add(sessao);
                }
            }
            if (veiculo instanceof VeiculoEletrico) {
                totalSessoesEletricos += sessoesPorVeiculo.size();
                for (SessaoCarregamento sessao : sessoesPorVeiculo) {
                    totalEnergiaEletricos += sessao.getEnergia_consumida();
                }
            } else {
                totalSessoesHibridos += sessoesPorVeiculo.size();
                for (SessaoCarregamento sessao : sessoesPorVeiculo) {
                    totalEnergiaHibridos += sessao.getEnergia_consumida();
                }
            }
        }
        double mediaEnergiaEletricos = 0.00;
        double mediaEnergiaHibridos = 0.00;
        if (totalSessoesEletricos != 0) {
            mediaEnergiaEletricos = totalEnergiaEletricos / totalSessoesEletricos;
        }
        if (totalSessoesHibridos != 0) {
            mediaEnergiaHibridos = totalEnergiaHibridos / totalSessoesHibridos;
        }
        System.out.println("Media de energia Veiculos eletricos: " + mediaEnergiaEletricos + " KWh, Veiculos hibridos: "
                + mediaEnergiaHibridos + " KWh\n");
        Consola.PressioneEnterParaContinuar();
    }

    /**
     * Este método é usado para listar os pagamentos que ainda não foram efetuados por cada cliente.
     * <p>
     * Primeiro, verifica se existem sessões de carregamento registradas. Se não houver, exibe uma mensagem informando que não existem sessões de carregamento registradas e retorna.
     * <p>
     * Para cada cliente na lista de clientes, percorre todas as sessões de carregamento. Para cada sessão, verifica se o NIF do cliente da sessão corresponde ao NIF do cliente atual e se o estado do pagamento da sessão é "Não pago". Se ambas as condições forem verdadeiras, exibe o nome do cliente, o valor a pagar e o código da sessão.
     */
    public void listagemPagamentosPorEfetuar() {
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Listagem de pagamentos por efetuar (por cliente)\n");

        if (sessoesCarregamento.isEmpty()) {
            System.out.println("Não existem sessões de carregamento registadas");
            return;
        }
        System.out.println("Clientes com pagamentos por efetuar: ");
        for (Cliente cliente : clientes) {
            for (SessaoCarregamento sessao : sessoesCarregamento.values()) {

                if (sessao.getCliente().getNif() == cliente.getNif()
                        && sessao.getEstado_pagamento().equals("Nao pago")) {
                    System.out
                            .println("Cliente: " + cliente.getNome() + " | Valor a pagar: " + sessao.getCusto_sessao()
                                    + " | Codigo da sessao: " + sessao.getCodigo_sessao());
                    break;
                }
            }
        }
        System.out.println();
        Consola.PressioneEnterParaContinuar();
    }

    /**
     * Este método é usado para exibir o histórico de sessões de carregamento para um posto de carregamento específico.
     * <p>
     * Primeiro, exibe todos os postos de carregamento registrados.
     * <p>
     * Em seguida, solicita ao utilizador que forneça o código do posto de carregamento que deseja consultar.
     * <p>
     * Se o posto de carregamento com o código fornecido for encontrado, cria uma lista de todas as sessões de carregamento que ocorreram nesse posto.
     * <p>
     * Para cada sessão de carregamento na lista, exibe o código da sessão, o custo da sessão, o estado do pagamento, o nome do cliente, a matrícula do veículo e o código do posto de carregamento.
     * <p>
     * Se o posto de carregamento com o código fornecido não for encontrado, exibe uma mensagem informando que o posto de carregamento não foi encontrado.
     */
    public void historicoSessoesPorPosto() {
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Histórico de sessões de carregamento (por posto de carregamento)\n");

        // listar todos os postos de carregamento
        System.out.println("Postos de carregamento registados: ");
        for (PostoCarregamento posto : postos) {
            System.out.println("-> Codigo do posto: " + posto.getCodigo_posto());
        }
        int codigo_posto = Consola.lerInt("Codigo do posto: ", 1, 999999999);
        int posicao = procurarPosto(codigo_posto);
        if (posicao == -1) {
            System.out.println("Posto de carregamento não encontrado");
        } else {
            PostoCarregamento posto = postos.get(posicao);
            List<SessaoCarregamento> sessoesPorPosto = new ArrayList<>();
            for (SessaoCarregamento sessao : sessoesCarregamento.values()) {
                if (sessao.getPostoCarregamento().getCodigo_posto() == posto.getCodigo_posto()) {
                    sessoesPorPosto.add(sessao);
                }
            }
            System.out.println("Posto: " + posto.getCodigo_posto());
            for (SessaoCarregamento sessao : sessoesPorPosto) {
                System.out.println("Codigo da sessao: " + sessao.getCodigo_sessao() + " | Preco a pagar: "
                        + sessao.getCusto_sessao() + " | Estado de pagamento: " + sessao.getEstado_pagamento()
                        + " | Cliente: " + sessao.getCliente().getNome() + " | Veiculo: "
                        + sessao.getVeiculo().getMatricula()
                        + " | Posto de carregamento: " + sessao.getPostoCarregamento().getCodigo_posto());
            }
        }
        System.out.println();
        Consola.PressioneEnterParaContinuar();
    }
}