import java.io.Serializable;
import java.time.LocalDateTime;

public class SessaoCarregamento implements Serializable {
    protected int matricula;
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

    public SessaoCarregamento(int matricula, double custo_kwh, String estado_pagamento, double custo_sessao,
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


    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public double getCusto_kwh() {
        return custo_kwh;
    }

    public void setCusto_kwh(double custo_kwh) {
        this.custo_kwh = custo_kwh;
    }

    public String getEstado_pagamento() {
        return estado_pagamento;
    }

    public void setEstado_pagamento(String estado_pagamento) {
        this.estado_pagamento = estado_pagamento;
    }

    public double getCusto_sessao() {
        return custo_sessao;
    }

    public void setCusto_sessao(double custo_sessao) {
        this.custo_sessao = custo_sessao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getCodigo_sessao() {
        return codigo_sessao;
    }

    public void setCodigo_sessao(String codigo_sessao) {
        this.codigo_sessao = codigo_sessao;
    }

    public LocalDateTime getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDateTime data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDateTime getData_fim() {
        return data_fim;
    }

    public void setData_fim(LocalDateTime data_fim) {
        this.data_fim = data_fim;
    }

    public double getEnergia_consumida() {
        return energia_consumida;
    }

    public void setEnergia_consumida(double energia_consumida) {
        this.energia_consumida = energia_consumida;
    }

    public PostoCarregamento getPostoCarregamento() {
        return postoCarregamento;
    }

    public void setPostoCarregamento(PostoCarregamento postoCarregamento) {
        this.postoCarregamento = postoCarregamento;
    }

    @Override
    public String toString() {
        return "Matricula: " + matricula + "\n" + "Custo por kWh: " + custo_kwh + "\n" + "Estado de pagamento: "
                + estado_pagamento + "\n" + "Custo da sessao: " + custo_sessao + "\n" + "Cliente: " + cliente + "\n"
                + "Veiculo: " + veiculo + "\n" + "Codigo da sessao: " + codigo_sessao + "\n" + "Data de inicio: "
                + data_inicio + "\n" + "Data de fim: " + data_fim + "\n" + "Energia consumida: " + energia_consumida
                + "\n" + "Posto de Carregamento: " + postoCarregamento + "\n"; // new line
    }
}
