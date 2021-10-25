package scala

import scala.io.Source

object Marche {
  def main(args: Array[String]): Unit = {
    //recuperer le fichier test dans le dossier ressources
    val lignes = Source.fromResource("test").getLines.toList
    val longueur = lignes.length

    if (longueur % 2 != 1) {
      //verifier le bon nombre de ligne du fichier test
      println("Revoir le format du fichier")
    }
    else {
      val taille = lignes.head
      val prog_list = lignes.takeRight(lignes.length - 1)
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
            println(s" ${instructionMarche(j).toString} : Action non reconnue")// dans le cas ou nous avons la bonne instruction
          }
        }
          // affiche la position de la tondeuse
        println(s"Tondeuse ${i + 1} : ${tondeuseMarche.position}")
      }
    }
  }
}
