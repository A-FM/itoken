rem /* service */
cd ..
cd itoken-service-redis
call mvn clean package

cd ..
cd itoken-service-sso
call mvn clean package

cd ..
cd itoken-service-admin
call mvn clean package

cd ..
cd itoken-service-posts
call mvn clean package

cd ..
cd itoken-service-upload
call mvn clean package

rem /* web */
cd ..
cd itoken-web-admin
call mvn clean package

cd ..
cd itoken-web-posts
call mvn clean package

cd ..
cd itoken-web-backend
call mvn clean package


cd ..
cd itoken-zuul
call mvn clean package