#!/bin/bash

# exit on error
set -e

# image name in Docker Hub
IMAGE_NAME="blandoncj/inventory-api:latest"

echo "📦 Building the image for the API..."
docker build -t $IMAGE_NAME ./backend/ || {
  echo "❌ Build failed!"
  exit 1
}

echo "⬆️ Uploading the image to Docker Hub..."
docker push $IMAGE_NAME || {
  echo "❌ Push failed!"
  exit 1
}

echo "🚀 Starting the containers..."
docker compose up -d --build || {
  echo "❌ Failed to start containers!"
  exit 1
}

echo "✅ Everything is ready!"
