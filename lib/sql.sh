#!/bin/bash
# 此脚本将在指定的PostgreSQL数据库中创建一个名为 'chartify_user' 的表格，以及一个名为 'project' 的表格。

# 这些应根据你的PostgreSQL设置进行修改
DB_HOST="localhost"  # 你PostgreSQL服务器的主机名
DB_PORT="5432"       # 你PostgreSQL服务器的端口
DB_NAME="your_database_name"  # 应创建表格的数据库名称
DB_USER="your_username"  # 用于连接数据库的用户名
DB_PASSWORD="your_password"  # 数据库用户的密码

# 创建 'chartify_user' 表格的SQL命令
SQL_COMMAND_USER="
CREATE TABLE IF NOT EXISTS chartify_user
(
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_time TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE chartify_user ADD CONSTRAINT unique_username UNIQUE (username);
ALTER TABLE chartify_user ADD CONSTRAINT unique_email UNIQUE (email);
"

# 创建 'project' 表格的SQL命令
SQL_COMMAND_PROJECT="
CREATE TABLE IF NOT EXISTS project
(
    project_id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    view_data TEXT NOT NULL,
    created_time TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES chartify_user(id)
);
"

# 导出密码以避免psql的提示
export PGPASSWORD=\$DB_PASSWORD

# 执行SQL命令创建用户表格
psql -h \$DB_HOST -p \$DB_PORT -U \$DB_USER -d \$DB_NAME -c "\$SQL_COMMAND_USER"

# 执行SQL命令创建项目表格
psql -h \$DB_HOST -p \$DB_PORT -U \$DB_USER -d \$DB_NAME -c "\$SQL_COMMAND_PROJECT"

# 取消设置密码以增强安全性
unset PGPASSWORD
