package Entites;

import java.util.ArrayList;
import Entites.Card.*;

/**
 * Representa a pilha de descarte do jogo.
 * Armazena as cartas que já foram jogadas.
 */

public class Discard {
    // Lista que armazena as cartas descartadas na ordem em que foram jogadas.
    private ArrayList<Card> cards = new ArrayList <> ();


    // Adiciona uma carta ao topo da pilha de descarte.
    public void discard( Card card ) {
        cards.add (card);
    }

    // Retorna todas as cartas presentes na pilha de descarte.
    public ArrayList<Card> getCards() {
        return cards;
    }

    // Esvazia completamente a pilha de descarte.
    public void clearDisposal (){
        cards.clear();
    }
}
