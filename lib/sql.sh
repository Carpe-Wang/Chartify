
#!/bin/bash
# This script will create a 'chartify_user' table in the specified PostgreSQL database.

# Variables - These should be modified according to your PostgreSQL setup
DB_HOST="localhost"  # Hostname for your PostgreSQL server
DB_PORT="5432"       # Port for your PostgreSQL server
DB_NAME="your_database_name"  # The name of the database where the table should be created
DB_USER="your_username"  # The username used to connect to the database
DB_PASSWORD="your_password"  # The password for the database user

# SQL command to create the 'chartify_user' table
SQL_COMMAND="
create table chartify.chartify_user
(
    id           serial
        primary key,
    username     varchar(255) not null,
    email        varchar(255) not null,
    password     varchar(255) not null,
    created_time timestamp with time zone default CURRENT_TIMESTAMP,
    updated_time timestamp with time zone default CURRENT_TIMESTAMP,
    user_id      varchar(100) not null,
    unique (username, email, user_id)
);
"

# Export password to avoid the prompt by psql
export PGPASSWORD=\$DB_PASSWORD

# Execute the SQL command
psql -h \$DB_HOST -p \$DB_PORT -U \$DB_USER -d \$DB_NAME -c "\$SQL_COMMAND"

# Unset the password for security
unset PGPASSWORD
