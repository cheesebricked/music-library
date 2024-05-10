package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentTest {

    Comment comment1;
    Comment comment2;

    @BeforeEach
    void before() {
        comment1 = new Comment("comment1");
        comment2 = new Comment("comment2");
    }

    @Test
    void testGetText() {
        assertEquals("comment1", comment1.getText());
    }
}
