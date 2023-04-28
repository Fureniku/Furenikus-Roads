package com.silvaniastudios.roads.blocks.paint.properties;

public class PaintGrid {

    protected boolean[][] grid;

    public PaintGrid(short[][] gridIn) {
        grid = toBoolean(gridIn);
    }

    public PaintGrid(boolean[][] gridIn) {
        grid = gridIn;
    }

    private boolean[][] toBoolean (short[][] input) {
        boolean[][] out = new boolean[input.length][input.length];

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                out[i][j] = input[j][i] != 0;
            }
        }

        return out;
    }

    public boolean[][] getGrid() {
        return grid;
    }

    public int size() {
        return grid.length;
    }
}