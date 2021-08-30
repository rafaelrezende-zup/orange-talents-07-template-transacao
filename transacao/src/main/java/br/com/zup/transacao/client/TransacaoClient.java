package br.com.zup.transacao.client;

import br.com.zup.transacao.dto.response.CartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "transacao", url = "http://localhost:7777/api")
public interface TransacaoClient {

    @PostMapping(value = "/cartoes")
    CartaoResponse geraTransacao(@RequestBody @Valid CartaoResponse cartao);

    @DeleteMapping(value = "/cartoes/{id}")
    void paraTransacao(@PathVariable String id);

}
