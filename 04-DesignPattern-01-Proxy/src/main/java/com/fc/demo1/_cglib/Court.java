package com.fc.demo1._cglib;

import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

public class Court {
    @Test
    public void test() {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(Parties.class);
        enhancer.setCallback(new LawOffice());
        Parties proxy = (Parties) enhancer.create();

        proxy.submit();
        proxy.defend();
    }
}
