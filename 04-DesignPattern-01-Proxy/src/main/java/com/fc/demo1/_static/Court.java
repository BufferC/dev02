package com.fc.demo1._static;

import org.junit.Test;

public class Court {
    @Test
    public void test() {
        // 原告永强
        Lawsuit parties = new Parties();

        // 律师
        Lawsuit lawyer = new Lawyer(parties);

        lawyer.submit();
        lawyer.defend();
    }
}
