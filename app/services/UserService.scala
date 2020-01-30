package services

import javax.inject.Inject
import play.api.libs.json.JsValue
import play.api.libs.ws.WSClient

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserService @Inject()(ws: WSClient) {

  def getUser: Future[JsValue] = {
    val url = "http://jsonplaceholder.typicode.com/users/1"
    val futureResult = ws.url(url).get().map { response =>
      response.json
    }
    futureResult
  }

}
