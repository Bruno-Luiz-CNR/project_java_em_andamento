package principal;

import model.Cliente;
import org.example.Pedidos;
import repository.Repositorio;
import java.time.LocalDate;
import java.util.Scanner;

public class Principal {

    private static final Repositorio repositorio = new Repositorio();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n# MENU PRINCIPAL #");
            System.out.println("1) Cadastrar cliente");
            System.out.println("2) Criar pedido");
            System.out.println("3) Mostrar pedidos");
            System.out.println("4) Remover cliente"); // Nova opção
            System.out.println("S) Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = sc.nextLine();

            switch (opcao.toUpperCase()) {
                case "1" -> cadastrarCliente();
                case "2" -> criarPedido();
                case "3" -> mostrarPedidos();
                case "4" -> removerCliente(); // Chama o novo método
                case "S" -> {
                    System.out.println("Encerrando o sistema...");
                    continuar = false;
                }
                default -> System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }

    private static void cadastrarCliente() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        // Cria o cliente com ID=0 para que o Repositorio defina o próximo ID
        Cliente cliente = new Cliente(0, nome, email, telefone);
        repositorio.salvar(cliente);

        System.out.println("Cliente cadastrado com sucesso!");
        System.out.println(cliente);
    }

    private static void criarPedido() {
        if (repositorio.listarClientes().isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
            return;
        }

        System.out.println("    LISTA DE CLIENTES DISPONÍVEIS:");
        for (Cliente c : repositorio.listarClientes()) {
            System.out.println("| ID: " + c.getId() + " | Nome: " + c.getNome());
        }

        System.out.print("Digite o ID do Cliente para vincular o pedido: ");

        int idIncPed = -1;
        try {
            idIncPed = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido! Digite apenas números.");
            return;
        }

        Cliente clienteSelecionado = repositorio.buscarClientePorId(idIncPed);

        if (clienteSelecionado == null) {
            System.out.println("Cliente não encontrado com o ID: " + idIncPed);
            return;
        }

        double valor = 0;
        boolean valorOk = false;

        while (!valorOk) {
            System.out.print("Digite o valor do pedido: R$");
            String input = sc.nextLine();

            if (input.isEmpty()) {
                System.out.println("Digite um valor!");
                continue;
            }

            try {
                valor = Double.parseDouble(input.replace(',', '.'));
                if (valor > 0) valorOk = true;
                else System.out.println("Valor deve ser maior que zero!");
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números válidos para o valor!");
            }
        }

        // Cria um objeto Pedidos e salva no repositório
        Pedidos novoPedido = new Pedidos(
                0, // ID 0 para ser definido pelo repositório
                clienteSelecionado.getId(),
                clienteSelecionado.getNome(),
                clienteSelecionado.getEmail(),
                clienteSelecionado.getTelefone(),
                LocalDate.now(),
                valor,
                "PENDENTE"
        );

        repositorio.salvarPedido(novoPedido);

        System.out.println("Pedido criado com sucesso!");
        System.out.println("Cliente: " + clienteSelecionado.getNome() + " | Valor: R$" + String.format("%.2f", valor));
    }

    private static void mostrarPedidos() {
        if (repositorio.listarPedidos().isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
            return;
        }

        System.out.println("=== Pedidos cadastrados ===");
        for (Pedidos p : repositorio.listarPedidos()) {
            System.out.println("ID Pedido: " + p.getId() + " | Cliente ID: " + p.getIdCliente() +
                    " | Nome: " + p.getNome() + " | Valor: R$" + String.format("%.2f", p.getValorTotal()) +
                    " | Status: " + p.getStatusPedido());
        }
    }

    // Novo método para remover cliente
    private static void removerCliente() {
        if (repositorio.listarClientes().isEmpty()) {
            System.out.println("Nenhum cliente cadastrado para remover.");
            return;
        }

        System.out.println("    LISTA DE CLIENTES DISPONÍVEIS:");
        for (Cliente c : repositorio.listarClientes()) {
            System.out.println("| ID: " + c.getId() + " | Nome: " + c.getNome());
        }

        System.out.print("Digite o ID do cliente que deseja remover: ");

        int idRemover = -1;
        try {
            idRemover = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido! Digite apenas números.");
            return;
        }

        // Remove o cliente usando o repositório
        if (repositorio.removerCliente(idRemover)) {
            System.out.println("✅ Cliente com ID " + idRemover + " removido com sucesso!");
        } else {
            System.out.println("❌ Cliente com ID " + idRemover + " não encontrado.");
        }
    }
}