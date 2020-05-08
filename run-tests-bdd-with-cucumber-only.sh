#!/bin/bash
# This is a basic bash script.

flashredtput=$(tput setab 2; tput setaf 3; tput bold; tput blink)

echo -e $flashredtput'cd ./BDDWithCucumber ...'
cd ./BDDWithCucumber

echo -e $flashredtput'Running all tests for BDDWithCucumber now...'
#gradle clean cucumber
gradle clean cucumber jacocoTestReport
