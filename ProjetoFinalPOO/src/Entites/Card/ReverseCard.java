package Entites.Card;

import UserInterface.Game;

public class ReverseCard extends Card {
    public ReverseCard(String value, String attribute) {
        super(value, attribute);
    }

    @Override
    public void applyEffect(Game game) {
        game.reverse();
    }
}
