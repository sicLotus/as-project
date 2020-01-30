package controllers

import javax.inject._
import play.api._
import play.api.libs.ws.WSClient
import play.api.mvc._

import scala.concurrent.ExecutionContext

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents,
                               val ws: WSClient,
                               implicit val ec: ExecutionContext) extends BaseController {
  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val url = "http://jsonplaceholder.typicode.com/users/1"
    val futureResult = ws.url(url).get().map { response =>
      Ok(views.html.index(response.json.toString))
    }
    futureResult
  }
}
