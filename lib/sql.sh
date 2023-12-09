#!/bin/bash
# 此脚本将在指定的PostgreSQL数据库中创建一个名为 'chartify_user' 的表格。

# 这些应根据你的PostgreSQL设置进行修改
DB_HOST="localhost"  # 你PostgreSQL服务器的主机名
DB_PORT="5432"       # 你PostgreSQL服务器的端口
DB_NAME="your_database_name"  # 应创建表格的数据库名称
DB_USER="your_username"  # 用于连接数据库的用户名
DB_PASSWORD="your_password"  # 数据库用户的密码

# 创建 'chartify_user' 表格的SQL命令
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

# 导出密码以避免psql的提示
export PGPASSWORD=\$DB_PASSWORD

# 执行SQL命令
psql -h \$DB_HOST -p \$DB_PORT -U \$DB_USER -d \$DB_NAME -c "\$SQL_COMMAND"

# 取消设置密码以增强安全性
unset PGPASSWORD
