package br.com.zup.transacao.dto;

import br.com.zup.transacao.dto.response.CartaoResponse;
import br.com.zup.transacao.dto.response.EstabelecimentoResponse;
import br.com.zup.transacao.model.Cartao;
import br.com.zup.transacao.model.Estabelecimento;
import br.com.zup.transacao.model.Transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EventoDeTransacao {

    private String id;
    private BigDecimal valor;
    private EstabelecimentoResponse estabelecimento;
    private CartaoResponse cartao;
    private LocalDateTime efetivadaEm;

    @Deprecated
    public EventoDeTransacao() {
    }

    public Transacao toModel(Cartao cartao, Estabelecimento estabelecimento) {
        return new Transacao(this.id, cartao, this.efetivadaEm, estabelecimento, this.valor);
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoResponse getEstabelecimento() {
        return estabelecimento;
    }

    public CartaoResponse getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }
}
