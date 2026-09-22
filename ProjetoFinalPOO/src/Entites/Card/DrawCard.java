package Entites.Card;

import UserInterface.Game;

// Classe filha que representará uma carta de +2 ou +4, tendo os campos principais mais um campo de quantidade;
public class DrawCard extends Card {
    private final int amount;

    public DrawCard(String value, String attribute, int amount) {
        super(value, attribute);
        this.amount = amount;
    }

    @Override
    public void applyEffect(Game game) {
        game.draws(amount);
        System.out.println("Next Player draws" + amount + "cards and was skipped!");
        game.moveNextPlayer(2);
    }

}
