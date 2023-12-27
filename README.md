### Steps

#### For local development, only run mysql

```bash
docker-compose -f=./docker-compose-local.yaml up -d --build
docker-compose -f=./docker-compose-local.yaml down
```

#### For dev deployment, use host port as mysql connection
```bash
docker-compose -f=./docker-compose-dev.yaml up -d --build
docker-compose -f=./docker-compose-dev.yaml down
```

#### For test deployment, use docker network as mysql connection
```bash
docker-compose -f=./docker-compose-test.yaml up -d --build
docker-compose -f=./docker-compose-test.yaml down
```

#### For k8s deployment, use k8s service as mysql connection

If use in mac local, need set local kubernetes cluster
```bash
brew install docker
docker build --build-arg="PROFILE=test" -t zxkfall/k8s-backend-image:latest .
docker push zxkfall/k8s-backend-image:latest

brew install minikube
brew install hyperkit
brew install kubectl
brew install helm
minikube start --driver=hyperkit
minikube dashboard

minikube service springboot-k8s-istio-backend

minikube stop
```

```bash
helm package helm
helm install springboot-k8s-istio helm-0.1.0.tgz
helm upgrade springboot-k8s-istio helm-0.1.0.tgz
helm uninstall springboot-k8s-istio
```





```bash
docker-compose -f=./docker-compose-test.yaml up -d --build
docker-compose -f=./docker-compose-dev.yaml up -d --build
docker-compose -f=./docker-compose-local.yaml up -d --build

docker-compose down
docker-compose -f=./docker-compose-local.yaml down
docker-compose -f=./docker-compose-test.yaml down
docker-compose -f=./docker-compose-dev.yaml down
```

```bash
docker build --build-arg="PROFILE=test" -t zxkfall/k8s-backend-image:latest .
docker push zxkfall/k8s-backend-image:latest

minikube delete
minikube start --driver=virtualbox
minikube dashboard

brew install hyperkit
minikube start --driver=hyperkit
minikube dashboard

helm package helm
helm install springboot-k8s-istio helm-0.1.0.tgz
helm upgrade springboot-k8s-istio helm-0.1.0.tgz

helm uninstall springboot-k8s-istio

minikube service springboot-k8s-istio-backend

kubectl describe pod POD_NAME
kubectl exec 'springboot-k8s-istio-backend-6b4bb865d7-mrbnr' -- printenv
kubectl exec -it 'springboot-k8s-istio-backend-7747b4c9c9-dxj9p' -- /bin/bash
kubectl exec -it 'springboot-k8s-istio-backend-6f9d8575d6-mvt22' -- /bin/sh

springboot-k8s-istio-backend-685c959796-ldcdf
kubectl describe pod springboot-k8s-istio-backend-7cc44bc554-c2wqm
kubectl config view --minify --output 'jsonpath={..namespace}'
kubectl get svc

nslookup springboot-k8s-istio-mysql.default.svc.cluster.local

jdbc:mysql://springboot-k8s-istio-mysql.default.svc.cluster.local:3306/employee?useUnicode=true&characterEncoding=utf8&useSSL=false

kubectl exec -it 'springboot-k8s-istio-backend-7cc44bc554-c2wqm' -- nslookup springboot-k8s-istio-mysql.default.svc.cluster.local

/ # java -jar app.jar --spring.profiles.active=test --spring.datasource.url=jdbc:mysql://springboot-k8s-istio-mysql.default.svc.cluster.local:3306/employee?useUnicode=true&characterEncoding=utf8&useSSL=false

```