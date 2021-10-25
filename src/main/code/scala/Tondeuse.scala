package scala

//class tondeuse
class Tondeuse(var x : Int, var y : Int, var oriente : String, val x_plan : Int, val y_plan : Int) {
  var x1 : Int = x
  var y1 : Int = y
  var oriente_T : String = oriente
  var position = s"$x1  $y1  $oriente_T"

  def avance(): Unit ={
    if ((oriente_T == "N") && (y1  < y_plan)){
      y1  = y1  + 1
      position = s"$x1 $y1 $oriente_T"
    } else if ((oriente_T == "S") && (y1  > 0)){
      y1  = y1  - 1
      position = s"$x1 $y1 $oriente_T"
    } else if ((oriente_T == "E") && (x1  < x_plan)){
      x1  = x1  + 1
      position = s"$x1 $y1 $oriente_T"
    } else if ((oriente_T == "W") && (x1  > 0)) {
      x1  = x1  - 1
      position = s"$x1 $y1 $oriente_T"
    }
  }

  def pivot(bord: String): Unit = { //Rotation de la tondeuse
    var n_oriente = "O" //initialisation
    if (bord == "G"){
      if (oriente_T == "N"){
        n_oriente = "W"
      } else if (oriente_T == "S"){
        n_oriente = "E"
      } else if (oriente_T == "E"){
        n_oriente = "N"
      } else if (oriente_T == "W") {
        n_oriente = "S"
      }
    }
    if (bord == "D"){
      if (oriente_T == "N"){
        n_oriente = "E"
      } else if (oriente_T == "S"){
        n_oriente = "W"
      } else if (oriente_T == "E"){
        n_oriente = "S"
      } else if (oriente_T == "W") {
        n_oriente = "N"
      }
    }
    oriente_T = n_oriente
    position = s"$x1  $y1  $oriente_T"
  }
}

