/**
 *
 * @author Kieron Ho
 *
 *	This class simplifies communication between the web server and the question server by
 *	making a simple send and receive system
 */
package questionservercommunication;

public class TalkToQuestionServer{

	/**
	 * 
	 * @param studentNumber
	 * @param questionNumber
	 * @return a Question object that holds all relevant question information and getters
	 */
  public static Question getQuestion(String studentNumber, String questionNumber){
	  SSLClient questionSession = new SSLClient();
    return new Question(questionSession.relayMessage(studentNumber + "::sendQues::" + questionNumber));
}

  	/**
  	 * 
  	 * @param studentNumber
  	 * @param userAnswer
  	 * @return The response from the question server ("true" or "false")
  	 */
public static String isAnswerCorrect(String studentNumber, String userAnswer){
	SSLClient answerSession = new SSLClient();
	return answerSession.relayMessage(studentNumber + "::markQues::" + userAnswer);
}
}
