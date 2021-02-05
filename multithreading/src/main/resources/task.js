// 引用PolyglotExchanger类
const polyglotExchanger = Java.type('idealworld.train.graalvm.multithreading.PolyglotExchanger')

// 订阅事件
polyglotExchanger.consumer(event => {
    // 获取到订阅数据
    let data = JSON.parse(event.body())
    let funName = data.funName
    let args = data.args
    // 此处可以实现要处理的逻辑
    // 这里使用http调用逻辑为示例
    polyglotExchanger.http('GET', 'http://127.0.0.1/s?fun=' + funName + '&args=' + args, null, null, resp => {
        // 处理完成后执行回调函数返回结果，这里返回的是http调用的结果
        event.reply(resp)
    })
})
