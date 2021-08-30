package br.com.zup.transacao.repository;

import br.com.zup.transacao.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
