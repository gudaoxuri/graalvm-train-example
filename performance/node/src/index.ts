import * as http from "http"

http.createServer((req, resp) => {
    resp.end('Hello Node.')
})
    .listen(8080)

console.log('Server listening at: http://localhost:8080/')
