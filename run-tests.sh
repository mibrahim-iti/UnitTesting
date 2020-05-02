#!/bin/bash
# This is a basic bash script.

flashredtput=$(tput setab 2; tput setaf 3; tput bold; tput blink)

echo -e $flashredtput'Running dateTime tag tests only...'
gradle clean dateTimeTest

echo -e $flashredtput'Running all tests now...'
gradle clean test
