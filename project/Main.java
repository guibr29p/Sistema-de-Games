package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/** 
Grupo:
NOME: Guilherme C.M  RA:925110655.
NOME: Gideão M  RA:925108311.
NOME: Willian M RA:925109803.
NOME: Samara B RA: 925108078.
NOME: Jeniffer O.F RA:925115615.
NOME: Felipe S.C RA:925108896.
NOME: Christian M.O RA:925111139.
**/
public class Main {
    private static final Scanner sc = new Scanner(System.in);

    private static final List<Jogo> jogos = new ArrayList<>();
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final List<Venda> vendas = new ArrayList<>();

    public static void main(String[] args) {
        int opcao = 0;
        while (opcao != 13) {
            mostrarMenu();
            opcao = lerInt("Escolha uma opção: ");

            try {
                if (opcao == 1) cadastrarJogo();
                else if (opcao == 2) cadastrarCliente();
                else if (opcao == 3) editarJogo();
                else if (opcao == 4) editarCliente();
                else if (opcao == 5) atualizarJogo();
                else if (opcao == 6) atualizarCliente();
                else if (opcao == 7) deletarJogo();
                else if (opcao == 8) deletarCliente();
                else if (opcao == 9) listarJogos();
                else if (opcao == 10) listarClientes();
                else if (opcao == 11) realizarVenda();
                else if (opcao == 12) historicoCompras();
                else if (opcao == 13) System.out.println("Saindo...");
                else System.out.println("Opção inválida.");
            } catch (Exception e) {
                // para não derrubar o programa caso algo de errado
                System.out.println("Erro: " + e.getMessage());
            }
        }

        sc.close();
    }

    // menu principal
    private static void mostrarMenu() {
        System.out.println("\n==== LOJA DE JOGOS ====");
        System.out.println("1 - Cadastrar Jogo");
        System.out.println("2 - Cadastrar Cliente (Regular/Premium)");
        System.out.println("3 - Editar Jogo");
        System.out.println("4 - Editar Cliente");
        System.out.println("5 - Atualizar Jogo (alternar disponível/indisponível)");
        System.out.println("6 - Atualizar Cliente (adicionar saldo)");
        System.out.println("7 - Deletar Jogo");
        System.out.println("8 - Deletar Cliente");
        System.out.println("9 - Listar Jogos");
        System.out.println("10 - Listar Clientes");
        System.out.println("11 - Realizar Venda");
        System.out.println("12 - Exibir Histórico de Compras");
        System.out.println("13 - Sair");
    }

    // leituras
    private static int lerInt(String msg) {
        System.out.print(msg);
        while (!sc.hasNextInt()) {
            System.out.print("Valor inválido. " + msg);
            sc.next();
        }
        int v = sc.nextInt();
        return v;
    }

    private static double lerDouble(String msg) {
        System.out.print(msg);
        while (!sc.hasNextDouble()) {
            System.out.print("Valor inválido. " + msg);
            sc.next();
        }
        double v = sc.nextDouble();
        return v;
    }

    private static String lerLinha(String msg) {
        System.out.print(msg);
        String texto = sc.nextLine();

        // se o texto vier vazio porque sobrou um "enter" anterior, lê de novo
        if (texto.isEmpty()) {
            texto = sc.nextLine();
        }
        return texto;
    }

    // BUSCAS 
    private static Jogo buscarJogoPorId(int id) {
        for (Jogo j : jogos) {
            if (j.getId() == id) return j;
        }
        return null;
    }

