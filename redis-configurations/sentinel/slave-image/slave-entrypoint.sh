#!/bin/sh

echo "replacing environment variables in /redis/slave.conf file..."

sed -i "s/SLAVE_LOG_FILE/$SLAVE_LOG_FILE/g" /redis/slave.conf
sed -i "s/SLAVE_IP_MASTER/$SLAVE_IP_MASTER/g" /redis/slave.conf

echo "starting slave with master ip $SLAVE_IP_MASTER..."

redis-server /redis/slave.conf