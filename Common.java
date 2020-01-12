package Utility;

import Model.*;

/**
 * Common functions
 *
 * @author ghyasi
 * @version 0.0.0
 */
public class Common
{
    static public Piece draggedPiece;
    /**
     * Convert column, row pair to global index
     *
     * @param col Column
     * @param row Row
     * @return Global index
     */
    static public int globalIndex(int col, int row)
    {
        return col * Constant.BOARD_NUM_ROWS + row;
    }
    
    /**
     * Get the column from global index
     *
     * @param globalIndex Global index
     * @return Column
     */
    static public int colOf(int globalIndex)
    {
        return globalIndex / Constant.BOARD_NUM_ROWS;
    }
    
    /**
     * Get the row from global index
     *
     * @param globalIndex Global index
     * @return Row
     */
    static public int rowOf(int globalIndex)
    {
        return globalIndex % Constant.BOARD_NUM_ROWS;
    }
}
