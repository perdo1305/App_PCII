import java.util.Date;

public class VeiculoEletrico extends Veiculo {
    protected float tempo_carregamento;

    /**
     * Construtor da classe VeiculoEletrico
     *
     * @param marca                    marca do veiculo (string)
     * @param modelo                   modelo do veículo (string)
     * @param matricula                matricula do veiculo (string formato XX-XX-XX)
     * @param data_registo             data de registo do veículo (Date)
     * @param potencia                 potencia do veículo KW (int)
     * @param capacidade_bateria       capacidade da bateria do veículo Kwh (int)
     * @param autonomia                autonomia do veículo em Km/carga (int)
     * @param velocidadedeCarregamento velocidade de carregamento do veículo em Kwh (double)
     * @param tempo_carregamento       tempo de carregamento do veículo em horas (float)
     */

    public VeiculoEletrico(String marca, String modelo, String matricula, Date data_registo, int potencia,
                           int capacidade_bateria, int autonomia, double velocidadedeCarregamento, float tempo_carregamento) {

        super(marca, modelo, matricula, data_registo, potencia, capacidade_bateria, autonomia, velocidadedeCarregamento);
        setTempo_carregamento(tempo_carregamento);
    }

    /**
     * Setter do tempo de carregamento
     *
     * @param tempo_carregamento tempo de carregamento do veículo em horas (float)
     */
    public void setTempo_carregamento(float tempo_carregamento) {
        this.tempo_carregamento = tempo_carregamento;
    }

    /**
     * ToString da classe VeiculoEletrico
     *
     * @return ‘string’ com a informação do veículo eletrico
     */
    @Override
    public String toString() {
        return super.toString() + "Tempo de carregamento (H): " + tempo_carregamento + "\n";
    }
}
