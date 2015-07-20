FROM centos:5.11
MAINTAINER Pine Mizune <pinemz@gmail.com>
EXPOSE 80

RUN yum update -y
RUN yum install -y --nogpgcheck epel-release

# git
RUN yum install -y --nogpgcheck git

# PHP
RUN yum install -y --nogpgcheck php php-mbstring

# spawn-fcgi
RUN yum install --nogpgcheck -y spawn-fcgi
ADD conf/php-fastcgi /etc/init.d/php-fastcgi
RUN chmod +x /etc/init.d/php-fastcgi

# nginx
RUN rpm -ivh --nosignature http://nginx.org/packages/centos/5/noarch/RPMS/nginx-release-centos-5-0.el5.ngx.noarch.rpm
RUN echo "retries=0" >> /etc/yum.conf
RUN yum install -y --nogpgcheck nginx

# conf
RUN rm -f /etc/nginx/conf.d/*
ADD conf/nginx.conf /etc/nginx/conf.d/default.conf
ADD . /tmp/www
RUN rm -rf /var/www/html
RUN ln -s /tmp/www/site /var/www/html

# clean
RUN yum clean all

CMD service nginx start && \
    service php-fastcgi start && \
    /bin/bash -r

