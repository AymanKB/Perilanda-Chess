package Controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;

import Model.*;
import Model.AdvancerInvRole;
import View.*;
import Utility.*;

/**
 * GameController
 *
 * @author  Ayman
 * @version 0.0.0
 */
public class GameController
{
    // Game model
    private Game model;
    // Game view
    private GameView view;
    // JavaFX stage
    private Stage stage;
    
    // Piece controllers
    private PieceController[] pieceControllers;
    // Scanner
    private Scanner scanner;
    
    /**
     * Constructor for objects of class GameController
     */
    public GameController()
    {
        model = null;
        view = null;
        stage = null;
    }

    /**
     * Constructor
     *
     * @param model Game model
     * @param view Game view
     * @param stage JavaFX stage
     */
    public GameController(Game model, GameView view, Stage stage)
    {
        this.model = model;
        this.view = view;
        this.stage = stage;
        
        initController();
        addMenuItemListener();
    }
    
    /**
     * Initialize the game controller
     *
     */
    public void initController()
    {
        // Create array to store every piece controller
        pieceControllers = new PieceController[Constant.BOARD_NUM_COLS * Constant.BOARD_NUM_ROWS];
        for (int col = 0; col < Constant.BOARD_NUM_COLS; col++) {
            for (int row = 0; row < Constant.BOARD_NUM_ROWS; row++) {
                // Get piece model
                Piece piece = model.getPiece(col, row);
                
                // Get piece view
                BoardView boardView = view.getBoardView();
                PieceView pieceView = boardView.getPieceView(col, row);
                
                // Create piece controller
                PieceController pieceController = new PieceController(piece, pieceView, this);
                pieceControllers[Common.globalIndex(col, row)] = pieceController;
                
                // Join model and view with observer pattern
                piece.addObserver(pieceView);
                piece.setRoleAndOwner(new NoRole(), PlayerState.NONE);
            }
        }
        
        // Place the pieces on the game board
        placeInitialPieces();
        // Enable pieces of current player
        toggleMovingPiece();
    }
    
