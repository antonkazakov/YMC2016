package com.antonkazakov.radio.data.content;

/**
 * Created by antonkazakov on 25.10.16.
 */

public class BusEvents {

    private BusEvents(){}

    public static class Message {

        public final int type;

        public Message(int type) {
            this.type = type;
        }
    }


}
