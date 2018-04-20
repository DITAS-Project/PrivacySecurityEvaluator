ssh -i /opt/keypairs/ditas-testbed-keypair.pem cloudsigma@31.171.247.162 << 'ENDSSH'
sudo docker rm -f ditas/privacy-security-evaluator || true
sudo docker pull ditas/ditas/privacy-security-evaluator:latest
sudo docker run -p 50008:8080 -d --name ditas/privacy-security-evaluator ditas/ditas/privacy-security-evaluator:latest
ENDSSH
