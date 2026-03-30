/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.luan.OSApiApplication.api.controller;

import br.eti.luan.OSApiApplication.domain.model.OrdemServico;
import br.eti.luan.OSApiApplication.domain.model.StatusOrdemServico;
import br.eti.luan.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.eti.luan.OSApiApplication.domain.sevice.OrdemServicoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author digma
 */
@RestController
@RequestMapping("/ordem-servico")  
public class OrdemServicoController {
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
        
    @Autowired
    private OrdemServicoService ordemServicoService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico){
    return ordemServicoService.criar(ordemServico);
    }
    
    @GetMapping("/ordens-servico")
    public List<OrdemServico> listas() {
        return ordemServicoRepository.findAll();
    }
   
    
   @GetMapping("/ordem-servico/{ordemServicoID}")
    public ResponseEntity<OrdemServico> buscar(@PathVariable Long ordemServicoID){
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoID);
        
        if(ordemServico.isPresent()){
        return ResponseEntity.ok(ordemServico.get());
        }else {
        return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/ordens-servico")
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico adicionar(@Valid @RequestBody OrdemServico ordemServico){
        
        return ordemServicoService.criar(ordemServico);
    }
    
    @PutMapping("ordem-servico/{ordemServicoID}")
    public ResponseEntity<OrdemServico> atualizar(@Valid @PathVariable Long ordemServicoID,
                                            @RequestBody OrdemServico ordemServico){
    //verifica se existe o ordemServico
        if(!ordemServicoRepository.existsById(ordemServicoID)) {
     return ResponseEntity.notFound().build();
        }
        
    ordemServico.setId(ordemServicoID);
    ordemServico = ordemServicoService.criar(ordemServico);
    return ResponseEntity.ok(ordemServico);
    }
    
    
    @DeleteMapping("/ordem-servico/{ordemServicoID}")
    public ResponseEntity<Void> excluir(@PathVariable Long ordemServicoID){
    //verifica se existe ou nao
    if(!ordemServicoRepository.existsById(ordemServicoID)){
    return ResponseEntity.notFound().build();
    }
    
    ordemServicoService.excluir(ordemServicoID);
    return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/cliente/{clienteId}")
    public List<OrdemServico> listarPorCliente(@PathVariable Long clienteId){
    return ordemServicoRepository.findByClienteId(clienteId);
    }
    
    @PatchMapping("/atualiza-status/{id}")
    public void atualizarStatus(@PathVariable Long id, @RequestBody StatusInput input) {
        // Você usa o "input" que o Spring criou para pegar o texto "STATUS"
        ordemServicoService.mudarStatus(id, input.STATUS());
    }
    
    public record StatusInput(String STATUS) {}
    
    }
