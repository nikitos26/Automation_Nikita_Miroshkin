FROM markhobson/maven-chrome:jdk-11

WORKDIR /app
COPY . /app

CMD ["mvn", "clean", "install"]