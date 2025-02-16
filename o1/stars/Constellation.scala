package o1.stars

import Ordering.Double.TotalOrdering

/** Each `Constellation` object represents a constellation visible on a star map.
  * A constellation consists of one or more imaginary “lines” between stars.
  *
  * @param name   the name of the constellation
  * @param lines  one or more pairs of stars; each pair defines an imaginary line between two stars */
class Constellation(val name: String, val lines: Vector[(Star,Star)]):

  /** All the stars in the constellation. That is, all the stars that are at one end
    * of any of the lines that form the constellation. */
  val stars: Set[Star] = lines.flatMap {
    case (star1, star2) => Seq(star1, star2) }.toSet // TODO: implement (in Chapter 10.1)

  /** A pair of [[StarCoords]] (X,Y), so that X is the smallest x coordinate of any star in the constellation
    * and Y the smallest y coordinate of any star in the constellation. */
  val minCoords = StarCoords(stars.map(_.coords.x).min, stars.map(_.coords.y).min)// TODO: replace with a proper implementation (in Chapter 10.1)

  /** A pair of [[StarCoords]] (X,Y), so that X is the greatest x coordinate of any star in the constellation
    * and Y the greatest y coordinate of any star in the constellation. */
  val maxCoords = StarCoords(stars.map(_.coords.x).max, stars.map(_.coords.y).max) // TODO: replace with a proper implementation (in Chapter 10.1)

  /** Determines whether the given coordinates are “roughly in the neighborhood” of this
    * constellation on the star map. */
  def isNearish(candidate: StarCoords) =
    def isBetween(value: Double, low: Double, high: Double) = value >= low && value < high
    val (min, max) = (this.minCoords, this.maxCoords)
    isBetween(candidate.x, min.x, max.x) && isBetween(candidate.y, min.y, max.y)

end Constellation

