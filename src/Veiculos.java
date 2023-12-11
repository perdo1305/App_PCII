import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Veiculos implements java.io.Serializable{

    static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    protected String marca;
    protected String modelo;
    protected String matricula;
    protected Date data_registo;
    protected int potencia; // Cv
    protected int capacidade_bateria; // kw/h
    protected int autonomia;// km por carga

    public Veiculos(String marca, String modelo, String matricula, Date data_registo, int autonomia,
            double velocidadeCarregamento, int potencia) {
        setMarca(marca);
        setModelo(modelo);
        setMatricula(matricula);
        setData_registo(data_registo);
        setPotencia(potencia);
        setCapacidade_bateria(capacidade_bateria);
        setAutonomia(autonomia);
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
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getData_registo() {
        return data_registo;
    }

    public void setData_registo(Date data_registo) {
        this.data_registo = data_registo;
    }

    public int getPotencia() {
        return potencia;
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

    public int getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }

}