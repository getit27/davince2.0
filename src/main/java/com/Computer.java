package com;

import pac.Packages;

public interface Computer {
    public Class<?>getInType();
    public Class<?>getOutType();
    public Packages compute(Packages p);
}
