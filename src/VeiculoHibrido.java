import java.util.Date;

public class VeiculoHibrido extends Veiculo {
    protected int cilindrada;
    protected double consumo;
    protected double emissao;

    public VeiculoHibrido(String marca, String modelo, String matricula, Date data_registo, int autonomia,
                            double velocidadeCarregamento, int potencia, int cilindrada, double consumo, double emissao) {
        super(marca, modelo, matricula, data_registo, autonomia, (int) velocidadeCarregamento, potencia);
        //TODO velocidadeCarregamento esta mal feito, devia de ter tambem a capacidade da bateria
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
        return super.toString() + "Cilindrada: " + cilindrada + "\n" + "Consumo: " + consumo + "\n" + "Emissao: " + emissao + "\n";
    }
}
