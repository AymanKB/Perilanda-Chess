package Model;

import java.util.Observable;
import Utility.*;

/**
 * Piece model
 *
 * @author ghyasi
 * @version 0.0.0
 */
public class Piece extends Observable
{
    // Column of piece
    private int col;
    // Row of piece
    private int row;
    
    // Role of piece
    private IRole role;
    // Owner of piece - player1, player2, none
    private int owner;
    
    // Draggabiliy of piece
    private boolean draggable;
    
    /**
     * Constructor for objects of class Piece
     */
    public Piece()
    {
        row = -1;
        col = -1;
        role = new NoRole();
        owner = PlayerState.NONE;
    }
    
    /**
     * Piece Constructor
     *
     * @param col Column
     * @param row Row
     */
    public Piece(int col, int row)
    {
        this.col = col;
        this.row = row;
        role = new NoRole();
        owner = PlayerState.NONE;
    }

    /**
     * Set role on the piece
     *
     * @param role Role
     */
    public void setRole(IRole role)
    {
        this.role = role;
        
        setChanged();
        notifyObservers();
    }
    
    /**
     * Set owner on the piece
     *
     * @param owner Owner
     */
    public void setOwner(int owner)
    {
        this.owner = owner;
        
        setChanged();
        notifyObservers();
    }
    
    /**
     * Set boath the role and owner
     *
     * @param role Role
     * @param owner Owner
     */
    public void setRoleAndOwner(IRole role, int owner)
    {
        this.role = role;
        this.owner = owner;
        
        setChanged();
        notifyObservers();
    }
    
    /**
     * set draggable state of the piece
     *
     * @param draggable draggable state
     */
    public void setDraggable(boolean draggable)
    {
        this.draggable = draggable;
    }
    
    /**
     * Get the draggable state of the piece
     *
     * @return Draggable state of the piece
     */
    public boolean isDraggable()
    {
        return draggable;
    }
    
    /**
     * Get the droppable state of the piece
     *
     * @param other Droppable place
     * @return Droppable state of the piece
     */
    public boolean isDroppable(Piece other)
    {
        return role.checkMove(this, other);
    }
    
    /**
     * Get the string represent of the current piece
     *
     * @return String represent of the current piece
     */
    public String getRoleString() {
        return role.getRoleString();
    }
    
    /**
     * Get image path of current piece
     *
     * @return image path of current piece
     */
    public String getImagePath()
    {
        if (owner == PlayerState.NONE) {
            return null;
        }
        
        if (role.getRoleString() == null) {
            return null;
        }
        
        return "Image/" + role.getRoleString() + String.valueOf(owner) + ".png";
    }
    
    /**
     * Get column number
     *
     * @return Column number
     */
    public int getCol()
    {
        return col;
    }
    
    /**
     * Get row number
     *
     * @return Row number
     */
    public int getRow()
    {
        return row;
    }
    
    /**
     * Get role of the piece
     *
     * @return Role of the piece
     */
    public IRole getRole()
    {
        return role;
    }
    
    /**
     * Get owner of the piece
     *
     * @return Owner of the piece
     */
    public int getOwner()
    {
        return owner;
    }
    
    /**
     * Place piece with other piece
     *
     * @param other Other piece to replace
     */
    public void set(Piece other)
    {
        this.role = other.getRole();
        this.owner = other.getOwner();
        
        setChanged();
        notifyObservers();
    }
    
    /**
     * Clear the piece
     *
     */
    public void clear()
    {
        this.role = new NoRole();
        this.owner = PlayerState.NONE;
        
        setChanged();
        notifyObservers();
    }

	public void updateRole() {
		setRole(getRole().getNextRole(col, row, owner));
	}
}
