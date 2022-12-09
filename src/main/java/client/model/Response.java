package main.java.client.model;

import java.io.Serializable;

public enum Response implements Serializable {
    OK,
    ERROR,
    UPDATED,
    NOTFOUND,
}
