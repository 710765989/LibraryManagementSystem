package com.library.librarymanagementsystem.utils;

import java.util.HashMap;

public class R extends HashMap<String, Object> {
    private static final int success = 0;
    private static final int error = 500;
    private static final String CODE = "code";
    private static final String MESSAGE = "message";
    public R () {
        put(CODE, success);
        put(MESSAGE, "success");
    }

    public static R error() {
        return error(error, "系统错误。");
    }

    public static R error(String msg) {
        return error(error, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put(CODE, code);
        r.put(MESSAGE, msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put(MESSAGE, msg);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
