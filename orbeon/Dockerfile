FROM tomcat:9

RUN apt-get update -y
RUN apt-get install nano lsof -y
RUN apt-get install gettext-base -y

LABEL maintainer="ronireiosantos@gmail.com"
ENV APP_PATH=/opt/app API_URL=http://localhost:8000/api/v1

ADD resources/config/license.xml /root/.orbeon/license.xml

RUN sed -i 's/port="8080"/port="9090"/' ${CATALINA_HOME}/conf/server.xml

EXPOSE 9090

COPY . $APP_PATH

RUN chmod 755 $APP_PATH/*.sh

RUN $APP_PATH/deploy.sh

CMD $APP_PATH/run.sh
