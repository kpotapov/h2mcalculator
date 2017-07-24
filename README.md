# build
mvn clean package

#run 
java -jar target/h2m_calc-1.0-SNAPSHOT.jar

# use
## load configuration
http://localhost:32320/calc/loadgame/game.json/game-save.json

##print
http://localhost:32320/calc/print

## action
http://localhost:32320/calc/attack/First/C/Second/C
