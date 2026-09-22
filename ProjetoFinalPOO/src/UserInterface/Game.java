package UserInterface;

import Entites.*;
import Entites.Card.*;
import Entites.Deck.*;
import Entites.Player.*;

import java.util.ArrayList;

/**
   Organiza a lógica principal do jogo, turnos, baralho e regras.
 */

public class Game {
    private final InputCorrection input = new InputCorrection();
    ArrayList<Player> players = new ArrayList<>();
    private final Discard disposal = new Discard();
    private int indexofCurrentPlayer = 0;
    private Way direction = Way.CLOCKWISE;
    private Deck gameDeck;

    /**
     * Inicia o Jogo, seus jogadores, o baralho de preferência e o início da partida..
     */

    public void execute (){
        System.out.println("Welcome to the Game !!! ");
        System.out.println("What type of deck do you want to play? ");
        int Number = input.getInt("[1] Play Classic Deck \n[2] Play Uno Deck\n", 1 ,2 );

        gameDeck = (Number == 1) ? new ClassicDeck() : new UnoDeck();
        gameDeck.initialize();

        int num = input.getInt("How many players ( 2 - 10 ): ", 2 , 10);
        this.addPlayers( num );

        Card topCard = gameDeck.removeCard();
        disposal.discard(topCard);
        this.playing ( topCard );

    }

    /**
     * Ciclo principal de jogadas. Continua até que um jogador vença.
     */

    private void playing ( Card topCard ) {
        while (!gameOver()) {
            checkDeck();

            Player currentPlayer = players.get( this.indexofCurrentPlayer );
            System.out.println("=================================================");
            System.out.println("  Top card : " + topCard );
            System.out.println("=================================================\n");

            currentPlayer.printHand();

            System.out.println();

            int option = input.getInt("[0] buy a card; \n[1] play \n", 0 , 1 );

            if (option == 0) {
                Card card1 = gameDeck.removeCard();
                currentPlayer.BuyCard( card1 );
                System.out.println("Card bought : " + card1);
                moveNextPlayer (1);
            }
            if (option == 1) {
                int cardNumber = input.getInt("What card do you wanna play ( Select Card Index ):", 1 , currentPlayer.personalCardsSize() ) - 1;

                // Validação de jogadas, se a carta pode ser jogada ou não, com base nas regras do jogo.
                while ( !currentPlayer.personalCards( cardNumber ).canPlay( topCard )) {
                    cardNumber = input.getInt("You can't play that, select a new card:", 1 , currentPlayer.personalCardsSize() ) - 1;
                }

                Card playedCard = currentPlayer.personalCards(cardNumber);
                currentPlayer.removeCard(cardNumber);

                if ( playedCard.isJoker() ) {
                    wildColor( playedCard );
                }

                disposal.discard( playedCard );
                topCard = playedCard;

                playedCard.applyEffect(this);
            }
            System.out.println("\n");
        }
        System.out.println("\n ======== Game Over ======== \n");
        for (Player player : players) {
            if (player.personalCardsSize() == 0) {
                System.out.println("Player " + ( player.getName() ) + " has won!");
            }

        }
    }

    // Verifica se o baralho possui poucas cartas restantes.
    private void checkDeck (){
        if ( gameDeck.deckSize() <= 5 ){ // Caso o baralho estiver com 5 cartas, recicla o descarte.
            System.out.println("Reshuffling discard pile into deck...");
            gameDeck.renewDeck( disposal.getCards() );
            disposal.clearDisposal();
        }
    }

    // Verifica se um jogador não possui mais cartas.
    private boolean gameOver( ){
        for (Player player : players) {
            if (player.personalCardsSize() == 0) {
                return true;
            }
        }
        return false;
    }

    // Adiciona jogadores à lista de jogadores
    private void addPlayers ( int num ){
        System.out.println("Please enter the name of the players. ");
        for (int i = 0; i < num; i++) {
            String name = input.getString("Name of player:");
            this.players.add(new Player ( name ) );
            for (int j = 0; j < 7; j++) {
                players.get(i).BuyCard( gameDeck.removeCard());
            }
        }
        System.out.println("\n");
    }

    //Permite ao jogador escolher a nova cor/naipe de um Coringa.
    private void wildColor(Card card ) {
        String newValue;
        if ( card.getValue().equals ( "Black" )){
            System.out.println("What color did you wanna ( Red, Yellow , Blue, Green ):");
        }else{
            System.out.println("What suit did you wanna ( Hearts , Diamonds , Clubs , Spades ):");
        }
        newValue = input.getString("");
        card.setValue ( newValue );
    }

    // Metodo que altera a direção do jogo.
    public void reverse (){
        if (this.direction == Way.CLOCKWISE) {
            this.direction = Way.COUNTERCLOCKWISE;
        } else {
            this.direction = Way.CLOCKWISE;
        }
        System.out.println("Direction changed to " + this.direction);
        if (players.size() == 2) {
            moveNextPlayer ( 2 );
        }
    }
    // Quantas cartas o próximo jogador terá de comprar;
    public void draws(int number ){
        int nextIdx = getNextIndex(1);
        for ( int i = 0; i < number; i++ ) {
            players.get(nextIdx).BuyCard(gameDeck.removeCard());
        }
    }

    // Passa a vez para o próximo jogar, utilizando uma função auxiliar "getNextIndex"
    public void moveNextPlayer(int steps) {
        indexofCurrentPlayer = getNextIndex(steps);
    }

    // O próximo índice é calculado com base na direção que o jogo está, utilizando aritmética modular.
    private int getNextIndex(int steps) {
        int size = players.size();
        if (direction == Way.CLOCKWISE) {
            return (indexofCurrentPlayer + steps) % size;
        } else {
            return (indexofCurrentPlayer - steps + size) % size;
        }
    }

}