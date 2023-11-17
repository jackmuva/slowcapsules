#!/bin/sh

echo "Pre-Build Steps:"
echo "authenticating with AWS ECR"
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 514832027284.dkr.ecr.us-east-1.amazonaws.com

echo "Build Steps:"
echo "building image..."
docker build -t 514832027284.dkr.ecr.us-east-1.amazonaws.com/slowcapsules:1 -t 514832027284.dkr.ecr.us-east-1.amazonaws.com/slowcapsules:latest .

echo "Post-Build steps:"
echo "pushing image to AWS ECR"
docker push --all-tags 514832027284.dkr.ecr.us-east-1.amazonaws.com/slowcapsules
