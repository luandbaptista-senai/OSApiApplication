/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.luan.OSApiApplication.domain.sevice;

import br.eti.luan.OSApiApplication.domain.exception.DomainException;
import br.eti.luan.OSApiApplication.domain.model.Comentario;
import br.eti.luan.OSApiApplication.domain.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.eti.luan.OSApiApplication.domain.exception.DomainException;
import br.eti.luan.OSApiApplication.domain.model.Cliente;
import br.eti.luan.OSApiApplication.domain.repository.ClienteRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author digma
 */
@Service
public class ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;
    
    public Comentario criar(Comentario comentario) {
        // A descrição já vem setada no objeto 'comentario' pelo Spring
        comentario.setDataEnvio(LocalDateTime.now());
        return comentarioRepository.save(comentario);
    }
    
    public void excluir(Long comentarioId) {
        comentarioRepository.deleteById(comentarioId);
    }
}