    /**
     * Add event listener to menu item
     *
     */
    public void addMenuItemListener()
    {
        // File/New menu item event listener
        view.getNewMenuItem().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                // Initialize controller
                initController();
            }
        }); 
        
        // File/Save menu item event listener
        view.getSaveMenuItem().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                // Open FileSave dialog
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Game");
                fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("TEXT", "*.txt"));
                File file = fileChooser.showSaveDialog(stage);
                // Save game model to file
                saveGame(file);
            }
        }); 
        
        // File/Load menu item event listener
        view.getLoadMenuItem().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                // Open FileOpen dialog
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Load Game");
                fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("TEXT", "*.txt"));
                File file = fileChooser.showOpenDialog(stage);
                // Load game model from file
                loadGame(file);
            }
        }); 
    }
    
    /**
     * Occur when the drag-drop of piece is finished
     *
     */
    public void onDragCompleted()
    {
        // Check the chief piece of each player
        boolean chiefAliveOfPlayer1 = isChiefAlive(PlayerState.PLAYER1);
        boolean chiefAliveOfPlayer2 = isChiefAlive(PlayerState.PLAYER2);
        
        // If chief piece is dead, the counter-part player wins
        if (chiefAliveOfPlayer1 == false) {
            showWinnerDialog(PlayerState.PLAYER2);
        }
        else if (chiefAliveOfPlayer2 == false) {
            showWinnerDialog(PlayerState.PLAYER1);
        }
        
        // Toggle current player
        model.toggleTurn();
        // Toggle moving pieces
        toggleMovingPiece();
    }
    
    /**
     * Place pieces on initial stage
     *
     */
    private void placeInitialPieces()
    {
        // Initialize the game board
        model.getPiece(0, 0).setRoleAndOwner(new TercelRole(model), PlayerState.PLAYER1);
        model.getPiece(1, 0).setRoleAndOwner(new ExcelRole(model), PlayerState.PLAYER1);
        model.getPiece(2, 0).setRoleAndOwner(new TridentInvRole(model), PlayerState.PLAYER1);
        model.getPiece(3, 0).setRoleAndOwner(new ChiefRole(model), PlayerState.PLAYER1);
        model.getPiece(4, 0).setRoleAndOwner(new TridentInvRole(model), PlayerState.PLAYER1);
        model.getPiece(5, 0).setRoleAndOwner(new ExcelRole(model), PlayerState.PLAYER1);
        model.getPiece(6, 0).setRoleAndOwner(new TercelRole(model), PlayerState.PLAYER1);
        
        model.getPiece(0, 1).setRoleAndOwner(new AdvancerInvRole(model), PlayerState.PLAYER1);
        model.getPiece(1, 1).setRoleAndOwner(new AdvancerInvRole(model), PlayerState.PLAYER1);
        model.getPiece(2, 1).setRoleAndOwner(new AdvancerInvRole(model), PlayerState.PLAYER1);
        model.getPiece(3, 1).setRoleAndOwner(new AdvancerInvRole(model), PlayerState.PLAYER1);
        model.getPiece(4, 1).setRoleAndOwner(new AdvancerInvRole(model), PlayerState.PLAYER1);
        model.getPiece(5, 1).setRoleAndOwner(new AdvancerInvRole(model), PlayerState.PLAYER1);
        model.getPiece(6, 1).setRoleAndOwner(new AdvancerInvRole(model), PlayerState.PLAYER1);
        
        model.getPiece(0, 5).setRoleAndOwner(new AdvancerRole(model), PlayerState.PLAYER2);
        model.getPiece(1, 5).setRoleAndOwner(new AdvancerRole(model), PlayerState.PLAYER2);
        model.getPiece(2, 5).setRoleAndOwner(new AdvancerRole(model), PlayerState.PLAYER2);
        model.getPiece(3, 5).setRoleAndOwner(new AdvancerRole(model), PlayerState.PLAYER2);
        model.getPiece(4, 5).setRoleAndOwner(new AdvancerRole(model), PlayerState.PLAYER2);
        model.getPiece(5, 5).setRoleAndOwner(new AdvancerRole(model), PlayerState.PLAYER2);
        model.getPiece(6, 5).setRoleAndOwner(new AdvancerRole(model), PlayerState.PLAYER2);
        
        model.getPiece(0, 6).setRoleAndOwner(new TercelRole(model), PlayerState.PLAYER2);
        model.getPiece(1, 6).setRoleAndOwner(new ExcelRole(model), PlayerState.PLAYER2);
        model.getPiece(2, 6).setRoleAndOwner(new TridentRole(model), PlayerState.PLAYER2);
        model.getPiece(3, 6).setRoleAndOwner(new ChiefRole(model), PlayerState.PLAYER2);
        model.getPiece(4, 6).setRoleAndOwner(new TridentRole(model), PlayerState.PLAYER2);
        model.getPiece(5, 6).setRoleAndOwner(new ExcelRole(model), PlayerState.PLAYER2);
        model.getPiece(6, 6).setRoleAndOwner(new TercelRole(model), PlayerState.PLAYER2);
    }
    
    /**
     * Toggle moving pieces
     *
     */
    private void toggleMovingPiece()
    {
        for (int col = 0; col < Constant.BOARD_NUM_COLS; col++) {
            for (int row = 0; row < Constant.BOARD_NUM_ROWS; row++) {
                Piece piece = model.getPiece(col, row);
                
                if (piece.getOwner() == model.getCurrentPlayer()) {
                    piece.setDraggable(true);
                }
                else {
                    piece.setDraggable(false);
                }
            }
        }
    }
    
    /**
     * Check the chief piece is alive
     *
     * @param player Player
     * @return Alive state of sun piece
     */
    private boolean isChiefAlive(int player)
    {
        for (int col = 0; col < Constant.BOARD_NUM_COLS; col++) {
            for (int row = 0; row < Constant.BOARD_NUM_ROWS; row++) {
                Piece piece = model.getPiece(col, row);
                
                if (piece.getOwner() == player && piece.getRoleString() == "chief") {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Show winner dialog
     *
     * @param player Winner
     */
    private void showWinnerDialog(int player)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Congratulations!");
        alert.setContentText("Player" + String.valueOf(player) + " won!");
 
        alert.showAndWait();
    }
    
    /**
     * Save game model to file
     *
     * @param file File object
     */
    private void saveGame(File file)
    {
        try {
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            
            printWriter.print(model.getCurrentPlayer());
            printWriter.print(" ");
            
            printWriter.print(model.getNumPlayer1Round());
            printWriter.print(" ");
            
            printWriter.print(model.getNumPlayer2Round());
            printWriter.print(" ");
            
            for (int col = 0; col < Constant.BOARD_NUM_COLS; col++) {
                for (int row = 0; row < Constant.BOARD_NUM_ROWS; row++) {
                    Piece piece = model.getPiece(col, row);
                    
                    String roleString = piece.getRoleString();
                    if (roleString == null) {
                        printWriter.print("none");
                    }
                    else {
                        printWriter.print(roleString);
                    }
                    printWriter.print(" ");
                    
                    printWriter.print(piece.getOwner());
                    printWriter.print(" ");
                }
            }
            
            fileWriter.close();
        }
        catch (Exception e) {
        }
    }
    
    /**
     * Load game model from file
     *
     * @param file File object
     */
    private void loadGame(File file)
    {
        try {
            scanner = new Scanner(file);
            
            model.setCurrentPlayer(Integer.parseInt(scanner.next()));
            model.setNumPlayer1Round(Integer.parseInt(scanner.next()));
            model.setNumPlayer2Round(Integer.parseInt(scanner.next()));
            
            for (int col = 0; col < Constant.BOARD_NUM_COLS; col++) {
                for (int row = 0; row < Constant.BOARD_NUM_ROWS; row++) {
                    Piece piece = model.getPiece(col, row);
                
                    String roleString = scanner.next();
                    switch (roleString) {
                    case "none":
                        piece.setRole(new NoRole());
                        break;
                    
                    case "advancerinv":
                        piece.setRole(new AdvancerInvRole(model));
                        break;
                    
                    case "advancer":
                        piece.setRole(new AdvancerRole(model));
                        break;
                    
                    case "chief":
                        piece.setRole(new ChiefRole(model));
                        break;
                    
                    case "excel":
                        piece.setRole(new ExcelRole(model));
                        break;
                    
                    case "tercel":
                        piece.setRole(new TercelRole(model));
                        break;
                        
                    case "tridentinv":
                        piece.setRole(new TridentInvRole(model));
                        break;
                        
                    case "trident":
                        piece.setRole(new TridentRole(model));
                        break;
                    }
                
                    String ownerString = scanner.next();
                    piece.setOwner(Integer.parseInt(ownerString));
                }
            
                toggleMovingPiece();
            }
        }
        catch (Exception e) {
        }
    }
}