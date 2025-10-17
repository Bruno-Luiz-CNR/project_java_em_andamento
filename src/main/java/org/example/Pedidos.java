package org.example;

import java.time.LocalDate;

public class Pedidos {
    private int id;
    private int idCliente;
    private LocalDate dataCadastro;
    private Double valorTotal;
    private String nome;
    private String telefone;
    private String email;
    private String statusPedido;

    public Pedidos(int id, int idCliente,String nome,String email, String telefone, LocalDate dataCadastro,
                   Double valorTotal, String statusPedido) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.idCliente = idCliente;
        this.dataCadastro = dataCadastro;
        this.valorTotal = valorTotal;
        this.statusPedido = statusPedido;
    }
    public void setId(int id) {this.id = id;}
    public void setIdCliente(int idCliente) {this.idCliente = idCliente;}
    public void setdataCadastro(LocalDate dataCadastro) {this.dataCadastro = dataCadastro;}
    public void setValorTotal(Double valorTotal) {this.valorTotal = valorTotal;}
    public void setStatusPedido(String statusPedido) {this.statusPedido = statusPedido;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
    public void setNome(String nome) {this.nome = nome;}
    public void setEmail(String email) {this.email = email;}

    public String getNome() {return this.nome;}
    public int getId() {return id;}
    public String getTelefone() {return this.telefone;}
    public int getIdCliente() {return idCliente;}
    public LocalDate getdataCadastro() {return dataCadastro;}
    public Double getValorTotal() {return valorTotal;}
    public String getStatusPedido() {return statusPedido;}
    public String getEmail() {return email;}

    public String toString(){
        return "Cliente ID: " + id + " | Nome: " + nome + " | Email: " + email +
                " | Telefone: " + telefone + " | Cadastrado em: " + dataCadastro;
    }
}
