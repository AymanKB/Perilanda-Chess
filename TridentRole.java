package Model;


import Utility.*;

/**
 * TridentRole
 *
 * @author ghyasi
 * @version 0.0.0
 */
public class TridentRole implements IRole {
    
    // Game model
    private Game game;
    
    /**
     * Constructor
     *
     */
    public TridentRole()
    {
        game = null;
    }
    
    /**
     * Constructor
     *
     * @param game Game model
     */
    public TridentRole(Game game)
    {
        this.game = game;
    }
    
    @Override
    public String getRoleString() {
        // TODO Auto-generated method stub
        return "trident";
    }

    @Override
    public IRole getNextRole(int col, int row, int owner) {
        if (row == 0)
            return new TridentInvRole(game);
        return new TridentRole(game);
    }

    @Override
    public boolean checkMove(Piece from, Piece to) {
        if (from.getOwner() == to.getOwner()) {
            return false;
        }
        
        int fromCol = from.getCol();
        int fromRow = from.getRow();
        
        int toCol = to.getCol();
        int toRow = to.getRow();
        
        int diffCol = toCol - fromCol;
        int diffRow = toRow - fromRow;
        
        if (diffRow == -1 && diffCol == 0) {
            return true;
        }
        
        if (diffRow != 0) {
            return false;
        }
        
        int stepCol = diffCol / Math.abs(diffCol);
        
        for (int col = fromCol + stepCol; col != toCol; col += stepCol) {
            if (game.getPiece(col, fromRow).getOwner() != PlayerState.NONE) {
                return false;
            }
        }
        
        return true;
    }
}
