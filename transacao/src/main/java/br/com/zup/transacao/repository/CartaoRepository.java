package br.com.zup.transacao.repository;

import br.com.zup.transacao.model.Cartao;
import br.com.zup.transacao.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    @Query(value = "SELECT t " +
            " FROM Cartao c " +
            " JOIN c.transacao t " +
            " WHERE c.id = :idCartao " +
            " ORDER BY t.efetivadaEm " +
            " LIMIT 10;", nativeQuery = true)
    List<Transacao> comprasRecentes(Long idCartao);

}
