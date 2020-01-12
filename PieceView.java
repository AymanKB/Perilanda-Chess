package View;


import java.util.Observable;
import java.util.Observer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Model.*;

/**
 * Piece view
 *
 * @author Ayman
 * @version 0.0.0
 */
public class PieceView extends ImageView implements Observer
{
    /**
     * Constructor
     */
    public PieceView()
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
        setFitWidth(60);
        setFitHeight(60);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Piece) {
            Piece model = (Piece)o;
            String imagePath = model.getImagePath();
            
            if (imagePath == null) {
                setImage(null);
            }
            else {
                Image image = new Image(imagePath);
                setImage(image);
            }
        }
    }
}
