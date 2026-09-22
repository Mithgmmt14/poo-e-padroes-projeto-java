package Entites.Player;

import Entites.Card.*;
import java.util.ArrayList;

/**
 * Representa um jogador no jogo.
 * Gerencia o nome do jogador e a suas cartas.
 */

public class Player {
    private final String name;
    private final ArrayList<Card> personalCards = new ArrayList<>();

    public Player (String name) {
        this.name = name;
    }

    // Adiciona uma nova carta à mão do jogador;
    public void BuyCard(Card card){
        personalCards.add(card);
    }

    // Retorna a quantidade de cartas que o jogador possui atualmente.
    public int personalCardsSize(){
        return personalCards.size();
    }

    // Obtém uma carta específica da mão do jogador baseada no índice.
    public Card personalCards(int index){
        return personalCards.get(index);
    }

    // Remove uma carta da mão do jogador após ela ser jogada.
    public void removeCard(int index){
        personalCards.remove(index);
    }

    public String getName(){
        return this.name;
    }

    // Exibe no console as cartas do jogador.
    public void printHand() {
        System.out.println("Player : " + ( this.name ) + " \n -> hand:");
        for (int i = 0; i < personalCards.size(); i++) {
            System.out.println ((i + 1) + ": " + personalCards.get(i) );
        }
    }
}
