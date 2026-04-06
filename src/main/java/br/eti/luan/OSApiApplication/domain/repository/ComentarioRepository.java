/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.eti.luan.OSApiApplication.domain.repository;

import br.eti.luan.OSApiApplication.domain.model.Comentario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author digma
 */
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
    List<Comentario> findByOrdemServicoId(Long ordemServicoId);
    List<Comentario> findByClienteId(Long clienteId);
}
