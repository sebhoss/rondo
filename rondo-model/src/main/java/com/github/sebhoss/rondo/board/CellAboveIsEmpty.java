package com.github.sebhoss.rondo.board;

import com.google.common.collect.Table;

final class CellAboveIsEmpty extends AbstractNeighborPredicate {

    CellAboveIsEmpty(final Table<Integer, Integer, Stone> playedStones) {
        super(playedStones);
    }

    @Override
    protected int calculateAbscissaToAsk(final int abscissa) {
        return abscissa + 1;
    }

    @Override
    protected int calculateOrdinateToAsk(final int ordinate) {
        return ordinate;
    }

}
