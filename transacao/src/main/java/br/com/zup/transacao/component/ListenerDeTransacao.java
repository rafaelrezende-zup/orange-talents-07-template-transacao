package br.com.zup.transacao.component;

import br.com.zup.transacao.dto.EventoDeTransacao;
import br.com.zup.transacao.dto.response.EstabelecimentoResponse;
import br.com.zup.transacao.model.Cartao;
import br.com.zup.transacao.model.Estabelecimento;
import br.com.zup.transacao.model.Transacao;
import br.com.zup.transacao.repository.CartaoRepository;
import br.com.zup.transacao.repository.EstabelecimentoRepository;
import br.com.zup.transacao.repository.TransacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ListenerDeTransacao {

    Logger logger = LoggerFactory.getLogger(ListenerDeTransacao.class);

    private TransacaoRepository transacaoRepository;
    private CartaoRepository cartaoRepository;
    private TransactionExecutor executor;
    private EstabelecimentoRepository estabelecimentoRepository;

    public ListenerDeTransacao(TransacaoRepository transacaoRepository, CartaoRepository cartaoRepository, TransactionExecutor executor, EstabelecimentoRepository estabelecimentoRepository) {
        this.transacaoRepository = transacaoRepository;
        this.cartaoRepository = cartaoRepository;
        this.executor = executor;
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    @KafkaListener(topics = "transacoes", containerFactory = "kafkaListenerContainerFactory")
    public void ouvir(@Payload EventoDeTransacao eventoDeTransacao) {
        executor.inTransaction(() -> {
            Cartao cartao = cartaoRepository.getById(eventoDeTransacao.getCartao().getId());
            Estabelecimento estabelecimento = recuperaEstabelecimento(eventoDeTransacao.getEstabelecimento());
            Transacao transacao = eventoDeTransacao.toModel(cartao, estabelecimento);
            transacaoRepository.save(transacao);
        });
    }

    private Estabelecimento recuperaEstabelecimento(EstabelecimentoResponse response) {
        Optional<Estabelecimento> possivelEstabelecimento = estabelecimentoRepository.findByNome(response.getNome());
        if (possivelEstabelecimento.isEmpty()) {
            Estabelecimento estabelecimento = new Estabelecimento(response);
            executor.inTransaction(() -> {
                estabelecimentoRepository.save(estabelecimento);
            });
            return estabelecimento;
        }
        return possivelEstabelecimento.get();
    }

}
