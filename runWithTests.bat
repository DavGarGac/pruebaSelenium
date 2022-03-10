@echo off
call mvn clean test
pause
call xcopy results allure-results /E /Y
pause
call xcopy allure-report\history allure-results\history /E /Y
pause 
call allure generate --clean
pause
call allure open
exit