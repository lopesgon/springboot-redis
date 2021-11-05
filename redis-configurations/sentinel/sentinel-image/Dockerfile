FROM redis:6.2.5

ENV SENTINEL_LOG_FILE "/redis/data/redis-sentinel.log"
ENV SENTINEL_IP_MASTER 127.0.0.1
ENV SENTINEL_QUORUM 2
ENV SENTINEL_DOWN_AFTER 2000
ENV SENTINEL_FAILOVER 2000
ENV SENTINEL_MASTER_NAME mymaster

RUN mkdir -p /redis
WORKDIR /redis

RUN mkdir -p data && \
    touch /redis/data/redis-sentinel.log && \
    chmod +w /redis/data/redis-sentinel.log

COPY sentinel.conf .
COPY sentinel-entrypoint.sh .

RUN chown redis:redis /redis/* && \
    chmod +x /redis/sentinel-entrypoint.sh

EXPOSE 26379

ENTRYPOINT ["/redis/sentinel-entrypoint.sh"]