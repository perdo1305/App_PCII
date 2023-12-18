public class PostoCarregamento implements java.io.Serializable {
    protected int codigo_posto;
    protected String localizacao; // morada
    protected String tipo_posto; // PCN - Posto de Carregamento Normal, PCR - Posto de Carregamento Rapido , PCUR
                                 // - Posto de Carregamento Ultra Rapido
    protected double custo_kwh; // custo por kWh em euros
    protected int numero_veiculos; // numero de veiculos que podem carregar em simultaneo

    public PostoCarregamento(int codigo_posto, String localizacao, String tipo_posto, double custo_kwh,
            int numero_veiculos) {
        setCodigo_posto(codigo_posto);
        setLocalizacao(localizacao);
        setTipo_posto(tipo_posto);
        setCusto_kwh(custo_kwh);
        setNumero_veiculos(numero_veiculos);
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

}
