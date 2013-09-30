package com.github.sebhoss.rondo.board;

import com.github.sebhoss.common.annotation.Nullsafe;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

abstract class AbstractNeighborPredicate extends AbstractCellPredicate {

    protected AbstractNeighborPredicate(final Table<Integer, Integer, Stone> playedStones) {
        super(playedStones);
    }

    @Override
    public final boolean apply(final Cell<Integer, Integer, Stone> cell) {
        final Integer abscissa = Nullsafe.nullsafe(cell.getRowKey());
        final Integer ordinate = Nullsafe.nullsafe(cell.getColumnKey());

        final int abscissaToAsk = calculateAbscissaToAsk(abscissa.intValue());
        final int ordinateToAsk = calculateOrdinateToAsk(ordinate.intValue());

        return cellIsOnBoard(abscissaToAsk, ordinateToAsk) && !stone(abscissaToAsk, ordinateToAsk).isPresent();
    }

    protected abstract int calculateAbscissaToAsk(int abscissa);

    protected abstract int calculateOrdinateToAsk(int ordinate);

}
