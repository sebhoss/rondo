package com.github.sebhoss.rondo.board;

import com.google.common.collect.Table;

final class CellRightIsEmpty extends AbstractNeighborPredicate {

    CellRightIsEmpty(final Table<Integer, Integer, Stone> playedStones) {
        super(playedStones);
    }

    @Override
    protected int calculateAbscissaToAsk(final int abscissa) {
        return abscissa;
    }

    @Override
    protected int calculateOrdinateToAsk(final int ordinate) {
        return ordinate + 1;
    }

}
