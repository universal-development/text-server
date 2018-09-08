#!/bin/bash

dir=`pwd`
docker run -i -t  --rm openjdk:10 -v $dir:/app /app/gradlew clean build