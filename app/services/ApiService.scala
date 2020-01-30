package services

import javax.inject.Inject
import play.api.libs.json.JsValue
import play.api.libs.ws.WSClient

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ApiService @Inject()(ws: WSClient) {

  def sendRequest(url: String): Future[JsValue] = {
    val futureResult = ws.url(url).get().map { response =>
      response.json
    }
    futureResult
  }

}
