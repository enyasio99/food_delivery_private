kubectl delete deploy message
kubectl delete service message
mvn package -B
docker build -t 879772956301.dkr.ecr.ap-southeast-1.amazonaws.com/message:latest .
docker push 879772956301.dkr.ecr.ap-southeast-1.amazonaws.com/message:latest
