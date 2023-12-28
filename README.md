### Steps

#### For local development, only run mysql(DockerCompose Mysql)

```bash
docker-compose -f=./docker-compose-local.yaml up -d --build
docker-compose -f=./docker-compose-local.yaml down
```

#### For dev deployment, use host port as mysql connection(DockerCompose Mysql and backend external network)
```bash
docker-compose -f=./docker-compose-dev.yaml up -d --build
docker-compose -f=./docker-compose-dev.yaml down
```

#### For test deployment, use docker network as mysql connection(DockerCompose Mysql and backend internal network)

```bash
docker-compose -f=./docker-compose-test.yaml up -d --build
docker-compose -f=./docker-compose-test.yaml down
```

#### For k8s deployment, use k8s service as mysql connection

If use in mac local, need set local kubernetes cluster

##### Prepare Local Environment

```bash
brew install colima
brew install docker
brew install docker-compose

colima start

docker login -u <username> -p <password>
docker build --build-arg="PROFILE=test" --build-arg="PORT=8080" -t zxkfall/k8s-backend-image:latest .
docker push zxkfall/k8s-backend-image:latest

brew install minikube
brew install hyperkit
brew install kubectl
brew install helm

minikube start --driver=hyperkit
minikube dashboard
```

##### Deploy to k8s

```bash
helm package helm-for-k8s
helm install -n=istio-system springboot-k8s-istio helm-for-k8s-0.1.0.tgz
helm upgrade -n=istio-system springboot-k8s-istio helm-for-k8s-0.1.0.tgz

minikube service springboot-k8s-istio-backend # expose service
```

##### Clean up

```bash
helm uninstall -n=istio-system springboot-k8s-istio
minikube stop # stop minikube
colima stop # stop colima

```

#### Integrate with Istio

First, install istio to k8s, see:
[PrepareEnvironment](./README.md#prepare-local-environment)
Then, install istio to k8s:
```bash
brew install istioctl # helm install has not recommended
istioctl install # install istio to k8s
kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.20/samples/addons/grafana.yaml
kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.20/samples/addons/prometheus.yaml
kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.20/samples/addons/kiali.yaml # 图形化界面
```

Deploy to k8s, see:
```bash
helm package helm-for-k8s-istio
helm install -n=istio-system springboot-k8s-istio-api-gateway helm-for-k8s-istio-0.1.0.tgz
```

Open kiali dashboard:
```bash
istioctl dashboard kiali
```

Enable LoadBalancer:
```bash
minikube tunnel
```










Istio

```bash
brew install istioctl # helm install has not recommended
istioctl install

kubectl get pods -n istio-system
kubectl get svc -A
minikube service istio-ingressgateway -n istio-system

kubectl -n istio-system get deploy
kubectl -n istio-system get svc

$ istioctl uninstall --purge


kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.20/samples/addons/grafana.yaml
kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.20/samples/addons/prometheus.yaml

kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.20/samples/addons/kiali.yaml # 图形化界面



istioctl dashboard kiali


curl -s -I -HHost:httpbin.example.com "http://10.108.150.180:80"
curl -s -I -HHost:httpbin.example.com "http://$INGRESS_HOST:$INGRESS_PORT/status/200"
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
helm install -n=istio-system springboot-k8s-istio helm-0.1.0.tgz
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



export INGRESS_HOST=$(kubectl -n "istio-system" get service "istio-ingressgateway" -o jsonpath='{.status.loadBalancer.ingress[0].ip}')
export INGRESS_PORT=$(kubectl -n "istio-system" get service "istio-ingressgateway" -o jsonpath='{.spec.ports[?(@.name=="http2")].port}')
export SECURE_INGRESS_PORT=$(kubectl -n "$INGRESS_NS" get service "$INGRESS_NAME" -o jsonpath='{.spec.ports[?(@.name=="https")].port}')
export TCP_INGRESS_PORT=$(kubectl -n "$INGRESS_NS" get service "$INGRESS_NAME" -o jsonpath='{.spec.ports[?(@.name=="tcp")].port}')

```