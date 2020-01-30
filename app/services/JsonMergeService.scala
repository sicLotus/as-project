package services

import play.api.libs.json._

class JsonMergeService {

  /**
   * Different merge strategies are automatically applied based on the input argument types.
   */
  def merge(json1: JsValue, json2: JsValue, mergeKey:Option[String] = None): JsValue = {
    (json1, json2) match {
      case (jsObject: JsObject, jsObject2: JsObject) => mergeObjAndObj(jsObject, jsObject2)
      case (jsObject: JsObject, jsArray: JsArray) => mergeObjAndArr(jsObject, jsArray, mergeKey.getOrElse("mergeKey"))
      case (jsArray: JsArray, jsObject: JsObject) => mergeArrAndObj(jsArray, jsObject)
      case (jsArray: JsArray, jsArray2: JsArray) => mergeArrAndArr(jsArray, jsArray2)
    }
  }

  def mergeObjAndObj(jsObject: JsObject, jsObject2: JsObject): JsObject = {
    jsObject.deepMerge(jsObject2)
  }

  def mergeObjAndArr(jsObject: JsObject, jsArray: JsArray, mergeKey:String): JsObject = {
    jsObject + (mergeKey -> jsArray)
  }

  /**
   * TODO: Define merge strategy
   *   Merge strategy could be that the JsObject will be inserted into every item of JsArray
   *     [{}.deepMerge(JsObject), {}.deepMerge(JsObject), {}.deepMerge(JsObject), ...]
   *   Alternatively it could be the same implementation like ObjAndArr and just switching the arguments order
   */
  def mergeArrAndObj(jsArray: JsArray, jsObject: JsObject): Nothing = {
    ???
  }

  def mergeArrAndArr(jsArray: JsArray, jsArray2: JsArray): JsArray = {
    jsArray ++ jsArray2
  }

}