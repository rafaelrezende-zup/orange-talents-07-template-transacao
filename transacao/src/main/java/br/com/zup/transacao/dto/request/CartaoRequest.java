package br.com.zup.transacao.dto.request;

import br.com.zup.transacao.model.Cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CartaoRequest {

    @NotBlank
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public Cartao toModel() {
        return new Cartao(this.email);
    }
}
