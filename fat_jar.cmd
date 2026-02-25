@echo off
setlocal

rem --- Configuraciones ---
set JAR=jar
set MAIN_JAR=dist\ParkingCentrico24_7.jar
set LIB_JAR=libs\jdatepicker-1.3.4.jar
set OUTPUT_JAR=ParkingCentrico24_7_FAT.jar
set MAIN_CLASS=parkingcentrico24_7.Main

rem --- Limpiar carpeta temporal ---
rd /s /q tempjar 2>nul
mkdir tempjar

rem --- Extraer JAR principal ---
cd tempjar
"%JAR%" -xf ..\%MAIN_JAR%

rem --- Extraer librería externa ---
"%JAR%" -xf ..\%LIB_JAR%
cd ..

rem --- Crear JAR unificado ---
"%JAR%" cfe %OUTPUT_JAR% %MAIN_CLASS% -C tempjar .

rem --- Limpiar ---
rd /s /q tempjar

echo.
echo ✅ FAT JAR generado: %OUTPUT_JAR%
pause
