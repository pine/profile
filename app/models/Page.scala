package models

sealed abstract class Page(
  val key: String,
  val path: String,
  val title: String
)

object Page {
  case object Index extends Page("index", "/", "トップページ")
  case object Program extends Page("program", "/program", "プログラミング")
  case object Anime extends Page("anime", "/anime", "アニメ")
  case object LightNovel extends Page("light_novel", "/light_novel", "ライトノベル")
  case object Link extends Page("link", "/link", "リンク")
  case object SiteInfo extends Page("site_info", "/site_info", "サイト情報")

  val values = Array(Index, Program, Anime, LightNovel, Link, SiteInfo)

  def of(key: String) = this.values.find(_.key == key)
}
