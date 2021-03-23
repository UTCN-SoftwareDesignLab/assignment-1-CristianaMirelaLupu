package model.builder;

import model.Account;
import java.util.Date;

public class AccountBuilder {
    private final Account account;

    public AccountBuilder()
    {
       account = new Account();
    }

    public AccountBuilder setId(Long id){
        account.setId(id);
        return this;
    }

    public AccountBuilder setType(String type){
        account.setType(type);
        return this;
    }

    public AccountBuilder setAmount(float amount){
        account.setAmount(amount);
        return this;
    }

    public AccountBuilder setCreationDate (Date creationDate){
        account.setCreationDate((java.sql.Date) creationDate);
        return this;
    }

    public AccountBuilder setClient (Long id_client) {
        account.setClient(id_client);
        return this;
    }

    public Account build ()
    {
        return account;
    }

}

