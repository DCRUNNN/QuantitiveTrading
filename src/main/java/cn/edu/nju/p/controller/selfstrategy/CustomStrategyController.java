package cn.edu.nju.p.controller.selfstrategy;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.strategy.customstrategy.javacompile.core.RuntimeCompiler;
import cn.edu.nju.p.strategy.customstrategy.javacompile.util.RunCompileUtil;
import cn.edu.nju.p.enums.ErrorCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by dell- on 2017/6/7.
 */

@RestController
@RequestMapping("/customstrategy")
public class CustomStrategyController {

    @GetMapping
    public BaseResult runCode(@RequestParam String code, HttpServletRequest request) {
        int errorCode=0;
        String className = null;
        String fullClassName = null;
        String result = "";
        try {
            className = RunCompileUtil.getClassName(code);
            fullClassName = RunCompileUtil.getFullClassName(code);
        } catch (Exception e) {
            result = "Can't find class name in content.";
            return new BaseResult(ErrorCode.NO_CLASS_NAME.getErrorCode(), result);
        }

        // Compile source code to tomcat real path
//        String realPath = Thread.currentThread().getContextClassLoader().getResource("").toString();
        RuntimeCompiler rc = new RuntimeCompiler("-d", "./target/classes");
        boolean success = rc.compile(className, code);

        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        // Tell Java to use your special stream
        System.setOut(printStream);

        if (success) {
            try {
                Class.forName(fullClassName).getDeclaredMethod("main", new Class[] {
                        String[].class
                }).invoke(null, new Object[] {
                        null
                });
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException
                    | InvocationTargetException e) {
                result = "Load class error: " + e;
            }
        }

        // flush output stream
        System.out.flush();

        if (success) {
            result = baos.toString();
            errorCode = 0;
        } else {
            result = rc.getTraceMsg();
            errorCode = 40000002;
        }
        return new BaseResult(errorCode, result);
    }

}
