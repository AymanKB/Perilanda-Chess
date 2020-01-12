package Model;


/**
 * ChiefRole
 *
 * @author ghyasi
 * @version 0.0.0
 */
public class ChiefRole implements IRole
{
    // Game model
    private Game game;
    
    /**
     * Constructor
     *
     */
    public ChiefRole()
    {
        this.game = null;
    }
    
    /**
     * Constructor
     *
     * @param game Game model
     */
    public ChiefRole(Game game)
    {
        this.game = game;
    }
    
    @Override
    public String getRoleString()
    {
        return "chief";
    }
    
    @Override
    public boolean checkMove(Piece from, Piece to)
    {
        if (from.getOwner() == to.getOwner()) {
            return false;
        }
        
        int fromCol = from.getCol();
        int fromRow = from.getRow();
        
        int toCol = to.getCol();
        int toRow = to.getRow();
        
        int diffCol = fromCol - toCol;
        int diffRow = fromRow - toRow;
        
        if (Math.abs(diffCol) <= 1 && Math.abs(diffRow) <= 1) {
            return true;
        }
        
        return false;
    }
    
    @Override
    public IRole getNextRole(int col, int row, int owner)
    {
        return new ChiefRole(game);
    }
}
