#!/usr/bin/env bash
set -e
set -o pipefail

cd $APP_PATH

export ENV=$ENV
export ORBEON_HOME=/opt/orbeon
export ORBEON_RESOURCES=$ORBEON_HOME/resources/

echo "Using Catalina home - $CATALINA_HOME"

replace_env_vars() {
    echo "Replacing env variables in :log4j.xml, properties-local.xml, context.xml:"
    # replace config variables with environment specifics
    # log4j.xml, properties-local.xml, context.xml
    mkdir -p  ${CATALINA_HOME}/conf/Catalina/localhost

    envsubst < ./resources/context.xml > ${ORBEON_RESOURCES}/context.xml
    envsubst < ./resources/context.xml > ${CATALINA_HOME}/conf/Catalina/localhost/orbeon.xml
    envsubst < ./resources/config/log4j.xml > ${ORBEON_RESOURCES}/config/log4j.xml
    envsubst < ./resources/config/properties-local.xml > ${ORBEON_RESOURCES}/config/properties-local.xml
}

deploy_orbeon() {
    # Orbeon, might not change that frequently
    export ORBEON_TAG_NAME=tag-release-2020.1.3-ce
    export ORBEON_ZIP_NAME=orbeon-2020.1.3.202105010041-PE
    export ORBEON_DIR_NAME=${ORBEON_HOME}/latest
    export ORBEON_DOWNLOADED_ZIP_NAME="${ORBEON_HOME}/${ORBEON_TAG_NAME}-${ORBEON_ZIP_NAME}.zip"

    echo "Copying orbeon configs from repo $(pwd) to resources dir $ORBEON_RESOURCES....."
    mkdir -p ${ORBEON_RESOURCES}
    cp -rf ./resources/* ${ORBEON_RESOURCES}/

    # Replace env variables in files
    replace_env_vars

    if [ ! -f ${CATALINA_HOME}/lib/mysql-connector-java-8.0.25.jar ]; then
        echo "Copying mysql connector to tomcat lib....."
        cp ${ORBEON_RESOURCES}/mysql-connector-java-8.0.25.jar ${CATALINA_HOME}/lib/mysql-connector-java-8.0.25.jar
    fi

    if [ ! -f ${ORBEON_DOWNLOADED_ZIP_NAME} ]; then
        echo "Downloading orbeon release to $ORBEON_DOWNLOADED_ZIP_NAME....."
        # Deploy latest orbeon if not already deployed to tomcat
        # wget1 to download VERSION file (replaces WGET1)
        if ! wget https://github.com/orbeon/orbeon-forms/releases/download/${ORBEON_TAG_NAME}/${ORBEON_ZIP_NAME}.zip -O ${ORBEON_DOWNLOADED_ZIP_NAME}; then
            echo "ERROR: can't get ORBEON" >&2
            exit 1
        fi
        echo "Unzipping orbeon release to $ORBEON_DIR_NAME....."
        unzip ${ORBEON_DOWNLOADED_ZIP_NAME} -d ${ORBEON_DIR_NAME}
    fi

    echo "Copying orbeon.war to tomcat apps....$CATALINA_HOME."
    cp ${ORBEON_DIR_NAME}/${ORBEON_ZIP_NAME}/orbeon.war ${CATALINA_HOME}/webapps/orbeon.war
}

deploy_orbeon
echo "================Finished orbeon deploy=========================="

exit 0
