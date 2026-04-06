/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.luan.OSApiApplication.api.controller;
import br.eti.luan.OSApiApplication.domain.model.Cliente;
import br.eti.luan.OSApiApplication.domain.model.Comentario;
import br.eti.luan.OSApiApplication.domain.model.Comentario;
import br.eti.luan.OSApiApplication.domain.repository.ComentarioRepository;
import br.eti.luan.OSApiApplication.domain.repository.ComentarioRepository;
import br.eti.luan.OSApiApplication.domain.sevice.ComentarioService;
import br.eti.luan.OSApiApplication.domain.sevice.ComentarioService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */
@RestController
@RequestMapping("/comentarios") // Define a rota base aqui
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;
        
    @Autowired
    private ComentarioService comentarioService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comentario adicionar(@Valid @RequestBody Comentario comentario) {
        return comentarioService.criar(comentario);
    }
    
    @GetMapping
    public List<Comentario> listar() {
        return comentarioRepository.findAll();
    }

    @GetMapping("/{comentarioID}")
    public ResponseEntity<Comentario> buscar(@PathVariable Long comentarioID) {
        return comentarioRepository.findById(comentarioID)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{comentarioID}")
    public ResponseEntity<Void> excluir(@PathVariable Long comentarioID) {
        if (!comentarioRepository.existsById(comentarioID)) {
            return ResponseEntity.notFound().build();
        }
        comentarioService.excluir(comentarioID);
        return ResponseEntity.noContent().build();
    }
    
    
    
    @GetMapping("/por-ordem/{ordemServicoId}")
    public List<Comentario> listarPorOrdem(@PathVariable Long ordemServicoId) {
        return comentarioRepository.findByOrdemServicoId(ordemServicoId);
    }

    
    @GetMapping("/por-cliente/{clienteId}")
    public List<Comentario> listarPorCliente(@PathVariable Long clienteId) {
        return comentarioRepository.findByClienteId(clienteId);
    }
    
    
}