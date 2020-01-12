package Model;


import Utility.*;

/**
 * Game model
 *
 * @author ghyasi
 * @version 0.0.0
 */
public class Game
{
    // Current player
    private int currentPlayer;
    
    // Piece of game board
    private Piece[] pieces;
    
    // Count of player1's turn
    private int numPlayer1Round;
    
    // Count of player2's turn
    private int numPlayer2Round;
    
    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        initModel();
    }
    
    /**
     * Initialize the game model
     *
     */
    public void initModel()
    {
        currentPlayer = PlayerState.PLAYER1;
        
        numPlayer1Round = 0;
        numPlayer2Round = 0;
        
        pieces = new Piece[Constant.BOARD_NUM_COLS * Constant.BOARD_NUM_ROWS];
        for (int i = 0; i < Constant.BOARD_NUM_COLS; i++) {
            for (int j = 0; j < Constant.BOARD_NUM_ROWS; j++) {
                pieces[Common.globalIndex(i, j)] = new Piece(i, j);
            }
        }
    }
    
    /**
     * Get the piece on column i, row j
     *
     * @param i Column
     * @param j Row
     * @return Piece on column i, row j
     */
    public Piece getPiece(int i, int j)
    {
        return pieces[Common.globalIndex(i, j)];
    }
    
    /**
     * Get the piece on global index
     *
     * @param globalIndex global index
     * @return Piece on global index
     */
    public Piece getPiece(int globalIndex)
    {
        return pieces[globalIndex];
    }
    
    /**
     * Get the current player
     *
     * @return Current player
     */
    public int getCurrentPlayer()
    {
        return currentPlayer;
    }
    
    /**
     * Set the current player
     *
     * @param currentPlayer current player
     */
    public void setCurrentPlayer(int currentPlayer)
    {
        this.currentPlayer = currentPlayer;
    }
    
    /**
     * Get the count of player1's turn
     *
     * @return Count of player1's turn
     */
    public int getNumPlayer1Round() {
        return numPlayer1Round;
    }
    
    /**
     * Set the count of player1's turn
     *
     * @param Count of player1's turn
     */
    public void setNumPlayer1Round(int numPlayer1Round) {
        this.numPlayer1Round = numPlayer1Round;
    }
    
    /**
     * Get the count of player2's turn
     *
     * @return Count of player2's turn
     */
    public int getNumPlayer2Round() {
        return numPlayer2Round;
    }
    
    /**
     * Set the count of player2's turn
     *
     * @param Count of player2's turn
     */
    public void setNumPlayer2Round(int numPlayer2Round) {
        this.numPlayer2Round = numPlayer2Round;
    }

    /**
     * Toggle current player
     *
     */
    public void toggleTurn() {
        if (currentPlayer == PlayerState.PLAYER1) {
            numPlayer1Round++;
        }
        else if (currentPlayer == PlayerState.PLAYER2) {
            numPlayer2Round++;
        }
        
        for (int i = 0; i < Constant.BOARD_NUM_COLS; i++) {
            for (int j = 0; j < Constant.BOARD_NUM_ROWS; j++) {
                Piece piece = pieces[Common.globalIndex(i, j)];
                piece.updateRole();
            }
        }
        
        if (currentPlayer == PlayerState.PLAYER1) {
            currentPlayer = PlayerState.PLAYER2;
        }
        else if (currentPlayer == PlayerState.PLAYER2) {
            currentPlayer = PlayerState.PLAYER1;
        }
    }
}
