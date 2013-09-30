package com.github.sebhoss.rondo.board;

import com.github.sebhoss.common.annotation.CompilerWarnings;
import com.github.sebhoss.common.annotation.Nullsafe;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

final class BoardPredicates {

    static Predicate<Cell<Integer, Integer, Stone>> cellIsOccupied() {
        return new CellIsOccupied();
    }

    static Predicate<Cell<Integer, Integer, Stone>> cellIsEmpty() {
        return Nullsafe.nullsafe(Predicates.not(cellIsOccupied()));
    }

    @SuppressWarnings(CompilerWarnings.UNCHECKED)
    static Predicate<Cell<Integer, Integer, Stone>> cellsWithFreedom(final Table<Integer, Integer, Stone> playedStones,
            final Stone color) {
        return Nullsafe.nullsafe(Predicates.or(cellAboveIsEmpty(playedStones), cellBelowIsEmpty(playedStones),
                cellLeftIsEmpty(playedStones), cellRightIsEmpty(playedStones), cellIsSurrounded(playedStones, color)));
    }

    static Predicate<Cell<Integer, Integer, Stone>> cellsWithoutFreedom(
            final Table<Integer, Integer, Stone> playedStones, final Stone color) {
        return Nullsafe.nullsafe(Predicates.not(cellsWithFreedom(playedStones, color)));
    }

    static Predicate<Cell<Integer, Integer, Stone>> cellAboveIsEmpty(final Table<Integer, Integer, Stone> playedStones) {
        return new CellAboveIsEmpty(playedStones);
    }

    private static Predicate<Cell<Integer, Integer, Stone>> cellBelowIsEmpty(
            final Table<Integer, Integer, Stone> playedStones) {
        return new CellBelowIsEmpty(playedStones);
    }

    private static Predicate<Cell<Integer, Integer, Stone>> cellLeftIsEmpty(
            final Table<Integer, Integer, Stone> playedStones) {
        return new CellLeftIsEmpty(playedStones);
    }

    private static Predicate<Cell<Integer, Integer, Stone>> cellRightIsEmpty(
            final Table<Integer, Integer, Stone> playedStones) {
        return new CellRightIsEmpty(playedStones);
    }

    private static Predicate<Cell<Integer, Integer, Stone>> cellIsSurrounded(
            final Table<Integer, Integer, Stone> playedStones, final Stone color) {
        return new CellHasFreedom(playedStones, color);
    }

}
