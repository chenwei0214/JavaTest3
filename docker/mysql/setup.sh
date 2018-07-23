#启动mysql
service mysql start
echo `service mysql status`
echo '开始导入数据....'
#导入数据
mysql < source /mysql/sakila-schema.sql
mysql < source /mysql/sakila-data.sql
echo '3.导入数据完毕....'

