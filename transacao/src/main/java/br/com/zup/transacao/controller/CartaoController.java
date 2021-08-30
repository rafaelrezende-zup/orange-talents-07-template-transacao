package br.com.zup.transacao.controller;

import br.com.zup.transacao.client.TransacaoClient;
import br.com.zup.transacao.component.TransactionExecutor;
import br.com.zup.transacao.dto.request.CartaoRequest;
import br.com.zup.transacao.dto.response.CartaoResponse;
import br.com.zup.transacao.model.Cartao;
import br.com.zup.transacao.repository.CartaoRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    Logger logger = LoggerFactory.getLogger(CartaoController.class);

    private final CartaoRepository cartaoRepository;
    private final TransactionExecutor executor;
    private TransacaoClient client;

    public CartaoController(CartaoRepository cartaoRepository,
                            TransactionExecutor executor,
                            TransacaoClient client) {
        this.cartaoRepository = cartaoRepository;
        this.executor = executor;
        this.client = client;
    }

    @PostMapping
    public ResponseEntity<?> cria(@RequestBody CartaoRequest request) {
        Cartao cartao = request.toModel();
        executor.inTransaction(() -> {
            cartaoRepository.save(cartao);
        });

        try {
            CartaoResponse response = client.geraTransacao(new CartaoResponse(cartao.getId(), cartao.getEmail()));
        } catch (FeignException e) {
            logger.debug("Erro ao gerar transações. " + e);
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

}
