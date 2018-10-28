/**
 *
 * @author Kieron Ho
 *
 * A class for preparing and holding question data
 */
package questionservercommunication;

public class Question {

	private boolean isMulti;
	private String question;
	private String choiceOne;
	private String choiceTwo;
	private String choiceThree;
	private String choiceFour;
	private String questionString;

	/**
	 * Takes the full String and separates out the questions and possibly choices
	 * @param questionContainer is the String that holds the question and extra information
	 */
	public Question(String questionContainer){
		//redundant, but just in case it has more prefixes
		this.questionString = questionContainer;

		//check if the question is multiple choice, and strip that marker
		if(questionContainer.startsWith("[MULTI]")){
			isMulti = true;
			questionString = questionString.replace("[MULTI]", "");
		}
		else {
			isMulti = false;
			questionString = questionString.replace("[PROG]", "");
		}

		//extract the question using indexOf(String searchedString) and subString(int start, int end) if it is multiple choice
		if(!isMulti){
			question = questionString;
			choiceOne = null;
			choiceTwo = null;
			choiceThree = null;
			choiceFour = null;
		}
		else{
			//extract the question
			question = questionString.substring(0, questionString.indexOf("[CHOICE]"));
			//strip the question
			questionString = questionString.replace(question + "[CHOICE]", "");

			//get and set the choices
			choiceOne = questionString.substring(0, questionString.indexOf("[CHOICE]"));
			questionString = questionString.replace(choiceOne + "[CHOICE]", "");
			choiceTwo = questionString.substring(0, questionString.indexOf("[CHOICE]"));
			questionString = questionString.replace(choiceTwo + "[CHOICE]", "");
			choiceThree = questionString.substring(0, questionString.indexOf("[CHOICE]"));
			choiceFour = questionString.replace(choiceThree + "[CHOICE]", "");
		}
	}

/**
 * 
 * @return if the question is multiple-choice
 */
	public boolean isMulti(){
		return isMulti;
	}

	/**
	 * 
	 * @return the question String
	 */
	public String getQuestion(){
		return question;
	}

	/**
	 * 
	 * @return an answer possibility
	 */
	public String getChoiceOne(){
		return choiceOne;
	}

	/**
	 * 
	 * @return an answer possibility
	 */
	public String getChoiceTwo(){
		return choiceTwo;
	}

	/**
	 * 
	 * @return an answer possibility
	 */
	public String getChoiceThree(){
		return choiceThree;
	}

	/**
	 * 
	 * @return an answer possibility
	 */
	public String getChoiceFour(){
		return choiceFour;
	}
}
