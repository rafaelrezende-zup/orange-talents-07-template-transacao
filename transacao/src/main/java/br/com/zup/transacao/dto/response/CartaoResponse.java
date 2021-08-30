package br.com.zup.transacao.dto.response;

import javax.validation.constraints.NotBlank;

public class CartaoResponse {

    @NotBlank
    private Long id;

    @NotBlank
    private String email;

    public CartaoResponse(@NotBlank Long id,
                          @NotBlank String email) {
        this.id = id;
        this.email = email;
    }

    @Deprecated
    public CartaoResponse() {
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
