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

    /**
     * Construtor da classe PostoCarregamento
     *
     * @param codigo_posto      codigo do posto (int)
     * @param localizacao       localizacao do posto (string)
     * @param tipo_posto        tipo de posto (string)
     * @param custo_kwh         custo por kWh em euros (double)
     * @param numero_veiculos   numero de veículos que podem carregar em simultaneo
     *                          (int)
     * @param tempo_carregamento tempo de carregamento (double)
     */


    public PostoCarregamento(int codigo_posto, String localizacao, String tipo_posto, double custo_kwh,
            int numero_veiculos, double tempo_carregamento) {
        setCodigo_posto(codigo_posto);
        setLocalizacao(localizacao);
        setTipo_posto(tipo_posto);
        setCusto_kwh(custo_kwh);
        setNumero_veiculos(numero_veiculos);
        setTempo_carregamento(tempo_carregamento);
    }


    /**
     * Getter do codigo do posto
     * @return codigo do posto (int)
     */
    public int getCodigo_posto() {
        return codigo_posto;
    }

    /**
     * setter do codigo do posto
     * @param codigo_posto codigo do posto (int)
     */
    public void setCodigo_posto(int codigo_posto) {
        this.codigo_posto = codigo_posto;
    }

    /**
     * Getter da localizacao do posto
     * @return localizacao do posto (string)
     */
    public String getLocalizacao() {
        return localizacao;
    }
    /**
     * Setter da localizacao do posto
     * @param localizacao localizacao do posto (string)
     */
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
    /**
     * Getter do tipo de posto
     * @return tipo de posto (string)
     */
    public String getTipo_posto() {
        return tipo_posto;
    }
    /**
     * Setter do tipo de posto
     * @param tipo_posto tipo de posto (string)
     */
    public void setTipo_posto(String tipo_posto) {
        this.tipo_posto = tipo_posto;
    }

    /**
     * Getter do custo por kWh
     * @return custo por kWh (double)
     */
    public double getCusto_kwh() {
        return custo_kwh;
    }

    /**
     * Setter do custo por kWh
     * @param custo_kwh custo por kWh (double)
     */
    public void setCusto_kwh(double custo_kwh) {
        this.custo_kwh = custo_kwh;
    }

    /**
     * Getter do número de veiculos
     * @return numero de veiculos (int)
     */
    public int getNumero_veiculos() {
        return numero_veiculos;
    }
    /**
     * Setter do número de veiculos
     * @param numero_veiculos numero de veiculos (int)
     */
    public void setNumero_veiculos(int numero_veiculos) {
        this.numero_veiculos = numero_veiculos;
    }
    /**
     * Setter do tempo de carregamento
     * @param tempo_carregamento tempo de carregamento (double)
     */
    public void setTempo_carregamento(double tempo_carregamento) {
        this.tempo_carregamento = tempo_carregamento;
    }

    /**
     * Getter do valor faturado
     * @return valor faturado (double)
     */
    public double getValor_faturado() {
        return valor_faturado;
    }

    /**
     * Setter do valor faturado
     * @param valor_faturado valor faturado pelo posto de carregamento (double)
     */
    public void setValor_faturado(double valor_faturado) {
        this.valor_faturado = valor_faturado;
    }

    /**
     * ToString da classe PostoCarregamento
     * @return ‘string’ com a informação do posto de carregamento
     */
    @Override
    public String toString() {
        return "Codigo do posto: " + codigo_posto + "\n" + "Localizacao: " + localizacao + "\n" + "Tipo de posto: "
                + tipo_posto + "\n" + "Custo por kWh: " + custo_kwh + "\n" + "Numero de veiculos: " + numero_veiculos
                + "\n" + "Tempo de carregamento: " + tempo_carregamento + "\n";
    }
}