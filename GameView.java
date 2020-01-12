package View;


import javafx.scene.layout.BorderPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * GameView
 *
 * @author Ayman
 * @version 0.0.0
 */
public class GameView extends BorderPane
{
    // Board view
    private BoardView boardView;
    
    // New menu
    private MenuItem newMenuItem;
    // Load menu
    private MenuItem loadMenuItem;
    // Save menu
    private MenuItem saveMenuItem;
    
    /**
     * Constructor
     */
    public GameView()
    {
        // initialize view layout
        initView();
    }

    /**
     * Initialize view layout
     *
     */
    public void initView()
    {
        // Menu bar
        MenuBar menuBar = new MenuBar();
        setTop(menuBar);
        
        // Game menu
        Menu gameMenu = new Menu("Game");
        menuBar.getMenus().add(gameMenu);
        
        // New menu item
        newMenuItem = new MenuItem("New");
        gameMenu.getItems().add(newMenuItem);
        
        // Load menu item
        loadMenuItem = new MenuItem("Load");
        gameMenu.getItems().add(loadMenuItem);
        
        // Save menu item
        saveMenuItem = new MenuItem("Save");
        gameMenu.getItems().add(saveMenuItem);
        
        // Board view
        boardView = new BoardView();
        setCenter(boardView);
    }
    
    /**
     * Get the board view
     *
     * @return Board view
     */
    public BoardView getBoardView()
    {
        return boardView;
    }
    
    /**
     * Get New menu item
     *
     * @return New menu item
     */
    public MenuItem getNewMenuItem()
    {
        return newMenuItem;
    }
    
    /**
     * Get Load menu item
     *
     * @return Load menu item
     */
    public MenuItem getLoadMenuItem()
    {
        return loadMenuItem;
    }
    
    /**
     * Get Save menu item
     *
     * @return Save menu item
     */
    public MenuItem getSaveMenuItem()
    {
        return saveMenuItem;
    }
}
