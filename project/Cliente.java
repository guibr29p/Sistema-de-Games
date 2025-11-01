package project;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    private int id;
    private String nome;
    private String email;
    private double saldo;
    private List<Venda> historico = new ArrayList<>();

    public Cliente(int id, String nome, String email, double saldoInicial) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        setSaldo(saldoInicial);
    }

    // polimorfismo
    public abstract double calcularDesconto(double precoBase);

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("Saldo não pode ficar negativo ao editar/criar.");
        }
        this.saldo = saldo;
    }

    public void adicionarSaldo(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor para adicionar deve ser positivo.");
        }
        this.saldo += valor;
    }

    public void debitar(double valor) throws SaldoInsuficienteException {
        if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para compra.");
        }
        this.saldo -= valor;
    }

    public List<Venda> getHistorico() { return historico; }
    public void adicionarVenda(Venda v) { historico.add(v); }

    @Override
    public String toString() {
        return "#" + id + " - " + nome + " <" + email + "> | Saldo: R$" +
               String.format("%.2f", saldo) + " | Tipo: " + getClass().getSimpleName();
    }
}
