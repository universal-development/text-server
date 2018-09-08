#!/bin/bash

dir=`pwd`
docker run  --rm openjdk:10 -v $dir:/app /app/gradlew clean build