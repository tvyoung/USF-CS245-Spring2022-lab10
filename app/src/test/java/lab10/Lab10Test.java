package lab10;

import static org.junit.jupiter.api.Assertions.*;

class Lab10Test {

    @org.junit.jupiter.api.Test
    void oneKingdomTest01() {
        int n = 5;
        int[][] arr = {{0,1},{1,2},{3,4},{4,2}};
        assertEquals(1, Lab10.survivedKingdoms(n, arr));
    }

    @org.junit.jupiter.api.Test
    void twoKingdomsTest02() {
        int n = 5;
        int[][] arr = {{0,1},{1,2},{3,4}};
        assertEquals(2, Lab10.survivedKingdoms(n, arr));
    }

    @org.junit.jupiter.api.Test
    void manyKingdomsTest03() {
        int n = 10;
        int[][] arr = {{4,5},{5,2},{2,7},{3,6},{7,6}};
        assertEquals(5, Lab10.survivedKingdoms(n, arr));
    }

    @org.junit.jupiter.api.Test
    void noConflictsTest04() {
        int n = 3;
        int[][] arr = {};
        assertEquals(3, Lab10.survivedKingdoms(n, arr));
    }

    @org.junit.jupiter.api.Test
    void argErrorTest05() {
        int n = 1001;
        int[][] arr = {{0,1},{1,2},{3,4},{4,5},{5,2},{2,7},{3,6},{7,6}};
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Lab10.survivedKingdoms(n, arr));
    }

    @org.junit.jupiter.api.Test
    void argErrorTest06() {
        int n = 0;
        int[][] arr = {{0,1},{1,2},{3,4},{4,5},{5,2},{2,7},{3,6},{7,6}};
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Lab10.survivedKingdoms(n, arr));
    }

    @org.junit.jupiter.api.Test
    void argErrorTest07() {
        int n =-1;
        int[][] arr = {{0,1},{1,2},{3,4},{4,5},{5,2},{2,7},{3,6},{7,6}};
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Lab10.survivedKingdoms(n, arr));
    }
}