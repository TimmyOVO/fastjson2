package com.alibaba.fastjson2.jsonpath;

import com.alibaba.fastjson2.JSONPath;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrinoSupportTest {
    @Test
    public void test_wildcard() {
        assertEquals(
                "[100,\"AFRICA\"]",
                JSONPath.extract("{\"customer\" : 100, \"region\" : \"AFRICA\"}", "$.*").toString()
        );
        assertEquals(
                "[\"ASIA\"]",
                JSONPath.extract("{\"region\" : \"ASIA\"}", "$.*").toString()
        );
        assertEquals(
                "[300,\"AFRICA\",null]",
                JSONPath.extract("{\"customer\" : 300, \"region\" : \"AFRICA\", \"comment\" : null}", "$.*").toString()
        );
    }

    @Test
    public void test_array_accessor() {
        assertEquals(
                "2",
                JSONPath.extract("[0, 1, 2]", "$[last]").toString()
        );
    }

    @Test
    public void test_predicate() {
        assertEquals(
                "[1]",
                JSONPath.extract("[0, 1, 2]", "$[?(@>0 && @<2)]").toString()
        );

        assertEquals(
                "[\"abc\",\"abb\"]",
                JSONPath.extract("['abc','aaa','abb']", "$[?(@ starts with 'ab')]").toString()
        );
    }

    @Test
    public void test_function() {
        assertEquals(
                2D,
                JSONPath.extract("2", "$.double()")
        );
    }
}
