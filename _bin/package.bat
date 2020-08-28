@echo off
echo.
echo [info] package
echo.

%~d0
cd %~dp0

cd ..
call mvn clean package -Dmaven.test.skip=true

pause
