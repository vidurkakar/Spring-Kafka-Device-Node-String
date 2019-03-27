package com.codenotfound.kafka.osdkLinker;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface LLib extends Library
{

    //    LLib instance = (LLib) Native.load("Length",LLib.class);
//    int main(String text);
    //LLib instance = (LLib) Native.load("testOut_2",LLib.class);
    LLib instance = (LLib) Native.load("Linked",LLib.class);
    void intialize_C();
    void droneTakeOff_C();
    void droneMove_C(float xOffsetDesire ,float yOffsetDesired, float zOffsetDesired, float yawDesired);
    void droneLand_C();

    //void linkFunction();
    //void droneTakeOff_C();

}