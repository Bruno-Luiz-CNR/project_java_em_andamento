package repository;

import model.Cliente;
import org.example.Pedidos;
import java.util.ArrayList;
import java.util.List;

public class Repositorio {

    // Listas em memória
    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Pedidos> pedidos = new ArrayList<>();

    private int proximoIdCliente = 1;
    private int proximoIdPedido = 1;

    // --- Repositório de Clientes ---

    // Salvar novo cliente
    public void salvar(Cliente cliente) {
        // Gera ID automaticamente caso não tenha
        if (cliente.getId() == 0) {
            cliente.setId(proximoIdCliente++);
        }
        clientes.add(cliente);
    }

    // Retorna todos os clientes
    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    // Busca cliente pelo ID
    public Cliente buscarClientePorId(int id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Remove cliente pelo ID (Método renomeado/atualizado)
    public boolean removerCliente(int id) {
        return clientes.removeIf(c -> c.getId() == id);
    }

    // Salvar novo pedido
    public void salvarPedido(Pedidos pedido) {
        if (pedido.getId() == 0) {
            pedido.setId(proximoIdPedido++);
        }
        pedidos.add(pedido);
    }

    // Retorna todos os pedidos
    public List<Pedidos> listarPedidos() {
        return new ArrayList<>(pedidos);
    }
}