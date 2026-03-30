/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.luan.OSApiApplication.domain.sevice;

import br.eti.luan.OSApiApplication.domain.exception.DomainException;
import br.eti.luan.OSApiApplication.domain.model.Cliente;
import br.eti.luan.OSApiApplication.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author digma
 */
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    
    public Cliente salvar(Cliente cliente) {
    Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
    //o metodo SAVE tambem pode registrar algo novo se o ID estiver vazio, caso contrário ele só altera
    
    //verificação de existencia do cliente
    if (clienteExistente != null && !clienteExistente.equals(cliente)) {
    //lançar exception
    throw new DomainException("Já existe um cliente cadastrado com esse email!");
    }
    return clienteRepository.save(cliente);
    }
    
    public void excluir(Long clienteID) {
    clienteRepository.deleteById(clienteID);
    }
    
}
