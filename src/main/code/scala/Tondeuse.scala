package scala

//class tondeuse
class Tondeuse(var x : Int, var y : Int, var oriente : String, val x_plan : Int, val y_plan : Int) {
  var x_ : Int = x
  var y_ : Int = y
  var oriente_ : String = oriente
  var position = s"$x_ $y_ $oriente_"

  def avance(): Unit ={
    if ((oriente_ == "N") && (y_ < y_plan)){
      y_ = y_ + 1
      position = s"$x_ $y_ $oriente_"
    } else if ((oriente_ == "S") && (y_ > 0)){
      y_ = y_ - 1
      position = s"$x_ $y_ $oriente_"
    } else if ((oriente_ == "E") && (x_ < x_plan)){
      x_ = x_ + 1
      position = s"$x_ $y_ $oriente_"
    } else if ((oriente_ == "W") && (x_ > 0)) {
      x_ = x_ - 1
      position = s"$x_ $y_ $oriente_"
    }
  }

  def pivot(bord: String): Unit ={ //Rotation de la tondeuse
    var n_oriente = "O"
    if (bord == "G"){
      if (oriente_ == "N"){
        n_oriente = "W"
      } else if (oriente_ == "S"){
        n_oriente = "E"
      } else if (oriente_ == "E"){
        n_oriente = "N"
      } else if (oriente_ == "W") {
        n_oriente = "S"
      }
    }
    if (bord == "D"){
      if (oriente_ == "N"){
        n_oriente = "E"
      } else if (oriente_ == "S"){
        n_oriente = "W"
      } else if (oriente_ == "E"){
        n_oriente = "S"
      } else if (oriente_ == "W") {
        n_oriente = "N"
      }
    }
    oriente_ = n_oriente
    position = s"$x_ $y_ $oriente_"
  }
}

