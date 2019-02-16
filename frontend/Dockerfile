FROM node:alpine AS builder

WORKDIR /home/chat/frontend/

COPY . .

RUN npm install && \
    npm run build

FROM nginx:alpine

COPY --from=builder /home/chat/frontend/* /usr/share/nginx/html/