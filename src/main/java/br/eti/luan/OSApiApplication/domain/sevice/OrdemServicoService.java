/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.luan.OSApiApplication.domain.sevice;

import br.eti.luan.OSApiApplication.domain.exception.DomainException;
import br.eti.luan.OSApiApplication.domain.model.OrdemServico;
import br.eti.luan.OSApiApplication.domain.model.StatusOrdemServico;
import br.eti.luan.OSApiApplication.domain.repository.OrdemServicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author digma
 */
@Service
public class OrdemServicoService {
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    public OrdemServico criar(OrdemServico ordemServico){
    ordemServico.setStatus(StatusOrdemServico.ABERTA);
    ordemServico.setDataAbertura(LocalDateTime.now());
    
    return ordemServicoRepository.save(ordemServico);
    }
    
     public void excluir(Long OrdemServicoID) {
    ordemServicoRepository.deleteById(OrdemServicoID);
    }
    
     /**
      * Implementa a atualização de Status da Ordem de Serviço
      * Verifica de existe antes de salvar
      * @param ordemServicoID
      * @param status
      * @return Optional<OrdemServico> or throw if not found.
      */
     public Optional<OrdemServico> atualizaStatus(Long ordemServicoID, StatusOrdemServico status){
             Optional<OrdemServico> optOrdemServico = ordemServicoRepository.findById(ordemServicoID);
     
     if(optOrdemServico.isPresent()){
        OrdemServico ordemServico = optOrdemServico.get();
        
        //verificacao se esta aberta
        if (ordemServico.getStatus()==StatusOrdemServico.ABERTA && status != StatusOrdemServico.ABERTA){
        ordemServico.setStatus(status);
        ordemServico.setDataFinalizacao(LocalDateTime.now());
        ordemServicoRepository.save(ordemServico);
        return Optional.of(ordemServico);
        
        } else {
        //finalizada ou cancelada
        return Optional.empty();
        }
}else {
     //lanca exception se nao encontrar id
     throw new DomainException("Não existe Ordem de Serviço com o id" + ordemServicoID);
     }
   }
    

@Transactional
    public void mudarStatus(Long id, String novoStatus) {
        // 1. Busca a ordem no banco
        OrdemServico ordem = ordemServicoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Ordem não encontrada"));

        // 2. Transforma o texto do JSON no valor do seu ENUM
        // O valueOf precisa que o texto seja IGUAL ao que está no Enum (ex: FINALIZADA)
        StatusOrdemServico statusEnum = StatusOrdemServico.valueOf(novoStatus.toUpperCase().trim());

        // 3. Atualiza e salva
        ordem.setStatus(statusEnum);
        ordemServicoRepository.save(ordem);
    }
    }
 
     

