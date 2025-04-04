#!/bin/sh
DIR="$(cd "$(dirname "$0")" && pwd)"
GRADLE_WRAPPER_DIR="$DIR/gradle/wrapper"
exec java -Xmx64m -Xms64m -Dorg.gradle.appname=broadcastmod -classpath "$GRADLE_WRAPPER_DIR/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
