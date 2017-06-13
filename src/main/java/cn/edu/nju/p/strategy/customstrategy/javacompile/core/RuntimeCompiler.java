package cn.edu.nju.p.strategy.customstrategy.javacompile.core;

import java.util.Arrays;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

/**
 * Created by dell- on 2017/6/7.
 */
public class RuntimeCompiler {

    private List<String> options = null;
    private JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    private DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
    private StringBuffer traceMsg = new StringBuffer();

    public RuntimeCompiler(String... options) {
        // inital compile params
        if (options != null && options.length > 0) {
            this.options = Arrays.asList(options);
        }
    }

    public boolean compile(String className, String code) {
        JavaFileObject sourceFile = new StringJavaFileObject(className, code);
        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(sourceFile);
        CompilationTask task = compiler.getTask(null, null, diagnostics, options, null, compilationUnits);
        boolean result = task.call();

        // Record compile error messages
        for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
            traceMsg.append(diagnostic.getMessage(null)).append("\n");
            traceMsg.append(String.format("Error on line %d in %s%n", diagnostic.getLineNumber(),
                    ((FileObject) diagnostic.getSource()).toUri()));
        }

        return result;
    }

    public String getTraceMsg() {
        return traceMsg.toString();
    }

}
