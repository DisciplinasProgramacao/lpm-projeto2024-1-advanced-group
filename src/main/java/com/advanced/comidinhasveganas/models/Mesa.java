package com.advanced.comidinhasveganas.models;

import javax.persistence.*;

@Entity
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMesa;
    
    private int capacidade;
    private int clientesSentados;

    public Mesa() {}

    public Mesa(int capacidade, int clientesSentados) {
        this.capacidade = capacidade;
        this.clientesSentados = clientesSentados;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getClientesSentados() {
        return clientesSentados;
    }

    public void setClientesSentados(int clientesSentados) {
        this.clientesSentados = clientesSentados;
    }

    public boolean verificarDisponibilidade() {
        return clientesSentados < capacidade;
    }

    @Override
    public String toString() {
        return "Mesa{" +
                "idMesa=" + idMesa +
                ", capacidade=" + capacidade +
                ", clientesSentados=" + clientesSentados +
                '}';
    }
}
