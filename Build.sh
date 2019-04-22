cd userservice
source ./env-variable.sh
mvn clean package
docker build -t user-pubg .
cd ..
cd favouriteservice
source ./env-variable.sh
mvn clean package
docker build -t pubg-app .
cd ..
