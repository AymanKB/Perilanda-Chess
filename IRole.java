package Model;

/**
 * Role of the chess piece
 *
 * @author ghyasi
 * @version 0.0.0
 */
public interface IRole
{
    /**
     * Get the string representation of role
     *
     * @return String representation of role
     */
    public String getRoleString();
    
    /**
     * Get the next role of current role
     *
     * @return Next role
     */
    public IRole getNextRole(int col, int row, int owner);
    
    /**
     * Check if the piece can move from one piece to another piece
     *
     * @param from Start piece
     * @param to End piece
     * @return true if the piece can move, otherwise false
     */
    public boolean checkMove(Piece from, Piece to);
}
