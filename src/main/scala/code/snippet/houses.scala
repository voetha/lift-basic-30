package code.snippet

import net.liftweb.common.{Box,Full,Empty}
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._
import net.liftweb.http.{RequestVar,SessionVar,SHtml,DispatchSnippet, StatefulSnippet,S, LiftScreen}
import code.model.House
import scala.xml.NodeSeq

object allHouses extends SessionVar[Box[List[House]]](Empty)

object house extends RequestVar[Box[String]](Empty)

class Houses  { //extends StatefulSnippet
  private var ho = new House("","",0,0,0)
    
//  def dispatch:DispatchIt = {
//    case "render" => render
//
//  }
//  def render:(NodeSeq)=>NodeSeq = {
//    houseList // & setupForm
//    
//  }
  
  def list = allHouses.is.map { hl =>
      "#houseList *" #> hl.map { h =>
        ".houseItem *" #> s"House: ${h.alias} , addr: ${h.shortAddr}"
      }
  } getOrElse "#houseList *" #> "No houses found"
  
//  private def setupForm = "name=alias" #> SHtml.text(ho.alias, a => ho = ho.copy(alias = a)) & 
//    "name=shortAddr" #> SHtml.text(ho.shortAddr, a => ho = ho.copy(shortAddr=a)) & 
//    "name=distSchool" #> SHtml.text(ho.distSchool.toString, asInt(_).map( d => ho = ho.copy(distSchool=d))) & 
//    "name=distStore" #> SHtml.text(ho.distStore.toString, asInt(_).map(d => ho = ho.copy(distStore = d))) & 
//    "name=distGe" #> SHtml.text(ho.distGe.toString, asInt(_).map(d => ho = ho.copy(distGe = d))) &
//    "type=submit" #> SHtml.onSubmitUnit(process)
//  

//  private def process() = {
//      println("Processing new house")
//      if (ho.alias == "") {
//        S.error("Alias is required")
//      } else {
//         println("Created a new house " + ho)
//        allHouses.set( Full(ho :: allHouses.get.getOrElse(Nil)))
//        ho = new House("","",0,0,0)
//      }
//      
//     
//    }
}

class HouseScreen extends LiftScreen {
  val name = field("alias","")
  val shortAddr = field("shortAddr","")
  val distSchool = field("Distance to School", 0, minVal(1, "Too Close, more than 1"))
  val distStore = field("Distance to Store", 0, minVal(2, "Too Close. more than 2"))
  val distGe = field("Distance to GE", 0, minVal(1, "Too Close, more than 2"))
  def finish() {
    val ho = new House(name.get,shortAddr.get,distSchool.get,distStore.get,distGe.get)
    
     println("Created a new house " + ho)
     allHouses.set( Full(ho :: allHouses.get.getOrElse(Nil)))
        
  }
  
  val formName = "Foobar"
}




