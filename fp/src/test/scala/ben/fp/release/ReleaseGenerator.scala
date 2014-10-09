package ben.fp.release

import java.net.URI

import org.scalatest.{Matchers, WordSpec}

import scala.util.Try

class ReleaseGeneratorSpec extends WordSpec with Matchers {


  "should generate the release notes" in {

    ReleaseGenerator.jirafy(
      """
        |In Progress    DCJ-2324 Apply software updates to OSS servers as part of deploy [DCJDevOps] -- Platform
        |Blocked        DCJ-1649 Make router resilient [DCJDevOps] -- Security RTP
        |Soon           DCJ-3096 Create Jenkins job for Service Notification message [DCJDevOps]
        |Next           DCJ-1071 GOV.UK domain on current Production platform [DCJDevOps]
        |In Test        DCJ-2756 Polish Story - Sprint 46+ [DCJTeamA]
        |In Test        DCJ-3076 Add English Language Questions for T4 Students (pre April 2011) [DCJTeamA] -- T2 Switching
        |In Test        DCJ-3066 Migrate cid and email asynchronous sending to the submission service [DCJTeamA] -- Tech Debt
        |In Test        DCJ-3104 IE 8 is unable to download PDF application forms and checklists in the Journey [DCJTeamA] -- Production Support
        |In Test        DCJ-3080 Update switching routes for T2 General, Sportsperson, Minister of Religion and ICT Long Term [DCJTeamA]
        |In Test        DCJ-2957 Spike to investigate alternative ways to manage the product catalogue data [DCJTeamA] -- Platform
        |Ready To Amaze DCJ-3042 Switch from Tier 1 Post Study [DCJTeamA] -- T2 Switching
        |Ready To Amaze DCJ-728  Route standard AND Priority Service Applications to a Separate Mailboxes [DCJTeamA] -- Standard Service Applicants
        |Amazing        DCJ-2600 Create Case and Person in CID - Thick slice [DCJTeamA|DCJTeamC] -- CID Integration
        |Amazing        DCJ-1724 Remove "new routing" feature switch from Admin app [DCJTeamA] -- Tech Debt
        |Amazing        DCJ-3010 Antivirus causes low memory alerts on Loadbalancers and Jumpboxes  [DCJDevOps]
        |Amazing        DCJ-3062 Tier2 ICT long term visa displayed twice in the dropdown list [DCJTeamA] -- T2 Switching
        |Amazing        DCJ-2938 Catalogue timemachine [DCJTeamA] -- Platform
        |Amazing        DCJ-2375 Move db update scripts into their respective projects [DCJTeamA]
        |Amazing        DCJ-3075 Exceptional Talent English Language Level Bug [DCJTeamA] -- T2 Switching
        |Amazing        DCJ-2581 Remove Option[HomeOfficeProduct] [DCJTeamA] -- Tech Debt
        |Test           DCJ-2925 Enhancements to List Selector Pattern [DCJTeamA] -- Tech Debt
        |Test           DCJ-2711 Refactor Play template to Mustache: gdsSelect [DCJTeamA] -- Tech Debt
      """.stripMargin)

  }
}


object ReleaseGenerator {

  private def toLines(text: String): List[String] = text.split("\n").toList.filter(_.trim.nonEmpty).map {
    case line: String if line.nonEmpty => Some(line.trim)
    case _ => None
  } flatten


  private def toStories(line: String): Story = {
    toStory(line) getOrElse UnidentifieableStory(line)
  }

  private def toStory(line: String): Option[JiraStory] = {
    for {
      backlog <- Backlog parseToBacklog line
      number <- JiraNumber parseToJiraNumber line
    } yield JiraStory(number, "", backlog)
  }

  def jirafy(text: String) = {

    val lines = toLines(text)
    val (parsed: List[JiraStory], unparsed:List[UnidentifieableStory]) = (lines map toStories) partition (_.parsed)

    val unparsedLines: Int = unparsed.size
    val parsedLines: Int = parsed.size

    println(s"Progress [${parsedLines + unparsedLines}/${lines.size}], Parsable [$parsedLines] Unparsable [$unparsedLines] ")

    val storiesByBacklog = parsed.sortBy(_.number.value).groupBy(_.backlog.name)

    storiesByBacklog.map {
      case (backlog, stories) =>
        println(s"\nBacklog: $backlog")
        stories.map(story => println(story.number.uri.toString))
    }

    println("\nStories that couldn't be parsed")
    unparsed.map(println)

  }
}




object Snippet {
  def positionOf(toFind: String, line: String): Option[Snippet] = {
    val start: Int = line.indexOf(toFind)
    if (start == -1) None
    else Some(Snippet(line, start, start + toFind.length))
  }
}

case class Snippet(context: String, start: Int, end: Int) {

  def offset(addToStart: Int, addToEnd: Int): Snippet =
    this.copy(start = start + addToStart, end = end + addToEnd)


  def offsetContents(addToStart: Int, addToEnd: Int): String =
    copy(start = start + addToStart, end = end + addToEnd) contents

  val contents = context.substring(start, end).trim
}

case class JiraNumber(number: String, value: Int, uri: URI)

object JiraNumber {

  def parseToJiraNumber(snippet: Snippet): Option[JiraNumber] = Try {
    val dcjNumber = snippet.offset(0, 4)
    val uri: URI = new URI(s"https://jira.ipttools.info/browse/${dcjNumber.contents}")
    val dcjValue: Int = Integer.parseInt(dcjNumber.offsetContents(4, 0))

    JiraNumber(dcjNumber.contents, dcjValue, uri)
  } toOption

  def parseToJiraNumber(line: String): Option[JiraNumber] =
    Snippet positionOf("DCJ-", line) flatMap parseToJiraNumber
}

case class Backlog(name: String, line: Snippet)

object Backlog {

  val names: Seq[String] = Seq("DCJTeamA", "DCJDevOps", "DCJTeamC")

  def parseToBacklog(line: String, backlogNames: Seq[String] = names): Option[Backlog] = {
    (backlogNames.map(Snippet.positionOf(_, line)) flatten).headOption map {
      case subString => Backlog(subString.contents, subString)
    }
  }
}

object Status {
  val statuses = Seq("In Progress", "Blocked", "Soon", "Next", "Ready To Amaze", "Amazing", "Test")
  def parseStatus(line:String) = ???
}

sealed trait Story {
  def jiraNumberValue: Int
  def parsed: Boolean
}

case class JiraStory(number: JiraNumber, description: String, backlog: Backlog) extends Story {
  override def jiraNumberValue = number.value
  override def parsed: Boolean = true
}

case class UnidentifieableStory(text: String) extends Story {
  override def jiraNumberValue = Int.MaxValue
  override def parsed: Boolean = false
}

