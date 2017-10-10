@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  Notepad startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and NOTEPAD_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\google-cloud-dns-0.25.0-alpha.jar;%APP_HOME%\lib\google-cloud-language-0.25.0-beta.jar;%APP_HOME%\lib\jackson-core-asl-1.9.11.jar;%APP_HOME%\lib\google-cloud-vision-0.25.0-beta.jar;%APP_HOME%\lib\datastore-v1-protos-1.3.0.jar;%APP_HOME%\lib\grpc-protobuf-1.6.1.jar;%APP_HOME%\lib\google-cloud-speech-0.25.0-alpha.jar;%APP_HOME%\lib\grpc-protobuf-lite-1.6.1.jar;%APP_HOME%\lib\commons-fileupload-1.3.2.jar;%APP_HOME%\lib\netty-resolver-4.1.14.Final.jar;%APP_HOME%\lib\protobuf-java-util-3.3.1.jar;%APP_HOME%\lib\commons-codec-1.3.jar;%APP_HOME%\lib\sphinx4-core-5prealpha-SNAPSHOT.jar;%APP_HOME%\lib\netty-codec-http-4.1.14.Final.jar;%APP_HOME%\lib\google-http-client-jackson2-1.22.0.jar;%APP_HOME%\lib\grpc-google-cloud-spanner-v1-0.1.20.jar;%APP_HOME%\lib\proto-google-cloud-spanner-admin-instance-v1-0.1.20.jar;%APP_HOME%\lib\jackson-core-2.1.3.jar;%APP_HOME%\lib\netty-common-4.1.14.Final.jar;%APP_HOME%\lib\netty-handler-4.1.14.Final.jar;%APP_HOME%\lib\sphinx4-data-5prealpha-SNAPSHOT.jar;%APP_HOME%\lib\guava-20.0.jar;%APP_HOME%\lib\threetenbp-1.3.3.jar;%APP_HOME%\lib\opencensus-api-0.5.1.jar;%APP_HOME%\lib\grpc-google-longrunning-v1-0.1.20.jar;%APP_HOME%\lib\proto-google-cloud-speech-v1-0.1.20.jar;%APP_HOME%\lib\datastore-v1-proto-client-1.3.0.jar;%APP_HOME%\lib\gax-1.8.1.jar;%APP_HOME%\lib\proto-google-cloud-vision-v1-0.1.20.jar;%APP_HOME%\lib\netty-codec-4.1.14.Final.jar;%APP_HOME%\lib\netty-tcnative-boringssl-static-2.0.3.Final.jar;%APP_HOME%\lib\proto-google-cloud-logging-v2-0.1.20.jar;%APP_HOME%\lib\hamcrest-core-1.3.jar;%APP_HOME%\lib\json-20160810.jar;%APP_HOME%\lib\google-cloud-core-grpc-1.7.0.jar;%APP_HOME%\lib\instrumentation-api-0.4.3.jar;%APP_HOME%\lib\proto-google-cloud-monitoring-v3-0.1.20.jar;%APP_HOME%\lib\google-cloud-compute-0.25.0-alpha.jar;%APP_HOME%\lib\google-cloud-resourcemanager-0.25.0-alpha.jar;%APP_HOME%\lib\google-cloud-core-http-1.7.0.jar;%APP_HOME%\lib\gax-grpc-0.25.1.jar;%APP_HOME%\lib\google-cloud-core-1.7.0.jar;%APP_HOME%\lib\gson-2.7.jar;%APP_HOME%\lib\google-cloud-0.25.0-alpha.jar;%APP_HOME%\lib\auto-value-1.2.jar;%APP_HOME%\lib\commons-math3-3.2.jar;%APP_HOME%\lib\api-common-1.1.0.jar;%APP_HOME%\lib\google-cloud-spanner-0.25.0-beta.jar;%APP_HOME%\lib\grpc-google-cloud-spanner-admin-instance-v1-0.1.20.jar;%APP_HOME%\lib\proto-google-cloud-trace-v1-0.1.20.jar;%APP_HOME%\lib\google-auth-library-oauth2-http-0.8.0.jar;%APP_HOME%\lib\google-api-services-bigquery-v2-rev347-1.22.0.jar;%APP_HOME%\lib\google-http-client-appengine-1.22.0.jar;%APP_HOME%\lib\google-auth-library-credentials-0.8.0.jar;%APP_HOME%\lib\proto-google-cloud-speech-v1beta1-0.1.20.jar;%APP_HOME%\lib\proto-google-cloud-language-v1beta2-0.1.20.jar;%APP_HOME%\lib\grpc-google-cloud-spanner-admin-database-v1-0.1.20.jar;%APP_HOME%\lib\netty-buffer-4.1.14.Final.jar;%APP_HOME%\lib\google-http-client-1.22.0.jar;%APP_HOME%\lib\google-api-services-compute-v1-rev103-1.21.0.jar;%APP_HOME%\lib\protobuf-java-3.3.1.jar;%APP_HOME%\lib\google-cloud-translate-1.7.0.jar;%APP_HOME%\lib\google-api-services-translate-v2-rev47-1.22.0.jar;%APP_HOME%\lib\proto-google-cloud-language-v1-0.1.20.jar;%APP_HOME%\lib\proto-google-common-protos-0.1.20.jar;%APP_HOME%\lib\google-api-client-1.22.0.jar;%APP_HOME%\lib\proto-google-cloud-pubsub-v1-0.1.20.jar;%APP_HOME%\lib\proto-google-cloud-spanner-admin-database-v1-0.1.20.jar;%APP_HOME%\lib\google-cloud-bigquery-0.25.0-beta.jar;%APP_HOME%\lib\junit-4.12.jar;%APP_HOME%\lib\grpc-google-cloud-pubsub-v1-0.1.20.jar;%APP_HOME%\lib\grpc-netty-1.6.1.jar;%APP_HOME%\lib\google-cloud-storage-1.7.0.jar;%APP_HOME%\lib\proto-google-longrunning-v1-0.1.20.jar;%APP_HOME%\lib\commons-logging-1.1.1.jar;%APP_HOME%\lib\google-api-services-storage-v1-rev108-1.22.0.jar;%APP_HOME%\lib\grpc-context-1.6.1.jar;%APP_HOME%\lib\joda-time-2.9.2.jar;%APP_HOME%\lib\google-cloud-monitoring-0.25.0-alpha.jar;%APP_HOME%\lib\google-api-services-dns-v1-rev7-1.21.0.jar;%APP_HOME%\lib\error_prone_annotations-2.0.19.jar;%APP_HOME%\lib\httpclient-4.0.1.jar;%APP_HOME%\lib\proto-google-cloud-error-reporting-v1beta1-0.1.20.jar;%APP_HOME%\lib\google-cloud-trace-0.25.0-alpha.jar;%APP_HOME%\lib\netty-transport-4.1.14.Final.jar;%APP_HOME%\lib\netty-handler-proxy-4.1.14.Final.jar;%APP_HOME%\lib\httpcore-4.0.1.jar;%APP_HOME%\lib\google-http-client-jackson-1.22.0.jar;%APP_HOME%\lib\netty-codec-socks-4.1.14.Final.jar;%APP_HOME%\lib\grpc-core-1.6.1.jar;%APP_HOME%\lib\google-cloud-pubsub-0.25.0-beta.jar;%APP_HOME%\lib\google-cloud-datastore-1.7.0.jar;%APP_HOME%\lib\proto-google-cloud-spanner-v1-0.1.20.jar;%APP_HOME%\lib\Notepad.jar;%APP_HOME%\lib\google-cloud-logging-1.7.0.jar;%APP_HOME%\lib\jsr305-3.0.0.jar;%APP_HOME%\lib\grpc-google-common-protos-0.1.20.jar;%APP_HOME%\lib\grpc-stub-1.6.1.jar;%APP_HOME%\lib\commons-io-2.2.jar;%APP_HOME%\lib\netty-codec-http2-4.1.14.Final.jar;%APP_HOME%\lib\google-cloud-errorreporting-0.25.0-alpha.jar;%APP_HOME%\lib\google-oauth-client-1.22.0.jar;%APP_HOME%\lib\grpc-auth-1.6.1.jar;%APP_HOME%\lib\google-api-services-cloudresourcemanager-v1beta1-rev10-1.21.0.jar;%APP_HOME%\lib\proto-google-iam-v1-0.1.20.jar;%APP_HOME%\lib\google-http-client-protobuf-1.20.0.jar

@rem Execute Notepad
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %NOTEPAD_OPTS%  -classpath "%CLASSPATH%" notepad.src.main.java.code.Main %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable NOTEPAD_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%NOTEPAD_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
