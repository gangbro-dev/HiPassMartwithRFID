FROM node:16.15.0 as build-stage
WORKDIR /var/jenkins_home/workspace/deploy/front
COPY ./shopmobile .
RUN npm install
RUN npm run build

FROM node:16.15.0 as kiosk
WORKDIR /var/jenkins_home/workspace/deploy/kiosk
COPY kiosk/package.json .
COPY kiosk/package-lock.json .
RUN npm install
COPY kiosk/public/ ./public
COPY kiosk/src/ ./src
RUN npm run build


FROM nginx:stable-alpine as production-stage

COPY --from=build-stage /var/jenkins_home/workspace/deploy/front/build /usr/share/nginx/html
COPY --from=kiosk /var/jenkins_home/workspace/deploy/kiosk/build /var/www/html/front/kiosk
COPY --from=build-stage /var/jenkins_home/workspace/deploy/front/deploy_conf/nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g","daemon off;"]