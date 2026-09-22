package Entites.Card;

import UserInterface.Game;

public class SkipCard extends Card {
    public SkipCard(String value, String attribute) {
        super(value, attribute);
    }

    @Override
    public void applyEffect(Game game) {
        System.out.println("Next player was skipped!");
        game.moveNextPlayer(2);
    }
}
