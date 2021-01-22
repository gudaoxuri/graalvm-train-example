/// <reference types="@vertx/core" />
// @ts-check

vertx.createHttpServer()
    .requestHandler(req => {
        req.response()
            .end('Hello ES4X')
    })
    .listen(8080)

console.log('Server listening at: http://localhost:8080/')
