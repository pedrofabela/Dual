FROM reg.edu-edomex.gob.mx/java/tomcat85-jre8
ENV TZ America/Mexico_City
ENV LANG es_MX.UTF-8
ENV LANGUAGE es_MX.UTF-8
ENV LC_ALL es_MX.UTF-8
RUN ["rm", "-rf", "/usr/local/tomcat/webapps/ROOT"]
ADD dist/ROOT.war /usr/local/tomcat/webapps/ROOT.war
CMD ["sysctl","-p"]
CMD ["catalina.sh", "run"]
