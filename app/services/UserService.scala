package services

import javax.inject.Inject
import play.api.libs.json.JsValue
import play.api.libs.ws.WSClient

import scala.concurrent.Future

class UserService @Inject()(ws: WSClient, apiService: ApiService) {

  def getUser: Future[JsValue] = {
    val url = "http://jsonplaceholder.typicode.com/users/1"
    apiService.sendRequest(url)
  }

  def getUserComments: Future[JsValue] = {
    val url = "http://jsonplaceholder.typicode.com/posts?userId=1"
    apiService.sendRequest(url)
  }

}
