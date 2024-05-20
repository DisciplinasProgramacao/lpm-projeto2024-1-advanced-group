package com.advanced.comidinhasveganas.services;

import com.advanced.comidinhasveganas.models.Requisicao;
import com.advanced.comidinhasveganas.repositories.RequisicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequisicaoService {

    @Autowired
    private RequisicaoRepository requisicaoRepository;

    public List<Requisicao> findAll() {
        return requisicaoRepository.findAll();
    }

    public Optional<Requisicao> findById(int id) {
        return requisicaoRepository.findById(id);
    }

    public Requisicao save(Requisicao requisicao) {
        return requisicaoRepository.save(requisicao);
    }

    public void deleteById(int id) {
        requisicaoRepository.deleteById(id);
    }
}
