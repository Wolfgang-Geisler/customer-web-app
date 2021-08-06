@echo off

set WILDFLY_HOME=E:\WEBF\wildfly-17.0.1.Final

if not exist %WILDFLY_HOME% (
    echo ===========================================
    echo  NOTE: set WILDFLY_HOME to WildFly 17 path
    echo ===========================================
    pause
    exit 1
)

if not "%~1" == "--nopause" (
    pause
)