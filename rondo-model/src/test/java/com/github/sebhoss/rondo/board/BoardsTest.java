package com.github.sebhoss.rondo.board;

import org.junit.Assert;
import org.junit.Test;

import com.github.sebhoss.common.annotation.CompilerWarnings;

/**
 * Test cases for {@link Boards}.
 */
@SuppressWarnings(CompilerWarnings.STATIC_METHOD)
public class BoardsTest {

    /**
     * Ensures that a beginner board can be constructed.
     */
    @Test
    public void shouldCreateBeginnerBoard() {
        Assert.assertNotNull(Boards.createBeginnerBoard());
    }

}
