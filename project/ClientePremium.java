package project;

public class ClientePremium extends Cliente {
    public ClientePremium(int id, String nome, String email, double saldoInicial) {
        super(id, nome, email, saldoInicial);
    }

    @Override
    public double calcularDesconto(double precoBase) {
        return precoBase * 0.10; // 10% de desconto, pode alterar se preferir 
    }
}
