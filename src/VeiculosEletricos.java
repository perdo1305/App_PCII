import java.util.Date;

public class VeiculosEletricos extends Veiculo {
    protected float tempo_carregamento;

    public VeiculosEletricos(String marca, String modelo, String matricula, Date data_registo, int autonomia,
            double velocidadeCarregamento, int potencia, float tempo_carregamento) {

        super(marca, modelo, matricula, data_registo, autonomia, velocidadeCarregamento, potencia);
        setTempo_carregamento(tempo_carregamento);
    }

    public float getTempo_carregamento() {
        return tempo_carregamento;
    }

    public void setTempo_carregamento(float tempo_carregamento) {
        this.tempo_carregamento = tempo_carregamento;
    }
}
