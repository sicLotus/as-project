package services

import javax.inject.Inject
import play.api.libs.json.JsValue

import scala.concurrent.Future

class UserService @Inject()(apiService: ApiService) {
  val DefaultUserId = 1

  def loadUser(id:Option[Int]): Future[JsValue] = {
    val url = "http://jsonplaceholder.typicode.com/users/" + id.getOrElse(DefaultUserId)
    apiService.sendRequest(url)
  }

  def loadUserComments(id:Option[Int]): Future[JsValue] = {
    val url = "http://jsonplaceholder.typicode.com/posts?userId=" + id.getOrElse(DefaultUserId)
    apiService.sendRequest(url)
  }

}
