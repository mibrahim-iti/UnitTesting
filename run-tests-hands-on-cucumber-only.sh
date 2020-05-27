#!/bin/bash
# This is a basic bash script.

flashredtput=$(tput setab 2; tput setaf 3; tput bold; tput blink)

echo -e $flashredtput'cd ./HandsOnCucumber ...'
cd ./HandsOnCucumber

echo -e $flashredtput'Running all tests for HandsOnCucumber now...'
#gradle clean cucumber
gradle clean cucumber
