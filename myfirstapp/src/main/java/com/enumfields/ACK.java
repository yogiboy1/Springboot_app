package com.enumfields;

import java.util.HashMap;
import java.util.Map;

public enum ACK {
NOT_RECEIVED(0),
RECEIVED(1),
ERROR(2),
PARTIAL_FILES(4);


private int value;
private static Map<Integer,ACK> map = new HashMap<>();

private ACK(int value) {
    this.value = value;
}

static {
    for (ACK ack: ACK.values()) {
        map.put(ack.value, ack);
    }
}

public static ACK valueOf(int value) {
    return (ACK) map.get(value);
}

public int getValue() {
    return value;
}
}
