package Entites.Deck;

import Entites.Card.*;
import java.util.ArrayList;
import java.util.Collections;



public class ClassicDeck extends Deck {

    /**
     * Inicializa o baralho com a seguinte composição:
     * - Naipes: Copas (Hearts), Ouros (Diamonds), Paus (Clubs) e Espadas (Spades).
     * - Valores: Numéricos (0 a 9) e Figuras (K, Q, J, A) para cada naipe.
     * - Especiais: 2 BlackJokers (Wild) e 2 RedJokers (Wild Draw Four).
     * O baralho é limpo antes da inicialização e embaralhado ao final.
     */

    public void initialize ( ){
        cards.clear();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        for (String suit : suits) {
            for (int i = 0; i <= 9; i++) {
                cards.add(new Card ( suit, String.valueOf(i)));
            }
            cards.add(new SkipCard ( suit, "J"));
            cards.add(new ReverseCard ( suit, "Q"));
            cards.add(new DrawCard ( suit, "K", 2));
            cards.add(new Card ( suit, "A"));
        }

        for (int i = 0; i < 2; i++) {
            cards.add(new Card ("BlackJoker", "Wild"));
            cards.add(new DrawCard("RedJoker", "Wild Draw Four", 4));
        }
        shuffle();
    }

}
