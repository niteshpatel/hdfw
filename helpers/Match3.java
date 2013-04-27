package com.herodevelop.hdfw.helpers;

import java.util.HashMap;

public final class Match3 {

    private Match3Handler handler;

    public final void setMatch3Handler(Match3Handler handler) {
        this.handler = handler;
    }

    // Move all the lines downwards
    public final void dropDown(Object[][] grid) {
        int width = grid.length;
        int height = grid[0].length;

        // Now shift all the rows above down one row
        int count;
        for (int i = 0; i < width; i++) {
            count = 0;
            for (int j = height - 1; j >= 0; j--) {

                // Count the number of positions the block drops
                if (grid[i][j] == null) {
                    count++;
                }

                // If the next block is occupied or the bottom of the grid
                // then we can finally perform the drop on all the relevant
                // blocks in the column
                if (count > 0 && ((j > 0 && grid[i][j - 1] != null) || j == 0)) {
                    for (int k = j; k + count < height; k++) {
                        grid[i][k] = grid[i][k + count];
                        grid[i][k + count] = null;

                        // Allow custom handling of the item after drop
                        if (handler != null) {
                            handler.postDropDown(grid[i][k], count);
                        }
                    }
                    break;
                }
            }
        }
    }

    // Remove all grid positions marked in 'chain' with 'symbol' from 'grid'
    public final void removeChain(Object[][] grid, Integer[][] chain, Integer symbol) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (chain[i][j].equals(symbol)) {

                    // Allow custom handling of the item after removal
                    if (handler != null) {
                        handler.postRemoveChain(grid[i][j]);
                    }
                    grid[i][j] = null;
                }
            }
        }
    }

    // Check for a chain of connected items, and mark the chain with 'symbol'
    public final Integer[][] getChain(Object[][] grid, int x, int y, Integer[][] checked, Integer symbol) {
        int width = grid.length;
        int height = grid[0].length;

        // Initialise the array if necessary
        if (checked == null) {
            checked = new Integer[width][height];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    checked[i][j] = 0;
                }
            }
        }

        // Recursively mark the chain
        if (checked[x][y] == 0 && grid[x][y] != null) {
            if (y + 1 < height && handler.itemKey(grid[x][y]) == handler.itemKey(grid[x][y + 1])) {
                checked[x][y] = symbol;
                getChain(grid, x, y + 1, checked, symbol);
            }
            if (x + 1 < width && handler.itemKey(grid[x][y]) == handler.itemKey(grid[x + 1][y])) {
                checked[x][y] = symbol;
                getChain(grid, x + 1, y, checked, symbol);
            }
            if (y - 1 > -1 && handler.itemKey(grid[x][y]) == handler.itemKey(grid[x][y - 1])) {
                checked[x][y] = symbol;
                getChain(grid, x, y - 1, checked, symbol);
            }
            if (x - 1 > -1 && handler.itemKey(grid[x][y]) == handler.itemKey(grid[x - 1][y])) {
                checked[x][y] = symbol;
                getChain(grid, x - 1, y, checked, symbol);
            }
        }

        // Handles the case where there is only one block in the chain
        checked[x][y] = symbol;
        return checked;
    }

    // Counts the number of positions 'grid' marked with 'symbol'
    public Integer getCount(Integer[][] grid, Integer symbol) {
        int width = grid.length;
        int height = grid[0].length;

        int counter = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grid[i][j].equals(symbol)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    // Get the counts of all the chains in the grid, note that the 0 key is
    // special and is used to store the symbol with the highest chain size
    public HashMap<Integer, Integer> countChains(Object[][] grid, Integer[][] checked) {
        int width = grid.length;
        int height = grid[0].length;

        // Initialise the checked array
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                checked[i][j] = 0;
            }
        }

        // Chain counts
        HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();

        // Get all the chains
        Integer[][] chain;
        int symbol = 0;
        int maxSize = 0;
        int chainSize;
        Integer chainCount;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                // Skip positions already checked
                if (checked[i][j] != 0) {
                    continue;
                }

                // Use a new marking symbol for each chain, this lets us mark
                // the chain on the same grid over and over, allow us to see
                // which parts of the grid have not been checked
                symbol++;

                // Mark the chain at the given position with the given symbol
                chain = getChain(grid, i, j, checked, symbol);

                // Get the size of the chain, and update our chain count map
                chainSize = getCount(chain, symbol);
                chainCount = counts.get(chainSize);
                if (chainCount == null) {
                    chainCount = 0;
                }
                chainCount++;
                counts.put(chainSize, chainCount);

                // Update maxSize
                if (chainSize > maxSize) {
                    maxSize = chainSize;

                    // We mark the symbol with the biggest chain size
                    counts.put(0, symbol);
                }
            }
        }
        return counts;
    }

    public interface Match3Handler {
        public void postDropDown(Object object, int count);

        public void postRemoveChain(Object object);

        public int itemKey(Object object);
    }
}
