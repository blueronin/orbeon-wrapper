FROM openjdk:11
ENV ENV=local PROFILE=local APP_PATH=/usr/app
EXPOSE 9090

# Get gradle distribution
COPY *.gradle *.gradle.kts gradlew gradlew.bat $APP_PATH/
COPY gradle $APP_PATH/gradle/
WORKDIR $APP_PATH
RUN ./gradlew --version

# Copy the project source
COPY . .

RUN chmod 755 ./gradlew*

RUN ./gradlew --no-daemon -Pprofile=$PROFILE clean bootJar

ARG JAR_NAME=orbeon-wrapper-0.0.1-$PROFILE.jar

ENV JAR_NAME=build/libs/${JAR_NAME}

RUN chmod 755 ./**/*.sh
CMD ./docker/run.sh
