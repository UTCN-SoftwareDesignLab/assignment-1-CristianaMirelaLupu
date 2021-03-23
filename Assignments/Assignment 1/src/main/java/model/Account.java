package model;

import java.sql.Date;

public class Account {

    private Long id;
    private String type;
    private float amount;
    private Date creationDate;
    private Long id_client;

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public float getAmount() {
        return amount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Long getClient() {
        return id_client;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setClient(Long id_client){
        this.id_client = id_client;
    }

    @Override
    public String toString(){
        return Long.toString(this.id) + " - " + this.type + " - " + Float.toString(this.amount);
    }

}

