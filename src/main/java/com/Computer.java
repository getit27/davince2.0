package com;

import pac.Packet;

public interface Computer {
    public Class<?>getInType();
    public Class<?>getOutType();
    public Packet compute(Packet p);
}
