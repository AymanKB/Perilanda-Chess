package Controller;


import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.image.Image;
import Model.*;
import View.*;
import Utility.*;

/**
 * Piece controller
 *
 * @author ayman
 * @version 0.0.0
 */
public class PieceController
{
    // Piece model
    private Piece model;
    // Piece view
    private PieceView view;
    // Super controller
    private GameController gameController;
    
    /**
     * Constructor
     */
    public PieceController(Piece model, PieceView view, GameController gameController)
    {
        this.model = model;
        this.view = view;
        this.gameController = gameController;
        
        addEventHandlers();
    }
    
    /**
     * Add event listener
     *
     */
    public void addEventHandlers() {
        view.setPickOnBounds(true);
        
        // Event listener occur when the drag get started
        view.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                // Get the image of piece
                Image image = view.getImage();
                if (image == null || !model.isDraggable()) {
                    event.consume();
                    return;
                }
                
                // Store the current piece to global variable
                Common.draggedPiece = model;
                
                // Copy piece image to drag board
                Dragboard dragboard = view.startDragAndDrop(TransferMode.COPY_OR_MOVE);
                ClipboardContent content = new ClipboardContent();
                
                // Set content of clipboard
                content.putImage(image);
                dragboard.setContent(content);
                event.consume();
                
                view.setVisible(false);
            }
        });
        
        // Event listener occurred when the drag is done
        view.setOnDragDone(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event)
            {
                // if draggable is success, inform that to the super controller
                TransferMode mode = event.getTransferMode();
                if (mode == TransferMode.MOVE) {
                    model.clear();
                    gameController.onDragCompleted();
                }
                
                view.setVisible(true);
                event.consume();
            }
        });
        
        // Event listener occurred when the drop get stared
        view.setOnDragOver(new EventHandler<DragEvent>()
        {
            @Override
            public void handle(DragEvent event)
            {
                // Get the content of dragboard
                Dragboard dragboard = event.getDragboard();
                
                // Get of dragged model from the global variable
                Piece droppedPiece = Common.draggedPiece;
                
                // Check if the dragged piece can be dropped on the current piece
                if (dragboard.hasImage() && droppedPiece.isDroppable(model)) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                
                event.consume();
            }
        });
        
        // Event listener occurred when the drop is done
        view.setOnDragDropped(new EventHandler<DragEvent>()
        {
            @Override
            public void handle(DragEvent event)
            {
                // Get the content of dragboard
                Dragboard dragboard = event.getDragboard();
                
                if (dragboard.hasImage()) {
                    // Get the dragged piece from the global variable
                    Piece droppedPiece = Common.draggedPiece;
                    
                    // Check if the dragged piece can be dropped on the current piece
                    if (droppedPiece.isDroppable(model)) {
                        // Replace the current piece with dragged piece
                        model.set(droppedPiece);
                        event.setDropCompleted(true);
                    }
                    else {
                        event.setDropCompleted(false);
                    }
                }
                else {
                    event.setDropCompleted(false);
                }
                
                event.consume();
            }
        });
    }
}
