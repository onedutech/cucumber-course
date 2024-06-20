package edu.one.dojo;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private String from;
    private String to;
    private List<String> contents = new ArrayList<String>();

    public void declareOwner(String romeo) {
        this.from = romeo;
    }

    public void declareTarget(String juliette) {
        this.to = juliette;
    }

    public List<String> getCocktails() {
        return contents;
    }

    private String message;

    //...
    public void withMessage(String something) {
        this.message = something;
    }

    public String getTicketMessage() {
        return "From " + from + " to " + to + ": " + message;
    }
}