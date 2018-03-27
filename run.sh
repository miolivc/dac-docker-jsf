
docker build -t miolivc/database-dac-jsf . -f Database
docker run -p 5432:5432 -d --name database miolivc/database-dac-jsf