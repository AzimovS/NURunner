import java.util.Random;
public abstract class AssessmentFactory{
	public Common common;

	public AssessmentFactory(Common common){
		this.common = common;
	}

	public abstract Assessment createAssessment(Vector2D position);

}

class LabFactory extends AssessmentFactory{
	public LabFactory(Common common){
		super(common);
	}

	public Assessment createAssessment(Vector2D position){
		Random random = new Random();
		int points = random.nextInt(3) + 2;
		return new Lab("Lab", position, new Stationary(), this.common, points);
	}
}

class QuizFactory extends AssessmentFactory{
	public QuizFactory(Common common){
		super(common);
	}

	public Assessment createAssessment(Vector2D position){
		Random random = new Random();
		int points = random.nextInt(3) + 3 ;
		return new Quiz("Quiz", position, new Stationary(), this.common, points);
	}
}

class HomeworkFactory extends AssessmentFactory{
	public HomeworkFactory(Common common){
		super(common);
	}

	public Assessment createAssessment(Vector2D position){
		Random random = new Random();
		int points = random.nextInt(3) + 1;
		return new Homework("HomeworkFactory", position, new Stationary(), this.common, points);
	}
}