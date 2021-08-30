package br.com.zup.transacao.repository;

import br.com.zup.transacao.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    @Query(value = "SELECT * " +
            " FROM Transacao as t " +
            " WHERE t.cartao_id = :idCartao " +
            " ORDER BY t.efetivada_em desc " +
            " LIMIT 10;", nativeQuery = true)
    List<Transacao> comprasRecentes(Long idCartao);

}
