// nom du package
package scala
// importation de la bibiothèque
import scala.io.Source
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
