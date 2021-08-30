package br.com.zup.transacao.dto.response;

import javax.validation.constraints.NotBlank;

public class EstabelecimentoResponse {

    @NotBlank
    private String nome;

    @NotBlank
    private String cidade;

    @NotBlank
    private String endereco;

    @Deprecated
    public EstabelecimentoResponse() {
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }
}
