### Steps

```bash
docker-compose -f=./docker-compose.yaml up -d --build
docker-compose -f=./docker-compose-dev.yaml up -d --build

docker-compose down
```