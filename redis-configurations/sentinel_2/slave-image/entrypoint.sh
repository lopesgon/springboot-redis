#!/bin/sh

echo "replacing environment variables in /redis/slave.conf file..."

sed -i "s/DEFAULT_PASSWORD/$DEFAULT_PASSWORD/g" /redis/slave.conf
sed -i "s/IP_MASTER/$IP_MASTER/g" /redis/slave.conf
sed -i "s/SLAVE_LOG_FILE/$SLAVE_LOG_FILE/g" /redis/slave.conf

echo "replacing environment variables in /redis/sentinel.conf file..."

sed -i "s/DEFAULT_PASSWORD/$DEFAULT_PASSWORD/g" /redis/sentinel.conf
sed -i "s/IP_MASTER/$IP_MASTER/g" /redis/sentinel.conf
sed -i "s/RESOLVE_HOSTNAMES/$RESOLVE_HOSTNAMES/g" /redis/sentinel.conf
sed -i "s/SENTINEL_LOG_FILE/$SENTINEL_LOG_FILE/g" /redis/sentinel.conf
sed -i "s/SENTINEL_QUORUM/$SENTINEL_QUORUM/g" /redis/sentinel.conf
sed -i "s/SENTINEL_DOWN_AFTER/$SENTINEL_DOWN_AFTER/g" /redis/sentinel.conf
sed -i "s/SENTINEL_FAILOVER/$SENTINEL_FAILOVER/g" /redis/sentinel.conf
sed -i "s/SENTINEL_MASTER_NAME/$SENTINEL_MASTER_NAME/g" /redis/sentinel.conf

echo "starting slave and sentinel with master ip $IP_MASTER..."

redis-server /redis/slave.conf --daemonize yes
redis-sentinel /redis/sentinel.conf