FROM jimschubert/8-jdk-alpine-mvn

LABEL Description="A simple calculator for an unusual chess"

COPY . h2mcalculator/
WORKDIR h2mcalculator
RUN mvn clean package

ENTRYPOINT java -jar target/h2m_calc-1.0-SNAPSHOT.jar
