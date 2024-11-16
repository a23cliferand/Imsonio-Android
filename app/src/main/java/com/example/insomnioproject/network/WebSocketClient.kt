import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class WebSocketClient(private val url: String, private val listener: WebSocketEventListener) {

    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null

    // Inicia la conexión al servidor WebSocket
    fun connect() {
        val request = Request.Builder().url(url).build()
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
                listener.onOpen()
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                listener.onMessage(text)
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                listener.onMessage(bytes.utf8())
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                listener.onClosing(code, reason)
                webSocket.close(code, reason)
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                listener.onClosed(code, reason)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: okhttp3.Response?) {
                listener.onError(t)
            }
        })
    }

    // Envía un mensaje al servidor WebSocket
    fun sendMessage(message: String) {
        webSocket?.send(message)
    }

    // Cierra la conexión al servidor WebSocket
    fun close(code: Int = 1000, reason: String = "Closing") {
        webSocket?.close(code, reason)
    }

    interface WebSocketEventListener {
        fun onOpen()
        fun onMessage(message: String)
        fun onClosing(code: Int, reason: String)
        fun onClosed(code: Int, reason: String)
        fun onError(error: Throwable)
    }
}
