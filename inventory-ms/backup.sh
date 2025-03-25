#!/bin/bash

CONTAINER_NAME="inventory-db"
DB_USER="blandoncj"
DB_PASSWORD="Oncenacio16"
DB_NAME="inventoryms"
BACKUP_DIR="./backups"
DATE=$(date +"%Y-%m-%d_%H-%M-%S")
BACKUP_FILE="$BACKUP_DIR/backup_$DATE.sql"

# create directory if not exists
mkdir -p $BACKUP_DIR

echo "📦 Generating backup of the database..."

# exec mysqldump on the container
docker exec $CONTAINER_NAME mariadb-dump -u $DB_USER -p$DB_PASSWORD $DB_NAME >$BACKUP_FILE

if [ $? -eq 0 ]; then
  echo "✅ Backup generated successfully"
else
  echo "❌ An error occurred while generating the backup"
fi
