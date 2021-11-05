FROM redis:6.2.5

ENV SLAVE_LOG_FILE "/redis/data/redis-slave.log"
ENV SLAVE_IP_MASTER 127.0.0.1

RUN mkdir -p /redis
WORKDIR /redis

RUN mkdir -p data && \
    touch /redis/data/redis-slave.log && \
    chmod +w /redis/data/redis-slave.log

COPY slave.conf .
COPY slave-entrypoint.sh .

RUN chown redis:redis /redis/* && \
    chmod +x /redis/slave-entrypoint.sh

EXPOSE 6379

ENTRYPOINT ["/redis/slave-entrypoint.sh"]