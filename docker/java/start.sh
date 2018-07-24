#!bin/bash
while ! nc -z ${MYSQL_IP} ${MYSQL_PORT}; do sleep 3; done
echo "${MYSQL_IP}:${MYSQL_PORT}"
java -cp target/Exam1.jar com.hand.Test