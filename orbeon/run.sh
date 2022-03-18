#!/usr/bin/env bash
set -e
set -o pipefail

cd $APP_PATH

export ORBEON_RESOURCES=/opt/orbeon/resources/

replace_env_vars() {
    echo "Replacing env variables in :log4j.xml, properties-local.xml, context.xml:"
    # replace config variables with environment specifics
    # log4j.xml, properties-local.xml, context.xml
    envsubst < ./resources/context.xml > ${ORBEON_RESOURCES}/context.xml
    envsubst < ./resources/context.xml > ${CATALINA_HOME}/conf/Catalina/localhost/orbeon.xml
    envsubst < ./resources/config/log4j.xml > ${ORBEON_RESOURCES}/config/log4j.xml
    envsubst < ./resources/config/properties-local.xml > ${ORBEON_RESOURCES}/config/properties-local.xml
    envsubst < ./resources/config/form-builder-permissions.xml > ${ORBEON_RESOURCES}/config/form-builder-permissions.xml
}

replace_env_vars
echo "================Finished Replacing Envs deploy=========================="

cd $CATALINA_HOME

bash -c "catalina.sh run"

exit 0
