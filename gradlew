#!/usr/bin/env sh
# Gradle start script
DIR="$(cd "$(dirname "$0")" && pwd)"
GRADLE_WRAPPER="$DIR/gradle/wrapper/gradle-wrapper.jar"
java -Dorg.gradle.appname=gradlew -classpath "$GRADLE_WRAPPER" org.gradle.wrapper.GradleWrapperMain "$@"
