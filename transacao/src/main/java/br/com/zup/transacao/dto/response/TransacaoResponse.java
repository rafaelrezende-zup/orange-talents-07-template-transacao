package br.com.zup.transacao.dto.response;

import br.com.zup.transacao.model.Transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TransacaoResponse {

    private Long id;
    private BigDecimal valor;
    private Long idEstabelecimento;
    private Long idCartao;
    private LocalDateTime efetivadaEm;

    public TransacaoResponse(Transacao i) {
        this.id = i.getId();
        this.valor = i.getValor();
        this.idEstabelecimento = i.getEstabelecimento().getId();
        this.idCartao = i .getCartao().getId();
        this.efetivadaEm = i.getEfetivadaEm();
    }

    public static Object toDTO(List<Transacao> transacaoList) {
        return transacaoList.stream().map(TransacaoResponse::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Long getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public Long getIdCartao() {
        return idCartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }
}
