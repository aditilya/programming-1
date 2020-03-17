//EDIT: Comment for Lab 06 - Software Engineering

/*Final project submission*/

import java.text.DecimalFormat;
import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        double menuSelection;
        double gamesWonByPlayer = 0;
        int gamesWonByDealer = 0;
        int gamesTied = 0;
        P1Random prand = new P1Random();
        Scanner sc = new Scanner(System.in);
        DecimalFormat df1 = new DecimalFormat("#0.0");
        DecimalFormat df2 = new DecimalFormat("#");
        int gameNumber = 1;
        System.out.println("START GAME #" + gameNumber + "\n");
        int handDealer = 0;
        int cardDealer = 0;
        int handPlayer = 0;
        int skip = 2;
        int skip2 = 2;
        int cardPlayer = dealCardPlayer(prand);
        if (cardPlayer >= 11) {
            handPlayer += 10;
        } else {
            handPlayer += cardPlayer;
        }
        displayCard(cardPlayer);
        displayHand(handPlayer);
        displayMenu();

        System.out.print("Choose an option: ");
        menuSelection = sc.nextDouble();
        System.out.print("\n");

        while((menuSelection != 1) && (menuSelection != 2) && (menuSelection != 3) && (menuSelection != 4)) {
            System.out.println("Invalid input!\nPlease enter an integer value between 1 and 4.");
            displayMenu();
            System.out.print("Choose an option: ");
            menuSelection = sc.nextDouble();
            System.out.print("\n");
        } //while - error in menu selection

        //While loop to loop through each iteration of the game
        while (menuSelection != 4) {
            if (gameNumber > 1) {
                System.out.println("START GAME # " + gameNumber + "\n");
            }

            if ((menuSelection == 2) && (skip == 1)) {
                cardPlayer = dealCardPlayer(prand);
                if (cardPlayer >= 11) {
                    handPlayer += 10;
                } else {
                    handPlayer += cardPlayer;
                }
                displayCard(cardPlayer);
                //System.out.println("hello from biggest while");
                displayHand(handPlayer);

                displayMenu();
                System.out.print("Choose an option: ");
                menuSelection = sc.nextDouble();
                System.out.print("\n");
            }


            while((menuSelection != 1) && (menuSelection != 2) && (menuSelection != 3) && (menuSelection != 4)) {
                System.out.println("Invalid input!\nPlease enter an integer value between 1 and 4.");
                displayMenu();
                System.out.print("Choose an option: ");
                menuSelection = sc.nextDouble();
                System.out.print("\n");
            } //while - error in menu selection

            //While loop to loop through each possible menu selection
            while((menuSelection >= 1) && (menuSelection <= 3)) {
                skip = 2;
                int menu = (int)menuSelection;

                //Switch statement to accommodate the implementation for each possible test case
                switch(menu) {
                    case 1:
                        cardPlayer = dealCardPlayer(prand);
                        displayCard(cardPlayer);
                        if (cardPlayer >= 11) {
                            handPlayer += 10;
                        } else {
                            handPlayer += cardPlayer;
                        }
                        //some debugging code:
                        //System.out.println("hello from case 1");
                        displayHand(handPlayer);

                        if (handPlayer == 21) {
                            gamesWonByPlayer++;
                            skip = 1;
                            System.out.println("BLACKJACK! You win!\n");
                            //break;
                        } else if (handPlayer > 21) {
                            gamesWonByDealer++;
                            skip = 1;
                            System.out.println("You exceeded 21! You lose.\n");
                            //break;
                        }
                        break;
                    case 2:
                        cardDealer = dealCardDealer(prand);
                        handDealer = cardDealer;
                        System.out.println("Dealer's hand: " + handDealer);
                        System.out.println("Your hand is: " + handPlayer + "\n");
                        if (handPlayer == handDealer) {
                            gamesTied++;
                            System.out.println("It's a tie! No one wins!\n");
                        } else if (handPlayer > handDealer) {
                            gamesWonByPlayer++;
                            System.out.println("You win!\n");
                        } else if (handDealer > 21) {
                            gamesWonByPlayer++;
                            System.out.println("You win!\n");
                        } else {
                            gamesWonByDealer++;
                            System.out.println("Dealer wins!\n");
                        }

                        skip = 1;
                        break;
                    case 3:
                        double percent = (gamesWonByPlayer/(gameNumber - 1)) * 100;
                        printStats(df1,df2,gamesWonByPlayer,gamesWonByDealer,gamesTied,gameNumber,percent);
                        break;
                }//switch(menuSelection)

                if (skip == 1) {
                    break;
                }
                displayMenu();
                System.out.print("Choose an option: ");
                menuSelection = sc.nextDouble();
                System.out.print("\n");
               // System.out.println("i think its here");

                while((menuSelection != 1) && (menuSelection != 2) && (menuSelection != 3) && (menuSelection != 4)) {
                    System.out.println("Invalid input!\nPlease enter an integer value between 1 and 4.");
                    //System.out.println("hello from error while");
                    displayMenu();
                    System.out.print("Choose an option: ");
                    menuSelection = sc.nextDouble();
                    System.out.print("\n");
                    menu = (int) menuSelection;
                } //while - error in menu selection
            }//while - each menu selection
            //System.out.println(gameNumber + "    1");
            gameNumber++;
            handPlayer = 0;
            handDealer = 0;
            cardDealer = 0;
            cardPlayer = 0;
           // System.out.println(gameNumber + "   2");
            if (menuSelection == 4) {
                break;
            }


        }//while - each game
    }//main

    //Method to deal the player's cards
    public static int dealCardPlayer(P1Random gen) {
        return gen.nextInt(13) + 1;
    }//dealCardPlayer

    //Method to deal the dealer's cards
    public static int dealCardDealer(P1Random gen) {
        return gen.nextInt(11) + 16;
    }//dealCardDealer

    //Method to print the menu
    public static void displayMenu() {
        System.out.println("\n1. Get another card\n2. Hold hand\n3. Print statistics\n4. Exit\n");
    }//displayMenu

    //Method to print the current dealt card
    public static void displayCard(int currentCard) {
        System.out.print("Your card is a ");
        if ((currentCard < 11) && (currentCard != 1)) {
            System.out.println(+ currentCard + "!");
        } else {
            String faceCard = "";
            switch(currentCard) {
                case 1:
                    faceCard = "ACE";
                    break;
                case 11:
                    faceCard = "JACK";
                    break;
                case 12:
                    faceCard = "QUEEN";
                    break;
                case 13:
                    faceCard = "KING";
                    break;
                default:
                    break;
            }//switch(currentCard)
            System.out.println(faceCard + "!");
        }//if else
    }//displayCard

    //Method to print the current dealt hand
    public static void displayHand(int currentHand) {
        System.out.println("Your hand is: " + currentHand);
    }//displayHand

    //Method to print the current player's game statistics
    public static void printStats(DecimalFormat x, DecimalFormat y, double playerWins, int dealerWins, int tieGames, int totalGames, double percentage) {
        System.out.println("Number of Player wins: " + y.format((int)playerWins));
        System.out.println("Number of Dealer wins: " + dealerWins);
        System.out.println("Number of tie games: " + tieGames);
        System.out.println("Total # of games played is: " + (totalGames-1));
        System.out.println("Percentage of Player wins: " + x.format(percentage) + "%");
    }//printStats
}
