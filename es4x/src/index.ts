/// <reference types="@vertx/core" />
// @ts-check

import { Router } from '@vertx/web';

const app = Router.router(vertx);
const $ = Java.type('com.ecfront.dew.common.$');

app.route('/').handler(ctx => {
    ctx.response()
        .end($.security.digest.digest('Hello from Vert.x Web!','MD5'));
});

vertx.createHttpServer()
    .requestHandler(app)
    .listen(8080);

console.log('Server listening at: http://localhost:8080/')
