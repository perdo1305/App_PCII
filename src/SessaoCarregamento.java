import java.io.Serializable;
import java.time.LocalDateTime;

public class SessaoCarregamento implements Serializable {
    protected String matricula;
    protected double custo_kwh;
    protected String estado_pagamento;
    protected double custo_sessao;
    protected Cliente cliente;
    protected Veiculo veiculo;
    protected String codigo_sessao;
    protected LocalDateTime data_inicio;
    protected LocalDateTime data_fim;
    protected double energia_consumida;
    protected PostoCarregamento postoCarregamento;

    /**
     * Construtor da classe SessaoCarregamento
     *
     * @param matricula          matricula do veiculo (string)
     * @param custo_kwh          custo por kWh em euros (double)
     * @param estado_pagamento   estado de pagamento (string)
     * @param custo_sessao       custo da sessao (double)
     * @param cliente            objeto da classe Cliente
     * @param veiculo            objeto da classe Veiculo
     * @param codigo_sessao      codigo da sessao (string)
     * @param data_inicio        data de inicio da sessao (LocalDateTime)
     * @param data_fim           data de fim da sessao (LocalDateTime)
     * @param energia_consumida  energia consumida (double)
     * @param postoCarregamento  objeto da classe PostoCarregamento
     */

    public SessaoCarregamento(String matricula, double custo_kwh, String estado_pagamento, double custo_sessao,
                              Cliente cliente, Veiculo veiculo, String codigo_sessao, LocalDateTime data_inicio,
                              LocalDateTime data_fim, double energia_consumida, PostoCarregamento postoCarregamento) {
        setMatricula(matricula);
        setCusto_kwh(custo_kwh);
        setEstado_pagamento(estado_pagamento);
        setCusto_sessao(custo_sessao);
        setCliente(cliente);
        setVeiculo(veiculo);
        setCodigo_sessao(codigo_sessao);
        setData_inicio(data_inicio);
        setData_fim(data_fim);
        setEnergia_consumida(energia_consumida);
        setPostoCarregamento(postoCarregamento);
    }

    /**
     * Setter da matricula
     * @param matricula matricula do veiculo (string)
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    /**
     * Setter do custo por kWh
     * @param custo_kwh custo por kWh em euros (double)
     */
    public void setCusto_kwh(double custo_kwh) {
        this.custo_kwh = custo_kwh;
    }

    /**
     * Getter do estado de pagamento
     * @return
     */
    public String getEstado_pagamento() {
        return estado_pagamento;
    }

    /**
     * Setter do estado de pagamento
     * @param estado_pagamento estado de pagamento (string)
     */
    public void setEstado_pagamento(String estado_pagamento) {
        this.estado_pagamento = estado_pagamento;
    }

    /**
     * Getter do custo da sessao
     * @return
     */
    public double getCusto_sessao() {
        return custo_sessao;
    }

    /**
     * Setter do custo da sessao
     * @param custo_sessao custo da sessao (double)
     */
    public void setCusto_sessao(double custo_sessao) {
        this.custo_sessao = custo_sessao;
    }

    /**
     * Getter do cliente
     * @return objeto da classe Cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Setter do cliente
     * @param cliente objeto da classe Cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Getter do veiculo
     * @return objeto da classe Veiculo
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * Setter do veiculo
     * @param veiculo objeto da classe Veiculo
     */
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /**
     * Getter do codigo da sessao
     * @return codigo da sessao (string)
     */
    public String getCodigo_sessao() {
        return codigo_sessao;
    }

    /**
     * Setter do codigo da sessao
     * @param codigo_sessao codigo da sessao (string)
     */
    public void setCodigo_sessao(String codigo_sessao) {
        this.codigo_sessao = codigo_sessao;
    }

    /**
     * Getter da data de inicio
     * @param data_inicio data de inicio da sessao (LocalDateTime)
     */
    public void setData_inicio(LocalDateTime data_inicio) {
        this.data_inicio = data_inicio;
    }

    /**
     * Setter da data de fim
     * @param data_fim data de fim da sessao (LocalDateTime)
     */
    public void setData_fim(LocalDateTime data_fim) {
        this.data_fim = data_fim;
    }

    /**
     * Getter da energia consumida
     * @return energia consumida (double)
     */
    public double getEnergia_consumida() {
        return energia_consumida;
    }

    /**
     * Setter da energia consumida
     * @param energia_consumida energia consumida (double)
     */
    public void setEnergia_consumida(double energia_consumida) {
        this.energia_consumida = energia_consumida;
    }

    /**
     * Getter do posto de carregamento
     * @return objeto da classe PostoCarregamento
     */
    public PostoCarregamento getPostoCarregamento() {
        return postoCarregamento;
    }

    /**
     * Setter do posto de carregamento
     * @param postoCarregamento objeto da classe PostoCarregamento
     */
    public void setPostoCarregamento(PostoCarregamento postoCarregamento) {
        this.postoCarregamento = postoCarregamento;
    }

    /**
     * ToString da classe SessaoCarregamento
     * @return ‘string’ com a informação da sessao de carregamento
     */
    @Override
    public String toString() {
        return "Matricula: " + matricula + "\n" + "Nif do cliente: " + cliente.getNif() + "\n" + "Data de inicio: " + data_inicio + "\n" + "Data de fim: " + data_fim + "\n" + "Energia consumida: " + energia_consumida + "\n" + "Custo da sessão: " + custo_sessao + "\n" + "Código de sessão: " + codigo_sessao + "\n" + "Estado de pagamento: " + estado_pagamento + "\n";
    }
}
