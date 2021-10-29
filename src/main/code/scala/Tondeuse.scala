package scala
import scala.io.Source
//definir class tondeuse
class Tondeuse(var x : Int, var y : Int, var oriente : String, val x_plan : Int, val y_plan : Int) {
  var x1 : Int = x
  var y1 : Int = y
  var orienter : String = oriente
  var position = s"$x1  $y1  $orienter"
// methode
  def avance(): Unit ={
    if ((orienter == "N") && (y1  < y_plan)){
      y1  = y1  + 1
      position = s"$x1 $y1 $orienter"
    } else if ((orienter == "S") && (y1  > 0)){
      y1  = y1  - 1
      position = s"$x1 $y1 $orienter"
    } else if ((orienter == "E") && (x1  < x_plan)){
      x1  = x1  + 1
      position = s"$x1 $y1 $orienter"
    } else if ((orienter == "W") && (x1  > 0)) {
      x1  = x1  - 1
      position = s"$x1 $y1 $orienter"
    }
  }

  def pivot(bord: String): Unit = { //Rotation de la tondeuse
    var n_oriente = "O" //initialisation
    if (bord == "G"){
      if (orienter == "N"){
        n_oriente = "W"
      } else if (orienter == "S"){
        n_oriente = "E"
      } else if (orienter == "E"){
        n_oriente = "N"
      } else if (orienter == "W") {
        n_oriente = "S"
      }
    }
    if (bord == "D"){
      if (orienter == "N"){
        n_oriente = "E"
      } else if (orienter == "S"){
        n_oriente = "W"
      } else if (orienter == "E"){
        n_oriente = "S"
      } else if (orienter == "W") {
        n_oriente = "N"
      }
    }
    orienter = n_oriente
    position = s"$x1  $y1  $orienter"
  }
}

// définition de l'objet qui va faire marcher les tondeuses
object Marche {
  def main(args: Array[String]): Unit = {
    //recuperer le fichier test dans le dossier ressources
    val lignes = Source.fromResource("test.txt").getLines.toList
    //récupérations de la longueur du fichier test (nombre de lignes)
    val longueur = lignes.length

    if (longueur % 2 != 1) {
      //verifier le bon nombre de ligne du fichier test elle doit avoir une taille de 2n +1
      println("Revoir le format du fichier")
    }
    else {
      val taille = lignes.head// recupérer la première ligne
      val prog_list = lignes.takeRight(lignes.length -1)// Rendre le fichier en liste sans ça prmière ligne
      // début de la double boucle pour le parcours de tous les champs
      for (i <- 0 until prog_list.length / 2) {

        //lecture de l'initialisation pour chaque tondeuse
        val x_Marche = prog_list(i * 2)(0) - '0' //transforme ASCII valeurs en integer
        val y_Marche = prog_list(i * 2)(2) - '0'
        val oriente_Marche = prog_list(i * 2)(4).toString
        //instanciation de chaque tondeuse  de la classe tondeuse
        val tondeuseMarche = new Tondeuse(x = x_Marche, y = y_Marche, oriente = oriente_Marche, x_plan = taille(0), y_plan = taille(2))
        //lire la liste des actions
        val instructionMarche = prog_list(i * 2 + 1)

        for (j <- 0 until instructionMarche.length) {
          //Appliquer les instructions
          if (instructionMarche(j).toString == "A") {
            tondeuseMarche.avance()
          }
          else if (instructionMarche(j).toString == "D") {
            tondeuseMarche.pivot("D")
          }
          else if (instructionMarche(j).toString == "G") {
            tondeuseMarche.pivot("G")
          }
          else {
            println(s" ${instructionMarche(j).toString} : Action non reconnue")// dans le cas ou nous avons pas la bonne instruction
          }
        }
        // affiche la position de la tondeuse
        println(s"Tondeuse ${i + 1} : ${tondeuseMarche.position}")
      }
    }
  }
}
