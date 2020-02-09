package com.cherryj.ebbingnote.common.model;

import lombok.Getter;
import lombok.Setter;

public class Response<T> {

    private @Getter
    @Setter
    String status;

    private @Getter
    @Setter
    String msg;

    private @Getter
    @Setter
    T data;
}
