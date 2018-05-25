package otg.learningconcurrency.chap2
import otg.learningconcurrency.chap2.ThreadUtils._

object ThreadsMain extends App {
  val t: Thread = Thread.currentThread
  val name = t.getName
  println(s"I a m the thread $name")
}

object ThreadsCreation extends App {

  class MyThread extends Thread {
    override def run(): Unit = {
      println("New Thread running.")
    }
  }

  val t = new MyThread
  t.start()
  t.join()
  println("New thread joined.")
}

object ThreadUtils{
  def thread(body: =>Unit): Thread = {
    val t = new Thread {
      override def run() = body
    }
    t.start()
    t
  }
  def log(mess:String)={println(mess)}

}

object ThreadsSleep extends App{
  import ThreadUtils._


  val t = thread{
    Thread.sleep(1000)
     log("New thread running.")
    Thread.sleep(1000)
    log("still running.")
    Thread.sleep(1000)
    log("Completed.")
  }
  t.join()
  log("New Thread joined")
}

object ThreadsCommunicate extends App {
  var result :String = null
  val t= thread { result = "\nTitle\n" + "=" * 5}
  t.join()
  log (result)
}

object ThreadsUnprotectedUid extends App {
  var uidCount = 0L

  def getUniqueId() = {
    val freshUid = uidCount + 1
    uidCount = freshUid
    freshUid
  }

  def printUniquesIds(n:Int) : Unit = {
    val uids = for(i<- 0 until n) yield getUniqueId()
    log(s"Generated uids: $uids")
  }
  val t = thread(printUniquesIds(5))
    printUniquesIds(5)
  t.join
}
