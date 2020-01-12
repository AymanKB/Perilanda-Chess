package Model;


import Utility.*;

/**
 * TercelRole
 *
 * @author ghyasi
 * @version 0.0.0
 */
public class TercelRole implements IRole {
    
    // Game model
    private Game game;
    
    /**
     * Constructor
     *
     */
    public TercelRole()
    {
        game = null;
    }
    
    /**
     * Constructor
     *
     * @param game Game model
     */
    public TercelRole(Game game)
    {
        this.game = game;
    }
    
    @Override
    public String getRoleString()
    {
        return "tercel";
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
        
        int diffCol = toCol - fromCol;
        int diffRow = toRow - fromRow;
        
        if (diffCol != 0 && diffRow != 0) {
            return false;
        }
        
        int stepCol = 0;
        int stepRow = 0;
        
        if (diffCol != 0) {
            stepCol = diffCol / Math.abs(diffCol);
            
            for (int col = fromCol + stepCol; col != toCol; col += stepCol) {
                if (game.getPiece(col, fromRow).getOwner() != PlayerState.NONE) {
                    return false;
                }
            }
        }
        
        if (diffRow != 0) {
            stepRow = diffRow / Math.abs(diffRow);

            for (int row = fromRow + stepRow; row != toRow; row += stepRow) {
                if (game.getPiece(fromCol, row).getOwner() != PlayerState.NONE) {
                    return false;
                }
            }
            
        }
        
        return true;
    }
    
    @Override
    public IRole getNextRole(int col, int row, int owner)
    {
        if (owner == PlayerState.PLAYER1 && game.getCurrentPlayer() == PlayerState.PLAYER1) {
            if (game.getNumPlayer1Round() % 3 == 0 && game.getNumPlayer1Round() > 0) {
                return new ExcelRole(game);
            }
        } else if (owner == PlayerState.PLAYER2 && game.getCurrentPlayer() == PlayerState.PLAYER2) {
            if (game.getNumPlayer2Round() % 3 == 0 && game.getNumPlayer2Round() > 0) {
                return new ExcelRole(game);
            }
        }
        
        return new TercelRole(game);
    }
}
