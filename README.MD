    db:
        build:
            context: ./db
            target: dev
        depend_on:
        healthcheck:
        volumes:
        ports:
        newtworks:
    backend:
        build:
            context: ./backend
            target: dev
        depend_on:
        healthcheck:
        volumes:
        ports:
        networks:
    frontend:
        build:
            context: ./frontend
            target: dev
        depend_on:
        healthcheck:
        volumes:
        ports:
        networks:




    mongo-express:
        image: mongo-express
        restart: always
        depend_on:
            - mongo
        enviroment:
            ME_CONFIG_MONGODB_ADMINUSERNAME: root
            ME_CONFIG_MONGODB_ADMINPASSWORD: Password@1234
            ME_CONFIG_MONGODB_URL: mongodb://root:'Pass@1234'@mongo:27017/
            ME_CONFIG_BASICAUTH: false
        networks:
          - back-end    
    networks:
        front-end:
          driver: bridge
          external: true
          ipam:
              driver: default
              config:
                  ip_range: 172.28.0.0/27
                  getway: 172.28.0.30



version: '1.0'
services:
    mongo:
        image: mongo
        restart: always
        networks:
          - backend_net
    networks:
        backend_net:
            name: backEndNet