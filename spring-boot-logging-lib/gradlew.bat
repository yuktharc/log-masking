@echo off
setlocal

rem Determine the directory of this script
set DIR=%~dp0

rem Set the Gradle home directory
set GRADLE_HOME=%DIR%gradle\wrapper

rem Execute the Gradle wrapper
"%GRADLE_HOME%\gradle-wrapper.jar" %*

endlocal