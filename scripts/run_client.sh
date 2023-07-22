
# set top directory to CnC top
PROJECT_DIR=$(pwd)

bash ${PROJECT_DIR}/scripts/compile_client.sh

cd ${PROJECT_DIR}/run

java source.client.GM_client