package cn.edu.nju.p.strategy.customstrategy;

import cn.edu.nju.p.strategy.customstrategy.javacompile.core.RuntimeCompiler;
import cn.edu.nju.p.strategy.customstrategy.javacompile.util.RunCompileUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by dell- on 2017/6/7.
 */
public class RuntimeCompileTest {

    public static void main(String args[]) throws IOException {
        StringBuffer str = new StringBuffer();
//        str.append("package cn.edu.nju.p.controller.customstrategy;");
        str.append("public class HelloWorld {");
        str.append("  public static void main(String args[]) {");
        str.append("    System.out.println(\"This is in my first java file\");");
        str.append("  }");
        str.append("}");
        String code = str.toString();
        System.out.println(code);
        String className = RunCompileUtil.getClassName(code);
        String fullClassName = RunCompileUtil.getFullClassName(code);

        // Compile source code to folder bin
        RuntimeCompiler rc = new RuntimeCompiler("-d", ".\\target\\classes");
        boolean success = rc.compile(className, code);

        if (success) {
            try {
                Class.forName(fullClassName).getDeclaredMethod("main", new Class[] {
                        String[].class
                }).invoke(null, new Object[] {
                        null
                });

            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException
                    | InvocationTargetException e) {
                System.err.println("Load class error: " + e);
            }
        }
    }

}
