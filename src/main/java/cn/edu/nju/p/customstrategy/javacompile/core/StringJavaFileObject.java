package cn.edu.nju.p.customstrategy.javacompile.core;


import java.net.URI;

import javax.tools.SimpleJavaFileObject;

/**
 * Created by dell- on 2017/6/7.
 */
class StringJavaFileObject extends SimpleJavaFileObject {

    final String code;

    StringJavaFileObject(String className, String code) {
        super(URI.create(className + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }
}