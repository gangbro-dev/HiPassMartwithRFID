FROM node:16.15.0 as build-stage
WORKDIR /var/jenkins_home/workspace/deploytest/kiosk
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

EXPOSE 81
CMD ["npm" "start"]