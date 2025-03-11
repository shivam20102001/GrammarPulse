@echo off
echo Setting up GrammarPulse project...
set PROJECT_NAME=GrammarPulse

:: Create project directory
mkdir %PROJECT_NAME%
cd %PROJECT_NAME%

:: ------------------------------
:: Setup Angular Frontend
:: ------------------------------
echo Creating Angular frontend...
npx -y @angular/cli new grammar-pulse-ui --style=scss --routing
cd grammar-pulse-ui

:: Install dependencies
echo Installing Angular dependencies...
npm install primeflex primeng @angular/material

:: Initialize Git for frontend
git init
cd ..

:: ------------------------------
:: Setup Spring Boot Backend
:: ------------------------------
echo Creating Spring Boot backend...
mkdir grammar-pulse-api
cd grammar-pulse-api

:: Initialize Spring Boot project using Spring Initializr
echo Generating Spring Boot project...
curl -o grammar-pulse-api.zip -X GET "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.4.3&baseDir=grammar-pulse-api&groupId=com.grammarpulse&artifactId=grammar-pulse-api&name=grammar-pulse-api&description=Grammar+Pulse+Backend&packageName=com.grammarpulse.api&dependencies=web,websocket,data-jpa,devtools"

:: Extract Spring Boot project
tar -xf grammar-pulse-api.zip
del grammar-pulse-api.zip

:: Initialize Git for backend
git init

echo GrammarPulse project setup completed!
echo Navigate to 'grammar-pulse-ui' for frontend and 'grammar-pulse-api' for backend.

:: Open in VS Code (if installed)
if exist "C:\Program Files\Microsoft VS Code\Code.exe" (
    echo Opening in VS Code...
    code ..
)

exit
