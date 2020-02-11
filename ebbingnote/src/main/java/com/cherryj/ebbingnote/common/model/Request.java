package com.cherryj.ebbingnote.common.model;

import lombok.Getter;
import lombok.Setter;

public class Request <T>{

    private @Getter
    @Setter
    T data;

}
