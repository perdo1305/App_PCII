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

    /**
     * Construtor da classe Veiculo
     *
     * @param marca                    marca do veiculo (string)
     * @param modelo                   modelo do veículo (string)
     * @param matricula                matricula do veiculo (string formato XX-XX-XX)
     * @param data_registo             data de registo do veículo (Date)
     * @param potencia                 potencia do veículo KW (int)
     * @param capacidade_bateria       capacidade da bateria do veículo Kwh (int)
     * @param autonomia                autonomia do veículo em Km/carga (int)
     * @param velocidadedeCarregamento velocidade de carregamento do veículo em Kwh (double)
     */
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

    /**
     * Getter da velocidade de carregamento
     *
     * @return velocidade de carregamento do veículo em Kwh (double)
     */
    public double getVelocidadedeCarregamento() {
        return velocidadedeCarregamento;
    }

    /**
     * Setter da velocidade de carregamento
     *
     * @param velocidadedeCarregamento velocidade de carregamento do veículo em Kwh (double)
     */
    public void setVelocidadedeCarregamento(double velocidadedeCarregamento) {
        this.velocidadedeCarregamento = velocidadedeCarregamento;
    }

    /**
     * Getter da Marca
     *
     * @return marca do veículo (string)
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Setter da Marca
     *
     * @param marca marca do veículo (string)
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Getter do Modelo
     *
     * @return modelo do veículo (string)
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Setter do Modelo
     *
     * @param modelo modelo do veículo (string)
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Getter da Matricula
     *
     * @return matricula do veículo (string UPPERCASE)
     */
    public String getMatricula() {
        matricula = matricula.toUpperCase();
        return matricula;
    }

    /**
     * Setter da Matricula
     *
     * @param matricula matricula do veículo
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Setter da Data de Registo
     *
     * @param data_registo data de registo do veículo (Date)
     */
    public void setData_registo(Date data_registo) {
        this.data_registo = data_registo;
    }

    /**
     * Setter da Potencia
     *
     * @param potencia potencia do veículo KW (int)
     */
    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    /**
     * Getter da Capacidade da Bateria
     *
     * @return capacidade da bateria do veículo Kwh (int)
     */
    public int getCapacidade_bateria() {
        return capacidade_bateria;
    }

    /**
     * Setter da Capacidade da Bateria
     *
     * @param capacidade_bateria capacidade da bateria do veículo Kwh (int)
     */
    public void setCapacidade_bateria(int capacidade_bateria) {
        this.capacidade_bateria = capacidade_bateria;
    }

    /**
     * Setter da Autonomia
     *
     * @param autonomia autonomia do veículo em Km/carga (int)
     */
    public void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }

    /**
     * ToString da classe Veiculo
     *
     * @return ‘string’ com os atributos da classe Veiculo
     */
    @Override
    public String toString() {
        return "Marca: " + marca + "\nModelo: " + modelo + "\nMatricula: " + matricula + "\nData de registo: "
                + dateFormat.format(data_registo) + "\nPotencia (KWh): " + potencia + "\nCapacidade da bateria (Wh): "
                + capacidade_bateria + "\nAutonomia (Km): " + autonomia + "\n";
    }

}
