from registry.saas.hand-china.com/hap-cloud/base:latest

#作者
MAINTAINER weibaoer <yanhh.@qq.com>

ADD target/ target/

ADD ./start.sh start.sh

RUN chmod a+x start.sh

ENTRYPOINT ["./start.sh"]
