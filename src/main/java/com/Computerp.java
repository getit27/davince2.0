package com;

import pac.inter.Mcomputable;
import pac.inter.Mcomputed;

abstract public  class Computerp {
    abstract public void initialize(Mcomputable[] source) throws Exception;
    abstract public Mcomputed[]compute();
    protected Object createRstObject(Class<?> type)throws Exception{
        Object ro=type.getDeclaredConstructor().newInstance();
        return ro;
    }
}
