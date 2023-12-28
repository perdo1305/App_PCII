import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Veiculo implements Serializable {

    static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    protected String marca;
    protected String modelo;
    protected String matricula;
    protected Date data_registo;
    protected int potencia; 
    protected int capacidade_bateria; 
    protected int autonomia;
    protected double velocidadedeCarregamento;

    public Veiculo(String marca, String modelo, String matricula, Date data_registo, int potencia,
            int capacidade_bateria, int autonomia, double velocidadedeCarregamento) {
        setMarca(marca);
        setModelo(modelo);
        setMatricula(matricula);
        setData_registo(data_registo);
        setPotencia(potencia);
        setCapacidade_bateria(capacidade_bateria);
        setAutonomia(autonomia);
        setVelocidadedeCarregamento(velocidadedeCarregamento);
    }

     public double getVelocidadedeCarregamento() {
        return velocidadedeCarregamento;
    }

    public void setVelocidadedeCarregamento(double velocidadedeCarregamento) {
        this.velocidadedeCarregamento = velocidadedeCarregamento;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        matricula = matricula.toUpperCase();
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setData_registo(Date data_registo) {
        this.data_registo = data_registo;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getCapacidade_bateria() {
        return capacidade_bateria;
    }

    public void setCapacidade_bateria(int capacidade_bateria) {
        this.capacidade_bateria = capacidade_bateria;
    }

    public void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }

    @Override
    public String toString() {
        return "Marca: " + marca + "\nModelo: " + modelo + "\nMatricula: " + matricula + "\nData de registo: "
                + dateFormat.format(data_registo) + "\nPotencia (KWh): " + potencia + "\nCapacidade da bateria (Wh): "
                + capacidade_bateria + "\nAutonomia (Km): " + autonomia + "\n";
    }

}
