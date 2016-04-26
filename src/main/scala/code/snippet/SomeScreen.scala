package code.snippet

import net.liftweb.http.js.JsCmds.SetHtml
import net.liftweb.http.{ LiftScreen, S, FieldBinding }
import scala.xml.{ NodeSeq, Text }
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import code.lib._
import Helpers._

class FooScreen extends LiftScreen {
  val shortAddr = field("Short Address", "", FieldBinding("shortAddr", FieldBinding.Template))
  val alias = field("Alias", "")
  val longAddr = field("Long Address", "")

  val formName = "customBinding"


  def finish() {
    //    AjaxOnDone.set(SetHtml("wizardAllResults", <b>All done!</b>))
    println("Called finish")
  }
}

class FooScreen2 extends LiftScreen {
  val shortAddr = field("Short Address", "", FieldBinding("shortAddr", FieldBinding.Template))
  // The FieldBinding.Self should be using the contents of the field block, e.g. 
  // <div id="customBinding_alias_field"> ... </div>
  val alias = field("Alias", "", FieldBinding("alias",FieldBinding.Self))
  val longAddr = field("Long Address", "")

  val formName = "customBinding"

  override def allTemplate = savedDefaultXml

  def finish() {
    //    AjaxOnDone.set(SetHtml("wizardAllResults", <b>All done!</b>))
    println("Called finish")
  }
}

