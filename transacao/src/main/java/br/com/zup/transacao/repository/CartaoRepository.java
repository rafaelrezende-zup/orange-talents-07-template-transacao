package br.com.zup.transacao.repository;

import br.com.zup.transacao.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
