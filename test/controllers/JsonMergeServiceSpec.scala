package controllers

import org.scalatestplus.play._
import play.api.libs.json.{JsArray, JsObject, Json}
import services.JsonMergeService

class JsonMergeServiceSpec extends PlaySpec {

  val objJson: JsObject = Json.obj()
  val arrJson: JsArray = Json.arr()

  "JsonMergeService" should {

    "merge JsObject with JsArray correctly" in {
      val actual = JsonMergeService.merge(objJson, arrJson)
      val expected = Json.obj("mergeKey" -> Json.arr())

      actual mustBe expected
    }

    "merge JsObject with JsArray and mergeKey correctly" in {
      val mergeKey = "testKey"
      val actual = JsonMergeService.merge(objJson, arrJson, Option(mergeKey))
      val expected = Json.obj(mergeKey -> Json.arr())

      actual mustBe expected
    }

    "merge JsObject with JsObject" in {
      val jsObj1 = Json.obj(
        "merged" -> false,
        "shouldBeInResult" -> true
      )
      val jsObj2 = Json.obj("merged" -> true)

      val actual = JsonMergeService.merge(jsObj1, jsObj2)
      actual mustBe Json.obj("shouldBeInResult" -> true, "merged" -> true)
    }
  }

}
