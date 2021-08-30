package br.com.zup.transacao.component;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class TransactionExecutor {

    @Transactional
    public void inTransaction(Runnable runnable) {
        runnable.run();
    }

}
