package edu.one.dojo.steps;

import edu.one.dojo.Order;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Quand;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class CocktailSteps {

    private Order order;

    @Etantdonnéque("^(.*) who wants to buy a drink")
    public void romeo_who_wants_to_buy_a_drink(String romeo) {
        order = new Order();
        order.declareOwner(romeo);
    }

    @Quand("^an order is declared for (.*)$")
    public void an_order_is_declared_for_Juliette(String juliette) {
        order.declareTarget(juliette);
    }

    @Alors("^there is (\\d+) cocktails in the order$")
    public void there_is_nb_cocktail_in_the_order(int number) {
        // Write code here that turns the phrase above into concrete actions
        List<String> cocktails = order.getCocktails();
        Assertions.assertEquals(number, cocktails.size());
    }


    @Quand("a message saying {string} is added")
    public void a_message_saying_is_added(String string) {
        // Write code here that turns the phrase above into concrete actions
        order.withMessage(string);
    }

    @Alors("the ticket must say {string}")
    public void the_ticket_must_say(String string) {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertEquals(string, order.getTicketMessage());
    }

}
