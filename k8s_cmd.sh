kubectl create namespace cloud-native-namespace
# kubectl get namespace
kubectl apply -f proj_deploy.yaml
# kubectl get deployment -n cloud-native-namespace
kubectl create -f proj_svc.yaml
# kubectl get svc -n cloud-native-namespace
