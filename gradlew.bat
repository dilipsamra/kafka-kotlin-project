@echo off
set DIR=%~dp0
set GRADLE_WRAPPER=%DIR%\gradle\wrapper\gradle-wrapper.jar
java -Dorg.gradle.appname=gradlew -classpath "%GRADLE_WRAPPER%" org.gradle.wrapper.GradleWrapperMain %*
