#!/bin/bash
# This is a basic bash script.

flashredtput=$(tput setab 2; tput setaf 3; tput bold; tput blink)

echo -e $flashredtput'cd ./TDDwithJUnit5 ...'
cd ./TDDwithJUnit5

echo -e $flashredtput'Running all tests for TDDwithJunit5 now...'
#gradle clean build jacocoTestReport
gradle clean test jacocoTestReport
