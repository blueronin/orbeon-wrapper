#!/bin/bash

echo "--------Running in env: ${ENV} with profile: ${PROFILE}--------"
export JAR_NAME=build/libs/orbeon-wrapper-0.0.1-$PROFILE.jar
cd $APP_PATH

if [[ $ENV == 'local' ]]; then
  ./gradlew -Pprofile=$PROFILE bootRun
else
  echo "Finding $JAR_NAME..."
  if [[ ! -f $JAR_NAME ]]; then
    ./gradlew -Pprofile=$PROFILE clean bootJar
  fi
  echo "========Running jar $JAR_NAME========"
  java -jar $JAR_NAME
fi
