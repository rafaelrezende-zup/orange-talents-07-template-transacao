package br.com.zup.transacao.controller;

import br.com.zup.transacao.client.TransacaoClient;
import br.com.zup.transacao.component.TransactionExecutor;
import br.com.zup.transacao.dto.request.CartaoRequest;
import br.com.zup.transacao.dto.response.CartaoResponse;
import br.com.zup.transacao.dto.response.TransacaoResponse;
import br.com.zup.transacao.model.Cartao;
import br.com.zup.transacao.model.Transacao;
import br.com.zup.transacao.repository.CartaoRepository;
import br.com.zup.transacao.repository.TransacaoRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    Logger logger = LoggerFactory.getLogger(CartaoController.class);

    private final CartaoRepository cartaoRepository;
    private final TransacaoRepository transacaoRepository;
    private final TransactionExecutor executor;
    private TransacaoClient client;

    public CartaoController(CartaoRepository cartaoRepository,
                            TransacaoRepository transacaoRepository,
                            TransactionExecutor executor,
                            TransacaoClient client) {
        this.cartaoRepository = cartaoRepository;
        this.transacaoRepository = transacaoRepository;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> recuperaTransacoes(@PathVariable Long id) {
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if (cartao.isEmpty()) return ResponseEntity.notFound().build();
        List<Transacao> transacaoList = transacaoRepository.comprasRecentes(id);
        return ResponseEntity.ok(TransacaoResponse.toDTO(transacaoList));
    }

}
