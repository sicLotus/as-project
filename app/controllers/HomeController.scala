package controllers

import javax.inject._
import play.api.libs.ws.WSClient
import play.api.mvc._
import services.UserService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents,
                               val ws: WSClient,
                               val userService: UserService) extends BaseController {
  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val future = userService.getUser
    for (userJson <- future) yield Ok(views.html.index(userJson.toString))
  }
}
