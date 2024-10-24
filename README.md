## Build

Must use GraalVM 21

```shell
$JAVA_HOME/bin/native-image --add-exports org.graalvm.nativeimage.builder/com.oracle.svm.core.c.function=ALL-UNNAMED -O0 -H:TempDirectory=./tmp -H:GenerateDebugInfo=1  --no-fallback -jar target/untitled3-1.0-SNAPSHOT.jar
```
