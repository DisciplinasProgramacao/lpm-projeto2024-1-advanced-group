import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Requisicao {
    private int idRequisicao;
    private Cliente cliente;
    private Mesa mesa;
    private int numPessoas;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private boolean atendida;
    private double conta;
    private List<Pedido> pedidos;
    private boolean pago;

    public Requisicao(int idRequisicao, Cliente cliente, Mesa mesa, int numPessoas, LocalDateTime dataHoraEntrada) {
        this.idRequisicao = idRequisicao;
        this.cliente = cliente;
        this.mesa = mesa;
        this.numPessoas = numPessoas;
        this.dataHoraEntrada = dataHoraEntrada;
        this.dataHoraSaida = null;
        this.atendida = false;
        this.conta = 0.0;
        this.pedidos = new ArrayList<>();
        this.pago = false;
    }

    public int getIdRequisicao() {
        return idRequisicao;
    }

    public void setIdRequisicao(int idRequisicao) {
        this.idRequisicao = idRequisicao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public int getNumPessoas() {
        return numPessoas;
    }

    public void setNumPessoas(int numPessoas) {
        this.numPessoas = numPessoas;
    }

    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public LocalDateTime getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }

    public boolean isAtendida() {
        return atendida;
    }

    public void setAtendida(boolean atendida) {
        this.atendida = atendida;
    }

    public double getConta() {
        return conta;
    }

    public void setConta(double conta) {
        this.conta = conta;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public long calcularDuracao() {
        if (dataHoraSaida != null) {
            Duration duracao = Duration.between(dataHoraEntrada, dataHoraSaida);
            return duracao.toMinutes();
        } else {
            return 0;
        }
    }

    public void encerrarRequisicao() {
        if (!atendida) {
            atendida = true;
            dataHoraSaida = LocalDateTime.now();
        }
    }

    public boolean clienteSentado() {
        return cliente != null;
    }

    public void pagarConta() {
        this.pago = true;
        this.pedidos.clear();
    }

    public double totalDaConta(Cardapio cardapio) {
        double total = 0.0;
        for (Pedido pedido : pedidos) {
            ItemCardapio item = cardapio.obterItemPorID(pedido.getIdItem());
            if (item != null) {
                total += item.getPreco() * pedido.getQuantidade();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return "Requisicao{" +
            "idRequisicao=" + idRequisicao +
            ", cliente=" + cliente.getNome() +
            ", mesa=" + (mesa != null ? mesa.getIdMesa() : "Nenhuma") +
            ", numPessoas=" + numPessoas +
            ", dataHoraEntrada=" + dataHoraEntrada +
            ", dataHoraSaida=" + dataHoraSaida +
            ", atendida=" + atendida +
            ", conta=" + conta +
            ", pedidos=" + pedidos.size() +
            ", pago=" + pago +
        '}';
    }
}