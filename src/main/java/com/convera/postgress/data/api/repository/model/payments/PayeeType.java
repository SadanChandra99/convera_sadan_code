package com.convera.postgress.data.api.repository.model.payments;

public enum PayeeType {

    INDIVIDUAL("Individual"),
    SOLE_PROPIETOR("Sole Proprietor"),
    BUSINESS("Business");




    public String type;

    public String getType() {
        return this.type;
    }

    PayeeType(String type) {
        this.type = type;
    }
}
