package project;

public class ClienteRegular extends Cliente {
    public ClienteRegular(int id, String nome, String email, double saldoInicial) {
        super(id, nome, email, saldoInicial);
    }

    @Override
    public double calcularDesconto(double precoBase) {
        return 0.0; // sem desconto
    }
}
