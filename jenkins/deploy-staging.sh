ssh -i /opt/keypairs/ditas-testbed-keypair.pem cloudsigma@31.171.247.162 << 'ENDSSH'
sudo docker rm -f PrivacySecurityEvaluator || true
sudo docker pull ditas/privacy-security-evaluator:latest
sudo docker run -p 50008:8080 -d --name PrivacySecurityEvaluator ditas/privacy-security-evaluator:latest
ENDSSH
