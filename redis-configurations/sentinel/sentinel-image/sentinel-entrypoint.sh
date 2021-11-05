#!/bin/sh

echo "replacing environment variables in /redis/sentinel.conf file..."

sed -i "s/SENTINEL_LOG_FILE/$SENTINEL_LOG_FILE/g" /redis/sentinel.conf
sed -i "s/SENTINEL_IP_MASTER/$SENTINEL_IP_MASTER/g" /redis/sentinel.conf
sed -i "s/SENTINEL_QUORUM/$SENTINEL_QUORUM/g" /redis/sentinel.conf
sed -i "s/SENTINEL_DOWN_AFTER/$SENTINEL_DOWN_AFTER/g" /redis/sentinel.conf
sed -i "s/SENTINEL_FAILOVER/$SENTINEL_FAILOVER/g" /redis/sentinel.conf
sed -i "s/SENTINEL_MASTER_NAME/$SENTINEL_MASTER_NAME/g" /redis/sentinel.conf

echo "Starting sentinel with master ip $SENTINEL_IP_MASTER..."

redis-server /redis/sentinel.conf --sentinel