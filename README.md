# build
mvn clean package

# run 
java -jar target/h2m_calc-1.0-SNAPSHOT.jar

# use
## load configuration
http://localhost:32320/calc/loadgame/game.json/game-save.json

##print
http://localhost:32320/calc/print

## action
http://localhost:32320/calc/attack/First/C/Second/C

# Docker
## build
```sudo docker  build  -t calc .```
## run
```sudo docker run -it -p 32320:32320  calc -i```
## test
```curl http://localhost:32320/calc/print```

# open swagger api
http://localhost:32320/swagger-ui.html#/
http://localhost:32320/v2/api-docs
http://localhost:32320/swagger-resources
