
docker build -t miolivc/database-dac-jsf . -f Database

mvn package
docker build -t miolivc/app-dac-jsf . -f Application

docker run -p 5433:5432 -d --name database miolivc/database-dac-jsf
docker run -p 8081:8080 -d --name app --link database:host-database miolivc/app-dac-jsf