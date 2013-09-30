package com.github.sebhoss.rondo.board;

import com.google.common.base.Predicate;
import com.google.common.collect.Table.Cell;

final class CellIsOccupied implements Predicate<Cell<Integer, Integer, Stone>> {

    @Override
    public boolean apply(final Cell<Integer, Integer, Stone> cell) {
        return cell.getValue() != null;
    }

}
