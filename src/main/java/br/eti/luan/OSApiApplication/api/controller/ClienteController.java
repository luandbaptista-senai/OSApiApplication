/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.luan.OSApiApplication.api.controller;

import br.eti.luan.OSApiApplication.domain.model.Cliente;
import br.eti.luan.OSApiApplication.domain.model.OrdemServico;
import br.eti.luan.OSApiApplication.domain.repository.ClienteRepository;
import br.eti.luan.OSApiApplication.domain.sevice.ClienteService;
import br.eti.luan.OSApiApplication.domain.sevice.OrdemServicoService;
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
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author digma
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public List<Cliente> listas() {
        return clienteRepository.findAll();
        //return clienteRepository.findByNome("KGe");
        //return clienteRepository.findByNomeContaining("Silva");
                }
    
    @GetMapping("/{clienteID}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteID){
        Optional<Cliente> cliente = clienteRepository.findById(clienteID);
        
        if(cliente.isPresent()){
        return ResponseEntity.ok(cliente.get());
        }else {
        return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){
        
        return clienteService.salvar(cliente);
    }
    
    @PutMapping("/{clienteID}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteID,
                                            @RequestBody Cliente cliente){
    //verifica se existe o cliente
        if(!clienteRepository.existsById(clienteID)) {
     return ResponseEntity.notFound().build();
        }
        
    cliente.setId(clienteID);
    cliente = clienteService.salvar(cliente);
    return ResponseEntity.ok(cliente);
    }
    
    
    @DeleteMapping("/{clienteID}")
    public ResponseEntity<Void> excluir(@PathVariable Long clienteID){
    //verifica se existe ou nao
    if(!clienteRepository.existsById(clienteID)){
    return ResponseEntity.notFound().build();
    }
    
    clienteService.excluir(clienteID);
    return ResponseEntity.noContent().build();
    }
    

    
    
    
}
