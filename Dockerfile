FROM 8-jdk-alpine

LABEL Description="A simple calculator for an unusual chess"

COPY  target/h2m_calc-1.0-SNAPSHOT.jar         h2m_calc-1.0-SNAPSHOT.jar

ENTRYPOINT java -jar h2m_calc-1.0-SNAPSHOT.jar