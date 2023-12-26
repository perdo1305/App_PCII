import java.util.Date;

public class VeiculoEletrico extends Veiculo {
    protected float tempo_carregamento;

    public VeiculoEletrico(String marca, String modelo, String matricula, Date data_registo, int autonomia,
                             double velocidadeCarregamento, int potencia, float tempo_carregamento, int capacidade_bateria) {

       super( marca,  modelo,  matricula,  data_registo,  potencia, capacidade_bateria,  autonomia);
        setTempo_carregamento(tempo_carregamento);
    }

    public float getTempo_carregamento() {
        return tempo_carregamento;
    }

    public void setTempo_carregamento(float tempo_carregamento) {
        this.tempo_carregamento = tempo_carregamento;
    }
    @Override
    public String toString() {
        return super.toString() + "Tempo de carregamento (H): " + tempo_carregamento + "\n";
    }
}
