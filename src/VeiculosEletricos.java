import java.util.Date;

public class VeiculosEletricos extends Veiculos {
    protected int tempo_carregamento;

    public VeiculosEletricos(String marca, String modelo, String matricula, Date data_registo, int autonomia,
            double velocidadeCarregamento, int potencia, int tempo_carregamento) {

        super(marca, modelo, matricula, data_registo, autonomia, velocidadeCarregamento, potencia);
        setTempo_carregamento(tempo_carregamento);
    }

    public int getTempo_carregamento() {
        return tempo_carregamento;
    }

    public void setTempo_carregamento(int tempo_carregamento) {
        this.tempo_carregamento = tempo_carregamento;
    }
}
