import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
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
                // System.out.println("Tempo de carregamento (0% -> 100%): " +
                // tempo_carregamento + " horas");
                double minutos = tempo_carregamento * 60;
                // transformar minutos em horas e minutos
                System.out.println("Tempo de carregamento (0% -> 100%): " + (int) minutos / 60 + " horas e "
                        + (int) minutos % 60 + " minutos");

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
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Consultar veiculo\n");
        String matricula;
        if (MostrarVeiculos())
            return;
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
        for (PostoCarregamento posto2 : postos) {
            System.out.println("-> Codigo do posto: " + posto2.getCodigo_posto());
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

        /*
         *
         * boolean error = false;
         * do {
         * try {
         * data_fim =
         * LocalDateTime.parse(Consola.lerString("Data de fim (dd-MM-yyyy HH:mm:ss): "),
         * formatter);
         * if (data_fim.isBefore(data_inicio)) {
         * System.out.println("Data de fim tem de ser superior a data de inicio");
         * error = true;
         * } else {
         * error = false;
         * }
         * } catch (Exception e) {
         * System.out.println("Data invalida");
         * error = true;
         * }
         * } while (error);
         */

        /*
         * int tempo_carregamento = (int) (velocidadeCarregamento / capacidade_bateria);
         * System.out.println("Tempo de carregamento: " + tempo_carregamento +
         * " horas");
         *
         * double energia_consumida = Consola.lerDouble("Energia consumida (KWh): ", 0,
         * 999999999);
         *
         * // TODO estado de pagamento (pago ou nao pago) melhorado
         * System.out.println("Estado de pagamento: ");
         * System.out.println("1 - Pago");
         * System.out.println("2 - Não pago");
         * int opcao = Consola.lerInt("Opcao: ", 1, 2);
         * String estado_pagamento;
         * if (opcao == 1) {
         * estado_pagamento = "Pago";
         * } else {
         * estado_pagamento = "Nao pago";
         * }
         * // custo da sessao = custo_kwh * energia_consumida
         * double custo_sessao = custo_kwh * energia_consumida;
         * System.out.println("Custo da sessao (E): " + custo_sessao);
         * // double custo_sessao = Consola.lerDouble("Custo da sessao: ", 0,
         * 999999999);
         */
        SessaoCarregamento sessao = new SessaoCarregamento(matricula, custo_kwh, estado_pagamento, custo_sessao,
                cliente, veiculo, codigo_sessao, data_inicio,
                data_fim, capacidade_bateria, posto);
        registarSessaoCarregamento(sessao);
        System.out.println("Sessao de carregamento registada com sucesso!");
        System.out.println("Para pagar a sessao de carregamento va ate ao menu 'registo de pagamento'");
        Consola.PressioneEnterParaContinuar();
    }

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

        Pagamento pagamento = consultarPagamentoPorSessao(codigo_sessao);

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
        //metodo de pagamento + data e hora de transacao
        System.out.println("Metodo de pagamento: " + metodoPagamento + "Preco a pagar: " + sessao.getCusto_sessao());
        LocalDateTime dataTransacao = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("Data de transacao: " + dataTransacao.format(formatter));
        //TODO acabar isto
        

    }

    public void menuConsultarPagamento() {
        System.out.println("\n***************************************\n");
        System.out.println("\tMenu Consultar pagamento de sessao\n");
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