### Steps

```bash
docker-compose -f=./docker-compose-test.yaml up -d --build
docker-compose -f=./docker-compose-dev.yaml up -d --build
docker-compose -f=./docker-compose-local.yaml up -d --build

docker-compose down
```