package model;

import java.time.LocalDate;

public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataCadastro;


    public Cliente(int id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataCadastro = LocalDate.now(); // registra a data do cadastro automaticamente
    }

    public Cliente() {
        this.dataCadastro = LocalDate.now();
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public LocalDate getDataCadastro() { return dataCadastro; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    @Override
    public String toString() {
        return "Cliente ID: " + id + " | Nome: " + nome +
                " | Email: " + email + " | Telefone: " + telefone +
                " | Cadastrado em: " + dataCadastro;
    }
}
