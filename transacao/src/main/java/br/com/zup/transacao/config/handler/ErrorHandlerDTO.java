package br.com.zup.transacao.config.handler;

public class ErrorHandlerDTO {

    private final String campo;
    private final String erro;

    public ErrorHandlerDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }

}
