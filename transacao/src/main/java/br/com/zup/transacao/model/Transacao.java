package br.com.zup.transacao.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String codigoApi;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal valor;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Estabelecimento estabelecimento;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Cartao cartao;

    @NotNull
    private LocalDateTime efetivadaEm;

    @Deprecated
    public Transacao() {
    }

    public Transacao(String id, Cartao cartao, LocalDateTime efetivadaEm, Estabelecimento estabelecimento, BigDecimal valor) {
        this.codigoApi = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    public Long getId() {
        return id;
    }

    public String getCodigoApi() {
        return codigoApi;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(codigoApi, transacao.codigoApi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoApi);
    }
}
