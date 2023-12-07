import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pagamento {
    static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private String metodoDePagamento;
    private Date dataTransacao;

    public Pagamento(String metodoDePagamento, Date dataTransacao) {
        this.metodoDePagamento = metodoDePagamento;
        this.dataTransacao = dataTransacao;
    }

    public String getMetodoDePagamento() {
        return metodoDePagamento;
    }

    public void setMetodoDePagamento(String metodoDePagamento) {
        this.metodoDePagamento = metodoDePagamento;
    }

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
}