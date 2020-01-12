package Model;


import Utility.*;

/**
 * ExcelRole
 *
 * @author ghyasi
 * @version 0.0.0
 */
public class ExcelRole implements IRole
{
    // Game model
    private Game game;
    
    /**
     * Constructor
     *
     */
    public ExcelRole()
    {
        game = null;
    }
    
    /**
     * Constructor
     *
     * @param game Game model
     */
    public ExcelRole(Game game)
    {
        this.game = game;
    }
    
    @Override
    public String getRoleString()
    {
        return "excel";
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
        
        if (Math.abs(diffCol) != Math.abs(diffRow)) {
        	return false;
        }
        
        if (Math.abs(diffCol) == 1 && Math.abs(diffRow) == 1) {
        	return true;
        }
        
        int stepCol = diffCol / Math.abs(diffCol);
        int stepRow = diffRow / Math.abs(diffRow);
        
        for (int col = fromCol + stepCol, row = fromRow + stepRow; col != toCol; col += stepCol, row += stepRow) {
        	if (game.getPiece(col, row).getOwner() != PlayerState.NONE) {
        		return false;
        	}
        }
        
        return true;
    }
    
    @Override
    public IRole getNextRole(int col, int row, int owner)
    {
    	if (owner == PlayerState.PLAYER1 && game.getCurrentPlayer() == PlayerState.PLAYER1) {
		if (game.getNumPlayer1Round() % 3 == 0 && game.getNumPlayer1Round() > 0) {
			return new TercelRole(game);
		}
    	} else if (owner == PlayerState.PLAYER2 && game.getCurrentPlayer() == PlayerState.PLAYER2) {
    		if (game.getNumPlayer2Round() % 3 == 0 && game.getNumPlayer2Round() > 0) {
    			return new TercelRole(game);
    		}
    	}
    	
    	return new ExcelRole(game);
    }
}
