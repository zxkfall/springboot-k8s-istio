#!/bin/bash

# reset docker container && volumes && network

# stop docker
#docker stop $(docker ps -aq)
#docker rm -f $(docker ps -aq)
#docker volume rm $(docker volume ls -q)
#docker network rm spring-boot-k8s-istio_default

# start miniKube
#minikube start --vm-driver=virtualbox --cpus=4 --memory=8192 --disk-size=50g --insecure-registry localhost:5000

echo "================stop minikube=================="
minikube stop

# set proxy
echo "================set proxy=================="
# not work, need to config in ~/.zprofile
# see https://minikube.sigs.k8s.io/docs/handbook/vpn_and_proxy/
# for minikube proxy
#export HTTP_PROXY=http://127.0.0.1:1082
#export HTTPS_PROXY=http://127.0.0.1:1082
#export NO_PROXY=localhost,127.0.0.1,10.96.0.0/12,192.168.59.0/24,192.168.49.0/24,192.168.39.0/24

echo "==============start minikube================"
minikube start

# set platform for docker in apple silicon
echo "================set platform for docker in apple silicon=================="
#export DOCKER_DEFAULT_PLATFORM=linux/amd64
#minikube ssh 'touch ~/.bash_profile'
#minikube ssh 'echo "export DOCKER_DEFAULT_PLATFORM=linux/amd64" >> ~/.bash_profile'
#minikube ssh 'source ~/.bash_profile'
#minikube ssh 'echo $DOCKER_DEFAULT_PLATFORM'

# install istio
echo "================install istio=================="
istioctl install -y

echo "=============install grafana================"
kubectl apply -f ./istio-plugin/grafana.yaml

echo "===========install prometheus==============="
kubectl apply -f ./istio-plugin/prometheus.yaml

echo "=============install kiali=================="
kubectl apply -f ./istio-plugin/kiali.yaml # 图形化界面
#kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.20/samples/addons/jaeger.yaml # 链路追踪
#kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.20/samples/addons/extras/zipkin.yaml # 链路追踪


echo "============package project================="
helm package helm-for-k8s-istio

echo "==============deploy helm==================="
helm install -n=istio-system springboot-k8s-istio-api-gateway helm-for-k8s-istio-0.1.0.tgz

echo "============open dashboard=================="
minikube dashboard & # open dashboard in backend

echo "========================================================="

echo "Please run 'minikube service -n istio-system kiali' in another terminal to open kiali dashboard"
#minikube service -n istio-system kiali  # need run in another terminal

echo "Please run 'minikube tunnel' in another terminal to access api gateway"
#minikube tunnel # need run in another terminal

