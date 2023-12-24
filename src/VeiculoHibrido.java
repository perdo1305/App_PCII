import java.util.Date;

public class VeiculoHibrido extends Veiculo {
    protected int cilindrada;
    protected double consumo;
    protected double emissao;

    public VeiculoHibrido(String marca, String modelo, String matricula, Date data_registo, int autonomia,
                            double velocidadeCarregamento, int potencia, int cilindrada, double consumo, double emissao) {
        super(marca, modelo, matricula, data_registo, autonomia, velocidadeCarregamento, potencia);
        setCilindrada(cilindrada);
        setConsumo(consumo);
        setEmissao(emissao);
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public double getEmissao() {
        return emissao;
    }

    public void setEmissao(double emissao) {
        this.emissao = emissao;
    }

    @Override
    public String toString() {
        return super.toString() + "Cilindrada: " + cilindrada + "\n" + "Consumo: " + consumo + "\n" + "Emissao: " + emissao + "\n";
    }
}
