package models

import scala.util.Random

object Background {
  val paths = Array(
    "yun_1375.jpg",
    "yun_7604.jpg",
    "yun_3218.jpg",
    "yun_1077.jpg",
    "yun_3281.jpg",
  )

  def choice() = this.paths(Random.nextInt(this.paths.size))
}
