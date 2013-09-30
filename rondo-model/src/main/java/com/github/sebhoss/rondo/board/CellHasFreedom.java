package com.github.sebhoss.rondo.board;

import com.github.sebhoss.common.annotation.Nullsafe;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

final class CellHasFreedom extends AbstractCellPredicate {

    private static final Predicate<Cell<Integer, Integer, Stone>> cellIsEmpty = BoardPredicates.cellIsEmpty();

    private final Stone                                           stone;

    protected CellHasFreedom(final Table<Integer, Integer, Stone> playedStones, final Stone stone) {
        super(playedStones);
        this.stone = stone;
    }

    @Override
    public boolean apply(final Cell<Integer, Integer, Stone> cell) {
        return cellIsEmpty.apply(cell) && neighborsHaveFreedom(cell);
    }

    private boolean neighborsHaveFreedom(final Cell<Integer, Integer, Stone> cell) {
        final int abscissa = Nullsafe.nullsafe(cell.getRowKey()).intValue();
        final int ordinate = Nullsafe.nullsafe(cell.getColumnKey()).intValue();
        final Optional<Stone> above = stone(abscissa + 1, ordinate);
        final Optional<Stone> below = stone(abscissa - 1, ordinate);
        final Optional<Stone> left = stone(abscissa, ordinate - 1);
        final Optional<Stone> right = stone(abscissa, ordinate + 1);

        boolean neighborsFree = false;

        neighborsFree |= !above.isPresent() || stone.equals(above.get());
        neighborsFree |= !below.isPresent() || stone.equals(below.get());
        neighborsFree |= !left.isPresent() || stone.equals(left.get());
        neighborsFree |= !right.isPresent() || stone.equals(right.get());

        return neighborsFree;
    }

}
