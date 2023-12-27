import java.io.Serializable;

public class PostoCarregamento implements Serializable {
    protected int codigo_posto;
    protected String localizacao; // morada
    protected String tipo_posto; // PCN - Posto de Carregamento Normal, PCR - Posto de Carregamento Rapido, PCUR
                                 // - Posto de Carregamento Ultrarrapido
    protected double custo_kwh; // custo por kWh em euros
    protected int numero_veiculos; // numero de veiculos que podem carregar em simultaneo
    protected double tempo_carregamento;

    private double valor_faturado;

    public PostoCarregamento(int codigo_posto, String localizacao, String tipo_posto, double custo_kwh,
            int numero_veiculos, double tempo_carregamento) {
        setCodigo_posto(codigo_posto);
        setLocalizacao(localizacao);
        setTipo_posto(tipo_posto);
        setCusto_kwh(custo_kwh);
        setNumero_veiculos(numero_veiculos);
        setTempo_carregamento(tempo_carregamento);
    }

    public int getCodigo_posto() {
        return codigo_posto;
    }

    public void setCodigo_posto(int codigo_posto) {
        this.codigo_posto = codigo_posto;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getTipo_posto() {
        return tipo_posto;
    }

    public void setTipo_posto(String tipo_posto) {
        this.tipo_posto = tipo_posto;
    }

    public double getCusto_kwh() {
        return custo_kwh;
    }

    public void setCusto_kwh(double custo_kwh) {
        this.custo_kwh = custo_kwh;
    }

    public int getNumero_veiculos() {
        return numero_veiculos;
    }

    public void setNumero_veiculos(int numero_veiculos) {
        this.numero_veiculos = numero_veiculos;
    }

    public double getTempo_carregamento() {
        return tempo_carregamento;
    }

    public void setTempo_carregamento(double tempo_carregamento) {
        this.tempo_carregamento = tempo_carregamento;
    }

    public double getValor_faturado() {
        return valor_faturado;
    }

    public void setValor_faturado(double valor_faturado) {
        this.valor_faturado = valor_faturado;
    }

    @Override
    public String toString() {
        return "Codigo do posto: " + codigo_posto + "\n" + "Localizacao: " + localizacao + "\n" + "Tipo de posto: "
                + tipo_posto + "\n" + "Custo por kWh: " + custo_kwh + "\n" + "Numero de veiculos: " + numero_veiculos
                + "\n" + "Tempo de carregamento: " + tempo_carregamento + "\n";
    }
}