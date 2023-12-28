import java.io.Serializable;
import java.time.LocalDateTime;

public class Pagamento implements Serializable {
    protected SessaoCarregamento sessao;
    protected String metodoPagamento;
    protected LocalDateTime DataHoraTransacao;

    protected boolean pago;

    public Pagamento(SessaoCarregamento sessao, String metodoPagamento, LocalDateTime DataHoraTransacao, boolean pago) {
        this.sessao = sessao;
        this.metodoPagamento = metodoPagamento;
        this.DataHoraTransacao = DataHoraTransacao;

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

    public LocalDateTime getDataHoraTransacao() {
        return DataHoraTransacao;
    }

    public void setDataHoraTransacao(LocalDateTime dataHoraTransacao) {
        DataHoraTransacao = dataHoraTransacao;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    @Override
    public String toString() {
        // numero posto, sessao e metodoPagamento
        return "Numero do posto: " + sessao.getPostoCarregamento().getCodigo_posto() + "\n" +
                "Dados da sessao: " + sessao +
                "Metodo de pagamento: " + metodoPagamento + "\n";
    }
}