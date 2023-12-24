import java.io.Serializable;
import java.time.LocalDateTime;


public class Pagamento implements Serializable {
    protected SessaoCarregamento sessao;
    protected String metodoPagamento;
    protected LocalDateTime dataTransacao;
    protected LocalDateTime horaTransacao;
    protected boolean pago;

    public Pagamento(SessaoCarregamento sessao, String metodoPagamento, LocalDateTime dataTransacao, LocalDateTime horaTransacao, boolean pago) {
        this.sessao = sessao;
        this.metodoPagamento = metodoPagamento;
        this.dataTransacao = dataTransacao;
        this.horaTransacao = horaTransacao;
        this.pago = pago;
    }

    public SessaoCarregamento getSessao() {
        return sessao;
    }

    public void setSessao(SessaoCarregamento sessao) {
        this.sessao = sessao;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public LocalDateTime getHoraTransacao() {
        return horaTransacao;
    }

    public void setHoraTransacao(LocalDateTime horaTransacao) {
        this.horaTransacao = horaTransacao;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
}