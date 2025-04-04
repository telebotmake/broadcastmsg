@echo off
set DIR=%~dp0
set DIR=%DIR:~0,-1%
set GRADLE_WRAPPER_DIR=%DIR%\gradle\wrapper
java -Xmx64m -Xms64m -Dorg.gradle.appname=broadcastmod -classpath "%GRADLE_WRAPPER_DIR%\gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain %*
