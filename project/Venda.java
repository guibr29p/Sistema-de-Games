package project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venda {
    private static int SEQ = 1;

    private int id;
    private Cliente cliente;
    private Jogo jogo;
    private double precoFinal;
    private LocalDateTime dataHora;

    public Venda(Cliente cliente, Jogo jogo, double precoFinal) {
        this.id = SEQ++;
        this.cliente = cliente;
        this.jogo = jogo;
        this.precoFinal = precoFinal;
        this.dataHora = LocalDateTime.now();
    }

    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Jogo getJogo() { return jogo; }
    public double getPrecoFinal() { return precoFinal; }
    public LocalDateTime getDataHora() { return dataHora; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Venda #" + id + " | " + cliente.getNome() + " comprou '" + jogo.getTitulo() +
               "' por R$" + String.format("%.2f", precoFinal) + " em " + dataHora.format(fmt);
    }
}
