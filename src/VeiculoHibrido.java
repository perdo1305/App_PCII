import java.util.Date;

public class VeiculoHibrido extends Veiculo {
    protected int cilindrada;
    protected double consumo;
    protected double emissao;

    public VeiculoHibrido(String marca, String modelo, String matricula, Date data_registo, int potencia,
    int capacidade_bateria, int autonomia, double velocidadedeCarregamento, int cilindrada, double consumo, double emissao) {
        super(marca, modelo, matricula, data_registo, potencia, capacidade_bateria, autonomia, velocidadedeCarregamento);
        setCilindrada(cilindrada);
        setConsumo(consumo);
        setEmissao(emissao);
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public void setEmissao(double emissao) {
        this.emissao = emissao;
    }

    @Override
    public String toString() {
        return super.toString() + "Cilindrada: " + cilindrada + "\n" + "Consumo: " + consumo + "\n" + "Emissao: "
                + emissao + "\n";
    }
}
