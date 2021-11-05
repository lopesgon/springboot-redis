FROM redis:6.2.5

ENV IP_MASTER=127.0.0.1 \
    DEFAULT_PASSWORD="" \
    RESOLVE_HOSTNAMES=yes \
    SLAVE_LOG_FILE=/redis/data/redis-slave.log \
    SENTINEL_LOG_FILE=/redis/data/redis-sentinel.log \
    SENTINEL_QUORUM=2 \
    SENTINEL_DOWN_AFTER=2000 \
    SENTINEL_FAILOVER=2000 \
    SENTINEL_MASTER_NAME=mymaster

RUN mkdir -p /redis
WORKDIR /redis

COPY slave.conf sentinel.conf entrypoint.sh ./

RUN mkdir -p data && \
    touch ./data/redis-slave.log && \
    chmod +w ./data/redis-slave.log && \
    touch ./data/redis-sentinel.log && \
    chmod +w ./data/redis-sentinel.log && \
    chown redis:redis ./* && \
    chmod +x ./entrypoint.sh

EXPOSE 6379 26379

ENTRYPOINT ["/redis/entrypoint.sh"]