package controllers

import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._
import services.{JsonMergeService, UserService}

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents,
                               val userService: UserService) extends BaseController {
  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index(id: Option[Int]): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val userFuture = userService.loadUser(id)
    val userCommentsFuture = userService.loadUserComments(id)

    for {
      userJson <- userFuture
      userCommentsJson <- userCommentsFuture
    } yield {
      val mergedJson = JsonMergeService.merge(userJson, userCommentsJson, Option("comments"))
      Ok(views.html.index(Json.prettyPrint(mergedJson)))
    }
  }
}
