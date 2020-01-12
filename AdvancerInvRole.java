package Model;


import Utility.PlayerState;
import Utility.Constant;

/**
 * AdvancerInvRole
 *
 * @author ghyasi
 * @version 0.0.0
 */
public class AdvancerInvRole implements IRole {

    // Game model
    private Game game;
    
    /**
     * Constructor
     *
     */
    public AdvancerInvRole()
    {
        game = null;
    }
    
    /**
     * Constructor
     *
     * @param game Game model
     */
    public AdvancerInvRole(Game game)
    {
        this.game = game;
    }
    
    @Override
    public String getRoleString() {
        return "advancerinv";
    }

    @Override
    public IRole getNextRole(int col, int row, int owner) {
        if (row == Constant.BOARD_NUM_ROWS - 1) {
            return new AdvancerRole(game);
        }
        
        return new AdvancerInvRole(game);
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
        
        if (diffCol != 0) {
            return false;
        }
        
        if (diffRow == 1) {
            return true;
        }
        
        if (diffRow == 2 && game.getPiece(fromCol, fromRow + 1).getOwner() == PlayerState.NONE) {
            return true;
        }
        
        return false;
    }
}
