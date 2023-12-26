import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.io.Serializable;

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

    public void criarVeiculo() {
        String marca, modelo, matricula, tipo_veiculo;
        Date data_registo = null;
        int potencia, capacidade_bateria, autonomia;

        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Criar veiculo\n");
        // tipo de veiculo E (eletrico) ou H (hibrido)
        do {
            tipo_veiculo = Consola.lerString("Tipo de veiculo (E/H): ");
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
            autonomia = Consola.lerInt("Autonomia: ", 1, 999999999);
        } while (autonomia == 0);

        float tempo_carregamento;
        double velocidadeCarregamento = 0;

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
                System.out.println("Tempo de carregamento: " + tempo_carregamento + " horas");

            } while (tempo_carregamento == 0);

            VeiculoEletrico veiculo = new VeiculoEletrico(marca, modelo, matricula, data_registo, autonomia,
                    velocidadeCarregamento, potencia, tempo_carregamento, capacidade_bateria);
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
                    velocidadeCarregamento, potencia, cilindrada, consumo_combustivel, emissao);
            veiculos.add(veiculo);
            System.out.println("Veiculo criado com sucesso");
            Consola.PressioneEnterParaContinuar();
        }
    }

    private int procurarVeiculo(String matricula) {
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getMatricula().equalsIgnoreCase(matricula)) {
                return i;
            }
        }
        return -1;
    }

    public void consultarVeiculo() {
        String matricula;
        if (veiculos.isEmpty()) {
            System.out.println("Não existem veiculos registados");
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

    public void consultarCliente() {
        int nif;
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Consultar cliente\n");
        if (clientes.isEmpty()) {
            System.out.println("Não existem clientes registados");
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

    public int procurarCliente(int nif) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getNif() == nif) {
                return i;
            }
        }
        return -1;
    }

    public void alterarDadosCliente() {
        System.out.println("Inisira o NIF do cliente que pretende alterar os dados: ");
        int nif = Consola.lerInt("", 100000000, 999999999);
        int posicao = procurarCliente(nif);
        if (posicao == -1) {
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
            // TODO Do while para o utilizador inserir um valor valido
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

    public void removerCliente() {
        System.out.println("Inisira o NIF do cliente que pretende remover: ");
        int nif = Consola.lerInt("", 100000000, 999999999);
        int posicao = procurarCliente(nif);
        if (posicao == -1) {
            System.out.println("Cliente não encontrado");
        } else {
            clientes.remove(posicao);
            System.out.println("Cliente removido com sucesso");
        }
    }

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
            localizacao = Consola.lerString("Localização(morada): ");
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
            custo_kwh = Consola.lerDouble("Custo por kWh(E): ", 0, 999999999);
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

    public void consultarPostoCarregamento() {
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Consultar posto de carregamento\n");
        int codigo_posto;
        if (postos.isEmpty()) {
            System.out.println("Não existem postos de carregamento registados");
            return;
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

    public int procurarPosto(int codigo_posto) {
        for (int i = 0; i < postos.size(); i++) {
            if (postos.get(i).getCodigo_posto() == codigo_posto) {
                return i;
            }
        }
        return -1;
    }

    public void menuRegistarSessaoCarregamento() {
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Registar sessao de carregamento\n");
        Cliente cliente = null;
        if (clientes.isEmpty()) {
            System.out.println("Não existem clientes registados");
            return;
        }
        int nif = Consola.lerInt("Inisira o NIF do cliente: ", 100000000, 999999999);
        int posicao = procurarCliente(nif);
        if (posicao == -1) {
            System.out.println("Cliente não encontrado");
        } else {
            cliente = clientes.get(posicao);
        }
        Veiculo veiculo = null;
        if (veiculos.isEmpty()) {
            System.out.println("Não existem veiculos registados");
            return;
        }
        String matricula = getStringMatricula();
        posicao = procurarVeiculo(matricula);
        if (posicao == -1) {
            System.out.println("Veiculo não encontrado");
        } else {
            veiculo = veiculos.get(posicao);
        }
        PostoCarregamento posto = null;
        if (postos.isEmpty()) {
            System.out.println("Não existem postos de carregamento registados");
            return;
        }
        double custo_kwh = 0;
        int codigo_posto = Consola.lerInt("Inisira o codigo do posto de carregamento: ", 1, 999999999);
        posicao = procurarPosto(codigo_posto);
        if (posicao == -1) {
            System.out.println("Posto de carregamento não encontrado");
        } else {
            posto = postos.get(posicao);
            custo_kwh = posto.getCusto_kwh();
        }

        String codigo_sessao = Consola.lerString("Codigo unico de sessao: ");

        LocalDateTime data_inicio = LocalDateTime.now();
        System.out.println("Data de inicio: " + data_inicio);

        LocalDateTime data_fim = null;
        boolean error = false;
        do {
            try {
                data_fim = LocalDateTime.parse(Consola.lerString("Data de fim (yyyy-MM-dd HH:mm): "));
                if (data_fim.isBefore(data_inicio)) {
                    System.out.println("Data de fim tem de ser superior a data de inicio");
                    error = true;
                } else {
                    error = false;
                }
            } catch (Exception e) {
                System.out.println("Data invalida");
                error = true;
            }
        } while (error);

        double energia_consumida = Consola.lerDouble("Energia consumida (KWh): ", 0, 999999999);
        System.out.println("Estado de pagamento: ");
        System.out.println("1 - Pago");
        System.out.println("2 - Não pago");
        int opcao = Consola.lerInt("", 1, 2);
        String estado_pagamento;
        if (opcao == 1) {
            estado_pagamento = "Pago";
        } else {
            estado_pagamento = "Nao pago";
        }

        double custo_sessao = Consola.lerDouble("Custo da sessao: ", 0, 999999999);

        SessaoCarregamento sessao = new SessaoCarregamento(matricula, custo_kwh, estado_pagamento, custo_sessao,
                cliente, veiculo, codigo_sessao, data_inicio,
                data_fim, energia_consumida, posto);
        registarSessaoCarregamento(sessao);
        System.out.println("Sessao de carregamento registada com sucesso");
        Consola.PressioneEnterParaContinuar();
    }

    public void menuConsultarSessaoCarregamento() {
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Consultar sessao de carregamento\n");

        if (sessoesCarregamento.isEmpty()) {
            System.out.println("Não existem sessões de carregamento registadas");
            return;
        }

        for (String codigo_sessao : sessoesCarregamento.keySet()) {
            System.out.println("Codigo da sessao: " + codigo_sessao);
        }

        String codigo_sessao = Consola.lerString("Insira o codigo da sessao que deseja consultar: ");

        SessaoCarregamento sessao = consultarSessaoCarregamento(codigo_sessao);

        if (sessao == null) {
            System.out.println("Sessao de carregamento não encontrada");
        } else {
            System.out.println(sessao.toString());
        }
        Consola.PressioneEnterParaContinuar();
    }

    public void registarSessaoCarregamento(SessaoCarregamento sessao) {
        sessoesCarregamento.put(sessao.getCodigo_sessao(), sessao);
    }

    public SessaoCarregamento consultarSessaoCarregamento(String codigo_sessao) {
        return sessoesCarregamento.get(codigo_sessao);
    }

    // TODO Registar e consultar (por sessão) o pagamento de serviço de
    // carregamento;
    public void registarPagamento(SessaoCarregamento sessao, String metodoPagamento,
            LocalDateTime dataTransacao, LocalDateTime horaTransacao, boolean pago) {
        Pagamento pagamento = new Pagamento(sessao, metodoPagamento, dataTransacao, horaTransacao, pago);
        pagamentos.add(pagamento);
    }

    public Pagamento consultarPagamentoPorSessao(String codigoSessao) {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento.getSessao().getCodigo_sessao().equals(codigoSessao)) {
                return pagamento;
            }
        }
        return null;
    }

    // TODO Listagem dos 3 postos de carregamento com maior valor faturado
    // (liquidado);

    // TODO Listagem de sessões de carregamento cujo custo é superior a n
    // euros.Sendo o valor de n solicitado ao utilizador;

    // TODO Total de sessões de carregamento realizados (por cliente);

    // TODO Média de energia consumida por posto de carregamento e por tipo
    // de veículo (híbridos/elétricos);

    // TODO Listagem de pagamentos por efetuar (por cliente);

    // TODO Histórico de sessões de carregamento (por posto de carregamento).

}