package Entites.Deck;

import java.util.ArrayList;
import Entites.Card.Card;

/**
 * Representa a abstração de um baralho de cartas, definindo seu comportamento para qualquer tipo de baralho;
 */

public abstract class Deck {
    protected final ArrayList<Card> cards = new ArrayList<>();

    // Embaralha as cartas atualmente presentes no deck.
    public void shuffle() {
        java.util.Collections.shuffle(cards);
    }

    // Inicializa o baralho com sua composição padrão.
    public abstract void initialize();

    // Renova o baralho utilizando uma lista de cartas ( no jogo, é utilizado para renovar o baralho principal utilizando - se do baralho de descarte );
    public void renewDeck(ArrayList<Card> newCards) {
        this.cards.addAll(newCards);
        shuffle();
    }

    // Retorna a quantidade atual de cartas no baralho.
    public int deckSize() { return cards.size(); }

    // Remove e retorna uma carta do topo do baralho.
    public Card removeCard() {
        return cards.isEmpty() ? null : cards.removeLast();
    }
}
