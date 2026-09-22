package Entites.Card;

import UserInterface.Game;

/**
 * Representa uma carta genérica no jogo.
 * Serve como base para a lógica do baralho, descarte e mãos dos jogadores.
 */

public class Card {
    private String value;      // Cor ou naipe das cartas;
    private String attribute;  // Valores das cartas ( número ou ação ) ;

    public Card (String value, String attribute) {
        this.value = value;
        this.attribute = attribute;
    }

    // Cada tipo de carta decide o que ocorre no jogo.

    public void applyEffect( Game game ){
        game.moveNextPlayer(1);
    }

    // Verifica se esta carta pode ser jogada sobre a carta atual no topo do descarte.
    public boolean canPlay(Card top ) {
        // Coringas ou cartas pretas, sempre podem ser jogadas.
        if (this.isJoker()) return true;
        return this.attribute.equals(top.attribute) || this.value.equals(top.value); // Validação por cor e por valor/símbolo;

    }

    // Identifica se a carta possui propriedades de "Coringa" (Joker).
    public boolean isJoker( ){
        return this.value.equals("Black") || this.value.equals("BlackJoker") || this.value.equals("RedJoker");
    }

    // Getters e Setters
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getAttribute ( ){
        return this.attribute;
    }

    @Override
    public String toString() { return "[ Card : " + this.value + " - Value : " + this.attribute + "]"; }

}
