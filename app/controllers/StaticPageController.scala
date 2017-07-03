package controllers

import javax.inject._
import play.api.mvc._
import models._

@Singleton
class StaticPageController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def show(key: String) = Action {
    Page.of(key) match {
      case Some(page) => {
        page match {
          case Page.Index => Ok(views.html.index())
          case Page.Program => Ok(views.html.program())
          case Page.Anime => Ok(views.html.anime())
          case Page.LightNovel => Ok(views.html.light_novel())
          case Page.Link => Ok(views.html.link())
          case Page.SiteInfo => Ok(views.html.site_info())
          case _ => NotFound
        }
      }
      case None => NotFound
    }
  }

}
