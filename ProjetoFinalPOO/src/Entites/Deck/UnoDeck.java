package Entites.Deck;

import Entites.Card.*;

public class UnoDeck extends Deck {

    /**
     * Inicializa o baralho com a composição padrão de UNO:
     * - Cores: Vermelho, Azul, Verde e Amarelo;
     * - Números: 0 a 9 para cada cor;
     * - Ações: Bloqueio (Skip), Compra Duas (Draw Two) e Inverter (Reverse) para cada cor;
     * - Especiais: 4 Cartas Wild (Coringa) e 4 Cartas Wild Draw Four (Coringa Comprar Quatro).
     * O baralho é embaralhado após a criação.
     */

    public void initialize () {
        cards.clear();
        String[] colors = {"Red", "Blue", "Green", "Yellow"};

        for (String color : colors) {
            for (int i = 0; i <= 9; i++) {
                cards.add(new Card (color, String.valueOf(i)));
            }
            cards.add(new SkipCard (color, "Skip"));
            cards.add(new DrawCard (color, "Draw Two", 2));
            cards.add(new ReverseCard (color, "Reverse"));
        }

        for (int i = 0; i < 4; i++) {
            cards.add(new Card ("Black", "Wild"));
            cards.add(new DrawCard ("Black", "Wild Draw Four", 4));
        }
        shuffle();
    }
}
