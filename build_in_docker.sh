#!/usr/bin/env bash

dir=`pwd`
docker run -it --rm -v "$dir:/app" openjdk:10 bash -c "cd /app && ./gradlew clean build"