package services

import javax.inject.Inject
import play.api.libs.json.JsValue
import play.api.libs.ws.WSClient

import scala.concurrent.Future

class UserService @Inject()(ws: WSClient, apiService: ApiService) {
  val DefaultUserId = 1

  def getUser(id:Option[Int]): Future[JsValue] = {
    val url = "http://jsonplaceholder.typicode.com/users/" + id.getOrElse(DefaultUserId)
    apiService.sendRequest(url)
  }

  def getUserComments(id:Option[Int]): Future[JsValue] = {
    val url = "http://jsonplaceholder.typicode.com/posts?userId=" + id.getOrElse(DefaultUserId)
    apiService.sendRequest(url)
  }

}
