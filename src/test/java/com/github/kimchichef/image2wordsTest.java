package com.github.kimchichef;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.google.gson.JsonSyntaxException;
import org.junit.Test;

/**
 * Unit test for simple pinyinIME.
 */
public class image2wordsTest {
    /**
     * Rigorous Test :-)
     */

    @Test
    public void testCorrectAnswer() {
        image2words classUnderTest = new image2words();
        String result = "result";

        try {
            result = classUnderTest.image2words("http://mmbiz.qpic.cn/mmbiz_jpg/lmj7LYw8vJMSXInnsaODzdKUeUpgwhtY2NMrtypx03kXy9iceSWPTYfOMxjY4X3kZ5Qd97O2TXMzyibsrsr8BKwA/0");
        } catch (JsonSyntaxException e) {
            System.out.println("found exception in test");
            e.printStackTrace();
        }
        System.out.println(result);
        assertTrue(result.contains("产"));
    }
//
//    @Test
//    public void testIncorrectAnswer() {
//        image2words classUnderTest = new image2words();
//        String result = "result";
//
//        try {
//            result = classUnderTest.image2words(" nihao ");
//        } catch (JsonSyntaxException e) {
//            System.out.println("found exception in test");
//            e.printStackTrace();
//        }
//        assertEquals(" nihao ", result);
//    }

}
