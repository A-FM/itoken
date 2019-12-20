rem /* 工具项目 */
cd ..
cd itoken-common
call mvn clean package
call mvn deploy

cd ..
cd itoken-common-domain
call mvn clean package
call mvn deploy

cd ..
cd itoken-common-service
call mvn clean package
call mvn deploy

cd ..
cd itoken-common-web
call mvn clean package
call mvn deploy