    private static Cliente buscarClientePorId(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    //  OPÇÕES 
    private static void cadastrarJogo() {
        int id = lerInt("ID do jogo: ");
        String titulo = lerLinha("Título: ");
        String genero = lerLinha("Gênero: ");
        int ano = lerInt("Ano de lançamento: ");
        double preco = lerDouble("Preço: ");

        Jogo j = new Jogo(id, titulo, genero, ano, preco, true);
        jogos.add(j);
        System.out.println("Jogo cadastrado.");
    }

    private static void cadastrarCliente() {
        int id = lerInt("ID do cliente: ");
        String nome = lerLinha("Nome: ");
        String email = lerLinha("E-mail: ");
        double saldo = lerDouble("Saldo inicial: ");

        System.out.println("Tipo de cliente: 1- Regular  |  2- Premium");
        int tipo = lerInt("Digite o tipo: ");

        Cliente c;
        if (tipo == 2) {
            c = new ClientePremium(id, nome, email, saldo);
        } else {
            c = new ClienteRegular(id, nome, email, saldo);
        }

        clientes.add(c);
        System.out.println("Cliente cadastrado.");
    }

    private static void editarJogo() {
        int id = lerInt("ID do jogo para editar: ");
        Jogo j = buscarJogoPorId(id);
        if (j == null) {
            System.out.println("Jogo não encontrado.");
            return;
        }

        String novoTitulo = lerLinha("Novo título (enter para manter): ");
        if (!novoTitulo.isBlank()) j.setTitulo(novoTitulo);

        String novoGenero = lerLinha("Novo gênero (enter para manter): ");
        if (!novoGenero.isBlank()) j.setGenero(novoGenero);

        String sAno = lerLinha("Novo ano (enter para manter): ");
        if (!sAno.isBlank()) j.setAnoLancamento(Integer.parseInt(sAno));

        String sPreco = lerLinha("Novo preço (enter para manter): ");
        if (!sPreco.isBlank()) j.setPreco(Double.parseDouble(sPreco));

        System.out.println("Jogo atualizado.");
    }

    private static void editarCliente() {
        int id = lerInt("ID do cliente para editar: ");
        Cliente c = buscarClientePorId(id);
        if (c == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        String novoNome = lerLinha("Novo nome (enter para manter): ");
        if (!novoNome.isBlank()) c.setNome(novoNome);

        String novoEmail = lerLinha("Novo e-mail (enter para manter): ");
        if (!novoEmail.isBlank()) c.setEmail(novoEmail);

        String sSaldo = lerLinha("Novo saldo (enter para manter): ");
        if (!sSaldo.isBlank()) c.setSaldo(Double.parseDouble(sSaldo));

        System.out.println("Cliente atualizado.");
    }

    private static void atualizarJogo() {
        int id = lerInt("ID do jogo para alternar status: ");
        Jogo j = buscarJogoPorId(id);
        if (j == null) {
            System.out.println("Jogo não encontrado.");
            return;
        }
        boolean novo = !j.isDisponivel();
        j.setDisponivel(novo);
        if (novo) System.out.println("Status: disponível");
        else System.out.println("Status: indisponível");
    }

    private static void atualizarCliente() {
        int id = lerInt("ID do cliente para adicionar saldo: ");
        Cliente c = buscarClientePorId(id);
        if (c == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        double valor = lerDouble("Valor para adicionar: ");
        c.adicionarSaldo(valor);
        System.out.println("Saldo atualizado. Saldo atual: R$" + String.format("%.2f", c.getSaldo()));
    }

    private static void deletarJogo() {
        int id = lerInt("ID do jogo para deletar: ");
        Jogo j = buscarJogoPorId(id);
        if (j == null) {
            System.out.println("Jogo não encontrado.");
            return;
        }
        jogos.remove(j);
        System.out.println("Jogo removido.");
    }

    private static void deletarCliente() {
        int id = lerInt("ID do cliente para deletar: ");
        Cliente c = buscarClientePorId(id);
        if (c == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        clientes.remove(c);
        System.out.println("Cliente removido.");
    }

    private static void listarJogos() {
        if (jogos.isEmpty()) {
            System.out.println("Nenhum jogo cadastrado.");
            return;
        }
        System.out.println("=== Jogos ===");
        for (Jogo j : jogos) {
            System.out.println(j);
        }
    }

    private static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        System.out.println("=== Clientes ===");
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }

    private static void realizarVenda() {
        int idCliente = lerInt("ID do cliente: ");
        int idJogo = lerInt("ID do jogo: ");

        Cliente c = buscarClientePorId(idCliente);
        if (c == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        Jogo j = buscarJogoPorId(idJogo);
        if (j == null) {
            System.out.println("Jogo não encontrado.");
            return;
        }

        if (!j.isDisponivel()) {
            System.out.println("Falha: jogo indisponível.");
            return;
        }

        double preco = j.getPreco();
        double desconto = c.calcularDesconto(preco);
        double valorFinal = preco - desconto;

        try {
            c.debitar(valorFinal);
            j.setDisponivel(false);

            Venda v = new Venda(c, j, valorFinal);
            vendas.add(v);
            c.adicionarVenda(v);

            System.out.println("Venda realizada: " + v);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Falha: " + e.getMessage());
        }
    }

    private static void historicoCompras() {
        int idCliente = lerInt("ID do cliente: ");
        Cliente c = buscarClientePorId(idCliente);
        if (c == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        if (c.getHistorico().isEmpty()) {
            System.out.println("Sem compras registradas.");
            return;
        }

        System.out.println("=== Histórico de Compras de " + c.getNome() + " ===");
        for (Venda v : c.getHistorico()) {
            System.out.println(v);
        }
    }
}

