package View;


import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import Utility.*;

/**
 * BoardView
 *
 * @author Ayman
 * @version 0.0.0
 */
public class BoardView extends GridPane
{
    // Pieces in board
    private PieceView[] pieceViews;
    
    /**
     * Constructor
     */
    public BoardView()
    {
        // initialize view layout
        initView();
    }
    
    /**
     * Method initView - Initialize view layout
     *
     */
    public void initView()
    {
        pieceViews = new PieceView[Constant.BOARD_NUM_COLS * Constant.BOARD_NUM_ROWS];

        // Add row constraints
        for (int i = 0; i < Constant.BOARD_NUM_ROWS; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0 / Constant.BOARD_NUM_ROWS);
            rowConstraints.setVgrow(Priority.NEVER);
            getRowConstraints().add(rowConstraints);
        }
        
        // Add column constraints
        for (int i = 0; i < Constant.BOARD_NUM_COLS; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100.0 / Constant.BOARD_NUM_COLS);
            columnConstraints.setHgrow(Priority.NEVER);
            getColumnConstraints().add(columnConstraints);
        }
        
        // Add pieces to board
        for (int i = 0; i < Constant.BOARD_NUM_COLS; i++) {
            for (int j = 0; j < Constant.BOARD_NUM_ROWS; j++) {
                PieceView pieceView = new PieceView();
                GridPane.setHalignment(pieceView, HPos.CENTER);
                GridPane.setValignment(pieceView, VPos.CENTER);
                add(pieceView, i, j);
                pieceViews[Common.globalIndex(i, j)] = pieceView;
            }
        }
        
        setStyle("-fx-background-color: #ffffff;");
        setGridLinesVisible(true);
    }
    
    /**
     * Get piece view
     *
     * @param globalIndex global index
     * @return piece view
     */
    public PieceView getPieceView(int globalIndex)
    {
        return pieceViews[globalIndex];
    }
    
    /**
     * Get piece view
     *
     * @param col Column
     * @param row Row
     * @return piece view
     */
    public PieceView getPieceView(int col, int row)
    {
        return pieceViews[Common.globalIndex(col, row)];
    }
}
