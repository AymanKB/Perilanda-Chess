import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import Model.*;
import View.*;
import Controller.*;

/**
 * Main class that starts the application
 *
 * @author Ayman
 * @version 0.0.0
 */
public class Main extends Application
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Main
     */
    public Main()
    {
        // initialise instance variables
        x = 0;
    }

    
    /**
     * Method start
     *
     * @param stage A parameter
     */
    @Override
    public void start(Stage stage)
    {
        // Initialize Game
        Game game = new Game();
        // Initialize Game view
        GameView gameView = new GameView();
        // Initialize Game Controller;
        new GameController(game, gameView, stage);
        
        
        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(gameView,560,560);
        stage.setTitle("JavaFX Example");
        stage.setScene(scene);

        // Show the Stage (window)
        stage.show();
    } 
}
