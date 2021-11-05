# A SpringBoot Messaging with Redis dive

An introduction to Redis integration with a SpringBoot application.

# Redis
## Configuration

Being able to connect to a Redis container requires to have custom security configuration, you must edit the [default configuration](https://redis.io/topics/config) as follow:

```
protected-mode no           # remove protected-mode to allow out other hosts to connect
requirepass secret-password # set default user password
```

You must comment out `bind` attribute as we must listen from outside of the container, as follow `bind 127.0.0.1`

You must uncomment `aclfile` attribute if you want to use custom users instead of `default` allowing to specify custom access rights to Redis database, see [ACL Documentation](https://redis.io/topics/acl)
