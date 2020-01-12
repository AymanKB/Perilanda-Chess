package Model;


/**
 * NoRole
 *
 * @author ghyasi
 * @version 0.0.0
 */
public class NoRole implements IRole
{
    @SuppressWarnings("unused")
	private Game game;
    
    /**
     * NoRole Constructor
     *
     */
    public NoRole()
    {
        game = null;
    }
    
    /**
     * NoRole Constructor
     *
     * @param game Game model
     */
    public NoRole(Game game)
    {
        this.game = game;
    }
    
    @Override
    public String getRoleString()
    {
        return null;
    }
    
    @Override
    public boolean checkMove(Piece from, Piece to)
    {
        return false;
    }
    
    @Override
    public IRole getNextRole(int col, int row, int owner)
    {
        return new NoRole();
    }
}
